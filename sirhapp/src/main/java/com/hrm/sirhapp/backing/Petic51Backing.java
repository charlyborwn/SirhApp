package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Petic51;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Petic51ManagerLocal;
import com.hrm.sirhapp.service.exception.Petic51NotFound;
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
public class Petic51Backing extends BaseBacking implements Serializable {

    @EJB
    private Petic51ManagerLocal petic51Manager;

    @Named
    @Produces
    @RequestScoped
    private Petic51 selectedPetic51;

    private List<Petic51> petic51List;

    private int status = 1;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    /**
     *
     * @return
     */
    public List<Petic51> getPetic51List() {
        return petic51List;
    }

    /**
     *
     * @param petic51List
     */
    public void setPetic51RequestList(List<Petic51> petic51List) {
        this.petic51List = petic51List;
    }

    /**
     *
     * @return
     */
    public Petic51 getSelectedPetic51() {
        return selectedPetic51;
    }

    /**
     *
     * @param selectedPetic51
     */
    public void setSelectedPetic51(Petic51 selectedPetic51) {
        this.selectedPetic51 = selectedPetic51;
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
    public Petic51 getPetic51Request(Integer idbenA) {

        try {

            this.selectedPetic51 = petic51Manager.getPetic51(idbenA);

        } catch (Petic51NotFound ex) {
            Logger.getLogger(Petic51Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Petic51Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedPetic51;
    }

    /**
     *
     */
    public void getListPetic51() {

        petic51List = petic51Manager.getListPetic51();

        if (petic51List.isEmpty()) {
            infoMessage = "No existen reportes!";
        } else {
            infoMessage = petic51List.size() + " Comunicado";
        }
    }

    /**
     *
     */
    public void retrievePetic51List() {

        petic51List = petic51Manager.retrievePetic51();

        if (petic51List.isEmpty()) {
            infoMessage = "No existen reportes!";
        } else {
            infoMessage = petic51List.size() + " reportes";
        }

    }

    /**
     *
     * @return
     */
    public String updatePetic51() {
        try {

            selectedPetic51.setStpet(Constants.RECORD_ACTIVED);
            selectedPetic51.setFepet(new Date());
            selectedPetic51.setUspet(FacesUtil.getUserName());

            petic51Manager.updatePetic51(selectedPetic51);

            infoMessage = "Aspirante actualizado correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Petic51NotFound ex) {
            Logger.getLogger(Petic51Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al actualizar los datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        }
        return null;

    }

    /**
     *
     * @return
     */
    public String deletePetic51() {
        try {
            

            selectedPetic51.setStpet(Constants.RECORD_DELETED);
            selectedPetic51.setFepet(new Date());
            selectedPetic51.setUspet(FacesUtil.getUserName());

            petic51Manager.deletePetic51(selectedPetic51);

            infoMessage = "Registro eliminado correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Petic51NotFound ex) {
            Logger.getLogger(Petic51Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedPetic51 != null) {

            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>LISTA DE REPORTES</title>"
                    + "</head>"
                    + "<body>"
                    //                    + "<h1>REPORTES:" + this.selectedPetic51.getRfbenA() + "</h1>"
                    + "<div class=\"page-content\">"
                    + "<table class=\"table-content\">"
                    + "<tr>"
                    + "<td class=\"separador\"></td>"
                    + "<td>"
                    + "<table>"
                    + "<tr>"
                    + "<td class=\"titulo\">TIPO:</td>"
                    //                    + "<td class=\"dato\">" + this.selectedPetic51.getTpbenA() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">GENERO:</td>"
                    //                    + "<td class=\"dato\">" + this.selectedPetic51.getSebenA() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">FECHA DE NACIMIENTO:</td>"
                    //                    + "<td class=\"dato\">" + this.selectedPetic51.getFnbenA() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">PORCENTAJE A OTORGAR:</td>"
                    //                    + "<td class=\"dato\">" + this.selectedPetic51.getPobenA() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">NOMBRE COMPLETO:</td>"
                    + "<td class=\"dato\">" + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">OBSERVACIONES:</td>"
                    //                    + "<td class=\"dato\">" + this.selectedPetic51.getObbenA() + "</td>"
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
        String petic = "";

        if (petic51List.isEmpty() || petic51List.size() == 0) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>LISTA DE REPORTES</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>LISTA DE REPORTES</h1>"
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
                    + "<title>LISTA DE REPORTES</title>"
                    + "</head>"
                    + "<body>";
//                    + "<h1>LISTA DE REPORTES: " + this.petic51List.get(0).getRfbenA() + "</h1>";

            for (Petic51 e : petic51List) {
                petic = petic + "<div class=\"page-content-border\">"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">TIPO:</td>"
                        //                        + "<td class=\"dato\">" + e.getTpbenA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">GENERO:</td>"
                        //                        + "<td class=\"dato\">" + e.getSebenA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">FECHA DE NACIMIENTO:</td>"
                        //                        + "<td class=\"dato\">" + e.getFnbenA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">PORCENTAJE A OTORGAR:</td>"
                        //                        + "<td class=\"dato\">" + e.getPobenA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">NOMBRE COMPLETO:</td>"
                        + "<td class=\"dato\">" + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">OBSERVACIONES:</td>"
                        //                        + "<td class=\"dato\">" + e.getObbenA() + "</td>"
                        + "</tr>"
                        + "</table>"
                        + "</div><hr/>";
            }

            str = str + petic + "</body>"
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

        String outcome = "/secured/catalogos/solicitudComunicadoList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/solicitudComunicadoView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/solicitudComunicadoEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/solicitudComunicadoCreate.xhtml?faces-redirect=true";
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

}
