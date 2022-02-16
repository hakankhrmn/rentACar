package com.turkcell.rentACar.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.turkcell.rentACar.business.abstracts.ColorService;
import com.turkcell.rentACar.business.dtos.GetColorDto;
import com.turkcell.rentACar.business.requests.CreateColorRequest;
import com.turkcell.rentACar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar.dataAccess.abstracts.ColorDao;
import com.turkcell.rentACar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService {

	private ColorDao colorDao;
	private ModelMapperService modelMapperService;
	
	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
		super();
		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public List<Color> getAll() {
		
		return this.colorDao.findAll();
	}

	@Override
	public void add(CreateColorRequest createColorRequest) throws Exception {
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		checkIfColorExist(createColorRequest.getName());
		this.colorDao.save(color);
	}

	@Override
	public GetColorDto getById(int id) {
		Color color = colorDao.findById(id);
		return this.modelMapperService.forDto().map(color, GetColorDto.class);
	}

	private void checkIfColorExist(String name) throws Exception {
		if (this.colorDao.existsByName(name)) {
			throw new Exception("Aynı isimde renk eklenemez");
		}
	}

}
