package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Comit08;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Comit08ManagerLocal;
import com.hrm.sirhapp.service.exception.Comit08NotFound;
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
public class Comit08Backing extends BaseBacking implements Serializable {

    @EJB
    private Comit08ManagerLocal comit08Manager;

    @Named
    @Produces
    @RequestScoped
    private Comit08 selectedComit08;

    private List<Comit08> comit08List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListComit08();
        this.infoMessageModule = "Modulo Miembros del Comite";
    }

    /**
     *
     * @return
     */
    public List<Comit08> getComit08List() {
        return comit08List;
    }

    /**
     *
     * @param comit08List
     */
    public void setComit08List(List<Comit08> comit08List) {
        this.comit08List = comit08List;
    }

    /**
     *
     * @return
     */
    public Comit08 getSelectedComit08() {
        return selectedComit08;
    }

    /**
     *
     * @param selectedComit08
     */
    public void setSelectedComit08(Comit08 selectedComit08) {
        this.selectedComit08 = selectedComit08;
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
     * @param idmes
     * @return
     */
    public Comit08 getComit08Request(Integer idmes) {

        try {

            this.selectedComit08 = comit08Manager.getComit08(idmes);

        } catch (Comit08NotFound ex) {
            Logger.getLogger(Comit08Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Comit08Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedComit08;
    }

    /**
     *
     */
    public void getListComit08() {

        comit08List = comit08Manager.getListComit08();

        if (!comit08List.isEmpty()) {
            infoMessage = comit08List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveComit08List() {

        comit08List = comit08Manager.retrieveComit08();

        if (comit08List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = comit08List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateComit08() {
        try {

            

            selectedComit08.setStcom(Constants.RECORD_ACTIVED);
            selectedComit08.setFecom(new Date());
            selectedComit08.setUscom(FacesUtil.getUserName());

            comit08Manager.updateComit08(selectedComit08);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Comit08NotFound ex) {
            Logger.getLogger(Comit08Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteComit08() {
        try {
            

            selectedComit08.setStcom(Constants.RECORD_DELETED);
            selectedComit08.setFecom(new Date());
            selectedComit08.setUscom(FacesUtil.getUserName());

            comit08Manager.deleteComit08(selectedComit08);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Comit08NotFound ex) {
            Logger.getLogger(Comit08Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedComit08 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Causas Sin Documento</title>"
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
        if (comit08List != null) {
            if (comit08List.isEmpty() || comit08List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>LISTA DE MESES</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>LISTA DE MESES</h1>"
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
                        + "<title>LISTA DE MESES</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>LISTA DE MESES</h1>";

                for (Comit08 e : comit08List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">"+ e.getNocom()+"</td>"
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

        String outcome = "/secured/catalogos/miembrosComiteList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/miembrosComiteView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/miembrosComiteEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/miembrosComiteCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
