package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Strab15;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Strab15ManagerLocal;
import com.hrm.sirhapp.service.exception.Strab15NotFound;
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
public class Strab15Backing extends BaseBacking implements Serializable {

    @EJB
    private Strab15ManagerLocal strab15Manager;

    @Named
    @Produces
    @RequestScoped
    private Strab15 selectedStrab15;

    private List<Strab15> strab15List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListStrab15();
        this.infoMessageModule = "Modulo Status Trabajadores";
    }

    /**
     *
     * @return
     */
    public List<Strab15> getStrab15List() {
        return strab15List;
    }

    /**
     *
     * @param strab15List
     */
    public void setStrab15List(List<Strab15> strab15List) {
        this.strab15List = strab15List;
    }

    /**
     *
     * @return
     */
    public Strab15 getSelectedStrab15() {
        return selectedStrab15;
    }

    /**
     *
     * @param selectedStrab15
     */
    public void setSelectedStrab15(Strab15 selectedStrab15) {
        this.selectedStrab15 = selectedStrab15;
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
    public Strab15 getStrab15Request(Integer idstc) {

        try {

            this.selectedStrab15 = strab15Manager.getStrab15(idstc);

        } catch (Strab15NotFound ex) {
            Logger.getLogger(Strab15Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Strab15Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedStrab15;
    }

    /**
     *
     */
    public void getListStrab15() {

        strab15List = strab15Manager.getListStrab15();

        if (!strab15List.isEmpty()) {
            infoMessage = strab15List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveStrab15List() {

        strab15List = strab15Manager.retrieveStrab15();

        if (strab15List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = strab15List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateStrab15() {
        try {

            

            selectedStrab15.setStstr(Constants.RECORD_ACTIVED);
            selectedStrab15.setFestr(new Date());
            selectedStrab15.setUsstr(FacesUtil.getUserName());

            strab15Manager.updateStrab15(selectedStrab15);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Strab15NotFound ex) {
            Logger.getLogger(Strab15Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteStrab15() {
        try {
            

            selectedStrab15.setStstr(Constants.RECORD_DELETED);
            selectedStrab15.setFestr(new Date());
            selectedStrab15.setUsstr(FacesUtil.getUserName());

            strab15Manager.deleteStrab15(selectedStrab15);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Strab15NotFound ex) {
            Logger.getLogger(Strab15Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedStrab15 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Status Trabajadores</title>"
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
        if (strab15List != null) {
            if (strab15List.isEmpty() || strab15List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Status Trabajadores</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Status Trabajadores</h1>"
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
                        + "<title>Lista de Status Trabajadores</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Status Trabajadores</h1>";

                for (Strab15 e : strab15List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">" + e.getDestr() + "</td>"
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

        String outcome = "/secured/catalogos/statusTrabajadoresList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/statusTrabajadoresView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/statusTrabajadoresEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/statusTrabajadoresCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
