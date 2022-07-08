package com.jimyungkoh.springsecuritymaster.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myCard")
public class CardsController {
    @GetMapping
    public String getCardDetails(String input) {
        return "Here are the card details from the DB";
    }
}
