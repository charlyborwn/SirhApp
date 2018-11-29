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
public class Empre04AlreadyExists extends Exception {

    /**
     *
     */
    public Empre04AlreadyExists () {

        this.message = new LanguagesUtil().getResources("empre04.alreadyexist");
        
    }
    
    /**
     *
     * @param message
     */
    public Empre04AlreadyExists(String message) {
        
        this.message =message;
    }
    
    @Override
    public String getMessage() {
        return this.message; 
    }
    
    private String message;
}
