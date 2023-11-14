package com.experis.exercise.springLaMiaPizzeriaCrud.exceptions;

public class NotFoundPizzaException extends RuntimeException {
    public NotFoundPizzaException(String message) {
        super(message);
    }
}
