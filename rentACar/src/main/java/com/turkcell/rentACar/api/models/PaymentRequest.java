package com.turkcell.rentACar.api.models;

import com.turkcell.rentACar.business.dtos.cardDtos.CardDto;
import com.turkcell.rentACar.business.requests.carRentRequests.CreateCarRentRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    CreateCarRentRequest createCarRentRequest;
    private int customerId;
    private CardDto cardDto;

}
