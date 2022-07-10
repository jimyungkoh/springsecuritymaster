package com.jimyungkoh.springsecuritymaster.web;

import com.jimyungkoh.springsecuritymaster.entity.Contact;
import com.jimyungkoh.springsecuritymaster.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Random;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactRepository contactRepo;

    @PostMapping
    public Contact saveContactInquiryDetails(@RequestBody Contact contact) {
        contact.setContactId(getServiceReqNumber());
        contact.setCreateDt(new Date(System.currentTimeMillis()));
        return contactRepo.save(contact);
    }

    private String getServiceReqNumber() {
        Random random = new Random();

        int ranNum = random.nextInt(999999999 - 9999) + 9999;

        return "SR" + ranNum;
    }
}
