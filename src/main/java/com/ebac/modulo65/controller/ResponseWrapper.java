package com.ebac.modulo65.controller;

import lombok.Getter;
import org.springframework.http.ResponseEntity;

/*
@Getter: Notación Lombok de generación de propiedades a clases Java
Infiere los Getters y generando el método toString() por default.
 */
@Getter
public class ResponseWrapper<T> {

    private final boolean success;
    private final String message;
    private final ResponseEntity<T> responseEntity;

    public ResponseWrapper(boolean success, String message, ResponseEntity<T> responseEntity) {
        this.success = success;
        this.message = message;
        this.responseEntity = responseEntity;
    }

}
