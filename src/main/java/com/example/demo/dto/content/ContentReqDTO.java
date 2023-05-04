package com.example.demo.dto.content;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContentReqDTO {
    private Long id;
    private Long userId;
    private String type;
    private String description;
    private String location;
    private String eventTime;
    private int version;
    private Date createdDate;
    private Date modifyDate;
}
