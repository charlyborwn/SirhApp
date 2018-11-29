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
public class Exper31NotFound extends Exception {

    /**
     *
     */
    public Exper31NotFound () {
        this.message = "Sin datos de Experiencia";
    }
    
    /**
     *
     * @param message
     */
    public Exper31NotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
