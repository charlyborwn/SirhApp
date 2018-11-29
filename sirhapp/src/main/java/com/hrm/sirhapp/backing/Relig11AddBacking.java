package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Relig11;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Relig11ManagerLocal;
import com.hrm.sirhapp.service.exception.Relig11AlreadyExists;
import com.hrm.sirhapp.util.FacesUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@ViewScoped
public class Relig11AddBacking extends BaseBacking implements Serializable {

    @EJB
    private Relig11ManagerLocal relig11Manager;

    @Named
    @Produces
    @RequestScoped
    private Relig11 newRelig11 = new Relig11();

    private String infoMessageModule;
    private String infoMessage;
    private boolean registered;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Religiones";
        this.registered = false;
    }

    /**
     *
     * @return
     */
    public String getInfoMessageModule() {
        return infoMessageModule;
    }

    /**
     *
     * @param infoMessageModule
     */
    public void setInfoMessageModule(String infoMessageModule) {
        this.infoMessageModule = infoMessageModule;
    }

    /**
     *
     * @return
     */
    public boolean isRegistered() {
        return registered;
    }

    /**
     *
     * @param registered
     */
    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    /**
     *
     * @return
     */
    public String getInfoMessage() {
        return infoMessage;
    }

    /**
     *
     * @param infoMessage
     */
    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    /**
     *
     * @return
     */
    public String register() {

        try {
            

            newRelig11.setStrel(Constants.RECORD_ACTIVED); 
            newRelig11.setFerel(new Date());
            newRelig11.setUsrel(FacesUtil.getUserName());

            relig11Manager.registerRelig11(newRelig11);

            infoMessage = "El registro se creo correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Relig11AlreadyExists ex) {
            Logger.getLogger(Relig11AddBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);

        } catch (EJBException ex) {
            @SuppressWarnings("ThrowableResultIgnored")
            Exception cause = ex.getCausedByException();
            if (cause instanceof ConstraintViolationException) {
                @SuppressWarnings("ThrowableResultIgnored")
                ConstraintViolationException cve = (ConstraintViolationException) ex.getCausedByException();
                for (Iterator<ConstraintViolation<?>> it = cve.getConstraintViolations().iterator(); it.hasNext();) {
                    ConstraintViolation<? extends Object> v = it.next();
                    System.err.println(v);
                    System.err.println("==>>" + v.getMessage());
                }
                Logger.getLogger(Relig11AddBacking.class.getName()).log(Level.SEVERE, null, ex);

            }
            infoMessage = "Ocurrio un error en la base de datos";
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return null;
    }

}
