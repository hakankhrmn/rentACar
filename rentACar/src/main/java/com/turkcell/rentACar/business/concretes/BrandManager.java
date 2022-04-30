package com.turkcell.rentACar.business.concretes;

import com.turkcell.rentACar.business.abstracts.BrandService;
import com.turkcell.rentACar.business.dtos.brandDtos.BrandListDto;
import com.turkcell.rentACar.business.dtos.brandDtos.GetBrandDto;
import com.turkcell.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.turkcell.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.turkcell.rentACar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
import com.turkcell.rentACar.dataAccess.abstracts.BrandDao;
import com.turkcell.rentACar.entities.concretes.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.turkcell.rentACar.business.constants.messages.BusinessMessages.*;

@Service
public class BrandManager implements BrandService{
	
	private BrandDao brandDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
		super();
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<BrandListDto>> getAll() {
		
		List<Brand> result = this.brandDao.findAll(); 
		List<BrandListDto> response = result.stream()
				.map(brand -> this.modelMapperService.forDto().map(brand, BrandListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<BrandListDto>>(response, SUCCESS_GET_ALL_BRAND);
	}

	@Override
	public Result add(CreateBrandRequest createBrandRequest) throws Exception {

		checkIfBrandNameExists(createBrandRequest.getName());

		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandDao.save(brand);

		return new SuccessResult(SUCCESS_ADD_BRAND);
		
	}

	@Override
	public DataResult<GetBrandDto> getById(int id) {

		Brand brand = brandDao.findById(id);
		GetBrandDto response = this.modelMapperService.forDto().map(brand, GetBrandDto.class);
		return new SuccessDataResult<GetBrandDto>(response,SUCCESS_GET_BY_ID_BRAND);
	}

	@Override
	public Result delete(int id) {

		checkIfBrandExists(id);

		this.brandDao.deleteById(id);
		return new SuccessResult(SUCCESS_DELETE_BRAND);
	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) throws Exception {

		checkIfBrandExists(updateBrandRequest.getBrandId());
		checkIfBrandNameExists(updateBrandRequest.getBrandName());

		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandDao.save(brand);
		return new SuccessResult(SUCCESS_UPDATE_BRAND);
		
	}

	@Override
	public void checkIfBrandExists(int id) {
		if (!this.brandDao.existsById(id)) {
			throw new BusinessException(ERROR_BRAND_DOES_NOT_EXISTS);
		}
	}

	private void checkIfBrandNameExists(String name) throws Exception {
		if (this.brandDao.existsByBrandName(name)) {
			throw new BusinessException(ERROR_ADD_UPDATE_BRAND_SAME_NAME);
		}
	}

}
