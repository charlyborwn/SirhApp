package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Moned46;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Moned46ManagerLocal;
import com.hrm.sirhapp.service.exception.Moned46NotFound;
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
public class Moned46Backing extends BaseBacking implements Serializable {

    @EJB
    private Moned46ManagerLocal moned46Manager;

    @Named
    @Produces
    @RequestScoped
    private Moned46 selectedMoned46;

    private List<Moned46> moned46List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListMoned46();
        this.infoMessageModule = "Modulo Tipos Monedas";
    }

    /**
     *
     * @return
     */
    public List<Moned46> getMoned46List() {
        return moned46List;
    }

    /**
     *
     * @param moned46List
     */
    public void setMoned46List(List<Moned46> moned46List) {
        this.moned46List = moned46List;
    }

    /**
     *
     * @return
     */
    public Moned46 getSelectedMoned46() {
        return selectedMoned46;
    }

    /**
     *
     * @param selectedMoned46
     */
    public void setSelectedMoned46(Moned46 selectedMoned46) {
        this.selectedMoned46 = selectedMoned46;
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
     * @param idmon
     * @return
     */
    public Moned46 getMoned46Request(Integer idmon) {

        try {

            this.selectedMoned46 = moned46Manager.getMoned46(idmon);

        } catch (Moned46NotFound ex) {
            Logger.getLogger(Moned46Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Moned46Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedMoned46;
    }

    /**
     *
     */
    public void getListMoned46() {

        moned46List = moned46Manager.getListMoned46();

        if (!moned46List.isEmpty()) {
            infoMessage = moned46List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveMoned46List() {

        moned46List = moned46Manager.retrieveMoned46();

        if (moned46List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = moned46List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateMoned46() {
        try {

            

            selectedMoned46.setStmon(Constants.RECORD_ACTIVED);
            selectedMoned46.setFemon(new Date());
            selectedMoned46.setUsmon(FacesUtil.getUserName());

            moned46Manager.updateMoned46(selectedMoned46);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Moned46NotFound ex) {
            Logger.getLogger(Moned46Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteMoned46() {
        try {
            

            selectedMoned46.setStmon(Constants.RECORD_DELETED);
            selectedMoned46.setFemon(new Date());
            selectedMoned46.setUsmon(FacesUtil.getUserName());

            moned46Manager.deleteMoned46(selectedMoned46);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Moned46NotFound ex) {
            Logger.getLogger(Moned46Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedMoned46 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Tipos Monedas</title>"
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
        if (moned46List != null) {
            if (moned46List.isEmpty() || moned46List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Tipos Monedas</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Tipos Monedas</h1>"
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
                        + "<title>Lista de Tipos Monedas</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Tipos Monedas</h1>";

                for (Moned46 e : moned46List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">"+ e.getNcmon()+"</td>"
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

        String outcome = "/secured/catalogos/tiposMonedasList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/tiposMonedasView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/tiposMonedasEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/tiposMonedasCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
