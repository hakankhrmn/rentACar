package com.turkcell.rentACar.business.requests.cardRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCardRequest {

    private String cardHolderName;

    private String cardNo;

    private int month;

    private int year;

    private int cvv;

    private int customerCustomerId;
}
