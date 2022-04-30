package com.turkcell.rentACar.api.controllers;

import com.turkcell.rentACar.business.abstracts.CarService;
import com.turkcell.rentACar.business.dtos.carDtos.CarListDto;
import com.turkcell.rentACar.business.dtos.carDtos.GetCarDto;
import com.turkcell.rentACar.business.requests.carRequests.CreateCarRequest;
import com.turkcell.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
	
	private CarService carService;
	
	
	public CarsController(CarService carService) {
		super();
		this.carService = carService;
	}

	@GetMapping("/getAll")
	public DataResult<List<CarListDto>> getAll() {
		
		return carService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {
		
		return carService.add(createCarRequest);

	}

	@GetMapping("/get/{id}")
	public DataResult<GetCarDto> getById(@PathVariable int id) {
		return carService.getById(id);
	}

	@DeleteMapping("/delete/{id}")
	public Result delete(@PathVariable int id) {
		
		return carService.delete(id);

	}

	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {
		return this.carService.update(updateCarRequest);

	}
	
	@GetMapping("/get/{dailyPrice}")
	public DataResult<List<CarListDto>> getByDailyPrice(@PathVariable double dailyPrice) {
		return this.carService.getByDailyPrice(dailyPrice);
	}
	
	@GetMapping("/getallpages")
	public DataResult<List<CarListDto>> getAllPaged(int pageNo, int pageSize) {
		return carService.getAllPaged(pageNo, pageSize);
	}

	@GetMapping("/getallsorted")
	public DataResult<List<CarListDto>> getAllSorted(String direction) {
		return carService.getAllSorted(direction);
	}

}
