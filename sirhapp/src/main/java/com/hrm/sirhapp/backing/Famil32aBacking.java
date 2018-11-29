package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Famil32a;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Famil32aManagerLocal;
import com.hrm.sirhapp.service.exception.Famil32aNotFound;
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
public class Famil32aBacking extends BaseBacking implements Serializable {

    @EJB
    private Famil32aManagerLocal famil32aManager;

    @Named
    @Produces
    @RequestScoped
    private Famil32a selectedFamil32a;

    private List<Famil32a> famil32aList;

    private int status = 1;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    /**
     *
     * @return
     */
    public List<Famil32a> getFamil32aList() {
        return famil32aList;
    }

    /**
     *
     * @param famil32aList
     */
    public void setFamil32aRequestList(List<Famil32a> famil32aList) {
        this.famil32aList = famil32aList;
    }

    /**
     *
     * @return
     */
    public Famil32a getSelectedFamil32a() {
        return selectedFamil32a;
    }

    /**
     *
     * @param selectedFamil32a
     */
    public void setSelectedFamil32a(Famil32a selectedFamil32a) {
        this.selectedFamil32a = selectedFamil32a;
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
    public Famil32a getFamil32aRequest(Integer idbenA) {

        try {

            this.selectedFamil32a = famil32aManager.getFamil32a(idbenA);

        } catch (Famil32aNotFound ex) {
            Logger.getLogger(Famil32aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Famil32aBacking.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedFamil32a;
    }

    /**
     *
     * @param rfbenA
     */
    public void getListFamil32a(String rfbenA) {

        famil32aList = famil32aManager.getListFamil32a(rfbenA);

        if (famil32aList.isEmpty()) {
            infoMessage = "No existen familiares!";
        } else {
            infoMessage = famil32aList.size() + " Familiares";
        }
    }

    /**
     *
     */
    public void retrieveFamil32aList() {

        famil32aList = famil32aManager.retrieveFamil32a();

        if (famil32aList.isEmpty()) {
            infoMessage = "No existen familiares!";
        } else {
            infoMessage = famil32aList.size() + " Familiares";
        }

    }

    /**
     *
     * @return
     */
    public String updateFamil32a() {
        try {

            selectedFamil32a.setStfamA(Constants.RECORD_ACTIVED);
            selectedFamil32a.setFefamA(new Date());
            selectedFamil32a.setUsfamA(FacesUtil.getUserName());

            famil32aManager.updateFamil32a(selectedFamil32a);

            infoMessage = "Datos actualizados correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Famil32aNotFound ex) {
            Logger.getLogger(Famil32aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al actualizar los datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        }
        return null;

    }

    /**
     *
     * @return
     */
    public String deleteFamil32a() {
        try {
            

            selectedFamil32a.setStfamA(Constants.RECORD_DELETED);
            selectedFamil32a.setFefamA(new Date());
            selectedFamil32a.setUsfamA(FacesUtil.getUserName());

            famil32aManager.deleteFamil32a(selectedFamil32a);

            infoMessage = "Beneficiario eliminado correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Famil32aNotFound ex) {
            Logger.getLogger(Famil32aBacking.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedFamil32a != null) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>FAMILIARES DEL ASPIRANTE</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>FAMILIARES DEL ASPIRANTE:" + this.selectedFamil32a.getRffamA()+ "</h1>"
                        + "<div class=\"page-content\">"
                        + "<table class=\"table-content\">"
                        + "<tr>"
                        + "<td class=\"separador\"></td>"
                        + "<td>"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">TIPO:</td>"
                        + "<td class=\"dato\">" + this.selectedFamil32a.getTpfamA() + "</td>"
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

        if (famil32aList.isEmpty() || famil32aList.size() == 0) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>LISTA DE FAMILIARESS DEL ASPIRANTE</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>LISTA DE FAMILIARESS DEL ASPIRANTE</h1>"
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
                    + "<title>LISTA DE FAMILIARESS DEL ASPIRANTE</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>LISTA DE FAMILIARESS DEL ASPIRANTE: " + this.famil32aList.get(0).getRffamA()+ "</h1>";

            for (Famil32a e : famil32aList) {
                benef = benef + "<div class=\"page-content-border\">"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">TIPO:</td>"
                        + "<td class=\"dato\">" + e.getAmfamA()+ "</td>"
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

        String outcome = "/secured/aspirantes/familiaresList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/aspirantes/familiaresView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/aspirantes/familiaresEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/aspirantes/familiaresCreate.xhtml?faces-redirect=true";
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

}
