package com.jimyungkoh.springsecuritymaster.security;

import com.jimyungkoh.springsecuritymaster.filter.*;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ProjectSecurityConfig {
    /* NoOpPasswordEncoder is not recommended for production usage.
     * Use only for non-production level dev!!!!
     * just for simple JDBC-based Authentication and Custom Authentication
     * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        setSessionManagement(http);
        setCors(http);
        setCsrf(http);
        setHttpFilters(http);

        String[] noAuthMatchers = new String[]{"/notices", "/contact"};

        http.authorizeRequests(auth -> auth
                        .antMatchers("/myAccount").hasRole("USER")
                        .antMatchers("/myBalance").hasAnyRole("ADMIN", "USER")
                        .antMatchers("/myLoans").hasRole("ROOT")
                        .antMatchers("/myCards").authenticated()
                        .antMatchers("/user").authenticated()
                        .antMatchers(noAuthMatchers).permitAll())
                .formLogin()
                .and()
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    private void setSessionManagement(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    private void setCors(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> getCorsConfiguration());
    }

    private void setCsrf(HttpSecurity http) throws Exception {
        http.csrf().disable();
                /*.ignoringAntMatchers("/contact")
                .csrfTokenRepository(
                        CookieCsrfTokenRepository.withHttpOnlyFalse());*/
    }

    private void setHttpFilters(HttpSecurity http) {
        http.addFilterBefore(new RequestValidationBeforeFilter(),
                        BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(),
                        BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(),
                        BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(),
                        BasicAuthenticationFilter.class)
                .addFilterAt(new AuthoritiesLoggingAtFilter(),
                        BasicAuthenticationFilter.class);
    }

    @NotNull
    private CorsConfiguration getCorsConfiguration() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setExposedHeaders(List.of("Authorization"));
        config.setMaxAge(3600L);
        return config;
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
}
