package com.sanvalero.practicadosspringmayo.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

public class FarmNotFoundException extends RuntimeException {

    public FarmNotFoundException() {
        super();
    }

    public FarmNotFoundException(String message){
        super(message);
    }

    public FarmNotFoundException(long id){
        super("Farm not found: " + id);
    }
}
