package com.turkcell.rentACar.api.controllers;

import com.turkcell.rentACar.business.abstracts.PaymentService;
import com.turkcell.rentACar.business.dtos.paymentDtos.GetPaymentDto;
import com.turkcell.rentACar.business.dtos.paymentDtos.PaymentListDto;
import com.turkcell.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {

    private PaymentService paymentService;

    @Autowired
    public PaymentsController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/getall")
    public DataResult<List<PaymentListDto>> getAll() {

        return paymentService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreatePaymentRequest createPaymentRequest) {

        return paymentService.add(createPaymentRequest);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@RequestParam int id) {

        return paymentService.delete(id);
    }

    @GetMapping("/getByInvoiceId/{id}")
    public DataResult<GetPaymentDto> getByInvoiceId(@RequestParam int id) {

        return paymentService.getByInvoiceId(id);
    }
}
