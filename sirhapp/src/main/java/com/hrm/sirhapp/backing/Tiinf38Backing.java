package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Tiinf38;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Tiinf38ManagerLocal;
import com.hrm.sirhapp.service.exception.Tiinf38NotFound;
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
public class Tiinf38Backing extends BaseBacking implements Serializable {

    @EJB
    private Tiinf38ManagerLocal tiinf38Manager;

    @Named
    @Produces
    @RequestScoped
    private Tiinf38 selectedTiinf38;

    private List<Tiinf38> tiinf38List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Tipos de Inforamación";
    }

    /**
     *
     * @return
     */
    public List<Tiinf38> getTiinf38List() {
        return tiinf38List;
    }

    /**
     *
     * @param tiinf38List
     */
    public void setTiinf38List(List<Tiinf38> tiinf38List) {
        this.tiinf38List = tiinf38List;
    }

    /**
     *
     * @return
     */
    public Tiinf38 getSelectedTiinf38() {
        return selectedTiinf38;
    }

    /**
     *
     * @param selectedTiinf38
     */
    public void setSelectedTiinf38(Tiinf38 selectedTiinf38) {
        this.selectedTiinf38 = selectedTiinf38;
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
    public Tiinf38 getTiinf38Request(Integer idubi) {

        try {

            this.selectedTiinf38 = tiinf38Manager.getTiinf38(idubi);

        } catch (Tiinf38NotFound ex) {
            Logger.getLogger(Tiinf38Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Tiinf38Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedTiinf38;
    }

    /**
     *
     */
    public void getListTiinf38() {

        tiinf38List = tiinf38Manager.getListTiinf38();

        if (!tiinf38List.isEmpty()) {
            infoMessage = tiinf38List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveTiinf38List() {

        tiinf38List = tiinf38Manager.retrieveTiinf38();

        if (tiinf38List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = tiinf38List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateTiinf38() {
        try {

            

            selectedTiinf38.setSttin(Constants.RECORD_ACTIVED);
            selectedTiinf38.setFetin(new Date());
            selectedTiinf38.setUstin(FacesUtil.getUserName());

            tiinf38Manager.updateTiinf38(selectedTiinf38);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Tiinf38NotFound ex) {
            Logger.getLogger(Tiinf38Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteTiinf38() {
        try {
            

            selectedTiinf38.setSttin(Constants.RECORD_DELETED);
            selectedTiinf38.setFetin(new Date());
            selectedTiinf38.setUstin(FacesUtil.getUserName());

            tiinf38Manager.deleteTiinf38(selectedTiinf38);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Tiinf38NotFound ex) {
            Logger.getLogger(Tiinf38Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedTiinf38 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Tipos de Inforamación</title>"
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
        if (tiinf38List != null) {
            if (tiinf38List.isEmpty() || tiinf38List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Tipos de Inforamación</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Tipos de Inforamación</h1>"
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
                        + "<title>Lista de Tipos de Inforamación</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Tipos de Inforamación</h1>";

                for (Tiinf38 e : tiinf38List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">"+ e.getDetin()+"</td>"
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

        String outcome = "/secured/catalogos/tiposInformacionList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/tiposInformacionView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/tiposInformacionEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/tiposInformacionCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
