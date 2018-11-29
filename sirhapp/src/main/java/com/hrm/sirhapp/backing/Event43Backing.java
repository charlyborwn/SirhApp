package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Event43;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Event43ManagerLocal;
import com.hrm.sirhapp.service.exception.Event43NotFound;
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
public class Event43Backing extends BaseBacking implements Serializable {

    @EJB
    private Event43ManagerLocal event43Manager;

    @Named
    @Produces
    @RequestScoped
    private Event43 selectedEvent43;

    private List<Event43> event43List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Eventos de Transportes";
    }

    /**
     *
     * @return
     */
    public List<Event43> getEvent43List() {
        return event43List;
    }

    /**
     *
     * @param event43List
     */
    public void setEvent43List(List<Event43> event43List) {
        this.event43List = event43List;
    }

    /**
     *
     * @return
     */
    public Event43 getSelectedEvent43() {
        return selectedEvent43;
    }

    /**
     *
     * @param selectedEvent43
     */
    public void setSelectedEvent43(Event43 selectedEvent43) {
        this.selectedEvent43 = selectedEvent43;
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
    public Event43 getEvent43Request(Integer idubi) {

        try {

            this.selectedEvent43 = event43Manager.getEvent43(idubi);

        } catch (Event43NotFound ex) {
            Logger.getLogger(Event43Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Event43Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedEvent43;
    }

    /**
     *
     */
    public void getListEvent43() {

        event43List = event43Manager.getListEvent43();

        if (!event43List.isEmpty()) {
            infoMessage = event43List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveEvent43List() {

        event43List = event43Manager.retrieveEvent43();

        if (event43List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = event43List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateEvent43() {
        try {

            

            selectedEvent43.setSteve(Constants.RECORD_ACTIVED);
            selectedEvent43.setFeeve(new Date());
            selectedEvent43.setUseve(FacesUtil.getUserName());

            event43Manager.updateEvent43(selectedEvent43);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Event43NotFound ex) {
            Logger.getLogger(Event43Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteEvent43() {
        try {
            

            selectedEvent43.setSteve(Constants.RECORD_DELETED);
            selectedEvent43.setFeeve(new Date());
            selectedEvent43.setUseve(FacesUtil.getUserName());

            event43Manager.deleteEvent43(selectedEvent43);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Event43NotFound ex) {
            Logger.getLogger(Event43Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedEvent43 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Eventos de Transportes</title>"
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
        if (event43List != null) {
            if (event43List.isEmpty() || event43List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Eventos de Transportes</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Eventos de Transportes</h1>"
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
                        + "<title>Lista de Eventos de Transportes</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Eventos de Transportes</h1>";

                for (Event43 e : event43List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">"+ e.getNeeve()+"</td>"
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

        String outcome = "/secured/catalogos/eventosTransporteList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/eventosTransporteView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/eventosTransporteEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/eventosTransporteCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
