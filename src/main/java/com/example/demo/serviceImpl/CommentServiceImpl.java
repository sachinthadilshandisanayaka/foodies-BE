package com.example.demo.serviceImpl;

import com.example.demo.dto.comment.CommentReqDTO;
import com.example.demo.dto.comment.CommentResDTO;
import com.example.demo.dto.react.ReactReqDTO;
import com.example.demo.dto.react.ReactResDTO;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @Override
    public List<CommentResDTO> getComment(String contentID) throws Exception {
        List<CommentResDTO> dtoList = new ArrayList<>();
        if (Objects.isNull(contentID)) {
            throw new Exception("Content id is required");
        }
        List<TMnTrComment> comments = commentRepository.getCommentByCntID(Long.parseLong(contentID));
        if (Objects.isNull(comments) || comments.isEmpty()) {
            return dtoList;
        }
        comments.forEach(v -> {
            try {
                dtoList.add(mapEntityToDto(v));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return dtoList;
    }

    @Override
    public String updateComment(String commentID, CommentReqDTO dto) throws Exception {
        List<CommentResDTO> dtoList = new ArrayList<>();
        if (Objects.isNull(commentID)) {
            throw new Exception("Comment id is required");
        }
        TMnTrComment comment = commentRepository.getCommentByID(Long.parseLong(commentID));
        if (Objects.isNull(comment)) {
            throw new Exception("Invalid comment id :" + commentID);
        }
        comment.setCmtDescription(dto.getDescription());
        persistEntity(comment);
        return "Update success";
    }

    @Override
    public String deleteComment(String contentId, String userId) throws Exception {
        if (stringValidation.isNullOrEmpty(contentId)) {
            throw new Exception("Content id is required");
        }
        if (Objects.isNull(userId)) {
            throw new Exception("User id is required");
        }
        TMnTrComment comment = commentRepository.getCommentByCntIDAndUserID(Long.parseLong(contentId), Long.parseLong(userId));
        if (Objects.isNull(comment)) {
            throw new Exception("Invalid User id or Content id");
        }
        try {
            commentRepository.delete(comment);
            return "Delete success";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private CommentResDTO mapEntityToDto(TMnTrComment entity) throws Exception {
        CommentResDTO dto = new CommentResDTO();
        dto.setId(entity.getCmtID());
        dto.setContentId(entity.getContent().getCntID());
        dto.setUserID(entity.getUser().getUserId());
        dto.setUserName(entity.getUser().getUserName());
        dto.setDescription(entity.getCmtDescription());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
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
