package com.turkcell.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentACar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentACar.business.abstracts.CarRentService;
import com.turkcell.rentACar.business.dtos.carMaintenanceDtos.CarMaintenanceListDto;
import com.turkcell.rentACar.business.dtos.carMaintenanceDtos.GetCarMaintenanceDto;
import com.turkcell.rentACar.business.dtos.carRentDtos.CarRentListDto;
import com.turkcell.rentACar.business.requests.carMaintenanceRequests.CreateCarMaintenanceRequest;
import com.turkcell.rentACar.business.requests.carMaintenanceRequests.UpdateCarMaintenanceRequest;
import com.turkcell.rentACar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
import com.turkcell.rentACar.dataAccess.abstracts.CarMaintenanceDao;
import com.turkcell.rentACar.entities.concretes.CarMaintenance;
import com.turkcell.rentACar.exceptions.BusinessException;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {

	private CarMaintenanceDao carMaintenanceDao;
	private ModelMapperService modelMapperService;
	private CarRentService carRentService;
	
	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService, CarRentService carRentService) {
		this.carMaintenanceDao = carMaintenanceDao;
		this.modelMapperService = modelMapperService;
		this.carRentService = carRentService;
	}

	@Override
	public DataResult<List<CarMaintenanceListDto>> getAll() {
		List<CarMaintenance> result = this.carMaintenanceDao.findAll();
		List<CarMaintenanceListDto> response = result.stream()
				.map(carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarMaintenanceListDto>>(response, "Car maintenances listed successfully.");
	}

	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {
		checkIfManitenanceDatesCorrect(createCarMaintenanceRequest);
		checkIfManitenanceDatesSuitable(createCarMaintenanceRequest);
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(createCarMaintenanceRequest, CarMaintenance.class);
		carMaintenanceDao.save(carMaintenance);
		return new SuccessResult("Car maintenance added successfully.");
	}

	@Override
	public DataResult<GetCarMaintenanceDto> getById(int id) {
		CarMaintenance carMaintenance = this.carMaintenanceDao.findById(id);
		GetCarMaintenanceDto response = this.modelMapperService.forDto().map(carMaintenance, GetCarMaintenanceDto.class);
		return new SuccessDataResult<GetCarMaintenanceDto>(response, "Getting car maintenance by id");
	}

	@Override
	public Result delete(int id) {
		this.carMaintenanceDao.deleteById(id);
		return new SuccessResult("Car maintenance deleted successfully.");
	}

	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(updateCarMaintenanceRequest, CarMaintenance.class);
		carMaintenanceDao.save(carMaintenance);
		return new SuccessResult("Car maintenance updated successfully.");
	}

	@Override
	public DataResult<List<CarMaintenanceListDto>> getByCarId(int carId) {
		List<CarMaintenance> result = this.carMaintenanceDao.findByCar_CarId(carId);
		List<CarMaintenanceListDto> response = result.stream()
				.map(carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarMaintenanceListDto>>(response, "Car maintenances listed successfully.");
	}
	
	private void checkIfManitenanceDatesCorrect(CreateCarMaintenanceRequest createCarManitenanceRequest) throws BusinessException {
		if (createCarManitenanceRequest.getReturnDate().isBefore(createCarManitenanceRequest.getMaintenanceDate())) {
			throw new BusinessException("Return date can not be before maintenance date!");
		}

	}
	
	private void checkIfManitenanceDatesSuitable(CreateCarMaintenanceRequest createCarManitenanceRequest) {
		DataResult<List<CarRentListDto>> dataResult = carRentService.getByCarId(createCarManitenanceRequest.getCarId());
		List<CarRentListDto> carRentListDtos = dataResult.getData();
		
		LocalDate maintenanceDate = createCarManitenanceRequest.getMaintenanceDate();
		LocalDate returnDate = createCarManitenanceRequest.getReturnDate();
		
		for (CarRentListDto carRentListDto: carRentListDtos) {
			LocalDate rentDate = carRentListDto.getRentDate();
			LocalDate rentReturnDate = carRentListDto.getReturnDate();
			
			if(maintenanceDate.isBefore(rentReturnDate) && maintenanceDate.isAfter(rentDate)) {
				throw new BusinessException("Maintenance date can not be in rent dates!");
			}
			
			if(returnDate.isBefore(rentReturnDate) && returnDate.isAfter(rentDate)) {
				throw new BusinessException("Return date of the maintenance can not be in rent dates!");
			}
			
			if(maintenanceDate.isBefore(rentDate) && returnDate.isAfter(rentReturnDate)) {
				throw new BusinessException("Maintenance dates can not include rent dates!");
			}
		}
	}
}
