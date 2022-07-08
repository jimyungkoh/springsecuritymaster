package com.jimyungkoh.springsecuritymaster.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myAccount")
public class AccountController {
    @GetMapping
    public String getAccountDetails(String input) {
        return "Here are the account details from the DB";
    }
}
