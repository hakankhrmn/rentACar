package com.turkcell.rentACar.business.concretes;

import com.turkcell.rentACar.business.abstracts.CorporateCustomerService;
import com.turkcell.rentACar.business.abstracts.CustomerService;
import com.turkcell.rentACar.business.abstracts.IndividualCustomerService;
import com.turkcell.rentACar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACar.dataAccess.abstracts.CustomerDao;
import com.turkcell.rentACar.entities.concretes.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.turkcell.rentACar.business.constants.messages.BusinessMessages.ERROR_CUSTOMER_DOES_NOT_EXISTS;

@Service
public class CustomerManager implements CustomerService {

    private IndividualCustomerService individualCustomerService;
    private CorporateCustomerService corporateCustomerService;
    private CustomerDao customerDao;

    @Autowired
    public CustomerManager(IndividualCustomerService individualCustomerService, CorporateCustomerService corporateCustomerService, CustomerDao customerDao) {
        this.individualCustomerService = individualCustomerService;
        this.corporateCustomerService = corporateCustomerService;
        this.customerDao = customerDao;
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

    @Override
    public void checkIfCustomerExists(int id) {
        if (!this.customerDao.existsById(id)) {
            throw new BusinessException(ERROR_CUSTOMER_DOES_NOT_EXISTS);
        }
    }
}
