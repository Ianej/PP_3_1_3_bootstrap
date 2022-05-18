package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<String> globalExceptionHandler(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
