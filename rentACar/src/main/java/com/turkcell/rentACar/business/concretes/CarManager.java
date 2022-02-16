package com.turkcell.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentACar.business.abstracts.CarService;
import com.turkcell.rentACar.business.dtos.CarListDto;
import com.turkcell.rentACar.business.dtos.GetCarDto;
import com.turkcell.rentACar.business.requests.CreateCarRequest;
import com.turkcell.rentACar.business.requests.UpdateCarRequest;
import com.turkcell.rentACar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar.dataAccess.abstracts.CarDao;
import com.turkcell.rentACar.entities.concretes.Brand;
import com.turkcell.rentACar.entities.concretes.Car;

import lombok.AllArgsConstructor;

@Service
public class CarManager implements CarService {

	private CarDao carDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
		super();
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public List<CarListDto> getAll() {
		List<Car> result = this.carDao.findAll(); 
		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		return response;
	}

	@Override
	public void add(CreateCarRequest createCarRequest) {
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		carDao.save(car);

	}

	@Override
	public GetCarDto getById(int id) {
		Car car = this.carDao.findById(id);
		return this.modelMapperService.forDto().map(car, GetCarDto.class);
	}

	@Override
	public void delete(int id) {
		
		this.carDao.deleteById(id);

	}

	@Override
	public void update(UpdateCarRequest updateCarRequest) {
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		carDao.save(car);

	}

}
