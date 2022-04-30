package com.turkcell.rentACar.business.adapters.posAdapters;

import com.turkcell.rentACar.business.abstracts.PosService;
import com.turkcell.rentACar.business.dtos.cardDtos.CardDto;
import com.turkcell.rentACar.business.outServices.ZiraatBankPosManager;
import com.turkcell.rentACar.core.utilities.results.ErrorResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
import org.springframework.stereotype.Service;

@Service
public class ZiraatBankPosAdapter implements PosService {
    @Override
    public Result pos(CardDto cardDto) {
        ZiraatBankPosManager ziraatBankPosManager = new ZiraatBankPosManager();
        boolean posResult = ziraatBankPosManager.makePayment(cardDto.getCardHolderName(), cardDto.getCvv(), cardDto.getMonth(), cardDto.getYear(), cardDto.getCardNo());
        if (posResult) {
            return new SuccessResult();
        }
        return new ErrorResult();
    }
}
