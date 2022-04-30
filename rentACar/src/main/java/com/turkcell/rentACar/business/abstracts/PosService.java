package com.turkcell.rentACar.business.abstracts;

import com.turkcell.rentACar.business.dtos.cardDtos.CardDto;
import com.turkcell.rentACar.core.utilities.results.Result;

public interface PosService {
    Result pos(CardDto cardDto);
}
