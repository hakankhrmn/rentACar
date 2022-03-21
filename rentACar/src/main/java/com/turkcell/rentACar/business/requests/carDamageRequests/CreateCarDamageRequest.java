package com.turkcell.rentACar.business.requests.carDamageRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDamageRequest {

    private int carDamageId;

    private String carDamageInformation;

    private int carId;
}
