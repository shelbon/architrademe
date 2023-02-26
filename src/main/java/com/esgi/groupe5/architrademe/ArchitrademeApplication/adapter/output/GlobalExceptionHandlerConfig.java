package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerConfig {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        map.put("message", ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.FORBIDDEN);
    }
}
