package com.turkcell.rentACar.business.dtos.carMaintenanceDtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceListDto {
	
	private int carMaintenanceId;
	
	private String description;
	
	private LocalDate maintenanceDate;
	
	private LocalDate returnDate;
	
	private int carId;
	
}
