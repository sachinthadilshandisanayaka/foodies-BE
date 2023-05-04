package com.example.demo.dto.content;

import com.example.demo.entity.TMnTrContent;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentResDTO {
    private long id;
    private long userId;
    private String type;
    private String description;
    private int version;
    private String location;
    private Date eventTime;
    private Date createdDate;
    private Date modifyDate;
    private int commentCount;
    private int reactCount;

    public ContentResDTO(TMnTrContent content, long commentCount, long reactCount) {
        this.id = content.getCntID();
        this.userId = content.getUser().getUserId();
        this.type = content.getCntType();
        this.location = content.getCntLocation();
        this.eventTime = content.getCntEventTime();
        this.description = content.getCntDescription();
        this.version = content.getVersion();
        this.createdDate = content.getCreateDate();
        this.modifyDate = content.getModifyDate();
        this.commentCount = (int) commentCount;
        this.reactCount = (int) reactCount;
    }

}
