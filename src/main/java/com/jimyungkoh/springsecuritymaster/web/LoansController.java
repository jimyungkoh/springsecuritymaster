package com.jimyungkoh.springsecuritymaster.web;

import com.jimyungkoh.springsecuritymaster.entity.Customer;
import com.jimyungkoh.springsecuritymaster.entity.Loans;
import com.jimyungkoh.springsecuritymaster.repository.LoansRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/myLoans")
@RequiredArgsConstructor
public class LoansController {

    private final LoansRepository loansRepository;

    @PostMapping
    public List<Loans> getLoanDetails(@RequestBody Customer customer) {
        List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getId());

        return loans.isEmpty() ? null : loans;
    }
}
