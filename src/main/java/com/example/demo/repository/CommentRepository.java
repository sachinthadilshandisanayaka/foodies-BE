package com.example.demo.repository;

import com.example.demo.entity.TMnTrComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<TMnTrComment, Long> {

    @Query("SELECT t FROM TMnTrComment t WHERE t.cmtID=:cmtID")
    public TMnTrComment getCommentByID(@Param("cmtID") long cmtID);

    @Query("SELECT t FROM TMnTrComment t WHERE t.content.cntID=:cntId")
    public List<TMnTrComment> getCommentByCntID(@Param("cntId") long cntId);

    @Query("SELECT t FROM TMnTrComment t WHERE t.content.cntID=:cntId AND t.user.userId=:userId")
    public TMnTrComment getCommentByCntIDAndUserID(@Param("cntId") long cntId, @Param("userId") long userId);
}
