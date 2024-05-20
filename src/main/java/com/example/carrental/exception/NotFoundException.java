package com.example.carrental.exception;

public class NotFoundException extends ClientRuntimeException {
    public NotFoundException() {
        super("Object not found");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
