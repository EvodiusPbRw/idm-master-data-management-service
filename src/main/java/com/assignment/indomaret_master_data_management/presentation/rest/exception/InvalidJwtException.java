package com.assignment.indomaret_master_data_management.presentation.rest.exception;

public class InvalidJwtException extends RuntimeException {

    public InvalidJwtException(String message) {
        super(message);
    }

    public InvalidJwtException(String message, Throwable cause) {
        super(message, cause);
    }
}