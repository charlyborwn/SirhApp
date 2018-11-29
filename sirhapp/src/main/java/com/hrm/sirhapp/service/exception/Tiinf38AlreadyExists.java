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
public class Tiinf38AlreadyExists extends Exception {

    /**
     *
     */
    public Tiinf38AlreadyExists() {

        this.message = new LanguagesUtil().getResources("tiinf38.alreadyexist");

    }

    /**
     *
     * @param message
     */
    public Tiinf38AlreadyExists(String message) {

        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    private String message;
}
