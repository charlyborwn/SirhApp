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
public class Niacc33AlreadyExists extends Exception {

    /**
     *
     */
    public Niacc33AlreadyExists () {
        this.message = "El usuario ya tiene este permiso asignado";
    }
    
    /**
     *
     * @param message
     */
    public Niacc33AlreadyExists(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
