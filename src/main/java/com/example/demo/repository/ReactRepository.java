package com.example.demo.repository;

import com.example.demo.entity.TMnTrReact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactRepository extends JpaRepository<TMnTrReact, Long> {

    @Query("SELECT t FROM TMnTrReact t WHERE t.content.cntID=:cntId")
    public List<TMnTrReact> getContentByCntID(@Param("cntId") long cntId);

    @Query("SELECT t FROM TMnTrReact t WHERE t.content.cntID=:cntId AND t.user.userId=:userId")
    public TMnTrReact getContentByCntIDAndUserID(@Param("cntId") long cntId, @Param("userId") long userId);
}
