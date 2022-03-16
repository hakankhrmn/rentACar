package com.turkcell.rentACar.business.abstracts;

import com.turkcell.rentACar.api.models.CreateRentalRequest;
import com.turkcell.rentACar.business.dtos.carRentDtos.CarRentListDto;
import com.turkcell.rentACar.business.dtos.carRentDtos.GetCarRentDto;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.entities.concretes.CarRent;

import java.util.List;

public interface CarRentService {
	DataResult<List<CarRentListDto>> getAll();
	Result add(CreateRentalRequest createRentalRequest);
	DataResult<GetCarRentDto> getById(int id);
	Result delete(int id);
	DataResult<List<CarRentListDto>> getByCarId(int carId);
	CarRent getByCarRentId(int id);
}
