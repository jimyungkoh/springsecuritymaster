package com.jimyungkoh.springsecuritymaster.web;

import com.jimyungkoh.springsecuritymaster.entity.Customer;
import com.jimyungkoh.springsecuritymaster.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final CustomerRepository customerRepo;

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Principal user) {
        List<Customer> customers = customerRepo.findByEmail(user.getName());

        return customers.isEmpty() ? null : customers.get(0);
    }
}
