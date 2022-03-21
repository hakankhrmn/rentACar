package com.turkcell.rentACar.business.dtos.carRentDtos;

import com.turkcell.rentACar.business.dtos.additionalServiceDtos.AdditionalServiceListDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRentListDto {

	private int carRentId;

	private String description;
	
	private String rentCityName;

	private String returnCityName;
	
	private LocalDate rentDate;

	private LocalDate returnDate;

	private double rentStartKilometer;

	private double returnKilometer;

	private int carCarId;
	
	private List<AdditionalServiceListDto> additionalServiceListDtos;
}
