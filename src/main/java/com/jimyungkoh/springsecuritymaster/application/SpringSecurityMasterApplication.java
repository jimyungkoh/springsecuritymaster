package com.jimyungkoh.springsecuritymaster.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@ComponentScans({
        @ComponentScan("com.jimyungkoh.springsecuritymaster.security"),
        @ComponentScan("com.jimyungkoh.springsecuritymaster.web")})
@EntityScan({"com.jimyungkoh.springsecuritymaster.entity"})
@EnableJpaRepositories({"com.jimyungkoh.springsecuritymaster.repository"})
@EnableWebSecurity(debug = true)
public class SpringSecurityMasterApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringSecurityMasterApplication.class, args);
    }
}
