package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Contr27;
import com.hrm.sirhapp.model.Traba36;
import com.hrm.sirhapp.model.Usuar24;
import com.hrm.sirhapp.service.Contr27ManagerLocal;
import com.hrm.sirhapp.service.Traba36ManagerLocal;
import com.hrm.sirhapp.service.exception.Contr27NotFound;
import com.hrm.sirhapp.service.exception.Traba36NotFound;
import com.hrm.sirhapp.util.LanguagesUtil;
import com.hrm.sirhapp.util.FacesUtil;
import java.io.IOException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@ViewScoped
public class Contr27Backing extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Contr27ManagerLocal contr27Manager;

    @EJB
    private Traba36ManagerLocal traba36Manager;

    @Named
    @Produces
    @RequestScoped
    private Contr27 selectedContr27;

    @Named
    @Produces
    @RequestScoped
    private Traba36 selectedTraba36Cto;

    private List<Contr27> contr27List;
    private String rfc;
    private String rfcSearch;
    private String curp;
    private String ap;
    private String am;
    private String n;
    private String imss;
    private Integer idcto;
    private Integer nt;
    private String nucto;
    private Boolean submit;
    private String path;

    private boolean active;
    private String infoMessageModule;
    private String infoMessageTra;
    private String infoMessageCto;
    private String infoMessage;

    private String contentPdf;

    private List<Traba36> traba36List;

    @PostConstruct
    private void init() {
        this.active = true;
        this.submit = true;
        this.infoMessageModule = "Modulo Contratos";
    }

    /**
     *
     * @return
     */
    public String getNucto() {
        return nucto;
    }

    /**
     *
     * @param nucto
     */
    public void setNucto(String nucto) {
        this.nucto = nucto;
    }

    /**
     *
     * @return
     */
    public Contr27ManagerLocal getContr27Manager() {
        return contr27Manager;
    }

    /**
     *
     * @param contr27Manager
     */
    public void setContr27Manager(Contr27ManagerLocal contr27Manager) {
        this.contr27Manager = contr27Manager;
    }

    /**
     *
     * @return
     */
    public List<Contr27> getContr27List() {
        return contr27List;
    }

    /**
     *
     * @param contr27List
     */
    public void setContr27RequestList(List<Contr27> contr27List) {
        this.contr27List = contr27List;
    }

    /**
     *
     * @return
     */
    public Contr27 getSelectedContr27() {
        return selectedContr27;
    }

    /**
     *
     * @param contr27
     */
    public void setSelectedContr27(Contr27 contr27) {
        this.selectedContr27 = contr27;
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
    public Integer getIdcto() {
        return idcto;
    }

    /**
     *
     * @param idcto
     */
    public void setIdcto(Integer idcto) {
        this.idcto = idcto;
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
    public Boolean getSubmit() {
        return submit;
    }

    /**
     *
     * @param submit
     */
    public void setSubmit(Boolean submit) {
        this.submit = submit;
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
    public String getInfoMessageCto() {
        return infoMessageCto;
    }

    /**
     *
     * @param infoMessageCto
     */
    public void setInfoMessageCto(String infoMessageCto) {
        this.infoMessageCto = infoMessageCto;
    }

    /**
     *
     * @return
     */
    public Traba36 getSelectedTraba36Cto() {
        return selectedTraba36Cto;
    }

    /**
     *
     * @param selectedTraba36Cto
     */
    public void setSelectedTraba36Cto(Traba36 selectedTraba36Cto) {
        this.selectedTraba36Cto = selectedTraba36Cto;
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
     * @param idcto
     * @return
     */
    public Contr27 getContr27Request(Integer idcto) {

        try {
            UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);

            System.out.println(userSessionBean.getContratoTrabajador());
            this.selectedContr27 = contr27Manager.getContr27(idcto);
            this.selectedTraba36Cto = getTraba36Request(userSessionBean.getContratoTrabajador());

            if (contr27Manager.alreadyExistsInnactive(idcto)) {
                this.active = false;
            }

        } catch (Contr27NotFound ex) {
            Logger.getLogger(Contr27Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        } catch (Exception ex) {
            Logger.getLogger(Contr27Backing.class.getName()).log(Level.SEVERE, null, ex);
            //getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
            infoMessage = new LanguagesUtil().getResources("reports.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return selectedContr27;
    }

    /**
     *
     * @param nucto
     */
    public void getNtctoNucto(String nucto) {
        Integer nt = null;
        nt = contr27Manager.getNtctoNucto(nucto);
        if (nt != null) {
            this.nt = nt;
        }

    }

    /**
     *
     * @return
     */
    public String retrieveContr27List() {

        contr27List = contr27Manager.retrieveContr27();

        if (contr27List.isEmpty()) {
            infoMessage = "No se encontraron contratos.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } else {
            infoMessage = contr27List.size() + " contratos.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        }

        return infoMessage;

    }

    /**
     *
     * @return
     */
    public String retrieveContr27ListNtcto() {

        if (nt != null) {
            contr27List = contr27Manager.getListContr27Ntcto(nt);

            if (contr27List.isEmpty()) {
                infoMessageCto = "No se encontraron contratos.";

            } else {
                infoMessageCto = contr27List.size() + " contratos.";

            }
        } else {
            infoMessage = "Proporcione un numero de trabajador.";
        }

        return infoMessage;
    }

    /**
     *
     * @return
     */
    public String updateContr27Foto() {

        if (selectedContr27 != null) {
            selectedContr27.setPacto(null);
        }
        FacesUtil.removeManagedBeanInSession("fileUploadBean");
        String oldInfoMessageModule = infoMessageModule;
        infoMessageModule = "Contrato";
        infoMessage = "Se borro el Documento del Contrato";
        FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
        infoMessageModule = oldInfoMessageModule;
        updateContr27();
        return "success";
    }

    /**
     *
     * @return
     */
    public String acivateContr27() {
        try {

            if (contr27Manager.alreadyExistsInnactive(selectedContr27.getIdcto())) {
                infoMessage = "El contrato fue activado correctamente";
                FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            }

            selectedContr27.setStcto(Constants.RECORD_ACTIVED);
            selectedContr27.setFecto(new Date());
            selectedContr27.setUscto(FacesUtil.getUserName());

            contr27Manager.updateContr27(selectedContr27);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Contr27NotFound ex) {
            Logger.getLogger(Contr27Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String updateContr27() {
        try {

            if (contr27Manager.alreadyExistsInnactive(selectedContr27.getIdcto())) {
                infoMessage = "El contrato fue activado correctamente";
                FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            }

            if (path != null) {
                selectedContr27.setPacto(path);
            }

            selectedContr27.setStcto(Constants.RECORD_ACTIVED);
            selectedContr27.setFecto(new Date());
            selectedContr27.setUscto(FacesUtil.getUserName());

            selectedContr27 = contr27Manager.updateContr27(selectedContr27);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Contr27NotFound ex) {
            Logger.getLogger(Contr27Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteContr27() {
        try {

            selectedContr27.setStcto(Constants.RECORD_DELETED);
            selectedContr27.setFecto(new Date());
            selectedContr27.setUscto(FacesUtil.getUserName());

            contr27Manager.deleteContr27(selectedContr27);

            infoMessage = "El contrato se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Contr27NotFound ex) {
            Logger.getLogger(Contr27Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String getContentPdf() {
        String str;

        if (selectedContr27 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>DATOS DEL CONTRATO</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>DATOS DEL CONTRATO</h1>"
                    + "<div class=\"page-content\">"
                    + "<table class=\"table-content\">"
                    + "<tr>"
                    + "<td width=\"160px\"><img width=\"160\"  src=\"http://sirhapp.dynu.net/sirhapp/files/" + this.selectedContr27.getPacto() + "\" /></td>"
                    + "<td class=\"separador\"></td>"
                    + "<td>"
                    + "<table>"
                    + "<tr>"
                    + "<td class=\"titulo\">NOMBRE:</td>"
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

        String outcome = "/secured/contratos/contratoView.xhtml?faces-redirect=true";

        if (action == 1) {
            outcome = "/secured/contratos/contratoEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/contratos/contratoCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

    /**
     *
     * @return
     */
    public String retrieveTraba36ListActionListener() {
        selectedTraba36Cto = null;
        traba36List = null;

        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);
        userSessionBean.setContratoTrabajador(-1);
        
        if (nucto != null) {
            getNtctoNucto(nucto);
        }

        retrieveTraba36List();

        if (traba36List.size() == 1) {
            selectedTraba36Cto = traba36List.get(0);

            this.nt = selectedTraba36Cto.getNutra();
            userSessionBean.setContratoTrabajador(nt);

        } else if (traba36List.size() > 1) {
        } else {
            selectedTraba36Cto = null;
            infoMessage = "1. EL TRABAJADOR NO ESTA REGISTRADO.<BR/>"
                    + "2. EL CONTRATO NO EXISTE.<BR/>"
                    + "3. NO TIENE PERMISOS.<BR/>"
                    + "NOTA: VALIDE LA INFORMACIÓN O CONTACTE AL PERSONAL DE SISTEMAS PARA QUE LE ASESORE";
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
     * @param nutra
     * @return
     */
    public Traba36 getTraba36Request(Integer nutra) {

        try {

            this.selectedTraba36Cto = traba36Manager.getTraba36(nutra);
            this.nt = selectedTraba36Cto.getNutra();

        } catch (Traba36NotFound ex) {
            Logger.getLogger(Traba36aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("reports.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return selectedTraba36Cto;
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
