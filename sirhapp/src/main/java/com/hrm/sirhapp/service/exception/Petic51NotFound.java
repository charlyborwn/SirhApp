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
public class Petic51NotFound extends Exception {

    /**
     *
     */
    public Petic51NotFound () {
        this.message = "Solicitud no encontrada";
    }
    
    /**
     *
     * @param message
     */
    public Petic51NotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
