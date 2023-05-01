package com.example.demo.controller;

import com.example.demo.dto.content.ContentReqDTO;
import com.example.demo.dto.content.ImageReqDTO;
import com.example.demo.dto.content.ImageResDTO;
import com.example.demo.service.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/mn/file")
public class FileController {

    private final FileService fileService;

    FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(path = "/content/{contentId}/save")
    public ResponseEntity<String> saveDocument(@PathVariable String contentId,
                                               @RequestParam List<MultipartFile> files) throws Exception {
        ImageReqDTO dto = new ImageReqDTO(Long.parseLong(contentId), files);
        return ResponseEntity.ok().body(fileService.uploadFiles(dto));
    }

    @GetMapping(path = "/content/{contentId}/get")
    public ResponseEntity<List<ImageResDTO>> getDocument(@PathVariable String contentId) throws Exception {
        return ResponseEntity.ok().body(fileService.getByContentId(contentId));
    }
}
