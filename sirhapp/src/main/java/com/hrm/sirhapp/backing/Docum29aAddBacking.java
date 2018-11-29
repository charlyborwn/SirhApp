package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.ManagedBeans;
import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Docum29a;
import com.hrm.sirhapp.service.Docum29aManagerLocal;
import com.hrm.sirhapp.service.Tidoc18ManagerLocal;
import com.hrm.sirhapp.service.exception.Docum29aAlreadyExists;
import com.hrm.sirhapp.service.exception.Tidoc18NotFound;
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
import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@ViewScoped
public class Docum29aAddBacking extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Docum29aManagerLocal docum29aManager;
    
    @EJB
    private Tidoc18ManagerLocal tidoc18Manager;

    @Named
    @Produces
    @RequestScoped
    private Docum29a newDocum29a = new Docum29a();

    private String infoMessageModule;
    private String infoMessage;
    private boolean registered;
    private String path;
    private String w1docA;
    private String tddocA;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Documentacion del Aspirante";
        this.registered = false;
    }

    /**
     *
     */
    public void prepareCreateDocum29a() {
        newDocum29a = new Docum29a();
        path = null;
        w1docA = null;
        tddocA = null;
    }

    /**
     *
     * @return
     */
    public String getTddocA() {
        return tddocA;
    }

    /**
     *
     * @param tddocA
     */
    public void setTddocA(String tddocA) {
        this.tddocA = tddocA;
    }

    /**
     *
     * @return
     */
    public String getW1docA() {
        return w1docA;
    }

    /**
     *
     * @param w1docA
     */
    public void setW1docA(String w1docA) {
        this.w1docA = w1docA;
    }

    /**
     *
     * @return
     */
    public String getPath() {
        return path;
    }

    /**
     *
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
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
        UserSessionBean userSessionBean = ManagedBeans.getBean(UserSessionBean.class, "userSessionBean");
        newDocum29a.setRfdocA(userSessionBean.getAspirante());

        System.out.println("valor del rfc aspirante " + newDocum29a.getRfdocA());

        try {
            newDocum29a.setTddocA(tddocA);
            newDocum29a.setW1docA(w1docA);
            newDocum29a.setPadocA(path);
            newDocum29a.setStdocA(Constants.RECORD_ACTIVED);
            newDocum29a.setFedocA(new Date());
            newDocum29a.setUsdocA(FacesUtil.getUserName());

            docum29aManager.registerDocum29a(newDocum29a);

            infoMessage = "El registro se creo correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Docum29aAlreadyExists ex) {
            Logger.getLogger(Docum29aAddBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        } catch (EJBException ex) {
            Logger.getLogger(Docum29aAddBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error en la base de datos";
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return "success";
    }

    /**
     *
     * @param docum29a
     * @return
     */
    public String register(Docum29a docum29a) {

        System.out.println(docum29a.getRfdocA());

        try {

            docum29aManager.registerDocum29a(docum29a);

            infoMessage = "El registro se creo correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            return "success";

        } catch (Docum29aAlreadyExists ex) {
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
            return "failure";
        } catch (EJBException ex) {
            Logger.getLogger(Docum29aAddBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error en la base de datos";
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
            return "failure";
        }

    }

    /**
     *
     * @param event
     */
    public void prepareFileUpload(AjaxBehaviorEvent event) {

        String value = (String) ((ValueHolder) event.getSource()).getValue();
        String id = ((UIComponent) event.getSource()).getId();
        tddocA = value;

        try {
            if (newDocum29a != null) {

                w1docA = tidoc18Manager.getTidoc18(value);
            }

        } catch (Tidoc18NotFound ex) {
            Logger.getLogger(Traba36aWizardBacking.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(value); // Look, (new) value is already set.
    }

}
