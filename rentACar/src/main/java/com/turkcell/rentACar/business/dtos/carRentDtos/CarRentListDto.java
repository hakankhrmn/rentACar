package com.turkcell.rentACar.business.dtos.carRentDtos;

import java.time.LocalDate;
import java.util.List;

import com.turkcell.rentACar.business.dtos.additionalServiceDtos.AdditionalServiceListDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRentListDto {

	private int carRentId;

	private String description;
	
	private String rentCity;

	private String returnCity;
	
	private LocalDate rentDate;

	private LocalDate returnDate;

	private int carCarId;
	
	private List<AdditionalServiceListDto> additionalServiceListDtos;
}
