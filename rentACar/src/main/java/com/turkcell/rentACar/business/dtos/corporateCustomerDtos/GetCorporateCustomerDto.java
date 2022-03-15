package com.turkcell.rentACar.business.dtos.corporateCustomerDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCorporateCustomerDto {

	private int corporateCustomerId;
	
	private String companyName;
	
	private String taxNumber;
}
