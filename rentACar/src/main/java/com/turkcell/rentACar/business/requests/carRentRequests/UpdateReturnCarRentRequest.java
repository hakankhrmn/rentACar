package com.turkcell.rentACar.business.requests.carRentRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReturnCarRentRequest {

    @NotNull
    @Min(1)
    private int carRentId;

    private int returnCityId;

    private LocalDate returnDate;

    private double returnKilometer;
}
