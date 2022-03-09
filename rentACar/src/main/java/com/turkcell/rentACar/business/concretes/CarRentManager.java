package com.turkcell.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.turkcell.rentACar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentACar.business.abstracts.CarRentService;
import com.turkcell.rentACar.business.dtos.carMaintenanceDtos.CarMaintenanceListDto;
import com.turkcell.rentACar.business.dtos.carRentDtos.CarRentListDto;
import com.turkcell.rentACar.business.dtos.carRentDtos.GetCarRentDto;
import com.turkcell.rentACar.business.requests.carRentRequests.CreateCarRentRequest;
import com.turkcell.rentACar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
import com.turkcell.rentACar.dataAccess.abstracts.CarRentDao;
import com.turkcell.rentACar.entities.concretes.CarRent;
import com.turkcell.rentACar.exceptions.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarRentManager implements CarRentService {
	
	private final CarMaintenanceService carMaintenanceService;
	private final CarRentDao carRentDao;
	private ModelMapperService modelMapperService;

	@Override
	public DataResult<List<CarRentListDto>> getAll() {
		List<CarRent> result = this.carRentDao.findAll();
		List<CarRentListDto> response = result.stream()
				.map(carRent -> this.modelMapperService.forDto().map(carRent, CarRentListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarRentListDto>>(response, "Car rents listed successfully.");
	}

	@Override
	public Result add(CreateCarRentRequest createCarRentRequest) {
		checkIfRentalDatesCorrect(createCarRentRequest);
		checkIfRentalDatesSuitable(createCarRentRequest);

		CarRent carMaintenance = this.modelMapperService.forRequest().map(createCarRentRequest, CarRent.class);
		carRentDao.save(carMaintenance);
		return new SuccessResult("Car rent added successfully.");
	}

	@Override
	public DataResult<GetCarRentDto> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<List<CarRentListDto>> getByCarId(int carId) {
		List<CarRent> result = this.carRentDao.getAllByCar_CarId(carId);
		List<CarRentListDto> response = result.stream()
				.map(carRent-> this.modelMapperService.forDto().map(carRent, CarRentListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarRentListDto>>(response, "Car rents listed successfully.");
	}

	private void checkIfRentalDatesCorrect(CreateCarRentRequest createCarRentRequest) throws BusinessException {
		if (createCarRentRequest.getReturnDate().isBefore(createCarRentRequest.getRentDate())) {
			throw new BusinessException("Return date can not be before rent date!");
		}

	}
	
	private void checkIfRentalDatesSuitable(CreateCarRentRequest createCarRentRequest) {
		DataResult<List<CarMaintenanceListDto>> dataResult = carMaintenanceService.getByCarId(createCarRentRequest.getCarId());
		List<CarMaintenanceListDto> carMaintenanceListDtos = dataResult.getData();
		
		LocalDate rentDate = createCarRentRequest.getRentDate();
		LocalDate returnDate = createCarRentRequest.getReturnDate();
		
		for (CarMaintenanceListDto carMaintenanceListDto: carMaintenanceListDtos) {
			LocalDate maintenanceDate = carMaintenanceListDto.getMaintenanceDate();
			LocalDate maintenanceReturnDate = carMaintenanceListDto.getReturnDate();
			
			if(rentDate.isBefore(maintenanceReturnDate) && rentDate.isAfter(maintenanceDate)) {
				throw new BusinessException("Rent date can not be in maintenance dates!");
			}
			
			if(returnDate.isBefore(maintenanceReturnDate) && returnDate.isAfter(maintenanceDate)) {
				throw new BusinessException("Return date of the rent can not be in maintenance dates!");
			}
			
			if(rentDate.isBefore(maintenanceDate) && returnDate.isAfter(maintenanceReturnDate)) {
				throw new BusinessException("Rent dates can not include maintenance dates!");
			}
		}
		
	}

}
