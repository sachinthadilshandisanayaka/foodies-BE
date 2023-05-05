package com.example.demo.service;

import com.example.demo.dto.content.ContentReqDTO;
import com.example.demo.dto.content.ContentResDTO;

import java.util.List;

public interface ContentService {
    List<ContentResDTO> paginatedSearch(Long contentID,
                                        Long userId,
                                        Long loginUserId,
                                        String type,
                                        int page,
                                        int size) throws Exception;

    long saveContent(ContentReqDTO dto) throws Exception;

    long updateContent(ContentReqDTO dto, String contentID) throws Exception;

    String deleteContent(String contentID) throws Exception;
}
