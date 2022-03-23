package com.turkcell.rentACar.api.controllers;

import com.turkcell.rentACar.api.models.CreateRentalRequest;
import com.turkcell.rentACar.business.abstracts.CarRentService;
import com.turkcell.rentACar.business.dtos.carRentDtos.CarRentListDto;
import com.turkcell.rentACar.business.dtos.carRentDtos.GetCarRentDto;
import com.turkcell.rentACar.business.requests.carRentRequests.UpdateReturnCarRentRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateRentalRequest createRentalRequest) {
		return carRentService.add(createRentalRequest);
	}

	@PutMapping("/return-car-rent")
	public Result returnCarRent(@RequestBody UpdateReturnCarRentRequest updateReturnCarRentRequest) {
		return carRentService.returnCarRent(updateReturnCarRentRequest);
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
