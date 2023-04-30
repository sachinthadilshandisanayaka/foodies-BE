package com.example.demo.serviceImpl;

import com.example.demo.dto.comment.CommentReqDTO;
import com.example.demo.dto.react.ReactReqDTO;
import com.example.demo.entity.TMnTrComment;
import com.example.demo.entity.TMnTrContent;
import com.example.demo.entity.TMnTrReact;
import com.example.demo.entity.TMnTrUser;
import com.example.demo.function.StringValidation;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.ContentRepository;
import com.example.demo.repository.ReactRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.CommentService;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class CommentServiceImpl implements CommentService {
    private final StringValidation stringValidation;
    private final CommentRepository commentRepository;
    private final UsersRepository usersRepository;
    private final ContentRepository contentRepository;

    CommentServiceImpl(StringValidation stringValidation,
                       CommentRepository commentRepository,
                       UsersRepository usersRepository,
                       ContentRepository contentRepository) {
        this.stringValidation = stringValidation;
        this.usersRepository = usersRepository;
        this.contentRepository = contentRepository;
        this.commentRepository = commentRepository;
    }

    /**
     * @param dto Comment data transfer object
     * @return comment ID
     * @apiNote save a comment for each content,
     * save by content id
     */
    @Override
    public long saveComment(CommentReqDTO dto) throws Exception {
        if (Objects.isNull(dto)) {
            throw new Exception("Invalid data");
        }
        if (Objects.isNull(dto.getContentId())) {
            throw new Exception("Content id is required");
        }
        if (Objects.isNull(dto.getUserID())) {
            throw new Exception("User id is required");
        }
        return persistEntity(mapDtoToEntity(dto)).getCmtID();
    }

    private TMnTrComment mapDtoToEntity(CommentReqDTO dto) throws Exception {
        TMnTrComment entity = new TMnTrComment();
        TMnTrUser user = usersRepository.getByUserID(dto.getUserID());
        if (Objects.isNull(user)) {
            throw new Exception("invalid user id :" + dto.getUserID());
        }
        TMnTrContent content = contentRepository.getContentByCntID(dto.getContentId());
        if (Objects.isNull(content)) {
            throw new Exception("Invalid content id :" + dto.getContentId());
        }
        entity.setUser(user);
        entity.setContent(content);
        entity.setCmtDescription(dto.getDescription());
        entity.setVersion((short) 1);
        entity.setCreateDate(new Date());
        return entity;
    }

    private TMnTrComment persistEntity(TMnTrComment entity) throws Exception {
        try {
            return commentRepository.saveAndFlush(entity);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            Logger.getLogger("Exception : ", e.toString());
            throw e;
        }
    }
}
