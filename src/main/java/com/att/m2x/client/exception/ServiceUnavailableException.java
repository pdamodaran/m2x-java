package com.att.m2x.client.exception;

public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException(Throwable cause) {
        super(cause);
    }

    public ServiceUnavailableException(String message) {
        super(message);
    }

    public ServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

}