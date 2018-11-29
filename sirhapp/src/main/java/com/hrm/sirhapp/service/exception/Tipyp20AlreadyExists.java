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
public class Tipyp20AlreadyExists extends Exception {

    /**
     *
     */
    public Tipyp20AlreadyExists() {

        this.message = new LanguagesUtil().getResources("tipyp20.alreadyexist");

    }

    /**
     *
     * @param message
     */
    public Tipyp20AlreadyExists(String message) {

        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    private String message;
}
