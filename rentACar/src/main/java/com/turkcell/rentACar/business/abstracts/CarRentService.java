package com.turkcell.rentACar.business.abstracts;

import com.turkcell.rentACar.business.dtos.carRentDtos.CarRentListDto;
import com.turkcell.rentACar.business.dtos.carRentDtos.GetCarRentDto;
import com.turkcell.rentACar.business.requests.carRentRequests.CreateCarRentRequest;
import com.turkcell.rentACar.business.requests.carRentRequests.UpdateReturnCarRentRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.entities.concretes.CarRent;

import java.util.List;

public interface CarRentService {
	DataResult<List<CarRentListDto>> getAll();
	DataResult<GetCarRentDto> add(CreateCarRentRequest createCarRentRequest);
	DataResult<GetCarRentDto> returnCarRent(UpdateReturnCarRentRequest updateReturnCarRentRequest);
	DataResult<GetCarRentDto> getById(int id);
	Result delete(int id);
	DataResult<List<CarRentListDto>> getByCarId(int carId);
	CarRent getByCarRentId(int id);
	void checkIfCarRentExists(int id);
}
