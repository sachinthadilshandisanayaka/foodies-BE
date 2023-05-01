package com.example.demo.serviceImpl;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.TMnTrUser;
import com.example.demo.function.StringValidation;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.UserService;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final StringValidation stringValidation;

    UserServiceImpl(UsersRepository usersRepository,
                    StringValidation stringValidation) {
        this.usersRepository = usersRepository;
        this.stringValidation = stringValidation;
    }

    @Override
    public UserDTO register(UserDTO dto) throws Exception {
        if (stringValidation.isNullOrEmpty(dto.getUserName())) {
            throw new Exception("User name is required");
        }
        if (stringValidation.isNullOrEmpty(dto.getPassword())) {
            throw new Exception("User password is required");
        }
        TMnTrUser user = usersRepository.getByUserName(dto.getUserName());
        if (Objects.nonNull(user)) {
            throw new Exception("Already registers, user name: " + dto.getUserName());
        }
        if (!stringValidation.isNullOrEmpty(dto.getEmail())) {
            TMnTrUser user2 = usersRepository.getByUserEmail(dto.getEmail());
            if (Objects.nonNull(user2)) {
                throw new Exception("Already registers, email: " + dto.getEmail());
            }
        }
        return mapDtoToEntity(persistEntity(mapDtoToEntity(dto)));
    }

    @Override
    public UserDTO login(UserDTO dto) throws Exception {
        if (stringValidation.isNullOrEmpty(dto.getUserName())) {
            throw new Exception("User name is required");
        }
        if (stringValidation.isNullOrEmpty(dto.getPassword())) {
            throw new Exception("User password is required");
        }
        TMnTrUser user = usersRepository.getByUserNameAndPassword(dto.getUserName(), dto.getPassword());
        if (Objects.isNull(user)) {
            throw new Exception("Invalid credentials");
        }
        return mapDtoToEntity(user);
    }

    private TMnTrUser persistEntity(TMnTrUser entity) throws Exception {
        try {
            return usersRepository.saveAndFlush(entity);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            Logger.getLogger("Exception : ", e.toString());
            throw e;
        }
    }


    public TMnTrUser mapDtoToEntity(UserDTO dto) {
        TMnTrUser entity = new TMnTrUser();
        entity.setUserName(dto.getUserName());
        entity.setUserEmail(dto.getEmail());
        entity.setUserPassword(dto.getPassword());
        entity.setCreatedDate(new Date());
        return entity;
    }

    public UserDTO mapDtoToEntity(TMnTrUser entity) {
        UserDTO dto = new UserDTO();
        dto.setUserId(entity.getUserId());
        dto.setUserName(entity.getUserName());
        dto.setEmail(entity.getUserEmail());
        return dto;
    }
}
