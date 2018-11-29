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
public class Chofe40AlreadyExists extends Exception {

    /**
     *
     */
    public Chofe40AlreadyExists () {

        this.message = new LanguagesUtil().getResources("chofe40.alreadyexist");
        
    }
    
    /**
     *
     * @param message
     */
    public Chofe40AlreadyExists(String message) {
        
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
