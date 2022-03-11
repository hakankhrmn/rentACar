package com.turkcell.rentACar.business.dtos.orderedAdditionalServiceDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderedAdditionalServiceDto {

	private int carRentId;
	private int additionalServiceId;
}
