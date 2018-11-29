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
public class Requi54NotFound extends Exception {

    /**
     *
     */
    public Requi54NotFound () {
        this.message = "La lista de requisitos no se ha creado.";
    }
    
    /**
     *
     * @param message
     */
    public Requi54NotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
