package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Chofe40;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Chofe40ManagerLocal;
import com.hrm.sirhapp.service.exception.Chofe40NotFound;
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
public class Chofe40Backing extends BaseBacking implements Serializable {

    @EJB
    private Chofe40ManagerLocal chofe40Manager;

    @Named
    @Produces
    @RequestScoped
    private Chofe40 selectedChofe40;

    private List<Chofe40> chofe40List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Choferes";
    }

    /**
     *
     * @return
     */
    public List<Chofe40> getChofe40List() {
        return chofe40List;
    }

    /**
     *
     * @param chofe40List
     */
    public void setChofe40List(List<Chofe40> chofe40List) {
        this.chofe40List = chofe40List;
    }

    /**
     *
     * @return
     */
    public Chofe40 getSelectedChofe40() {
        return selectedChofe40;
    }

    /**
     *
     * @param selectedChofe40
     */
    public void setSelectedChofe40(Chofe40 selectedChofe40) {
        this.selectedChofe40 = selectedChofe40;
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
    public Chofe40 getChofe40Request(Integer idubi) {

        try {

            this.selectedChofe40 = chofe40Manager.getChofe40(idubi);

        } catch (Chofe40NotFound ex) {
            Logger.getLogger(Chofe40Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Chofe40Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedChofe40;
    }

    /**
     *
     */
    public void getListChofe40() {

        chofe40List = chofe40Manager.getListChofe40();

        if (!chofe40List.isEmpty()) {
            infoMessage = chofe40List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveChofe40List() {

        chofe40List = chofe40Manager.retrieveChofe40();

        if (chofe40List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = chofe40List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateChofe40() {
        try {

            

            selectedChofe40.setStcho(Constants.RECORD_ACTIVED);
            selectedChofe40.setFecho(new Date());
            selectedChofe40.setUscho(FacesUtil.getUserName());

            chofe40Manager.updateChofe40(selectedChofe40);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Chofe40NotFound ex) {
            Logger.getLogger(Chofe40Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteChofe40() {
        try {
            

            selectedChofe40.setStcho(Constants.RECORD_DELETED);
            selectedChofe40.setFecho(new Date());
            selectedChofe40.setUscho(FacesUtil.getUserName());

            chofe40Manager.deleteChofe40(selectedChofe40);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Chofe40NotFound ex) {
            Logger.getLogger(Chofe40Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedChofe40 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Choferes</title>"
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
        if (chofe40List != null) {
            if (chofe40List.isEmpty() || chofe40List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Choferes</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Choferes</h1>"
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
                        + "<title>Lista de Choferes</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Choferes</h1>";

                for (Chofe40 e : chofe40List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">"+ e.getNocho()+"</td>"
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

        String outcome = "/secured/catalogos/choferesList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/choferesView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/choferesEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/choferesCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
