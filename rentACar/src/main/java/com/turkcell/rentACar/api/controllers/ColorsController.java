package com.turkcell.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentACar.business.abstracts.ColorService;
import com.turkcell.rentACar.business.dtos.GetColorDto;
import com.turkcell.rentACar.business.requests.CreateColorRequest;
import com.turkcell.rentACar.entities.concretes.Color;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {

	private final ColorService colorService;

	public ColorsController(ColorService colorService) {
		super();
		this.colorService = colorService;
	}
	
	@GetMapping("/getall")
	public List<Color> getAll(){
		return colorService.getAll();
	}
	
	@PostMapping("/add")
	public void add(@RequestBody CreateColorRequest createColorRequest) throws Exception {
		this.colorService.add(createColorRequest);
	}

	@GetMapping("/getbyid/{id}")
	public GetColorDto getById(@RequestParam int id) {
		return this.colorService.getById(id);
	}
	
}
