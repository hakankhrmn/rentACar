package com.turkcell.rentACar.business.dtos.cardDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardListDto {

    private int cardId;

    private String cardHolderName;

    private String cardNo;

    private int month;

    private int year;

    private int cvv;

    private int customerCustomerId;
}

