package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Benef26;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Benef26ManagerLocal;
import com.hrm.sirhapp.service.exception.Benef26NotFound;
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
public class Benef26Backing extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Benef26ManagerLocal benef26Manager;

    @Named
    @Produces
    @RequestScoped
    private Benef26 selectedBenef26;

    private List<Benef26> benef26List;

    private int status = 1;
    private String infoMessage;
    private String infoMessageModule;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Beneficiarios";
    }

    /**
     *
     * @return
     */
    public List<Benef26> getBenef26List() {
        return benef26List;
    }

    /**
     *
     * @param benef26List
     */
    public void setBenef26RequestList(List<Benef26> benef26List) {
        this.benef26List = benef26List;
    }

    /**
     *
     * @return
     */
    public Benef26 getSelectedBenef26() {
        return selectedBenef26;
    }

    /**
     *
     * @param selectedBenef26
     */
    public void setSelectedBenef26(Benef26 selectedBenef26) {
        this.selectedBenef26 = selectedBenef26;
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
     * @param idben
     * @return
     */
    public Benef26 getBenef26Request(Integer idben) {

        try {

            this.selectedBenef26 = benef26Manager.getBenef26(idben);

        } catch (Benef26NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
        }

        return selectedBenef26;
    }

    /**
     *
     * @param ntben
     */
    public void getListBenef26(Integer ntben) {

        benef26List = benef26Manager.getListBenef26(ntben);

        if (benef26List.isEmpty()) {
            infoMessage = "No existen beneficiarios!";
        } else {
            infoMessage = benef26List.size() + " Beneficiarios";
        }
    }

    /**
     *
     */
    public void retrieveBenef26List() {

        benef26List = benef26Manager.retrieveBenef26();

        if (benef26List.isEmpty()) {
            infoMessage = "No existen beneficiarios!";
        } else {
            infoMessage = benef26List.size() + " Beneficiarios";
        }

    }

    /**
     *
     * @return
     */
    public String updateBenef26() {
        try {

            selectedBenef26.setStben(Constants.RECORD_ACTIVED);
            selectedBenef26.setFeben(new Date());
            selectedBenef26.setUsben(FacesUtil.getUserName());

            benef26Manager.updateBenef26(selectedBenef26);

            infoMessage = "Aspirante actualizado correctamente";

        } catch (Benef26NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);

        }
        return null;

    }

    /**
     *
     * @return
     */
    public String deleteBenef26() {
        try {

            selectedBenef26.setStben(Constants.RECORD_DELETED);
            selectedBenef26.setFeben(new Date());
            selectedBenef26.setUsben(FacesUtil.getUserName());

            benef26Manager.deleteBenef26(selectedBenef26);

            infoMessage = "Beneficiario eliminado correctamente";

        } catch (Benef26NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);

        }
        return null;

    }

    /**
     *
     * @return
     */
    public String getContentPdf() {
        String str = null;

        if (selectedBenef26 != null) {

            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>BENEFICIARIO DEL TRABAJADOR</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>BENEFICIARIO DEL TRABAJADOR:" + this.selectedBenef26.getNtben() + "</h1>"
                    + "<div class=\"page-content\">"
                    + "<table class=\"table-content\">"
                    + "<tr>"
                    + "<td class=\"separador\"></td>"
                    + "<td>"
                    + "<table>"
                    + "<tr>"
                    + "<td class=\"titulo\">TIPO:</td>"
                    + "<td class=\"dato\">" + this.selectedBenef26.getTpben() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">GENERO:</td>"
                    + "<td class=\"dato\">" + this.selectedBenef26.getSeben() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">FECHA DE NACIMIENTO:</td>"
                    + "<td class=\"dato\">" + this.selectedBenef26.getFnben() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">PORCENTAJE A OTORGAR:</td>"
                    + "<td class=\"dato\">" + this.selectedBenef26.getPoben() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">NOMBRE COMPLETO:</td>"
                    + "<td class=\"dato\">" + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">OBSERVACIONES:</td>"
                    + "<td class=\"dato\">" + this.selectedBenef26.getObben() + "</td>"
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

        if (benef26List.isEmpty() || benef26List.size() == 0) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>LISTA DE BENEFICIARIOS DEL TRABAJADOR</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>LISTA DE BENEFICIARIOS DEL TRABAJADOR</h1>"
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
                    + "<title>LISTA DE BENEFICIARIOS DEL TRABAJADOR</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>LISTA DE BENEFICIARIOS DEL TRABAJADOR: " + this.benef26List.get(0).getNtben() + "</h1>";

            for (Benef26 e : benef26List) {
                benef = benef + "<div class=\"page-content-border\">"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">TIPO:</td>"
                        + "<td class=\"dato\">" + e.getTpben() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">GENERO:</td>"
                        + "<td class=\"dato\">" + e.getSeben() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">FECHA DE NACIMIENTO:</td>"
                        + "<td class=\"dato\">" + e.getFnben() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">PORCENTAJE A OTORGAR:</td>"
                        + "<td class=\"dato\">" + e.getPoben() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">NOMBRE COMPLETO:</td>"
                        + "<td class=\"dato\">" + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">OBSERVACIONES:</td>"
                        + "<td class=\"dato\">" + e.getObben() + "</td>"
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

        String outcome = "/secured/trabajadores/beneficiariosList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/trabajadores/beneficiariosView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/trabajadores/beneficiariosEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/trabajadores/beneficiariosCreate.xhtml?faces-redirect=true";
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

}
