package com.turkcell.rentACar.api.controllers;

import com.turkcell.rentACar.business.abstracts.IndividualCustomerService;
import com.turkcell.rentACar.business.dtos.individualCustomerDtos.GetIndividualCustomerDto;
import com.turkcell.rentACar.business.dtos.individualCustomerDtos.IndividualCustomerListDto;
import com.turkcell.rentACar.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/individual-customers")
public class IndividualCustomersController {
	
	private IndividualCustomerService individualCustomerService;

	@Autowired
	public IndividualCustomersController(IndividualCustomerService individualCustomerService) {
		super();
		this.individualCustomerService = individualCustomerService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<IndividualCustomerListDto>> getAll() {
		return individualCustomerService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		return individualCustomerService.add(createIndividualCustomerRequest);
	}

	@GetMapping("/getbyid/{id}")
	public DataResult<GetIndividualCustomerDto> getById(@RequestParam int id) {
		return individualCustomerService.getById(id);
	}

	@DeleteMapping("/delete/{id}")
	public Result delete(@RequestParam int id) {
		return individualCustomerService.delete(id);
	}

}
