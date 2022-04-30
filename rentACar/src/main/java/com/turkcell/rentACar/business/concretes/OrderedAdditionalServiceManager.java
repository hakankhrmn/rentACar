package com.turkcell.rentACar.business.concretes;

import com.turkcell.rentACar.business.abstracts.AdditionalServiceService;
import com.turkcell.rentACar.business.abstracts.CarRentService;
import com.turkcell.rentACar.business.abstracts.OrderedAdditionalServiceService;
import com.turkcell.rentACar.business.dtos.orderedAdditionalServiceDtos.GetOrderedAdditionalServiceDto;
import com.turkcell.rentACar.business.requests.orderedAdditionalServiceRequests.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentACar.business.requests.orderedAdditionalServiceRequests.UpdateOrderedAdditionalServiceRequest;
import com.turkcell.rentACar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar.dataAccess.abstracts.OrderedAdditionalServiceDao;
import com.turkcell.rentACar.entities.concretes.OrderedAdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.turkcell.rentACar.business.constants.messages.BusinessMessages.ERROR_ORDERED_ADDITIONAL_SERVICE_DOES_NOT_EXISTS;

@Service
public class OrderedAdditionalServiceManager implements OrderedAdditionalServiceService {

	private OrderedAdditionalServiceDao orderedAdditionalServiceDao;
	private ModelMapperService modelMapperService;
	private CarRentService carRentService;
	private AdditionalServiceService additionalServiceService;
	
	@Autowired
	public OrderedAdditionalServiceManager(
			OrderedAdditionalServiceDao orderedAdditionalServiceDao,
			ModelMapperService modelMapperService,
			CarRentService carRentService,
			AdditionalServiceService additionalServiceService) {
		super();
		this.orderedAdditionalServiceDao = orderedAdditionalServiceDao;
		this.modelMapperService = modelMapperService;
		this.carRentService = carRentService;
		this.additionalServiceService = additionalServiceService;
	}

	@Override
	public void addAll(List<CreateOrderedAdditionalServiceRequest> createOrderedAdditionalServiceRequests) {

		for (CreateOrderedAdditionalServiceRequest createOrderedAdditionalServiceRequest : createOrderedAdditionalServiceRequests) {
			additionalServiceService.checkIfAdditionalServiceExists(createOrderedAdditionalServiceRequest.getAdditionalServiceId());
		}

		List<OrderedAdditionalService> orderedAdditionalServices = createOrderedAdditionalServiceRequests.stream()
				.map(createOrderedAdditionalServiceRequest -> this.modelMapperService.forRequest().map(createOrderedAdditionalServiceRequest, OrderedAdditionalService.class)).collect(Collectors.toList());
		
		orderedAdditionalServiceDao.saveAll(orderedAdditionalServices);
		
	}

	@Override
	public void updateAll(List<UpdateOrderedAdditionalServiceRequest> updateOrderedAdditionalServiceRequests) {

		for (UpdateOrderedAdditionalServiceRequest updateOrderedAdditionalServiceRequest : updateOrderedAdditionalServiceRequests) {
			additionalServiceService.checkIfAdditionalServiceExists(updateOrderedAdditionalServiceRequest.getAdditionalServiceId());
		}

		List<OrderedAdditionalService> orderedAdditionalServices = updateOrderedAdditionalServiceRequests.stream()
				.map(updateOrderedAdditionalServiceRequest -> this.modelMapperService.forRequest().map(updateOrderedAdditionalServiceRequest, OrderedAdditionalService.class)).collect(Collectors.toList());
		
		orderedAdditionalServiceDao.saveAll(orderedAdditionalServices);		
	}

	@Override
	public void deleteAllByCarRentId(int carRentId) {

		carRentService.checkIfCarRentExists(carRentId);

		orderedAdditionalServiceDao.deleteAllByCarRent_CarRentId(carRentId);

	}

	@Override
	public List<GetOrderedAdditionalServiceDto> getAllByCarRent_CarRentId(int carRentId) {

		carRentService.checkIfCarRentExists(carRentId);

		List<OrderedAdditionalService> orderedAdditionalServices = orderedAdditionalServiceDao.getAllByCarRent_CarRentId(carRentId);
		return orderedAdditionalServices.stream()
				.map(orderedAdditionalService -> this.modelMapperService.forDto().map(orderedAdditionalServices, GetOrderedAdditionalServiceDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<OrderedAdditionalService> getByCarRent_CarRentId(int carRentId) {

		carRentService.checkIfCarRentExists(carRentId);

		return orderedAdditionalServiceDao.getAllByCarRent_CarRentId(carRentId);
	}

	@Override
	public void checkIfOrderedAdditionalServiceExists(int id) {
		if (!this.orderedAdditionalServiceDao.existsById(id)) {
			throw new BusinessException(ERROR_ORDERED_ADDITIONAL_SERVICE_DOES_NOT_EXISTS);
		}
	}

}
