package com.turkcell.rentACar.business.abstracts;

import com.turkcell.rentACar.business.dtos.paymentDtos.GetPaymentDto;
import com.turkcell.rentACar.business.dtos.paymentDtos.PaymentListDto;
import com.turkcell.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;

import java.util.List;

public interface PaymentService {
    DataResult<List<PaymentListDto>> getAll();
    Result add(CreatePaymentRequest createPaymentRequest);
    Result delete(int id);
    DataResult<GetPaymentDto> getByInvoiceId(int id);
}
