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
public class Benef26aAlreadyExists extends Exception {

    /**
     *
     */
    public Benef26aAlreadyExists () {

        this.message = new LanguagesUtil().getResources("benef26a.alreadyexist");
        
    }
    
    /**
     *
     * @param message
     */
    public Benef26aAlreadyExists(String message) {
        
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
