package com.att.m2x.client.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(Throwable cause) {
        super(cause);
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

}
