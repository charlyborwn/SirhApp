package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Sanci35;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Sanci35ManagerLocal;
import com.hrm.sirhapp.service.exception.Sanci35NotFound;
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
public class Sanci35Backing extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Sanci35ManagerLocal sanci35Manager;

    @Named
    @Produces
    @RequestScoped
    private Sanci35 selectedSanci35;

    private List<Sanci35> sanci35List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Sanciones";
    }

    /**
     *
     * @return
     */
    public List<Sanci35> getSanci35List() {
        return sanci35List;
    }

    /**
     *
     * @param sanci35List
     */
    public void setSanci35List(List<Sanci35> sanci35List) {
        this.sanci35List = sanci35List;
    }

    /**
     *
     * @return
     */
    public Sanci35 getSelectedSanci35() {
        return selectedSanci35;
    }

    /**
     *
     * @param selectedSanci35
     */
    public void setSelectedSanci35(Sanci35 selectedSanci35) {
        this.selectedSanci35 = selectedSanci35;
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
     * @param idapr
     * @return
     */
    public Sanci35 getSanci35Request(Integer idapr) {

        try {

            this.selectedSanci35 = sanci35Manager.getSanci35(idapr);

        } catch (Sanci35NotFound ex) {
            Logger.getLogger(Sanci35Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Sanci35Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedSanci35;
    }

    /**
     *
     * @param nusan
     */
    public void getListSanci35(String nusan) {

        sanci35List = sanci35Manager.getListSanci35(nusan);

        if (!sanci35List.isEmpty()) {
            infoMessage = sanci35List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveSanci35List() {

        sanci35List = sanci35Manager.retrieveSanci35();

        if (sanci35List.isEmpty()) {
            infoMessage = "No existen avisos";
        } else {
            infoMessage = sanci35List.size() + " avisos";
        }

    }

    /**
     *
     * @return
     */
    public String updateSanci35() {
        try {

            selectedSanci35.setStsan(Constants.RECORD_ACTIVED);
            selectedSanci35.setFesan(new Date());
            selectedSanci35.setUssan(FacesUtil.getUserName());

            sanci35Manager.updateSanci35(selectedSanci35);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Sanci35NotFound ex) {
            Logger.getLogger(Sanci35Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteSanci35() {
        try {

            selectedSanci35.setStsan(Constants.RECORD_DELETED);
            selectedSanci35.setFesan(new Date());
            selectedSanci35.setUssan(FacesUtil.getUserName());

            sanci35Manager.deleteSanci35(selectedSanci35);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Sanci35NotFound ex) {
            Logger.getLogger(Sanci35Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedSanci35 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Sanciones</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>Sanciones</h1>"
                    + "<div class=\"page-content\">"
                    + "<table class=\"table-content\">"
                    + "<tr>"
                    + "<td width=\"160px\"><img width=\"160\"  src=\"http://sirhapp.dynu.net/sirhapp/files/" + this.selectedSanci35.getPtsan() + "\" /></td>"
                    + "<td class=\"separador\"></td>"
                    + "<td>"
                    + "<table>"
                    + "<tr>"
                    + "<td class=\"titulo\">RFC:</td>"
                    + "<td class=\"dato\">" + this.selectedSanci35.getRfsan() + "</td>"
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

        if (sanci35List.isEmpty() || sanci35List.size() == 0) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Lista de Sanciones</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>Lista de Sanciones</h1>"
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
                    + "<title>Lista de Sanciones</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>Lista de Sanciones: " + this.sanci35List.get(0).getRfsan() + "</h1>";

            for (Sanci35 e : sanci35List) {
                benef = benef + "<div class=\"page-content-border\">"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">TIPO:</td>"
                        + "<td class=\"dato\">" + e.getTcsan() + "</td>"
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

        String outcome = "/secured/contratos/sanci.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/contratos/verSanci.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/contratos/edicionSanci.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/contratos/nuevoSanci.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
