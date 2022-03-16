package com.turkcell.rentACar.business.concretes;

import com.turkcell.rentACar.business.abstracts.CarRentService;
import com.turkcell.rentACar.business.abstracts.CustomerService;
import com.turkcell.rentACar.business.abstracts.InvoiceService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceManager implements InvoiceService {

    private InvoiceDao invoiceDao;
    private ModelMapperService modelMapperService;
    private CarRentService carRentService;
    private CustomerService customerService;

    @Autowired
    public InvoiceManager(InvoiceDao invoiceDao, ModelMapperService modelMapperService, @Lazy CarRentService carRentService, CustomerService customerService) {
        this.invoiceDao = invoiceDao;
        this.modelMapperService = modelMapperService;
        this.carRentService = carRentService;
        this.customerService = customerService;
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
        return new SuccessDataResult<>("Getting invoice by id: " + id);
    }


}
