package com.example.demo.serviceImpl;

import com.example.demo.dto.content.ContentReqDTO;
import com.example.demo.dto.react.ReactReqDTO;
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

import java.util.Date;
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
        return persistEntity(mapDtoToEntity(dto)).getRctId();
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
