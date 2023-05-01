package com.example.demo.repository;

import com.example.demo.entity.TMnTrUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<TMnTrUser, String> {

    @Query("SELECT t FROM TMnTrUser t WHERE t.userId = :userId")
    TMnTrUser getByUserID(@Param("userId") long cntId);

    @Query("SELECT t FROM TMnTrUser t WHERE t.userName = :userName")
    TMnTrUser getByUserName(@Param("userName") String userName);

    @Query("SELECT t FROM TMnTrUser t WHERE t.userName = :userName AND t.userPassword = :userPassword")
    TMnTrUser getByUserNameAndPassword(@Param("userName") String userName,
                                       @Param("userPassword") String userPassword);

    @Query("SELECT t FROM TMnTrUser t WHERE t.userEmail = :userEmail")
    TMnTrUser getByUserEmail(@Param("userEmail") String userEmail);
}
