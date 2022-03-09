package com.turkcell.rentACar.business.requests.carRentRequests;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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

	@NotNull
	private LocalDate rentDate;

	@NotNull
	private LocalDate returnDate;

	@NotNull
	@Min(1)
	private int carId;
}
