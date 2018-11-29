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
public class Usuar24NotFound extends Exception {

    /**
     *
     */
    public Usuar24NotFound () {
        this.message = new LanguagesUtil().getResources("security.usernotfound");
    }
    
    /**
     *
     * @param message
     */
    public Usuar24NotFound(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
