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
public class CreateCarRentRequest {

	private String description;
	
	@NotNull
	private int rentCityId;

	@NotNull
	private int returnCityId;

	@NotNull
	private LocalDate rentDate;

	@NotNull
	private LocalDate returnDate;

	@NotNull
	@Min(1)
	private int carId;
	
	private List<OrderedAdditionalServiceRequest> orderedAdditionalServiceRequests;
	
}
