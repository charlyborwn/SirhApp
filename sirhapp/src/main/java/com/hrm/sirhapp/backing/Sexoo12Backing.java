package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Sexoo12;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Sexoo12ManagerLocal;
import com.hrm.sirhapp.service.exception.Sexoo12NotFound;
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
public class Sexoo12Backing extends BaseBacking implements Serializable {

    @EJB
    private Sexoo12ManagerLocal sexoo12Manager;

    @Named
    @Produces
    @RequestScoped
    private Sexoo12 selectedSexoo12;

    private List<Sexoo12> sexoo12List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListSexoo12();
        this.infoMessageModule = "Modulo Catalogo Generos";
    }

    /**
     *
     * @return
     */
    public List<Sexoo12> getSexoo12List() {
        return sexoo12List;
    }

    /**
     *
     * @param sexoo12List
     */
    public void setSexoo12List(List<Sexoo12> sexoo12List) {
        this.sexoo12List = sexoo12List;
    }

    /**
     *
     * @return
     */
    public Sexoo12 getSelectedSexoo12() {
        return selectedSexoo12;
    }

    /**
     *
     * @param selectedSexoo12
     */
    public void setSelectedSexoo12(Sexoo12 selectedSexoo12) {
        this.selectedSexoo12 = selectedSexoo12;
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
     * @param idrel
     * @return
     */
    public Sexoo12 getSexoo12Request(Integer idrel) {

        try {

            this.selectedSexoo12 = sexoo12Manager.getSexoo12(idrel);

        } catch (Sexoo12NotFound ex) {
            Logger.getLogger(Sexoo12Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Sexoo12Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedSexoo12;
    }

    /**
     *
     */
    public void getListSexoo12() {

        sexoo12List = sexoo12Manager.getListSexoo12();

        if (!sexoo12List.isEmpty()) {
            infoMessage = sexoo12List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveSexoo12List() {

        sexoo12List = sexoo12Manager.retrieveSexoo12();

        if (sexoo12List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = sexoo12List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateSexoo12() {
        try {

            

            selectedSexoo12.setStsex(Constants.RECORD_ACTIVED);
            selectedSexoo12.setFesex(new Date());
            selectedSexoo12.setUssex(FacesUtil.getUserName());

            sexoo12Manager.updateSexoo12(selectedSexoo12);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Sexoo12NotFound ex) {
            Logger.getLogger(Sexoo12Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteSexoo12() {
        try {
            

            selectedSexoo12.setStsex(Constants.RECORD_DELETED);
            selectedSexoo12.setFesex(new Date());
            selectedSexoo12.setUssex(FacesUtil.getUserName());

            sexoo12Manager.deleteSexoo12(selectedSexoo12);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Sexoo12NotFound ex) {
            Logger.getLogger(Sexoo12Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedSexoo12 != null) {
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
        if (sexoo12List != null) {
            if (sexoo12List.isEmpty() || sexoo12List.size() == 0) {
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

                for (Sexoo12 e : sexoo12List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">"+ e.getNosex()+"</td>"
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

        String outcome = "/secured/catalogos/sexoList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/sexoView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/sexoEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/sexoCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
