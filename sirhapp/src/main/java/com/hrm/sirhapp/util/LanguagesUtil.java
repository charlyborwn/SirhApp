package com.hrm.sirhapp.util;

import com.hrm.sirhapp.LocaleBean;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@SessionScoped
public class LanguagesUtil implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String resources;
    private String bundle;

    /**
     *
     * @param msg
     * @return
     */
    public String getResources(String msg) {

        String strResources;

        FacesContext ctx = FacesContext.getCurrentInstance();
        LocaleBean lbd = ctx.getApplication().evaluateExpressionGet(ctx, "#{localeBean}", LocaleBean.class);

        ResourceBundle resource = ResourceBundle.getBundle("messages", lbd.getLocale());
        strResources = resource.getString(msg);

        try {

            this.resources = strResources;
            return resources;

        } catch (Exception ex) {

            this.resources = "Error 500: Al obtener el mensaje en el idioma seleccionado, contacte a area de soporte técnico";
            return resources;

        }
    }

    /**
     *
     * @param resources
     */
    public void setResources(String resources) {
        this.resources = resources;
    }

    /**
     *
     * @param msg
     * @return
     */
    public String getBundle(String msg) {

        ResourceBundle resource = ResourceBundle.getBundle("Bundle");

        try {

            this.bundle = resource.getString(msg);
            return bundle;

        } catch (Exception ex) {

            this.bundle = "Error 500: Al obtener el mensaje en el idioma seleccionado, contacte a area de soporte técnico";
            return bundle;

        }
    }

    /**
     *
     * @param bundle
     */
    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

}
