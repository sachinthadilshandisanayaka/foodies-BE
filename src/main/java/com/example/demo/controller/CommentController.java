package com.example.demo.controller;


import com.example.demo.dto.comment.CommentReqDTO;
import com.example.demo.dto.react.ReactReqDTO;
import com.example.demo.service.CommentService;
import com.example.demo.service.ReactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mn/cmt")
public class CommentController {
    private ObjectMapper mapper = new ObjectMapper();
    private final CommentService commentService;

    @Autowired
    CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(path = "/save")
    public long saveComment(@RequestBody String dtoToJsonString) throws Exception {
        CommentReqDTO dto = mapper.readValue(dtoToJsonString, CommentReqDTO.class);
        return commentService.saveComment(dto);
    }
}
