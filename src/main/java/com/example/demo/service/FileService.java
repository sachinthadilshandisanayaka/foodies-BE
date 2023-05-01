package com.example.demo.service;

import com.example.demo.dto.content.ImageReqDTO;
import com.example.demo.dto.content.ImageResDTO;

import java.util.List;

public interface FileService {
    List<ImageResDTO> getByContentId(String contentId) throws Exception;

    String uploadFiles(ImageReqDTO imageReqDTO) throws Exception;
}
