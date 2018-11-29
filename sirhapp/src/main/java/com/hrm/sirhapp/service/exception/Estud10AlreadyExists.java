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
public class Estud10AlreadyExists extends Exception {

    /**
     *
     */
    public Estud10AlreadyExists () {

        this.message = new LanguagesUtil().getResources("estud10.alreadyexist");
        
    }
    
    /**
     *
     * @param message
     */
    public Estud10AlreadyExists(String message) {
        
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
