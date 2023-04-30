package com.example.demo.controller;

import com.example.demo.dto.react.ReactReqDTO;
import com.example.demo.dto.react.ReactResDTO;
import com.example.demo.service.ReactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mn/rct")
public class ReactController {
    private ObjectMapper mapper = new ObjectMapper();
    private final ReactService reactService;

    @Autowired
    ReactController(ReactService reactService) {
        this.reactService = reactService;
    }

    @GetMapping(path = "/{contentID}")
    public List<ReactResDTO> getContent(@PathVariable String contentID) throws Exception {
        return reactService.getByContentId(contentID);
    }

    @PostMapping(path = "/save")
    public long saveReact(@RequestBody String dtoToJsonString) throws Exception {
        ReactReqDTO dto = mapper.readValue(dtoToJsonString, ReactReqDTO.class);
        return reactService.saveReact(dto);
    }

    @DeleteMapping(path = "/{contentId}/user/{userID}/delete")
    public String deleteReact(@PathVariable String contentId, @PathVariable String userID) throws Exception {
        return reactService.deleteReact(contentId, userID);
    }

}
