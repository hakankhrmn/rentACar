package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.business.dtos.carRentDtos.CarRentListDto;
import com.turkcell.rentACar.business.dtos.carRentDtos.GetCarRentDto;
import com.turkcell.rentACar.business.requests.carRentRequests.CreateCarRentRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;

public interface CarRentService {
	DataResult<List<CarRentListDto>> getAll();
	Result add(CreateCarRentRequest createCarRentRequest);
	DataResult<GetCarRentDto> getById(int id);
	Result delete(int id);
	DataResult<List<CarRentListDto>> getByCarId(int carId);
}
