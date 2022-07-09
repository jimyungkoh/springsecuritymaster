package com.jimyungkoh.springsecuritymaster.web;

import com.jimyungkoh.springsecuritymaster.entity.Cards;
import com.jimyungkoh.springsecuritymaster.entity.Customer;
import com.jimyungkoh.springsecuritymaster.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/myCards")
@RequiredArgsConstructor
public class CardsController {
    private final CardsRepository cardsRepository;

    @PostMapping
    public List<Cards> getCardDetails(@RequestBody Customer customer) {
        List<Cards> cards = cardsRepository.findByCustomerId(customer.getId());

        return cards.isEmpty() ? null : cards;
    }
}
