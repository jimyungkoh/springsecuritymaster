package com.jimyungkoh.springsecuritymaster.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class ProjectSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    /*
     * /myAccount - Secured
     * /myBalance - Secured
     * /myLoan - Secured
     * /myCard - Secured
     * /notice - Not Secured
     * /contact - Not Secured
     * */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(auth -> auth
                        .antMatchers("/myAccount").authenticated()
                        .antMatchers("/myBalance").authenticated()
                        .antMatchers("/myLoan").authenticated()
                        .antMatchers("/myCard").authenticated()
                        .antMatchers("/notice").permitAll()
                        .antMatchers("/contact").permitAll())
                .formLogin()
                .and()
                .httpBasic();

        return http.build();
    }

    //    In-Memory Authentication: First Way
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withUsername("admin")
                .passwordEncoder(passwordEncoder::encode)
                .password("12345")
                .authorities("admin")
                .build();
        UserDetails user = User.withUsername("user")
                .passwordEncoder(passwordEncoder::encode)
                .password("12345")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
