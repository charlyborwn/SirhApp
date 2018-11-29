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
public class Sanci35NotFound extends Exception {

    /**
     *
     */
    public Sanci35NotFound () {
        this.message = "Sin sanciones";
    }
    
    /**
     *
     * @param message
     */
    public Sanci35NotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
