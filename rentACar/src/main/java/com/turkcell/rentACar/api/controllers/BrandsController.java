package com.turkcell.rentACar.api.controllers;

import com.turkcell.rentACar.business.abstracts.BrandService;
import com.turkcell.rentACar.business.dtos.brandDtos.BrandListDto;
import com.turkcell.rentACar.business.dtos.brandDtos.GetBrandDto;
import com.turkcell.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.turkcell.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {
	
	private BrandService brandService;

	public BrandsController(BrandService brandService) {
		super();
		this.brandService = brandService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<BrandListDto>> getAll(){
		return this.brandService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateBrandRequest createBrandRequest) throws Exception {
		return this.brandService.add(createBrandRequest);
	}

	@GetMapping("/getbyid/{id}")
	public DataResult<GetBrandDto> getById(@PathVariable int id) {
		return this.brandService.getById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public Result delete(@PathVariable int id) {
		return this.brandService.delete(id);
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) throws Exception {
		return this.brandService.update(updateBrandRequest);
	}
	

}
