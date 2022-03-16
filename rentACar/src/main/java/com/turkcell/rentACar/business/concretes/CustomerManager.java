package com.turkcell.rentACar.business.concretes;

import com.turkcell.rentACar.business.abstracts.CorporateCustomerService;
import com.turkcell.rentACar.business.abstracts.CustomerService;
import com.turkcell.rentACar.business.abstracts.IndividualCustomerService;
import com.turkcell.rentACar.entities.concretes.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerManager implements CustomerService {

    private IndividualCustomerService individualCustomerService;
    private CorporateCustomerService corporateCustomerService;

    @Autowired
    public CustomerManager(IndividualCustomerService individualCustomerService, CorporateCustomerService corporateCustomerService) {
        this.individualCustomerService = individualCustomerService;
        this.corporateCustomerService = corporateCustomerService;
    }

    @Override
    public Customer getCustomerById(int id) {
        if (individualCustomerService.existsByIndividualCustomerId(id)){
            return individualCustomerService.getIndividualCustomerById(id);
        } else if (corporateCustomerService.existsByCorporateCustomerId(id)) {
            return corporateCustomerService.getCorporateCustomerById(id);
        }
        return null;
    }
}
