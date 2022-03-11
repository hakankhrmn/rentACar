package com.turkcell.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentACar.business.abstracts.AdditionalServiceService;
import com.turkcell.rentACar.business.dtos.additionalServiceDtos.AdditionalServiceListDto;
import com.turkcell.rentACar.business.dtos.additionalServiceDtos.GetAdditionalServiceDto;
import com.turkcell.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additional-services")
public class AdditionalServicesController {
	
	private AdditionalServiceService additionalServiceService;
	
	@Autowired
	public AdditionalServicesController(AdditionalServiceService additionalServiceService) {
		super();
		this.additionalServiceService = additionalServiceService;
	}

	@GetMapping("/getAll")
	public DataResult<List<AdditionalServiceListDto>> getAll() {
		return additionalServiceService.getAll();
	}

	@PostMapping("/add")
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		return additionalServiceService.add(createAdditionalServiceRequest);
	}

	@GetMapping("/get/{id}")
	public DataResult<GetAdditionalServiceDto> getById(int id) {
		return additionalServiceService.getById(id);
	}

	@DeleteMapping("/delete/{id}")
	public Result delete(int id) {
		return additionalServiceService.delete(id);
	}

}
