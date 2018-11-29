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
public class Inven37AlreadyExists extends Exception {

    /**
     *
     */
    public Inven37AlreadyExists () {

        this.message = new LanguagesUtil().getResources("inven37.alreadyexist");
        
    }
    
    /**
     *
     * @param message
     */
    public Inven37AlreadyExists(String message) {
        
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
