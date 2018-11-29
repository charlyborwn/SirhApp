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
public class Traba36NotFound extends Exception {

    /**
     *
     */
    public Traba36NotFound () {
        this.message = "El trabajador no esta registrado";
    }
    
    /**
     *
     * @param message
     */
    public Traba36NotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
