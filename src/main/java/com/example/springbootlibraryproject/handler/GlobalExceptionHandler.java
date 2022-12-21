package com.example.springbootlibraryproject.handler;

import com.example.springbootlibraryproject.exceptions.MemberErrorException;
import com.example.springbootlibraryproject.exceptions.MemberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MemberException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<MemberErrorException> handleException(MemberException memberException, HttpServletRequest servletRequest) {
        MemberErrorException exception = MemberErrorException.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDate.now())
                .message(memberException.getMessage())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .path(servletRequest.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<MemberErrorException> handleException(Exception exception, HttpServletRequest servletRequest) {
        MemberErrorException errorException = MemberErrorException.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDate.now())
                .message(exception.getMessage())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .path(servletRequest.getRequestURI())
                .build();
        return ResponseEntity.badRequest().body(errorException);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<MemberErrorException> handleException(MethodArgumentNotValidException methodArgumentNotValidException, HttpServletRequest servletRequest) {
        MemberErrorException errorException = MemberErrorException.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDate.now())
                .message(methodArgumentNotValidException.getMessage())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .path(servletRequest.getRequestURI())
                .build();
        return ResponseEntity.badRequest().body(errorException);
    }
}
