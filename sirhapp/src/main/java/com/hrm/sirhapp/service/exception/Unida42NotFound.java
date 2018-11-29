
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
public class Unida42NotFound extends Exception {

    /**
     *
     */
    public   Unida42NotFound () {
        this.message = "Sin unidades de transporte registradas";
    }
    
    /**
     *
     * @param message
     */
    public Unida42NotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
