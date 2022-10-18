package com.example.onlineshop.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    private final String message;

    public ResourceNotFoundException(final String message) {
        super(message);
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
