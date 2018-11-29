package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Escol30;
import com.hrm.sirhapp.service.Escol30ManagerLocal;
import com.hrm.sirhapp.service.exception.Escol30AlreadyExists;
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
import javax.faces.context.FacesContext;
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
public class Escol30AddBacking extends BaseBacking implements Serializable {

    @EJB
    private Escol30ManagerLocal datos28aManager;

    @Named
    @Produces
    @RequestScoped
    private Escol30 newEscol30 = new Escol30();

    private String infoMessage;
    private boolean registered;

    @PostConstruct
    private void init() {
        this.registered = false;
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

            newEscol30.setStesc(Constants.RECORD_ACTIVED);
            newEscol30.setFeesc(new Date());
            newEscol30.setUsesc(FacesUtil.getUserName());

            datos28aManager.registerEscol30(newEscol30);

            infoMessage = "El registro se creo correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));
            this.registered = true;

        } catch (Escol30AlreadyExists ex) {
            Logger.getLogger(Escol30AddBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "El registro de escolaridad del trabajador ya se encuentra en la base de datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

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
                Logger.getLogger(Escol30AddBacking.class.getName()).log(Level.SEVERE, null, ex);

            }
            infoMessage = "Ocurrio un error en la base de datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));
        }

        return null;
    }

}
