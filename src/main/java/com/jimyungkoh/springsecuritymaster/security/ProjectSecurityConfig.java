package com.jimyungkoh.springsecuritymaster.security;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

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
        http.cors().configurationSource(request -> getCorsConfiguration());

        String[] authMatchers = new String[]{"/myAccount", "/myBalance", "/myLoans", "/myCards"};
        String[] noAuthMatchers = new String[]{"/notices", "/contact"};

        http.authorizeRequests(auth -> auth
                        .antMatchers(authMatchers).authenticated()
                        .antMatchers(noAuthMatchers).permitAll())
                .formLogin()
                .and()
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @NotNull
    private CorsConfiguration getCorsConfiguration() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Collections.singletonList("*"));
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
