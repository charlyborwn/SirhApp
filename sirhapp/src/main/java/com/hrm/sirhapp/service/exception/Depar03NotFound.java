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
public class Depar03NotFound extends Exception {

    /**
     *
     */
    public Depar03NotFound () {
        this.message = "Sin departamentos registrados";
    }
    
    /**
     *
     * @param message
     */
    public Depar03NotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
