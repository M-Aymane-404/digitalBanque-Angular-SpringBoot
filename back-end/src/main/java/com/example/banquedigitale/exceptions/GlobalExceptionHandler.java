package com.example.banquedigitale.exceptions;

import com.example.banquedigitale.DTO.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<?> handleClientNotFound(ClientNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError("CLIENT_NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<?> handleAccountNotFound(AccountNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError("ACCOUNT_NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(SoldNotsufficientException.class)
    public ResponseEntity<?> handleInsufficientBalance(SoldNotsufficientException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError("INSUFFICIENT_BALANCE", ex.getMessage()));
    }

    @ExceptionHandler(AccountNotValidException.class)
    public ResponseEntity<?> handleInvalidAccount(AccountNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError("ACCOUNT_INVALID", ex.getMessage()));
    }
}
