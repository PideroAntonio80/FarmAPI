package com.sanvalero.practicadosspringmayo.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */
public class AnimalNotFoundException extends RuntimeException {

    public AnimalNotFoundException() {
        super();
    }

    public AnimalNotFoundException(String message){
        super(message);
    }

    public AnimalNotFoundException(long id){
        super("Animal not found: " + id);
    }
}
