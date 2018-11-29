package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Sangr21;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Sangr21ManagerLocal;
import com.hrm.sirhapp.service.exception.Sangr21NotFound;
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
public class Sangr21Backing extends BaseBacking implements Serializable {

    @EJB
    private Sangr21ManagerLocal sangr21Manager;

    @Named
    @Produces
    @RequestScoped
    private Sangr21 selectedSangr21;

    private List<Sangr21> sangr21List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListSangr21();
        this.infoMessageModule = "Modulo Tipos de Sangre";
    }

    /**
     *
     * @return
     */
    public List<Sangr21> getSangr21List() {
        return sangr21List;
    }

    /**
     *
     * @param sangr21List
     */
    public void setSangr21List(List<Sangr21> sangr21List) {
        this.sangr21List = sangr21List;
    }

    /**
     *
     * @return
     */
    public Sangr21 getSelectedSangr21() {
        return selectedSangr21;
    }

    /**
     *
     * @param selectedSangr21
     */
    public void setSelectedSangr21(Sangr21 selectedSangr21) {
        this.selectedSangr21 = selectedSangr21;
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
     * @param idstc
     * @return
     */
    public Sangr21 getSangr21Request(Integer idstc) {

        try {

            this.selectedSangr21 = sangr21Manager.getSangr21(idstc);

        } catch (Sangr21NotFound ex) {
            Logger.getLogger(Sangr21Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Sangr21Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedSangr21;
    }

    /**
     *
     */
    public void getListSangr21() {

        sangr21List = sangr21Manager.getListSangr21();

        if (!sangr21List.isEmpty()) {
            infoMessage = sangr21List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveSangr21List() {

        sangr21List = sangr21Manager.retrieveSangr21();

        if (sangr21List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = sangr21List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateSangr21() {
        try {

            

            selectedSangr21.setStsan(Constants.RECORD_ACTIVED);
            selectedSangr21.setFesan(new Date());
            selectedSangr21.setUssan(FacesUtil.getUserName());

            sangr21Manager.updateSangr21(selectedSangr21);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Sangr21NotFound ex) {
            Logger.getLogger(Sangr21Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteSangr21() {
        try {
            

            selectedSangr21.setStsan(Constants.RECORD_DELETED);
            selectedSangr21.setFesan(new Date());
            selectedSangr21.setUssan(FacesUtil.getUserName());

            sangr21Manager.deleteSangr21(selectedSangr21);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Sangr21NotFound ex) {
            Logger.getLogger(Sangr21Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedSangr21 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Tipos de Sangre</title>"
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
        if (sangr21List != null) {
            if (sangr21List.isEmpty() || sangr21List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Tipos de Sangre</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Tipos de Sangre</h1>"
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
                        + "<title>Lista de Tipos de Sangre</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Tipos de Sangre</h1>";

                for (Sangr21 e : sangr21List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">" + e.getCvsan() + "</td>"
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

        String outcome = "/secured/catalogos/tiposSangreList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/tiposSangreView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/tiposSangreEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/tiposSangreCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
