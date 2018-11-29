package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Nacio09;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Nacio09ManagerLocal;
import com.hrm.sirhapp.service.exception.Nacio09NotFound;
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
public class Nacio09Backing extends BaseBacking implements Serializable {

    @EJB
    private Nacio09ManagerLocal nacio09Manager;

    @Named
    @Produces
    @RequestScoped
    private Nacio09 selectedNacio09;

    private List<Nacio09> nacio09List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListNacio09();
        this.infoMessageModule = "Modulo Nacionalidades";
    }

    /**
     *
     * @return
     */
    public List<Nacio09> getNacio09List() {
        return nacio09List;
    }

    /**
     *
     * @param nacio09List
     */
    public void setNacio09List(List<Nacio09> nacio09List) {
        this.nacio09List = nacio09List;
    }

    /**
     *
     * @return
     */
    public Nacio09 getSelectedNacio09() {
        return selectedNacio09;
    }

    /**
     *
     * @param selectedNacio09
     */
    public void setSelectedNacio09(Nacio09 selectedNacio09) {
        this.selectedNacio09 = selectedNacio09;
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
    public Nacio09 getNacio09Request(Integer idmes) {

        try {

            this.selectedNacio09 = nacio09Manager.getNacio09(idmes);

        } catch (Nacio09NotFound ex) {
            Logger.getLogger(Nacio09Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Nacio09Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedNacio09;
    }

    /**
     *
     */
    public void getListNacio09() {

        nacio09List = nacio09Manager.getListNacio09();

        if (!nacio09List.isEmpty()) {
            infoMessage = nacio09List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveNacio09List() {

        nacio09List = nacio09Manager.retrieveNacio09();

        if (nacio09List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = nacio09List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateNacio09() {
        try {

            

            selectedNacio09.setStnac(Constants.RECORD_ACTIVED);
            selectedNacio09.setFenac(new Date());
            selectedNacio09.setUsnac(FacesUtil.getUserName());

            nacio09Manager.updateNacio09(selectedNacio09);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Nacio09NotFound ex) {
            Logger.getLogger(Nacio09Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteNacio09() {
        try {
            

            selectedNacio09.setStnac(Constants.RECORD_DELETED);
            selectedNacio09.setFenac(new Date());
            selectedNacio09.setUsnac(FacesUtil.getUserName());

            nacio09Manager.deleteNacio09(selectedNacio09);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Nacio09NotFound ex) {
            Logger.getLogger(Nacio09Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedNacio09 != null) {
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
        if (nacio09List != null) {
            if (nacio09List.isEmpty() || nacio09List.size() == 0) {
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

                for (Nacio09 e : nacio09List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">"+ e.getNonac()+"</td>"
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

        String outcome = "/secured/catalogos/nacionalidadesList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/nacionalidadesView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/nacionalidadesEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/nacionalidadesCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
