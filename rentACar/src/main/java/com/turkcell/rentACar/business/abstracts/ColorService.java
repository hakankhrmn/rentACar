package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.business.dtos.ColorListDto;
import com.turkcell.rentACar.business.dtos.GetColorDto;
import com.turkcell.rentACar.business.requests.CreateColorRequest;
import com.turkcell.rentACar.business.requests.UpdateBrandRequest;
import com.turkcell.rentACar.business.requests.UpdateColorRequest;
import com.turkcell.rentACar.entities.concretes.Color;

public interface ColorService {

	List<ColorListDto> getAll();
	void add(CreateColorRequest createColorRequest) throws Exception;
	GetColorDto getById(int id);
	void delete(int id);
	void update(UpdateColorRequest updateColorRequest) throws Exception;
}
