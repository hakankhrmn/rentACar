package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.business.dtos.brandDtos.BrandListDto;
import com.turkcell.rentACar.business.dtos.brandDtos.GetBrandDto;
import com.turkcell.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.turkcell.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;

public interface BrandService {

	DataResult<List<BrandListDto>> getAll();
	Result add(CreateBrandRequest createBrandRequest) throws Exception;
	DataResult<GetBrandDto> getById(int id);
	Result delete(int id);
	Result update(UpdateBrandRequest updateBrandRequest) throws Exception;
}
