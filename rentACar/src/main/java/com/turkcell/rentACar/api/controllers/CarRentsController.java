package com.turkcell.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentACar.business.abstracts.CarRentService;
import com.turkcell.rentACar.business.dtos.carRentDtos.CarRentListDto;
import com.turkcell.rentACar.business.dtos.carRentDtos.GetCarRentDto;
import com.turkcell.rentACar.business.requests.carRentRequests.CreateCarRentRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;


@RestController
@RequestMapping("/api/car-rents")
public class CarRentsController {

	private CarRentService carRentService;
	
	@Autowired
	public CarRentsController(CarRentService carRentService) {
		super();
		this.carRentService = carRentService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCarRentRequest createCarRentRequest) {
		return carRentService.add(createCarRentRequest);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<CarRentListDto>> getAll() {
		return carRentService.getAll();
	}
	
	@GetMapping("/getByCarId/{carId}")
	public DataResult<List<CarRentListDto>> getByCarId(@RequestParam int carId) {
		return this.carRentService.getByCarId(carId);
	}
	
	@GetMapping("/get/{id}")
	public DataResult<GetCarRentDto> getById(@RequestParam int id) {
		return carRentService.getById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public Result delete(@RequestParam int id) {
		return carRentService.delete(id);
	}
}
