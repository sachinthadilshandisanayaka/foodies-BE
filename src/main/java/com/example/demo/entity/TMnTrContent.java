package com.example.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "\"T_MN_TR_CONTENT\"", schema = "\"FOODY_MN\"")
public class TMnTrContent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "content_id_sequence")
    @SequenceGenerator(name = "content_id_sequence", sequenceName = "CONTENT_ID_SEQ", allocationSize = 1)
    @Column(name = "\"CNT_ID\"", nullable = false)
    private long cntID;

    @Column(name = "\"CNT_TYPE\"", nullable = false)
    private String cntType;

    @JoinColumn(name = "\"CNT_USER_ID\"", nullable = false)
    @ManyToOne(optional = false)
    private TMnTrUser user;

    @Column(name = "\"CNT_DESCRIPTION\"")
    private String cntDescription;

    @Column(name = "\"CNT_LOCATION\"")
    private String cntLocation;

    @Column(name = "\"CNT_EVENT_TIME\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cntEventTime;

    @Version
    @Column(name = "\"VERSION\"", nullable = false)
    private int version;

    @Column(name = "\"CREATED_DATE\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "\"MODIFY_DATE\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    public String getCntLocation() {
        return cntLocation;
    }

    public void setCntLocation(String cntLocation) {
        this.cntLocation = cntLocation;
    }

    public Date getCntEventTime() {
        return cntEventTime;
    }

    public void setCntEventTime(Date cntEventTime) {
        this.cntEventTime = cntEventTime;
    }

    public long getCntID() {
        return cntID;
    }

    public void setCntID(long cntID) {
        this.cntID = cntID;
    }

    public String getCntType() {
        return cntType;
    }

    public void setCntType(String cntType) {
        this.cntType = cntType;
    }

    public TMnTrUser getUser() {
        return user;
    }

    public void setUser(TMnTrUser user) {
        this.user = user;
    }

    public String getCntDescription() {
        return cntDescription;
    }

    public void setCntDescription(String cntDescription) {
        this.cntDescription = cntDescription;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public TMnTrContent(long cntID, String cntType, String cntDescription, int version, Date createDate, Date modifyDate) {
        this.cntID = cntID;
        this.cntType = cntType;
        this.cntDescription = cntDescription;
        this.version = version;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public TMnTrContent() {
    }
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        TMnTrContent that = (TMnTrContent) o;
//        return cntID != null && Objects.equals(cntID, that.cntID);
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}
