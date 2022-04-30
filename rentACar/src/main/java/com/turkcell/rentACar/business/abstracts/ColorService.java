package com.turkcell.rentACar.business.abstracts;

import com.turkcell.rentACar.business.dtos.colorDtos.ColorListDto;
import com.turkcell.rentACar.business.dtos.colorDtos.GetColorDto;
import com.turkcell.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.turkcell.rentACar.business.requests.colorRequests.UpdateColorRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;

import java.util.List;

public interface ColorService {

	DataResult<List<ColorListDto>> getAll();
	Result add(CreateColorRequest createColorRequest) throws Exception;
	DataResult<GetColorDto> getById(int id);
	Result delete(int id);
	Result update(UpdateColorRequest updateColorRequest) throws Exception;
	void checkIfColorExists(int id);
}
