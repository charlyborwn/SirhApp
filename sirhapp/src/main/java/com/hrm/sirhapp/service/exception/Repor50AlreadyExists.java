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
public class Repor50AlreadyExists extends Exception {

    /**
     *
     */
    public Repor50AlreadyExists () {
        this.message = "El reporte ya esta registrado";
    }
    
    /**
     *
     * @param message
     */
    public Repor50AlreadyExists(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
