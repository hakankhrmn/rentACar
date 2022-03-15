package com.turkcell.rentACar.business.requests.individualCustomerRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {

	private String firstName;
	
	private String lastName;
	
	private String nationalIdentity;

	private String email;

	private String password;
}
