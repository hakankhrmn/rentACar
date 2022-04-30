package com.turkcell.rentACar.business.abstracts;

import com.turkcell.rentACar.business.dtos.carDtos.CarListDto;
import com.turkcell.rentACar.business.dtos.carDtos.GetCarDto;
import com.turkcell.rentACar.business.requests.carRequests.CreateCarRequest;
import com.turkcell.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;

import java.util.List;

public interface CarService {

	DataResult<List<CarListDto>> getAll();
	Result add(CreateCarRequest createCarRequest);
	DataResult<GetCarDto> getById(int id);
	Result delete(int id);
	Result update(UpdateCarRequest updateCarRequest);
	DataResult<List<CarListDto>> getByDailyPrice(double dailyPrice);
	DataResult<List<CarListDto>> getAllPaged(int pageNo,int pageSize);
	DataResult<List<CarListDto>> getAllSorted(String direction);
	void checkIfCarExists(int id);
	
}
