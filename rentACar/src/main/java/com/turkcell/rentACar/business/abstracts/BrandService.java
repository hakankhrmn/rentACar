package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.business.dtos.GetBrandDto;
import com.turkcell.rentACar.business.requests.CreateBrandRequest;
import com.turkcell.rentACar.entities.concretes.Brand;

public interface BrandService {

	List<Brand> getAll();
	void add(CreateBrandRequest createBrandRequest) throws Exception;
	GetBrandDto getById(int id);
}
