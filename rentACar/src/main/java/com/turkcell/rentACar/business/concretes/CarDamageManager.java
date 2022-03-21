package com.turkcell.rentACar.business.concretes;

import com.turkcell.rentACar.business.abstracts.CarDamageService;
import com.turkcell.rentACar.business.dtos.carDamageDtos.CarDamageListDto;
import com.turkcell.rentACar.business.requests.carDamageRequests.CreateCarDamageRequest;
import com.turkcell.rentACar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
import com.turkcell.rentACar.dataAccess.abstracts.CarDamageDao;
import com.turkcell.rentACar.entities.concretes.CarDamage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarDamageManager implements CarDamageService {

    private CarDamageDao carDamageDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public CarDamageManager(CarDamageDao carDamageDao, ModelMapperService modelMapperService) {
        this.carDamageDao = carDamageDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<CarDamageListDto>> getAll() {
        List<CarDamage> carDamages = carDamageDao.findAll();
        List<CarDamageListDto> carDamageListDtos = carDamages.stream()
                .map(carDamage -> modelMapperService.forDto().map(carDamages, CarDamageListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(carDamageListDtos, "Car damages listed successfully.");
    }

    @Override
    public Result add(CreateCarDamageRequest createCarDamageRequest) {
        CarDamage carDamage = modelMapperService.forRequest().map(createCarDamageRequest, CarDamage.class);
        carDamageDao.save(carDamage);
        return new SuccessResult("Car damage added successfully");
    }

    @Override
    public DataResult<List<CarDamageListDto>> getByCarId(int id) {
        List<CarDamage> carDamages = carDamageDao.findByCar_CarId(id);
        List<CarDamageListDto> carDamageListDtos = carDamages.stream()
                .map(carDamage -> modelMapperService.forDto().map(carDamages, CarDamageListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(carDamageListDtos, "Car damages listed successfully for the car.");
    }

    @Override
    public Result delete(int id) {
        carDamageDao.deleteById(id);
        return new SuccessResult("Car damage deleted successfully.");
    }
}