package com.workshop.pokemon.exception;

public class ReviewNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public ReviewNotFoundException(String message) {
        super(message);
    }
}
