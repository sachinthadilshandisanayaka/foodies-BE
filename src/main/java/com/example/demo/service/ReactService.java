package com.example.demo.service;

import com.example.demo.dto.react.ReactReqDTO;
import com.example.demo.dto.react.ReactResDTO;

import java.util.List;

public interface ReactService {
    List<ReactResDTO> getByContentId(String contentID) throws Exception;

    long saveReact(ReactReqDTO dto) throws Exception;

    String deleteReact(String contentId, String userId) throws Exception;
}
