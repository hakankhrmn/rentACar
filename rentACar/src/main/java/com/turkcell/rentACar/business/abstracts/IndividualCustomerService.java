package com.turkcell.rentACar.business.abstracts;

import com.turkcell.rentACar.business.dtos.individualCustomerDtos.GetIndividualCustomerDto;
import com.turkcell.rentACar.business.dtos.individualCustomerDtos.IndividualCustomerListDto;
import com.turkcell.rentACar.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.entities.concretes.IndividualCustomer;

import java.util.List;

public interface IndividualCustomerService {
	
	DataResult<List<IndividualCustomerListDto>> getAll();
	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
	DataResult<GetIndividualCustomerDto> getById(int id);
	Result delete(int id);

	boolean existsByIndividualCustomerId(int id);
	IndividualCustomer getIndividualCustomerById(int id);
	void checkIfIndividualCustomerExists(int id);
}
