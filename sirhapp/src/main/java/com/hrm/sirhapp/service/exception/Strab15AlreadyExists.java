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
public class Strab15AlreadyExists extends Exception {

    /**
     *
     */
    public Strab15AlreadyExists() {

        this.message = new LanguagesUtil().getResources("strab15.alreadyexist");

    }

    /**
     *
     * @param message
     */
    public Strab15AlreadyExists(String message) {

        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    private String message;
}
