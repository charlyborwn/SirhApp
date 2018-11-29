package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Apriv25;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Apriv25ManagerLocal;
import com.hrm.sirhapp.service.exception.Apriv25NotFound;
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
public class Apriv25Backing extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Apriv25ManagerLocal apriv25Manager;

    @Named
    @Produces
    @RequestScoped
    private Apriv25 selectedApriv25;

    private List<Apriv25> apriv25List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo APRIV";
    }

    /**
     *
     * @return
     */
    public List<Apriv25> getApriv25List() {
        return apriv25List;
    }

    /**
     *
     * @param apriv25List
     */
    public void setApriv25List(List<Apriv25> apriv25List) {
        this.apriv25List = apriv25List;
    }

    /**
     *
     * @return
     */
    public Apriv25 getSelectedApriv25() {
        return selectedApriv25;
    }

    /**
     *
     * @param selectedApriv25
     */
    public void setSelectedApriv25(Apriv25 selectedApriv25) {
        this.selectedApriv25 = selectedApriv25;
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
     * @param idapr
     * @return
     */
    public Apriv25 getApriv25Request(Integer idapr) {

        try {

            this.selectedApriv25 = apriv25Manager.getApriv25(idapr);

        } catch (Apriv25NotFound ex) {
            Logger.getLogger(Apriv25Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Apriv25Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedApriv25;
    }

    /**
     *
     * @param nuapr
     */
    public void getListApriv25(String nuapr) {

        apriv25List = apriv25Manager.getListApriv25(nuapr);

        if (!apriv25List.isEmpty()) {
            infoMessage = apriv25List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveApriv25List() {

        apriv25List = apriv25Manager.retrieveApriv25();

        if (apriv25List.isEmpty()) {
            infoMessage = "No existen avisos";
        } else {
            infoMessage = apriv25List.size() + " registro(s)";
        }

    }

    /**
     *
     * @return
     */
    public String updateApriv25() {
        try {

            selectedApriv25.setStapr(Constants.RECORD_ACTIVED);
            selectedApriv25.setFeapr(new Date());
            selectedApriv25.setUsapr(FacesUtil.getUserName());

            apriv25Manager.updateApriv25(selectedApriv25);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Apriv25NotFound ex) {
            Logger.getLogger(Apriv25Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteApriv25() {
        try {

            selectedApriv25.setStapr(Constants.RECORD_DELETED);
            selectedApriv25.setFeapr(new Date());
            selectedApriv25.setUsapr(FacesUtil.getUserName());

            apriv25Manager.deleteApriv25(selectedApriv25);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Apriv25NotFound ex) {
            Logger.getLogger(Apriv25Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedApriv25 != null) {
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
                    + "<td width=\"160px\"><img width=\"160\"  src=\"http://sirhapp.dynu.net/sirhapp/files/" + this.selectedApriv25.getP1apr() + "\" /></td>"
                    + "<td class=\"separador\"></td>"
                    + "<td>"
                    + "<table>"
                    + "<tr>"
                    + "<td class=\"titulo\">RFC:</td>"
                    + "<td class=\"dato\">" + this.selectedApriv25.getRfapr() + "</td>"
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

        if (apriv25List.isEmpty() || apriv25List.size() == 0) {
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
                    + "<h1>LISTA DE APRIV: " + this.apriv25List.get(0).getRfapr() + "</h1>";

            for (Apriv25 e : apriv25List) {
                benef = benef + "<div class=\"page-content-border\">"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">TIPO:</td>"
                        + "<td class=\"dato\">" + e.getTiapr() + "</td>"
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

        String outcome = "/secured/contratos/aprivList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/contratos/aprivView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/contratos/aprivEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/contratos/aprivCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
