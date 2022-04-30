package com.turkcell.rentACar.api.controllers;

import com.turkcell.rentACar.business.abstracts.ColorService;
import com.turkcell.rentACar.business.dtos.colorDtos.ColorListDto;
import com.turkcell.rentACar.business.dtos.colorDtos.GetColorDto;
import com.turkcell.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.turkcell.rentACar.business.requests.colorRequests.UpdateColorRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {

	private final ColorService colorService;

	public ColorsController(ColorService colorService) {
		super();
		this.colorService = colorService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<ColorListDto>> getAll(){
		return colorService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateColorRequest createColorRequest) throws Exception {
		return this.colorService.add(createColorRequest);
	}

	@GetMapping("/getbyid/{id}")
	public DataResult<GetColorDto> getById(@PathVariable int id) {
		return this.colorService.getById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public Result delete(@PathVariable int id) {
		return this.colorService.delete(id);
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateColorRequest updateColorRequest) throws Exception {
		return this.colorService.update(updateColorRequest);
	}
	
}
