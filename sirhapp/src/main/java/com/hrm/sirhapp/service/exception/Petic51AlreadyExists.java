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
public class Petic51AlreadyExists extends Exception {

    /**
     *
     */
    public Petic51AlreadyExists () {
        this.message = "La solicitud ya existe";
    }
    
    /**
     *
     * @param message
     */
    public Petic51AlreadyExists(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
