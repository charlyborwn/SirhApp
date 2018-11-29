package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Requi54;
import com.hrm.sirhapp.service.Requi54ManagerLocal;
import com.hrm.sirhapp.service.exception.Requi54NotFound;
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
public class Requi54Backing extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Requi54ManagerLocal requi54Manager;

    @Named
    @Produces
    @RequestScoped
    private Requi54 selectedRequi54;

    private List<Requi54> requi54List;

    private boolean active;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;

    @PostConstruct
    private void init() {
        this.active = true;
        this.infoMessageModule = "Lista de requisitos";
    }

    /**
     *
     * @return
     */
    public List<Requi54> getRequi54List() {
        return requi54List;
    }

    /**
     *
     * @param requi54List
     */
    public void setRequi54RequestList(List<Requi54> requi54List) {
        this.requi54List = requi54List;
    }

    /**
     *
     * @return
     */
    public Requi54 getSelectedRequi54() {
        return selectedRequi54;
    }

    /**
     *
     * @param selectedRequi54
     */
    public void setSelectedRequi54(Requi54 selectedRequi54) {
        this.selectedRequi54 = selectedRequi54;
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
     * @return
     */
    public Requi54ManagerLocal getRequi54Manager() {
        return requi54Manager;
    }

    /**
     *
     * @param requi54Manager
     */
    public void setRequi54Manager(Requi54ManagerLocal requi54Manager) {
        this.requi54Manager = requi54Manager;
    }

    /**
     *
     * @return
     */
    public boolean isActive() {
        return active;
    }

    /**
     *
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
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
     * @param rfreq
     * @return
     */
    public Requi54 getRequi54Request(String rfreq) {

        try {

            this.selectedRequi54 = requi54Manager.getRequi54(rfreq);

        } catch (Requi54NotFound ex) {
            Logger.getLogger(Requi54Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Requi54Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        } finally {

        }

        return selectedRequi54;
    }

    /**
     *
     */
    public void retrieveRequi54List() {

        requi54List = requi54Manager.retrieveRequi54();

        if (requi54List.isEmpty()) {
            infoMessage = "Registros de la lista de requisitos!";
        } else {
            infoMessage = requi54List.size() + " listas de requisitos";
        }

    }

    /**
     *
     * @param ntdat
     * @return
     */
    public String acivateRequi54(Integer ntdat) {
        try {

            Requi54 activateRequi54 = requi54Manager.getRequi54ById(ntdat);

            activateRequi54.setStreq(Constants.RECORD_ACTIVED);
            activateRequi54.setFereq(new Date());
            activateRequi54.setUsreq(FacesUtil.getUserName());

            requi54Manager.updateRequi54(activateRequi54);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Requi54NotFound ex) {
            Logger.getLogger(Requi54Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        } finally {

        }

        return null;
    }

    /**
     *
     * @return
     */
    public String updateRequi54() {
        try {

            selectedRequi54.setStreq(Constants.RECORD_ACTIVED);
            selectedRequi54.setFereq(new Date());
            selectedRequi54.setUsreq(FacesUtil.getUserName());

            requi54Manager.updateRequi54(selectedRequi54);

            infoMessage = "Actualizado correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Requi54NotFound ex) {
            Logger.getLogger(Requi54Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al actualizar los datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        }
        return null;

    }

    /**
     *
     * @return
     */
    public String deleteRequi54() {
        try {

            selectedRequi54.setStreq(Constants.RECORD_DELETED);
            selectedRequi54.setFereq(new Date());
            selectedRequi54.setUsreq(FacesUtil.getUserName());

            requi54Manager.deleteRequi54(selectedRequi54);

            infoMessage = "Se elimino correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Requi54NotFound ex) {
            Logger.getLogger(Requi54Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al eliminar los datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        }
        return null;
    }

    /**
     *
     * @return
     */
    public String getContentPdf() {
        String str = null;

        if (selectedRequi54 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>LISTA DE REQUISITOS</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>LISTA DE REQUISITOS</h1>"
                    + "<div class=\"page-content\">"
                    + "<table class=\"table-content\">"
                    + "<tr>"
                    + "<td width=\"160px\"><img width=\"160\"  src=\"http://sirhapp.dynu.net/sirhapp/files/" + this.selectedRequi54.getRfreq() + "\" /></td>"
                    + "<td class=\"separador\"></td>"
                    + "<td>"
                    + "<table>"
                    + "<tr>"
                    + "<td class=\"titulo\">RFC:</td>"
                    + "<td class=\"dato\">" + this.selectedRequi54.getRfreq() + "</td>"
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
     * @param action
     * @return
     * @throws IOException
     */
    public String go(int action) throws IOException {

        String outcome = "/secured/aspirantes/requisitos.xhtml?faces-redirect=true";

        if (action == 1) {
            outcome = "/secured/aspirantes/requisitosEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/aspirantes/requisitosCreate.xhtml?faces-redirect=true";
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

}
