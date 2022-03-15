package com.turkcell.rentACar.business.abstracts;

import com.turkcell.rentACar.business.dtos.corporateCustomerDtos.CorporateCustomerListDto;
import com.turkcell.rentACar.business.dtos.corporateCustomerDtos.GetCorporateCustomerDto;
import com.turkcell.rentACar.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;

import java.util.List;

public interface CorporateCustomerService {
	
	DataResult<List<CorporateCustomerListDto>> getAll();
	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
	DataResult<GetCorporateCustomerDto> getById(int id);
	Result delete(int id);

	boolean existsByCorporateCustomerId(int id);
}
