package com.turkcell.rentACar.business.dtos.posDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PosDto {

    private String cardHolderName;

    private String cardNo;

    private LocalDate expirationDate;

    private String cvv;
}
