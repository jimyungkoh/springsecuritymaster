package com.jimyungkoh.springsecuritymaster.web;

import com.jimyungkoh.springsecuritymaster.entity.Notice;
import com.jimyungkoh.springsecuritymaster.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notices")
@RequiredArgsConstructor
public class NoticesController {
    private final NoticeRepository noticeRepo;

    @GetMapping
    public List<Notice> getNotices(String input) {
        List<Notice> notices = noticeRepo.findAllActiveNotices();

        return notices == null ? null : notices;
    }
}
