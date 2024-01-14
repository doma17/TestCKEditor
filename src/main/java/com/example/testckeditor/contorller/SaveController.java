package com.example.testckeditor.contorller;

import com.example.testckeditor.service.ContentService;
import com.example.testckeditor.dto.SaveDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class SaveController {


    private final ContentService contentService;

    @Autowired
    public SaveController(ContentService contentService) {

        this.contentService = contentService;
    }


    @PostMapping("/save")
    public String saveLogic(SaveDto saveDto) {
        log.info("[saveFunc] saveDto = {}", saveDto);
        contentService.saveContent(saveDto);


        return "redirect:/";
    }

    @PostMapping("/save/{id}")
    public String updateLogic(SaveDto saveDto, @PathVariable("id") String id) {

        contentService.updateOneContent(saveDto, id);

        return "redirect:/content/" + id;
    }
}