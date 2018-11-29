package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Tidoc18;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Tidoc18ManagerLocal;
import com.hrm.sirhapp.service.exception.Tidoc18NotFound;
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
public class Tidoc18Backing extends BaseBacking implements Serializable {

    @EJB
    private Tidoc18ManagerLocal tidoc18Manager;

    @Named
    @Produces
    @RequestScoped
    private Tidoc18 selectedTidoc18;

    private List<Tidoc18> tidoc18List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListTidoc18();
        this.infoMessageModule = "Modulo Tipos de Documento";
    }

    /**
     *
     * @return
     */
    public List<Tidoc18> getTidoc18List() {
        return tidoc18List;
    }

    /**
     *
     * @param tidoc18List
     */
    public void setTidoc18List(List<Tidoc18> tidoc18List) {
        this.tidoc18List = tidoc18List;
    }

    /**
     *
     * @return
     */
    public Tidoc18 getSelectedTidoc18() {
        return selectedTidoc18;
    }

    /**
     *
     * @param selectedTidoc18
     */
    public void setSelectedTidoc18(Tidoc18 selectedTidoc18) {
        this.selectedTidoc18 = selectedTidoc18;
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
    public Tidoc18 getTidoc18Request(Integer idstc) {

        try {

            this.selectedTidoc18 = tidoc18Manager.getTidoc18(idstc);

        } catch (Tidoc18NotFound ex) {
            Logger.getLogger(Tidoc18Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Tidoc18Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedTidoc18;
    }

    /**
     *
     */
    public void getListTidoc18() {

        tidoc18List = tidoc18Manager.getListTidoc18();

        if (!tidoc18List.isEmpty()) {
            infoMessage = tidoc18List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveTidoc18List() {

        tidoc18List = tidoc18Manager.retrieveTidoc18();

        if (tidoc18List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = tidoc18List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateTidoc18() {
        try {

            

            selectedTidoc18.setSttid(Constants.RECORD_ACTIVED);
            selectedTidoc18.setFetid(new Date());
            selectedTidoc18.setUstid(FacesUtil.getUserName());

            tidoc18Manager.updateTidoc18(selectedTidoc18);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Tidoc18NotFound ex) {
            Logger.getLogger(Tidoc18Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteTidoc18() {
        try {
            

            selectedTidoc18.setSttid(Constants.RECORD_DELETED);
            selectedTidoc18.setFetid(new Date());
            selectedTidoc18.setUstid(FacesUtil.getUserName());

            tidoc18Manager.deleteTidoc18(selectedTidoc18);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Tidoc18NotFound ex) {
            Logger.getLogger(Tidoc18Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedTidoc18 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Tipos de Documento</title>"
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
        if (tidoc18List != null) {
            if (tidoc18List.isEmpty() || tidoc18List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Tipos de Documento</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Tipos de Documento</h1>"
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
                        + "<title>Lista de Tipos de Documento</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Tipos de Documento</h1>";

                for (Tidoc18 e : tidoc18List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">"+ e.getNotid()+"</td>"
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

        String outcome = "/secured/catalogos/tiposDocumentosList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/tiposDocumentosView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/tiposDocumentosEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/tiposDocumentosCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
