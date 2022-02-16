package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.business.dtos.BrandListDto;
import com.turkcell.rentACar.business.dtos.GetBrandDto;
import com.turkcell.rentACar.business.requests.CreateBrandRequest;
import com.turkcell.rentACar.business.requests.UpdateBrandRequest;
import com.turkcell.rentACar.entities.concretes.Brand;

public interface BrandService {

	List<BrandListDto> getAll();
	void add(CreateBrandRequest createBrandRequest) throws Exception;
	GetBrandDto getById(int id);
	void delete(int id);
	void update(UpdateBrandRequest updateBrandRequest) throws Exception;
}
