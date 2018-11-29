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
public class Civil06AlreadyExists extends Exception {

    /**
     *
     */
    public Civil06AlreadyExists () {

        this.message = new LanguagesUtil().getResources("civil06.alreadyexist");
        
    }
    
    /**
     *
     * @param message
     */
    public Civil06AlreadyExists(String message) {
        
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
