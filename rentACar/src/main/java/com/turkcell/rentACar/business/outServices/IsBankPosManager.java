package com.turkcell.rentACar.business.outServices;

import org.springframework.stereotype.Service;

@Service
public class IsBankPosManager {

    public boolean makePayment(String cardNo, String holderName, int cvv, int mounth, int year ) {
        return true;
    }

}
