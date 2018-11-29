package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Relig11;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Relig11ManagerLocal;
import com.hrm.sirhapp.service.exception.Relig11NotFound;
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
public class Relig11Backing extends BaseBacking implements Serializable {

    @EJB
    private Relig11ManagerLocal relig11Manager;

    @Named
    @Produces
    @RequestScoped
    private Relig11 selectedRelig11;

    private List<Relig11> relig11List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListRelig11();
        this.infoMessageModule = "Modulo Religiones";
    }

    /**
     *
     * @return
     */
    public List<Relig11> getRelig11List() {
        return relig11List;
    }

    /**
     *
     * @param relig11List
     */
    public void setRelig11List(List<Relig11> relig11List) {
        this.relig11List = relig11List;
    }

    /**
     *
     * @return
     */
    public Relig11 getSelectedRelig11() {
        return selectedRelig11;
    }

    /**
     *
     * @param selectedRelig11
     */
    public void setSelectedRelig11(Relig11 selectedRelig11) {
        this.selectedRelig11 = selectedRelig11;
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
    public Relig11 getRelig11Request(Integer idrel) {

        try {

            this.selectedRelig11 = relig11Manager.getRelig11(idrel);

        } catch (Relig11NotFound ex) {
            Logger.getLogger(Relig11Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Relig11Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedRelig11;
    }

    /**
     *
     */
    public void getListRelig11() {

        relig11List = relig11Manager.getListRelig11();

        if (!relig11List.isEmpty()) {
            infoMessage = relig11List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveRelig11List() {

        relig11List = relig11Manager.retrieveRelig11();

        if (relig11List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = relig11List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateRelig11() {
        try {

            

            selectedRelig11.setStrel(Constants.RECORD_ACTIVED);
            selectedRelig11.setFerel(new Date());
            selectedRelig11.setUsrel(FacesUtil.getUserName());

            relig11Manager.updateRelig11(selectedRelig11);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Relig11NotFound ex) {
            Logger.getLogger(Relig11Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteRelig11() {
        try {
            

            selectedRelig11.setStrel(Constants.RECORD_DELETED);
            selectedRelig11.setFerel(new Date());
            selectedRelig11.setUsrel(FacesUtil.getUserName());

            relig11Manager.deleteRelig11(selectedRelig11);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Relig11NotFound ex) {
            Logger.getLogger(Relig11Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedRelig11 != null) {
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
        if (relig11List != null) {
            if (relig11List.isEmpty() || relig11List.size() == 0) {
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

                for (Relig11 e : relig11List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">"+ e.getNorel()+"</td>"
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

        String outcome = "/secured/catalogos/religionesList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/religionesView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/religionesEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/religionesCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
