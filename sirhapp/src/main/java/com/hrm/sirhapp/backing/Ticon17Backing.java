package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Ticon17;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Ticon17ManagerLocal;
import com.hrm.sirhapp.service.exception.Ticon17NotFound;
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
public class Ticon17Backing extends BaseBacking implements Serializable {

    @EJB
    private Ticon17ManagerLocal ticon17Manager;

    @Named
    @Produces
    @RequestScoped
    private Ticon17 selectedTicon17;

    private List<Ticon17> ticon17List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListTicon17();
        this.infoMessageModule = "Modulo Tipos de Contrato";
    }

    /**
     *
     * @return
     */
    public List<Ticon17> getTicon17List() {
        return ticon17List;
    }

    /**
     *
     * @param ticon17List
     */
    public void setTicon17List(List<Ticon17> ticon17List) {
        this.ticon17List = ticon17List;
    }

    /**
     *
     * @return
     */
    public Ticon17 getSelectedTicon17() {
        return selectedTicon17;
    }

    /**
     *
     * @param selectedTicon17
     */
    public void setSelectedTicon17(Ticon17 selectedTicon17) {
        this.selectedTicon17 = selectedTicon17;
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
     * @param idstc
     * @return
     */
    public Ticon17 getTicon17Request(Integer idstc) {

        try {

            this.selectedTicon17 = ticon17Manager.getTicon17(idstc);

        } catch (Ticon17NotFound ex) {
            Logger.getLogger(Ticon17Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Ticon17Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedTicon17;
    }

    /**
     *
     */
    public void getListTicon17() {

        ticon17List = ticon17Manager.getListTicon17();

        if (!ticon17List.isEmpty()) {
            infoMessage = ticon17List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveTicon17List() {

        ticon17List = ticon17Manager.retrieveTicon17();

        if (ticon17List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = ticon17List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateTicon17() {
        try {

            

            selectedTicon17.setSttic(Constants.RECORD_ACTIVED);
            selectedTicon17.setFetic(new Date());
            selectedTicon17.setUstic(FacesUtil.getUserName());

            ticon17Manager.updateTicon17(selectedTicon17);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Ticon17NotFound ex) {
            Logger.getLogger(Ticon17Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteTicon17() {
        try {
            

            selectedTicon17.setSttic(Constants.RECORD_DELETED);
            selectedTicon17.setFetic(new Date());
            selectedTicon17.setUstic(FacesUtil.getUserName());

            ticon17Manager.deleteTicon17(selectedTicon17);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Ticon17NotFound ex) {
            Logger.getLogger(Ticon17Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedTicon17 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Tipos de Contrato</title>"
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
        if (ticon17List != null) {
            if (ticon17List.isEmpty() || ticon17List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Tipos de Contrato</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Tipos de Contrato</h1>"
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
                        + "<title>Lista de Tipos de Contrato</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Tipos de Contrato</h1>";

                for (Ticon17 e : ticon17List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">"+ e.getCvtic()+"</td>"
                            + "</tr>"
                            + "</table>"
                            + "</div><hr/>";
                }

                str = str + benef + "</body>"
                        + "</html>";

            }
        }
        this.contentListPdf = str+"";

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

        String outcome = "/secured/catalogos/tiposContratosList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/tiposContratosView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/tiposContratosEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/tiposContratosCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
