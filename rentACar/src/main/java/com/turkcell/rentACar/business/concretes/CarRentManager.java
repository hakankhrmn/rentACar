package com.turkcell.rentACar.business.concretes;

import com.turkcell.rentACar.api.controllers.models.CreateRentalRequest;
import com.turkcell.rentACar.business.abstracts.*;
import com.turkcell.rentACar.business.dtos.carMaintenanceDtos.CarMaintenanceListDto;
import com.turkcell.rentACar.business.dtos.carRentDtos.CarRentListDto;
import com.turkcell.rentACar.business.dtos.carRentDtos.GetCarRentDto;
import com.turkcell.rentACar.business.requests.carRentRequests.CreateCarRentRequest;
import com.turkcell.rentACar.business.requests.orderedAdditionalServiceRequests.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentACar.business.requests.orderedAdditionalServiceRequests.OrderedAdditionalServiceRequest;
import com.turkcell.rentACar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
import com.turkcell.rentACar.dataAccess.abstracts.CarRentDao;
import com.turkcell.rentACar.entities.concretes.CarRent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarRentManager implements CarRentService {
	
	private OrderedAdditionalServiceService orderedAdditionalServiceService;
	private CarMaintenanceService carMaintenanceService;
	private CarRentDao carRentDao;
	private ModelMapperService modelMapperService;
	private AdditionalServiceService additionalServiceService;
	private CarService carService;
	private CityService cityService;
	private IndividualCustomerService individualCustomerService;
	private CorporateCustomerService corporateCustomerService;

	@Autowired
	public CarRentManager(@Lazy CarMaintenanceService carMaintenanceService,
						  CarRentDao carRentDao,
						  ModelMapperService modelMapperService,
						  OrderedAdditionalServiceService orderedAdditionalServiceService,
						  AdditionalServiceService additionalServiceService,
						  CarService carService,
						  CityService cityService) {
		super();
		this.carMaintenanceService = carMaintenanceService;
		this.carRentDao = carRentDao;
		this.modelMapperService = modelMapperService;
		this.orderedAdditionalServiceService = orderedAdditionalServiceService;
		this.additionalServiceService = additionalServiceService;
		this.carService = carService;
		this.cityService = cityService;
	}

	@Override
	public DataResult<List<CarRentListDto>> getAll() {
		List<CarRent> result = this.carRentDao.findAll();
		List<CarRentListDto> response = result.stream()
				.map(carRent -> this.modelMapperService.forDto().map(carRent, CarRentListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarRentListDto>>(response, "Car rents listed successfully.");
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {

		CreateCarRentRequest createCarRentRequest = createRentalRequest.getCreateCarRentRequest();
		checkIfRentalDatesCorrect(createCarRentRequest);
		checkIfRentalDatesSuitable(createCarRentRequest);

		List<OrderedAdditionalServiceRequest> orderedAdditionalServiceRequests = createCarRentRequest.getOrderedAdditionalServiceRequests();

		CarRent carRent = this.modelMapperService.forRequest().map(createCarRentRequest, CarRent.class);
		carRent.setCarRentId(0);

		CarRent savedCarRent = carRentDao.save(carRent);
		
		List<CreateOrderedAdditionalServiceRequest> createOrderedAdditionalServiceRequests = orderedAdditionalServiceRequests.stream()
				.map(orderedAdditionalServiceRequest -> this.modelMapperService.forDto().map(orderedAdditionalServiceRequest, CreateOrderedAdditionalServiceRequest.class)).collect(Collectors.toList());

		createOrderedAdditionalServiceRequests.forEach(createOrderedAdditionalServiceRequest -> createOrderedAdditionalServiceRequest.setCarRentId(savedCarRent.getCarRentId()));
		orderedAdditionalServiceService.addAll(createOrderedAdditionalServiceRequests);

		//calculate total payment
		double totalPayment = chekCustomerForPayment(createRentalRequest.getCustomerId());

		//total payment ayrı methoda

		//createInvoiceRequest

		//InvoiceService.add(createInvoiceRequest)
		
		return new SuccessResult("Car rent added successfully.");
	}

	@Override
	public DataResult<GetCarRentDto> getById(int id) {
		CarRent carRent = this.carRentDao.getById(id);
		GetCarRentDto getCarRentDto = this.modelMapperService.forDto().map(carRent, GetCarRentDto.class);
		return new SuccessDataResult<GetCarRentDto>(getCarRentDto, "Getting car rent by car rent id.");
	}

	@Override
	public Result delete(int id) {
		this.carRentDao.deleteById(id);
		return new SuccessResult("Car rent deleted successfully.");
	}

	@Override
	public DataResult<List<CarRentListDto>> getByCarId(int carId) {
		List<CarRent> result = this.carRentDao.getAllByCar_CarId(carId);
		List<CarRentListDto> response = result.stream()
				.map(carRent-> this.modelMapperService.forDto().map(carRent, CarRentListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarRentListDto>>(response, "Car rents listed successfully.");
	}

	private void checkIfRentalDatesCorrect(CreateCarRentRequest createCarRentRequest) {
		if (createCarRentRequest.getReturnDate().isBefore(createCarRentRequest.getRentDate())) {
			throw new BusinessException("Return date can not be before rent date!");
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

	private double chekCustomerForPayment(int customerId) {
		if (individualCustomerService.existsByIndividualCustomerId(customerId)) {
			return 20;
		}

		if (corporateCustomerService.existsByCorporateCustomerId(customerId)) {
			return 10;
		}
		return 0;
	}
}
