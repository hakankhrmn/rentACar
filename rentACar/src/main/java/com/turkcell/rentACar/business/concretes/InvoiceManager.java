package com.turkcell.rentACar.business.concretes;

import com.turkcell.rentACar.business.abstracts.*;
import com.turkcell.rentACar.business.dtos.invoiceDtos.GetInvoiceDto;
import com.turkcell.rentACar.business.dtos.invoiceDtos.InvoiceListDto;
import com.turkcell.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.turkcell.rentACar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
import com.turkcell.rentACar.dataAccess.abstracts.InvoiceDao;
import com.turkcell.rentACar.entities.concretes.CarRent;
import com.turkcell.rentACar.entities.concretes.Customer;
import com.turkcell.rentACar.entities.concretes.Invoice;
import com.turkcell.rentACar.entities.concretes.OrderedAdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class InvoiceManager implements InvoiceService {

    private InvoiceDao invoiceDao;
    private ModelMapperService modelMapperService;
    private CarRentService carRentService;
    private CustomerService customerService;
    private IndividualCustomerService individualCustomerService;
    private CorporateCustomerService corporateCustomerService;
    private CarService carService;
    private OrderedAdditionalServiceService orderedAdditionalServiceService;
    private AdditionalServiceService additionalServiceService;

    @Autowired
    public InvoiceManager(InvoiceDao invoiceDao,
                          ModelMapperService modelMapperService,
                          @Lazy CarRentService carRentService,
                          CustomerService customerService,
                          IndividualCustomerService individualCustomerService,
                          CorporateCustomerService corporateCustomerService,
                          CarService carService,
                          OrderedAdditionalServiceService orderedAdditionalServiceService,
                          AdditionalServiceService additionalServiceService
                          ) {
        this.invoiceDao = invoiceDao;
        this.modelMapperService = modelMapperService;
        this.carRentService = carRentService;
        this.customerService = customerService;
        this.individualCustomerService = individualCustomerService;
        this.corporateCustomerService = corporateCustomerService;
        this.carService = carService;
        this.orderedAdditionalServiceService = orderedAdditionalServiceService;
        this.additionalServiceService = additionalServiceService;
    }

    @Override
    public DataResult<List<InvoiceListDto>> getAll() {
        List<Invoice> invoices = invoiceDao.findAll();
        List<InvoiceListDto> invoiceListDtos = invoices.stream()
                .map(invoice -> modelMapperService.forDto().map(invoice, InvoiceListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(invoiceListDtos, "Successfully listed invoices");
    }

    @Override
    public Result add(CreateInvoiceRequest createInvoiceRequest) {
        Invoice invoice = modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
        CarRent carRent = carRentService.getByCarRentId(createInvoiceRequest.getCarRentCarRentId());
        Customer customer = customerService.getCustomerById(createInvoiceRequest.getCustomerId());

        invoice.setInvoiceNumber(generateInvoiceNo());
        invoice.setInvoiceDate(carRent.getReturnDate());
        invoice.setTotalRentDay((int) DAYS.between(carRent.getRentDate(), carRent.getReturnDate()));
        invoice.setTotalPayment(calculateTotalPayment(createInvoiceRequest.getCustomerId(), carRent));
        invoice.setCarRent(carRent);
        invoice.setCustomer(customer);
        invoiceDao.save(invoice);
        return new SuccessResult("Successfully added invoices");
    }

    @Override
    public Result delete(int id) {
        invoiceDao.deleteById(id);
        return new SuccessResult("Successfully deleted invoices");
    }

    @Override
    public DataResult<GetInvoiceDto> getById(int id) {
        Invoice invoice = invoiceDao.getById(id);
        GetInvoiceDto getInvoiceDto = modelMapperService.forDto().map(invoice, GetInvoiceDto.class);
        return new SuccessDataResult<>(getInvoiceDto, "Getting invoice by id: " + id);
    }

    private double calculateTotalPayment(int customerId, CarRent carRent) {
        double totalPayment = 0;
        long days = DAYS.between(carRent.getRentDate(), carRent.getReturnDate());
        totalPayment += calculatePaymentForCar(carRent.getCar().getCarId(), days);
        totalPayment += calculatePaymentForAdditionalService(carRent.getCarRentId(), days);
        totalPayment += calculatePaymentForCity(carRent);
        totalPayment += calculatePaymentForCustomer(customerId);
        return totalPayment;
    }

    private double calculatePaymentForCustomer(int customerId) {
        double totalPayment = 0;
        if (individualCustomerService.existsByIndividualCustomerId(customerId)) {
            totalPayment += 20;
            return totalPayment;
        }

        if (corporateCustomerService.existsByCorporateCustomerId(customerId)) {
            totalPayment += 10;
            return totalPayment;
        }
        return totalPayment;
    }

    private double calculatePaymentForAdditionalService(int carRentId, long days) {
        double totalPayment = 0;
        List<OrderedAdditionalService> orderedAdditionalServices = orderedAdditionalServiceService.getByCarRent_CarRentId(carRentId);
        for (OrderedAdditionalService orderedAdditionalService : orderedAdditionalServices) {
            totalPayment += additionalServiceService.getById(orderedAdditionalService.getAdditionalService().getAdditionalServiceId()).getData().getAdditionalServiceDailyPrice() * days;
        }
        return totalPayment;
    }

    private double calculatePaymentForCar(int carId, long days) {
        double totalPayment = 0;
        totalPayment += carService.getById(carId).getData().getDailyPrice() * days;
        return totalPayment;
    }

    private double calculatePaymentForCity(CarRent carRent ) {
        double totalPayment = 0;
        if (!carRent.getRentCity().equals(carRent.getReturnCity())) {
            totalPayment += 750;
        }
        return totalPayment;
    }

    private String generateInvoiceNo() {
        String invoiceNumber;
        String numbers = "0123456789";

        StringBuilder sb = new StringBuilder();

        Random random = new Random();

        int length = 10;

        while (true) {
            for(int i = 0; i < length; i++) {

                int index = random.nextInt(numbers.length());

                char randomChar = numbers.charAt(index);

                sb.append(randomChar);
            }

            invoiceNumber = sb.toString();

            if (checkIfExistsByInvoiceNumber(invoiceNumber)) {
                break;
            }
        }


        return invoiceNumber;
    }

    private boolean checkIfExistsByInvoiceNumber(String invoiceNumber) {
        Invoice invoice = invoiceDao.findByInvoiceNumber(invoiceNumber);
        if (invoice == null) {
            return false;
        }
        return true;
    }


}
