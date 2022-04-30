package com.turkcell.rentACar.business.requests.paymentRequests;

import com.turkcell.rentACar.business.dtos.cardDtos.CardDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {

    private CardDto cardDto;
    private int invoiceId;
}
