package com.github.training.error_handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ErrorHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgument() {
        ErrorResponse errorResponse = new ErrorResponse("Hiba: Érvénytelen kérelem.");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestException.class)
    public ResponseEntity<ErrorResponse> restException(RestException error) {
        HttpStatus errorCode = error.getErrorCode();
        String errorMessage = error.getErrorMessage();
        ErrorResponse errorResponse = new ErrorResponse(errorMessage);
        //log.error("Error: {}" , error.getMessage());
        log.error("Error: ", error);
        return new ResponseEntity<>(errorResponse, errorCode);
    }
}