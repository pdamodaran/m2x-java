package com.att.m2x.client.exception;

public class NotFoundException extends ClientException {

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
