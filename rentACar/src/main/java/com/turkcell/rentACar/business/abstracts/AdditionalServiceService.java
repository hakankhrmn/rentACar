package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.business.dtos.additionalServiceDtos.AdditionalServiceListDto;
import com.turkcell.rentACar.business.dtos.additionalServiceDtos.GetAdditionalServiceDto;
import com.turkcell.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;

public interface AdditionalServiceService {
	
	DataResult<List<AdditionalServiceListDto>> getAll();
	Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest);
	DataResult<GetAdditionalServiceDto> getById(int id);
	Result delete(int id);
}
