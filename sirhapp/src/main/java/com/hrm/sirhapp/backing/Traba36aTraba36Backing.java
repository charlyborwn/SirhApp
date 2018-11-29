package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Benef26;
import com.hrm.sirhapp.model.Benef26a;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Datos28;
import com.hrm.sirhapp.model.Datos28a;
import com.hrm.sirhapp.model.Docum29;
import com.hrm.sirhapp.model.Docum29a;
import com.hrm.sirhapp.model.Escol30;
import com.hrm.sirhapp.model.Escol30a;
import com.hrm.sirhapp.model.Exper31;
import com.hrm.sirhapp.model.Exper31a;
import com.hrm.sirhapp.model.Famil32;
import com.hrm.sirhapp.model.Famil32a;
import com.hrm.sirhapp.model.Ident49;
import com.hrm.sirhapp.model.Ident49a;
import com.hrm.sirhapp.model.Requi54;
import com.hrm.sirhapp.model.Traba36;
import com.hrm.sirhapp.model.Traba36a;
import com.hrm.sirhapp.model.Usuar24;
import com.hrm.sirhapp.service.Benef26ManagerLocal;
import com.hrm.sirhapp.service.Benef26aManagerLocal;
import com.hrm.sirhapp.service.Datos28ManagerLocal;
import com.hrm.sirhapp.service.Datos28aManagerLocal;
import com.hrm.sirhapp.service.Docum29ManagerLocal;
import com.hrm.sirhapp.service.Docum29aManagerLocal;
import com.hrm.sirhapp.service.Escol30ManagerLocal;
import com.hrm.sirhapp.service.Escol30aManagerLocal;
import com.hrm.sirhapp.service.Exper31ManagerLocal;
import com.hrm.sirhapp.service.Exper31aManagerLocal;
import com.hrm.sirhapp.service.Famil32ManagerLocal;
import com.hrm.sirhapp.service.Famil32aManagerLocal;
import com.hrm.sirhapp.service.Ident49ManagerLocal;
import com.hrm.sirhapp.service.Ident49aManagerLocal;
import com.hrm.sirhapp.service.Requi54ManagerLocal;
import com.hrm.sirhapp.service.Traba36ManagerLocal;
import com.hrm.sirhapp.service.Traba36aManagerLocal;
import com.hrm.sirhapp.service.exception.Benef26AlreadyExists;
import com.hrm.sirhapp.service.exception.Benef26aNotFound;
import com.hrm.sirhapp.service.exception.Datos28AlreadyExists;
import com.hrm.sirhapp.service.exception.Datos28aNotFound;
import com.hrm.sirhapp.service.exception.Docum29AlreadyExists;
import com.hrm.sirhapp.service.exception.Docum29aNotFound;
import com.hrm.sirhapp.service.exception.Escol30AlreadyExists;
import com.hrm.sirhapp.service.exception.Escol30aNotFound;
import com.hrm.sirhapp.service.exception.Exper31AlreadyExists;
import com.hrm.sirhapp.service.exception.Exper31aNotFound;
import com.hrm.sirhapp.service.exception.Famil32AlreadyExists;
import com.hrm.sirhapp.service.exception.Famil32aNotFound;
import com.hrm.sirhapp.service.exception.Ident49AlreadyExists;
import com.hrm.sirhapp.service.exception.Ident49aNotFound;
import com.hrm.sirhapp.service.exception.Requi54NotFound;
import com.hrm.sirhapp.service.exception.Traba36AlreadyExists;
import com.hrm.sirhapp.service.exception.Traba36NotFound;
import com.hrm.sirhapp.service.exception.Traba36aNotFound;
import com.hrm.sirhapp.util.FacesUtil;
import com.hrm.sirhapp.util.LanguagesUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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
public class Traba36aTraba36Backing extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Requi54ManagerLocal requi54Manager;

    @EJB
    private Traba36aManagerLocal traba36aManager;
    @EJB
    private Traba36ManagerLocal traba36Manager;

    @EJB
    private Datos28aManagerLocal datos28aManager;
    @EJB
    private Datos28ManagerLocal datos28Manager;

    @EJB
    private Ident49aManagerLocal ident49aManager;
    @EJB
    private Ident49ManagerLocal ident49Manager;

    @EJB
    private Benef26aManagerLocal benef26aManager;
    @EJB
    private Benef26ManagerLocal benef26Manager;

    @EJB
    private Docum29aManagerLocal docum29aManager;
    @EJB
    private Docum29ManagerLocal docum29Manager;

    @EJB
    private Escol30aManagerLocal escol30aManager;
    @EJB
    private Escol30ManagerLocal escol30Manager;

    @EJB
    private Exper31aManagerLocal exper31aManager;
    @EJB
    private Exper31ManagerLocal exper31Manager;

    @EJB
    private Famil32aManagerLocal famil32aManager;
    @EJB
    private Famil32ManagerLocal famil32Manager;

    @Named
    @Produces
    @RequestScoped
    private Traba36a selectedTraba36aTraba36;

    private Traba36 selectedTraba36;

    private Datos28a selectedDatos28a;
    private Ident49a selectedIdent49a;
    private List<Benef26a> benef26aList;
    private List<Docum29a> docum29aList;
    private List<Escol30a> escol30aList;
    private List<Exper31a> exper31aList;
    private List<Famil32a> famil32aList;

    private List<Traba36> traba36List;
    private List<Traba36a> traba36aList;
    private String rfc;
    private String rfcSearch;
    private String curp;
    private String imss;
    private String ap;
    private String am;
    private String n;

    private Date fitra;
    private Integer nutra;
    private String sitra;
    private String eitra;
    private String ditra;

    private String userName;

    private Date newDate;

    private String infoMessageModule;
    private String infoMessage;
    private String infoMessageTra;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Proceso Aspirantes a Trabajadores";

        userName = FacesUtil.getUserName();
    }

    /**
     *
     * @return
     */
    public Traba36 getSelectedTraba36() {
        return selectedTraba36;
    }

    /**
     *
     * @param selectedTraba36
     */
    public void setSelectedTraba36(Traba36 selectedTraba36) {
        this.selectedTraba36 = selectedTraba36;
    }

    /**
     *
     * @return
     */
    public Datos28a getSelectedDatos28a() {
        return selectedDatos28a;
    }

    /**
     *
     * @param selectedDatos28a
     */
    public void setSelectedDatos28a(Datos28a selectedDatos28a) {
        this.selectedDatos28a = selectedDatos28a;
    }

    /**
     *
     * @return
     */
    public Ident49a getSelectedIdent49a() {
        return selectedIdent49a;
    }

    /**
     *
     * @param selectedIdent49a
     */
    public void setSelectedIdent49a(Ident49a selectedIdent49a) {
        this.selectedIdent49a = selectedIdent49a;
    }

    /**
     *
     * @return
     */
    public List<Traba36> getTraba36List() {
        return traba36List;
    }

    /**
     *
     * @param traba36List
     */
    public void setTraba36List(List<Traba36> traba36List) {
        this.traba36List = traba36List;
    }

    /**
     *
     * @return
     */
    public String getInfoMessageTra() {
        return infoMessageTra;
    }

    /**
     *
     * @param infoMessageTra
     */
    public void setInfoMessageTra(String infoMessageTra) {
        this.infoMessageTra = infoMessageTra;
    }

    /**
     *
     * @return
     */
    public String getRfcSearch() {
        return rfcSearch;
    }

    /**
     *
     * @param rfcSearch
     */
    public void setRfcSearch(String rfcSearch) {
        this.rfcSearch = rfcSearch;
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
    public Date getFitra() {
        return fitra;
    }

    /**
     *
     * @param fitra
     */
    public void setFitra(Date fitra) {
        this.fitra = fitra;
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
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return
     */
    public Integer getNutra() {
        return nutra;
    }

    /**
     *
     * @param nutra
     */
    public void setNutra(Integer nutra) {
        this.nutra = nutra;
    }

    /**
     *
     * @return
     */
    public String getSitra() {
        return sitra;
    }

    /**
     *
     * @param sitra
     */
    public void setSitra(String sitra) {
        this.sitra = sitra;
    }

    /**
     *
     * @return
     */
    public String getEitra() {
        return eitra;
    }

    /**
     *
     * @param eitra
     */
    public void setEitra(String eitra) {
        this.eitra = eitra;
    }

    /**
     *
     * @return
     */
    public String getDitra() {
        return ditra;
    }

    /**
     *
     * @param ditra
     */
    public void setDitra(String ditra) {
        this.ditra = ditra;
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
    public List<Traba36a> getTraba36aList() {
        return traba36aList;
    }

    /**
     *
     * @param traba36aList
     */
    public void setTraba36aRequestList(List<Traba36a> traba36aList) {
        this.traba36aList = traba36aList;
    }

    /**
     *
     * @return
     */
    public Traba36a getSelectedTraba36aTraba36() {
        return selectedTraba36aTraba36;
    }

    /**
     *
     * @param selectedTraba36aTraba36
     */
    public void setSelectedTraba36aTraba36(Traba36a selectedTraba36aTraba36) {
        this.selectedTraba36aTraba36 = selectedTraba36aTraba36;
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
    public String getAp() {
        return ap;
    }

    /**
     *
     * @param ap
     */
    public void setAp(String ap) {
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
    public List<Benef26a> getBenef26aList() {
        return benef26aList;
    }

    /**
     *
     * @param benef26aList
     */
    public void setBenef26aList(List<Benef26a> benef26aList) {
        this.benef26aList = benef26aList;
    }

    /**
     *
     * @return
     */
    public List<Docum29a> getDocum29aList() {
        return docum29aList;
    }

    /**
     *
     * @param docum29aList
     */
    public void setDocum29aList(List<Docum29a> docum29aList) {
        this.docum29aList = docum29aList;
    }

    /**
     *
     * @return
     */
    public List<Escol30a> getEscol30aList() {
        return escol30aList;
    }

    /**
     *
     * @param escol30aList
     */
    public void setEscol30aList(List<Escol30a> escol30aList) {
        this.escol30aList = escol30aList;
    }

    /**
     *
     * @return
     */
    public List<Exper31a> getExper31aList() {
        return exper31aList;
    }

    /**
     *
     * @param exper31aList
     */
    public void setExper31aList(List<Exper31a> exper31aList) {
        this.exper31aList = exper31aList;
    }

    /**
     *
     * @return
     */
    public List<Famil32a> getFamil32aList() {
        return famil32aList;
    }

    /**
     *
     * @param famil32aList
     */
    public void setFamil32aList(List<Famil32a> famil32aList) {
        this.famil32aList = famil32aList;
    }

    /**
     *
     * @return
     */
    public Date getNewDate() {
        return newDate;
    }

    /**
     *
     * @param newDate
     */
    public void setNewDate(Date newDate) {
        this.newDate = newDate;
    }

    /**
     *
     * @param rftraA
     * @return
     */
    public Traba36a getTraba36aRequest(String rftraA) {

        try {

            this.selectedTraba36aTraba36 = traba36aManager.getTraba36a(rftraA);

        } catch (Traba36aNotFound ex) {
            Logger.getLogger(Traba36aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("reports.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return selectedTraba36aTraba36;
    }

    /**
     *
     * @param rfdatA
     * @return
     */
    public Datos28a getDatos28aRequest(String rfdatA) {

        try {

            this.selectedDatos28a = datos28aManager.getDatos28a(rfdatA);

        } catch (Datos28aNotFound ex) {
            Logger.getLogger(Datos28aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedDatos28a;
    }

    /**
     *
     * @param rfideA
     * @return
     */
    public Ident49a getIdent49aRequest(String rfideA) {

        try {

            this.selectedIdent49a = ident49aManager.getIdent49a(rfideA);

        } catch (Ident49aNotFound ex) {
            Logger.getLogger(Ident49aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedIdent49a;
    }

    /**
     *
     * @param rfideA
     * @return
     */
    public List<Benef26a> getBenef26aRequest(String rfideA) {

        try {

            this.benef26aList = benef26aManager.getListBenef26a(rfideA);

        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return benef26aList;
    }

    /**
     *
     * @param rfideA
     * @return
     */
    public List<Docum29a> getDocum29aRequest(String rfideA) {

        try {

            this.docum29aList = docum29aManager.getListDocum29a(rfideA);

        } catch (Exception ex) {
            Logger.getLogger(Docum29aBacking.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return docum29aList;
    }

    /**
     *
     * @param rfideA
     * @return
     */
    public List<Escol30a> getEscol30aRequest(String rfideA) {

        try {

            this.escol30aList = escol30aManager.getListEscol30a(rfideA);

        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return escol30aList;
    }

    /**
     *
     * @param rfideA
     * @return
     */
    public List<Exper31a> getExper31aRequest(String rfideA) {

        try {

            this.exper31aList = exper31aManager.getListExper31a(rfideA);

        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return exper31aList;
    }

    /**
     *
     * @param rfideA
     * @return
     */
    public List<Famil32a> getFamil32aRequest(String rfideA) {

        try {

            this.famil32aList = famil32aManager.getListFamil32a(rfideA);

        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return famil32aList;
    }

    /**
     *
     */
    public void aspiranteTrabajador() {

        try {
            if (traba36Manager.getTraba36(nutra) != null) {
                infoMessage = "El numero de trabajador que intenta registrar ya existe.";
                FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
            }
        } catch (Traba36NotFound e) {

            try {
                this.newDate = new Date();
                aspiranteTrabajadorTraba36();
                aspitanteTrabajadorDatos28();
                aspiranteTrabajadorIdent49();
                aspiranteTrabajadorBenef26();
                aspiranteTrabajadorDocum29();
                aspiranteTrabajadorEscol30();
                aspiranteTrabajadorExper31();
                aspiranteTrabajadorFamil32();

                this.newDate = null;
                if (selectedTraba36aTraba36 != null) {
                    removeTraba36a();
                }
                if (selectedDatos28a != null) {
                    removeDatos28a();
                }
                if (selectedIdent49a != null) {
                    removeIdent49a();
                }

                //removeRequi54();
                infoMessage = "El aspirante fue convertido a trabajador.";
                FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            } catch (Ident49AlreadyExists | Datos28AlreadyExists | Traba36AlreadyExists | Benef26AlreadyExists | Docum29AlreadyExists | Escol30AlreadyExists | Exper31AlreadyExists | Famil32AlreadyExists exception) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, exception);

            }
        }
    }

    /**
     *
     * @throws Traba36AlreadyExists
     */
    public void aspiranteTrabajadorTraba36() throws Traba36AlreadyExists {

        Traba36 newTraba36 = new Traba36();
        if (selectedTraba36aTraba36 != null) {
            newTraba36.setNutra(nutra);
            newTraba36.setRftra(selectedTraba36aTraba36.getRftraA());
            newTraba36.setFxtra(selectedTraba36aTraba36.getFxtraA());
            newTraba36.setCutra(selectedTraba36aTraba36.getCutraA());
            newTraba36.setRitra(selectedTraba36aTraba36.getRitraA());
            newTraba36.setCctra(selectedTraba36aTraba36.getCctraA());
            newTraba36.setAptra(selectedTraba36aTraba36.getAptraA());
            newTraba36.setAmtra(selectedTraba36aTraba36.getAmtraA());
            newTraba36.setNotra(selectedTraba36aTraba36.getNotraA());
            newTraba36.setPatra(selectedTraba36aTraba36.getPatraA());
            newTraba36.setFntra(selectedTraba36aTraba36.getFntraA());
            newTraba36.setSetra(selectedTraba36aTraba36.getSetraA());
            newTraba36.setNatra(selectedTraba36aTraba36.getNatraA());
            newTraba36.setEstra(selectedTraba36aTraba36.getEstraA());
            newTraba36.setSitra(sitra);
            newTraba36.setEitra(eitra);
            newTraba36.setDitra(ditra);
            newTraba36.setOetra(selectedTraba36aTraba36.getOetraA());
            newTraba36.setEntra(selectedTraba36aTraba36.getEntraA());
            newTraba36.setRetra(selectedTraba36aTraba36.getRetraA());
            newTraba36.setExtra(selectedTraba36aTraba36.getExtraA());
            newTraba36.setRxtra(selectedTraba36aTraba36.getRxtraA());
            newTraba36.setOdtra(selectedTraba36aTraba36.getOdtraA());
            newTraba36.setOctra(selectedTraba36aTraba36.getOctraA());
            newTraba36.setPrtra(fitra);

            newTraba36.setSatra(sitra);
            newTraba36.setEatra(eitra);
            newTraba36.setDatra(ditra);

            newTraba36.setSstra("VIABLE");
            newTraba36.setFstra(fitra);
            newTraba36.setFitra(fitra);

            newTraba36.setFptra(null);
            newTraba36.setFutra(null);
            newTraba36.setFrtra(null);

            newTraba36.setSttra(Constants.RECORD_ACTIVED);
            newTraba36.setFetra(newDate);
            newTraba36.setUstra(userName);

            traba36Manager.registerTraba36(newTraba36);
        }
    }

    /**
     *
     * @throws Datos28AlreadyExists
     */
    public void aspitanteTrabajadorDatos28() throws Datos28AlreadyExists {
        getDatos28aRequest(selectedTraba36aTraba36.getRftraA());
        Datos28 newDatos28 = new Datos28();
        if (selectedDatos28a != null) {
            newDatos28.setNtdat(nutra);
            newDatos28.setRfdat(selectedDatos28a.getRfdatA());
            newDatos28.setPadat(selectedDatos28a.getPadatA());
            newDatos28.setEsdat(selectedDatos28a.getEsdatA());
            newDatos28.setMddat(selectedDatos28a.getMddatA());
            newDatos28.setLodat(selectedDatos28a.getLodatA());
            newDatos28.setCodat(selectedDatos28a.getCodatA());
            newDatos28.setCadat(selectedDatos28a.getCadatA());
            newDatos28.setNedat(selectedDatos28a.getNedatA());
            newDatos28.setNidat(selectedDatos28a.getNidatA());
            newDatos28.setCpdat(selectedDatos28a.getCpdatA());
            newDatos28.setR1dat(selectedDatos28a.getR1datA());
            newDatos28.setR2dat(selectedDatos28a.getR2datA());
            newDatos28.setThdat(selectedDatos28a.getThdatA());
            newDatos28.setTddat(selectedDatos28a.getTddatA());
            newDatos28.setTcdat(selectedDatos28a.getTcdatA());
            newDatos28.setCedat(selectedDatos28a.getCedatA());
            newDatos28.setPndat(selectedDatos28a.getPndatA());
            newDatos28.setEndat(selectedDatos28a.getEndatA());
            newDatos28.setMndat(selectedDatos28a.getMndatA());
            newDatos28.setLndat(selectedDatos28a.getLndatA());
            newDatos28.setTpdat(selectedDatos28a.getTpdatA());
            newDatos28.setPrdat(selectedDatos28a.getPrdatA());
            newDatos28.setF1dat(selectedDatos28a.getF1datA());
            newDatos28.setF2dat(selectedDatos28a.getF2datA());
            newDatos28.setF3dat(selectedDatos28a.getF3datA());
            newDatos28.setStdat(Constants.RECORD_ACTIVED);
            newDatos28.setFedat(newDate);
            newDatos28.setUsdat(userName);
            datos28Manager.registerDatos28(newDatos28);
        }
    }

    /**
     *
     * @throws Ident49AlreadyExists
     */
    public void aspiranteTrabajadorIdent49() throws Ident49AlreadyExists {
        getIdent49aRequest(selectedTraba36aTraba36.getRftraA());
        Ident49 newIdent49 = new Ident49();
        if (selectedIdent49a != null) {
            newIdent49.setNtide(nutra);
            newIdent49.setRfide(selectedIdent49a.getRfideA());
            newIdent49.setRiide(selectedIdent49a.getRiideA());
            newIdent49.setUiide(selectedIdent49a.getUiideA());
            newIdent49.setCeide(selectedIdent49a.getCeideA());
            newIdent49.setEeide(selectedIdent49a.getEeideA());
            newIdent49.setMeide(selectedIdent49a.getMeideA());
            newIdent49.setLeide(selectedIdent49a.getLeideA());
            newIdent49.setSiide(selectedIdent49a.getSiideA());
            newIdent49.setEmide(selectedIdent49a.getEmideA());
            newIdent49.setVeide(selectedIdent49a.getVeideA());
            newIdent49.setNeide(selectedIdent49a.getNeideA());
            newIdent49.setRgide(selectedIdent49a.getRgideA());
            newIdent49.setEcide(selectedIdent49a.getEcideA());
            newIdent49.setTside(selectedIdent49a.getTsideA());
            newIdent49.setDside(selectedIdent49a.getDsideA());
            newIdent49.setFdide(selectedIdent49a.getFdideA());
            newIdent49.setDoide(selectedIdent49a.getDoideA());
            newIdent49.setAlide(selectedIdent49a.getAlideA());
            newIdent49.setKgide(selectedIdent49a.getKgideA());
            newIdent49.setFkide(selectedIdent49a.getFkideA());
            newIdent49.setEside(selectedIdent49a.getEsideA());
            newIdent49.setFmide(selectedIdent49a.getFmideA());
            newIdent49.setCcide(selectedIdent49a.getCcideA());
            newIdent49.setPpide(selectedIdent49a.getPpideA());
            newIdent49.setZaide(selectedIdent49a.getZaideA());
            newIdent49.setNlide(selectedIdent49a.getNlideA());
            newIdent49.setVlide(selectedIdent49a.getVlideA());
            newIdent49.setElide(selectedIdent49a.getElideA());
            newIdent49.setApide(selectedIdent49a.getApideA());
            newIdent49.setMtide(selectedIdent49a.getMtideA());
            newIdent49.setOtide(selectedIdent49a.getOtideA());
            newIdent49.setAoide(selectedIdent49a.getAoideA());
            newIdent49.setPlide(selectedIdent49a.getPlideA());
            newIdent49.setIiide(selectedIdent49a.getIiideA());
            newIdent49.setIfide(selectedIdent49a.getIfideA());
            newIdent49.setOlide(selectedIdent49a.getOlideA());
            newIdent49.setIoide(selectedIdent49a.getIoideA());
            newIdent49.setA1ide(selectedIdent49a.getA1ideA());
            newIdent49.setA2ide(selectedIdent49a.getA2ideA());
            newIdent49.setStide(selectedIdent49a.getStideA());
            newIdent49.setFeide(selectedIdent49a.getFeideA());
            newIdent49.setUside(selectedIdent49a.getUsideA());
            newIdent49.setStide(Constants.RECORD_ACTIVED);
            newIdent49.setFeide(newDate);
            newIdent49.setUside(userName);
            ident49Manager.registerIdent49(newIdent49);
        }
    }

    /**
     *
     * @throws Benef26AlreadyExists
     */
    public void aspiranteTrabajadorBenef26() throws Benef26AlreadyExists {

        getBenef26aRequest(selectedTraba36aTraba36.getRftraA());
        Benef26 newBenef26 = new Benef26();

        if (benef26aList != null) {

            for (Benef26a e : benef26aList) {

                newBenef26.setNtben(nutra);
                newBenef26.setTpben(e.getTpbenA());
                newBenef26.setApben(e.getApbenA());
                newBenef26.setAmben(e.getAmbenA());
                newBenef26.setNoben(e.getNobenA());
                newBenef26.setFnben(e.getFnbenA());
                newBenef26.setSeben(e.getSebenA());
                newBenef26.setPoben(e.getPobenA());
                newBenef26.setObben(e.getObbenA());
                newBenef26.setStben(Constants.RECORD_ACTIVED);
                newBenef26.setFeben(newDate);
                newBenef26.setUsben(userName);
                benef26Manager.registerBenef26(newBenef26);
                removeBenef26a(e);
                System.out.println("Lista de beneficiarios del aspirante con RFC" + e.getRfbenA());
            }
        }
    }

    /**
     *
     * @throws Docum29AlreadyExists
     */
    public void aspiranteTrabajadorDocum29() throws Docum29AlreadyExists {

        getDocum29aRequest(selectedTraba36aTraba36.getRftraA());
        Docum29 newDocum29 = new Docum29();

        if (docum29aList != null) {

            for (Docum29a e : docum29aList) {

                newDocum29.setNtdoc(nutra);
                newDocum29.setTddoc(e.getTddocA());
                newDocum29.setW1doc(e.getW1docA());
                newDocum29.setCadoc(e.getCadocA());
                newDocum29.setDedoc(e.getDedocA());
                newDocum29.setIndoc(e.getIndocA());
                newDocum29.setTedoc(e.getTedocA());
                newDocum29.setPadoc(e.getPadocA());
                newDocum29.setObdoc(e.getObdocA());
                newDocum29.setStdoc(Constants.RECORD_ACTIVED);
                newDocum29.setFedoc(newDate);
                newDocum29.setUsdoc(userName);
                docum29Manager.registerDocum29(newDocum29);
                removeDocum29a(e);
                System.out.println("Lista de documentos del aspirante con RFC " + e.getRfdocA());
            }

        }

    }

    /**
     *
     * @throws Escol30AlreadyExists
     */
    public void aspiranteTrabajadorEscol30() throws Escol30AlreadyExists {

        getEscol30aRequest(selectedTraba36aTraba36.getRftraA());
        Escol30 newEscol30 = new Escol30();

        if (escol30aList != null) {

            for (Escol30a e : escol30aList) {

                newEscol30.setNtesc(nutra);
                newEscol30.setTnesc(e.getTnescA());
                newEscol30.setNiesc(e.getNiescA());
                newEscol30.setAcesc(e.getAcescA());
                newEscol30.setCaesc(e.getCaescA());
                newEscol30.setHcesc(e.getHcescA());
                newEscol30.setCeesc(e.getCeescA());
                newEscol30.setEsesc(e.getEsescA());
                newEscol30.setTdesc(e.getTdescA());
                newEscol30.setW1esc(e.getW1escA());
                newEscol30.setPaesc(e.getPaescA());
                newEscol30.setObesc(e.getObescA());
                newEscol30.setStesc(Constants.RECORD_ACTIVED);
                newEscol30.setFeesc(newDate);
                newEscol30.setUsesc(userName);
                escol30Manager.registerEscol30(newEscol30);
                removeEscol30a(e);
                System.out.println("Lista de escolaridad del aspirante con RFC " + e.getRfescA());
            }
        }
    }

    /**
     *
     * @throws Exper31AlreadyExists
     */
    public void aspiranteTrabajadorExper31() throws Exper31AlreadyExists {

        getExper31aRequest(selectedTraba36aTraba36.getRftraA());
        Exper31 newExper31 = new Exper31();

        if (exper31aList != null) {

            for (Exper31a e : exper31aList) {

                newExper31.setNtexp(nutra);
                newExper31.setEmexp(e.getEmexpA());
                newExper31.setCeexp(e.getCeexpA());
                newExper31.setInexp(e.getInexpA());
                newExper31.setTeexp(e.getTeexpA());
                newExper31.setCsexp(e.getCsexpA());
                newExper31.setSxexp(e.getSxexpA());
                newExper31.setSiexp(e.getSiexpA());
                newExper31.setPaexp(e.getPaexpA());
                newExper31.setEsexp(e.getEsexpA());
                newExper31.setMdexp(e.getMdexpA());
                newExper31.setLoexp(e.getLoexpA());
                newExper31.setCoexp(e.getCoexpA());
                newExper31.setCaexp(e.getCaexpA());
                newExper31.setCpexp(e.getCpexpA());
                newExper31.setTdexp(e.getTdexpA());
                newExper31.setJiexp(e.getJiexpA());
                newExper31.setCjexp(e.getCjexpA());
                newExper31.setTcexp(e.getTcexpA());
                newExper31.setMaexp(e.getMaexpA());
                newExper31.setTiexp(e.getTiexpA());
                newExper31.setW1exp(e.getW1expA());
                newExper31.setPtexp(e.getPtexpA());
                newExper31.setObexp(e.getObexpA());
                newExper31.setStexp(Constants.RECORD_ACTIVED);
                newExper31.setFeexp(newDate);
                newExper31.setUsexp(userName);
                exper31Manager.registerExper31(newExper31);
                removeExper31a(e);
                System.out.println("Lista de experiencia laboral del aspirante con RFC " + e.getRfexpA());
            }
        }
    }

    /**
     *
     * @throws Famil32AlreadyExists
     */
    public void aspiranteTrabajadorFamil32() throws Famil32AlreadyExists {

        getFamil32aRequest(selectedTraba36aTraba36.getRftraA());
        Famil32 newFamil32 = new Famil32();

        if (famil32aList != null) {

            for (Famil32a e : famil32aList) {

                newFamil32.setNtfam(nutra);
                newFamil32.setTpfam(e.getTpfamA());
                newFamil32.setW1fam(e.getW1famA());
                newFamil32.setOrfam(e.getOrfamA());
                newFamil32.setApfam(e.getApfamA());
                newFamil32.setAmfam(e.getAmfamA());
                newFamil32.setNofam(e.getNofamA());
                newFamil32.setVifam(e.getVifamA());
                newFamil32.setSefam(e.getSefamA());
                newFamil32.setFnfam(e.getFnfamA());
                newFamil32.setOffam(e.getOffamA());
                newFamil32.setVmfam(e.getVmfamA());
                newFamil32.setPafam(e.getPafamA());
                newFamil32.setEsfam(e.getEsfamA());
                newFamil32.setMdfam(e.getMdfamA());
                newFamil32.setLofam(e.getLofamA());
                newFamil32.setCofam(e.getCofamA());
                newFamil32.setCafam(e.getCafamA());
                newFamil32.setCpfam(e.getCpfamA());
                newFamil32.setTdfam(e.getTdfamA());
                newFamil32.setTcfam(e.getTcfamA());
                newFamil32.setCefam(e.getCefamA());
                newFamil32.setTifam(e.getTifamA());
                newFamil32.setW2fam(e.getW2famA());
                newFamil32.setPtfam(e.getPtfamA());
                newFamil32.setObfam(e.getObfamA());
                newFamil32.setStfam(e.getStfamA());
                newFamil32.setFefam(e.getFefamA());
                newFamil32.setUsfam(e.getUsfamA());
                newFamil32.setStfam(Constants.RECORD_ACTIVED);
                newFamil32.setFefam(newDate);
                newFamil32.setUsfam(userName);
                famil32Manager.registerFamil32(newFamil32);
                removeFamil32a(e);
                System.out.println("Lista de familiares del aspirante con RFC " + e.getRffamA());
            }
        }
    }

    /**
     *
     * @return
     */
    public String retrieveTraba36aRfcList() {
        Traba36a searchableTraba36a = new Traba36a();

        if (rfc != null) {
            searchableTraba36a.setRftraA(rfc);
        }

        traba36aList = traba36aManager.getListTraba36a(rfc);

        if (traba36aList.isEmpty()) {
            infoMessage = "No se encontraron registros.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } else {
            infoMessage = traba36aList.size() + " registros encontrados.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        }

        return null;
    }

    /**
     *
     * @return
     */
    public String retrieveTraba36aNcList() {
        Traba36a searchableTraba36a = new Traba36a();

        if (ap != null && ap.length() > 0) {
            searchableTraba36a.setAptraA(ap.toUpperCase());
        }
        if (am != null && am.length() > 0) {
            searchableTraba36a.setAmtraA(am.toUpperCase());
        }
        if (n != null && n.length() > 0) {
            searchableTraba36a.setNotraA(n.toUpperCase());
        }

        //System.out.println("Paterno" + searchableTraba36a.getAptraA());
        //System.out.println("Materno" + searchableTraba36a.getAmtraA());
        //System.out.println("Nombres" + searchableTraba36a.getNotraA());
        traba36aList = traba36aManager.getListTraba36a(searchableTraba36a);

        if (traba36aList.isEmpty()) {
            infoMessage = "No se encontraron registros.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } else {
            infoMessage = traba36aList.size() + " registros encontrados.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        }

        return null;
    }

    /**
     *
     * @return
     */
    public String removeTraba36a() {
        try {

            traba36aManager.removeTraba36a(selectedTraba36aTraba36);
            return "Successful";

        } catch (Traba36aNotFound ex) {
            return "Sucessful";
        }
    }

    /**
     *
     * @return
     */
    public String removeDatos28a() {
        try {

            datos28aManager.removeDatos28a(selectedDatos28a);

            return "Successful";

        } catch (Datos28aNotFound ex) {
            return "Sucessful";
        }
    }

    /**
     *
     * @return
     */
    public String removeIdent49a() {
        try {

            ident49aManager.removeIdent49a(selectedIdent49a);
            return "Sucessful";

        } catch (Ident49aNotFound ex) {
            return "Sucessful";
        }
    }

    /**
     *
     * @return
     */
    public String removeRequi54() {
        try {

            Requi54 requi54 = requi54Manager.getRequi54(rfc);
            requi54Manager.removeRequi54(requi54);
            return "Sucessful";

        } catch (Requi54NotFound ex) {
            return "Sucessful";
        }
    }

    /**
     *
     * @param selectedBenef26a
     * @return
     */
    public String removeBenef26a(Benef26a selectedBenef26a) {
        try {

            benef26aManager.removeBenef26a(selectedBenef26a);
            return "Sucessful";

        } catch (Benef26aNotFound ex) {
            return "Sucessful";
        }
    }

    /**
     *
     * @param selectedDocum29a
     * @return
     */
    public String removeDocum29a(Docum29a selectedDocum29a) {
        try {

            docum29aManager.removeDocum29a(selectedDocum29a);

            return "Sucessful";

        } catch (Docum29aNotFound ex) {
            return "Sucessful";
        }
    }

    /**
     *
     * @param selectedEscol30a
     * @return
     */
    public String removeEscol30a(Escol30a selectedEscol30a) {
        try {

            escol30aManager.removeEscol30a(selectedEscol30a);
            return "Sucessful";

        } catch (Escol30aNotFound ex) {
            return "Sucessful";
        }
    }

    /**
     *
     * @param selectedExper31a
     * @return
     */
    public String removeExper31a(Exper31a selectedExper31a) {
        try {

            exper31aManager.removeExper31a(selectedExper31a);
            return "Sucessful";

        } catch (Exper31aNotFound ex) {
            return "Sucessful";
        }
    }

    /**
     *
     * @param selectedFamil32a
     * @return
     */
    public String removeFamil32a(Famil32a selectedFamil32a) {
        try {

            famil32aManager.removeFamil32a(selectedFamil32a);
            return "Sucessful";

        } catch (Famil32aNotFound ex) {
            return "Sucessful";
        }
    }

    /**
     *
     * @param action
     * @return
     * @throws IOException
     */
    public String go(int action) throws IOException {

        String outcome = "/secured/procesos/find.xhtml?faces-redirect=true";

        if (action == 2) {
            outcome = "/secured/trabajadores/datosGenerales.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

    /**
     *
     * @param vce
     */
    public void handleChange(AjaxBehaviorEvent vce) {

        String value = (String) ((ValueHolder) vce.getSource()).getValue();
        String id = ((UIComponent) vce.getSource()).getId();
        System.out.println("id:" + id + " rfc:" + value);
        switch (id) {
            case "rfcSearch":
                rfcSearch = value;
                if (rfcSearch != null) {
                    retrieveTraba36aListTraba36List();
                }
                break;
            default:
                break;
        }
    }

    /**
     *
     * @return
     */
    public String retrieveTraba36aListTraba36List() {
        selectedTraba36aTraba36 = null;
        traba36aList = null;

        retrieveTraba36aList();
        retrieveTraba36List();

        if (traba36aList.size() > 0) {
            selectedTraba36aTraba36 = traba36aList.get(0);

            this.rfc = selectedTraba36aTraba36.getRftraA();
            this.rfc = rfc.toUpperCase();

        } else {
            selectedTraba36aTraba36 = null;
            infoMessage = "EL ASPIRANTE NO ESTA REGISTRADO";
        }

        return "resultadosBuscar";
    }

    /**
     *
     */
    public void retrieveTraba36List() {
        try {
            Boolean ex = false;
            Traba36 searchableTraba36 = new Traba36();
            List<Traba36> traba36ListCurp = new ArrayList<Traba36>();
            List<Traba36> traba36ListRfc = new ArrayList<Traba36>();
            List<Traba36> traba36ListNc = new ArrayList<Traba36>();
            List<Traba36> traba36ListImss = new ArrayList<Traba36>();

            HashMap<Integer, Traba36> map = new HashMap<Integer, Traba36>();

            Usuar24 usuar24 = FacesUtil.getUsuar24();

            if (imss != null && imss.length()
                    > 0) {
                searchableTraba36.setRitra(imss);
                traba36ListImss = traba36Manager.getListTraba36Wiz(searchableTraba36);
                System.out.println("Imss" + searchableTraba36.getRitra());
            }

            if (rfcSearch
                    != null && rfcSearch.length()
                    > 0) {
                searchableTraba36.setRftra(rfcSearch.toUpperCase());
                traba36ListRfc = traba36Manager.getListTraba36Wiz(searchableTraba36);
                System.out.println("Rfc" + searchableTraba36.getRftra());
            }
            if (curp
                    != null && curp.length()
                    > 0) {
                searchableTraba36.setRftra(null);
                searchableTraba36.setCutra(curp.toUpperCase());
                traba36ListCurp = traba36Manager.getListTraba36Wiz(searchableTraba36);
                System.out.println("Curp" + searchableTraba36.getCutra());
            }
            if (ap
                    != null && ap.length()
                    > 0) {
                searchableTraba36.setAptra(ap.toUpperCase());
                ex = true;
            }
            if (am
                    != null && am.length()
                    > 0) {
                searchableTraba36.setAmtra(am.toUpperCase());
                ex = true;
            }
            if (n
                    != null && n.length()
                    > 0) {
                searchableTraba36.setNotra(n.toUpperCase());
                ex = true;
            }

            if (ex) {
                searchableTraba36.setRftra(null);
                searchableTraba36.setCutra(null);

                System.out.println("Paterno" + searchableTraba36.getAptra());
                System.out.println("Materno" + searchableTraba36.getAmtra());
                System.out.println("Nombres" + searchableTraba36.getNotra());

                traba36ListNc = traba36Manager.getListTraba36Wiz(searchableTraba36);
            }

            traba36List = new ArrayList<Traba36>();

            System.out.println("Lista Imss" + traba36ListImss.size());

            if (!traba36ListImss.isEmpty()) {
                for (Traba36 traba36 : traba36ListImss) {
                    System.out.println(traba36.toString());
                    map.put(traba36.getNutra(), traba36);
                }
            }

            if (!traba36ListCurp.isEmpty()) {
                for (Traba36 traba36 : traba36ListCurp) {
                    map.put(traba36.getNutra(), traba36);
                }
            }

            if (!traba36ListRfc.isEmpty()) {
                for (Traba36 traba36 : traba36ListRfc) {
                    map.put(traba36.getNutra(), traba36);
                }
            }

            if (!traba36ListNc.isEmpty()) {
                for (Traba36 traba36 : traba36ListNc) {
                    map.put(traba36.getNutra(), traba36);
                }
            }

            traba36List = new ArrayList(map.values());

            if (!traba36List.isEmpty()) {
                infoMessageTra = traba36List.size() + " Trabajadores";
            } else {
                infoMessageTra = "";
            }

        } catch (SecurityException | IllegalArgumentException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public void retrieveTraba36aList() {
        try {
            Boolean ex = false;
            Traba36a searchableTraba36a = new Traba36a();
            List<Traba36a> traba36aListCurp = new ArrayList<Traba36a>();
            List<Traba36a> traba36aListRfc = new ArrayList<Traba36a>();
            List<Traba36a> traba36aListImss = new ArrayList<Traba36a>();
            List<Traba36a> traba36aListNc = new ArrayList<Traba36a>();

            HashMap<String, Traba36a> map = new HashMap<String, Traba36a>();

            if (imss != null && imss.length()
                    > 0) {
                searchableTraba36a.setRitraA(imss);
                traba36aListImss = traba36aManager.getListTraba36aWiz(searchableTraba36a);
                System.out.println("Imss" + searchableTraba36a.getRitraA());
            }

            if (rfcSearch
                    != null && rfcSearch.length()
                    > 0) {
                searchableTraba36a.setRftraA(rfcSearch.toUpperCase());
                traba36aListRfc = traba36aManager.getListTraba36aWiz(searchableTraba36a);
            }
            if (curp
                    != null && curp.length()
                    > 0) {
                searchableTraba36a.setCutraA(curp.toUpperCase());
                traba36aListCurp = traba36aManager.getListTraba36aWiz(searchableTraba36a);
            }
            if (ap
                    != null && ap.length()
                    > 0) {
                searchableTraba36a.setAptraA(ap.toUpperCase());
                ex = true;
            }
            if (am
                    != null && am.length()
                    > 0) {
                searchableTraba36a.setAmtraA(am.toUpperCase());
                ex = true;
            }
            if (n
                    != null && n.length()
                    > 0) {
                searchableTraba36a.setNotraA(n.toUpperCase());
                ex = true;
            }

            if (ex) {
                searchableTraba36a.setRftraA(null);
                searchableTraba36a.setCutraA(null);

                System.out.println("Paterno Aspirante" + searchableTraba36a.getAptraA());
                System.out.println("Materno Aspirante" + searchableTraba36a.getAmtraA());
                System.out.println("Nombres Aspirante" + searchableTraba36a.getNotraA());

                traba36aListNc = traba36aManager.getListTraba36aWiz(searchableTraba36a);

            }

            traba36aList = new ArrayList<Traba36a>();

            if (!traba36aListImss.isEmpty()) {
                for (Traba36a traba36a : traba36aListImss) {
                    System.out.println(traba36a.toString());
                    map.put(traba36a.getRftraA(), traba36a);
                }
            }

            if (!traba36aListCurp.isEmpty()) {
                for (Traba36a traba36a : traba36aListCurp) {
                    System.out.println(traba36a.toString());
                    map.put(traba36a.getRftraA(), traba36a);
                }
            }

            if (!traba36aListRfc.isEmpty()) {
                for (Traba36a traba36a : traba36aListRfc) {
                    System.out.println(traba36a.toString());
                    map.put(traba36a.getRftraA(), traba36a);
                }
            }

            if (!traba36aListNc.isEmpty()) {
                for (Traba36a traba36a : traba36aListNc) {
                    System.out.println(traba36a.toString());
                    map.put(traba36a.getRftraA(), traba36a);
                }
            }

            traba36aList = new ArrayList(map.values());

            if (!traba36aList.isEmpty()) {
                infoMessage = +traba36aList.size() + " Aspirantes";
            } else {
                infoMessage = "";
            }

        } catch (SecurityException | IllegalArgumentException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

}
