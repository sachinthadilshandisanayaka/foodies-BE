package com.example.demo.repository;

import com.example.demo.entity.TMnTrDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<TMnTrDocument, Long> {
}
