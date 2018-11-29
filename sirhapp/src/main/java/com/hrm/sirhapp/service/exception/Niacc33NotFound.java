
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
public class Niacc33NotFound extends Exception {

    /**
     *
     */
    public   Niacc33NotFound () {
        this.message = "Sin accesos";
    }
    
    /**
     *
     * @param message
     */
    public Niacc33NotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
