package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Catin44;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Catin44ManagerLocal;
import com.hrm.sirhapp.service.Empre04ManagerLocal;
import com.hrm.sirhapp.service.exception.Catin44NotFound;
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
public class Catin44Backing extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Catin44ManagerLocal catin44Manager;
    @EJB
    private Empre04ManagerLocal empre04Manager;

    @Named
    @Produces
    @RequestScoped
    private Catin44 selectedCatin44;

    private List<Catin44> catin44List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListCatin44();
        this.infoMessageModule = "Modulo Catalogo Articulos Inventarios";
    }

    /**
     *
     * @return
     */
    public List<Catin44> getCatin44List() {
        return catin44List;
    }

    /**
     *
     * @param catin44List
     */
    public void setCatin44List(List<Catin44> catin44List) {
        this.catin44List = catin44List;
    }

    /**
     *
     * @return
     */
    public Catin44 getSelectedCatin44() {
        return selectedCatin44;
    }

    /**
     *
     * @param selectedCatin44
     */
    public void setSelectedCatin44(Catin44 selectedCatin44) {
        this.selectedCatin44 = selectedCatin44;
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
     * @param idcin
     * @return
     */
    public Catin44 getCatin44Request(Integer idcin) {

        try {

            this.selectedCatin44 = catin44Manager.getCatin44(idcin);

        } catch (Catin44NotFound ex) {
            Logger.getLogger(Catin44Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Catin44Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedCatin44;
    }

    /**
     *
     */
    public void getListCatin44() {

        catin44List = catin44Manager.getListCatin44();

        if (!catin44List.isEmpty()) {
            infoMessage = catin44List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveCatin44List() {

        catin44List = catin44Manager.retrieveCatin44();

        if (catin44List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = catin44List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateCatin44() {
        try {

            selectedCatin44.setStcin(Constants.RECORD_ACTIVED);
            selectedCatin44.setFecin(new Date());
            selectedCatin44.setUscin(FacesUtil.getUserName());

            catin44Manager.updateCatin44(selectedCatin44);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Catin44NotFound ex) {
            Logger.getLogger(Catin44Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteCatin44() {
        try {

            selectedCatin44.setStcin(Constants.RECORD_DELETED);
            selectedCatin44.setFecin(new Date());
            selectedCatin44.setUscin(FacesUtil.getUserName());

            catin44Manager.deleteCatin44(selectedCatin44);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Catin44NotFound ex) {
            Logger.getLogger(Catin44Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedCatin44 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Catalogo Articulos Inventarios</title>"
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
        if (catin44List != null) {
            if (catin44List.isEmpty() || catin44List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Catalogo Articulos Inventarios</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Catalogo Articulos Inventarios</h1>"
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
                        + "<title>Lista de Catalogo Articulos Inventarios</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Catalogo Articulos Inventarios</h1>";

                for (Catin44 e : catin44List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">" + e.getNecin() + "</td>"
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

        String outcome = "/secured/catalogos/articulosInventariosList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/articulosInventariosView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/articulosInventariosEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/articulosInventariosCreate.xhtml?faces-redirect=true";
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

        String value = (String) ((ValueHolder) vce.getSource()).getValue();
        String id = ((UIComponent) vce.getSource()).getId();
        System.out.println("id:" + id + " value:" + value);
        switch (id) {
            case "cecin":
                if (selectedCatin44 != null) {
                    try {
                        selectedCatin44.setNecin(empre04Manager.getEmpre04(value).getNoemp());
                        updateCatin44();
                        go(1);
                        break;
                    } catch (Empre04NotFound ex) {
                        Logger.getLogger(Catin44Backing.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            default:
                break;
        }
    }

}
