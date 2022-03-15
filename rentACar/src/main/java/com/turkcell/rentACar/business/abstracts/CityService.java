package com.turkcell.rentACar.business.abstracts;

import com.turkcell.rentACar.business.dtos.cityDtos.CityListDto;
import com.turkcell.rentACar.business.dtos.cityDtos.GetCityDto;
import com.turkcell.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;

import java.util.List;

public interface CityService {
    DataResult<List<CityListDto>> getAll();
    Result add(CreateCityRequest createCityRequest);
    DataResult<GetCityDto> getById(int id);
    Result delete(int id);
}
