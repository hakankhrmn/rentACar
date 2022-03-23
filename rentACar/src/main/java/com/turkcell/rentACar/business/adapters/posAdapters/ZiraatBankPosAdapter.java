package com.turkcell.rentACar.business.adapters.posAdapters;

import com.turkcell.rentACar.business.abstracts.PosService;
import com.turkcell.rentACar.business.dtos.posDtos.PosDto;
import com.turkcell.rentACar.business.outServices.ZiraatBankPosManager;
import com.turkcell.rentACar.core.utilities.results.ErrorResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
import org.springframework.stereotype.Service;

@Service
public class ZiraatBankPosAdapter implements PosService {
    @Override
    public Result pos(PosDto posDto) {
        ZiraatBankPosManager ziraatBankPosManager = new ZiraatBankPosManager();
        boolean posResult = ziraatBankPosManager.makePayment(posDto.getCardHolderName(), posDto.getCvv(), posDto.getExpirationDate(), posDto.getCardNo());
        if (posResult) {
            return new SuccessResult();
        }
        return new ErrorResult();
    }
}
