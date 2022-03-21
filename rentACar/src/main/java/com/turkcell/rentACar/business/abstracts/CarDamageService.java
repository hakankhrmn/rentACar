package com.turkcell.rentACar.business.abstracts;

import com.turkcell.rentACar.business.dtos.carDamageDtos.CarDamageListDto;
import com.turkcell.rentACar.business.requests.carDamageRequests.CreateCarDamageRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;

import java.util.List;

public interface CarDamageService {
    DataResult<List<CarDamageListDto>> getAll();
    Result add(CreateCarDamageRequest createCarDamageRequest);
    DataResult<List<CarDamageListDto>> getByCarId(int id);
    Result delete(int id);
}
