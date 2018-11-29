package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Apriv25;
import com.hrm.sirhapp.model.Benef26;
import com.hrm.sirhapp.model.Contr27;
import com.hrm.sirhapp.model.Datos28;
import com.hrm.sirhapp.model.Docum29;
import com.hrm.sirhapp.model.Escol30;
import com.hrm.sirhapp.model.Exper31;
import com.hrm.sirhapp.model.Famil32;
import com.hrm.sirhapp.model.Ident49;
import com.hrm.sirhapp.model.Prypr34;
import com.hrm.sirhapp.model.Requi54;
import com.hrm.sirhapp.model.Sanci35;
import com.hrm.sirhapp.model.Traba36;
import com.hrm.sirhapp.model.Usuar24;
import com.hrm.sirhapp.service.Apriv25ManagerLocal;
import com.hrm.sirhapp.service.Benef26ManagerLocal;
import com.hrm.sirhapp.service.Contr27ManagerLocal;
import com.hrm.sirhapp.service.Datos28ManagerLocal;
import com.hrm.sirhapp.service.Docum29ManagerLocal;
import com.hrm.sirhapp.service.Escol30ManagerLocal;
import com.hrm.sirhapp.service.Exper31ManagerLocal;
import com.hrm.sirhapp.service.Famil32ManagerLocal;
import com.hrm.sirhapp.service.Ident49ManagerLocal;
import com.hrm.sirhapp.service.Prypr34ManagerLocal;
import com.hrm.sirhapp.service.Sanci35ManagerLocal;
import com.hrm.sirhapp.service.Traba36ManagerLocal;
import com.hrm.sirhapp.service.exception.Apriv25NotFound;
import com.hrm.sirhapp.service.exception.Benef26NotFound;
import com.hrm.sirhapp.service.exception.Contr27NotFound;
import com.hrm.sirhapp.service.exception.Datos28NotFound;
import com.hrm.sirhapp.service.exception.Docum29NotFound;
import com.hrm.sirhapp.service.exception.Escol30NotFound;
import com.hrm.sirhapp.service.exception.Exper31NotFound;
import com.hrm.sirhapp.service.exception.Famil32NotFound;
import com.hrm.sirhapp.service.exception.Ident49NotFound;
import com.hrm.sirhapp.service.exception.Prypr34NotFound;
import com.hrm.sirhapp.service.exception.Sanci35NotFound;
import com.hrm.sirhapp.service.exception.Traba36NotFound;
import com.hrm.sirhapp.util.FacesUtil;
import com.hrm.sirhapp.util.LanguagesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
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
public class Traba36UpdateNtBacking extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Traba36ManagerLocal traba36Manager;
    @EJB
    private Datos28ManagerLocal datos28Manager;
    @EJB
    private Ident49ManagerLocal ident49Manager;
    @EJB
    private Docum29ManagerLocal docum29Manager;
    @EJB
    private Escol30ManagerLocal escol30Manager;
    @EJB
    private Exper31ManagerLocal exper31Manager;
    @EJB
    private Famil32ManagerLocal famil32Manager;
    @EJB
    private Benef26ManagerLocal benef26Manager;
    @EJB
    private Contr27ManagerLocal contr27Manager;
    @EJB
    private Prypr34ManagerLocal prypr34Manager;
    @EJB
    private Apriv25ManagerLocal apriv25Manager;
    @EJB
    private Sanci35ManagerLocal sanci35Manager;

    private Integer nt;
    private Integer nuevont;
    private String imss;
    private String rfc;
    private String curp;
    private String ap;
    private String am;
    private String n;

    private Date newDate = new Date();
    private String userName = FacesUtil.getUserName();

    private String infoMessageModule;
    private String infoMessageAsp;
    private String infoMessageTra;
    private String infoMessage;

    @Named
    @Produces
    @RequestScoped
    private Traba36 selectedTraba36Unt;

    private List<Traba36> traba36List;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Proceso actualizacion de Numero de Trabajador";
    }

    /**
     *
     * @param nutra
     * @return
     */
    public Traba36 getTraba36Request(Integer nutra) {

        try {

            this.selectedTraba36Unt = traba36Manager.getTraba36(nutra);

        } catch (Traba36NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("reports.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return selectedTraba36Unt;
    }

    /**
     *
     * @return
     */
    public String updateTraba36Nt1() {

        System.out.println("NT" + nt);
        System.out.println("NUEVO NT" + nuevont);

        return null;
    }

    /**
     *
     * @return
     * @throws Apriv25NotFound
     * @throws Apriv25NotFound
     */
    public String updateTraba36Nt() throws Apriv25NotFound, Apriv25NotFound, Apriv25NotFound {

        System.out.println("NT: " + nt);
        System.out.println("NUEVO NT: " + nuevont);
        nt = selectedTraba36Unt.getNutra();

        Boolean valid = false;
        Traba36 selectedTraba36 = null;
        Datos28 selectedDatos28 = null;
        Ident49 selectedIdent49 = null;
        Requi54 selectedRequi54 = null;

        List<Docum29> listDocum29;
        List<Escol30> listEscol30;
        List<Exper31> listExper31;
        List<Famil32> listFamil32;
        List<Benef26> listBenef26;

        List<Contr27> listContr27;

        List<Prypr34> listPrypr34;
        List<Apriv25> listApriv25;
        List<Sanci35> listSanci35;

        Contr27 selectedContr27 = null;

        try {
            selectedTraba36 = traba36Manager.getTraba36(nuevont);
        } catch (Traba36NotFound ex) {
            Logger.getLogger(Traba36UpdateNtBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            selectedDatos28 = datos28Manager.getDatos28(nuevont);
        } catch (Datos28NotFound ex) {
            Logger.getLogger(Traba36UpdateNtBacking.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            selectedIdent49 = ident49Manager.getIdent49(nuevont);
        } catch (Ident49NotFound ex) {
            Logger.getLogger(Traba36UpdateNtBacking.class.getName()).log(Level.SEVERE, null, ex);
        }

        listDocum29 = docum29Manager.getListDocum29(nuevont);
        listEscol30 = escol30Manager.getListEscol30(nuevont);
        listExper31 = exper31Manager.getListExper31(nuevont);
        listFamil32 = famil32Manager.getListFamil32(nuevont);
        listBenef26 = benef26Manager.getListBenef26(nuevont);

        listContr27 = contr27Manager.getListContr27Ntcto(nuevont);

        if (selectedTraba36 == null) {
            valid = true;
        }
        if (selectedDatos28 == null && valid == true) {
            valid = true;
        }
        if (selectedIdent49 == null && valid == true) {
            valid = true;
        }
        if (selectedRequi54 == null && valid == true) {
            valid = true;
        }
        if (listDocum29.isEmpty() && valid == true) {
            valid = true;
        }
        if (listEscol30.isEmpty() && valid == true) {
            valid = true;
        }
        if (listExper31.isEmpty() && valid == true) {
            valid = true;
        }
        if (listFamil32.isEmpty() && valid == true) {
            valid = true;
        }
        if (listBenef26.isEmpty() && valid == true) {
            valid = true;
        }

        if (listContr27.isEmpty() && valid == true) {
            valid = true;
        }

        if (valid == true) {

            try {
                selectedTraba36 = traba36Manager.getTraba36(nt);
                System.out.println(selectedTraba36.getIdtra());
            } catch (Traba36NotFound ex) {
                Logger.getLogger(Traba36UpdateNtBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                selectedDatos28 = datos28Manager.getDatos28(nt);
                System.out.println(selectedDatos28.getIddat());
            } catch (Datos28NotFound ex) {
                Logger.getLogger(Traba36UpdateNtBacking.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                selectedIdent49 = ident49Manager.getIdent49(nt);
                System.out.println(selectedIdent49.getIdide());
            } catch (Ident49NotFound ex) {
                Logger.getLogger(Traba36UpdateNtBacking.class.getName()).log(Level.SEVERE, null, ex);
            }

            listDocum29 = docum29Manager.getListDocum29(nt);
            listEscol30 = escol30Manager.getListEscol30(nt);
            listExper31 = exper31Manager.getListExper31(nt);
            listFamil32 = famil32Manager.getListFamil32(nt);
            listBenef26 = benef26Manager.getListBenef26(nt);

            listContr27 = contr27Manager.getListContr27Ntcto(nt);

            System.out.println("SE ACTUALIZARA TODO POR ESTE " + nuevont);

            if (listContr27 != null) {
                for (Contr27 e : listContr27) {
                    try {
                        e.setNtcto(nuevont);
                        e.setFecto(newDate);
                        e.setUscto(userName);

                        listPrypr34 = prypr34Manager.getListPrypr34(e.getNucto());
                        listApriv25 = apriv25Manager.getListApriv25(e.getNucto());
                        listSanci35 = sanci35Manager.getListSanci35(e.getNucto());

                        if (listPrypr34 != null) {
                            for (Prypr34 p : listPrypr34) {
                                try {
                                    p.setNtpry(nuevont);
                                    p.setFepry(newDate);
                                    p.setUspry(userName);
                                    prypr34Manager.updatePrypr34(p);
                                    System.out.println("Actualiza NT Prypr34" + p.getIdpry());
                                } catch (Prypr34NotFound ex) {
                                    Logger.getLogger(Traba36UpdateNtBacking.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        if (listApriv25 != null) {
                            for (Apriv25 a : listApriv25) {
                                try {
                                    a.setNtapr(nuevont);
                                    a.setFeapr(newDate);
                                    a.setUsapr(userName);
                                    apriv25Manager.updateApriv25(a);
                                    System.out.println("Actualiza NT Apriv25" + a.getIdapr());
                                } catch (Apriv25NotFound ex) {
                                    Logger.getLogger(Traba36UpdateNtBacking.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        if (listSanci35 != null) {
                            for (Sanci35 s : listSanci35) {
                                try {
                                    s.setNtsan(nuevont);
                                    s.setFesan(newDate);
                                    s.setUssan(userName);
                                    sanci35Manager.updateSanci35(s);
                                    System.out.println("Actualiza NT Sanci35" + s.getIdsan());
                                } catch (Sanci35NotFound ex) {
                                    Logger.getLogger(Traba36UpdateNtBacking.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }

                        contr27Manager.updateContr27(e);
                        System.out.println("Actualiza NT Contr27" + e.getIdcto());

                    } catch (Contr27NotFound ex) {
                        Logger.getLogger(Traba36UpdateNtBacking.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            if (selectedTraba36 != null) {
                selectedTraba36.setNutra(nuevont);
                selectedTraba36.setFetra(newDate);
                selectedTraba36.setUstra(userName);
                try {
                    traba36Manager.updateTraba36(selectedTraba36);
                } catch (Traba36NotFound ex) {
                    Logger.getLogger(Traba36UpdateNtBacking.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (selectedDatos28 != null) {
                selectedDatos28.setNtdat(nuevont);
                selectedDatos28.setFedat(newDate);
                selectedDatos28.setUsdat(userName);
                try {
                    datos28Manager.updateDatos28(selectedDatos28);
                } catch (Datos28NotFound ex) {
                    Logger.getLogger(Traba36UpdateNtBacking.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (selectedIdent49 != null) {
                selectedIdent49.setNtide(nuevont);
                selectedIdent49.setFeide(newDate);
                selectedIdent49.setUside(userName);
                try {
                    ident49Manager.updateIdent49(selectedIdent49);
                } catch (Ident49NotFound ex) {
                    Logger.getLogger(Traba36UpdateNtBacking.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            listDocum29 = docum29Manager.getListDocum29(nt);
            listEscol30 = escol30Manager.getListEscol30(nt);
            listExper31 = exper31Manager.getListExper31(nt);
            listFamil32 = famil32Manager.getListFamil32(nt);
            listBenef26 = benef26Manager.getListBenef26(nt);

            if (listBenef26 != null) {
                for (Benef26 e : listBenef26) {
                    try {
                        e.setNtben(nuevont);
                        e.setFeben(newDate);
                        e.setUsben(userName);
                        benef26Manager.updateBenef26(e);
                        System.out.println("Actualiza NT Benef26" + e.getIdben());
                    } catch (Benef26NotFound ex) {
                        Logger.getLogger(Traba36UpdateNtBacking.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (listFamil32 != null) {
                for (Famil32 e : listFamil32) {
                    try {
                        e.setNtfam(nuevont);
                        e.setFefam(newDate);
                        e.setUsfam(userName);
                        famil32Manager.updateFamil32(e);
                        System.out.println("Actualiza NT Famil32" + e.getIdfam());
                    } catch (Famil32NotFound ex) {
                        Logger.getLogger(Traba36UpdateNtBacking.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (listExper31 != null) {
                for (Exper31 e : listExper31) {
                    try {
                        e.setNtexp(nuevont);
                        e.setFeexp(newDate);
                        e.setUsexp(userName);
                        exper31Manager.updateExper31(e);
                        System.out.println("Actualiza NT Exper31" + e.getIdexp());
                    } catch (Exper31NotFound ex) {
                        Logger.getLogger(Traba36UpdateNtBacking.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (listEscol30 != null) {
                for (Escol30 e : listEscol30) {
                    try {
                        e.setNtesc(nuevont);
                        e.setFeesc(newDate);
                        e.setUsesc(userName);
                        escol30Manager.updateEscol30(e);
                        System.out.println("Actualiza NT Escol30" + e.getIdesc());
                    } catch (Escol30NotFound ex) {
                        Logger.getLogger(Traba36UpdateNtBacking.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (listDocum29 != null) {
                for (Docum29 e : listDocum29) {
                    try {
                        e.setNtdoc(nuevont);
                        e.setFedoc(newDate);
                        e.setUsdoc(userName);
                        docum29Manager.updateDocum29(e);
                        System.out.println("Actualiza NT Docum29" + e.getIddoc());
                    } catch (Docum29NotFound ex) {
                        Logger.getLogger(Traba36UpdateNtBacking.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            infoMessage = "El no de trabajador se actualizo correctamentamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } else {
            infoMessage = "El no de trabajador no se puede actualizar debido a errores de consistencia en la bd";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
        }

        return null;

    }

    /**
     *
     * @return
     */
    public Integer getNt() {
        return nt;
    }

    /**
     *
     * @param nt
     */
    public void setNt(Integer nt) {
        this.nt = nt;
    }

    /**
     *
     * @return
     */
    public Integer getNuevont() {
        return nuevont;
    }

    /**
     *
     * @param nuevont
     */
    public void setNuevont(Integer nuevont) {
        this.nuevont = nuevont;
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
    public Traba36 getSelectedTraba36Unt() {
        return selectedTraba36Unt;
    }

    /**
     *
     * @param selectedTraba36Unt
     */
    public void setSelectedTraba36Unt(Traba36 selectedTraba36Unt) {
        this.selectedTraba36Unt = selectedTraba36Unt;
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
    public String getInfoMessageAsp() {
        return infoMessageAsp;
    }

    /**
     *
     * @param infoMessageAsp
     */
    public void setInfoMessageAsp(String infoMessageAsp) {
        this.infoMessageAsp = infoMessageAsp;
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
    public List<Traba36> getTraba36List() {
        if (traba36List != null) {
            traba36List.sort(Comparator.comparing(Traba36::getNutra));
        }
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
     * @param vce
     */
    public void handleChange(AjaxBehaviorEvent vce) {

        String id = ((UIComponent) vce.getSource()).getId();

        switch (id) {

            case "nt":
                Integer value = (Integer) ((ValueHolder) vce.getSource()).getValue();
                System.out.println("id:" + id + " nt:" + value);
                nt = value;
                if (nt != null) {
                    retrieveTraba36aListActionListener();
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
    public String retrieveTraba36aListActionListener() {
        selectedTraba36Unt = null;
        traba36List = null;

        retrieveTraba36List();

        if (traba36List.size() > 0) {
            selectedTraba36Unt = traba36List.get(0);

            this.nt = selectedTraba36Unt.getNutra();

        } else {
            selectedTraba36Unt = null;
            infoMessage = "EL TRABAJADOR NO ESTA REGISTRADO";
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
            List<Traba36> traba36ListN = new ArrayList<Traba36>();

            HashMap<Integer, Traba36> map = new HashMap<Integer, Traba36>();

            Usuar24 usuar24 = FacesUtil.getUsuar24();

            if (nt != null && nt > 0) {
                searchableTraba36.setNutra(nt);
                traba36ListN = traba36Manager.getListTraba36PvWiz(searchableTraba36, usuar24);
                System.out.println("nt" + searchableTraba36.getNutra());
                searchableTraba36 = new Traba36();
            }

            if (imss != null && imss.length()
                    > 0) {
                searchableTraba36.setRitra(imss);
                traba36ListImss = traba36Manager.getListTraba36PvWiz(searchableTraba36, usuar24);
                System.out.println("Imss" + searchableTraba36.getRitra());
                searchableTraba36 = new Traba36();
            }

            if (rfc
                    != null && rfc.length()
                    > 0) {
                searchableTraba36.setRftra(rfc.toUpperCase());
                traba36ListRfc = traba36Manager.getListTraba36PvWiz(searchableTraba36, usuar24);
                System.out.println("Rfc" + searchableTraba36.getRftra());
                searchableTraba36 = new Traba36();
            }
            if (curp
                    != null && curp.length()
                    > 0) {
                searchableTraba36.setCutra(curp.toUpperCase());
                traba36ListCurp = traba36Manager.getListTraba36PvWiz(searchableTraba36, usuar24);
                System.out.println("Curp" + searchableTraba36.getCutra());
                searchableTraba36 = new Traba36();
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

                System.out.println("Paterno" + searchableTraba36.getAptra());
                System.out.println("Materno" + searchableTraba36.getAmtra());
                System.out.println("Nombres" + searchableTraba36.getNotra());

                traba36ListNc = traba36Manager.getListTraba36PvWiz(searchableTraba36, usuar24);
                searchableTraba36 = new Traba36();
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

            if (!traba36ListNc.isEmpty()) {
                for (Traba36 traba36 : traba36ListNc) {
                    map.put(traba36.getNutra(), traba36);
                }
            }

            if (!traba36ListRfc.isEmpty()) {
                for (Traba36 traba36 : traba36ListRfc) {
                    map.put(traba36.getNutra(), traba36);
                }
            }

            if (!traba36ListN.isEmpty()) {
                for (Traba36 traba36 : traba36ListN) {
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

}
