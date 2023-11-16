package com.denzhn.usercars.exception;

public class BusinessLayerException extends RuntimeException{
    public BusinessLayerException(String message, Throwable cause) {
        super(message, cause);
    }
}
