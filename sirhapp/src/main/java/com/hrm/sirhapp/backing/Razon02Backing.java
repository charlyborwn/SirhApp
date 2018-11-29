package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Razon02;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Razon02ManagerLocal;
import com.hrm.sirhapp.service.exception.Razon02NotFound;
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
public class Razon02Backing extends BaseBacking implements Serializable {

    @EJB
    private Razon02ManagerLocal razon02Manager;

    @Named
    @Produces
    @RequestScoped
    private Razon02 selectedRazon02;

    private List<Razon02> razon02List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListRazon02();
        this.infoMessageModule = "Modulo Razon sin Documentos";
    }

    /**
     *
     * @return
     */
    public List<Razon02> getRazon02List() {
        return razon02List;
    }

    /**
     *
     * @param razon02List
     */
    public void setRazon02List(List<Razon02> razon02List) {
        this.razon02List = razon02List;
    }

    /**
     *
     * @return
     */
    public Razon02 getSelectedRazon02() {
        return selectedRazon02;
    }

    /**
     *
     * @param selectedRazon02
     */
    public void setSelectedRazon02(Razon02 selectedRazon02) {
        this.selectedRazon02 = selectedRazon02;
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
    public Razon02 getRazon02Request(Integer idapr) {

        try {

            this.selectedRazon02 = razon02Manager.getRazon02(idapr);

        } catch (Razon02NotFound ex) {
            Logger.getLogger(Razon02Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Razon02Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedRazon02;
    }

    /**
     *
     */
    public void getListRazon02() {

        razon02List = razon02Manager.getListRazon02();

        if (!razon02List.isEmpty()) {
            infoMessage = razon02List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveRazon02List() {

        razon02List = razon02Manager.retrieveRazon02();

        if (razon02List.isEmpty()) {
            infoMessage = "No existen avisos";
        } else {
            infoMessage = razon02List.size() + " avisos";
        }

    }

    /**
     *
     * @return
     */
    public String updateRazon02() {
        try {

            

            selectedRazon02.setStraz(Constants.RECORD_ACTIVED);
            selectedRazon02.setFeraz(new Date());
            selectedRazon02.setUsraz(FacesUtil.getUserName());

            razon02Manager.updateRazon02(selectedRazon02);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Razon02NotFound ex) {
            Logger.getLogger(Razon02Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteRazon02() {
        try {
            

            selectedRazon02.setStraz(Constants.RECORD_DELETED);
            selectedRazon02.setFeraz(new Date());
            selectedRazon02.setUsraz(FacesUtil.getUserName());

            razon02Manager.deleteRazon02(selectedRazon02);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Razon02NotFound ex) {
            Logger.getLogger(Razon02Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedRazon02 != null) {
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
        if (razon02List != null) {
            if (razon02List.isEmpty() || razon02List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>LISTA DE CAUSAS SIN DOCUMENTO</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>LISTA DE CAUSAS SIN DOCUMENTO</h1>"
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
                        + "<title>LISTA DE CAUSAS SIN DOCUMENTO</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>LISTA DE CAUSAS SIN DOCUMENTO</h1>";

                for (Razon02 e : razon02List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">CAUSA:</td>"
                            + "<td class=\"dato\">"+ e.getDeraz()+"</td>"
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

        String outcome = "/secured/catalogos/causasSinDocumentoList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/causasSinDocumentoView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/causasSinDocumentoEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/causasSinDocumentoCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
