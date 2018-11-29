package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Usuar24;
import com.hrm.sirhapp.service.Usuar24ManagerLocal;
import com.hrm.sirhapp.service.exception.Usuar24AlreadyExists;
import com.hrm.sirhapp.util.FacesUtil;
import com.hrm.sirhapp.util.LanguagesUtil;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
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
public class Usuar24AddBacking extends BaseBacking implements Serializable {

    @EJB
    private Usuar24ManagerLocal usuar24Manager;

    @Named
    @Produces
    @RequestScoped
    private Usuar24 newUsuar24 = new Usuar24();

    private String infoMessageModule;
    private String infoMessage;
    private boolean registered;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Usuarios";
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

    private Boolean EnviarEmail(String email, String usuario, String clave) {
        Boolean returnValue = false;
        try {
            Object mailServiceBean = FacesUtil.getManagedBean("mailServiceBean", Object.class);
            Method sendRecuperaClaveEmail = mailServiceBean.getClass().getMethod("sendNuevoUsuarioEmail", String.class, String.class, String.class);
            Boolean returnValue1 = (Boolean) sendRecuperaClaveEmail.invoke(mailServiceBean, email, usuario, clave);

            Method sendNvoUsuarioInfoEmail = mailServiceBean.getClass().getMethod("sendNvoUsuarioInfoEmail", String.class);
            Boolean returnValue2 = (Boolean) sendNvoUsuarioInfoEmail.invoke(mailServiceBean, usuario);

            if (returnValue1 == true && returnValue2 == true) {
                returnValue = true;
            }
            return returnValue;
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     *
     * @return
     */
    public String register() {
        Severity SEVERITY = FacesMessage.SEVERITY_INFO;

        try {
            String userName;
            String userActive;

            userName = (String) FacesUtil.getUserName();
            userActive = Constants.RECORD_DELETED;

            if (!newUsuar24.getPausu().equals(newUsuar24.getPassword2())) {
                infoMessage = new LanguagesUtil().getResources("security.identicalpassword");
                SEVERITY = FacesMessage.SEVERITY_ERROR;
            } else {

                if (newUsuar24.getCousu() != null) {
                    newUsuar24.setStusu(newUsuar24.getCousu().toLowerCase());
                }
                
                newUsuar24.setNausu("8");

                newUsuar24.setStusu(userActive);
                newUsuar24.setFeusu(new Date());
                newUsuar24.setUsusu(userName);

                usuar24Manager.registerUsuar24(newUsuar24);

                EnviarEmail(newUsuar24.getCousu(), newUsuar24.getCvusu(), newUsuar24.getPausu());

                SEVERITY = FacesMessage.SEVERITY_INFO;
                infoMessage = new LanguagesUtil().getResources("security.usersaved");
                registered = true;
                newUsuar24 = new Usuar24();
            }
        } catch (Usuar24AlreadyExists ex) {
            infoMessage = ex.getMessage();
            SEVERITY = FacesMessage.SEVERITY_INFO;
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);

        } catch (EJBException ex) {
            if (ex.getMessage().contains("Error Code: 1062")) {
                SEVERITY = FacesMessage.SEVERITY_INFO;
                infoMessage = new LanguagesUtil().getResources("security.useremailexist");
            } else {
                SEVERITY = FacesMessage.SEVERITY_FATAL;
                infoMessage = new LanguagesUtil().getResources("security.registerusererror");
            }
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        FacesUtil.addMessage(SEVERITY, infoMessageModule, infoMessage);
        return infoMessage;
    }
}
