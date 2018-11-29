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
public class Benef26NotFound extends Exception {

    /**
     *
     */
    public Benef26NotFound () {
        this.message = "Beneficiario no registrado";
    }
    
    /**
     *
     * @param message
     */
    public Benef26NotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
