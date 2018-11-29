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
public class Tapri16AlreadyExists extends Exception {

    /**
     *
     */
    public Tapri16AlreadyExists() {

        this.message = new LanguagesUtil().getResources("tapri16.alreadyexist");

    }

    /**
     *
     * @param message
     */
    public Tapri16AlreadyExists(String message) {

        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    private String message;
}
