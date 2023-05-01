package com.example.demo.service;

import com.example.demo.dto.UserDTO;

public interface UserService {
    UserDTO register(UserDTO dto) throws Exception;

    UserDTO login(UserDTO dto) throws Exception;
}
