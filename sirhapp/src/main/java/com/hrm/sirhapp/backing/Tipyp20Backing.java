package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Tipyp20;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Tipyp20ManagerLocal;
import com.hrm.sirhapp.service.exception.Tipyp20NotFound;
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
public class Tipyp20Backing extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Tipyp20ManagerLocal tipyp20Manager;

    @Named
    @Produces
    @RequestScoped
    private Tipyp20 selectedTipyp20;

    private List<Tipyp20> tipyp20List;

    private List<String> tipyp20TopList;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListTipyp20();
        getTopListTipyp20();
        this.infoMessageModule = "Modulo Tipos de Premios y Prestaciones";
    }

    /**
     *
     * @return
     */
    public List<Tipyp20> getTipyp20List() {
        return tipyp20List;
    }

    /**
     *
     * @param tipyp20List
     */
    public void setTipyp20List(List<Tipyp20> tipyp20List) {
        this.tipyp20List = tipyp20List;
    }

    /**
     *
     */
    public void getTopListTipyp20() {
        tipyp20TopList = tipyp20Manager.getTopListTipyp20();
    }

    /**
     *
     * @return
     */
    public List<String> getTipyp20TopList() {
        return tipyp20TopList;
    }

    /**
     *
     * @param tipyp20TopList
     */
    public void setTipyp20TopList(List<String> tipyp20TopList) {
        this.tipyp20TopList = tipyp20TopList;
    }

    /**
     *
     * @return
     */
    public Tipyp20 getSelectedTipyp20() {
        return selectedTipyp20;
    }

    /**
     *
     * @param selectedTipyp20
     */
    public void setSelectedTipyp20(Tipyp20 selectedTipyp20) {
        this.selectedTipyp20 = selectedTipyp20;
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
     * @param idstc
     * @return
     */
    public Tipyp20 getTipyp20Request(Integer idstc) {

        try {

            this.selectedTipyp20 = tipyp20Manager.getTipyp20(idstc);

        } catch (Tipyp20NotFound ex) {
            Logger.getLogger(Tipyp20Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Tipyp20Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedTipyp20;
    }

    /**
     *
     */
    public void getListTipyp20() {

        tipyp20List = tipyp20Manager.getListTipyp20();

        if (!tipyp20List.isEmpty()) {
            infoMessage = tipyp20List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveTipyp20List() {

        tipyp20List = tipyp20Manager.retrieveTipyp20();

        if (tipyp20List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = tipyp20List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateTipyp20() {
        try {

            selectedTipyp20.setStpyp(Constants.RECORD_ACTIVED);
            selectedTipyp20.setFepyp(new Date());
            selectedTipyp20.setUspyp(FacesUtil.getUserName());

            tipyp20Manager.updateTipyp20(selectedTipyp20);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Tipyp20NotFound ex) {
            Logger.getLogger(Tipyp20Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteTipyp20() {
        try {

            selectedTipyp20.setStpyp(Constants.RECORD_DELETED);
            selectedTipyp20.setFepyp(new Date());
            selectedTipyp20.setUspyp(FacesUtil.getUserName());

            tipyp20Manager.deleteTipyp20(selectedTipyp20);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Tipyp20NotFound ex) {
            Logger.getLogger(Tipyp20Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedTipyp20 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Tipos de Premios y Prestaciones</title>"
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
        if (tipyp20List != null) {
            if (tipyp20List.isEmpty() || tipyp20List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Tipos de Premios y Prestaciones</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Tipos de Premios y Prestaciones</h1>"
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
                        + "<title>Lista de Tipos de Premios y Prestaciones</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Tipos de Premios y Prestaciones</h1>";

                for (Tipyp20 e : tipyp20List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">" + e.getNopyp() + "</td>"
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

        String outcome = "/secured/catalogos/tiposPrypList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/tiposPrypView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/tiposPrypEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/tiposPrypCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
