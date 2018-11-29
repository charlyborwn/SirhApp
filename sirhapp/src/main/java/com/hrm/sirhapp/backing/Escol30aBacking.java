package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Escol30a;
import com.hrm.sirhapp.service.Escol30aManagerLocal;
import com.hrm.sirhapp.service.Espec05ManagerLocal;
import com.hrm.sirhapp.service.exception.Escol30aNotFound;
import com.hrm.sirhapp.util.FacesUtil;
import com.hrm.sirhapp.util.LanguagesUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@ViewScoped
public class Escol30aBacking extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Escol30aManagerLocal escol30aManager;

    @EJB
    private Espec05ManagerLocal espec05Manager;

    @Named
    @Produces
    @RequestScoped
    private Escol30a selectedEscol30a;

    private List<Escol30a> escol30aList;

    private int status = 1;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    private Integer escol30aTab;

    /**
     *
     * @return
     */
    public List<Escol30a> getEscol30aList() {
        return escol30aList;
    }

    /**
     *
     * @param escol30aList
     */
    public void setEscol30aRequestList(List<Escol30a> escol30aList) {
        this.escol30aList = escol30aList;
    }

    /**
     *
     * @return
     */
    public Escol30a getSelectedEscol30a() {
        return selectedEscol30a;
    }

    /**
     *
     * @param selectedEscol30a
     */
    public void setSelectedEscol30a(Escol30a selectedEscol30a) {
        this.selectedEscol30a = selectedEscol30a;
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
     * @param idescA
     * @return
     */
    public Escol30a getEscol30aRequest(Integer idescA) {

        try {

            this.selectedEscol30a = escol30aManager.getEscol30a(idescA);

        } catch (Escol30aNotFound ex) {
            Logger.getLogger(Escol30aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Escol30aBacking.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedEscol30a;
    }

    /**
     *
     * @param rfescA
     */
    public void getListEscol30a(String rfescA) {

        escol30aList = escol30aManager.getListEscol30a(rfescA);

        if (!escol30aList.isEmpty()) {
            infoMessage = escol30aList.size() + " registro(s) de escolaridad";
        }
    }

    /**
     *
     */
    public void retrieveEscol30aList() {

        escol30aList = escol30aManager.retrieveEscol30a();

        if (escol30aList.isEmpty()) {
            infoMessage = "No existen resgistros de escolaridad";
        } else {
            infoMessage = escol30aList.size() + " registro(s) de escolaridad";
        }

    }

    /**
     *
     * @return
     */
    public String updateEscol30a() {
        try {

            selectedEscol30a.setStescA(Constants.RECORD_ACTIVED);
            selectedEscol30a.setFeescA(new Date());
            selectedEscol30a.setUsescA(FacesUtil.getUserName());

            escol30aManager.updateEscol30a(selectedEscol30a);

            infoMessage = "Registro de escolaridad actualizado correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Escol30aNotFound ex) {
            Logger.getLogger(Escol30aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al actualizar el registro del Aspirante";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteEscol30a() {
        try {

            selectedEscol30a.setStescA(Constants.RECORD_DELETED);
            selectedEscol30a.setFeescA(new Date());
            selectedEscol30a.setUsescA(FacesUtil.getUserName());

            escol30aManager.deleteEscol30a(selectedEscol30a);

            infoMessage = "Registro de Escolaridad eliminado correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Escol30aNotFound ex) {
            Logger.getLogger(Escol30aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al eliminar el registro del Aspirante";
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

        if (selectedEscol30a != null) {
            if (selectedEscol30a.getRfescA().length() > 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>DATOS DE ESCOLARIDAD DEL ASPIRANTE</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>DATOS DE ESCOLARIDAD DEL ASPIRANTE</h1>"
                        + "<div class=\"page-content\">"
                        + "<table class=\"table-content\">"
                        + "<tr>"
                        + "<td width=\"160px\"><img width=\"160\"  src=\"http://sirhapp.dynu.net/sirhapp/files/" + this.selectedEscol30a.getPaescA() + "\" /></td>"
                        + "<td class=\"separador\"></td>"
                        + "<td>"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">TIPO DE DOCUMENTO:</td>"
                        + "<td class=\"dato\">" + this.selectedEscol30a.getTnescA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">OBSERVACIONES:</td>"
                        + "<td class=\"dato\">" + this.selectedEscol30a.getRfescA() + "</td>"
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
        String escol = "";

        if (escol30aList.isEmpty() || escol30aList.size() == 0) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>ESCOLARIDAD DEL ASPIRANTE</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>ESCOLARIDAD DEL ASPIRANTE</h1>"
                    + "<div class=\"page-content\">"
                    + "<table class=\"table-content\">"
                    + "<tr>"
                    + "<td>No existen datos de escolaridad del aspirante</td>"
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
                    + "<title>ESCOLARIDAD DEL ASPIRANTE</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>ESCOLARIDAD DEL ASPIRANTE: " + this.escol30aList.get(0).getRfescA() + "</h1>";

            for (Escol30a e : escol30aList) {
                escol = escol + "<div class=\"page-content-border\">"
                        + "<table class=\"table-content\">"
                        + "<tr>"
                        + "<td width=\"160px\"><img width=\"160\"  src=\"http://sirhapp.dynu.net/sirhapp/files/" + e.getPaescA() + "\" /></td>"
                        + "<td class=\"separador\"></td>"
                        + "<td>"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">TIPO:</td>"
                        + "<td class=\"dato\">" + e.getTnescA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">OBSERVACIONES:</td>"
                        + "<td class=\"dato\">" + e.getObescA() + "</td>"
                        + "</tr>"
                        + "</table></td>"
                        + "</tr>"
                        + "</table>"
                        + "</div><hr/>";
            }

            str = str + escol + "</body>"
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

        String outcome = "/secured/aspirantes/escolaridad.xhtml?faces-redirect=true";

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

    /**
     *
     * @param vce
     */
    public void handleChange(AjaxBehaviorEvent vce) {

        String value = (String) ((ValueHolder) vce.getSource()).getValue();
        String id = ((UIComponent) vce.getSource()).getId();
        System.out.println("id:" + id + " value:" + value);
        switch (id) {
            default:
                break;
        }
    }

    /**
     *
     * @param event
     */
    public void onTabChange(TabChangeEvent event) {
        TabView tabView = (TabView) event.getComponent();

        escol30aTab = tabView.getChildren().indexOf(event.getTab());
        System.out.println("tab actual escol:" + escol30aTab);

    }

    /**
     *
     * @param query
     * @return
     */
    public List<String> especialidadLaboral(String query) {

        if (selectedEscol30a.getEsescA() != null) {
            query = selectedEscol30a.getEsescA();
        }

        List<String> results = espec05Manager.getListEspec05Noesp(query);

        return results;
    }

    /**
     *
     * @return
     */
    public Integer getEscol30aTab() {
        return escol30aTab;
    }

    /**
     *
     * @param escol30aTab
     */
    public void setEscol30aTab(Integer escol30aTab) {
        this.escol30aTab = escol30aTab;
    }
    
    

}
