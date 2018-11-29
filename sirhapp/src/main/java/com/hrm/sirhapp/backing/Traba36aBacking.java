package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Depar03;
import com.hrm.sirhapp.model.Empre04;
import com.hrm.sirhapp.model.Sedes53;
import com.hrm.sirhapp.model.Traba36;
import com.hrm.sirhapp.model.Traba36a;
import com.hrm.sirhapp.service.Depar03ManagerLocal;
import com.hrm.sirhapp.service.Empre04ManagerLocal;
import com.hrm.sirhapp.service.Espec05ManagerLocal;
import com.hrm.sirhapp.service.Sedes53ManagerLocal;
import com.hrm.sirhapp.service.Traba36ManagerLocal;
import com.hrm.sirhapp.service.Traba36aManagerLocal;
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
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
public class Traba36aBacking extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Traba36aManagerLocal traba36aManager;

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

    @Named
    @Produces
    @ViewScoped
    private Traba36a selectedTraba36a;

    private Traba36 selectedTraba36;

    private List<Sedes53> sedes53List;

    private List<Empre04> empre04List;

    private List<Depar03> depar03List;

    private List<Traba36> traba36List;
    private List<Traba36a> traba36aList;
    private String rfc;
    private String rfcSearch;
    private String curp;
    private String imss;
    private String ap;
    private String am;
    private String n;

    private String sitraA;
    private String eitraA;
    private String ditraA;

    private String estraA;

    private String path;

    private Date fntraA;

    private boolean active;
    private String infoMessageModule;
    private String infoMessage;
    private String infoMessageTra;
    private String contentPdf;

    private String outcome;

    @PostConstruct
    private void init() {
        this.active = true;
        this.infoMessageModule = "Modulo Aspirantes";

        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
            preRenderView();
            System.out.println("preRenderView called");
        }

    }

    /**
     *
     */
    public void preRenderView() {
        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);

        if (userSessionBean.getAspirante() != null && userSessionBean.getAspirante().length() > 0) {
            getTraba36aRequest(userSessionBean.getAspirante());
            if (selectedTraba36a != null) {
                System.out.println("ESTE ES EL RFC" + selectedTraba36a.getRftraA());

            }
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
    public Traba36a getSelectedTraba36a() {
        return selectedTraba36a;
    }

    /**
     *
     * @param traba36a
     */
    public void setSelectedTraba36a(Traba36a traba36a) {
        this.selectedTraba36a = traba36a;
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
     * @param query
     * @return
     */
    public List<String> especialidadLaboral(String query) {

        List<String> results = espec05Manager.getListEspec05Noesp(query);

        return results;
    }

    /**
     *
     * @param rftraA
     * @return
     */
    public Traba36a getTraba36aRequest(String rftraA) {

        try {

            this.selectedTraba36a = traba36aManager.getTraba36a(rftraA);

            if (traba36aManager.alreadyExistsInnactive(rftraA)) {
                this.active = false;
            }

        } catch (Traba36aNotFound ex) {
            Logger.getLogger(Traba36aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        } catch (Exception ex) {
            Logger.getLogger(Traba36aBacking.class.getName()).log(Level.SEVERE, null, ex);
            //getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
            infoMessage = new LanguagesUtil().getResources("reports.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return selectedTraba36a;
    }

    /**
     *
     * @return
     */
    public String activateTraba36a() {
        try {

            if (traba36aManager.alreadyExistsInnactive(selectedTraba36a.getRftraA())) {
                infoMessage = "El aspirante fue activado correctamente.";
                FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            }

            selectedTraba36a.setSttraA(Constants.RECORD_ACTIVED);
            selectedTraba36a.setFetraA(new Date());
            selectedTraba36a.setUstraA(FacesUtil.getUserName());

            traba36aManager.updateTraba36a(selectedTraba36a);

        } catch (Traba36aNotFound ex) {
            Logger.getLogger(Traba36aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String updateTraba36a() {
        try {

            if (traba36aManager.alreadyExistsInnactive(selectedTraba36a.getRftraA())) {
                infoMessage = "El aspirante fue activado correctamente";
                FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            }

            if (fntraA != null) {
                selectedTraba36a.setFntraA(fntraA);
            }

            if (sitraA != null) {
                selectedTraba36a.setSitraA(sitraA);
            }
            if (eitraA != null) {
                selectedTraba36a.setEitraA(eitraA);
            }
            if (ditraA != null) {
                selectedTraba36a.setDitraA(ditraA);
            }
            if (estraA != null) {
                selectedTraba36a.setEstraA(estraA);
            }
            if (path != null) {
                selectedTraba36a.setPatraA(path);
            }

            System.out.println("getSitraA" + selectedTraba36a.getEstraA());

            System.out.println("getSitraA" + selectedTraba36a.getSitraA());
            System.out.println("getEitraA" + selectedTraba36a.getEitraA());
            System.out.println("getDitraA" + selectedTraba36a.getDitraA());

            selectedTraba36a.setSttraA(Constants.RECORD_ACTIVED);
            selectedTraba36a.setFetraA(new Date());
            selectedTraba36a.setUstraA(FacesUtil.getUserName());

            traba36aManager.updateTraba36a(selectedTraba36a);
            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Traba36aNotFound ex) {
            Logger.getLogger(Traba36aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String updateTraba36aFoto() {
        try {

            selectedTraba36a.setPatraA(null);
            selectedTraba36a.setSttraA(Constants.RECORD_ACTIVED);
            selectedTraba36a.setFetraA(new Date());
            selectedTraba36a.setUstraA(FacesUtil.getUserName());

            traba36aManager.updateTraba36a(selectedTraba36a);
            FacesUtil.removeManagedBeanInSession("fileUploadBean");
            infoMessageModule = "Fotografia";
            infoMessage = "Se borro la fotografia del aspirante";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Traba36aNotFound ex) {
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
    public String deleteTraba36a() {
        try {

            selectedTraba36a.setSttraA(Constants.RECORD_DELETED);
            selectedTraba36a.setFetraA(new Date());
            selectedTraba36a.setUstraA(FacesUtil.getUserName());

            traba36aManager.deleteTraba36a(selectedTraba36a);

            infoMessage = "El aspirante se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            return "success";

        } catch (Traba36aNotFound ex) {
            Logger.getLogger(Traba36aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
            return "failure";
        }
    }

    /**
     *
     * @return
     */
    public String getContentPdf() {
        String str;

        if (selectedTraba36a != null) {
            if (selectedTraba36a.getRftraA().length() > 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>DATOS GENERALES DE ASPIRANTE</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>DATOS GENERALES DE ASPIRANTE</h1>"
                        + "<div class=\"page-content\">"
                        + "<table class=\"table-content\">"
                        + "<tr>"
                        + "<td width=\"160px\"><img width=\"160\"  src=\"" + FacesUtil.getUrl() + FacesUtil.getImgPath(this.selectedTraba36a.getPatraA()) + "\" /></td>"
                        + "<td class=\"separador\"></td>"
                        + "<td>"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">RFC:</td>"
                        + "<td class=\"dato\">" + this.selectedTraba36a.getRftraA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">CURP:</td>"
                        + "<td class=\"dato\">" + this.selectedTraba36a.getCutraA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">NOMBRE:</td>"
                        + "<td class=\"dato\">" + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">NACIMIENTO FECHA:</td>"
                        + "<td class=\"dato\">" + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">EDAD:</td>"
                        + "<td class=\"dato\">" + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">SEXO:</td>"
                        + "<td class=\"dato\">" + this.selectedTraba36a.getSetraA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">NACIONALIDAD:</td>"
                        + "<td class=\"dato\">" + this.selectedTraba36a.getNatraA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">OBSERVACIONES:</td>"
                        + "<td class=\"dato\">" + "</td>"
                        + "</tr>"
                        + "</table></td>"
                        + "</tr>"
                        + "</table>"
                        + "</div>"
                        + "</body>"
                        + "</html>";
                this.contentPdf = str;
            }
        }

        return contentPdf;
    }

    /**
     *
     * @param contentPdf
     */
    public void setContentPdf(String contentPdf) {
        this.contentPdf = contentPdf;
    }

    /**
     *
     * @param action
     * @return
     * @throws IOException
     */
    public String go(int action) throws IOException {

        String outcome = "/secured/aspirantes/datosGenerales.xhtml?faces-redirect=true";

        if (action == 1) {
            outcome = "/secured/aspirantes/datosGeneralesEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/aspirantes/datosGeneralesCreate.xhtml?faces-redirect=true";
        }
        if (action == 7) {
            outcome = "/secured/nuevoaspirante/capturaCorta.xhtml?faces-redirect=true";
        }
        if (action == 8) {
            outcome = new LanguagesUtil().getBundle("StartPage");
        }
        if (action == 9) {
            outcome = "/secured/aspirantes/find.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

    /**
     *
     * @return
     */
    public String getOutcome() {
        String out = "/secured/aspirantes/datosGeneralesCreate.xhtml?faces-redirect=true&ap=" + ap + "&am=" + am + "&n=" + n + "&rfc=" + rfc + "&curp=" + curp + "&imss=" + imss;
        System.out.println(out);
        this.outcome = out;
        return outcome;
    }

    /**
     *
     * @param outcome
     */
    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    /**
     *
     * @param vce
     */
    public void handleChange(AjaxBehaviorEvent vce) {

        String id = ((UIComponent) vce.getSource()).getId();

        switch (id) {
            case "sel_estraA":
                String valueEstraA = (String) ((ValueHolder) vce.getSource()).getValue();
                estraA = valueEstraA;
                System.out.println(valueEstraA);
                break;
            case "sel_fntraA":
                Date value = (Date) ((ValueHolder) vce.getSource()).getValue();
                fntraA = value;
                break;
            case "sel_sitraA":
                String valueSitraA = (String) ((ValueHolder) vce.getSource()).getValue();
                sitraA = valueSitraA;
                break;
            case "sel_eitraA":
                String valueEitraA = (String) ((ValueHolder) vce.getSource()).getValue();
                eitraA = valueEitraA;
                break;
            case "sel_ditraA":
                String valueDitraA = (String) ((ValueHolder) vce.getSource()).getValue();
                ditraA = valueDitraA;
                break;
            case "rfcSearch":
                String valueRfcSearch = (String) ((ValueHolder) vce.getSource()).getValue();
                rfcSearch = valueRfcSearch;
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
        selectedTraba36a = null;
        traba36aList = null;

        retrieveTraba36aList();
        retrieveTraba36List();

        System.out.println("traba36aList.size()" + traba36aList.size());

        if (traba36aList.size() == 1) {
            selectedTraba36a = traba36aList.get(0);

            this.rfc = selectedTraba36a.getRftraA();
            this.rfc = rfc.toUpperCase();

        } else if (traba36aList.size() > 1) {

        } else {
            selectedTraba36a = null;
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

            if (imss != null && imss.length()
                    > 0) {
                searchableTraba36.setRitra(imss);
                traba36ListImss = traba36Manager.getListTraba36Wiz(searchableTraba36);
                System.out.println("Imss" + searchableTraba36.getRitra());
                searchableTraba36 = new Traba36();
            }

            if (rfcSearch
                    != null && rfcSearch.length()
                    > 0) {
                searchableTraba36.setRftra(rfcSearch.toUpperCase());
                traba36ListRfc = traba36Manager.getListTraba36Wiz(searchableTraba36);
                System.out.println("Rfc" + searchableTraba36.getRftra());
                searchableTraba36 = new Traba36();
            }
            if (curp
                    != null && curp.length()
                    > 0) {
                searchableTraba36.setRftra(null);
                searchableTraba36.setCutra(curp.toUpperCase());
                traba36ListCurp = traba36Manager.getListTraba36Wiz(searchableTraba36);
                System.out.println("Curp" + searchableTraba36.getCutra());
                searchableTraba36 = new Traba36();
            }
            if (ap != null && ap.length() > 0) {
                searchableTraba36.setAptra(ap.toUpperCase());
                ex = true;
            }
            if (am != null && am.length() > 0) {
                searchableTraba36.setAmtra(am.toUpperCase());
                ex = true;
            }
            if (n != null && n.length() > 0) {
                searchableTraba36.setNotra(n.toUpperCase());
                ex = true;
            }

            if (ex) {
                System.out.println("Paterno" + searchableTraba36.getAptra());
                System.out.println("Materno" + searchableTraba36.getAmtra());
                System.out.println("Nombres" + searchableTraba36.getNotra());

                traba36ListNc = traba36Manager.getListTraba36Wiz(searchableTraba36);
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
            List<Traba36a> traba36aListRfcAll = new ArrayList<Traba36a>();
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
            if (rfcSearch
                    != null && rfcSearch.length()
                    >= 10) {
                searchableTraba36a.setRftraA(rfcSearch.toUpperCase());
                traba36aListRfcAll = traba36aManager.getListTraba36aAll(rfcSearch);
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

            if (!traba36aListRfcAll.isEmpty()) {
                for (Traba36a traba36a : traba36aListRfcAll) {
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
    public String getSitraA() {
        return sitraA;
    }

    /**
     *
     * @param sitraA
     */
    public void setSitraA(String sitraA) {
        this.sitraA = sitraA;
    }

    /**
     *
     * @return
     */
    public String getEitraA() {
        return eitraA;
    }

    /**
     *
     * @param eitraA
     */
    public void setEitraA(String eitraA) {
        this.eitraA = eitraA;
    }

    /**
     *
     * @return
     */
    public String getDitraA() {
        return ditraA;
    }

    /**
     *
     * @param ditraA
     */
    public void setDitraA(String ditraA) {
        this.ditraA = ditraA;
    }

    /**
     *
     * @return
     */
    public Empre04ManagerLocal getEmpre04Manager() {
        return empre04Manager;
    }

    /**
     *
     * @param empre04Manager
     */
    public void setEmpre04Manager(Empre04ManagerLocal empre04Manager) {
        this.empre04Manager = empre04Manager;
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
    public Sedes53ManagerLocal getSedes53Manager() {
        return sedes53Manager;
    }

    /**
     *
     * @param sedes53Manager
     */
    public void setSedes53Manager(Sedes53ManagerLocal sedes53Manager) {
        this.sedes53Manager = sedes53Manager;
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
    public Depar03ManagerLocal getDepar03Manager() {
        return depar03Manager;
    }

    /**
     *
     * @param depar03Manager
     */
    public void setDepar03Manager(Depar03ManagerLocal depar03Manager) {
        this.depar03Manager = depar03Manager;
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
    public String getEstraA() {
        return estraA;
    }

    /**
     *
     * @param estraA
     */
    public void setEstraA(String estraA) {
        this.estraA = estraA;
    }

    /**
     *
     * @return
     */
    public Date getFntraA() {
        return fntraA;
    }

    /**
     *
     * @param fntraA
     */
    public void setFntraA(Date fntraA) {
        this.fntraA = fntraA;
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

}
