package com.example.demo.service;

import com.example.demo.dto.content.ContentReqDTO;
import com.example.demo.dto.content.ContentResDTO;

import java.util.List;

public interface ContentService {
    List<ContentResDTO> paginatedSearch(Long contentID,
                                        Long userId,
                                        int page,
                                        int size) throws Exception;

    long saveContent(ContentReqDTO dto) throws Exception;
}
