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
public class Ident49aAlreadyExists extends Exception {

    /**
     *
     */
    public Ident49aAlreadyExists () {

        this.message = new LanguagesUtil().getResources("ident49a.alreadyexist");
        
    }
    
    /**
     *
     * @param message
     */
    public Ident49aAlreadyExists(String message) {
        
        this.message =message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
