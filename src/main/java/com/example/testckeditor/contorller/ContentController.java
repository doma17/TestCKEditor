package com.example.testckeditor.contorller;

import com.example.testckeditor.service.ContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class ContentController {

    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/content/{id}")
    public String contentPage(@PathVariable("id") String id, Model model) {
        log.info("[contentPage] id : {}", id);

        model.addAttribute("Content", contentService.selectOneContent(id));

        return "content";
    }

}
