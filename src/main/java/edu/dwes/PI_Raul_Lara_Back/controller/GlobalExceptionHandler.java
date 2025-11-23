package edu.dwes.PI_Raul_Lara_Back.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;

import java.time.LocalDateTime;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NonExistentException.class)
    public ResponseEntity<?> handleNotFound(NonExistentException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
            "error", "Resource not found",
            "message", ex.getMessage(),
            "timestamp", LocalDateTime.now().toString(),
            "status", 404
        ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleBadRequest(IllegalArgumentException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
            "error", "Bad request",
            "message", ex.getMessage(),
            "timestamp", LocalDateTime.now().toString(),
            "status", 400
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleOther(Exception ex){
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
            "error", "Internal error",
            "message", ex.getMessage(),
            "timestamp", LocalDateTime.now().toString(),
            "status", 500
        ));
    }
}