package com.jimyungkoh.springsecuritymaster.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
public class NoticesController {
    @GetMapping
    public String getNotices(String input) {
        return "Here are the notices details from the DB";
    }
}
