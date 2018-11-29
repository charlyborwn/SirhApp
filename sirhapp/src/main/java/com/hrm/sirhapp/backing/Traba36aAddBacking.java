package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Traba36a;
import com.hrm.sirhapp.service.Espec05ManagerLocal;
import com.hrm.sirhapp.service.Traba36aManagerLocal;
import com.hrm.sirhapp.service.exception.Traba36aAlreadyExists;
import com.hrm.sirhapp.util.FacesUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
public class Traba36aAddBacking extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Traba36aManagerLocal traba36aManager;
    @EJB
    private Espec05ManagerLocal espec05Manager;

    @Named
    @Produces
    @RequestScoped
    private final Traba36a newTraba36a = new Traba36a();

    private String imss;
    private String rfc;
    private String curp;
    private String ap;
    private String am;
    private String n;

    private String infoMessageModule;
    private String infoMessage;
    private boolean registered;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Aspirantes";
        this.registered = false;
    }

    /**
     *
     * @return
     */
    public Traba36aManagerLocal getTraba36aManager() {
        return traba36aManager;
    }

    /**
     *
     * @param traba36aManager
     */
    public void setTraba36aManager(Traba36aManagerLocal traba36aManager) {
        this.traba36aManager = traba36aManager;
    }

    /**
     *
     * @return
     */
    public String getRfc() {
        return rfc;
    }

    /**
     *
     * @param rfc
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     *
     * @return
     */
    public String getCurp() {
        return curp;
    }

    /**
     *
     * @param curp
     */
    public void setCurp(String curp) {
        this.curp = curp;
    }

    /**
     *
     * @return
     */
    public String getAp() {
        return ap;
    }

    /**
     *
     * @param ap
     */
    public void setAp(String ap) {
        System.out.println("se estableve valor de ape pat" + ap);
        newTraba36a.setAptraA(ap);
        this.ap = ap;
    }

    /**
     *
     * @return
     */
    public String getAm() {
        return am;
    }

    /**
     *
     * @param am
     */
    public void setAm(String am) {
        System.out.println("se estableve valor de ape mat" + am);
        this.am = am;
    }

    /**
     *
     * @return
     */
    public String getN() {
        return n;
    }

    /**
     *
     * @param n
     */
    public void setN(String n) {
        this.n = n;
    }

    /**
     *
     * @return
     */
    public String getImss() {
        return imss;
    }

    /**
     *
     * @param imss
     */
    public void setImss(String imss) {
        this.imss = imss;
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
     * @param query
     * @return
     */
    public List<String> especialidadLaboral(String query) {

        List<String> results = espec05Manager.getListEspec05Noesp(query);

        return results;
    }

    /**
     *
     * @return
     */
    public String register() {

        try {
            if (ap != null) {
                newTraba36a.setAptraA(ap);
            }
            if (am != null) {
                newTraba36a.setAmtraA(am);
            }
            if (n != null) {
                newTraba36a.setNotraA(n);
            }
            if (curp != null) {
                newTraba36a.setCutraA(curp);
            }
            if (rfc != null) {
                newTraba36a.setRftraA(rfc);
            }
            if (imss != null) {
                newTraba36a.setRitraA(imss);
            }

            newTraba36a.setSttraA(Constants.RECORD_ACTIVED);
            newTraba36a.setFetraA(new Date());
            newTraba36a.setUstraA(FacesUtil.getUserName());

            traba36aManager.registerTraba36a(newTraba36a);

            this.registered = true;
            infoMessage = "El registro se creo correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            return "success";

        } catch (Traba36aAlreadyExists ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        } catch (EJBException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error en la base de datos";
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);

        }

        return "failure";
    }
}
