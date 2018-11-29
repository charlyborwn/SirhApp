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
public class Stcon14AlreadyExists extends Exception {

    /**
     *
     */
    public Stcon14AlreadyExists() {

        this.message = new LanguagesUtil().getResources("stcon14.alreadyexist");

    }

    /**
     *
     * @param message
     */
    public Stcon14AlreadyExists(String message) {

        this.message =message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    private String message;
}
