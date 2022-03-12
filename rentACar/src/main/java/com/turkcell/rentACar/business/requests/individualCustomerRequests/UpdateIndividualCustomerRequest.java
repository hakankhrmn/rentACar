package com.turkcell.rentACar.business.requests.individualCustomerRequests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest {
	
	private int individualCustomerId;
	
	private String firstName;
	
	private String lastName;
	
	private String nationalIdentity;
}
