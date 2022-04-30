package com.turkcell.rentACar.business.outServices;

import org.springframework.stereotype.Service;

@Service
public class ZiraatBankPosManager {

    public boolean makePayment(String holderName, int cvv, int month, int year, String cardNo) {
        return true;
    }
}
