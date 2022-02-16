package com.turkcell.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentACar.business.abstracts.ColorService;
import com.turkcell.rentACar.business.dtos.CarListDto;
import com.turkcell.rentACar.business.dtos.ColorListDto;
import com.turkcell.rentACar.business.dtos.GetColorDto;
import com.turkcell.rentACar.business.requests.CreateColorRequest;
import com.turkcell.rentACar.business.requests.UpdateColorRequest;
import com.turkcell.rentACar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar.dataAccess.abstracts.ColorDao;
import com.turkcell.rentACar.entities.concretes.Brand;
import com.turkcell.rentACar.entities.concretes.Car;
import com.turkcell.rentACar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService {

	private ColorDao colorDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
		super();
		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public List<ColorListDto> getAll() {
		
		List<Color> result = this.colorDao.findAll(); 
		List<ColorListDto> response = result.stream()
				.map(color -> this.modelMapperService.forDto().map(color, ColorListDto.class))
				.collect(Collectors.toList());
		return response;
	}

	@Override
	public void add(CreateColorRequest createColorRequest) throws Exception {
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		checkIfColorExists(createColorRequest.getName());
		this.colorDao.save(color);
	}

	@Override
	public GetColorDto getById(int id) {
		Color color = colorDao.findById(id);
		return this.modelMapperService.forDto().map(color, GetColorDto.class);
	}

	private void checkIfColorExists(String name) throws Exception {
		if (this.colorDao.existsByColorName(name)) {
			throw new Exception("Aynı isimde renk eklenemez");
		}
	}
	private void checkIfColorNameExists(Color color) throws Exception {
		
		Color ifExsistsColor= this.colorDao.findByColorName(color.getColorName());
		
		if (ifExsistsColor != null && ifExsistsColor.getColorId()!=color.getColorId()) {
			throw new Exception("Aynı isimde renk eklenemez");
		}
	}

	@Override
	public void delete(int id) {
		this.colorDao.deleteById(id);
		
	}

	@Override
	public void update(UpdateColorRequest updateColorRequest) throws Exception {
		
		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		
		checkIfColorNameExists(color);
		
		this.colorDao.save(color);
		
	}

}
