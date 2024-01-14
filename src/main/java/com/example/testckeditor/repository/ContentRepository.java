package com.example.testckeditor.repository;

import com.example.testckeditor.entity.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<ContentEntity, Integer> {
    ContentEntity findById(int id);
}
