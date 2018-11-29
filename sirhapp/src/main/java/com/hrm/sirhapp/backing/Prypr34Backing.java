package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Prypr34;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Prypr34ManagerLocal;
import com.hrm.sirhapp.service.exception.Prypr34NotFound;
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
public class Prypr34Backing extends BaseBacking implements Serializable {

    @EJB
    private Prypr34ManagerLocal prypr34Manager;

    @Named
    @Produces
    @RequestScoped
    private Prypr34 selectedPrypr34;

    private List<Prypr34> prypr34List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo PRYP";
    }

    /**
     *
     * @return
     */
    public List<Prypr34> getPrypr34List() {
        return prypr34List;
    }

    /**
     *
     * @param prypr34List
     */
    public void setPrypr34List(List<Prypr34> prypr34List) {
        this.prypr34List = prypr34List;
    }

    /**
     *
     * @return
     */
    public Prypr34 getSelectedPrypr34() {
        return selectedPrypr34;
    }

    /**
     *
     * @param selectedPrypr34
     */
    public void setSelectedPrypr34(Prypr34 selectedPrypr34) {
        this.selectedPrypr34 = selectedPrypr34;
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
     * @param idpry
     * @return
     */
    public Prypr34 getPrypr34Request(Integer idpry) {

        try {

            this.selectedPrypr34 = prypr34Manager.getPrypr34(idpry);

        } catch (Prypr34NotFound ex) {
            Logger.getLogger(Prypr34Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Prypr34Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedPrypr34;
    }

    /**
     *
     * @param nupry
     */
    public void getListPrypr34(String nupry) {

        prypr34List = prypr34Manager.getListPrypr34(nupry);

        if (!prypr34List.isEmpty()) {
            infoMessage = prypr34List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrievePrypr34List() {

        prypr34List = prypr34Manager.retrievePrypr34();

        if (prypr34List.isEmpty()) {
            infoMessage = "No existen avisos";
        } else {
            infoMessage = prypr34List.size() + " avisos";
        }

    }

    /**
     *
     * @return
     */
    public String updatePrypr34() {
        try {

            selectedPrypr34.setStpry(Constants.RECORD_ACTIVED);
            selectedPrypr34.setFepry(new Date());
            selectedPrypr34.setUspry(FacesUtil.getUserName());

            prypr34Manager.updatePrypr34(selectedPrypr34);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Prypr34NotFound ex) {
            Logger.getLogger(Prypr34Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deletePrypr34() {
        try {

            selectedPrypr34.setStpry(Constants.RECORD_DELETED);
            selectedPrypr34.setFepry(new Date());
            selectedPrypr34.setUspry(FacesUtil.getUserName());

            prypr34Manager.deletePrypr34(selectedPrypr34);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Prypr34NotFound ex) {
            Logger.getLogger(Prypr34Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedPrypr34 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>APRIV</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>APRIV</h1>"
                    + "<div class=\"page-content\">"
                    + "<table class=\"table-content\">"
                    + "<tr>"
                    + "<td width=\"160px\"><img width=\"160\"  src=\"http://sirhapp.dynu.net/sirhapp/files/" + this.selectedPrypr34.getPspry() + "\" /></td>"
                    + "<td class=\"separador\"></td>"
                    + "<td>"
                    + "<table>"
                    + "<tr>"
                    + "<td class=\"titulo\">RFC:</td>"
                    + "<td class=\"dato\">" + this.selectedPrypr34.getRfpry() + "</td>"
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

        if (prypr34List.isEmpty() || prypr34List.size() == 0) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>LISTA DE APRIV</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>LISTA DE APRIV</h1>"
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
                    + "<title>LISTA DE APRIV</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>LISTA DE APRIV: " + this.prypr34List.get(0).getRfpry() + "</h1>";

            for (Prypr34 e : prypr34List) {
                benef = benef + "<div class=\"page-content-border\">"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">TIPO:</td>"
                        + "<td class=\"dato\">" + e.getTipry() + "</td>"
                        + "</tr>"
                        + "</table>"
                        + "</div><hr/>";
            }

            str = str + benef + "</body>"
                    + "</html>";

        }
        this.contentListPdf = str;

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

        String outcome = "/secured/contratos/prypr.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/contratos/verPrypr.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/contratos/edicionPrypr.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/contratos/nuevoPrypr.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
