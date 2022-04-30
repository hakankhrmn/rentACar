package com.turkcell.rentACar.business.concretes;

import com.turkcell.rentACar.business.abstracts.ColorService;
import com.turkcell.rentACar.business.dtos.colorDtos.ColorListDto;
import com.turkcell.rentACar.business.dtos.colorDtos.GetColorDto;
import com.turkcell.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.turkcell.rentACar.business.requests.colorRequests.UpdateColorRequest;
import com.turkcell.rentACar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
import com.turkcell.rentACar.dataAccess.abstracts.ColorDao;
import com.turkcell.rentACar.entities.concretes.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.turkcell.rentACar.business.constants.messages.BusinessMessages.*;

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
	public DataResult<List<ColorListDto>> getAll() {
		
		List<Color> result = this.colorDao.findAll(); 
		List<ColorListDto> response = result.stream()
				.map(color -> this.modelMapperService.forDto().map(color, ColorListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ColorListDto>>(response, SUCCESS_GET_ALL_COLOR);
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) throws Exception {

		checkIfColorNameExists(createColorRequest.getName());

		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);

		this.colorDao.save(color);
		return new SuccessResult(SUCCESS_ADD_COLOR);
	}

	@Override
	public DataResult<GetColorDto> getById(int id) {
		Color color = colorDao.findById(id);
		GetColorDto response = this.modelMapperService.forDto().map(color, GetColorDto.class);
		return new SuccessDataResult<GetColorDto>(response, SUCCESS_GET_BY_ID_COLOR);
	}

	@Override
	public Result delete(int id) {
		this.colorDao.deleteById(id);
		return new SuccessResult(SUCCESS_DELETE_COLOR);
	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) throws Exception {

		checkIfColorExists(updateColorRequest.getColorId());
		checkIfColorNameExists(updateColorRequest.getColorName());

		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		
		this.colorDao.save(color);
		
		return new SuccessResult(SUCCESS_UPDATE_COLOR);
	}

	@Override
	public void checkIfColorExists(int id) {
		if (!this.colorDao.existsById(id)) {
			throw new BusinessException(ERROR_COLOR_DOES_NOT_EXISTS);
		}
	}


	private void checkIfColorNameExists(String name) throws Exception {
		if (this.colorDao.existsByColorName(name)) {
			throw new Exception(ERROR_ADD_UPDATE_COLOR_SAME_NAME);
		}
	}
}
