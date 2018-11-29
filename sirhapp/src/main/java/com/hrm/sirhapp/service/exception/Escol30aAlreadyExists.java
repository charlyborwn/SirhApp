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
public class Escol30aAlreadyExists extends Exception {

    /**
     *
     */
    public Escol30aAlreadyExists () {

        this.message = new LanguagesUtil().getResources("escol30a.alreadyexist");
        
    }
    
    /**
     *
     * @param message
     */
    public Escol30aAlreadyExists(String message) {
        
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
