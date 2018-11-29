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
public class Relig11AlreadyExists extends Exception {

    /**
     *
     */
    public Relig11AlreadyExists() {

        this.message = new LanguagesUtil().getResources("relig11.alreadyexist");

    }

    /**
     *
     * @param message
     */
    public Relig11AlreadyExists(String message) {

        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    private String message;
}
