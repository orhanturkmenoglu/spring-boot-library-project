package com.example.springbootlibraryproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ErrorResponse {

    public static <T> ResponseEntity<T> responseBuilder(String message, HttpStatus httpStatus, T data) {
        return getResponseBuilder(message, httpStatus, data);
    }

    public static <T> ResponseEntity<T> responseBuilder(String message, HttpStatus httpStatus) {
        return getResponseBuilder(message, httpStatus, null);
    }

    private static <T> ResponseEntity<T> getResponseBuilder(String message, HttpStatus httpStatus, T data) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("message", message);
        response.put("statusCode", httpStatus.value());
        response.put("statusInfo", httpStatus.getReasonPhrase());
        response.put("data", data);

        return new ResponseEntity(response, httpStatus);
    }
}
