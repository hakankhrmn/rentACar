package com.turkcell.rentACar.api.controllers;

import com.turkcell.rentACar.business.abstracts.CardService;
import com.turkcell.rentACar.business.dtos.cardDtos.CardListDto;
import com.turkcell.rentACar.business.requests.cardRequests.CreateCardRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardsController {

    private CardService cardService;

    @Autowired
    public CardsController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/getAll")
    public DataResult<List<CardListDto>> getAll() {
        return cardService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateCardRequest createCardRequest) {
        return cardService.add(createCardRequest);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id) {
        return cardService.delete(id);
    }

    @GetMapping("/getByCustomerId/{id}")
    public DataResult<List<CardListDto>> getCustomerCards(@PathVariable int id) {
        return cardService.getCustomerCards(id);
    }
}
