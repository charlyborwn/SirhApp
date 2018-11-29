package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Usuar24;
import com.hrm.sirhapp.service.Usuar24ManagerLocal;
import com.hrm.sirhapp.service.exception.Usuar24NotFound;
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
import javax.faces.application.FacesMessage;
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
public class Usuar24RecuperarClaveBacking extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Usuar24ManagerLocal usuar24Manager;

    private Usuar24 selectedUsuar24;

    private boolean active;
    private boolean sent;
    private String infoMessageModule;
    private String infoMessage;
    private String email;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Recuperación del password";
    }

    /**
     *
     */
    public void RecuperarClave() {
        if (email != null && !email.isEmpty() && email.length() >= 5) {
            this.email = email.toLowerCase();
        } else {
            this.email = null;
        }

        if (email != null) {
            getUsuar24EmailRequest(email);
            if (selectedUsuar24 != null) {
                String claveDinamica = updateUsuar24ClaveDinamica();
                if (claveDinamica != null) {
                    sent = EnviarEmail(selectedUsuar24.getCvusu(), claveDinamica);
                    if (!sent) {
                        infoMessage = "Ocurrio un error y no se realizo la soliciutud. " + email;
                        FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
                    }
                } else {
                    infoMessage = "Ocurrio un error y no se realizo la soliciutud. " + email;
                    FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
                }
            } else {
                infoMessage = "La direccion de correo que proporciono no existe: " + email;
            }
        }
    }

    /**
     *
     * @param email
     */
    public void RecuperarClave(String email) {
        if (email != null && !email.isEmpty() && email.length() >= 5) {
            this.email = email.toLowerCase();
        } else {
            this.email = null;
        }

        if (this.email != null) {
            getUsuar24EmailRequest(email);
            if (selectedUsuar24 != null) {
                String claveDinamica = updateUsuar24ClaveDinamica();
                if (claveDinamica != null) {
                    sent = EnviarEmail(selectedUsuar24.getCvusu(), claveDinamica);
                    if (!sent) {
                        infoMessage = "Ocurrio un error y no se realizo la soliciutud. " + email;
                        FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
                    }
                } else {
                    infoMessage = "Ocurrio un error y no se realizo la soliciutud. " + email;
                    FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
                }
            } else {
                infoMessage = "La direccion de correo que proporciono no existe: " + email;
            }
        }
    }

    private Boolean EnviarEmail(String usuario, String claveDinamica) {

        try {
            Object mailServiceBean = FacesUtil.getManagedBean("mailServiceBean", Object.class);
            Method sendRecuperaClaveEmail = mailServiceBean.getClass().getMethod("sendRecuperaClaveEmail", String.class, String.class, String.class);
            Boolean returnValue = (Boolean) sendRecuperaClaveEmail.invoke(mailServiceBean, email, usuario, claveDinamica);
            return returnValue;
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private Usuar24 getUsuar24EmailRequest(String email) {

        try {

            this.selectedUsuar24 = usuar24Manager.getUsuar24Email(email);

            if (usuar24Manager.alreadyExistsInnactive(selectedUsuar24.getCvusu())) {
                this.active = false;
            }

        } catch (Usuar24NotFound ex) {
            Logger.getLogger(Usuar24Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Usuar24Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedUsuar24;
    }

    /**
     *
     * @return
     */
    public String updateUsuar24ClaveDinamica() {

        try {

            String user = FacesUtil.getUserName();

            String claveDianmica = FacesUtil.getClaveDianmica();

            selectedUsuar24.setPausu(claveDianmica);

            selectedUsuar24.setStusu(Constants.RECORD_ACTIVED);
            selectedUsuar24.setFeusu(new Date());
            selectedUsuar24.setUsusu(user);

            usuar24Manager.updateUsuar24(selectedUsuar24);

            infoMessage = "Se genero el nuevo password, revise su buzón de correo: " + email;
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

            return claveDianmica;
        } catch (Usuar24NotFound ex) {
            Logger.getLogger(Usuar24Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        } catch (SecurityException | IllegalArgumentException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @return
     */
    public boolean isActive() {
        return active;
    }

    /**
     *
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
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
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public boolean isSent() {
        return sent;
    }

    /**
     *
     * @param sent
     */
    public void setSent(boolean sent) {
        this.sent = sent;
    }

}
