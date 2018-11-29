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
public class Tidoc18NotFound extends Exception {

    /**
     *
     */
    public Tidoc18NotFound () {
        this.message = "Sin registros.";
    }
    
    /**
     *
     * @param message
     */
    public Tidoc18NotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
