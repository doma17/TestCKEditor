package com.example.testckeditor.contorller;

import com.example.testckeditor.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DeleteController {

    private ContentService contentService;

    @Autowired
    public DeleteController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/delete/{id}")
    public String deleteC(@PathVariable("id") String id) {
        contentService.deleteOneContent(id);

        return "redirect:/list";
    }
}
