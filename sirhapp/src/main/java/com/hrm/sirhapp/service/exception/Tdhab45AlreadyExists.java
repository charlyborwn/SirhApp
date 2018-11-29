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
@ApplicationException(rollback = true)
public class Tdhab45AlreadyExists extends Exception {

    /**
     *
     */
    public Tdhab45AlreadyExists() {

        this.message = new LanguagesUtil().getResources("tdhab45.alreadyexist");

    }

    /**
     *
     * @param message
     */
    public Tdhab45AlreadyExists(String message) {

        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    private String message;
}
