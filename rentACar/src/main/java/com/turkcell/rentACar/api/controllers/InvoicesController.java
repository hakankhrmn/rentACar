package com.turkcell.rentACar.api.controllers;

import com.turkcell.rentACar.business.abstracts.InvoiceService;
import com.turkcell.rentACar.business.dtos.invoiceDtos.GetInvoiceDto;
import com.turkcell.rentACar.business.dtos.invoiceDtos.InvoiceListDto;
import com.turkcell.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoicesController {

    private InvoiceService invoiceService;

    @Autowired
    public InvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/getall")
    public DataResult<List<InvoiceListDto>> getAll() {
        return invoiceService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateInvoiceRequest createInvoiceRequest) {
        return invoiceService.add(createInvoiceRequest);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@RequestParam int id) {
        return invoiceService.delete(id);
    }

    @GetMapping("/getbyid/{id}")
    public DataResult<GetInvoiceDto> getById(@RequestParam int id) {
        return invoiceService.getById(id);
    }
}
