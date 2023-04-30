package com.example.demo.dto.react;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReactReqDTO {
    private Long id;
    private int count;
    private Long contentId;
    private Long userID;
    private String modeCode;
    private int version;
    private Date createDate;
}
