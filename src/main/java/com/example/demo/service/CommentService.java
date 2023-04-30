package com.example.demo.service;

import com.example.demo.dto.comment.CommentReqDTO;

public interface CommentService {
    long saveComment(CommentReqDTO dto) throws Exception;
}
