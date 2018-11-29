package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Tipar19;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Tipar19ManagerLocal;
import com.hrm.sirhapp.service.exception.Tipar19NotFound;
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
public class Tipar19Backing extends BaseBacking implements Serializable {

    @EJB
    private Tipar19ManagerLocal tipar19Manager;

    @Named
    @Produces
    @RequestScoped
    private Tipar19 selectedTipar19;

    private List<Tipar19> tipar19List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {

        getListTipar19();

        this.infoMessageModule = "Modulo Tipos de Parientes";
    }

    /**
     *
     * @return
     */
    public List<Tipar19> getTipar19List() {
        return tipar19List;
    }

    /**
     *
     * @param tipar19List
     */
    public void setTipar19List(List<Tipar19> tipar19List) {
        this.tipar19List = tipar19List;
    }

    /**
     *
     * @return
     */
    public Tipar19 getSelectedTipar19() {
        return selectedTipar19;
    }

    /**
     *
     * @param selectedTipar19
     */
    public void setSelectedTipar19(Tipar19 selectedTipar19) {
        this.selectedTipar19 = selectedTipar19;
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
    public Tipar19 getTipar19Request(Integer idstc) {

        try {

            this.selectedTipar19 = tipar19Manager.getTipar19(idstc);

        } catch (Tipar19NotFound ex) {
            Logger.getLogger(Tipar19Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Tipar19Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedTipar19;
    }

    /**
     *
     */
    public void getListTipar19() {

        tipar19List = tipar19Manager.getListTipar19();

        if (!tipar19List.isEmpty()) {
            infoMessage = tipar19List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveTipar19List() {

        tipar19List = tipar19Manager.retrieveTipar19();

        if (tipar19List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = tipar19List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateTipar19() {
        try {

            

            selectedTipar19.setSttip(Constants.RECORD_ACTIVED);
            selectedTipar19.setFetip(new Date());
            selectedTipar19.setUstip(FacesUtil.getUserName());

            tipar19Manager.updateTipar19(selectedTipar19);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Tipar19NotFound ex) {
            Logger.getLogger(Tipar19Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteTipar19() {
        try {
            

            selectedTipar19.setSttip(Constants.RECORD_DELETED);
            selectedTipar19.setFetip(new Date());
            selectedTipar19.setUstip(FacesUtil.getUserName());

            tipar19Manager.deleteTipar19(selectedTipar19);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Tipar19NotFound ex) {
            Logger.getLogger(Tipar19Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedTipar19 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Tipos de Parientes</title>"
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
        if (tipar19List != null) {
            if (tipar19List.isEmpty() || tipar19List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Tipos de Parientes</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Tipos de Parientes</h1>"
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
                        + "<title>Lista de Tipos de Parientes</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Tipos de Parientes</h1>";

                for (Tipar19 e : tipar19List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">" + e.getNotip() + "</td>"
                            + "</tr>"
                            + "</table>"
                            + "</div><hr/>";
                }

                str = str + benef + "</body>"
                        + "</html>";

            }
        }
        this.contentListPdf = str + "";

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

        String outcome = "/secured/catalogos/tiposParientesList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/tiposParientesView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/tiposParientesEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/tiposParientesCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
