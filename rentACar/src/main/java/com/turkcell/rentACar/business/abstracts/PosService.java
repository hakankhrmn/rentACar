package com.turkcell.rentACar.business.abstracts;

import com.turkcell.rentACar.business.dtos.posDtos.PosDto;
import com.turkcell.rentACar.core.utilities.results.Result;

public interface PosService {
    Result pos(PosDto posDto);
}
