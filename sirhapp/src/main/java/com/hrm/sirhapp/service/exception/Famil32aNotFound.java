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
public class Famil32aNotFound extends Exception {

    /**
     *
     */
    public Famil32aNotFound () {
        this.message = "Sin datos de familia";
    }
    
    /**
     *
     * @param message
     */
    public Famil32aNotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
