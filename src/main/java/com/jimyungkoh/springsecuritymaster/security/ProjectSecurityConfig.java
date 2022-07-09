package com.jimyungkoh.springsecuritymaster.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class ProjectSecurityConfig {

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
                        .antMatchers("/myLoans").authenticated()
                        .antMatchers("/myCards").authenticated()
                        .antMatchers("/notices").permitAll()
                        .antMatchers("/contact").permitAll())
                .formLogin()
                .and()
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    //    In-Memory Authentication
    /*@Bean
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
    }*/

    //    JDBC based Authentication
    /*@Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }*/

    /* NoOpPasswordEncoder is not recommended for production usage.
     * Use only for non-production level dev!!!!
     * just for simple JDBC-based Authentication and Custom Authentication
     * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
