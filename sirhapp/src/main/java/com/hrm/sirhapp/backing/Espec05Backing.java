package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Espec05;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Espec05ManagerLocal;
import com.hrm.sirhapp.service.exception.Espec05NotFound;
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
public class Espec05Backing extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Espec05ManagerLocal espec05Manager;

    @Named
    @Produces
    @RequestScoped
    private Espec05 selectedEspec05;

    private List<Espec05> espec05List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListEspec05();
        this.infoMessageModule = "Modulo Especialidades";
    }

    /**
     *
     * @return
     */
    public List<Espec05> getEspec05List() {
        return espec05List;
    }

    /**
     *
     * @param espec05List
     */
    public void setEspec05List(List<Espec05> espec05List) {
        this.espec05List = espec05List;
    }

    /**
     *
     * @return
     */
    public Espec05 getSelectedEspec05() {
        return selectedEspec05;
    }

    /**
     *
     * @param selectedEspec05
     */
    public void setSelectedEspec05(Espec05 selectedEspec05) {
        this.selectedEspec05 = selectedEspec05;
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
    public Espec05 getEspec05Request(Integer idapr) {

        try {

            this.selectedEspec05 = espec05Manager.getEspec05(idapr);

        } catch (Espec05NotFound ex) {
            Logger.getLogger(Espec05Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Espec05Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedEspec05;
    }

    /**
     *
     */
    public void getListEspec05() {

        espec05List = espec05Manager.getListEspec05();

        if (!espec05List.isEmpty()) {
            infoMessage = espec05List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveEspec05List() {

        espec05List = espec05Manager.retrieveEspec05();

        if (espec05List.isEmpty()) {
            infoMessage = "No existen avisos";
        } else {
            infoMessage = espec05List.size() + " avisos";
        }

    }

    /**
     *
     * @return
     */
    public String updateEspec05() {
        try {

            selectedEspec05.setStesp(Constants.RECORD_ACTIVED);
            selectedEspec05.setFeesp(new Date());
            selectedEspec05.setUsesp(FacesUtil.getUserName());

            espec05Manager.updateEspec05(selectedEspec05);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Espec05NotFound ex) {
            Logger.getLogger(Espec05Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteEspec05() {
        try {

            selectedEspec05.setStesp(Constants.RECORD_DELETED);
            selectedEspec05.setFeesp(new Date());
            selectedEspec05.setUsesp(FacesUtil.getUserName());

            espec05Manager.deleteEspec05(selectedEspec05);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Espec05NotFound ex) {
            Logger.getLogger(Espec05Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedEspec05 != null) {
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
        if (espec05List != null) {
            if (espec05List.isEmpty() || espec05List.size() == 0) {
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

                for (Espec05 e : espec05List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">" + e.getNoesp() + "</td>"
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

        String outcome = "/secured/catalogos/especialidadesList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/especialidadesView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/especialidadesEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/especialidadesCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
