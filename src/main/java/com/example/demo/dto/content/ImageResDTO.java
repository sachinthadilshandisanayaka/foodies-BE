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
public class ImageResDTO {
    private Long id;
    private Long contentId;
    private String fileName;
    private String fileType;
    private byte[] bytes;
    private Date createdDate;
}
