package com.turkcell.rentACar.business.requests.paymentRequests;

import com.turkcell.rentACar.business.dtos.posDtos.PosDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {

    private PosDto posDto;
    private int invoiceId;
}
