package com.example.demo.controller;

import com.example.demo.dto.comment.CommentReqDTO;
import com.example.demo.dto.comment.CommentResDTO;
import com.example.demo.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(path = "/{contentID}")
    public ResponseEntity<List<CommentResDTO>> getContent(@PathVariable String contentID) throws Exception {
        return ResponseEntity.ok().body(commentService.getComment(contentID));
    }

    @PutMapping(path = "/{contentID}/update")
    public ResponseEntity<String> updateComment(@PathVariable String contentID, @RequestBody String dtoToJsonString) throws Exception {
        CommentReqDTO dto = mapper.readValue(dtoToJsonString, CommentReqDTO.class);
        return ResponseEntity.ok().body(commentService.updateComment(contentID, dto));
    }

    @DeleteMapping(path = "/{contentId}/user/{userID}/delete")
    public ResponseEntity<String> deleteReact(@PathVariable String contentId, @PathVariable String userID) throws Exception {
        return ResponseEntity.ok().body(commentService.deleteComment(contentId, userID));
    }
}
