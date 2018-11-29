package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Rutas41;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Rutas41ManagerLocal;
import com.hrm.sirhapp.service.exception.Rutas41NotFound;
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
public class Rutas41Backing extends BaseBacking implements Serializable {

    @EJB
    private Rutas41ManagerLocal rutas41Manager;

    @Named
    @Produces
    @RequestScoped
    private Rutas41 selectedRutas41;

    private List<Rutas41> rutas41List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Rutas";
    }

    /**
     *
     * @return
     */
    public List<Rutas41> getRutas41List() {
        return rutas41List;
    }

    /**
     *
     * @param rutas41List
     */
    public void setRutas41List(List<Rutas41> rutas41List) {
        this.rutas41List = rutas41List;
    }

    /**
     *
     * @return
     */
    public Rutas41 getSelectedRutas41() {
        return selectedRutas41;
    }

    /**
     *
     * @param selectedRutas41
     */
    public void setSelectedRutas41(Rutas41 selectedRutas41) {
        this.selectedRutas41 = selectedRutas41;
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
     * @param idubi
     * @return
     */
    public Rutas41 getRutas41Request(Integer idubi) {

        try {

            this.selectedRutas41 = rutas41Manager.getRutas41(idubi);

        } catch (Rutas41NotFound ex) {
            Logger.getLogger(Rutas41Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Rutas41Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedRutas41;
    }

    /**
     *
     */
    public void getListRutas41() {

        rutas41List = rutas41Manager.getListRutas41();

        if (!rutas41List.isEmpty()) {
            infoMessage = rutas41List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveRutas41List() {

        rutas41List = rutas41Manager.retrieveRutas41();

        if (rutas41List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = rutas41List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateRutas41() {
        try {

            

            selectedRutas41.setStrut(Constants.RECORD_ACTIVED);
            selectedRutas41.setFerut(new Date());
            selectedRutas41.setUsrut(FacesUtil.getUserName());

            rutas41Manager.updateRutas41(selectedRutas41);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Rutas41NotFound ex) {
            Logger.getLogger(Rutas41Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteRutas41() {
        try {
            

            selectedRutas41.setStrut(Constants.RECORD_DELETED);
            selectedRutas41.setFerut(new Date());
            selectedRutas41.setUsrut(FacesUtil.getUserName());

            rutas41Manager.deleteRutas41(selectedRutas41);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Rutas41NotFound ex) {
            Logger.getLogger(Rutas41Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedRutas41 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Rutas</title>"
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
        if (rutas41List != null) {
            if (rutas41List.isEmpty() || rutas41List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Rutas</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Rutas</h1>"
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
                        + "<title>Lista de Rutas</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Rutas</h1>";

                for (Rutas41 e : rutas41List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">"+ e.getNurut()+"</td>"
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

        String outcome = "/secured/catalogos/rutasList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/rutasView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/rutasEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/rutasCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
