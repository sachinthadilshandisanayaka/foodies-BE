package com.example.demo.repository;

import com.example.demo.dto.content.ContentResDTO;
import com.example.demo.entity.TMnTrContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<TMnTrContent, Long> {

    @Query("SELECT t FROM TMnTrContent t WHERE t.cntID=:cntId")
    public TMnTrContent getContentByCntID(@Param("cntId") long cntId);

    @Query("SELECT new com.example.demo.dto.content.ContentResDTO(t, COUNT(DISTINCT c.cmtID), COUNT(DISTINCT r.rctId)) " +
            "FROM TMnTrContent t LEFT JOIN TMnTrComment c ON t.cntID = c.content.cntID LEFT JOIN TMnTrReact r ON t.cntID = r.content.cntID " +
            " WHERE (:cntId IS NULL OR (:cntId IS NOT NULL AND t.cntID = :cntId)) AND " +
            " (:userId IS NULL OR (:userId IS NOT NULL AND t.user.userId = :userId)) AND " +
            " (:type IS NULL OR (:type IS NOT NULL AND t.cntType = :type)) " +
            " GROUP BY t.cntID")
    public Page<ContentResDTO> findAllBy(@Param("cntId") Long cntId,
                                  @Param("userId") Long userId,
                                  @Param("type") String type,
                                  Pageable pageable);
}
