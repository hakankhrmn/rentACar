package com.turkcell.rentACar.business.concretes;

import com.turkcell.rentACar.business.abstracts.InvoiceService;
import com.turkcell.rentACar.business.abstracts.IsbankPosService;
import com.turkcell.rentACar.business.abstracts.PaymentService;
import com.turkcell.rentACar.business.abstracts.ZiraatBankPosService;
import com.turkcell.rentACar.business.dtos.paymentDtos.GetPaymentDto;
import com.turkcell.rentACar.business.dtos.paymentDtos.PaymentListDto;
import com.turkcell.rentACar.business.dtos.posDtos.PosDto;
import com.turkcell.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.turkcell.rentACar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
import com.turkcell.rentACar.dataAccess.abstracts.PaymentDao;
import com.turkcell.rentACar.entities.concretes.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.turkcell.rentACar.business.constants.messages.BusinessMessages.*;

@Service
public class PaymentManager implements PaymentService {

    private PaymentDao paymentDao;
    private ModelMapperService modelMapperService;
    private ZiraatBankPosService ziraatBankPosService;
    private IsbankPosService isbankPosService;
    private InvoiceService invoiceService;

    @Autowired
    public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService, ZiraatBankPosService ziraatBankPosService, IsbankPosService isbankPosService, InvoiceService invoiceService) {
        this.paymentDao = paymentDao;
        this.modelMapperService = modelMapperService;
        this.ziraatBankPosService = ziraatBankPosService;
        this.isbankPosService = isbankPosService;
        this.invoiceService = invoiceService;
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

        if (pos(createPaymentRequest.getPostDto())){
            paymentDao.save(payment);
        }

        return new SuccessResult(SUCCESS_ADD_PAYMENT);
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

    private boolean pos(PosDto posDto) {
        return ziraatBankPosService.pos(posDto).isSuccess();
    }
}
