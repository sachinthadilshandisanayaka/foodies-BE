package com.example.demo.repository;

import com.example.demo.entity.TMnTrReact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactRepository extends JpaRepository<TMnTrReact, Long> {
}
