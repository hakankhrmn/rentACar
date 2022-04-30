package com.turkcell.rentACar.business.adapters.posAdapters;

import com.turkcell.rentACar.business.abstracts.PosService;
import com.turkcell.rentACar.business.dtos.cardDtos.CardDto;
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
    public Result pos(CardDto cardDto) {
        IsBankPosManager isBankPosManager = new IsBankPosManager();
        boolean posResult = isBankPosManager.makePayment(cardDto.getCardNo(), cardDto.getCardHolderName(), cardDto.getCvv(), cardDto.getMonth(), cardDto.getYear());
        if (posResult) {
            return new SuccessResult();
        }
        return new ErrorResult();
    }
}
