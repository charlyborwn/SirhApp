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
public class Tipsa47AlreadyExists extends Exception {

    /**
     *
     */
    public Tipsa47AlreadyExists() {

        this.message = new LanguagesUtil().getResources("tipsa47.alreadyexist");

    }

    /**
     *
     * @param message
     */
    public Tipsa47AlreadyExists(String message) {

        this.message =message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    private String message;
}
