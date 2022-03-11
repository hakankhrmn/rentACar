package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.business.dtos.orderedAdditionalServiceDtos.GetOrderedAdditionalServiceDto;
import com.turkcell.rentACar.business.requests.orderedAdditionalServiceRequests.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentACar.business.requests.orderedAdditionalServiceRequests.UpdateOrderedAdditionalServiceRequest;

public interface OrderedAdditionalServiceService {

	public void addAll(List<CreateOrderedAdditionalServiceRequest> createOrderedAdditionalServiceRequests);
	public void updateAll(List<UpdateOrderedAdditionalServiceRequest> updateOrderedAdditionalServiceRequests);
	public void deleteAllByCarRentId(int carRentId);
	List<GetOrderedAdditionalServiceDto> getAllByCarRent_CarRentId(int carRentId);
}
