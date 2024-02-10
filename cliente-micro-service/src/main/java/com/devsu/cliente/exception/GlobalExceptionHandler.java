package com.devsu.cliente.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConflict(RuntimeException ex, WebRequest request) {
        return new ErrorResponse("Error", ex.getMessage());
    }
}