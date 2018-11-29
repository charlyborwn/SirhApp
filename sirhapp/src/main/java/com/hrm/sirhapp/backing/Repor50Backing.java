package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Repor50;
import com.hrm.sirhapp.model.Petic51;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Usuar24;
import com.hrm.sirhapp.service.Repor50ManagerLocal;
import com.hrm.sirhapp.service.Petic51ManagerLocal;
import com.hrm.sirhapp.service.exception.Petic51AlreadyExists;
import com.hrm.sirhapp.service.exception.Repor50NotFound;
import com.hrm.sirhapp.service.exception.Petic51NotFound;
import com.hrm.sirhapp.util.FacesUtil;
import com.hrm.sirhapp.util.LanguagesUtil;
import java.io.IOException;
import java.io.OutputStream;
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
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@ViewScoped
public class Repor50Backing extends BaseBacking implements Serializable {

    @EJB
    private Repor50ManagerLocal repor50Manager;

    @EJB
    private Petic51ManagerLocal petic51Manager;

    @Named
    @Produces
    @RequestScoped
    private Repor50 selectedRepor50;

    private List<Repor50> repor50List;

    private int status = 1;
    private String searchTitle;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    private List<Petic51> petic51List;
    private Petic51 selectedPetic51;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Comunicados";
    }

    /**
     *
     * @return
     */
    public String getSearchTitle() {
        return searchTitle;
    }

    /**
     *
     * @param searchTitle
     */
    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    /**
     *
     * @return
     */
    public List<Petic51> getPetic51List() {
        return petic51List;
    }

    /**
     *
     * @param petic51List
     */
    public void setPetic51List(List<Petic51> petic51List) {
        this.petic51List = petic51List;
    }

    /**
     *
     * @return
     */
    public Petic51 getSelectedPetic51() {
        return selectedPetic51;
    }

    /**
     *
     * @param selectedPetic51
     */
    public void setSelectedPetic51(Petic51 selectedPetic51) {
        this.selectedPetic51 = selectedPetic51;
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
     * @return
     */
    public Repor50 getSelectedRepor50() {
        return selectedRepor50;
    }

    /**
     *
     * @param selectedRepor50
     */
    public void setSelectedRepor50(Repor50 selectedRepor50) {
        this.selectedRepor50 = selectedRepor50;
    }

    /**
     *
     * @return
     */
    public List<Repor50> getRepor50List() {
        return repor50List;
    }

    /**
     *
     * @param repor50List
     */
    public void setRepor50List(List<Repor50> repor50List) {
        this.repor50List = repor50List;
    }

    /**
     *
     * @return
     */
    public String getInfoMessageModule() {
        return infoMessageModule;
    }

    /**
     *
     * @param infoMessageModule
     */
    public void setInfoMessageModule(String infoMessageModule) {
        this.infoMessageModule = infoMessageModule;
    }

    /**
     *
     * @param idrep
     * @return
     */
    public Repor50 getRepor50Request(Integer idrep) {

        try {

            this.selectedRepor50 = repor50Manager.getRepor50(idrep);

        } catch (Repor50NotFound ex) {
            Logger.getLogger(Repor50Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Repor50Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedRepor50;
    }

    /**
     *
     * @return
     */
    public String requestRepor50Copy() {
        Petic51 Petic51 = new Petic51();
        Usuar24 usuar24 = new Usuar24();

        usuar24.setCvusu(getRequest().getUserPrincipal().getName());

        Petic51.setCvpet(usuar24);
        Petic51.setRepet(getSelectedRepor50());

        try {
            petic51Manager.sendPetic51(Petic51);
            infoMessage = "Solicitudes realizadas";
        } catch (Petic51AlreadyExists ex) {
            Logger.getLogger(Repor50Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "La solicitud ya la realizaste";
        } catch (Exception ex) {
            Logger.getLogger(Repor50Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage("Error al enviar la solicitud"));
        }

        return null;
    }

    /**
     *
     */
    public void retrieveRepor50Requests() {
        petic51List = petic51Manager.viewRequests(getRequest().getUserPrincipal().getName(), status);

        if (petic51List.isEmpty()) {
            infoMessage = "No hay solicitudes.";
        } else {
            infoMessage = petic51List.size() + " de solicitudes encontradas.";
        }

    }

    /**
     *
     */
    public void getListRepor50() {

        repor50List = repor50Manager.getListRepor50();

        if (!repor50List.isEmpty()) {
            infoMessage = repor50List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void getListInnactiveRepor50() {

        repor50List = repor50Manager.getListInnactiveRepor50();

        if (repor50List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = repor50List.size() + " registros";
        }

    }

    /**
     *
     */
    public void retrieveRepor50List() {
        Repor50 searchableRepor50 = new Repor50();

        searchableRepor50.setTirep(searchTitle);

        System.out.println(searchableRepor50.getTirep());

        repor50List = repor50Manager.getAllRepor50s(searchableRepor50);

        if (repor50List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = repor50List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String downloadRepor50() {
        Repor50 currentSelectedRepor50 = getSelectedRepor50();

        Repor50 repor50;
        byte[] content;

        try {
            repor50 = repor50Manager.getRepor50(currentSelectedRepor50.getIdrep());
            content = repor50Manager.getRepor50Content(currentSelectedRepor50.getIdrep());

        } catch (Repor50NotFound ex) {
            Logger.getLogger(Repor50Backing.class.getName()).log(Level.SEVERE, "No encontrado !!!", ex);
            return null;
        }

        ExternalContext externalContext = getContext().getExternalContext();

        externalContext.responseReset();
        externalContext.setResponseContentType(Constants.APP_PDF_TYPE);
        externalContext.setResponseContentLength(content.length);
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + repor50.getTirep() + ".pdf\"");
        OutputStream output = null;

        try {
            output = externalContext.getResponseOutputStream();

            output.write(content);

            output.flush();
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(Repor50Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage("No se pudo descargar el archivo"));
        } finally {
            getContext().responseComplete();
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String approveRequest() {
        Petic51 currentPetic51 = getSelectedPetic51();

        try {
            petic51Manager.approvePetic51(currentPetic51.getIdpet());
            infoMessage = "La solicitud fue aprovada";
        } catch (Petic51NotFound ex) {
            Logger.getLogger(Repor50Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage("La solicitud no fue aprovada"));
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String rejectRequest() {
        Petic51 currentPetic51 = getSelectedPetic51();

        try {
            petic51Manager.rejectPetic51(currentPetic51.getIdpet());
            infoMessage = "La solicitud fue rechazada";
        } catch (Petic51NotFound ex) {
            Logger.getLogger(Repor50Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage("No se pudo rechazar la solicitud"));
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String updateRepor50() {
        try {

            selectedRepor50.setStrep(Constants.RECORD_ACTIVED);
            selectedRepor50.setFerep(new Date());
            selectedRepor50.setUsrep(FacesUtil.getUserName());

            repor50Manager.updateRepor50(selectedRepor50);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Repor50NotFound ex) {
            Logger.getLogger(Repor50Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteRepor50() {
        try {

            selectedRepor50.setStrep(Constants.RECORD_DELETED);
            selectedRepor50.setFerep(new Date());
            selectedRepor50.setUsrep(FacesUtil.getUserName());

            repor50Manager.deleteRepor50(selectedRepor50);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Repor50NotFound ex) {
            Logger.getLogger(Repor50Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedRepor50 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Comunicados</title>"
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
        if (repor50List != null) {
            if (repor50List.isEmpty() || repor50List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Comunicados</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Comunicados</h1>"
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
                        + "<title>Lista de Comunicados</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Comunicados</h1>";

                for (Repor50 e : repor50List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">" + e.getDerep() + "</td>"
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

        String outcome = "/secured/catalogos/comunicadosList.xhtml?faces-redirect=true";

        if (action == 4) {
            outcome = "/secured/catalogos/comunicadosInactivos.xhtml?faces-redirect=true";
        }
        if (action == 3) {
            outcome = "/secured/catalogos/comunicadosView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/comunicadosEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/comunicadosCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
