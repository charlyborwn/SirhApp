package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Sedes53;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Sedes53ManagerLocal;
import com.hrm.sirhapp.service.exception.Sedes53NotFound;
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
public class Sedes53Backing extends BaseBacking implements Serializable {

    @EJB
    private Sedes53ManagerLocal sedes53Manager;

    @Named
    @Produces
    @RequestScoped
    private Sedes53 selectedSedes53;

    private List<Sedes53> sedes53List;

    private int status = 1;
    private String infoMessage;
    private String infoMessageModule;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListSedes53();
        this.infoMessageModule = "Modulo Sedes";
    }

    /**
     *
     * @return
     */
    public List<Sedes53> getSedes53List() {
        return sedes53List;
    }

    /**
     *
     * @param sedes53List
     */
    public void setSedes53RequestList(List<Sedes53> sedes53List) {
        this.sedes53List = sedes53List;
    }

    /**
     *
     * @return
     */
    public Sedes53 getSelectedSedes53() {
        return selectedSedes53;
    }

    /**
     *
     * @param selectedSedes53
     */
    public void setSelectedSedes53(Sedes53 selectedSedes53) {
        this.selectedSedes53 = selectedSedes53;
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
     * @param idsed
     * @return
     */
    public Sedes53 getSedes53Request(Integer idsed) {

        try {

            this.selectedSedes53 = sedes53Manager.getSedes53(idsed);

        } catch (Sedes53NotFound ex) {
            Logger.getLogger(Sedes53Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Sedes53Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedSedes53;
    }

    /**
     *
     * @param cvsed
     * @return
     */
    public String getSedes53RequestString(String cvsed) {
        try {
            Sedes53 sedes53 = sedes53Manager.getSedes53(cvsed);

            String out = sedes53.getCvsed() + " | " + sedes53.getDesed();

            return out;

        } catch (Sedes53NotFound ex) {
            Logger.getLogger(Sedes53Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Sedes53Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return "";
    }

    /**
     *
     */
    public void getListSedes53() {

        sedes53List = sedes53Manager.getListSedes53();

        if (sedes53List.isEmpty()) {
            infoMessage = "No existen Registros!";
        } else {
            infoMessage = sedes53List.size() + " Registros";
        }
    }

    /**
     *
     */
    public void retrieveSedes53List() {

        sedes53List = sedes53Manager.retrieveSedes53();

        if (sedes53List.isEmpty()) {
            infoMessage = "No existen Registros!";
        } else {
            infoMessage = sedes53List.size() + " Registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateSedes53() {
        try {

            selectedSedes53.setStsed(Constants.RECORD_ACTIVED);
            selectedSedes53.setFesed(new Date());
            selectedSedes53.setUssed(FacesUtil.getUserName());

            sedes53Manager.updateSedes53(selectedSedes53);

            infoMessage = "Registro actualizado correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Sedes53NotFound ex) {
            Logger.getLogger(Sedes53Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al actualizar los datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        }
        return null;

    }

    /**
     *
     * @return
     */
    public String deleteSedes53() {
        try {

            selectedSedes53.setStsed(Constants.RECORD_DELETED);
            selectedSedes53.setFesed(new Date());
            selectedSedes53.setUssed(FacesUtil.getUserName());

            sedes53Manager.deleteSedes53(selectedSedes53);

            infoMessage = "Registro eliminado correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Sedes53NotFound ex) {
            Logger.getLogger(Sedes53Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedSedes53 != null) {
            if (selectedSedes53.getIdsed() > 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Catalogo de Sedes</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Categoria." + this.selectedSedes53.getIdsed() + "</h1>"
                        + "<div class=\"page-content\">"
                        + "<table class=\"table-content\">"
                        + "<tr>"
                        + "<td class=\"separador\"></td>"
                        + "<td>"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">Descripción:</td>"
                        + "<td class=\"dato\">" + this.selectedSedes53.getDesed() + "</td>"
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

        if (sedes53List.isEmpty() || sedes53List.size() == 0) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Catalogo de Sedes</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>Catalogo de Sedes</h1>"
                    + "<div class=\"page-content\">"
                    + "<table class=\"table-content\">"
                    + "<tr>"
                    + "<td>No existen registros</td>"
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
                    + "<title>Catalogo de Sedes</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>Catalogo de Sedes</h1>";

            for (Sedes53 e : sedes53List) {
                benef = benef + "<div class=\"page-content-border\">"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">Descripción:</td>"
                        + "<td class=\"dato\">" + e.getDesed() + "</td>"
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

        String outcome = "/secured/catalogos/sedesList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/sedesView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/sedesEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/sedesCreate.xhtml?faces-redirect=true";
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

}
