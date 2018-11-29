package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Prove48;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Prove48ManagerLocal;
import com.hrm.sirhapp.service.exception.Prove48NotFound;
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
public class Prove48Backing extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Prove48ManagerLocal prove48Manager;

    @Named
    @Produces
    @RequestScoped
    private Prove48 selectedProve48;

    private List<Prove48> prove48List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListProve48();
        this.infoMessageModule = "Modulo Proveedores";
    }

    /**
     *
     * @return
     */
    public List<Prove48> getProve48List() {
        return prove48List;
    }

    /**
     *
     * @param prove48List
     */
    public void setProve48List(List<Prove48> prove48List) {
        this.prove48List = prove48List;
    }

    /**
     *
     * @return
     */
    public Prove48 getSelectedProve48() {
        return selectedProve48;
    }

    /**
     *
     * @param selectedProve48
     */
    public void setSelectedProve48(Prove48 selectedProve48) {
        this.selectedProve48 = selectedProve48;
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
     * @param idpro
     * @return
     */
    public Prove48 getProve48Request(Integer idpro) {

        try {

            this.selectedProve48 = prove48Manager.getProve48(idpro);

        } catch (Prove48NotFound ex) {
            Logger.getLogger(Prove48Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Prove48Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedProve48;
    }

    /**
     *
     */
    public void getListProve48() {

        prove48List = prove48Manager.getListProve48();

        if (!prove48List.isEmpty()) {
            infoMessage = prove48List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveProve48List() {

        prove48List = prove48Manager.retrieveProve48();

        if (prove48List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = prove48List.size() + " registros";
        }

    }

    /**
     *
     * @param rfpro
     */
    public void retrieveProve48(String rfpro) {

        prove48List = prove48Manager.retrieveProve48();

        if (prove48List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = prove48List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateProve48() {
        try {

            selectedProve48.setStpro(Constants.RECORD_ACTIVED);
            selectedProve48.setFepro(new Date());
            selectedProve48.setUspro(FacesUtil.getUserName());

            prove48Manager.updateProve48(selectedProve48);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Prove48NotFound ex) {
            Logger.getLogger(Prove48Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteProve48() {
        try {

            selectedProve48.setStpro(Constants.RECORD_DELETED);
            selectedProve48.setFepro(new Date());
            selectedProve48.setUspro(FacesUtil.getUserName());

            prove48Manager.deleteProve48(selectedProve48);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Prove48NotFound ex) {
            Logger.getLogger(Prove48Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedProve48 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Proveedores</title>"
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
        if (prove48List != null) {
            if (prove48List.isEmpty() || prove48List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Proveedores</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Proveedores</h1>"
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
                        + "<title>Lista de Proveedores</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Proveedores</h1>";

                for (Prove48 e : prove48List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">" + e.getNcpro() + "</td>"
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

        String outcome = "/secured/catalogos/proveedoresList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/proveedoresView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/proveedoresEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/proveedoresCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
