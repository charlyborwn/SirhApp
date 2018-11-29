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
public class Ubica23AlreadyExists extends Exception {

    /**
     *
     */
    public Ubica23AlreadyExists() {

        this.message = new LanguagesUtil().getResources("ubica23.alreadyexist");

    }

    /**
     *
     * @param message
     */
    public Ubica23AlreadyExists(String message) {

        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    private String message;
}
