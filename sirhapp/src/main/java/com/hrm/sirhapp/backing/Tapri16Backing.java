package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Tapri16;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Tapri16ManagerLocal;
import com.hrm.sirhapp.service.exception.Tapri16NotFound;
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
public class Tapri16Backing extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Tapri16ManagerLocal tapri16Manager;

    @Named
    @Produces
    @RequestScoped
    private Tapri16 selectedTapri16;

    private List<Tapri16> tapri16List;

    private List<String> tapri16TopList;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListTapri16();
        getTopListTapri16();
        this.infoMessageModule = "Modulo Tipos Apriv";
    }

    /**
     *
     * @return
     */
    public List<Tapri16> getTapri16List() {
        return tapri16List;
    }

    /**
     *
     * @param tapri16List
     */
    public void setTapri16List(List<Tapri16> tapri16List) {
        this.tapri16List = tapri16List;
    }

    /**
     *
     * @return
     */
    public Tapri16 getSelectedTapri16() {
        return selectedTapri16;
    }

    /**
     *
     * @param selectedTapri16
     */
    public void setSelectedTapri16(Tapri16 selectedTapri16) {
        this.selectedTapri16 = selectedTapri16;
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
     * @param idstc
     * @return
     */
    public Tapri16 getTapri16Request(Integer idstc) {

        try {

            this.selectedTapri16 = tapri16Manager.getTapri16(idstc);

        } catch (Tapri16NotFound ex) {
            Logger.getLogger(Tapri16Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Tapri16Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedTapri16;
    }

    /**
     *
     */
    public void getListTapri16() {

        tapri16List = tapri16Manager.getListTapri16();

        if (!tapri16List.isEmpty()) {
            infoMessage = tapri16List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void getTopListTapri16() {
        tapri16TopList = tapri16Manager.getTopListTapri16();
    }

    /**
     *
     * @return
     */
    public List<String> getTapri16TopList() {
        return tapri16TopList;
    }

    /**
     *
     * @param tapri16TopList
     */
    public void setTapri16TopList(List<String> tapri16TopList) {
        this.tapri16TopList = tapri16TopList;
    }
    
    /**
     *
     */
    public void retrieveTapri16List() {

        tapri16List = tapri16Manager.retrieveTapri16();

        if (tapri16List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = tapri16List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateTapri16() {
        try {

            selectedTapri16.setSttap(Constants.RECORD_ACTIVED);
            selectedTapri16.setFetap(new Date());
            selectedTapri16.setUstap(FacesUtil.getUserName());

            tapri16Manager.updateTapri16(selectedTapri16);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Tapri16NotFound ex) {
            Logger.getLogger(Tapri16Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteTapri16() {
        try {

            selectedTapri16.setSttap(Constants.RECORD_DELETED);
            selectedTapri16.setFetap(new Date());
            selectedTapri16.setUstap(FacesUtil.getUserName());

            tapri16Manager.deleteTapri16(selectedTapri16);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Tapri16NotFound ex) {
            Logger.getLogger(Tapri16Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedTapri16 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Tipos Apriv</title>"
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
        if (tapri16List != null) {
            if (tapri16List.isEmpty() || tapri16List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Tipos Apriv</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Tipos Apriv</h1>"
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
                        + "<title>Lista de Tipos Apriv</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Tipos Apriv</h1>";

                for (Tapri16 e : tapri16List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">" + e.getDetap() + "</td>"
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

        String outcome = "/secured/catalogos/tiposAprivList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/tiposAprivView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/tiposAprivEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/tiposAprivCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
