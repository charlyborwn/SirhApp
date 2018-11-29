package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Depar03;
import com.hrm.sirhapp.model.Empre04;
import com.hrm.sirhapp.model.Sedes53;
import com.hrm.sirhapp.model.Traba36;
import com.hrm.sirhapp.model.Usuar24;
import com.hrm.sirhapp.service.Depar03ManagerLocal;
import com.hrm.sirhapp.service.Empre04ManagerLocal;
import com.hrm.sirhapp.service.Espec05ManagerLocal;
import com.hrm.sirhapp.service.Sedes53ManagerLocal;
import com.hrm.sirhapp.service.Traba36ManagerLocal;
import com.hrm.sirhapp.service.exception.Traba36AlreadyExists;
import com.hrm.sirhapp.service.exception.Traba36NotFound;
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
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
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
public class Traba36BackingBean extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Traba36ManagerLocal traba36Manager;
    @EJB
    private Sedes53ManagerLocal sedes53Manager;
    @EJB
    private Empre04ManagerLocal empre04Manager;
    @EJB
    private Depar03ManagerLocal depar03Manager;
    @EJB
    private Espec05ManagerLocal espec05Manager;

    private Traba36 newTraba36Bean;
    private Traba36 selectedTraba36Bean;

    private List<Traba36> traba36List;
    private List<Sedes53> sedes53List;
    private List<Empre04> empre04List;
    private List<Depar03> depar03List;

    private Integer nt;
    private String imss;
    private String rfc;
    private String curp;
    private String ap;
    private String am;
    private String n;

    private String sitra;
    private String eitra;
    private String ditra;

    private String satra;
    private String eatra;
    private String datra;

    private String estra;

    private String sstra;

    private String path;

    private Date fntra;

    private Boolean active;
    private Boolean select;
    private Boolean create;
    private Boolean registered;
    private String infoMessageModule;
    private String infoMessageAsp;
    private String infoMessageTra;
    private String infoMessage;

    @PostConstruct
    private void init() {
        this.active = true;
        this.infoMessageModule = "Modulo Trabajadores";
        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);

        FacesContext facesContext = FacesContext.getCurrentInstance();

        if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
            select = false;
            create = false;
            prepareSelect();
        }
    }

    /**
     *
     */
    public void prepareSelect() {
        if (!select) {
            UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);
            if (userSessionBean.getTrabajador() > 0) {
                try {
                    this.selectedTraba36Bean = traba36Manager.getTraba36(userSessionBean.getTrabajador());
                } catch (Traba36NotFound ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                }

                this.active = true;
                System.out.println("prepareSelect():" + selectedTraba36Bean.toString());

                if (traba36Manager.alreadyExistsInnactive(selectedTraba36Bean.getNutra())) {
                    this.active = false;
                }
            }
            select = true;
        }

    }

    /**
     *
     */
    public void prepareCreate() {
        if (!create) {
            newTraba36Bean = new Traba36();
            newTraba36Bean.setCctra(new Date());
            create = true;
        }
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
    public Traba36ManagerLocal getTraba36Manager() {
        return traba36Manager;
    }

    /**
     *
     * @param traba36Manager
     */
    public void setTraba36Manager(Traba36ManagerLocal traba36Manager) {
        this.traba36Manager = traba36Manager;
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
    public void setTraba36RequestList(List<Traba36> traba36List) {
        this.traba36List = traba36List;
    }

    /**
     *
     * @return
     */
    public Traba36 getSelectedTraba36() {
        return selectedTraba36Bean;
    }

    /**
     *
     * @param traba36
     */
    public void setSelectedTraba36(Traba36 traba36) {
        this.selectedTraba36Bean = traba36;
    }

    /**
     *
     * @return
     */
    public Traba36 getNewTraba36Bean() {
        return newTraba36Bean;
    }

    /**
     *
     * @param newTraba36Bean
     */
    public void setNewTraba36Bean(Traba36 newTraba36Bean) {
        this.newTraba36Bean = newTraba36Bean;
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
     * @param nutra
     * @return
     */
    public Traba36 getTraba36Request(Integer nutra) {

        try {

            this.selectedTraba36Bean = traba36Manager.getTraba36(nutra);

            if (traba36Manager.alreadyExistsInnactive(nutra)) {
                this.active = false;
            }

        } catch (Traba36NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            //getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
            infoMessage = new LanguagesUtil().getResources("reports.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return selectedTraba36Bean;
    }

    /**
     *
     * @return
     */
    public String activateTraba36() {
        try {

            if (traba36Manager.alreadyExistsInnactive(selectedTraba36Bean.getNutra())) {
                infoMessage = "El trabajador fue activado correctamente.";
                FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            }

            selectedTraba36Bean.setSttra(Constants.RECORD_ACTIVED);
            selectedTraba36Bean.setFetra(new Date());
            selectedTraba36Bean.setUstra(FacesUtil.getUserName());

            traba36Manager.updateTraba36(selectedTraba36Bean);

        } catch (Traba36NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String updateTraba36() {
        try {

            if (traba36Manager.alreadyExistsInnactive(selectedTraba36Bean.getNutra())) {
                infoMessage = "El trabajador fue activado correctamente";
                FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            }

            if (fntra != null) {
                selectedTraba36Bean.setFntra(fntra);
            }

            if (sitra != null) {
                selectedTraba36Bean.setSitra(sitra);
            }
            if (eitra != null) {
                selectedTraba36Bean.setEitra(eitra);
            }
            if (ditra != null) {
                selectedTraba36Bean.setDitra(ditra);
            }
            if (estra != null) {
                selectedTraba36Bean.setEstra(estra);
            }
            if (path != null) {
                selectedTraba36Bean.setPatra(path);
            }

            selectedTraba36Bean.setSttra(Constants.RECORD_ACTIVED);
            selectedTraba36Bean.setFetra(new Date());
            selectedTraba36Bean.setUstra(FacesUtil.getUserName());

            traba36Manager.updateTraba36(selectedTraba36Bean);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Traba36NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String updateTraba36Foto() {
        try {
            if (newTraba36Bean != null) {
                
                newTraba36Bean.setPatra(null);

            } else if (selectedTraba36Bean != null) {

                selectedTraba36Bean.setPatra(null);
                selectedTraba36Bean.setSttra(Constants.RECORD_ACTIVED);
                selectedTraba36Bean.setFetra(new Date());
                selectedTraba36Bean.setUstra(FacesUtil.getUserName());

                selectedTraba36Bean = traba36Manager.updateTraba36(selectedTraba36Bean);
            }
            FacesUtil.removeManagedBeanInSession("fileUploadBean");
            infoMessageModule = "Fotografia";
            infoMessage = "Se borro la fotografia del trabajador";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            return "success";
        } catch (Traba36NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
            return "failure";
        }
    }

    /**
     *
     * @return
     */
    public String deleteTraba36() {
        try {

            selectedTraba36Bean.setSttra(Constants.RECORD_DELETED);
            selectedTraba36Bean.setFetra(new Date());
            selectedTraba36Bean.setUstra(FacesUtil.getUserName());

            traba36Manager.deleteTraba36(selectedTraba36Bean);

            infoMessage = "El trabajador se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            return "success";

        } catch (Traba36NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
            return "failure";
        }

    }

    /**
     *
     * @return
     */
    public String register() {

        try {
            if (sstra != null) {
                newTraba36Bean.setSstra(sstra);
                newTraba36Bean.setFstra(new Date());
            }
            if (path != null) {
                newTraba36Bean.setPatra(path);
            }

            newTraba36Bean.setSttra(Constants.RECORD_ACTIVED);
            newTraba36Bean.setFetra(new Date());
            newTraba36Bean.setUstra(FacesUtil.getUserName());

            traba36Manager.registerTraba36(newTraba36Bean);

            this.registered = true;
            infoMessage = "El registro se creo correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Traba36AlreadyExists ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        } catch (EJBException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error en la base de datos";
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @param action
     * @return
     * @throws IOException
     */
    public String go(int action) throws IOException {

        String outcome = "/secured/trabajadores/datosGenerales.xhtml?faces-redirect=true";

        if (action == 1) {
            outcome = "/secured/trabajadores/datosGeneralesEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/trabajadores/datosGeneralesCreate.xhtml?faces-redirect=true";
        }
        if (action == 9) {
            outcome = "/secured/trabajadores/find.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

    /**
     *
     * @param vce
     * @throws IOException
     */
    public void handleChange(AjaxBehaviorEvent vce) throws IOException {

        String id = ((UIComponent) vce.getSource()).getId();

        switch (id) {
            case "sel_sstra":
                String valueSstra = (String) ((ValueHolder) vce.getSource()).getValue();
                if (newTraba36Bean != null) {
                    this.sstra = valueSstra;
                    newTraba36Bean.setFstra(new Date());
                }
                if (selectedTraba36Bean != null) {
                    selectedTraba36Bean.setSstra(valueSstra);
                    selectedTraba36Bean.setFstra(new Date());
                    updateTraba36();
                    go(1);
                }
                break;
            case "sel_estra":
                String valueEstra = (String) ((ValueHolder) vce.getSource()).getValue();
                estra = valueEstra;
                break;
            case "sel_fntra":
                Date sel_fntra = (Date) ((ValueHolder) vce.getSource()).getValue();
                fntra = sel_fntra;
                break;
            case "sel_satra":
                String valueSatra = (String) ((ValueHolder) vce.getSource()).getValue();
                satra = valueSatra;
                if (newTraba36Bean != null) {
                    newTraba36Bean.setSatra(valueSatra);
                }
                break;
            case "sel_eatra":
                String valueEatra = (String) ((ValueHolder) vce.getSource()).getValue();
                eatra = valueEatra;
                if (newTraba36Bean != null) {
                    newTraba36Bean.setEatra(valueEatra);
                }
                break;
            case "sel_datra":
                String valueDatra = (String) ((ValueHolder) vce.getSource()).getValue();
                datra = valueDatra;
                if (newTraba36Bean != null) {
                    newTraba36Bean.setDatra(valueDatra);
                }
                break;
            case "sel_sitra":
                String valueSitra = (String) ((ValueHolder) vce.getSource()).getValue();
                sitra = valueSitra;
                break;
            case "sel_eitra":
                String valueEitra = (String) ((ValueHolder) vce.getSource()).getValue();
                eitra = valueEitra;
                break;
            case "sel_ditra":
                String valueDitra = (String) ((ValueHolder) vce.getSource()).getValue();
                ditra = valueDitra;
                break;

            case "nt":
                Integer value = (Integer) ((ValueHolder) vce.getSource()).getValue();
                System.out.println("id:" + id + " nt:" + value);
                nt = value;
                if (nt != null) {
                    retrieveTraba36ListActionListener();
                }
                break;
            default:
                break;
        }
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

    /**
     *
     * @return
     */
    public List<Sedes53> getListSedes53() {

        sedes53List = sedes53Manager.getListSedes53();

        if (sedes53List.isEmpty()) {
            infoMessage = "No existen Registros!";
        } else {
            infoMessage = sedes53List.size() + " Registros";
        }
        return sedes53List;
    }

    /**
     *
     * @return
     */
    public List<Empre04> getListEmpre04() {

        empre04List = empre04Manager.getListEmpre04();

        if (!empre04List.isEmpty()) {
            infoMessage = empre04List.size() + " registro(s)";
        }
        return empre04List;
    }

    /**
     *
     * @param seemp
     * @return
     */
    public List<Empre04> getListEmpre04(String seemp) {

        List<Empre04> result = empre04Manager.getListEmpre04();
        if (seemp != null) {
            result = result.stream()
                    .filter(empre04 -> seemp.equals(empre04.getSeemp()))
                    .collect(Collectors.toList());
        }

        System.out.println(seemp);
        return result;
    }

    /**
     *
     * @return
     */
    public List<Depar03> getListDepar03() {

        depar03List = depar03Manager.getListDepar03();

        return depar03List;
    }

    /**
     *
     * @param seemp
     * @param cedep
     * @return
     */
    public List<Depar03> getListDepar03(String seemp, String cedep) {
        List<Depar03> result = depar03Manager.getListDepar03();
        if (seemp != null) {
            System.out.println(seemp + "| seemp gelist");
            result = result.stream()
                    .filter(depar03 -> seemp.equals(depar03.getSedep()))
                    .collect(Collectors.toList());
        }

        if (cedep != null) {
            System.out.println(cedep + "| cedep gelist");
            result = result.stream()
                    .filter(depar03 -> cedep.equals(depar03.getCedep()))
                    .collect(Collectors.toList());
        }

        return result;
    }

    /**
     *
     * @return
     */
    public String retrieveTraba36ListActionListener() {
        selectedTraba36Bean = null;
        traba36List = null;

        retrieveTraba36List();

        System.out.println("traba36List.size()" + traba36List.size());

        if (traba36List.size() == 1) {
            selectedTraba36Bean = traba36List.get(0);

            this.nt = selectedTraba36Bean.getNutra();

        } else if (traba36List.size() > 1) {

        } else {
            selectedTraba36Bean = null;
            infoMessage = "EL TRABAJADOR NO ESTA REGISTRADO";
        }

        return "resultadosBuscar";
    }

    /**
     *
     * @return
     */
    public String retrieveTraba36aListTraba36List() {
        selectedTraba36Bean = null;
        traba36List = null;

        retrieveTraba36List();

        System.out.println("traba36List.size()" + traba36List.size());

        if (traba36List.size() == 1) {
            selectedTraba36Bean = traba36List.get(0);

            this.nt = selectedTraba36Bean.getNutra();

        } else if (traba36List.size() > 1) {

        } else {
            selectedTraba36Bean = null;
            infoMessage = "EL TRABAJADOR NO ESTA REGISTRADO";
        }

        return "resultadosBuscar";
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
    public String getSatra() {
        return satra;
    }

    /**
     *
     * @param satra
     */
    public void setSatra(String satra) {
        this.satra = satra;
    }

    /**
     *
     * @return
     */
    public String getEatra() {
        return eatra;
    }

    /**
     *
     * @param eatra
     */
    public void setEatra(String eatra) {
        this.eatra = eatra;
    }

    /**
     *
     * @return
     */
    public String getDatra() {
        return datra;
    }

    /**
     *
     * @param datra
     */
    public void setDatra(String datra) {
        this.datra = datra;
    }

    /**
     *
     * @return
     */
    public Date getFntra() {
        return fntra;
    }

    /**
     *
     * @param fntra
     */
    public void setFntra(Date fntra) {
        this.fntra = fntra;
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
    public String getEstra() {
        return estra;
    }

    /**
     *
     * @param estra
     */
    public void setEstra(String estra) {
        this.estra = estra;
    }

    /**
     *
     * @return
     */
    public String getSstra() {
        return sstra;
    }

    /**
     *
     * @param sstra
     */
    public void setSstra(String sstra) {
        this.sstra = sstra;
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
    public Traba36 getSelectedTraba36Bean() {
        return selectedTraba36Bean;
    }

    /**
     *
     * @param selectedTraba36Bean
     */
    public void setSelectedTraba36Bean(Traba36 selectedTraba36Bean) {
        this.selectedTraba36Bean = selectedTraba36Bean;
    }

    /**
     *
     * @return
     */
    public List<Sedes53> getSedes53List() {
        return sedes53List;
    }

    /**
     *
     * @param sedes53List
     */
    public void setSedes53List(List<Sedes53> sedes53List) {
        this.sedes53List = sedes53List;
    }

    /**
     *
     * @return
     */
    public List<Empre04> getEmpre04List() {
        return empre04List;
    }

    /**
     *
     * @param empre04List
     */
    public void setEmpre04List(List<Empre04> empre04List) {
        this.empre04List = empre04List;
    }

    /**
     *
     * @return
     */
    public List<Depar03> getDepar03List() {
        return depar03List;
    }

    /**
     *
     * @param depar03List
     */
    public void setDepar03List(List<Depar03> depar03List) {
        this.depar03List = depar03List;
    }

    /**
     *
     * @return
     */
    public Boolean getRegistered() {
        return registered;
    }

    /**
     *
     * @param registered
     */
    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }

    /**
     *
     * @return
     */
    public Boolean getCreate() {
        return create;
    }

    /**
     *
     * @param create
     */
    public void setCreate(Boolean create) {
        this.create = create;
    }

    /**
     *
     * @return
     */
    public Boolean getActive() {
        return active;
    }

    /**
     *
     * @param active
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     *
     * @return
     */
    public Boolean getSelect() {
        return select;
    }

    /**
     *
     * @param select
     */
    public void setSelect(Boolean select) {
        this.select = select;
    }

}
