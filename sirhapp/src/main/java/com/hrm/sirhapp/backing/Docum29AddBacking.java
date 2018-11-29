package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.ManagedBeans;
import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Docum29;
import com.hrm.sirhapp.service.Docum29ManagerLocal;
import com.hrm.sirhapp.service.Tidoc18ManagerLocal;
import com.hrm.sirhapp.service.exception.Docum29AlreadyExists;
import com.hrm.sirhapp.service.exception.Tidoc18NotFound;
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
import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
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
public class Docum29AddBacking extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Docum29ManagerLocal datos28aManager;

    @EJB
    private Tidoc18ManagerLocal tidoc18Manager;

    @Named
    @Produces
    @RequestScoped
    private Docum29 newDocum29 = new Docum29();

    private String infoMessage;
    private boolean registered;
    private String path;
    private String w1doc;
    private String tddoc;

    @PostConstruct
    private void init() {
        this.registered = false;
    }

    /**
     *
     */
    public void prepareCreateDocum29() {
        newDocum29 = new Docum29();
        path = null;
        w1doc = null;
        tddoc = null;
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
        newDocum29.setNtdoc(userSessionBean.getTrabajador());

        System.out.println("valor del nt  " + newDocum29.getNtdoc());

        try {
            newDocum29.setTddoc(tddoc);
            newDocum29.setW1doc(w1doc);
            newDocum29.setPadoc(path);
            newDocum29.setStdoc(Constants.RECORD_ACTIVED);
            newDocum29.setFedoc(new Date());
            newDocum29.setUsdoc(FacesUtil.getUserName());

            datos28aManager.registerDocum29(newDocum29);

            infoMessage = "El registro se creo correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));
            this.registered = true;

        } catch (Docum29AlreadyExists ex) {
            Logger.getLogger(Docum29AddBacking.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Docum29AddBacking.class.getName()).log(Level.SEVERE, null, ex);

            }
            infoMessage = "Ocurrio un error en la base de datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));
        }

        return null;
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
    public String getW1doc() {
        return w1doc;
    }

    /**
     *
     * @param w1doc
     */
    public void setW1doc(String w1doc) {
        this.w1doc = w1doc;
    }

    /**
     *
     * @return
     */
    public String getTddoc() {
        return tddoc;
    }

    /**
     *
     * @param tddoc
     */
    public void setTddoc(String tddoc) {
        this.tddoc = tddoc;
    }

    /**
     *
     * @param event
     */
    public void prepareFileUpload(AjaxBehaviorEvent event) {

        String value = (String) ((ValueHolder) event.getSource()).getValue();
        String id = ((UIComponent) event.getSource()).getId();
        tddoc = value;

        try {
            if (newDocum29 != null) {

                w1doc = tidoc18Manager.getTidoc18(value);
            }

        } catch (Tidoc18NotFound ex) {
            Logger.getLogger(Traba36aWizardBacking.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(value); // Look, (new) value is already set.
    }

}
