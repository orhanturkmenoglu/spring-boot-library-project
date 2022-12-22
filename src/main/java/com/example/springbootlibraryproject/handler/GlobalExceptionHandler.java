package com.example.springbootlibraryproject.handler;

import com.example.springbootlibraryproject.exceptions.MemberErrorResponse;
import com.example.springbootlibraryproject.exceptions.MemberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MemberException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<MemberErrorResponse> handleException(MemberException memberException, HttpServletRequest servletRequest) {
        MemberErrorResponse exception = MemberErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .message(memberException.getMessage())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .path(servletRequest.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<MemberErrorResponse> handleException(Exception exception, HttpServletRequest servletRequest) {
        MemberErrorResponse errorException = MemberErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .path(servletRequest.getRequestURI())
                .build();
        return ResponseEntity.badRequest().body(errorException);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<MemberErrorResponse> handleException(MethodArgumentNotValidException methodArgumentNotValidException, HttpServletRequest servletRequest) {
        MemberErrorResponse errorException = MemberErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message(methodArgumentNotValidException.getMessage())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .path(servletRequest.getRequestURI())
                .build();
        return ResponseEntity.badRequest().body(errorException);
    }


}
