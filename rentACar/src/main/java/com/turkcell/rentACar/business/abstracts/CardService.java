package com.turkcell.rentACar.business.abstracts;

import com.turkcell.rentACar.business.dtos.cardDtos.CardListDto;
import com.turkcell.rentACar.business.requests.cardRequests.CreateCardRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;

import java.util.List;

public interface CardService {
    DataResult<List<CardListDto>> getAll();
    Result add(CreateCardRequest createCardRequest);
    Result delete(int id);
    DataResult<List<CardListDto>> getCustomerCards(int id);
    void checkIfCardExists(int id);
}
