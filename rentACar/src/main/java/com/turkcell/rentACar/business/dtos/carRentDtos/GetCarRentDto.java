package com.turkcell.rentACar.business.dtos.carRentDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarRentDto {

	private int carRentId;

	private String description;
	
	private String rentCityCityName;

	private String returnCityCityName;

	private LocalDate rentDate;

	private LocalDate returnDate;

	private int carId;
	
}
