package com.turkcell.rentACar.business.abstracts;

import com.turkcell.rentACar.entities.concretes.Customer;

public interface CustomerService {
    Customer getCustomerById(int id);
    void checkIfCustomerExists(int id);
}
