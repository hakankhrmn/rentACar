package com.turkcell.rentACar.business.concretes;

import com.turkcell.rentACar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentACar.business.abstracts.CarRentService;
import com.turkcell.rentACar.business.dtos.carMaintenanceDtos.CarMaintenanceListDto;
import com.turkcell.rentACar.business.dtos.carMaintenanceDtos.GetCarMaintenanceDto;
import com.turkcell.rentACar.business.dtos.carRentDtos.CarRentListDto;
import com.turkcell.rentACar.business.requests.carMaintenanceRequests.CreateCarMaintenanceRequest;
import com.turkcell.rentACar.business.requests.carMaintenanceRequests.UpdateCarMaintenanceRequest;
import com.turkcell.rentACar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
import com.turkcell.rentACar.dataAccess.abstracts.CarMaintenanceDao;
import com.turkcell.rentACar.entities.concretes.CarMaintenance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.turkcell.rentACar.business.constants.messages.BusinessMessages.*;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {

	private CarMaintenanceDao carMaintenanceDao;
	private ModelMapperService modelMapperService;
	private CarRentService carRentService;
	
	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService, @Lazy CarRentService carRentService) {
		this.carMaintenanceDao = carMaintenanceDao;
		this.modelMapperService = modelMapperService;
		this.carRentService = carRentService;
	}

	@Override
	public DataResult<List<CarMaintenanceListDto>> getAll() {
		List<CarMaintenance> result = this.carMaintenanceDao.findAll();
		List<CarMaintenanceListDto> response = result.stream()
				.map(carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarMaintenanceListDto>>(response, SUCCESS_GET_ALL_CAR_MAINNTENANCE);
	}

	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {
		checkIfManitenanceDatesCorrect(createCarMaintenanceRequest);
		checkIfManitenanceDatesSuitable(createCarMaintenanceRequest);
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(createCarMaintenanceRequest, CarMaintenance.class);
		carMaintenanceDao.save(carMaintenance);
		return new SuccessResult(SUCCESS_ADD_CAR_MAINNTENANCE);
	}

	@Override
	public DataResult<GetCarMaintenanceDto> getById(int id) {
		CarMaintenance carMaintenance = this.carMaintenanceDao.findById(id);
		GetCarMaintenanceDto response = this.modelMapperService.forDto().map(carMaintenance, GetCarMaintenanceDto.class);
		return new SuccessDataResult<GetCarMaintenanceDto>(response, SUCCESS_GET_BY_ID_CAR_MAINNTENANCE);
	}

	@Override
	public Result delete(int id) {
		this.carMaintenanceDao.deleteById(id);
		return new SuccessResult(SUCCESS_DELETE_CAR_MAINNTENANCE);
	}

	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(updateCarMaintenanceRequest, CarMaintenance.class);
		carMaintenanceDao.save(carMaintenance);
		return new SuccessResult(SUCCESS_UPDATE_CAR_MAINNTENANCE);
	}

	@Override
	public DataResult<List<CarMaintenanceListDto>> getByCarId(int carId) {
		List<CarMaintenance> result = this.carMaintenanceDao.findByCar_CarId(carId);
		List<CarMaintenanceListDto> response = result.stream()
				.map(carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarMaintenanceListDto>>(response, SUCCESS_GET_BY_CAR_ID_CAR_MAINNTENANCE);
	}
	
	private void checkIfManitenanceDatesCorrect(CreateCarMaintenanceRequest createCarManitenanceRequest) {
		if (createCarManitenanceRequest.getReturnDate().isBefore(createCarManitenanceRequest.getMaintenanceDate())) {
			throw new BusinessException(ERROR_RETURN_DATE_BEFORE_MAINTENANCE_DATE_CAR_MAINNTENANCE);
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
				throw new BusinessException(ERROR_MAINTENANCE_DATE_IN_RENT_DATES_CAR_MAINNTENANCE);
			}
			
			if(returnDate.isBefore(rentReturnDate) && returnDate.isAfter(rentDate)) {
				throw new BusinessException(ERROR_RETURN_DATE_IN_RENT_DATE_CAR_MAINNTENANCE);
			}
			
			if(maintenanceDate.isBefore(rentDate) && returnDate.isAfter(rentReturnDate)) {
				throw new BusinessException(ERROR_RENT_DATE_IN_MAINTENANCE_DATE_CAR_MAINNTENANCE);
			}
		}
	}
}
