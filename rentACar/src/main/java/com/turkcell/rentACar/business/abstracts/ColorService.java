package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.entities.concretes.Color;

public interface ColorService {

	List<Color> getAll();
	void add(Color color);
}
