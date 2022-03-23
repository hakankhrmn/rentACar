package com.turkcell.rentACar.business.outServices;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class IsBankPosManager {

    public boolean makePayment(String cardNo, String holderName, String cvv, LocalDate date ) {
        return true;
    }

}
