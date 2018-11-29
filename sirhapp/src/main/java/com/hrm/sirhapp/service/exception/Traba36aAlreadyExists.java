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
public class Traba36aAlreadyExists extends Exception {

    /**
     *
     */
    public Traba36aAlreadyExists () {

        this.message = new LanguagesUtil().getResources("traba36a.alreadyexist");
        
    }
    
    /**
     *
     * @param message
     */
    public Traba36aAlreadyExists(String message) {
        
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
