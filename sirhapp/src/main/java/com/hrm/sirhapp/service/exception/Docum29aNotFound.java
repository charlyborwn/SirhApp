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
public class Docum29aNotFound extends Exception {

    /**
     *
     */
    public Docum29aNotFound () {
        this.message = "Sin documentos";
    }
    
    /**
     *
     * @param message
     */
    public Docum29aNotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
