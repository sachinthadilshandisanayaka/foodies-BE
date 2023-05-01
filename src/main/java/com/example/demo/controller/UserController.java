package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mn/user")
public class UserController {
    private ObjectMapper mapper = new ObjectMapper();

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody String dtoToJsonBody) throws Exception {
        UserDTO dto = mapper.readValue(dtoToJsonBody, UserDTO.class);
        return ResponseEntity.ok().body(userService.register(dto));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<UserDTO> saveReact(@RequestBody String dtoToJsonBody) throws Exception {
        UserDTO dto = mapper.readValue(dtoToJsonBody, UserDTO.class);
        return ResponseEntity.ok().body(userService.login(dto));
    }
}
