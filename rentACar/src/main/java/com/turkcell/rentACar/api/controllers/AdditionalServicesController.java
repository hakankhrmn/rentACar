package com.turkcell.rentACar.api.controllers;

import com.turkcell.rentACar.business.abstracts.AdditionalServiceService;
import com.turkcell.rentACar.business.dtos.additionalServiceDtos.AdditionalServiceListDto;
import com.turkcell.rentACar.business.dtos.additionalServiceDtos.GetAdditionalServiceDto;
import com.turkcell.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	public DataResult<GetAdditionalServiceDto> getById(@PathVariable int id) {
		return additionalServiceService.getById(id);
	}

	@DeleteMapping("/delete/{id}")
	public Result delete(@PathVariable int id) {
		return additionalServiceService.delete(id);
	}

}
