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
public class Tabla52AlreadyExists extends Exception {

    /**
     *
     */
    public Tabla52AlreadyExists() {

        this.message = new LanguagesUtil().getResources("tabla52.alreadyexist");

    }

    /**
     *
     * @param message
     */
    public Tabla52AlreadyExists(String message) {

        this.message =message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    private String message;
}
