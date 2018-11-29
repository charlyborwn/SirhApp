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
public class Rutas41NotFound extends Exception {

    /**
     *
     */
    public Rutas41NotFound () {
        this.message = "Sin Avisos";
    }
    
    /**
     *
     * @param message
     */
    public Rutas41NotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
