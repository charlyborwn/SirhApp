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
public class Sangr21AlreadyExists extends Exception {

    /**
     *
     */
    public Sangr21AlreadyExists() {

        this.message = new LanguagesUtil().getResources("sangr21.alreadyexist");

    }

    /**
     *
     * @param message
     */
    public Sangr21AlreadyExists(String message) {

        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    private String message;
}
