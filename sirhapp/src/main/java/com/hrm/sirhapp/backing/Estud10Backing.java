package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Estud10;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Estud10ManagerLocal;
import com.hrm.sirhapp.service.exception.Estud10NotFound;
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
public class Estud10Backing extends BaseBacking implements Serializable {

    @EJB
    private Estud10ManagerLocal estud10Manager;

    @Named
    @Produces
    @RequestScoped
    private Estud10 selectedEstud10;

    private List<Estud10> estud10List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListEstud10();
        this.infoMessageModule = "Modulo Niveles de Estudio";
    }

    /**
     *
     * @return
     */
    public List<Estud10> getEstud10List() {
        return estud10List;
    }

    /**
     *
     * @param estud10List
     */
    public void setEstud10List(List<Estud10> estud10List) {
        this.estud10List = estud10List;
    }

    /**
     *
     * @return
     */
    public Estud10 getSelectedEstud10() {
        return selectedEstud10;
    }

    /**
     *
     * @param selectedEstud10
     */
    public void setSelectedEstud10(Estud10 selectedEstud10) {
        this.selectedEstud10 = selectedEstud10;
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
     * @param idmes
     * @return
     */
    public Estud10 getEstud10Request(Integer idmes) {

        try {

            this.selectedEstud10 = estud10Manager.getEstud10(idmes);

        } catch (Estud10NotFound ex) {
            Logger.getLogger(Estud10Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Estud10Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedEstud10;
    }

    /**
     *
     */
    public void getListEstud10() {

        estud10List = estud10Manager.getListEstud10();

        if (!estud10List.isEmpty()) {
            infoMessage = estud10List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveEstud10List() {

        estud10List = estud10Manager.retrieveEstud10();

        if (estud10List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = estud10List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateEstud10() {
        try {

            

            selectedEstud10.setStest(Constants.RECORD_ACTIVED);
            selectedEstud10.setFeest(new Date());
            selectedEstud10.setUsest(FacesUtil.getUserName());

            estud10Manager.updateEstud10(selectedEstud10);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Estud10NotFound ex) {
            Logger.getLogger(Estud10Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteEstud10() {
        try {
            

            selectedEstud10.setStest(Constants.RECORD_DELETED);
            selectedEstud10.setFeest(new Date());
            selectedEstud10.setUsest(FacesUtil.getUserName());

            estud10Manager.deleteEstud10(selectedEstud10);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Estud10NotFound ex) {
            Logger.getLogger(Estud10Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedEstud10 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Causas Sin Documento</title>"
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
        if (estud10List != null) {
            if (estud10List.isEmpty() || estud10List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>LISTA DE MESES</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>LISTA DE MESES</h1>"
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
                        + "<title>LISTA DE MESES</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>LISTA DE MESES</h1>";

                for (Estud10 e : estud10List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">"+ e.getNiest()+"</td>"
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

        String outcome = "/secured/catalogos/nivelesEstudioList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/nivelesEstudioView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/nivelesEstudioEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/nivelesEstudioCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
