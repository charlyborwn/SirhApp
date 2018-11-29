package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Ident49;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Ident49ManagerLocal;
import com.hrm.sirhapp.service.exception.Ident49NotFound;
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
public class Ident49Backing extends BaseBacking implements Serializable {

    @EJB
    private Ident49ManagerLocal ident49Manager;

    @Named
    @Produces
    @RequestScoped
    private Ident49 selectedIdent49;

    private List<Ident49> ident49List;

    private int status = 1;
    private boolean active;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;

    @PostConstruct
    private void init() {
        this.active = true;
        this.infoMessageModule = "Modulo Datos de Identidad Trabajador";
    }

    /**
     *
     * @return
     */
    public Ident49ManagerLocal getIdent49Manager() {
        return ident49Manager;
    }

    /**
     *
     * @param ident49Manager
     */
    public void setIdent49Manager(Ident49ManagerLocal ident49Manager) {
        this.ident49Manager = ident49Manager;
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
     * @return
     */
    public List<Ident49> getIdent49List() {
        return ident49List;
    }

    /**
     *
     * @param ident49List
     */
    public void setIdent49RequestList(List<Ident49> ident49List) {
        this.ident49List = ident49List;
    }

    /**
     *
     * @return
     */
    public Ident49 getSelectedIdent49() {
        return selectedIdent49;
    }

    /**
     *
     * @param selectedIdent49
     */
    public void setSelectedIdent49(Ident49 selectedIdent49) {
        this.selectedIdent49 = selectedIdent49;
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
     * @param ntide
     * @return
     */
    public Ident49 getIdent49Request(Integer ntide) {

        try {

            this.selectedIdent49 = ident49Manager.getIdent49(ntide);

        } catch (Ident49NotFound ex) {
            Logger.getLogger(Ident49Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Ident49Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        } finally {
            if (ident49Manager.alreadyExistsInnactive(ntide)) {

                this.infoMessage = "El registro fue eliminado previamente";
                this.active = false;
            }
        }

        return selectedIdent49;
    }

    /**
     *
     * @param rfbenA
     */
    public void getListIdent49(String rfbenA) {

        ident49List = ident49Manager.getListIdent49();

        if (ident49List.isEmpty()) {
            infoMessage = "No existen registros!";
        } else {
            infoMessage = ident49List.size() + " registros";
        }
    }

    /**
     *
     */
    public void retrieveIdent49List() {

        ident49List = ident49Manager.retrieveIdent49();

        if (ident49List.isEmpty()) {
            infoMessage = "No existen registros!";
        } else {
            infoMessage = ident49List.size() + " registros";
        }

    }

    /**
     *
     * @param ntide
     * @return
     */
    public String acivateIdent49(Integer ntide) {
        try {

            Ident49 activateIdent49 = ident49Manager.getIdent49ById(ntide);

            activateIdent49.setStide(Constants.RECORD_ACTIVED);
            activateIdent49.setFeide(new Date());
            activateIdent49.setUside(FacesUtil.getUserName());

            ident49Manager.updateIdent49(activateIdent49);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Ident49NotFound ex) {
            Logger.getLogger(Ident49Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        } finally {
            if (ident49Manager.alreadyExistsInnactive(ntide)) {

                this.infoMessage = "El registro fue eliminado previamente";
                this.active = false;
            }
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String updateIdent49() {
        try {

            selectedIdent49.setStide(Constants.RECORD_ACTIVED);
            selectedIdent49.setFeide(new Date());
            selectedIdent49.setUside(FacesUtil.getUserName());

            ident49Manager.updateIdent49(selectedIdent49);

            infoMessage = "Datos actualizados correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Ident49NotFound ex) {
            Logger.getLogger(Ident49Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al actualizar los datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        }
        return null;

    }

    /**
     *
     * @return
     */
    public String deleteIdent49() {
        try {

            selectedIdent49.setStide(Constants.RECORD_DELETED);
            selectedIdent49.setFeide(new Date());
            selectedIdent49.setUside(FacesUtil.getUserName());

            ident49Manager.deleteIdent49(selectedIdent49);

            infoMessage = "Registro de Identidad eliminado correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Ident49NotFound ex) {
            Logger.getLogger(Ident49Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedIdent49 != null) {

            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>DATOS DE IDENTIDAD DEL TRABAJADOR</title>"
                    + "</head>"
                    + "<body>"
                    //                    + "<h1>IDENTIDAD DEL TRABAJADOR:" + this.selectedIdent49.getRfbenA() + "</h1>"
                    + "<div class=\"page-content\">"
                    + "<table class=\"table-content\">"
                    + "<tr>"
                    + "<td class=\"separador\"></td>"
                    + "<td>"
                    + "<table>"
                    + "<tr>"
                    + "<td class=\"titulo\">TIPO:</td>"
                    //                    + "<td class=\"dato\">" + this.selectedIdent49.getTpbenA() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">GENERO:</td>"
                    //                    + "<td class=\"dato\">" + this.selectedIdent49.getSebenA() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">FECHA DE NACIMIENTO:</td>"
                    //                    + "<td class=\"dato\">" + this.selectedIdent49.getFnbenA() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">PORCENTAJE A OTORGAR:</td>"
                    //                    + "<td class=\"dato\">" + this.selectedIdent49.getPobenA() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">NOMBRE COMPLETO:</td>"
                    + "<td class=\"dato\">" + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">OBSERVACIONES:</td>"
                    //                    + "<td class=\"dato\">" + this.selectedIdent49.getObbenA() + "</td>"
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

        String outcome = "/secured/trabajadores/datosIdentidad.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/trabajadores/verDatosIdentidad.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/trabajadores/datosIdentidadEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/trabajadores/datosIdentidadCreate.xhtml?faces-redirect=true";
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

}
