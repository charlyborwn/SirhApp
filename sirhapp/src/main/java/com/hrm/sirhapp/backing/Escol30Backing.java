package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Escol30;
import com.hrm.sirhapp.service.Escol30ManagerLocal;
import com.hrm.sirhapp.service.exception.Escol30NotFound;
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
public class Escol30Backing extends BaseBacking implements Serializable {

    @EJB
    private Escol30ManagerLocal escol30Manager;

    @Named
    @Produces
    @RequestScoped
    private Escol30 selectedEscol30;

    private List<Escol30> escol30List;

    private int status = 1;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    /**
     *
     * @return
     */
    public List<Escol30> getEscol30List() {
        return escol30List;
    }

    /**
     *
     * @param escol30List
     */
    public void setEscol30RequestList(List<Escol30> escol30List) {
        this.escol30List = escol30List;
    }

    /**
     *
     * @return
     */
    public Escol30 getSelectedEscol30() {
        return selectedEscol30;
    }

    /**
     *
     * @param selectedEscol30
     */
    public void setSelectedEscol30(Escol30 selectedEscol30) {
        this.selectedEscol30 = selectedEscol30;
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
     * @param idesc
     * @return
     */
    public Escol30 getEscol30Request(Integer idesc) {

        try {

            this.selectedEscol30 = escol30Manager.getEscol30(idesc);

        } catch (Escol30NotFound ex) {
            Logger.getLogger(Escol30Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Escol30Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedEscol30;
    }

    /**
     *
     * @param ntesc
     */
    public void getListEscol30(Integer ntesc) {

        escol30List = escol30Manager.getListEscol30(ntesc);

        if (!escol30List.isEmpty()) {
            infoMessage = escol30List.size() + " registro(s) de escolaridad";
        }
    }

    /**
     *
     */
    public void retrieveEscol30List() {

        escol30List = escol30Manager.retrieveEscol30();

        if (escol30List.isEmpty()) {
            infoMessage = "No existen resgistros de escolaridad";
        } else {
            infoMessage = escol30List.size() + " registro(s) de escolaridad";
        }

    }

    /**
     *
     * @return
     */
    public String updateEscol30() {
        try {

            selectedEscol30.setStesc(Constants.RECORD_ACTIVED);
            selectedEscol30.setFeesc(new Date());
            selectedEscol30.setUsesc(FacesUtil.getUserName());

            escol30Manager.updateEscol30(selectedEscol30);

            infoMessage = "Registro de escolaridad actualizado correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Escol30NotFound ex) {
            Logger.getLogger(Escol30Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al actualizar el registro del Trabajador";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteEscol30() {
        try {
            

            selectedEscol30.setStesc(Constants.RECORD_DELETED);
            selectedEscol30.setFeesc(new Date());
            selectedEscol30.setUsesc(FacesUtil.getUserName());

            escol30Manager.deleteEscol30(selectedEscol30);

            infoMessage = "Registro de Escolaridad eliminado correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Escol30NotFound ex) {
            Logger.getLogger(Escol30Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al eliminar el registro del Trabajador";
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

        if (selectedEscol30 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>DATOS DE ESCOLARIDAD DEL TRABAJADOR</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>DATOS DE ESCOLARIDAD DEL TRABAJADOR</h1>"
                    + "<div class=\"page-content\">"
                    + "<table class=\"table-content\">"
                    + "<tr>"
                    + "<td width=\"160px\"><img width=\"160\"  src=\"http://sirhapp.dynu.net/sirhapp/files/" + this.selectedEscol30.getPaesc() + "\" /></td>"
                    + "<td class=\"separador\"></td>"
                    + "<td>"
                    + "<table>"
                    + "<tr>"
                    + "<td class=\"titulo\">TIPO DE DOCUMENTO:</td>"
                    + "<td class=\"dato\">" + this.selectedEscol30.getTnesc() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">OBSERVACIONES:</td>"
                    + "<td class=\"dato\">" + this.selectedEscol30.getNtesc()+ "</td>"
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
        String escol = "";

        if (escol30List.isEmpty() || escol30List.size() == 0) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>ESCOLARIDAD DEL TRABAJADOR</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>ESCOLARIDAD DEL TRABAJADOR</h1>"
                    + "<div class=\"page-content\">"
                    + "<table class=\"table-content\">"
                    + "<tr>"
                    + "<td>No existen datos de escolaridad del aspirante</td>"
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
                    + "<title>ESCOLARIDAD DEL TRABAJADOR</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>ESCOLARIDAD DEL TRABAJADOR: " + this.escol30List.get(0).getNtesc()+ "</h1>";

            for (Escol30 e : escol30List) {
                escol = escol + "<div class=\"page-content-border\">"
                        + "<table class=\"table-content\">"
                        + "<tr>"
                        + "<td width=\"160px\"><img width=\"160\"  src=\"http://sirhapp.dynu.net/sirhapp/files/" + e.getPaesc() + "\" /></td>"
                        + "<td class=\"separador\"></td>"
                        + "<td>"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">TIPO:</td>"
                        + "<td class=\"dato\">" + e.getTnesc() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">OBSERVACIONES:</td>"
                        + "<td class=\"dato\">" + e.getObesc() + "</td>"
                        + "</tr>"
                        + "</table></td>"
                        + "</tr>"
                        + "</table>"
                        + "</div><hr/>";
            }

            str = str + escol + "</body>"
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

        String outcome = "/secured/trabajadores/escolaridad.xhtml?faces-redirect=true";

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

}
