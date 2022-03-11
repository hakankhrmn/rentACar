package com.turkcell.rentACar.business.requests.carRentRequests;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.turkcell.rentACar.business.requests.orderedAdditionalServiceRequests.OrderedAdditionalServiceRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRentRequest {
	
	@NotNull
	@Min(1)
	private int carRentId;

	private String description;
	
	private String rentCity;

	private String returnCity;

	private LocalDate rentDate;

	private LocalDate returnDate;

	@Min(1)
	private int carId;
	
	private List<OrderedAdditionalServiceRequest> orderedAdditionalServiceRequests;
}
