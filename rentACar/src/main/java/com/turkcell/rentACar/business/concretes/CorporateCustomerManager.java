package com.turkcell.rentACar.business.concretes;

import com.turkcell.rentACar.business.abstracts.CorporateCustomerService;
import com.turkcell.rentACar.business.dtos.corporateCustomerDtos.CorporateCustomerListDto;
import com.turkcell.rentACar.business.dtos.corporateCustomerDtos.GetCorporateCustomerDto;
import com.turkcell.rentACar.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.turkcell.rentACar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
import com.turkcell.rentACar.dataAccess.abstracts.CorporateCustomerDao;
import com.turkcell.rentACar.entities.concretes.CorporateCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.turkcell.rentACar.business.constants.messages.BusinessMessages.*;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {

	private CorporateCustomerDao corporateCustomerDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService) {
		super();
		this.corporateCustomerDao = corporateCustomerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<CorporateCustomerListDto>> getAll() {
		List<CorporateCustomer> corporateCustomers = corporateCustomerDao.findAll();
		List<CorporateCustomerListDto> corporateCustomerListDtos = corporateCustomers.stream()
				.map(corporateCustomer -> modelMapperService.forDto().map(corporateCustomer, CorporateCustomerListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CorporateCustomerListDto>>(corporateCustomerListDtos, SUCCESS_GET_ALL_CORPORATE_CUSTOMER);
	}

	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);
		corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(SUCCESS_ADD_CORPORATE_CUSTOMER);
	}

	@Override
	public DataResult<GetCorporateCustomerDto> getById(int id) {
		CorporateCustomer corporateCustomer = corporateCustomerDao.getById(id);
		GetCorporateCustomerDto getCorporateCustomerDto = modelMapperService.forDto().map(corporateCustomer, GetCorporateCustomerDto.class);
		return new SuccessDataResult<GetCorporateCustomerDto>(getCorporateCustomerDto, SUCCESS_GET_BY_ID_CORPORATE_CUSTOMER);
	}

	@Override
	public Result delete(int id) {
		corporateCustomerDao.deleteById(id);
		return new SuccessResult(SUCCESS_DELETE_CORPORATE_CUSTOMER);
	}

	@Override
	public boolean existsByCorporateCustomerId(int id) {
		return corporateCustomerDao.existsByCorporateCustomerId(id);
	}

	@Override
	public CorporateCustomer getCorporateCustomerById(int id) {
		return corporateCustomerDao.getById(id);
	}

}
