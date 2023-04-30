package com.example.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "\"T_MN_TR_REACT\"", schema = "\"FOODY_MN\"")
public class TMnTrReact {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rct_id_sequence")
    @SequenceGenerator(name = "rct_id_sequence", sequenceName = "RCT_ID_SEQ", allocationSize = 1)
    @Column(name = "\"RCT_ID\"", nullable = false)
    private long rctId;

    @Column(name = "\"RCT_COUNT\"", nullable = false)
    private int rctCount;

    @JoinColumn(name = "\"RCT_CNT_ID\"", nullable = false)
    @ManyToOne(optional = false)
    private TMnTrContent content;

    @JoinColumn(name = "\"RCT_USER_ID\"", nullable = false)
    @ManyToOne(optional = false)
    private TMnTrUser user;

    @Column(name = "\"RCT_MODE\"")
    private String rctMode;

    @Version
    @Column(name = "\"VERSION\"", nullable = false)
    private int version;

    @Column(name = "\"CREATED_DATE\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public TMnTrReact() {
    }

    public TMnTrReact(long rctId, int rctCount, TMnTrContent content, TMnTrUser user, String rctMode, int version) {
        this.rctId = rctId;
        this.rctCount = rctCount;
        this.content = content;
        this.user = user;
        this.rctMode = rctMode;
        this.version = version;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public long getRctId() {
        return rctId;
    }

    public void setRctId(long rctId) {
        this.rctId = rctId;
    }

    public int getRctCount() {
        return rctCount;
    }

    public void setRctCount(int rctCount) {
        this.rctCount = rctCount;
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

    public String getRctMode() {
        return rctMode;
    }

    public void setRctMode(String rctMode) {
        this.rctMode = rctMode;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
