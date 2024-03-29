package com.turkcell.rentACar.business.concretes;

import com.turkcell.rentACar.business.abstracts.AdditionalServiceService;
import com.turkcell.rentACar.business.dtos.additionalServiceDtos.AdditionalServiceListDto;
import com.turkcell.rentACar.business.dtos.additionalServiceDtos.GetAdditionalServiceDto;
import com.turkcell.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.turkcell.rentACar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
import com.turkcell.rentACar.dataAccess.abstracts.AdditionalServiceDao;
import com.turkcell.rentACar.entities.concretes.AdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.turkcell.rentACar.business.constants.messages.BusinessMessages.*;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {

	private AdditionalServiceDao additionalServiceDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, ModelMapperService modelMapperService) {
		super();
		this.additionalServiceDao = additionalServiceDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<AdditionalServiceListDto>> getAll() {
		List<AdditionalService> result = this.additionalServiceDao.findAll();
		List<AdditionalServiceListDto> response = result.stream()
				.map(additionalService -> this.modelMapperService.forDto().map(additionalService, AdditionalServiceListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<AdditionalServiceListDto>>(response, SUCCESS_GET_ALL_ADDITIONAL_SERVICE);
	}

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		AdditionalService additionalService = this.modelMapperService.forRequest().map(createAdditionalServiceRequest, AdditionalService.class);
		additionalServiceDao.save(additionalService);
		return new SuccessResult(SUCCESS_ADD_ADDITIONAL_SERVICE);
	}

	@Override
	public DataResult<GetAdditionalServiceDto> getById(int id) {

		AdditionalService additionalService = this.additionalServiceDao.findById(id);
		GetAdditionalServiceDto response = this.modelMapperService.forDto().map(additionalService, GetAdditionalServiceDto.class);
		return new SuccessDataResult<GetAdditionalServiceDto>(response, SUCCESS_GET_BY_ID_ADDITIONAL_SERVICE);
	}

	@Override
	public Result delete(int id) {

		checkIfAdditionalServiceExists(id);

		this.additionalServiceDao.deleteById(id);
		return new SuccessResult(SUCCESS_DELETE_ADDITIONAL_SERVICE);
	}

	@Override
	public void checkIfAdditionalServiceExists(int id) {
		if (!this.additionalServiceDao.existsById(id)) {
			throw new BusinessException(ERROR_ADDITIONAL_SERVICE_DOES_NOT_EXISTS);
		}
	}

}
