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
public class Datos28NotFound extends Exception {

    /**
     *
     */
    public Datos28NotFound () {
        this.message = "Los datos personales del trabajador no estan registrados";
    }
    
    /**
     *
     * @param message
     */
    public Datos28NotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
