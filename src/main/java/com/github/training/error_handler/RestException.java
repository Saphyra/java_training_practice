package com.github.training.error_handler;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RestException extends RuntimeException{
    private final String errorMessage;
    private final HttpStatus errorCode;

    public RestException(String errorMessage,HttpStatus errorCode, String logMessage){
        super(logMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
