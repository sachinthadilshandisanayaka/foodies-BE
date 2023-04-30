package com.example.demo.function;

import org.springframework.stereotype.Component;

@Component
public class StringValidation {
    public boolean isNullOrEmpty(String val) {
        return val == null || val.isEmpty();
    }
}
