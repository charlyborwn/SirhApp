package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Catin44;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Catin44ManagerLocal;
import com.hrm.sirhapp.service.Empre04ManagerLocal;
import com.hrm.sirhapp.service.exception.Catin44AlreadyExists;
import com.hrm.sirhapp.service.exception.Empre04NotFound;
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
import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;
import javax.faces.event.AjaxBehaviorEvent;
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
public class Catin44AddBacking extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Catin44ManagerLocal catin44Manager;
    @EJB
    private Empre04ManagerLocal empre04Manager;

    @Named
    @Produces
    @RequestScoped
    private Catin44 newCatin44 = new Catin44();

    private String infoMessageModule;
    private String infoMessage;
    private boolean registered;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Catalogo Articulos Inventarios";
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

            newCatin44.setStcin(Constants.RECORD_ACTIVED);
            newCatin44.setFecin(new Date());
            newCatin44.setUscin(FacesUtil.getUserName());

            catin44Manager.registerCatin44(newCatin44);

            infoMessage = "El registro se creo correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

            return "success";

        } catch (Catin44AlreadyExists ex) {
            Logger.getLogger(Catin44AddBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
            return "failure";
        } catch (Exception ex) {
            Logger.getLogger(Catin44AddBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error en la base de datos";
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
            return "failure";
        }
    }

    /**
     *
     * @param vce
     */
    public void handleChange(AjaxBehaviorEvent vce) {

        String value = (String) ((ValueHolder) vce.getSource()).getValue();
        String id = ((UIComponent) vce.getSource()).getId();
        System.out.println("id:" + id + " value:" + value);
        switch (id) {
            case "cecin":
                if (newCatin44 != null) {
                    try {
                        newCatin44.setNecin(empre04Manager.getEmpre04(value).getNoemp());
                    } catch (Empre04NotFound ex) {
                        Logger.getLogger(Catin44AddBacking.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            default:
                break;
        }
    }

}
