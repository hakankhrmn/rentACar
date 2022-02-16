package com.turkcell.rentACar.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentACar.business.abstracts.CarService;
import com.turkcell.rentACar.business.dtos.CarListDto;
import com.turkcell.rentACar.business.dtos.GetCarDto;
import com.turkcell.rentACar.business.requests.CreateCarRequest;
import com.turkcell.rentACar.business.requests.UpdateCarRequest;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
	
	private CarService carService;
	
	
	public CarsController(CarService carService) {
		super();
		this.carService = carService;
	}

	@GetMapping("/getAll")
	public List<CarListDto> getAll() {
		
		return carService.getAll();
	}

	@PostMapping("/add")
	public void add(@RequestBody CreateCarRequest createCarRequest) {
		
		carService.add(createCarRequest);

	}

	@GetMapping("/get/{id}")
	public GetCarDto getById(@RequestParam int id) {
		return carService.getById(id);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@RequestParam int id) {
		
		carService.delete(id);

	}

	@PutMapping("/update")
	public void update(@RequestBody UpdateCarRequest updateCarRequest) {
		this.carService.update(updateCarRequest);

	}

}
