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
public class Ident49aNotFound extends Exception {

    /**
     *
     */
    public Ident49aNotFound () {
        this.message = "Los Datos de Identidad no se han creado.";
    }
    
    /**
     *
     * @param message
     */
    public Ident49aNotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
