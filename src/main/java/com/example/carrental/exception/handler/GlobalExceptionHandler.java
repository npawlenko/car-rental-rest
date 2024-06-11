package com.example.carrental.exception.handler;

import com.example.carrental.exception.AuthException;
import com.example.carrental.exception.NotFoundException;
import com.example.carrental.exception.ServerRuntimeException;
import com.example.carrental.model.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorMessage> handleUnauthorized(AuthException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler(ServerRuntimeException.class)
    public ResponseEntity<ErrorMessage> handleInternalServerError(ServerRuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(ex.getMessage()));
    }
}
