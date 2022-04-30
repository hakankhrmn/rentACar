package com.turkcell.rentACar.api.controllers;

import com.turkcell.rentACar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentACar.business.dtos.carMaintenanceDtos.CarMaintenanceListDto;
import com.turkcell.rentACar.business.dtos.carMaintenanceDtos.GetCarMaintenanceDto;
import com.turkcell.rentACar.business.requests.carMaintenanceRequests.CreateCarMaintenanceRequest;
import com.turkcell.rentACar.business.requests.carMaintenanceRequests.UpdateCarMaintenanceRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/car-maintenances")
public class CarMaintenancesController {
	
	CarMaintenanceService carMaintenanceService;
	
	@Autowired
	public CarMaintenancesController(CarMaintenanceService carMaintenanceService) {
		this.carMaintenanceService = carMaintenanceService;
	}

	@GetMapping("/getAll")
	public DataResult<List<CarMaintenanceListDto>> getAll() {
		return carMaintenanceService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCarMaintenanceRequest createCarMaintenanceRequest) {
		return carMaintenanceService.add(createCarMaintenanceRequest);
	}

	@GetMapping("/get/{id}")
	public DataResult<GetCarMaintenanceDto> getById(@PathVariable int id) {
		return carMaintenanceService.getById(id);
	}

	@DeleteMapping("/delete/{id}")
	public Result delete(@PathVariable int id) {
		return carMaintenanceService.delete(id);
	}

	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		return this.carMaintenanceService.update(updateCarMaintenanceRequest);
	}

	@GetMapping("/getByCarId/{carId}")
	public DataResult<List<CarMaintenanceListDto>> getByCarId(@PathVariable int carId) {
		return this.carMaintenanceService.getByCarId(carId);
	}
}
