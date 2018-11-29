package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Meses07;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Meses07ManagerLocal;
import com.hrm.sirhapp.service.exception.Meses07NotFound;
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
public class Meses07Backing extends BaseBacking implements Serializable {

    @EJB
    private Meses07ManagerLocal meses07Manager;

    @Named
    @Produces
    @RequestScoped
    private Meses07 selectedMeses07;

    private List<Meses07> meses07List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListMeses07();
        this.infoMessageModule = "Modulo Meses";
    }

    /**
     *
     * @return
     */
    public List<Meses07> getMeses07List() {
        return meses07List;
    }

    /**
     *
     * @param meses07List
     */
    public void setMeses07List(List<Meses07> meses07List) {
        this.meses07List = meses07List;
    }

    /**
     *
     * @return
     */
    public Meses07 getSelectedMeses07() {
        return selectedMeses07;
    }

    /**
     *
     * @param selectedMeses07
     */
    public void setSelectedMeses07(Meses07 selectedMeses07) {
        this.selectedMeses07 = selectedMeses07;
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
    public Meses07 getMeses07Request(Integer idmes) {

        try {

            this.selectedMeses07 = meses07Manager.getMeses07(idmes);

        } catch (Meses07NotFound ex) {
            Logger.getLogger(Meses07Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Meses07Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedMeses07;
    }

    /**
     *
     */
    public void getListMeses07() {

        meses07List = meses07Manager.getListMeses07();

        if (!meses07List.isEmpty()) {
            infoMessage = meses07List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveMeses07List() {

        meses07List = meses07Manager.retrieveMeses07();

        if (meses07List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = meses07List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateMeses07() {
        try {

            

            selectedMeses07.setStmes(Constants.RECORD_ACTIVED);
            selectedMeses07.setFemes(new Date());
            selectedMeses07.setUsmes(FacesUtil.getUserName());

            meses07Manager.updateMeses07(selectedMeses07);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Meses07NotFound ex) {
            Logger.getLogger(Meses07Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteMeses07() {
        try {
            

            selectedMeses07.setStmes(Constants.RECORD_DELETED);
            selectedMeses07.setFemes(new Date());
            selectedMeses07.setUsmes(FacesUtil.getUserName());

            meses07Manager.deleteMeses07(selectedMeses07);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Meses07NotFound ex) {
            Logger.getLogger(Meses07Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedMeses07 != null) {
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
        if (meses07List != null) {
            if (meses07List.isEmpty() || meses07List.size() == 0) {
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

                for (Meses07 e : meses07List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">"+ e.getNomes()+"</td>"
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

        String outcome = "/secured/catalogos/mesesList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/mesesView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/mesesEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/mesesCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
