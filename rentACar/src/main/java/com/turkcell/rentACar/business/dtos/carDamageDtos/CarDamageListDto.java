package com.turkcell.rentACar.business.dtos.carDamageDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDamageListDto {

    private int carDamageId;

    private String carDamageInformation;

    private int carId;
}
