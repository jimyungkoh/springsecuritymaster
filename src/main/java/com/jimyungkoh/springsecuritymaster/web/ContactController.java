package com.jimyungkoh.springsecuritymaster.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactController {
    @GetMapping
    public String saveContactInquiryDetails(String input) {
        return "Inquiry details are saved to the DB";
    }
}
