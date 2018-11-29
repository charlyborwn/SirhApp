package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Tipsa47;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Tipsa47ManagerLocal;
import com.hrm.sirhapp.service.exception.Tipsa47NotFound;
import com.hrm.sirhapp.util.FacesUtil;
import com.hrm.sirhapp.util.LanguagesUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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
public class Tipsa47Backing extends BaseBacking implements Serializable {
    
     private static final long serialVersionUID = 1L;

    @EJB
    private Tipsa47ManagerLocal tipsa47Manager;

    @Named
    @Produces
    @RequestScoped
    private Tipsa47 selectedTipsa47;

    private List<Tipsa47> tipsa47List;

    private List<String> tipsa47TopList;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListTipsa47();
        getTopListTipsa47();
        this.infoMessageModule = "Modulo Tipos Sanciones";
    }

    /**
     *
     * @return
     */
    public List<Tipsa47> getTipsa47List() {
        return tipsa47List;
    }

    /**
     *
     * @param tipsa47List
     */
    public void setTipsa47List(List<Tipsa47> tipsa47List) {
        this.tipsa47List = tipsa47List;
    }

    /**
     *
     */
    public void getTopListTipsa47() {
        tipsa47TopList = tipsa47Manager.getTopListTipsa47();
    }

    /**
     *
     * @return
     */
    public List<String> getTipsa47TopList() {
        return tipsa47TopList;
    }

    /**
     *
     * @param tipsa47TopList
     */
    public void setTipsa47TopList(List<String> tipsa47TopList) {
        this.tipsa47TopList = tipsa47TopList;
    }

    /**
     *
     * @return
     */
    public Tipsa47 getSelectedTipsa47() {
        return selectedTipsa47;
    }

    /**
     *
     * @param selectedTipsa47
     */
    public void setSelectedTipsa47(Tipsa47 selectedTipsa47) {
        this.selectedTipsa47 = selectedTipsa47;
    }

    /**
     *
     * @return
     */
    public int getStatus() {
        return status;
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
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     *
     * @param idpsa
     * @return
     */
    public Tipsa47 getTipsa47Request(Integer idpsa) {

        try {

            this.selectedTipsa47 = tipsa47Manager.getTipsa47(idpsa);

        } catch (Tipsa47NotFound ex) {
            Logger.getLogger(Tipsa47Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Tipsa47Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedTipsa47;
    }

    /**
     *
     */
    public void getListTipsa47() {

        tipsa47List = tipsa47Manager.getListTipsa47();

        if (!tipsa47List.isEmpty()) {
            infoMessage = tipsa47List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveTipsa47List() {

        tipsa47List = tipsa47Manager.retrieveTipsa47();

        if (tipsa47List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = tipsa47List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateTipsa47() {
        try {

            selectedTipsa47.setStpsa(Constants.RECORD_ACTIVED);
            selectedTipsa47.setFepsa(new Date());
            selectedTipsa47.setUspsa(FacesUtil.getUserName());

            tipsa47Manager.updateTipsa47(selectedTipsa47);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Tipsa47NotFound ex) {
            Logger.getLogger(Tipsa47Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteTipsa47() {
        try {

            selectedTipsa47.setStpsa(Constants.RECORD_DELETED);
            selectedTipsa47.setFepsa(new Date());
            selectedTipsa47.setUspsa(FacesUtil.getUserName());

            tipsa47Manager.deleteTipsa47(selectedTipsa47);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Tipsa47NotFound ex) {
            Logger.getLogger(Tipsa47Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedTipsa47 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Tipos Sanciones</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>APRIV</h1>"
                    + "<div class=\"page-content\">"
                    + "<table class=\"table-content\">"
                    + "<tr>"
                    + "<td width=\"160px\"><img width=\"160\"  src=\"http://sirhapp.dynu.net/sirhapp/files/" + "\" /></td>"
                    + "<td class=\"separador\"></td>"
                    + "<td>"
                    + "<table>"
                    + "<tr>"
                    + "<td class=\"titulo\">RFC:</td>"
                    + "<td class=\"dato\">" + "</td>"
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
     * @return
     */
    public String getContentListPdf() {
        String str = null;
        String benef = "";
        if (tipsa47List != null) {
            if (tipsa47List.isEmpty() || tipsa47List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Tipos Sanciones</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Tipos Sanciones</h1>"
                        + "<div class=\"page-content\">"
                        + "<table class=\"table-content\">"
                        + "<tr>"
                        + "<td>No existen datos</td>"
                        + "</tr>"
                        + "</table>"
                        + "</div>"
                        + "</body>"
                        + "</html>";
            } else {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Tipos Sanciones</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Tipos Sanciones</h1>";

                for (Tipsa47 e : tipsa47List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">" + e.getNepsa() + "</td>"
                            + "</tr>"
                            + "</table>"
                            + "</div><hr/>";
                }

                str = str + benef + "</body>"
                        + "</html>";

            }
        }
        this.contentListPdf = str + "";

        return contentListPdf;
    }

    /**
     *
     * @param contentListPdf
     */
    public void setContentListPdf(String contentListPdf) {
        this.contentListPdf = contentListPdf;
    }

    /**
     *
     * @param action
     * @return
     * @throws IOException
     */
    public String go(int action) throws IOException {

        String outcome = "/secured/catalogos/tiposSancionesList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/tiposSancionesView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/tiposSancionesEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/tiposSancionesCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
