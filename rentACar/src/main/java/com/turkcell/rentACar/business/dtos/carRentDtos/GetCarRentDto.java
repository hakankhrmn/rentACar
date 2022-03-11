package com.turkcell.rentACar.business.dtos.carRentDtos;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarRentDto {

	private int carRentId;

	private String description;
	
	private double totalPrice;
	
	private String rentCity;

	private String returnCity;

	private LocalDate rentDate;

	private LocalDate returnDate;

	private int carId;
	
}
