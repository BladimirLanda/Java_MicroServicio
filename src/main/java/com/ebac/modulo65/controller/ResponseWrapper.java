package com.ebac.modulo65.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

//@Getter-@RequiredArgsConstructor: Lombok Annotation
@Getter
@RequiredArgsConstructor
public class ResponseWrapper<T> {

    private final boolean success;
    private final String message;
    private final ResponseEntity<T> responseEntity;

}
