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
public class Usuar24AlreadyExists extends Exception {

    /**
     *
     */
    public Usuar24AlreadyExists () {

        this.message = new LanguagesUtil().getResources("security.useralreadyexist");
        
    }
    
    /**
     *
     * @param message
     */
    public Usuar24AlreadyExists(String message) {
        
        this.message = new LanguagesUtil().getResources("security.useralreadyexist");
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
