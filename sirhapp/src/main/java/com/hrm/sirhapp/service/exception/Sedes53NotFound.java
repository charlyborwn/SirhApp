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
public class Sedes53NotFound extends Exception {

    /**
     *
     */
    public Sedes53NotFound () {
        this.message = "Sin registros.";
    }
    
    /**
     *
     * @param message
     */
    public Sedes53NotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
