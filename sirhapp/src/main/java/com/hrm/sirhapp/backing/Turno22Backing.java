package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Turno22;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Turno22ManagerLocal;
import com.hrm.sirhapp.service.exception.Turno22NotFound;
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
public class Turno22Backing extends BaseBacking implements Serializable {

    @EJB
    private Turno22ManagerLocal turno22Manager;

    @Named
    @Produces
    @RequestScoped
    private Turno22 selectedTurno22;

    private List<Turno22> turno22List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListTurno22();
        this.infoMessageModule = "Modulo Turnos";
    }

    /**
     *
     * @return
     */
    public List<Turno22> getTurno22List() {
        return turno22List;
    }

    /**
     *
     * @param turno22List
     */
    public void setTurno22List(List<Turno22> turno22List) {
        this.turno22List = turno22List;
    }

    /**
     *
     * @return
     */
    public Turno22 getSelectedTurno22() {
        return selectedTurno22;
    }

    /**
     *
     * @param selectedTurno22
     */
    public void setSelectedTurno22(Turno22 selectedTurno22) {
        this.selectedTurno22 = selectedTurno22;
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
    public Turno22 getTurno22Request(Integer idstc) {

        try {

            this.selectedTurno22 = turno22Manager.getTurno22(idstc);

        } catch (Turno22NotFound ex) {
            Logger.getLogger(Turno22Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Turno22Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedTurno22;
    }

    /**
     *
     */
    public void getListTurno22() {

        turno22List = turno22Manager.getListTurno22();

        if (!turno22List.isEmpty()) {
            infoMessage = turno22List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveTurno22List() {

        turno22List = turno22Manager.retrieveTurno22();

        if (turno22List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = turno22List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateTurno22() {
        try {

            

            selectedTurno22.setSttur(Constants.RECORD_ACTIVED);
            selectedTurno22.setFetur(new Date());
            selectedTurno22.setUstur(FacesUtil.getUserName());

            turno22Manager.updateTurno22(selectedTurno22);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Turno22NotFound ex) {
            Logger.getLogger(Turno22Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteTurno22() {
        try {
            

            selectedTurno22.setSttur(Constants.RECORD_DELETED);
            selectedTurno22.setFetur(new Date());
            selectedTurno22.setUstur(FacesUtil.getUserName());

            turno22Manager.deleteTurno22(selectedTurno22);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Turno22NotFound ex) {
            Logger.getLogger(Turno22Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedTurno22 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Turnos</title>"
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
        if (turno22List != null) {
            if (turno22List.isEmpty() || turno22List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Turnos</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Turnos</h1>"
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
                        + "<title>Lista de Turnos</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Turnos</h1>";

                for (Turno22 e : turno22List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">"+ e.getNotur()+"</td>"
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

        String outcome = "/secured/catalogos/turnosList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/turnosView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/turnosEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/turnosCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
