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
public class Escol30NotFound extends Exception {

    /**
     *
     */
    public Escol30NotFound () {
        this.message = "Sin datos de escolaridad";
    }
    
    /**
     *
     * @param message
     */
    public Escol30NotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
