package com.example.orderservice.common.exceptions;

public class BadRequestException extends RuntimeException {
    private String message;

    public BadRequestException(String message) {
        super(message);
        this.message = message;
    }
}
