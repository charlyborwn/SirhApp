package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Benef26;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Benef26ManagerLocal;
import com.hrm.sirhapp.service.exception.Benef26AlreadyExists;
import com.hrm.sirhapp.util.FacesUtil;
import java.io.Serializable;
import java.util.Date;
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

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@ViewScoped
public class Benef26AddBacking extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Benef26ManagerLocal benef26Manager;

    @Named
    @Produces
    @RequestScoped
    private Benef26 newBenef26 = new Benef26();

    private String infoMessageModule;
    private String infoMessage;
    private boolean registered;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Beneficiarios del Trabajador";
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

            newBenef26.setStben(Constants.RECORD_ACTIVED);
            newBenef26.setFeben(new Date());
            newBenef26.setUsben(FacesUtil.getUserName());

            benef26Manager.registerBenef26(newBenef26);

            infoMessage = "El registro se creo correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Benef26AlreadyExists ex) {
            Logger.getLogger(Benef26AddBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);

        } catch (EJBException ex) {

            Logger.getLogger(Benef26AddBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error en la base de datos";
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return null;
    }

}
