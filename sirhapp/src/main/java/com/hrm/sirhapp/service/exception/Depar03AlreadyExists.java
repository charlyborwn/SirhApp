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
public class Depar03AlreadyExists extends Exception {

    /**
     *
     */
    public Depar03AlreadyExists() {

        this.message = new LanguagesUtil().getResources("depar03.alreadyexist");

    }

    /**
     *
     * @param message
     */
    public Depar03AlreadyExists(String message) {

        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    private String message;
}
