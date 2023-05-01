package com.example.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "\"T_MN_TR_DOCUMENT\"", schema = "\"FOODY_MN\"")
public class TMnTrDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "document_id_sequence")
    @SequenceGenerator(name = "document_id_sequence", sequenceName = "DOCUMENT_ID_SEQ", allocationSize = 1)
    @Column(name = "\"DOC_ID\"", nullable = false)
    private long docID;

    @JoinColumn(name = "\"DOC_CNT_ID\"", nullable = false)
    @ManyToOne(optional = false)
    private TMnTrContent content;

    @Column(name = "\"DOC_NAME\"")
    private String docName;

    @Column(name = "\"DOC_TYPE\"", nullable = false)
    private String docType;

    @Column(name = "\"DOC_BYTES\"", nullable = false)
    private byte[] docBytes;

    @Version
    @Column(name = "\"VERSION\"", nullable = false)
    private int version;

    @Column(name = "\"CREATED_DATE\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public TMnTrDocument(long docID, TMnTrContent content, String docName, String docType, byte[] docBytes, int version, Date createDate) {
        this.docID = docID;
        this.content = content;
        this.docName = docName;
        this.docType = docType;
        this.docBytes = docBytes;
        this.version = version;
        this.createDate = createDate;
    }

    public TMnTrDocument() {
    }

    public long getDocID() {
        return docID;
    }

    public void setDocID(long docID) {
        this.docID = docID;
    }

    public TMnTrContent getContent() {
        return content;
    }

    public void setContent(TMnTrContent content) {
        this.content = content;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public byte[] getDocBytes() {
        return docBytes;
    }

    public void setDocBytes(byte[] docBytes) {
        this.docBytes = docBytes;
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
}
