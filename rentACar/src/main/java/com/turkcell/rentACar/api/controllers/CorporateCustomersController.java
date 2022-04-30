package com.turkcell.rentACar.api.controllers;

import com.turkcell.rentACar.business.abstracts.CorporateCustomerService;
import com.turkcell.rentACar.business.dtos.corporateCustomerDtos.CorporateCustomerListDto;
import com.turkcell.rentACar.business.dtos.corporateCustomerDtos.GetCorporateCustomerDto;
import com.turkcell.rentACar.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/corporate-customers")
public class CorporateCustomersController {
	
	private CorporateCustomerService corporateCustomerService;

	@Autowired
	public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {
		super();
		this.corporateCustomerService = corporateCustomerService;
	}

	@GetMapping("/getall")
	public DataResult<List<CorporateCustomerListDto>> getAll() {
		return corporateCustomerService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		return corporateCustomerService.add(createCorporateCustomerRequest);
	}

	@GetMapping("/getbyid/{id}")
	public DataResult<GetCorporateCustomerDto> getById(@PathVariable int id) {
		return corporateCustomerService.getById(id);
	}

	@DeleteMapping("/delete/{id}")
	public Result delete(@PathVariable int id) {
		return corporateCustomerService.delete(id);
	}
}
