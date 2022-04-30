package com.turkcell.rentACar.business.concretes;

import com.turkcell.rentACar.business.abstracts.*;
import com.turkcell.rentACar.business.dtos.carMaintenanceDtos.CarMaintenanceListDto;
import com.turkcell.rentACar.business.dtos.carRentDtos.CarRentListDto;
import com.turkcell.rentACar.business.dtos.carRentDtos.GetCarRentDto;
import com.turkcell.rentACar.business.requests.carRentRequests.CreateCarRentRequest;
import com.turkcell.rentACar.business.requests.carRentRequests.UpdateReturnCarRentRequest;
import com.turkcell.rentACar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
import com.turkcell.rentACar.dataAccess.abstracts.CarRentDao;
import com.turkcell.rentACar.entities.concretes.CarRent;
import com.turkcell.rentACar.entities.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.turkcell.rentACar.business.constants.messages.BusinessMessages.*;

@Service
public class CarRentManager implements CarRentService {

	private CarMaintenanceService carMaintenanceService;
	private CarRentDao carRentDao;
	private ModelMapperService modelMapperService;
	private CarService carService;
	private CityService cityService;

	@Autowired
	public CarRentManager(@Lazy CarMaintenanceService carMaintenanceService,
						  CarRentDao carRentDao,
						  ModelMapperService modelMapperService,
						  CarService carService,
						  CityService cityService) {
		super();
		this.carMaintenanceService = carMaintenanceService;
		this.carRentDao = carRentDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
		this.cityService = cityService;

	}

	@Override
	public DataResult<List<CarRentListDto>> getAll() {
		List<CarRent> result = this.carRentDao.findAll();
		List<CarRentListDto> response = result.stream()
				.map(carRent -> this.modelMapperService.forDto().map(carRent, CarRentListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarRentListDto>>(response, SUCCESS_GET_ALL_CAR_RENT);
	}

	@Override
	public DataResult<GetCarRentDto> add(CreateCarRentRequest createCarRentRequest) {

		checkIfRentalDatesCorrect(createCarRentRequest);
		checkIfRentalDatesSuitable(createCarRentRequest);

		CarRent carRent = this.modelMapperService.forRequest().map(createCarRentRequest, CarRent.class);
		carRent.setCarRentId(0);
		carRent.setRentStartKilometer(carService.getById(carRent.getCar().getCarId()).getData().getKilometerInformation());

		CarRent savedCarRent = carRentDao.save(carRent);
		GetCarRentDto carRentDto = modelMapperService.forDto().map(savedCarRent, GetCarRentDto.class);

		return new SuccessDataResult<>(carRentDto, SUCCESS_ADD_CAR_RENT);
	}

	@Override
	public DataResult<GetCarRentDto> returnCarRent(UpdateReturnCarRentRequest updateReturnCarRentRequest) {

		checkIfCarRentExists(updateReturnCarRentRequest.getCarRentId());
		cityService.checkIfCityExists(updateReturnCarRentRequest.getReturnCityId());

		CarRent carRent = carRentDao.getById(updateReturnCarRentRequest.getCarRentId());
		City city = cityService.getCityById(updateReturnCarRentRequest.getReturnCityId());

		carRent.setReturnCity(city);
		carRent.setReturnKilometer(updateReturnCarRentRequest.getReturnKilometer());
		carRent.setReturnDate(updateReturnCarRentRequest.getReturnDate());
		GetCarRentDto getCarRentDto = modelMapperService.forDto().map(carRentDao.save(carRent), GetCarRentDto.class);
		return new SuccessDataResult<>(getCarRentDto, SUCCESS_UPDATE_RETURN_CAR_RENT);
	}

	@Override
	public DataResult<GetCarRentDto> getById(int id) {
		CarRent carRent = this.carRentDao.getById(id);
		GetCarRentDto getCarRentDto = this.modelMapperService.forDto().map(carRent, GetCarRentDto.class);
		return new SuccessDataResult<GetCarRentDto>(getCarRentDto, SUCCESS_GET_BY_ID_CAR_RENT);
	}

	@Override
	public Result delete(int id) {

		checkIfCarRentExists(id);

		this.carRentDao.deleteById(id);
		return new SuccessResult(SUCCESS_DELETE_CAR_RENT);
	}

	@Override
	public DataResult<List<CarRentListDto>> getByCarId(int carId) {
		List<CarRent> result = this.carRentDao.getAllByCar_CarId(carId);
		List<CarRentListDto> response = result.stream()
				.map(carRent-> this.modelMapperService.forDto().map(carRent, CarRentListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarRentListDto>>(response, SUCCESS_GET_BY_CAR_ID_CAR_RENT);
	}

	@Override
	public CarRent getByCarRentId(int id) {
		return this.carRentDao.findById(id);
	}

	private void checkIfRentalDatesCorrect(CreateCarRentRequest createCarRentRequest) {
		if (createCarRentRequest.getReturnDate().isBefore(createCarRentRequest.getRentDate())) {
			throw new BusinessException(ERROR_RETURN_DATE_BEFORE_RENT_DATE_CAR_RENT);
		}
	}

	@Override
	public void checkIfCarRentExists(int id) {
		if (!this.carRentDao.existsById(id)) {
			throw new BusinessException(ERROR_CAR_RENT_DOES_NOT_EXISTS);
		}
	}
	
	private void checkIfRentalDatesSuitable(CreateCarRentRequest createCarRentRequest) {
		DataResult<List<CarMaintenanceListDto>> dataResult = carMaintenanceService.getByCarId(createCarRentRequest.getCarId());
		List<CarMaintenanceListDto> carMaintenanceListDtos = dataResult.getData();
		
		if (carMaintenanceListDtos == null) {
			return;
		}
		
		LocalDate rentDate = createCarRentRequest.getRentDate();
		LocalDate returnDate = createCarRentRequest.getReturnDate();
		
		for (CarMaintenanceListDto carMaintenanceListDto: carMaintenanceListDtos) {
			LocalDate maintenanceDate = carMaintenanceListDto.getMaintenanceDate();
			LocalDate maintenanceReturnDate = carMaintenanceListDto.getReturnDate();
			
			if(rentDate.isBefore(maintenanceReturnDate) && rentDate.isAfter(maintenanceDate)) {
				throw new BusinessException(ERROR_RENT_DATE_IN_MAINTENANCE_DATES_CAR_RENT);
			}
			
			if(returnDate.isBefore(maintenanceReturnDate) && returnDate.isAfter(maintenanceDate)) {
				throw new BusinessException(ERROR_RETURN_DATE_IN_MAINTENANCE_DATE_CAR_RENT);
			}
			
			if(rentDate.isBefore(maintenanceDate) && returnDate.isAfter(maintenanceReturnDate)) {
				throw new BusinessException(ERROR_MAINTENANCE_DATE_IN_RENT_DATE_CAR_RENT);
			}
		}
		
	}

}
