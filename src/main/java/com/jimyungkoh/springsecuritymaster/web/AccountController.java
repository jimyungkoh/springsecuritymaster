package com.jimyungkoh.springsecuritymaster.web;

import com.jimyungkoh.springsecuritymaster.entity.Accounts;
import com.jimyungkoh.springsecuritymaster.entity.Customer;
import com.jimyungkoh.springsecuritymaster.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myAccount")
@RequiredArgsConstructor
public class AccountController {
    private final AccountsRepository accountsRepo;

    @PostMapping
    public Accounts getAccountDetails(@RequestBody Customer customer) {
        Accounts accounts = accountsRepo.findByCustomerId(customer.getId());

        return accounts != null ? accounts : null;
    }
}
