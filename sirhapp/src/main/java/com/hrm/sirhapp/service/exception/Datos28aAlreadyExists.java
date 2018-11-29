package com.hrm.sirhapp.service.exception;

import com.hrm.sirhapp.util.LanguagesUtil;
import javax.ejb.ApplicationException;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@ApplicationException(rollback=true)
public class Datos28aAlreadyExists extends Exception {

    /**
     *
     */
    public Datos28aAlreadyExists () {

        this.message = new LanguagesUtil().getResources("datos28a.alreadyexist");
        
    }
    
    /**
     *
     * @param message
     */
    public Datos28aAlreadyExists(String message) {
        
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
