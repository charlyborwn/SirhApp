package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Ident49a;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Ident49aManagerLocal;
import com.hrm.sirhapp.service.exception.Ident49aNotFound;
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
public class Ident49aBacking extends BaseBacking implements Serializable {

    @EJB
    private Ident49aManagerLocal ident49aManager;

    @Named
    @Produces
    @ViewScoped
    private Ident49a selectedIdent49a;

    private List<Ident49a> ident49aList;

    private int status = 1;
    private boolean active;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;

    @PostConstruct
    private void init() {
        this.active = true;
        this.infoMessageModule = "Modulo Datos de Identidad Aspirante";
    }

    /**
     *
     * @return
     */
    public Ident49aManagerLocal getIdent49aManager() {
        return ident49aManager;
    }

    /**
     *
     * @param ident49aManager
     */
    public void setIdent49aManager(Ident49aManagerLocal ident49aManager) {
        this.ident49aManager = ident49aManager;
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
    public List<Ident49a> getIdent49aList() {
        return ident49aList;
    }

    /**
     *
     * @param ident49aList
     */
    public void setIdent49aRequestList(List<Ident49a> ident49aList) {
        this.ident49aList = ident49aList;
    }

    /**
     *
     * @return
     */
    public Ident49a getSelectedIdent49a() {
        return selectedIdent49a;
    }

    /**
     *
     * @param selectedIdent49a
     */
    public void setSelectedIdent49a(Ident49a selectedIdent49a) {
        this.selectedIdent49a = selectedIdent49a;
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
     * @param rfideA
     * @return
     */
    public Ident49a getIdent49aRequest(String rfideA) {

        try {

            this.selectedIdent49a = ident49aManager.getIdent49a(rfideA);

        } catch (Ident49aNotFound ex) {
            Logger.getLogger(Ident49aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Ident49aBacking.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        } finally {
            if (ident49aManager.alreadyExistsInnactive(rfideA)) {

                this.infoMessage = "El registro fue eliminado previamente";
                this.active = false;
            }
        }

        return selectedIdent49a;
    }

    /**
     *
     * @param rfideA
     */
    public void getListIdent49a(String rfideA) {

        ident49aList = ident49aManager.getListIdent49a();

        if (ident49aList.isEmpty()) {
            infoMessage = "No existen datos de Identidad del aspirante!";
        } else {
            infoMessage = ident49aList.size() + " Aspirantes";
        }
    }

    /**
     *
     */
    public void retrieveIdent49aList() {

        ident49aList = ident49aManager.retrieveIdent49a();

        if (ident49aList.isEmpty()) {
            infoMessage = "No existen datosIdentidad!";
        } else {
            infoMessage = ident49aList.size() + " DatosIdentidad";
        }

    }

    /**
     *
     * @param rfideA
     * @return
     */
    public String acivateIdent49a(String rfideA) {
        try {

            Ident49a activateIdent49a = ident49aManager.getIdent49aById(rfideA);

            activateIdent49a.setStideA(Constants.RECORD_ACTIVED);
            activateIdent49a.setFeideA(new Date());
            activateIdent49a.setUsideA(FacesUtil.getUserName());

            ident49aManager.updateIdent49a(activateIdent49a);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Ident49aNotFound ex) {
            Logger.getLogger(Ident49Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        } finally {
            if (ident49aManager.alreadyExistsInnactive(rfideA)) {

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
    public String updateIdent49a() {
        try {

            selectedIdent49a.setStideA(Constants.RECORD_ACTIVED);
            selectedIdent49a.setFeideA(new Date());
            selectedIdent49a.setUsideA(FacesUtil.getUserName());

            ident49aManager.updateIdent49a(selectedIdent49a);

            infoMessage = "Aspirante actualizado correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Ident49aNotFound ex) {
            Logger.getLogger(Ident49aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al actualizar los datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        }
        return null;

    }

    /**
     *
     * @return
     */
    public String deleteIdent49a() {
        try {

            selectedIdent49a.setStideA(Constants.RECORD_DELETED);
            selectedIdent49a.setFeideA(new Date());
            selectedIdent49a.setUsideA(FacesUtil.getUserName());

            ident49aManager.deleteIdent49a(selectedIdent49a);

            infoMessage = "Se elimino correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Ident49aNotFound ex) {
            Logger.getLogger(Ident49aBacking.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedIdent49a != null) {

            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>DATOS DE IDENTIDAD DEL ASPIRANTE</title>"
                    + "</head>"
                    + "<body>"
                    //                    + "<h1>DATOS DE IDENTIDAD DEL ASPIRANTE:" + this.selectedIdent49a.getRfbenA() + "</h1>"
                    + "<div class=\"page-content\">"
                    + "<table class=\"table-content\">"
                    + "<tr>"
                    + "<td class=\"separador\"></td>"
                    + "<td>"
                    + "<table>"
                    + "<tr>"
                    + "<td class=\"titulo\">TIPO:</td>"
                    //                    + "<td class=\"dato\">" + this.selectedIdent49a.getTpbenA() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">GENERO:</td>"
                    //                    + "<td class=\"dato\">" + this.selectedIdent49a.getSebenA() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">FECHA DE NACIMIENTO:</td>"
                    //                    + "<td class=\"dato\">" + this.selectedIdent49a.getFnbenA() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">PORCENTAJE A OTORGAR:</td>"
                    //                    + "<td class=\"dato\">" + this.selectedIdent49a.getPobenA() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">NOMBRE COMPLETO:</td>"
                    + "<td class=\"dato\">" + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">OBSERVACIONES:</td>"
                    //                    + "<td class=\"dato\">" + this.selectedIdent49a.getObbenA() + "</td>"
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

        String outcome = "/secured/aspirantes/datosIdentidad.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/aspirantes/datosIdentidad.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/aspirantes/datosIdentidadEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/aspirantes/datosIdentidadCreate.xhtml?faces-redirect=true";
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

}
