package com.turkcell.rentACar.business.adapters.posAdapters;

import com.turkcell.rentACar.business.abstracts.PosService;
import com.turkcell.rentACar.business.dtos.posDtos.PosDto;
import com.turkcell.rentACar.business.outServices.IsBankPosManager;
import com.turkcell.rentACar.core.utilities.results.ErrorResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class IsBankPosAdapter implements PosService {

    @Override
    public Result pos(PosDto posDto) {
        IsBankPosManager isBankPosManager = new IsBankPosManager();
        boolean posResult = isBankPosManager.makePayment(posDto.getCardNo(), posDto.getCardHolderName(), posDto.getCvv(), posDto.getExpirationDate());
        if (posResult) {
            return new SuccessResult();
        }
        return new ErrorResult();
    }
}
