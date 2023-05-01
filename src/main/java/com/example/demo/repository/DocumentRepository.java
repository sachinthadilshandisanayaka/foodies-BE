package com.example.demo.repository;

import com.example.demo.entity.TMnTrDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentRepository extends JpaRepository<TMnTrDocument, Long> {
    @Query("SELECT t FROM TMnTrDocument t WHERE t.content.cntID=:cntId")
    public List<TMnTrDocument> getContentByCntID(@Param("cntId") long cntId);
}
