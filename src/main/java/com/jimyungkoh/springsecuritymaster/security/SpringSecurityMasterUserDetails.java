package com.jimyungkoh.springsecuritymaster.security;

import com.jimyungkoh.springsecuritymaster.entity.Customer;
import com.jimyungkoh.springsecuritymaster.entity.SecurityCustomer;
import com.jimyungkoh.springsecuritymaster.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpringSecurityMasterUserDetails implements UserDetailsService {

    private final CustomerRepository customerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Customer> customers = customerRepo.findByEmail(username);

        if (customers.isEmpty()) {
            throw new UsernameNotFoundException("User details not found for the user: " + username);
        }

        customers.get(0);

        return new SecurityCustomer(customers.get(0));
    }
}