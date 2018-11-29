package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Contr27;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Contr27ManagerLocal;
import com.hrm.sirhapp.service.exception.Contr27AlreadyExists;
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
import javax.faces.context.FacesContext;
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
public class Contr27AddBacking extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Contr27ManagerLocal contr27Manager;

    @Named
    @Produces
    @RequestScoped
    private Contr27 newContr27 = new Contr27();

    private String infoMessageModule;
    private String infoMessage;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Catalogo Articulos de Inventarios";
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {

        }
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
    public String register() {

        try {

            newContr27.setStcto(Constants.RECORD_ACTIVED);
            newContr27.setFecto(new Date());
            newContr27.setUscto(FacesUtil.getUserName());

            contr27Manager.registerContr27(newContr27);

            infoMessage = "El registro se creo correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

            return "success";

        } catch (Contr27AlreadyExists ex) {
            Logger.getLogger(Contr27AddBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
            return "failure";
        } catch (EJBException ex) {
            infoMessage = "El registro no se agregó en la base de datos.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
            return "failure";
        }

    }

}
