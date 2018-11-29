package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Benef26a;
import com.hrm.sirhapp.model.Datos28a;
import com.hrm.sirhapp.model.Docum29a;
import com.hrm.sirhapp.model.Escol30a;
import com.hrm.sirhapp.model.Exper31a;
import com.hrm.sirhapp.model.Famil32a;
import com.hrm.sirhapp.model.Ident49a;
import com.hrm.sirhapp.model.Requi54;
import com.hrm.sirhapp.model.Traba36a;
import com.hrm.sirhapp.service.Benef26aManagerLocal;
import com.hrm.sirhapp.service.Datos28aManagerLocal;
import com.hrm.sirhapp.service.Docum29aManagerLocal;
import com.hrm.sirhapp.service.Escol30aManagerLocal;
import com.hrm.sirhapp.service.Exper31aManagerLocal;
import com.hrm.sirhapp.service.Famil32aManagerLocal;
import com.hrm.sirhapp.service.Ident49aManagerLocal;
import com.hrm.sirhapp.service.Requi54ManagerLocal;
import com.hrm.sirhapp.service.Traba36aManagerLocal;
import com.hrm.sirhapp.service.exception.Benef26aNotFound;
import com.hrm.sirhapp.service.exception.Datos28aNotFound;
import com.hrm.sirhapp.service.exception.Docum29aNotFound;
import com.hrm.sirhapp.service.exception.Escol30aNotFound;
import com.hrm.sirhapp.service.exception.Exper31aNotFound;
import com.hrm.sirhapp.service.exception.Famil32aNotFound;
import com.hrm.sirhapp.service.exception.Ident49aNotFound;
import com.hrm.sirhapp.service.exception.Requi54NotFound;
import com.hrm.sirhapp.service.exception.Traba36aNotFound;
import com.hrm.sirhapp.util.FacesUtil;
import com.hrm.sirhapp.util.LanguagesUtil;
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
public class Traba36aUpdateRfcBacking extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Traba36aManagerLocal traba36aManager;

    @EJB
    private Datos28aManagerLocal datos28aManager;

    @EJB
    private Ident49aManagerLocal ident49aManager;

    @EJB
    private Requi54ManagerLocal requi54Manager;

    @EJB
    private Docum29aManagerLocal docum29aManager;

    @EJB
    private Escol30aManagerLocal escol30aManager;

    @EJB
    private Exper31aManagerLocal exper31aManager;

    @EJB
    private Famil32aManagerLocal famil32aManager;

    @EJB
    private Benef26aManagerLocal benef26aManager;

    private Date newDate = new Date();
    private String userName = FacesUtil.getUserName();

    @Named
    @Produces
    @RequestScoped
    private Traba36a selectedTraba36aUpdateRfc;

    private List<Traba36a> traba36aList;
    private String rfcSearch;
    private String nuevorfc;
    private String curp;
    private String imss;
    private String ap;
    private String am;
    private String n;

    private String infoMessageModule;
    private String infoMessage;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Proceso actualizacion de rfc de aspirantes";
    }

    /**
     *
     * @return
     */
    public String updateTraba36aRfc1() {

        System.out.println("RFC" + selectedTraba36aUpdateRfc.getRftraA());
        System.out.println("NUEVO RFC" + nuevorfc);

        return null;
    }

    /**
     *
     * @return
     */
    public String updateTraba36aRfc() {

        rfcSearch = selectedTraba36aUpdateRfc.getRftraA();
        nuevorfc = nuevorfc.replace("_", "");

        System.out.println("RFC: " + selectedTraba36aUpdateRfc.getRftraA());
        System.out.println("NUEVO RFC: " + nuevorfc);

        Boolean valid = false;
        Traba36a selectedTraba36a = null;
        Datos28a selectedDatos28a = null;
        Ident49a selectedIdent49a = null;
        Requi54 selectedRequi54 = null;

        List<Docum29a> listDocum29a;
        List<Escol30a> listEscol30a;
        List<Exper31a> listExper31a;
        List<Famil32a> listFamil32a;
        List<Benef26a> listBenef26a;

        try {
            selectedTraba36a = traba36aManager.getTraba36a(nuevorfc);
        } catch (Traba36aNotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        try {
            selectedDatos28a = datos28aManager.getDatos28a(nuevorfc);
        } catch (Datos28aNotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }

        try {
            selectedIdent49a = ident49aManager.getIdent49a(nuevorfc);
        } catch (Ident49aNotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }

        try {
            selectedRequi54 = requi54Manager.getRequi54(nuevorfc);
        } catch (Requi54NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }

        listDocum29a = docum29aManager.getListDocum29a(nuevorfc);
        listEscol30a = escol30aManager.getListEscol30a(nuevorfc);
        listExper31a = exper31aManager.getListExper31a(nuevorfc);
        listFamil32a = famil32aManager.getListFamil32a(nuevorfc);
        listBenef26a = benef26aManager.getListBenef26a(nuevorfc);

        if (selectedTraba36a == null) {
            valid = true;
        }
        if (selectedDatos28a == null && valid == true) {
            valid = true;
        }
        if (selectedIdent49a == null && valid == true) {
            valid = true;
        }
        if (selectedRequi54 == null && valid == true) {
            valid = true;
        }
        if (listDocum29a.isEmpty() && valid == true) {
            valid = true;
        }
        if (listEscol30a.isEmpty() && valid == true) {
            valid = true;
        }
        if (listExper31a.isEmpty() && valid == true) {
            valid = true;
        }
        if (listFamil32a.isEmpty() && valid == true) {
            valid = true;
        }
        if (listBenef26a.isEmpty() && valid == true) {
            valid = true;
        }

        if (valid == true) {

            try {
                selectedTraba36a = traba36aManager.getTraba36a(rfcSearch);
                System.out.println(selectedTraba36a.getIdtraA());
            } catch (Traba36aNotFound ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
            try {
                selectedDatos28a = datos28aManager.getDatos28a(rfcSearch);
                System.out.println(selectedDatos28a.getIddatA());
            } catch (Datos28aNotFound ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }

            try {
                selectedIdent49a = ident49aManager.getIdent49a(rfcSearch);
                System.out.println(selectedIdent49a.getIdideA());
            } catch (Ident49aNotFound ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }

            try {
                selectedRequi54 = requi54Manager.getRequi54(rfcSearch);
                System.out.println(selectedRequi54.getIdreq());
            } catch (Requi54NotFound ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }

            listDocum29a = docum29aManager.getListDocum29a(rfcSearch);
            listEscol30a = escol30aManager.getListEscol30a(rfcSearch);
            listExper31a = exper31aManager.getListExper31a(rfcSearch);
            listFamil32a = famil32aManager.getListFamil32a(rfcSearch);
            listBenef26a = benef26aManager.getListBenef26a(rfcSearch);

            System.out.println("SE ACTUALIZARA TODO POR ESTE " + nuevorfc);

            if (selectedTraba36a != null) {
                selectedTraba36a.setRftraA(nuevorfc);
                selectedTraba36a.setFetraA(newDate);
                selectedTraba36a.setUstraA(userName);
                try {
                    traba36aManager.updateTraba36a(selectedTraba36a);
                } catch (Traba36aNotFound ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (selectedDatos28a != null) {
                selectedDatos28a.setRfdatA(nuevorfc);
                selectedDatos28a.setFedatA(newDate);
                selectedDatos28a.setUsdatA(userName);
                try {
                    datos28aManager.updateDatos28a(selectedDatos28a);
                } catch (Datos28aNotFound ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (selectedIdent49a != null) {
                selectedIdent49a.setRfideA(nuevorfc);
                selectedIdent49a.setFeideA(newDate);
                selectedIdent49a.setUsideA(userName);
                try {
                    ident49aManager.updateIdent49a(selectedIdent49a);
                } catch (Ident49aNotFound ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (selectedRequi54 != null) {
                selectedRequi54.setRfreq(nuevorfc);
                selectedRequi54.setFereq(newDate);
                selectedRequi54.setUsreq(userName);
                try {
                    requi54Manager.updateRequi54(selectedRequi54);
                } catch (Requi54NotFound ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }

            listDocum29a = docum29aManager.getListDocum29a(rfcSearch);
            listEscol30a = escol30aManager.getListEscol30a(rfcSearch);
            listExper31a = exper31aManager.getListExper31a(rfcSearch);
            listFamil32a = famil32aManager.getListFamil32a(rfcSearch);
            listBenef26a = benef26aManager.getListBenef26a(rfcSearch);

            if (listBenef26a != null) {
                for (Benef26a e : listBenef26a) {
                    try {
                        e.setRfbenA(nuevorfc);
                        e.setFebenA(newDate);
                        e.setUsbenA(userName);
                        benef26aManager.updateBenef26a(e);
                        System.out.println("Actualiza RFC Benef26" + e.getIdbenA());
                    } catch (Benef26aNotFound ex) {
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (listFamil32a != null) {
                for (Famil32a e : listFamil32a) {
                    try {
                        e.setRffamA(nuevorfc);
                        e.setFefamA(newDate);
                        e.setUsfamA(userName);
                        famil32aManager.updateFamil32a(e);
                        System.out.println("Actualiza RFC Famil32" + e.getIdfamA());
                    } catch (Famil32aNotFound ex) {
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (listExper31a != null) {
                for (Exper31a e : listExper31a) {
                    try {
                        e.setRfexpA(nuevorfc);
                        e.setFeexpA(newDate);
                        e.setUsexpA(userName);
                        exper31aManager.updateExper31a(e);
                        System.out.println("Actualiza RFC Exper31a" + e.getIdexpA());
                    } catch (Exper31aNotFound ex) {
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (listEscol30a != null) {
                for (Escol30a e : listEscol30a) {
                    try {
                        e.setRfescA(nuevorfc);
                        e.setFeescA(newDate);
                        e.setUsescA(userName);
                        escol30aManager.updateEscol30a(e);
                        System.out.println("Actualiza RFC Escol30a" + e.getIdescA());
                    } catch (Escol30aNotFound ex) {
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (listDocum29a != null) {
                for (Docum29a e : listDocum29a) {
                    try {
                        e.setRfdocA(nuevorfc);
                        e.setFedocA(newDate);
                        e.setUsdocA(userName);
                        docum29aManager.updateDocum29a(e);
                        System.out.println("Actualiza RFC Docum29a" + e.getIddocA());
                    } catch (Docum29aNotFound ex) {
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            infoMessage = "El rfc del aspirante se actualizo correctamentamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } else {
            infoMessage = "El rfc del aspirante no se puede actualizar debido a errores de consistencia en la bd";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
        }

        return null;

    }

    /**
     *
     * @return
     */
    public Traba36a getSelectedTraba36aUpdateRfc() {
        return selectedTraba36aUpdateRfc;
    }

    /**
     *
     * @param selectedTraba36aUpdateRfc
     */
    public void setSelectedTraba36aUpdateRfc(Traba36a selectedTraba36aUpdateRfc) {
        this.selectedTraba36aUpdateRfc = selectedTraba36aUpdateRfc;
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
    public void setTraba36aList(List<Traba36a> traba36aList) {
        this.traba36aList = traba36aList;
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
    public String getNuevorfc() {
        return nuevorfc;
    }

    /**
     *
     * @param nuevorfc
     */
    public void setNuevorfc(String nuevorfc) {
        this.nuevorfc = nuevorfc;
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
        selectedTraba36aUpdateRfc = null;
        traba36aList = null;

        retrieveTraba36aList();

        if (traba36aList.size() > 0) {
            selectedTraba36aUpdateRfc = traba36aList.get(0);

            this.rfcSearch = selectedTraba36aUpdateRfc.getRftraA();
            this.rfcSearch = rfcSearch.toUpperCase();

        } else {
            selectedTraba36aUpdateRfc = null;
            infoMessage = "EL ASPIRANTE NO ESTA REGISTRADO";
        }

        return "resultadosBuscar";
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
                searchableTraba36a = new Traba36a();
            }

            if (rfcSearch
                    != null && rfcSearch.length()
                    > 0) {
                searchableTraba36a.setRftraA(rfcSearch.toUpperCase());
                traba36aListRfc = traba36aManager.getListTraba36aWiz(searchableTraba36a);
                searchableTraba36a = new Traba36a();
            }
            if (curp
                    != null && curp.length()
                    > 0) {
                searchableTraba36a.setCutraA(curp.toUpperCase());
                traba36aListCurp = traba36aManager.getListTraba36aWiz(searchableTraba36a);
                searchableTraba36a = new Traba36a();
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

                System.out.println("Paterno Aspirante" + searchableTraba36a.getAptraA());
                System.out.println("Materno Aspirante" + searchableTraba36a.getAmtraA());
                System.out.println("Nombres Aspirante" + searchableTraba36a.getNotraA());

                traba36aListNc = traba36aManager.getListTraba36aWiz(searchableTraba36a);
                searchableTraba36a = new Traba36a();

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

    /**
     *
     * @param rftraA
     * @return
     */
    public Traba36a getTraba36aRequest(String rftraA) {

        try {

            this.selectedTraba36aUpdateRfc = traba36aManager.getTraba36a(rftraA);

        } catch (Traba36aNotFound ex) {
            Logger.getLogger(Traba36aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("reports.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return selectedTraba36aUpdateRfc;
    }

}
