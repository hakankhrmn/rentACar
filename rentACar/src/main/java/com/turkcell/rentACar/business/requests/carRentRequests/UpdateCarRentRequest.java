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
public class UpdateCarRentRequest {
	
	@NotNull
	@Min(1)
	private int carRentId;

	private String description;

	private int rentCityId;

	private int returnCityId;

	private LocalDate rentDate;

	private LocalDate returnDate;

	private double returnKilometer;

	private int carId;
	
	private List<OrderedAdditionalServiceRequest> orderedAdditionalServiceRequests;
}
