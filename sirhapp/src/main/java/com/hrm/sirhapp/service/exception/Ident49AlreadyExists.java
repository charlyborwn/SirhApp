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
public class Ident49AlreadyExists extends Exception {

    /**
     *
     */
    public Ident49AlreadyExists () {

        this.message = new LanguagesUtil().getResources("ident49.alreadyexist");
        
    }
    
    /**
     *
     * @param message
     */
    public Ident49AlreadyExists(String message) {
        
        this.message =message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
