package com.example.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "\"T_MN_TR_COMMENT\"", schema = "\"FOODY_MN\"")
public class TMnTrComment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_sequence")
    @SequenceGenerator(name = "comment_id_sequence", sequenceName = "COMMENT_ID_SEQ", allocationSize = 1)
    @Column(name = "\"CMT_ID\"", nullable = false)
    private long cmtID;

    @JoinColumn(name = "\"CMT_CNT_ID\"", nullable = false)
    @ManyToOne(optional = false)
    private TMnTrContent content;

    @JoinColumn(name = "\"CMT_USER_ID\"", nullable = false)
    @ManyToOne(optional = false)
    private TMnTrUser user;

    @Column(name = "\"CMT_DESCRIPTION\"")
    private String cmtDescription;

    @Version
    @Column(name = "\"VERSION\"", nullable = false)
    private int version;

    @Column(name = "\"CREATED_DATE\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public TMnTrComment() {
    }

    public TMnTrComment(long cmtID, TMnTrContent content, TMnTrUser user, String cmtDescription, int version) {
        this.cmtID = cmtID;
        this.content = content;
        this.user = user;
        this.cmtDescription = cmtDescription;
        this.version = version;
    }

    public long getCmtID() {
        return cmtID;
    }

    public void setCmtID(long cmtID) {
        this.cmtID = cmtID;
    }

    public TMnTrContent getContent() {
        return content;
    }

    public void setContent(TMnTrContent content) {
        this.content = content;
    }

    public TMnTrUser getUser() {
        return user;
    }

    public void setUser(TMnTrUser user) {
        this.user = user;
    }

    public String getCmtDescription() {
        return cmtDescription;
    }

    public void setCmtDescription(String cmtDescription) {
        this.cmtDescription = cmtDescription;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
