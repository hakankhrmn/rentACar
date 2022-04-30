package com.turkcell.rentACar.api.controllers;

import com.turkcell.rentACar.business.abstracts.CityService;
import com.turkcell.rentACar.business.dtos.cityDtos.CityListDto;
import com.turkcell.rentACar.business.dtos.cityDtos.GetCityDto;
import com.turkcell.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {

    private CityService cityService;

    @Autowired
    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }
    @GetMapping("/getall")
    public DataResult<List<CityListDto>> getAll() {
        return cityService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateCityRequest createCityRequest) {
        return cityService.add(createCityRequest);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id) {
        return cityService.delete(id);
    }

    @GetMapping("/getbyid/{id}")
    public DataResult<GetCityDto> getById(@PathVariable int id) {
        return cityService.getById(id);
    }
}
