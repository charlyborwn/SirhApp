package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Infor39;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Infor39ManagerLocal;
import com.hrm.sirhapp.service.exception.Infor39NotFound;
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
public class Infor39Backing extends BaseBacking implements Serializable {

    @EJB
    private Infor39ManagerLocal infor39Manager;

    @Named
    @Produces
    @RequestScoped
    private Infor39 selectedInfor39;

    private List<Infor39> infor39List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Archivos de Información";
    }

    /**
     *
     * @return
     */
    public List<Infor39> getInfor39List() {
        return infor39List;
    }

    /**
     *
     * @param infor39List
     */
    public void setInfor39List(List<Infor39> infor39List) {
        this.infor39List = infor39List;
    }

    /**
     *
     * @return
     */
    public Infor39 getSelectedInfor39() {
        return selectedInfor39;
    }

    /**
     *
     * @param selectedInfor39
     */
    public void setSelectedInfor39(Infor39 selectedInfor39) {
        this.selectedInfor39 = selectedInfor39;
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
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     *
     * @param idubi
     * @return
     */
    public Infor39 getInfor39Request(Integer idubi) {

        try {

            this.selectedInfor39 = infor39Manager.getInfor39(idubi);

        } catch (Infor39NotFound ex) {
            Logger.getLogger(Infor39Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Infor39Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedInfor39;
    }

    /**
     *
     */
    public void getListInfor39() {

        infor39List = infor39Manager.getListInfor39();

        if (!infor39List.isEmpty()) {
            infoMessage = infor39List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveInfor39List() {

        infor39List = infor39Manager.retrieveInfor39();

        if (infor39List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = infor39List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateInfor39() {
        try {

            selectedInfor39.setStinf(Constants.RECORD_ACTIVED);
            selectedInfor39.setFeinf(new Date());
            selectedInfor39.setUsinf(FacesUtil.getUserName());

            infor39Manager.updateInfor39(selectedInfor39);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Infor39NotFound ex) {
            Logger.getLogger(Infor39Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteInfor39() {
        try {

            selectedInfor39.setStinf(Constants.RECORD_DELETED);
            selectedInfor39.setFeinf(new Date());
            selectedInfor39.setUsinf(FacesUtil.getUserName());

            infor39Manager.deleteInfor39(selectedInfor39);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Infor39NotFound ex) {
            Logger.getLogger(Infor39Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedInfor39 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Archivos de Información</title>"
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
        if (infor39List != null) {
            if (infor39List.isEmpty() || infor39List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Archivos de Información</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Archivos de Información</h1>"
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
                        + "<title>Lista de Archivos de Información</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Archivos de Información</h1>";

                for (Infor39 e : infor39List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">" + e.getTiinf() + "</td>"
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

        String outcome = "/secured/catalogos/archivosInformacionList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/archivosInformacionView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/archivosInformacionEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/archivosInformacionCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
