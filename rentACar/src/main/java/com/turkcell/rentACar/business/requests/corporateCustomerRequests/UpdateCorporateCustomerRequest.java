package com.turkcell.rentACar.business.requests.corporateCustomerRequests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateCustomerRequest {
	
	private int corporateCustomerId;
	
	private String companyName;
	
	private String taxNumber;

	private String email;

	private String password;
}
