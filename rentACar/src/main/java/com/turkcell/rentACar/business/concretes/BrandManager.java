package com.turkcell.rentACar.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.turkcell.rentACar.business.abstracts.BrandService;
import com.turkcell.rentACar.business.dtos.GetBrandDto;
import com.turkcell.rentACar.business.requests.CreateBrandRequest;
import com.turkcell.rentACar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar.dataAccess.abstracts.BrandDao;
import com.turkcell.rentACar.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService{
	
	private BrandDao brandDao;
	private ModelMapperService modelMapperService;

	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
		super();
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public List<Brand> getAll() {
		
		return this.brandDao.findAll();
	}

	

	@Override
	public void add(CreateBrandRequest createBrandRequest) throws Exception {
		try {
			Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
			checkIfBrandExist(createBrandRequest.getName());
			this.brandDao.save(brand);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public GetBrandDto getById(int id) {
		Brand brand = brandDao.findById(id);
		return this.modelMapperService.forDto().map(brand, GetBrandDto.class);
	}
	
	private void checkIfBrandExist(String name) throws Exception {
		if (this.brandDao.existsByName(name)) {
			throw new Exception("Aynı isimde marka eklenemez");
		}
	}

}
