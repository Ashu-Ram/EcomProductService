package com.scaler.productservice.exception;

public class InvalidTitleException extends RuntimeException {

    public InvalidTitleException() {}

    public InvalidTitleException(String message) {
        super(message);
    }
}
