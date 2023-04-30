package com.example.demo.service;

import com.example.demo.dto.react.ReactReqDTO;

public interface ReactService {
    long saveReact(ReactReqDTO dto) throws Exception;
}
