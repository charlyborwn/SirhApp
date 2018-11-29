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
public class Espec05AlreadyExists extends Exception {

    /**
     *
     */
    public Espec05AlreadyExists () {

        this.message = new LanguagesUtil().getResources("espec05.alreadyexist");
        
    }
    
    /**
     *
     * @param message
     */
    public Espec05AlreadyExists(String message) {
        
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
