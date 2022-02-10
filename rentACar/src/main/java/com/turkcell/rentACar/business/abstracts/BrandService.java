package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.entities.concretes.Brand;

public interface BrandService {

	List<Brand> getAll();
	void add(Brand brand);
}
