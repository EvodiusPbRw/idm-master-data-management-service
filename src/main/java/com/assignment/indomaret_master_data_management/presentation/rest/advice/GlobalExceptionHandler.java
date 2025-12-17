package com.assignment.indomaret_master_data_management.presentation.rest.advice;

import com.assignment.indomaret_master_data_management.presentation.rest.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleApiException(CustomException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());

        if (ex.getData() != null) {
            body.put("data", ex.getData());
        }

        return ResponseEntity
                .status(ex.getStatus())
                .body(body);
    }
}
