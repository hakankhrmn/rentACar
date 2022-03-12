package com.turkcell.rentACar.business.requests.userRequests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
		
	private String email;
	
	private String password;
}