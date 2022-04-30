package com.turkcell.rentACar.business.concretes;

import com.turkcell.rentACar.api.models.AdditionalPaymentRequest;
import com.turkcell.rentACar.api.models.PaymentRequest;
import com.turkcell.rentACar.business.abstracts.*;
import com.turkcell.rentACar.business.dtos.carRentDtos.GetCarRentDto;
import com.turkcell.rentACar.business.dtos.invoiceDtos.GetInvoiceDto;
import com.turkcell.rentACar.business.dtos.paymentDtos.GetPaymentDto;
import com.turkcell.rentACar.business.dtos.paymentDtos.PaymentListDto;
import com.turkcell.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.turkcell.rentACar.business.requests.orderedAdditionalServiceRequests.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.turkcell.rentACar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
import com.turkcell.rentACar.dataAccess.abstracts.PaymentDao;
import com.turkcell.rentACar.entities.concretes.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.turkcell.rentACar.business.constants.messages.BusinessMessages.*;

@Service
public class PaymentManager implements PaymentService {

    private PaymentDao paymentDao;
    private ModelMapperService modelMapperService;
    private PosService posService;
    private InvoiceService invoiceService;
    private CarRentService carRentService;
    private CustomerService customerService;
    private OrderedAdditionalServiceService orderedAdditionalServiceService;

    @Autowired
    public PaymentManager(PaymentDao paymentDao,
                          ModelMapperService modelMapperService,
                          PosService posService,
                          InvoiceService invoiceService,
                          CarRentService carRentService,
                          OrderedAdditionalServiceService orderedAdditionalServiceService,
                          CustomerService customerService) {
        this.paymentDao = paymentDao;
        this.modelMapperService = modelMapperService;
        this.posService = posService;
        this.invoiceService = invoiceService;
        this.carRentService = carRentService;
        this.customerService = customerService;
        this.orderedAdditionalServiceService = orderedAdditionalServiceService;
    }

    @Override
    public DataResult<List<PaymentListDto>> getAll() {
        List<Payment> payments = paymentDao.findAll();
        List<PaymentListDto> paymentListDtos = payments.stream()
                .map(payment -> modelMapperService.forDto().map(payment, PaymentListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(paymentListDtos, SUCCESS_GET_ALL_PAYMENT);
    }

    @Override
    public Result add(CreatePaymentRequest createPaymentRequest) {

        Payment payment = modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
        payment.setPaymentDate(LocalDate.now());

        paymentDao.save(payment);
        return new SuccessResult(SUCCESS_ADD_PAYMENT);
    }

    @Override
    public Result pay(PaymentRequest paymentRequest) {
        Result result = posService.pos(paymentRequest.getCardDto());

        if (result.isSuccess()){
            runPaymentSuccessor(paymentRequest);
        }

        return new SuccessResult(SUCCESS_PAYMENT);
    }

    @Override
    public Result payAdditional(AdditionalPaymentRequest additionalPaymentRequest) {
        Result result = posService.pos(additionalPaymentRequest.getCardDto());

        if (result.isSuccess()){
            runAdditionalPaymentSuccessor(additionalPaymentRequest);
        }

        return new SuccessResult(SUCCESS_PAYMENT);
    }

    @Transactional(rollbackFor = BusinessException.class)
    public void runPaymentSuccessor(PaymentRequest paymentRequest) {

        //rental ekle
        GetCarRentDto carRentDto = (GetCarRentDto) carRentService.add(paymentRequest.getCreateCarRentRequest()).getData();

        //add additional service
        List<CreateOrderedAdditionalServiceRequest> createOrderedAdditionalServiceRequests = paymentRequest.getCreateCarRentRequest().getOrderedAdditionalServiceRequests().stream()
                .map(orderedAdditionalServiceRequest -> this.modelMapperService.forDto().map(orderedAdditionalServiceRequest, CreateOrderedAdditionalServiceRequest.class)).collect(Collectors.toList());

        carRentService.checkIfCarRentExists(carRentDto.getCarRentId());

        createOrderedAdditionalServiceRequests.forEach(createOrderedAdditionalServiceRequest -> createOrderedAdditionalServiceRequest.setCarRentId(carRentDto.getCarRentId()));
        orderedAdditionalServiceService.addAll(createOrderedAdditionalServiceRequests);

        //add invoice

        int customerId = paymentRequest.getCustomerId();
        System.out.println(customerId);
        customerService.checkIfCustomerExists(customerId);

        CreateInvoiceRequest createInvoiceRequest = new CreateInvoiceRequest(paymentRequest.getCustomerId(), carRentDto.getCarRentId());
        GetInvoiceDto invoiceDto = (GetInvoiceDto) invoiceService.add(createInvoiceRequest).getData();

        //add payment

        invoiceService.checkIfInvoiceExists(invoiceDto.getInvoiceId());

        CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest(paymentRequest.getCardDto(), invoiceDto.getInvoiceId());
        this.add(createPaymentRequest);
    }

    @Transactional(rollbackFor = BusinessException.class)
    public void runAdditionalPaymentSuccessor(AdditionalPaymentRequest additionalPaymentRequest) {

        //rental ekle
        GetCarRentDto carRentDto = (GetCarRentDto) carRentService.returnCarRent(additionalPaymentRequest.getUpdateReturnCarRentRequest()).getData();

        //add additional service
        List<CreateOrderedAdditionalServiceRequest> createOrderedAdditionalServiceRequests = additionalPaymentRequest.getUpdateReturnCarRentRequest().getOrderedAdditionalServiceRequests().stream()
                .map(orderedAdditionalServiceRequest -> this.modelMapperService.forDto().map(orderedAdditionalServiceRequest, CreateOrderedAdditionalServiceRequest.class)).collect(Collectors.toList());

        carRentService.checkIfCarRentExists(carRentDto.getCarRentId());

        createOrderedAdditionalServiceRequests.forEach(createOrderedAdditionalServiceRequest -> createOrderedAdditionalServiceRequest.setCarRentId(carRentDto.getCarRentId()));
        orderedAdditionalServiceService.addAll(createOrderedAdditionalServiceRequests);

        //add invoice

        customerService.checkIfCustomerExists(additionalPaymentRequest.getCustomerId());

        CreateInvoiceRequest createInvoiceRequest = new CreateInvoiceRequest(additionalPaymentRequest.getCustomerId(), carRentDto.getCarRentId());
        GetInvoiceDto invoiceDto = (GetInvoiceDto) invoiceService.add(createInvoiceRequest).getData();

        //add payment

        invoiceService.checkIfInvoiceExists(invoiceDto.getInvoiceId());

        CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest(additionalPaymentRequest.getCardDto(), invoiceDto.getInvoiceId());
        this.add(createPaymentRequest);
    }

    @Override
    public Result delete(int id) {
        paymentDao.deleteById(id);
        return new SuccessResult(SUCCESS_DELETE_PAYMENT);
    }

    @Override
    public DataResult<GetPaymentDto> getByInvoiceId(int id) {
        Payment payment = paymentDao.findByInvoice_InvoiceId(id);
        GetPaymentDto getPaymentDto = modelMapperService.forDto().map(payment, GetPaymentDto.class);
        return new SuccessDataResult<>(getPaymentDto, SUCCESS_GET_BY_ID_PAYMENT);
    }

    @Override
    public void checkIfPaymentExists(int id) {
        if (!this.paymentDao.existsById(id)) {
            throw new BusinessException(ERROR_PAYMENT_DOES_NOT_EXISTS);
        }
    }
}
