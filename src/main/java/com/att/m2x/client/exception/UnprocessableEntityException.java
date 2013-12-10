package com.att.m2x.client.exception;

import java.util.Map;


public class UnprocessableEntityException extends RuntimeException {

    private Map<String, String> errors;


    public UnprocessableEntityException(Throwable cause) {
        super(cause);
    }

    public UnprocessableEntityException(String message) {
        super(message);
    }

    public UnprocessableEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public Map<String, String> getErrors() {
        return errors;
    }

}