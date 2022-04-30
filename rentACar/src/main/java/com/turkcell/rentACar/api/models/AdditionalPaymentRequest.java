package com.turkcell.rentACar.api.models;

import com.turkcell.rentACar.business.dtos.cardDtos.CardDto;
import com.turkcell.rentACar.business.requests.carRentRequests.UpdateReturnCarRentRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalPaymentRequest {
    UpdateReturnCarRentRequest updateReturnCarRentRequest;
    private int customerId;
    private CardDto cardDto;
}
