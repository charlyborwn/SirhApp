package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Escol30a;
import com.hrm.sirhapp.service.Escol30aManagerLocal;
import com.hrm.sirhapp.service.exception.Escol30aAlreadyExists;
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
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@ViewScoped
public class Escol30aAddBacking extends BaseBacking implements Serializable {

    @EJB
    private Escol30aManagerLocal datos28aManager;

    @Named
    @Produces
    @RequestScoped
    private Escol30a newEscol30a = new Escol30a();

    private String infoMessage;
    private boolean registered;
    private Integer escol30aTab;

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

            newEscol30a.setStescA(Constants.RECORD_ACTIVED);
            newEscol30a.setFeescA(new Date());
            newEscol30a.setUsescA(FacesUtil.getUserName());

            datos28aManager.registerEscol30a(newEscol30a);

            infoMessage = "El registro se creo correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));
            this.registered = true;

        } catch (Escol30aAlreadyExists ex) {
            Logger.getLogger(Escol30aAddBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Los datos personales del aspirante ya se encuentran en la base de datos";
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
                Logger.getLogger(Escol30aAddBacking.class.getName()).log(Level.SEVERE, null, ex);

            }
            infoMessage = "Ocurrio un error en la base de datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));
        }

        return null;
    }

    /**
     *
     * @param event
     */
    public void onTabChange(TabChangeEvent event) {
        TabView tabView = (TabView) event.getComponent();

        escol30aTab = tabView.getChildren().indexOf(event.getTab());
        System.out.println("tab actual escol:" + escol30aTab);

    }

    /**
     *
     * @return
     */
    public Integer getEscol30aTab() {
        return escol30aTab;
    }

    /**
     *
     * @param escol30aTab
     */
    public void setEscol30aTab(Integer escol30aTab) {
        this.escol30aTab = escol30aTab;
    }
    
    

}
