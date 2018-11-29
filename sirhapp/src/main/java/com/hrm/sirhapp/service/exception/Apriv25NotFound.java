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
public class Apriv25NotFound extends Exception {

    /**
     *
     */
    public Apriv25NotFound () {
        this.message = "Sin Avisos";
    }
    
    /**
     *
     * @param message
     */
    public Apriv25NotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
