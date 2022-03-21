package com.turkcell.rentACar.api.controllers;

import com.turkcell.rentACar.business.abstracts.CarDamageService;
import com.turkcell.rentACar.business.dtos.carDamageDtos.CarDamageListDto;
import com.turkcell.rentACar.business.requests.carDamageRequests.CreateCarDamageRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car-damages")
public class CarDamageController {

    private CarDamageService carDamageService;

    @Autowired
    public CarDamageController(CarDamageService carDamageService) {
        this.carDamageService = carDamageService;
    }

    @GetMapping("/getall")
    public DataResult<List<CarDamageListDto>> getAll() {

        return carDamageService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateCarDamageRequest createCarDamageRequest) {

        return carDamageService.add(createCarDamageRequest);
    }

    @GetMapping("/get/{id}")
    public DataResult<List<CarDamageListDto>> getByCarId(@RequestParam int id) {

        return carDamageService.getByCarId(id);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@RequestParam int id) {

        return carDamageService.delete(id);
    }
}
