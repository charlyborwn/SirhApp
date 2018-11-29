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
public class Escol30AlreadyExists extends Exception {

    /**
     *
     */
    public Escol30AlreadyExists () {

        this.message = new LanguagesUtil().getResources("escol30.alreadyexist");
        
    }
    
    /**
     *
     * @param message
     */
    public Escol30AlreadyExists(String message) {
        
        this.message =message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
