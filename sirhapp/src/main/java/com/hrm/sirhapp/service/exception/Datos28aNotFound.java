package com.hrm.sirhapp.service.exception;

import javax.ejb.ApplicationException;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@ApplicationException(rollback=true)
public class Datos28aNotFound extends Exception {

    /**
     *
     */
    public Datos28aNotFound () {
        this.message = "Los datos personales del aspirante no estan registrados";
    }
    
    /**
     *
     * @param message
     */
    public Datos28aNotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
