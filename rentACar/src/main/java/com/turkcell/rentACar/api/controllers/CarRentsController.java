package com.turkcell.rentACar.api.controllers;

import com.turkcell.rentACar.business.abstracts.CarRentService;
import com.turkcell.rentACar.business.dtos.carRentDtos.CarRentListDto;
import com.turkcell.rentACar.business.dtos.carRentDtos.GetCarRentDto;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/car-rents")
public class CarRentsController {

	private CarRentService carRentService;
	
	@Autowired
	public CarRentsController(CarRentService carRentService) {
		super();
		this.carRentService = carRentService;
	}
/*
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCarRentRequest createCarRentRequest) {
		return carRentService.add(createCarRentRequest);
	}

	@PutMapping("/return-car-rent")
	public Result returnCarRent(@RequestBody UpdateReturnCarRentRequest updateReturnCarRentRequest) {
		return carRentService.returnCarRent(updateReturnCarRentRequest);
	}
*/
	@GetMapping("/getAll")
	public DataResult<List<CarRentListDto>> getAll() {
		return carRentService.getAll();
	}
	
	@GetMapping("/getByCarId/{carId}")
	public DataResult<List<CarRentListDto>> getByCarId(@PathVariable int carId) {
		return this.carRentService.getByCarId(carId);
	}
	
	@GetMapping("/get/{id}")
	public DataResult<GetCarRentDto> getById(@PathVariable int id) {
		return carRentService.getById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public Result delete(@PathVariable int id) {
		return carRentService.delete(id);
	}
}
