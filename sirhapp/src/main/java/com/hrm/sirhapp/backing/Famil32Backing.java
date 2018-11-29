package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Famil32;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Famil32ManagerLocal;
import com.hrm.sirhapp.service.exception.Famil32NotFound;
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
public class Famil32Backing extends BaseBacking implements Serializable {

    @EJB
    private Famil32ManagerLocal famil32Manager;

    @Named
    @Produces
    @RequestScoped
    private Famil32 selectedFamil32;

    private List<Famil32> famil32List;

    private int status = 1;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    /**
     *
     * @return
     */
    public List<Famil32> getFamil32List() {
        return famil32List;
    }

    /**
     *
     * @param famil32List
     */
    public void setFamil32RequestList(List<Famil32> famil32List) {
        this.famil32List = famil32List;
    }

    /**
     *
     * @return
     */
    public Famil32 getSelectedFamil32() {
        return selectedFamil32;
    }

    /**
     *
     * @param selectedFamil32
     */
    public void setSelectedFamil32(Famil32 selectedFamil32) {
        this.selectedFamil32 = selectedFamil32;
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
     * @param idben
     * @return
     */
    public Famil32 getFamil32Request(Integer idben) {

        try {

            this.selectedFamil32 = famil32Manager.getFamil32(idben);

        } catch (Famil32NotFound ex) {
            Logger.getLogger(Famil32Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Famil32Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedFamil32;
    }

    /**
     *
     * @param ntben
     */
    public void getListFamil32(Integer ntben) {

        famil32List = famil32Manager.getListFamil32(ntben);

        if (famil32List.isEmpty()) {
            infoMessage = "No existen familiares!";
        } else {
            infoMessage = famil32List.size() + " Familiares";
        }
    }

    /**
     *
     */
    public void retrieveFamil32List() {

        famil32List = famil32Manager.retrieveFamil32();

        if (famil32List.isEmpty()) {
            infoMessage = "No existen familiares!";
        } else {
            infoMessage = famil32List.size() + " Familiares";
        }

    }

    /**
     *
     * @return
     */
    public String updateFamil32() {
        try {

            selectedFamil32.setStfam(Constants.RECORD_ACTIVED);
            selectedFamil32.setFefam(new Date());
            selectedFamil32.setUsfam(FacesUtil.getUserName());

            famil32Manager.updateFamil32(selectedFamil32);

            infoMessage = "Datos actualizados correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Famil32NotFound ex) {
            Logger.getLogger(Famil32Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al actualizar los datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        }
        return null;

    }

    /**
     *
     * @return
     */
    public String deleteFamil32() {
        try {
            

            selectedFamil32.setStfam(Constants.RECORD_DELETED);
            selectedFamil32.setFefam(new Date());
            selectedFamil32.setUsfam(FacesUtil.getUserName());

            famil32Manager.deleteFamil32(selectedFamil32);

            infoMessage = "Beneficiario eliminado correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Famil32NotFound ex) {
            Logger.getLogger(Famil32Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedFamil32 != null) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>FAMILIARES DEL TRABAJADOR</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>FAMILIARES DEL TRABAJADOR:" + this.selectedFamil32.getNtfam()+ "</h1>"
                        + "<div class=\"page-content\">"
                        + "<table class=\"table-content\">"
                        + "<tr>"
                        + "<td class=\"separador\"></td>"
                        + "<td>"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">TIPO:</td>"
                        + "<td class=\"dato\">" + this.selectedFamil32.getTpfam() + "</td>"
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
        String famil = "";

        if (famil32List.isEmpty() || famil32List.size() == 0) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>LISTA DE FAMILIARESS DEL TRABAJADOR</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>LISTA DE FAMILIARESS DEL TRABAJADOR</h1>"
                    + "<div class=\"page-content\">"
                    + "<table class=\"table-content\">"
                    + "<tr>"
                    + "<td>No existen familiares</td>"
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
                    + "<title>LISTA DE FAMILIARESS DEL TRABAJADOR</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>LISTA DE FAMILIARESS DEL TRABAJADOR: " + this.famil32List.get(0).getNtfam()+ "</h1>";

            for (Famil32 e : famil32List) {
                famil = famil + "<div class=\"page-content-border\">"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">TIPO:</td>"
                        + "<td class=\"dato\">" + e.getAmfam()+ "</td>"
                        + "</tr>"
                        + "</table>"
                        + "</div><hr/>";
            }

            str = str + famil + "</body>"
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

        String outcome = "/secured/trabajadores/familiaresList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/trabajadores/familiaresView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/trabajadores/familiaresEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/trabajadores/familiaresCreate.xhtml?faces-redirect=true";
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

}
