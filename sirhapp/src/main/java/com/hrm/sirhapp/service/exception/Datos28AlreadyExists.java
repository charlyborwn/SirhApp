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
public class Datos28AlreadyExists extends Exception {

    /**
     *
     */
    public Datos28AlreadyExists() {

        this.message = new LanguagesUtil().getResources("datos28.alreadyexist");

    }

    /**
     *
     * @param message
     */
    public Datos28AlreadyExists(String message) {

        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    private String message;
}
