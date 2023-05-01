package com.example.demo.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "\"T_MN_TR_USER\"", schema = "\"FOODY_MN\"")
public class TMnTrUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
    @SequenceGenerator(name = "user_id_sequence", sequenceName = "USER_ID_SEQ", allocationSize = 1)
    @Column(name = "\"USER_ID\"", nullable = false)
    private long userId;

    @NotEmpty(message = "User name is required")
    @Column(name = "\"USER_NAME\"", nullable = false)
    private String userName;

    @NotEmpty(message = "User password is required")
    @Column(name = "\"USER_PASSWORD\"", nullable = false)
    private String userPassword;

    @Column(name = "\"USER_EMAIL\"")
    private String userEmail;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "\"CREATED_DATE\"", nullable = false, updatable = false)
    @CreatedDate
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "\"MODIFY_DATE\"")
    @LastModifiedDate
    private Date modifyDate;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
