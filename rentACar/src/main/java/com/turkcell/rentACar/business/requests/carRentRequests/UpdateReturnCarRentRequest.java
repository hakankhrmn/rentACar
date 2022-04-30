package com.turkcell.rentACar.business.requests.carRentRequests;

import com.turkcell.rentACar.business.requests.orderedAdditionalServiceRequests.OrderedAdditionalServiceRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

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

    private List<OrderedAdditionalServiceRequest> orderedAdditionalServiceRequests;
}
