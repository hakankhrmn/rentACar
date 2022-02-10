package com.turkcell.rentACar.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.turkcell.rentACar.business.abstracts.BrandService;
import com.turkcell.rentACar.dataAccess.abstracts.BrandDao;
import com.turkcell.rentACar.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService{
	
	private BrandDao brandDao;

	public BrandManager(BrandDao brandDao) {
		super();
		this.brandDao = brandDao;
	}

	@Override
	public List<Brand> getAll() {
		
		return this.brandDao.findAll();
	}

	@Override
	public void add(Brand brand) {

		if(brandDao.findByName(brand.getName())!=null) {
			System.out.println("hata");
		}else {
			brandDao.save(brand);
		}
	}

}
