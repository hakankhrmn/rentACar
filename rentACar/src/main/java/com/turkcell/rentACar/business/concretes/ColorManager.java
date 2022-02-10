package com.turkcell.rentACar.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.turkcell.rentACar.business.abstracts.ColorService;
import com.turkcell.rentACar.dataAccess.abstracts.ColorDao;
import com.turkcell.rentACar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService {

	private ColorDao colorDao;
	
	
	public ColorManager(ColorDao colorDao) {
		super();
		this.colorDao = colorDao;
	}

	@Override
	public List<Color> getAll() {
		
		return this.colorDao.findAll();
	}

	@Override
	public void add(Color color) {
		
		if(this.colorDao.findByName(color.getName())!=null) {
			System.out.println("hata");
		}else {
			this.colorDao.save(color);
		}
		
		
	}

}
