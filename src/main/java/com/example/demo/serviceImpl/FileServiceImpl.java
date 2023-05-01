package com.example.demo.serviceImpl;

import com.example.demo.dto.content.ImageReqDTO;
import com.example.demo.dto.content.ImageResDTO;
import com.example.demo.entity.TMnTrContent;
import com.example.demo.entity.TMnTrDocument;
import com.example.demo.function.StringValidation;
import com.example.demo.repository.ContentRepository;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.service.FileService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class FileServiceImpl implements FileService {
    private final DocumentRepository documentRepository;
    private final ContentRepository contentRepository;
    private final StringValidation stringValidation;

    FileServiceImpl(DocumentRepository documentRepository,
                    ContentRepository contentRepository,
                    StringValidation stringValidation) {
        this.documentRepository = documentRepository;
        this.contentRepository = contentRepository;
        this.stringValidation = stringValidation;
    }

    @Override
    public List<ImageResDTO> getByContentId(String contentId) throws Exception {
        if (stringValidation.isNullOrEmpty(contentId)) {
            throw new Exception("Content id is required");
        }
        List<TMnTrDocument> documentList = documentRepository.getContentByCntID(Long.parseLong(contentId));
        if (Objects.isNull(documentList) || documentList.isEmpty()) {
            throw new Exception("Invalid document id");
        }
        return mapEntityToDto(documentList);
    }

    @Override
    public String uploadFiles(ImageReqDTO imageReqDTO) throws Exception {
        if (Objects.isNull(imageReqDTO)) {
            throw new Exception("Image upload object is required");
        }
        if (imageReqDTO.getContentID() == null) {
            throw new Exception("Content id is required");
        }
        if (imageReqDTO.getFiles() == null || imageReqDTO.getFiles().isEmpty()) {
            throw new Exception("No files to upload");
        }
        try {
            documentRepository.saveAll(mapDtoToEntity(imageReqDTO));
            return "Image upload success";
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    private List<TMnTrDocument> mapDtoToEntity(ImageReqDTO dto) throws Exception {
        List<TMnTrDocument> entityList = new ArrayList<>();
        TMnTrContent content = contentRepository.getContentByCntID(dto.getContentID());
        if (Objects.isNull(content)) {
            throw new Exception("Invalid content id :" + dto.getContentID());
        }
        if (Objects.isNull(dto.getFiles()) || dto.getFiles().isEmpty()) {
            throw new Exception("Files are required");
        }
        dto.getFiles().forEach(v -> {
            TMnTrDocument entity = new TMnTrDocument();
            entity.setContent(content);
            entity.setDocName(v.getOriginalFilename());
            entity.setDocType(Objects.requireNonNull(v.getOriginalFilename()).substring(v.getOriginalFilename().lastIndexOf(".") + 1));
            try {
                entity.setDocBytes(v.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            entity.setCreateDate(new Date());
            entityList.add(entity);
        });
        return entityList;
    }

    private List<ImageResDTO> mapEntityToDto(List<TMnTrDocument> entities) {
        List<ImageResDTO> dtoList = new ArrayList<>();
        if (Objects.nonNull(entities) && !entities.isEmpty()) {
            entities.forEach(v -> {
                ImageResDTO dto = new ImageResDTO();
                dto.setId(v.getDocID());
                dto.setContentId(v.getContent().getCntID());
                dto.setFileName(v.getDocName());
                dto.setFileType(v.getDocType());
                dto.setBytes(v.getDocBytes());
                dto.setCreatedDate(v.getCreateDate());
                dtoList.add(dto);
            });
        }
        return dtoList;
    }
}
