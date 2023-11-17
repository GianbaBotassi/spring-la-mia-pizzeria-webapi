package com.experis.exercise.springLaMiaPizzeriaCrud.exceptions;

public class UniqueNameIngrediente extends RuntimeException {
    public UniqueNameIngrediente(String message) {
        super(message);
    }
}
