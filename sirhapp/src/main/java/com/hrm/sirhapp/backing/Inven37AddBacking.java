package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Inven37;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Inven37ManagerLocal;
import com.hrm.sirhapp.service.Prove48ManagerLocal;
import com.hrm.sirhapp.service.exception.Inven37AlreadyExists;
import com.hrm.sirhapp.util.FacesUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
public class Inven37AddBacking extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Inven37ManagerLocal inven37Manager;
    @EJB
    private Prove48ManagerLocal prove48Manager;

    @Named
    @Produces
    @RequestScoped
    private Inven37 newInven37 = new Inven37();

    private String infoMessageModule;
    private String infoMessage;
    private boolean registered;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Inventario";
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

            if (prove48Manager.retrieveProve48(newInven37.getRfinv()).getNcpro().equals("EL PROVEEDOR NO ESTA REGISTRADO")) {
                infoMessage = "Registre primero el Proveedor y vuelva a intentarlo.";
            } else {

                newInven37.setStinv(Constants.RECORD_ACTIVED);
                newInven37.setFeinv(new Date());
                newInven37.setUsinv(FacesUtil.getUserName());

                inven37Manager.registerInven37(newInven37);

                infoMessage = "El registro se creo correctamente";
            }
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            return "success";

        } catch (Inven37AlreadyExists ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error en la base de datos";
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return "failure";
    }
}
