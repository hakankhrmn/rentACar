package com.turkcell.rentACar.business.concretes;

import com.turkcell.rentACar.business.abstracts.ZiraatBankPosService;
import com.turkcell.rentACar.business.dtos.posDtos.PosDto;
import com.turkcell.rentACar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
import org.springframework.stereotype.Service;

import static com.turkcell.rentACar.business.constants.messages.BusinessMessages.ERROR_POS_DOES_NOT_EXISTS;

@Service
public class ZiraatBankPosManager implements ZiraatBankPosService {

    @Override
    public Result pos(PosDto posDto) {
        checkIfExistsPos(posDto);
        return new SuccessResult();
    }

    private boolean checkIfExistsPos(PosDto posDto) {
        boolean exists = true;
        if (!exists) {
            throw new BusinessException(ERROR_POS_DOES_NOT_EXISTS);
        }
        return exists;
    }
}
