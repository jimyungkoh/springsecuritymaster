package com.jimyungkoh.springsecuritymaster.security;

import com.jimyungkoh.springsecuritymaster.entity.Customer;
import com.jimyungkoh.springsecuritymaster.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SpringSecurityMasterUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    private final CustomerRepository customerRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        List<Customer> customers = customerRepo.findByEmail(username);

        validateCustomer(customers, pwd);

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(customers.get(0).getRole()));

        return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
    }

    private void validateCustomer(List<Customer> customers, String pwd) {
        if (customers.isEmpty()) {
            throw new BadCredentialsException("No user registered with this details!");
        }

        if (!passwordEncoder.matches(pwd, customers.get(0).getPwd())) {
            throw new BadCredentialsException("Invalid password!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}