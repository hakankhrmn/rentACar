package com.turkcell.rentACar.business.dtos.carRentDtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRentListDto {

	private int carRentId;

	private String description;

	private LocalDate rentDate;

	private LocalDate returnDate;

	private int carId;
}
