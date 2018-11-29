package com.hrm.sirhapp;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * Manages the application Bean Values 
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@ApplicationScoped
public class ApplicationBean {
    
    private String infoMessageModule;
    private String infoMessage;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Servicio de la aplicacion SIRH";
    }

    /**
     *
     * @return infoMessageModule The String value
     */
    public String getInfoMessageModule() {
        return infoMessageModule;
    }

    /**
     *
     * @param infoMessageModule Set the String value provided
     */
    public void setInfoMessageModule(String infoMessageModule) {
        this.infoMessageModule = infoMessageModule;
    }

    /**
     *
     * @return return The String value
     */
    public String getInfoMessage() {
        return infoMessage;
    }

    /**
     *
     * @param infoMessage Set the String value provided
     */
    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }
    
    
}
