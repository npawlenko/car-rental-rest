package com.example.carrental.exception;

public class AuthException extends ClientRuntimeException {
    public AuthException(String message) {
        super(message);
    }
}
