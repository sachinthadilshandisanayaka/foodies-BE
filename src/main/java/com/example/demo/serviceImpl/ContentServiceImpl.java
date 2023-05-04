package com.example.demo.serviceImpl;

import com.example.demo.dto.content.ContentReqDTO;
import com.example.demo.dto.content.ContentResDTO;
import com.example.demo.entity.TMnTrContent;
import com.example.demo.entity.TMnTrUser;
import com.example.demo.function.StringValidation;
import com.example.demo.repository.ContentRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class ContentServiceImpl implements ContentService {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private final StringValidation stringValidation;
    private final ContentRepository contentRepository;
    private final UsersRepository usersRepository;

    @Autowired
    ContentServiceImpl(ContentRepository contentRepository,
                       StringValidation stringValidation,
                       UsersRepository usersRepository) {
        this.contentRepository = contentRepository;
        this.stringValidation = stringValidation;
        this.usersRepository = usersRepository;
    }

    @Override
    public List<ContentResDTO> paginatedSearch(Long contentID,
                                               Long userId,
                                               String type,
                                               int page,
                                               int size) throws Exception {
        if (page < 1) {
            throw new Exception("Page should be a value greater than 0");
        }
        if (size < 1) {
            throw new Exception("Size should be a value greater than 0");
        }
        type = stringValidation.isNullOrEmpty(type) ? null : type;
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ContentResDTO> resDTOPage = contentRepository.findAllBy(contentID, userId, type, pageable);
        return resDTOPage.toList();
    }

    @Override
    public long saveContent(ContentReqDTO dto) throws Exception {
        if (stringValidation.isNullOrEmpty(dto.getType())) {
            throw new Exception("Content type is required");
        }
        return persistEntity(mapDtoToEntity(dto)).getCntID();
    }

    @Override
    public long updateContent(ContentReqDTO dto, String contentID) throws Exception {
        if (stringValidation.isNullOrEmpty(dto.getType())) {
            throw new Exception("Content type is required");
        }
        if (stringValidation.isNullOrEmpty(contentID)) {
            throw new Exception("Content id is required");
        }
        TMnTrContent content = contentRepository.getContentByCntID(Long.parseLong(contentID));
        if (Objects.isNull(content)) {
            throw new Exception("Content id is invalid :" + contentID);
        }
        content.setCntLocation(stringValidation.isNullOrEmpty(dto.getLocation()) ? content.getCntLocation() : dto.getLocation());
        content.setCntEventTime(stringValidation.isNullOrEmpty(dto.getEventTime()) ? content.getCntEventTime() : formatter.parse(dto.getEventTime()));
        content.setCntDescription(stringValidation.isNullOrEmpty(dto.getDescription()) ? content.getCntDescription() : dto.getDescription());
        content.setCntType(stringValidation.isNullOrEmpty(dto.getType()) ? content.getCntType() : dto.getType());
        content.setModifyDate(new Date());
        return persistEntity(content).getCntID();
    }

    @Override
    public String deleteContent(String contentID) throws Exception {
        if (stringValidation.isNullOrEmpty(contentID)) {
            throw new Exception("Content id is required");
        }
        TMnTrContent content = contentRepository.getContentByCntID(Long.parseLong(contentID));
        if (Objects.isNull(content)) {
            throw new Exception("Content id is invalid :" + contentID);
        }
        try {
            contentRepository.delete(content);
            return "Delete success";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private TMnTrContent mapDtoToEntity(ContentReqDTO dto) throws ParseException {
        TMnTrContent entity = new TMnTrContent();
        TMnTrUser user = usersRepository.getByUserID(1);
        entity.setCntType(dto.getType());
        entity.setCntDescription(dto.getDescription());
        entity.setCntLocation(dto.getLocation());
        entity.setCntEventTime(stringValidation.isNullOrEmpty(dto.getEventTime()) ? null : formatter.parse(dto.getEventTime()));
        entity.setVersion((short) 1);
        entity.setUser(user);
        entity.setCreateDate(new Date());
        return entity;
    }

    private TMnTrContent persistEntity(TMnTrContent entity) throws Exception {
        try {
            return contentRepository.saveAndFlush(entity);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            Logger.getLogger("Exception : ", e.toString());
            throw e;
        }
    }

}
