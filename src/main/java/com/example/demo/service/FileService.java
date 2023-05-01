package com.example.demo.service;

import com.example.demo.dto.content.ImageReqDTO;

public interface FileService {
    String uploadFiles(ImageReqDTO imageReqDTO) throws Exception;
}
