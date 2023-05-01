package com.example.demo.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResDTO {
    private Long id;
    private Long contentId;
    private Long userID;
    private String userName;
    private String description;
    private int version;
    private Date createDate;
}
