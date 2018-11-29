package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Tdhab45;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Tdhab45ManagerLocal;
import com.hrm.sirhapp.service.exception.Tdhab45NotFound;
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
public class Tdhab45Backing extends BaseBacking implements Serializable {

    @EJB
    private Tdhab45ManagerLocal tdhab45Manager;

    @Named
    @Produces
    @RequestScoped
    private Tdhab45 selectedTdhab45;

    private List<Tdhab45> tdhab45List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
          getListTdhab45();
        this.infoMessageModule = "Modulo Tipos Domicilio";
    }

    /**
     *
     * @return
     */
    public List<Tdhab45> getTdhab45List() {
        return tdhab45List;
    }

    /**
     *
     * @param tdhab45List
     */
    public void setTdhab45List(List<Tdhab45> tdhab45List) {
        this.tdhab45List = tdhab45List;
    }

    /**
     *
     * @return
     */
    public Tdhab45 getSelectedTdhab45() {
        return selectedTdhab45;
    }

    /**
     *
     * @param selectedTdhab45
     */
    public void setSelectedTdhab45(Tdhab45 selectedTdhab45) {
        this.selectedTdhab45 = selectedTdhab45;
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
     * @param idhab
     * @return
     */
    public Tdhab45 getTdhab45Request(Integer idhab) {

        try {

            this.selectedTdhab45 = tdhab45Manager.getTdhab45(idhab);

        } catch (Tdhab45NotFound ex) {
            Logger.getLogger(Tdhab45Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Tdhab45Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedTdhab45;
    }

    /**
     *
     */
    public void getListTdhab45() {

        tdhab45List = tdhab45Manager.getListTdhab45();

        if (!tdhab45List.isEmpty()) {
            infoMessage = tdhab45List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveTdhab45List() {

        tdhab45List = tdhab45Manager.retrieveTdhab45();

        if (tdhab45List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = tdhab45List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateTdhab45() {
        try {

            

            selectedTdhab45.setSthab(Constants.RECORD_ACTIVED);
            selectedTdhab45.setFehab(new Date());
            selectedTdhab45.setUshab(FacesUtil.getUserName());

            tdhab45Manager.updateTdhab45(selectedTdhab45);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Tdhab45NotFound ex) {
            Logger.getLogger(Tdhab45Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteTdhab45() {
        try {
            

            selectedTdhab45.setSthab(Constants.RECORD_DELETED);
            selectedTdhab45.setFehab(new Date());
            selectedTdhab45.setUshab(FacesUtil.getUserName());

            tdhab45Manager.deleteTdhab45(selectedTdhab45);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Tdhab45NotFound ex) {
            Logger.getLogger(Tdhab45Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedTdhab45 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Tipos Domicilio</title>"
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
        if (tdhab45List != null) {
            if (tdhab45List.isEmpty() || tdhab45List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Tipos Domicilio</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Tipos Domicilio</h1>"
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
                        + "<title>Lista de Tipos Domicilio</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Tipos Domicilio</h1>";

                for (Tdhab45 e : tdhab45List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">"+ e.getDehab()+"</td>"
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

        String outcome = "/secured/catalogos/tiposDomiciliosList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/tiposDomiciliosView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/tiposDomiciliosEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/tiposDomiciliosCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
