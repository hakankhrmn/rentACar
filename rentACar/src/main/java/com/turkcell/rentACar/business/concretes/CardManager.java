package com.turkcell.rentACar.business.concretes;

import com.turkcell.rentACar.business.abstracts.CardService;
import com.turkcell.rentACar.business.dtos.cardDtos.CardListDto;
import com.turkcell.rentACar.business.requests.cardRequests.CreateCardRequest;
import com.turkcell.rentACar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
import com.turkcell.rentACar.dataAccess.abstracts.CardDao;
import com.turkcell.rentACar.entities.concretes.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.turkcell.rentACar.business.constants.messages.BusinessMessages.*;

@Service
public class CardManager implements CardService {

    private CardDao cardDao;
    private ModelMapperService modelMapperService;


    @Autowired
    public CardManager(CardDao cardDao, ModelMapperService modelMapperService) {
        this.cardDao = cardDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<CardListDto>> getAll() {
        List<Card> cards = cardDao.findAll();
        List<CardListDto> cardListDtos = cards.stream()
                .map(card -> modelMapperService.forDto().map(card, CardListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(cardListDtos, SUCCESS_GET_ALL_CARD);
    }

    @Override
    public Result add(CreateCardRequest createCardRequest) {
        Card card = modelMapperService.forRequest().map(createCardRequest, Card.class);
        card.setCardId(0);
        cardDao.save(card);
        return new SuccessResult(SUCCESS_ADD_CARD);
    }

    @Override
    public Result delete(int id) {

        checkIfCardExists(id);

        cardDao.deleteById(id);
        return new SuccessResult(SUCCESS_DELETE_CARD);
    }

    @Override
    public DataResult<List<CardListDto>> getCustomerCards(int id) {
        List<Card> cards = cardDao.findByCustomer_CustomerId(id);
        List<CardListDto> cardListDtos = cards.stream()
                .map(card -> modelMapperService.forDto().map(card, CardListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(cardListDtos, SUCCESS_GET_CUSTOMER_CARDS_CARD);
    }

    @Override
    public void checkIfCardExists(int id) {
        if (!this.cardDao.existsById(id)) {
            throw new BusinessException(ERROR_CARD_DOES_NOT_EXISTS);
        }
    }
}
