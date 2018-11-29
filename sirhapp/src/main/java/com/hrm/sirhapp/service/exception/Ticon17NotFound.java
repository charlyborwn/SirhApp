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
public class Ticon17NotFound extends Exception {

    /**
     *
     */
    public Ticon17NotFound () {
        this.message = "Sin registros.";
    }
    
    /**
     *
     * @param message
     */
    public Ticon17NotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
