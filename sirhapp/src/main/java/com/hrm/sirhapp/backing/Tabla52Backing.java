package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Tabla52;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Tabla52ManagerLocal;
import com.hrm.sirhapp.service.exception.Tabla52NotFound;
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
public class Tabla52Backing extends BaseBacking implements Serializable {

    @EJB
    private Tabla52ManagerLocal tabla52Manager;

    @Named
    @Produces
    @RequestScoped
    private Tabla52 selectedTabla52;

    private List<Tabla52> tabla52List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Tablas del Sistema";
    }

    /**
     *
     * @return
     */
    public List<Tabla52> getTabla52List() {
        return tabla52List;
    }

    /**
     *
     * @param tabla52List
     */
    public void setTabla52List(List<Tabla52> tabla52List) {
        this.tabla52List = tabla52List;
    }

    /**
     *
     * @return
     */
    public Tabla52 getSelectedTabla52() {
        return selectedTabla52;
    }

    /**
     *
     * @param selectedTabla52
     */
    public void setSelectedTabla52(Tabla52 selectedTabla52) {
        this.selectedTabla52 = selectedTabla52;
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
    public Tabla52 getTabla52Request(Integer idubi) {

        try {

            this.selectedTabla52 = tabla52Manager.getTabla52(idubi);

        } catch (Tabla52NotFound ex) {
            Logger.getLogger(Tabla52Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Tabla52Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedTabla52;
    }

    /**
     *
     */
    public void getListTabla52() {

        tabla52List = tabla52Manager.getListTabla52();

        if (!tabla52List.isEmpty()) {
            infoMessage = tabla52List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveTabla52List() {

        tabla52List = tabla52Manager.retrieveTabla52();

        if (tabla52List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = tabla52List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateTabla52() {
        try {

            

            selectedTabla52.setSttab(Constants.RECORD_ACTIVED);
            selectedTabla52.setFetab(new Date());
            selectedTabla52.setUstab(FacesUtil.getUserName());

            tabla52Manager.updateTabla52(selectedTabla52);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Tabla52NotFound ex) {
            Logger.getLogger(Tabla52Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteTabla52() {
        try {
            

            selectedTabla52.setSttab(Constants.RECORD_DELETED);
            selectedTabla52.setFetab(new Date());
            selectedTabla52.setUstab(FacesUtil.getUserName());

            tabla52Manager.deleteTabla52(selectedTabla52);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Tabla52NotFound ex) {
            Logger.getLogger(Tabla52Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedTabla52 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Tablas del Sistema</title>"
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
        if (tabla52List != null) {
            if (tabla52List.isEmpty() || tabla52List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Tablas del Sistema</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Tablas del Sistema</h1>"
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
                        + "<title>Lista de Tablas del Sistema</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Tablas del Sistema</h1>";

                for (Tabla52 e : tabla52List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">"+ e.getNotab()+"</td>"
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

        String outcome = "/secured/catalogos/tablasSistemaList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/tablasSistemaView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/tablasSistemaEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/tablasSistemaCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
