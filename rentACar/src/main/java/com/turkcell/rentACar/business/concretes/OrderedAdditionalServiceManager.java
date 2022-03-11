package com.turkcell.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentACar.business.abstracts.OrderedAdditionalServiceService;
import com.turkcell.rentACar.business.dtos.orderedAdditionalServiceDtos.GetOrderedAdditionalServiceDto;
import com.turkcell.rentACar.business.requests.orderedAdditionalServiceRequests.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentACar.business.requests.orderedAdditionalServiceRequests.UpdateOrderedAdditionalServiceRequest;
import com.turkcell.rentACar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar.dataAccess.abstracts.OrderedAdditionalServiceDao;
import com.turkcell.rentACar.entities.concretes.OrderedAdditionalService;

@Service
public class OrderedAdditionalServiceManager implements OrderedAdditionalServiceService {

	private OrderedAdditionalServiceDao orderedAdditionalServiceDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public OrderedAdditionalServiceManager(
			OrderedAdditionalServiceDao orderedAdditionalServiceDao,
			ModelMapperService modelMapperService) {
		super();
		this.orderedAdditionalServiceDao = orderedAdditionalServiceDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public void addAll(List<CreateOrderedAdditionalServiceRequest> createOrderedAdditionalServiceRequests) {
		List<OrderedAdditionalService> orderedAdditionalServices = createOrderedAdditionalServiceRequests.stream()
				.map(createOrderedadditionalServiceRequest -> this.modelMapperService.forRequest().map(createOrderedadditionalServiceRequest, OrderedAdditionalService.class)).collect(Collectors.toList());
		
		orderedAdditionalServiceDao.saveAll(orderedAdditionalServices);
		
	}

	@Override
	public void updateAll(List<UpdateOrderedAdditionalServiceRequest> updateOrderedAdditionalServiceRequests) {
		List<OrderedAdditionalService> orderedAdditionalServices = updateOrderedAdditionalServiceRequests.stream()
				.map(createOrderedadditionalServiceRequest -> this.modelMapperService.forRequest().map(createOrderedadditionalServiceRequest, OrderedAdditionalService.class)).collect(Collectors.toList());
		
		orderedAdditionalServiceDao.saveAll(orderedAdditionalServices);		
	}

	@Override
	public void deleteAllByCarRentId(int carRentId) {

		orderedAdditionalServiceDao.deleteAllByCarRent_CarRentId(carRentId);

	}

	@Override
	public List<GetOrderedAdditionalServiceDto> getAllByCarRent_CarRentId(int carRentId) {
		List<OrderedAdditionalService> orderedAdditionalServices = orderedAdditionalServiceDao.getAllByCarRent_CarRentId(carRentId);
		List<GetOrderedAdditionalServiceDto> getOrderedAdditionalServiceDtos = orderedAdditionalServices.stream()
				.map(orderedAdditionalService -> this.modelMapperService.forDto().map(orderedAdditionalServices, GetOrderedAdditionalServiceDto.class)).collect(Collectors.toList());
		return getOrderedAdditionalServiceDtos;
	}

}
