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
public class Comit08AlreadyExists extends Exception {

    /**
     *
     */
    public Comit08AlreadyExists () {

        this.message = new LanguagesUtil().getResources("comit08.alreadyexist");
        
    }
    
    /**
     *
     * @param message
     */
    public Comit08AlreadyExists(String message) {
        
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
