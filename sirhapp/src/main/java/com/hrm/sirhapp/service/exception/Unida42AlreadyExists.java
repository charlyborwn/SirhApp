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
public class Unida42AlreadyExists extends Exception {

    /**
     *
     */
    public Unida42AlreadyExists() {

        this.message = new LanguagesUtil().getResources("unida42.alreadyexist");

    }

    /**
     *
     * @param message
     */
    public Unida42AlreadyExists(String message) {

        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    private String message;
}
