package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.business.dtos.CarListDto;
import com.turkcell.rentACar.business.dtos.GetCarDto;
import com.turkcell.rentACar.business.requests.CreateCarRequest;
import com.turkcell.rentACar.business.requests.UpdateCarRequest;

public interface CarService {

	List<CarListDto> getAll();
	void add(CreateCarRequest createCarRequest);
	GetCarDto getById(int id);
	void delete(int id);
	void update(UpdateCarRequest updateCarRequest);
	
}
