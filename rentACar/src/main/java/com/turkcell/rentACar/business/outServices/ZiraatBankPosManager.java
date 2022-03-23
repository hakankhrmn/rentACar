package com.turkcell.rentACar.business.outServices;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ZiraatBankPosManager {

    public boolean makePayment(String holderName, String cvv, LocalDate date, String cardNo) {
        return true;
    }
}
