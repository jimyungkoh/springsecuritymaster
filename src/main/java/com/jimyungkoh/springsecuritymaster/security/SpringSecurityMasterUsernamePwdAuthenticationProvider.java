package com.jimyungkoh.springsecuritymaster.security;

import com.jimyungkoh.springsecuritymaster.entity.Authority;
import com.jimyungkoh.springsecuritymaster.entity.Customer;
import com.jimyungkoh.springsecuritymaster.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SpringSecurityMasterUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    private final CustomerRepository customerRepo;

    private final PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(SpringSecurityMasterUsernamePwdAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        List<Customer> customers = customerRepo.findByEmail(username);

        // Authentication Filter
        validateCustomer(customers, pwd);

        logger.debug(customers.get(0).getAuthorities().toString());

        return new UsernamePasswordAuthenticationToken(username, pwd,
                getGrantedAuthorities(customers.get(0).getAuthorities()));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        authorities.forEach(System.out::println);

        for (Authority authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }

        return grantedAuthorities;
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