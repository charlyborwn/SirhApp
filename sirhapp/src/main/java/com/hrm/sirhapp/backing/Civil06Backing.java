package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Civil06;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Civil06ManagerLocal;
import com.hrm.sirhapp.service.exception.Civil06NotFound;
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
public class Civil06Backing extends BaseBacking implements Serializable {

    @EJB
    private Civil06ManagerLocal civil06Manager;

    @Named
    @Produces
    @RequestScoped
    private Civil06 selectedCivil06;

    private List<Civil06> civil06List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListCivil06();
        this.infoMessageModule = "Modulo Estado Civil";
    }

    /**
     *
     * @return
     */
    public List<Civil06> getCivil06List() {
        return civil06List;
    }

    /**
     *
     * @param civil06List
     */
    public void setCivil06List(List<Civil06> civil06List) {
        this.civil06List = civil06List;
    }

    /**
     *
     * @return
     */
    public Civil06 getSelectedCivil06() {
        return selectedCivil06;
    }

    /**
     *
     * @param selectedCivil06
     */
    public void setSelectedCivil06(Civil06 selectedCivil06) {
        this.selectedCivil06 = selectedCivil06;
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
    public Civil06 getCivil06Request(Integer idapr) {

        try {

            this.selectedCivil06 = civil06Manager.getCivil06(idapr);

        } catch (Civil06NotFound ex) {
            Logger.getLogger(Civil06Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Civil06Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedCivil06;
    }

    /**
     *
     */
    public void getListCivil06() {

        civil06List = civil06Manager.getListCivil06();

        if (!civil06List.isEmpty()) {
            infoMessage = civil06List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveCivil06List() {

        civil06List = civil06Manager.retrieveCivil06();

        if (civil06List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = civil06List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateCivil06() {
        try {

            

            selectedCivil06.setStciv(Constants.RECORD_ACTIVED);
            selectedCivil06.setFeciv(new Date());
            selectedCivil06.setUsciv(FacesUtil.getUserName());

            civil06Manager.updateCivil06(selectedCivil06);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Civil06NotFound ex) {
            Logger.getLogger(Civil06Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteCivil06() {
        try {
            

            selectedCivil06.setStciv(Constants.RECORD_DELETED);
            selectedCivil06.setFeciv(new Date());
            selectedCivil06.setUsciv(FacesUtil.getUserName());

            civil06Manager.deleteCivil06(selectedCivil06);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Civil06NotFound ex) {
            Logger.getLogger(Civil06Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedCivil06 != null) {
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
        if (civil06List != null) {
            if (civil06List.isEmpty() || civil06List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>LISTA DE DEPARTAMENTOS</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>LISTA DE DEPARTAMENTOS</h1>"
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
                        + "<title>LISTA DE DEPARTAMENTOS</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>LISTA DE DEPARTAMENTOS</h1>";

                for (Civil06 e : civil06List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">"+ e.getNociv()+"</td>"
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

        String outcome = "/secured/catalogos/estadoCivilList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/estadoCivilView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/estadoCivilEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/estadoCivilCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
