package com.jimyungkoh.springsecuritymaster.web;

import com.jimyungkoh.springsecuritymaster.entity.AccountTransactions;
import com.jimyungkoh.springsecuritymaster.entity.Customer;
import com.jimyungkoh.springsecuritymaster.repository.AccountTransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/myBalance")
@RequiredArgsConstructor
public class BalanceController {
    private final AccountTransactionsRepository accountTransactionsRepo;

    @PostMapping
    public List<AccountTransactions> getBalanceDetails(@RequestBody Customer customer) {
        List<AccountTransactions> accountTransactions = accountTransactionsRepo
                .findByCustomerIdOrderByTransactionDtDesc(customer.getId());

        return accountTransactions.isEmpty() ? null : accountTransactions;
    }
}
