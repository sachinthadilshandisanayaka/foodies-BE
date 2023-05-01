package com.example.demo.service;

import com.example.demo.dto.comment.CommentReqDTO;
import com.example.demo.dto.comment.CommentResDTO;

import java.util.List;

public interface CommentService {
    long saveComment(CommentReqDTO dto) throws Exception;

    List<CommentResDTO> getComment(String contentID) throws Exception;

    String updateComment(String commentID, CommentReqDTO dto) throws Exception;

    String deleteComment(String contentId, String userId) throws Exception;
}
