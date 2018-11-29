package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Categ01;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Categ01ManagerLocal;
import com.hrm.sirhapp.service.Empre04ManagerLocal;
import com.hrm.sirhapp.service.exception.Categ01NotFound;
import com.hrm.sirhapp.service.exception.Empre04NotFound;
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
import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
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
public class Categ01Backing extends BaseBacking implements Serializable {

    @EJB
    private Categ01ManagerLocal categ01Manager;

    @EJB
    private Empre04ManagerLocal empre04Manager;

    @Named
    @Produces
    @RequestScoped
    private Categ01 selectedCateg01;

    private List<Categ01> categ01List;

    private int status = 1;
    private String infoMessage;
    private String infoMessageModule;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListCateg01();
        this.infoMessageModule = "Modulo Categorias";
    }

    /**
     *
     * @return
     */
    public List<Categ01> getCateg01List() {
        return categ01List;
    }

    /**
     *
     * @param categ01List
     */
    public void setCateg01RequestList(List<Categ01> categ01List) {
        this.categ01List = categ01List;
    }

    /**
     *
     * @return
     */
    public Categ01 getSelectedCateg01() {
        return selectedCateg01;
    }

    /**
     *
     * @param selectedCateg01
     */
    public void setSelectedCateg01(Categ01 selectedCateg01) {
        this.selectedCateg01 = selectedCateg01;
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
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     *
     * @param idbenA
     * @return
     */
    public Categ01 getCateg01Request(Integer idbenA) {

        try {

            this.selectedCateg01 = categ01Manager.getCateg01(idbenA);

        } catch (Categ01NotFound ex) {
            Logger.getLogger(Categ01Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Categ01Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedCateg01;
    }

    /**
     *
     */
    public void getListCateg01() {

        categ01List = categ01Manager.getListCateg01();

        if (categ01List.isEmpty()) {
            infoMessage = "No existen Registros!";
        } else {
            infoMessage = categ01List.size() + " Registros";
        }
    }

    /**
     *
     */
    public void retrieveCateg01List() {

        categ01List = categ01Manager.retrieveCateg01();

        if (categ01List.isEmpty()) {
            infoMessage = "No existen Registros!";
        } else {
            infoMessage = categ01List.size() + " Registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateCateg01() {
        try {

            selectedCateg01.setStcat(Constants.RECORD_ACTIVED);
            selectedCateg01.setFecat(new Date());
            selectedCateg01.setUscat(FacesUtil.getUserName());

            categ01Manager.updateCateg01(selectedCateg01);

            infoMessage = "Registro actualizado correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Categ01NotFound ex) {
            Logger.getLogger(Categ01Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al actualizar los datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        }
        return null;

    }

    /**
     *
     * @return
     */
    public String deleteCateg01() {
        try {

            selectedCateg01.setStcat(Constants.RECORD_DELETED);
            selectedCateg01.setFecat(new Date());
            selectedCateg01.setUscat(FacesUtil.getUserName());

            categ01Manager.deleteCateg01(selectedCateg01);

            infoMessage = "Registro eliminado correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Categ01NotFound ex) {
            Logger.getLogger(Categ01Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedCateg01 != null) {
            if (selectedCateg01.getIdcat() > 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Categoria de Trabajador</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Categoria." + this.selectedCateg01.getIdcat() + "</h1>"
                        + "<div class=\"page-content\">"
                        + "<table class=\"table-content\">"
                        + "<tr>"
                        + "<td class=\"separador\"></td>"
                        + "<td>"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">Clave Categoria:</td>"
                        + "<td class=\"dato\">" + this.selectedCateg01.getCvcat() + "</td>"
                        + "</tr>"
                        + "</table></td>"
                        + "</tr>"
                        + "</table>"
                        + "</div>"
                        + "</body>"
                        + "</html>";
                this.contentPdf = str;
            }
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

        if (categ01List.isEmpty() || categ01List.size() == 0) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Catalogo de Categoraias de Trabajadores</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>Catalogo de Categoraias de Trabajadores</h1>"
                    + "<div class=\"page-content\">"
                    + "<table class=\"table-content\">"
                    + "<tr>"
                    + "<td>No existen registros</td>"
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
                    + "<title>Catalogo de Categoraias de Trabajadores</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>Catalogo de Categoraias de Trabajadores </h1>";

            for (Categ01 e : categ01List) {
                benef = benef + "<div class=\"page-content-border\">"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">TIPO:</td>"
                        + "<td class=\"dato\">" + e.getCvcat() + "</td>"
                        + "</tr>"
                        + "</table>"
                        + "</div><hr/>";
            }

            str = str + benef + "</body>"
                    + "</html>";

        }
        this.contentListPdf = str;

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

        String outcome = "/secured/catalogos/categoriasTrabajadoresList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/categoriasTrabajadoresView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/categoriasTrabajadoresEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/categoriasTrabajadoresCreate.xhtml?faces-redirect=true";
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

    /**
     *
     * @param vce
     * @throws IOException
     */
    public void handleChange(AjaxBehaviorEvent vce) throws IOException {

        String value = "";
        String id = ((UIComponent) vce.getSource()).getId();
        switch (id) {
            case "cecat":
                value = (String) ((ValueHolder) vce.getSource()).getValue();
                if (selectedCateg01 != null) {
                    try {
                        selectedCateg01.setNecat(empre04Manager.getEmpre04(value).getNoemp());

                    } catch (Empre04NotFound ex) {
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                    }
                }

                break;

            default:
                break;
        }
        updateCateg01();
        go(1);
    }

}
