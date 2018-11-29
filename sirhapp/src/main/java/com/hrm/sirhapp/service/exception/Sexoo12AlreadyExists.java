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
public class Sexoo12AlreadyExists extends Exception {

    /**
     *
     */
    public Sexoo12AlreadyExists() {

        this.message = new LanguagesUtil().getResources("sexoo12.alreadyexist");

    }

    /**
     *
     * @param message
     */
    public Sexoo12AlreadyExists(String message) {

        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    private String message;
}
