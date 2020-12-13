package com.github.training.error_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgument(){
        ErrorResponse errorResponse = new ErrorResponse("Hiba: Érvénytelen kérelem.");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}