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
public class Traba36aNotFound extends Exception {

    /**
     *
     */
    public Traba36aNotFound () {
        this.message = "El aspirante no esta registrado";
    }
    
    /**
     *
     * @param message
     */
    public Traba36aNotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
