package com.jimyungkoh.springsecuritymaster.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myLoan")
public class LoansController {
    @GetMapping
    public String getLoanDetails(String input) {
        return "Here are the loan details from the DB";
    }
}
