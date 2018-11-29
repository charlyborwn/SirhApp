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
public class Benef26aNotFound extends Exception {

    /**
     *
     */
    public Benef26aNotFound () {
        this.message = "Beneficiario no registrado";
    }
    
    /**
     *
     * @param message
     */
    public Benef26aNotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
