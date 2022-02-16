package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.business.dtos.GetColorDto;
import com.turkcell.rentACar.business.requests.CreateColorRequest;
import com.turkcell.rentACar.entities.concretes.Color;

public interface ColorService {

	List<Color> getAll();
	void add(CreateColorRequest createColorRequest) throws Exception;
	GetColorDto getById(int id);
}
