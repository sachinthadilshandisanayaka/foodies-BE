package com.example.demo.serviceImpl;

import com.example.demo.dto.content.ContentReqDTO;
import com.example.demo.dto.react.ReactReqDTO;
import com.example.demo.dto.react.ReactResDTO;
import com.example.demo.entity.TMnTrContent;
import com.example.demo.entity.TMnTrReact;
import com.example.demo.entity.TMnTrUser;
import com.example.demo.function.StringValidation;
import com.example.demo.repository.ContentRepository;
import com.example.demo.repository.ReactRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.ReactService;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class ReactServiceImpl implements ReactService {

    private final StringValidation stringValidation;
    private final ReactRepository reactRepository;
    private final UsersRepository usersRepository;
    private final ContentRepository contentRepository;

    ReactServiceImpl(StringValidation stringValidation,
                     ReactRepository reactRepository,
                     UsersRepository usersRepository,
                     ContentRepository contentRepository) {
        this.stringValidation = stringValidation;
        this.reactRepository = reactRepository;
        this.usersRepository = usersRepository;
        this.contentRepository = contentRepository;
    }

    @Override
    public List<ReactResDTO> getByContentId(String contentID) throws Exception {
        List<ReactResDTO> dtoList = new ArrayList<>();
        if (stringValidation.isNullOrEmpty(contentID)) {
            throw new Exception("Content id is required");
        }
        List<TMnTrReact> reactList = reactRepository.getContentByCntID(Long.parseLong(contentID));
        if (Objects.isNull(reactList) || reactList.isEmpty()) {
            return dtoList;
        }
        reactList.forEach(v -> {
            try {
                dtoList.add(mapEntityToDto(v));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return dtoList;
    }

    @Override
    public long saveReact(ReactReqDTO dto) throws Exception {
        if (Objects.isNull(dto)) {
            throw new Exception("Invalid data");
        }
        if (Objects.isNull(dto.getContentId())) {
            throw new Exception("Content id is required");
        }
        if (Objects.isNull(dto.getUserID())) {
            throw new Exception("User id is required");
        }
        TMnTrReact react = reactRepository.getContentByCntIDAndUserID(dto.getContentId(), dto.getUserID());
        if (Objects.nonNull(react)) {
            throw new Exception("Already react");
        }
        return persistEntity(mapDtoToEntity(dto)).getRctId();
    }

    @Override
    public String deleteReact(String contentId, String userId) throws Exception {
        if (stringValidation.isNullOrEmpty(contentId)) {
            throw new Exception("Content id is required");
        }
        if (Objects.isNull(userId)) {
            throw new Exception("User id is required");
        }
        TMnTrReact react = reactRepository.getContentByCntIDAndUserID(Long.parseLong(contentId), Long.parseLong(userId));
        if (Objects.isNull(react)) {
            throw new Exception("Invalid User id or Content id");
        }
        try {
            reactRepository.delete(react);
            return "Delete success";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private TMnTrReact mapDtoToEntity(ReactReqDTO dto) throws Exception {
        TMnTrReact entity = new TMnTrReact();
        TMnTrUser user = usersRepository.getByUserID(dto.getUserID());
        if (Objects.isNull(user)) {
            throw new Exception("invalid user id :" + dto.getUserID());
        }
        TMnTrContent content = contentRepository.getContentByCntID(dto.getContentId());
        if (Objects.isNull(content)) {
            throw new Exception("Invalid content id :" + dto.getContentId());
        }
        entity.setRctCount((short) 1);
        entity.setUser(user);
        entity.setContent(content);
        entity.setRctMode(dto.getModeCode());
        entity.setVersion((short) 1);
        entity.setCreateDate(new Date());
        return entity;
    }

    private ReactResDTO mapEntityToDto(TMnTrReact entity) throws Exception {
        ReactResDTO dto = new ReactResDTO();
        dto.setId(entity.getRctId());
        dto.setCount(entity.getRctCount());
        dto.setContentId(entity.getContent().getCntID());
        dto.setUserID(entity.getUser().getUserId());
        dto.setUserName(entity.getUser().getUserName());
        dto.setModeCode(entity.getRctMode());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }

    private TMnTrReact persistEntity(TMnTrReact entity) throws Exception {
        try {
            return reactRepository.saveAndFlush(entity);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            Logger.getLogger("Exception : ", e.toString());
            throw e;
        }
    }
}
