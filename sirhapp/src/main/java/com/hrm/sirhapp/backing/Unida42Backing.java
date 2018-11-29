package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Unida42;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Unida42ManagerLocal;
import com.hrm.sirhapp.service.exception.Unida42NotFound;
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
public class Unida42Backing extends BaseBacking implements Serializable {

    @EJB
    private Unida42ManagerLocal unida42Manager;

    @Named
    @Produces
    @RequestScoped
    private Unida42 selectedUnida42;

    private List<Unida42> unida42List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Unidades de Transporte";
    }

    /**
     *
     * @return
     */
    public List<Unida42> getUnida42List() {
        return unida42List;
    }

    /**
     *
     * @param unida42List
     */
    public void setUnida42List(List<Unida42> unida42List) {
        this.unida42List = unida42List;
    }

    /**
     *
     * @return
     */
    public Unida42 getSelectedUnida42() {
        return selectedUnida42;
    }

    /**
     *
     * @param selectedUnida42
     */
    public void setSelectedUnida42(Unida42 selectedUnida42) {
        this.selectedUnida42 = selectedUnida42;
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
     * @param iduni
     * @return
     */
    public Unida42 getUnida42Request(Integer iduni) {

        try {

            this.selectedUnida42 = unida42Manager.getUnida42(iduni);

        } catch (Unida42NotFound ex) {
            Logger.getLogger(Unida42Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Unida42Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedUnida42;
    }

    /**
     *
     */
    public void getListUnida42() {

        unida42List = unida42Manager.getListUnida42();

        if (!unida42List.isEmpty()) {
            infoMessage = unida42List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveUnida42List() {

        unida42List = unida42Manager.retrieveUnida42();

        if (unida42List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = unida42List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateUnida42() {
        try {

            selectedUnida42.setStuni(Constants.RECORD_ACTIVED);
            selectedUnida42.setFeuni(new Date());
            selectedUnida42.setUsuni(FacesUtil.getUserName());

            unida42Manager.updateUnida42(selectedUnida42);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Unida42NotFound ex) {
            Logger.getLogger(Unida42Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteUnida42() {
        try {

            selectedUnida42.setStuni(Constants.RECORD_DELETED);
            selectedUnida42.setFeuni(new Date());
            selectedUnida42.setUsuni(FacesUtil.getUserName());

            unida42Manager.deleteUnida42(selectedUnida42);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Unida42NotFound ex) {
            Logger.getLogger(Unida42Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedUnida42 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Unidades de Transporte</title>"
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
        if (unida42List != null) {
            if (unida42List.isEmpty() || unida42List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Unidades de Transporte</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Unidades de Transporte</h1>"
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
                        + "<title>Lista de Unidades de Transporte</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Unidades de Transporte</h1>";

                for (Unida42 e : unida42List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">" + e.getNuuni() + "</td>"
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

        String outcome = "/secured/catalogos/unidadesTransporteList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/unidadesTransporteView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/unidadesTransporteEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/unidadesTransporteCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
