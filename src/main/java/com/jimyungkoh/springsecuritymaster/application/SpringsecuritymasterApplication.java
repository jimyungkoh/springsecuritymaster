package com.jimyungkoh.springsecuritymaster.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans({
        @ComponentScan("com.jimyungkoh.springsecuritymaster.security"),
        @ComponentScan("com.jimyungkoh.springsecuritymaster.web")})
public class SpringsecuritymasterApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringsecuritymasterApplication.class, args);
    }
}
