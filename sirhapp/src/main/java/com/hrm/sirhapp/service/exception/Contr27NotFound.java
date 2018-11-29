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
public class Contr27NotFound extends Exception {

    /**
     *
     */
    public Contr27NotFound () {
        this.message = "No existen datos del contrato";
    }
    
    /**
     *
     * @param message
     */
    public Contr27NotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}

