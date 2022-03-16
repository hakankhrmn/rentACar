package com.turkcell.rentACar.api.models;

import com.turkcell.rentACar.business.requests.carRentRequests.CreateCarRentRequest;
import lombok.Data;

@Data

public class CreateRentalRequest {

    private int customerId;
    private CreateCarRentRequest createCarRentRequest;

}
