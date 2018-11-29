package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Exper31a;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Exper31aManagerLocal;
import com.hrm.sirhapp.service.exception.Exper31aNotFound;
import com.hrm.sirhapp.util.FacesUtil;
import com.hrm.sirhapp.util.LanguagesUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Exper31aBacking extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Exper31aManagerLocal exper31aManager;

    @Named
    @Produces
    @RequestScoped
    private Exper31a selectedExper31a;

    private List<Exper31a> exper31aList;

    private int status = 1;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    /**
     *
     * @return
     */
    public List<Exper31a> getExper31aList() {
        return exper31aList;
    }

    /**
     *
     * @param exper31aList
     */
    public void setExper31aRequestList(List<Exper31a> exper31aList) {
        this.exper31aList = exper31aList;
    }

    /**
     *
     * @return
     */
    public Exper31a getSelectedExper31a() {
        return selectedExper31a;
    }

    /**
     *
     * @param selectedExper31a
     */
    public void setSelectedExper31a(Exper31a selectedExper31a) {
        this.selectedExper31a = selectedExper31a;
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
     * @param idbenA
     * @return
     */
    public Exper31a getExper31aRequest(Integer idbenA) {

        try {

            this.selectedExper31a = exper31aManager.getExper31a(idbenA);

        } catch (Exper31aNotFound ex) {
            Logger.getLogger(Exper31aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Exper31aBacking.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedExper31a;
    }

    /**
     *
     * @param rfbenA
     */
    public void getListExper31a(String rfbenA) {

        exper31aList = exper31aManager.getListExper31a(rfbenA);

        if (exper31aList.isEmpty()) {
            infoMessage = "No existen experiencias!";
        } else {
            infoMessage = exper31aList.size() + " Experiencias";
        }
    }

    /**
     *
     */
    public void retrieveExper31aList() {

        exper31aList = exper31aManager.retrieveExper31a();

        if (exper31aList.isEmpty()) {
            infoMessage = "No existen experiencias!";
        } else {
            infoMessage = exper31aList.size() + " Experiencias";
        }

    }

    /**
     *
     * @return
     */
    public String updateExper31a() {
        try {

            selectedExper31a.setStexpA(Constants.RECORD_ACTIVED);
            selectedExper31a.setFeexpA(new Date());
            selectedExper31a.setUsexpA(FacesUtil.getUserName());

            exper31aManager.updateExper31a(selectedExper31a);

            infoMessage = "Aspirante actualizado correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Exper31aNotFound ex) {
            Logger.getLogger(Exper31aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al actualizar los datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        }
        return null;

    }

    /**
     *
     * @return
     */
    public String deleteExper31a() {
        try {

            selectedExper31a.setStexpA(Constants.RECORD_DELETED);
            selectedExper31a.setFeexpA(new Date());
            selectedExper31a.setUsexpA(FacesUtil.getUserName());

            exper31aManager.deleteExper31a(selectedExper31a);

            infoMessage = "Experiencia eliminada correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Exper31aNotFound ex) {
            Logger.getLogger(Exper31aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al eliminar los datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        }
        return null;

    }

    /**
     *
     * @return
     */
    public String getContentPdf() {
        String str = null;

        if (selectedExper31a != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>BENEFICIARIO DEL ASPIRANTE</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>BENEFICIARIO DEL ASPIRANTE:" + this.selectedExper31a.getRfexpA() + "</h1>"
                    + "<div class=\"page-content\">"
                    + "<table class=\"table-content\">"
                    + "<tr>"
                    + "<td class=\"separador\"></td>"
                    + "<td>"
                    + "<table>"
                    + "<tr>"
                    + "<td class=\"titulo\">TIPO:</td>"
                    + "<td class=\"dato\">" + this.selectedExper31a.getTiexpA() + "</td>"
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

        if (exper31aList.isEmpty() || exper31aList.size() == 0) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>LISTA DE BENEFICIARIOS DEL ASPIRANTE</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>LISTA DE BENEFICIARIOS DEL ASPIRANTE</h1>"
                    + "<div class=\"page-content\">"
                    + "<table class=\"table-content\">"
                    + "<tr>"
                    + "<td>No existen experiencias</td>"
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
                    + "<title>LISTA DE BENEFICIARIOS DEL ASPIRANTE</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>LISTA DE BENEFICIARIOS DEL ASPIRANTE: " + this.exper31aList.get(0).getRfexpA() + "</h1>";

            for (Exper31a e : exper31aList) {
                benef = benef + "<div class=\"page-content-border\">"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">TIPO:</td>"
                        + "<td class=\"dato\">" + e.getTiexpA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">NOMBRE COMPLETO:</td>"
                        + "<td class=\"dato\">" + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">OBSERVACIONES:</td>"
                        + "<td class=\"dato\">" + e.getObexpA() + "</td>"
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

        String outcome = "/secured/aspirantes/experienciaList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/aspirantes/experienciaView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/aspirantes/experienciaEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/aspirantes/experienciaCreate.xhtml?faces-redirect=true";
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

}
