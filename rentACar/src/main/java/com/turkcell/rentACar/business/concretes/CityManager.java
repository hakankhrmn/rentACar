package com.turkcell.rentACar.business.concretes;

import com.turkcell.rentACar.business.abstracts.CityService;
import com.turkcell.rentACar.business.dtos.cityDtos.CityListDto;
import com.turkcell.rentACar.business.dtos.cityDtos.GetCityDto;
import com.turkcell.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.turkcell.rentACar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
import com.turkcell.rentACar.dataAccess.abstracts.CityDao;
import com.turkcell.rentACar.entities.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.turkcell.rentACar.business.constants.messages.BusinessMessages.*;

@Service
public class CityManager implements CityService {

    private CityDao cityDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
        this.cityDao = cityDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<CityListDto>> getAll() {
        List<City> cities = cityDao.findAll();
        List<CityListDto> cityListDtos = cities.stream()
                .map(city -> modelMapperService.forDto().map(city, CityListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CityListDto>>(cityListDtos, SUCCESS_GET_ALL_CITY);
    }

    @Override
    public Result add(CreateCityRequest createCityRequest) {
        City city = modelMapperService.forRequest().map(createCityRequest, City.class);
        cityDao.save(city);
        return new SuccessResult(SUCCESS_ADD_CITY);
    }

    @Override
    public DataResult<GetCityDto> getById(int id) {
        City city = cityDao.findById(id);
        GetCityDto getCityDto = modelMapperService.forDto().map(city, GetCityDto.class);
        return new SuccessDataResult<GetCityDto>(getCityDto, SUCCESS_GET_BY_ID_CITY);
    }

    @Override
    public Result delete(int id) {
        cityDao.deleteById(id);
        return new SuccessResult(SUCCESS_DELETE_CITY);
    }

    @Override
    public City getCityById(int id) {
        return cityDao.findById(id);
    }
}
