package com.example.demo.controller;

import com.example.demo.dto.react.ReactReqDTO;
import com.example.demo.service.ContentService;
import com.example.demo.service.ReactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mn/rct")
public class ReactController {
    private ObjectMapper mapper = new ObjectMapper();
    private final ReactService reactService;

    @Autowired
    ReactController(ReactService reactService) {
        this.reactService = reactService;
    }

    @PostMapping(path = "/save")
    public long saveReact(@RequestBody String dtoToJsonString) throws Exception {
        ReactReqDTO dto = mapper.readValue(dtoToJsonString, ReactReqDTO.class);
        return reactService.saveReact(dto);
    }
}
