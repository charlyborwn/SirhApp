package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Benef26a;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Benef26aManagerLocal;
import com.hrm.sirhapp.service.exception.Benef26aNotFound;
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
public class Benef26aBacking extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Benef26aManagerLocal benef26aManager;

    @Named
    @Produces
    @RequestScoped
    private Benef26a selectedBenef26a;

    private List<Benef26a> benef26aList;

    private int status = 1;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;
    private Float percentage;

    /**
     *
     * @return
     */
    public List<Benef26a> getBenef26aList() {
        return benef26aList;
    }

    /**
     *
     * @param benef26aList
     */
    public void setBenef26aRequestList(List<Benef26a> benef26aList) {
        this.benef26aList = benef26aList;
    }

    /**
     *
     * @return
     */
    public Benef26a getSelectedBenef26a() {
        return selectedBenef26a;
    }

    /**
     *
     * @param selectedBenef26a
     */
    public void setSelectedBenef26a(Benef26a selectedBenef26a) {
        this.selectedBenef26a = selectedBenef26a;
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
     * @return
     */
    public Float getPercentage() {
        return percentage;
    }

    /**
     *
     * @param percentage
     */
    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    /**
     *
     * @param rfbenA
     */
    public void getPercentage(String rfbenA) {
        if (percentage == null) {
            Float result = benef26aManager.getPercentage(rfbenA);
            this.percentage = result;
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Benef26a getBenef26aRequest(Long id) {

        try {

            this.selectedBenef26a = benef26aManager.getBenef26a(id);

        } catch (Exception ex) {
            Logger.getLogger(Benef26aBacking.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedBenef26a;
    }

    /**
     *
     * @param rfbenA
     */
    public void getListBenef26a(String rfbenA) {

        benef26aList = benef26aManager.getListBenef26a(rfbenA);

        if (benef26aList.isEmpty()) {
            infoMessage = "No existen beneficiarios!";
        } else {
            infoMessage = benef26aList.size() + " Beneficiarios";
        }
    }

    /**
     *
     */
    public void retrieveBenef26aList() {

        benef26aList = benef26aManager.retrieveBenef26a();

        if (benef26aList.isEmpty()) {
            infoMessage = "No existen beneficiarios!";
        } else {
            infoMessage = benef26aList.size() + " Beneficiarios";
        }

    }

    /**
     *
     * @return
     */
    public String updateBenef26a() {
        try {

            selectedBenef26a.setStbenA(Constants.RECORD_ACTIVED);
            selectedBenef26a.setFebenA(new Date());
            selectedBenef26a.setUsbenA(FacesUtil.getUserName());

            benef26aManager.updateBenef26a(selectedBenef26a);

            infoMessage = "Aspirante actualizado correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Benef26aNotFound ex) {
            Logger.getLogger(Benef26aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al actualizar los datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        }
        return null;

    }

    /**
     *
     * @return
     */
    public String deleteBenef26a() {
        try {

            selectedBenef26a.setStbenA(Constants.RECORD_DELETED);
            selectedBenef26a.setFebenA(new Date());
            selectedBenef26a.setUsbenA(FacesUtil.getUserName());

            benef26aManager.deleteBenef26a(selectedBenef26a);

            infoMessage = "Beneficiario eliminado correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Benef26aNotFound ex) {
            Logger.getLogger(Benef26aBacking.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedBenef26a != null) {
            if (selectedBenef26a.getRfbenA().length() > 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>BENEFICIARIO DEL ASPIRANTE</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>BENEFICIARIO DEL ASPIRANTE:" + this.selectedBenef26a.getRfbenA() + "</h1>"
                        + "<div class=\"page-content\">"
                        + "<table class=\"table-content\">"
                        + "<tr>"
                        + "<td class=\"separador\"></td>"
                        + "<td>"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">TIPO:</td>"
                        + "<td class=\"dato\">" + this.selectedBenef26a.getTpbenA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">GENERO:</td>"
                        + "<td class=\"dato\">" + this.selectedBenef26a.getSebenA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">FECHA DE NACIMIENTO:</td>"
                        + "<td class=\"dato\">" + this.selectedBenef26a.getFnbenA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">PORCENTAJE A OTORGAR:</td>"
                        + "<td class=\"dato\">" + this.selectedBenef26a.getPobenA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">NOMBRE COMPLETO:</td>"
                        + "<td class=\"dato\">" + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">OBSERVACIONES:</td>"
                        + "<td class=\"dato\">" + this.selectedBenef26a.getObbenA() + "</td>"
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
     * @return
     */
    public String getContentListPdf() {
        String str = null;
        String benef = "";

        if (benef26aList.isEmpty() || benef26aList.size() == 0) {
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
                    + "<td>No existen beneficiarios</td>"
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
                    + "<h1>LISTA DE BENEFICIARIOS DEL ASPIRANTE: " + this.benef26aList.get(0).getRfbenA() + "</h1>";

            for (Benef26a e : benef26aList) {
                benef = benef + "<div class=\"page-content-border\">"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">TIPO:</td>"
                        + "<td class=\"dato\">" + e.getTpbenA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">GENERO:</td>"
                        + "<td class=\"dato\">" + e.getSebenA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">FECHA DE NACIMIENTO:</td>"
                        + "<td class=\"dato\">" + e.getFnbenA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">PORCENTAJE A OTORGAR:</td>"
                        + "<td class=\"dato\">" + e.getPobenA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">NOMBRE COMPLETO:</td>"
                        + "<td class=\"dato\">" + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">OBSERVACIONES:</td>"
                        + "<td class=\"dato\">" + e.getObbenA() + "</td>"
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

        String outcome = "/secured/aspirantes/beneficiarios.xhtml?faces-redirect=true";

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

}
