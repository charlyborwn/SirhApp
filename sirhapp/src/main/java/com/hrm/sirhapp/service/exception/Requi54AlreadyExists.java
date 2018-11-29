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
public class Requi54AlreadyExists extends Exception {

    /**
     *
     */
    public Requi54AlreadyExists () {

        this.message = new LanguagesUtil().getResources("requi54.alreadyexist");
        
    }
    
    /**
     *
     * @param message
     */
    public Requi54AlreadyExists(String message) {
        
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}

