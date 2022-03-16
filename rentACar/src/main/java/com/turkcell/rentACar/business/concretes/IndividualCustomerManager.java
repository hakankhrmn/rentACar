package com.turkcell.rentACar.business.concretes;

import com.turkcell.rentACar.business.abstracts.IndividualCustomerService;
import com.turkcell.rentACar.business.dtos.individualCustomerDtos.GetIndividualCustomerDto;
import com.turkcell.rentACar.business.dtos.individualCustomerDtos.IndividualCustomerListDto;
import com.turkcell.rentACar.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.turkcell.rentACar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
import com.turkcell.rentACar.dataAccess.abstracts.IndividualCustomerDao;
import com.turkcell.rentACar.entities.concretes.IndividualCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {

	private IndividualCustomerDao individualCustomerDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao,
			ModelMapperService modelMapperService) {
		super();
		this.individualCustomerDao = individualCustomerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<IndividualCustomerListDto>> getAll() {
		List<IndividualCustomer> individualCustomers = individualCustomerDao.findAll();
		List<IndividualCustomerListDto> individualCustomerListDtos = individualCustomers.stream()
				.map(individualCustomer -> modelMapperService.forDto().map(individualCustomer, IndividualCustomerListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<IndividualCustomerListDto>>(individualCustomerListDtos, "Successfully listed individual customers.");
	}

	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		IndividualCustomer individualCustomer = modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
		individualCustomerDao.save(individualCustomer);
		return new SuccessResult("Successfully added individual customer.");
	}

	@Override
	public DataResult<GetIndividualCustomerDto> getById(int id) {
		IndividualCustomer individualCustomer = individualCustomerDao.getById(id);
		GetIndividualCustomerDto getIndividualCustomerDto = modelMapperService.forDto().map(individualCustomer, GetIndividualCustomerDto.class);
		return new SuccessDataResult<GetIndividualCustomerDto>(getIndividualCustomerDto, "Getting individual customer.");
	}

	@Override
	public Result delete(int id) {
		individualCustomerDao.deleteById(id);
		return new SuccessResult("Successfully deleted individual customer with id: " + id);
	}

	@Override
	public boolean existsByIndividualCustomerId(int id) {
		return individualCustomerDao.existsByIndividualCustomerId(id);
	}

	@Override
	public IndividualCustomer getIndividualCustomerById(int id) {
		return individualCustomerDao.getById(id);
	}

}
