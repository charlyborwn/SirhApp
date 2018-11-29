package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Repor50;
import com.hrm.sirhapp.service.Repor50ManagerLocal;
import com.hrm.sirhapp.service.exception.Repor50AlreadyExists;
import com.hrm.sirhapp.util.FacesUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@ViewScoped
public class Repor50AddBacking extends BaseBacking implements Serializable {

    @EJB
    private Repor50ManagerLocal repor50Manager;

    @Named
    @Produces
    @RequestScoped
    private Repor50 newRepor50 = new Repor50();

    private String infoMessageModule;
    private String infoMessage;
    private boolean registered;
    private Part filePart;

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
    public Repor50ManagerLocal getRepor50Manager() {
        return repor50Manager;
    }

    /**
     *
     * @param repor50Manager
     */
    public void setRepor50Manager(Repor50ManagerLocal repor50Manager) {
        this.repor50Manager = repor50Manager;
    }

    /**
     *
     * @return
     */
    public Repor50 getNewRepor50() {
        return newRepor50;
    }

    /**
     *
     * @param newRepor50
     */
    public void setNewRepor50(Repor50 newRepor50) {
        this.newRepor50 = newRepor50;
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
    public Part getFilePart() {
        return filePart;
    }

    /**
     *
     * @param filePart
     */
    public void setFilePart(Part filePart) {
        this.filePart = filePart;
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public String register() throws IOException {
        try {

            if (filePart != null) {
                byte[] bytes = IOUtils.toByteArray(filePart.getInputStream());

                newRepor50.setCorep(bytes);
            }
            newRepor50.setStrep(Constants.RECORD_ACTIVED);
            newRepor50.setFerep(new Date());
            newRepor50.setUsrep(FacesUtil.getUserName());

            repor50Manager.registerRepor50(newRepor50);
            infoMessage = "Reporte guardado";

            System.out.println(newRepor50.getIdrep());

            newRepor50 = new Repor50();

            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Repor50AlreadyExists ex) {
            Logger.getLogger(Repor50AddBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "El reporte ya existe";
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
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
                Logger.getLogger(Repor50AddBacking.class.getName()).log(Level.SEVERE, null, ex);
                infoMessage = "No se puede guardar el reporte";
                FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
            }
        }

        return null;
    }

    /**
     *
     * @param ctx
     * @param comp
     * @param value
     */
    public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
        List<FacesMessage> msgs = new ArrayList<FacesMessage>();

        Part file = (Part) value;

        if (file.getSize() > 1048576) {
            msgs.add(new FacesMessage("El archivo no debe exceder 1 MB"));
        }

        if (!"application/pdf".equals(file.getContentType())) {
            msgs.add(new FacesMessage("El tipo de archivo debe ser PDF"));
        }

        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }
    }

}
