package com.example.testckeditor.service;

import com.example.testckeditor.entity.ContentEntity;
import com.example.testckeditor.dto.SaveDto;
import com.example.testckeditor.repository.ContentRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {

    private final ContentRepository contentRepository;

    @Autowired
    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public void saveContent(SaveDto saveDto) {

        String title = saveDto.getTitle();
        String content = saveDto.getContent();

        ContentEntity entity = new ContentEntity();

        entity.setTitle(title);
        entity.setContent(content);


        contentRepository.save(entity);

        return;
    }

    public List<ContentEntity> selectContent() {

        return contentRepository.findAll();
    }

    public ContentEntity selectOneContent(String id) {
        int to = Integer.parseInt(id);

        return contentRepository.findById(to);
    }

    public void deleteOneContent(String id) {
        int to = Integer.parseInt(id);

        contentRepository.deleteById(to);
    }

    public void updateOneContent(SaveDto saveDto, String id) {
        int to = Integer.parseInt(id);

        ContentEntity content = new ContentEntity();
        content.setId(to);
        content.setTitle(saveDto.getTitle());
        content.setContent(saveDto.getContent());

        contentRepository.save(content);
    }

}