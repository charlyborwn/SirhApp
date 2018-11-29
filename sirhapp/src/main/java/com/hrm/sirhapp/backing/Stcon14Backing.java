package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Stcon14;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Stcon14ManagerLocal;
import com.hrm.sirhapp.service.exception.Stcon14NotFound;
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
public class Stcon14Backing extends BaseBacking implements Serializable {

    @EJB
    private Stcon14ManagerLocal stcon14Manager;

    @Named
    @Produces
    @RequestScoped
    private Stcon14 selectedStcon14;

    private List<Stcon14> stcon14List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListStcon14();
        this.infoMessageModule = "Modulo Status Contrato";
    }

    /**
     *
     * @return
     */
    public List<Stcon14> getStcon14List() {
        return stcon14List;
    }

    /**
     *
     * @param stcon14List
     */
    public void setStcon14List(List<Stcon14> stcon14List) {
        this.stcon14List = stcon14List;
    }

    /**
     *
     * @return
     */
    public Stcon14 getSelectedStcon14() {
        return selectedStcon14;
    }

    /**
     *
     * @param selectedStcon14
     */
    public void setSelectedStcon14(Stcon14 selectedStcon14) {
        this.selectedStcon14 = selectedStcon14;
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
    public Stcon14 getStcon14Request(Integer idstc) {

        try {

            this.selectedStcon14 = stcon14Manager.getStcon14(idstc);

        } catch (Stcon14NotFound ex) {
            Logger.getLogger(Stcon14Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Stcon14Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedStcon14;
    }

    /**
     *
     */
    public void getListStcon14() {

        stcon14List = stcon14Manager.getListStcon14();

        if (!stcon14List.isEmpty()) {
            infoMessage = stcon14List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveStcon14List() {

        stcon14List = stcon14Manager.retrieveStcon14();

        if (stcon14List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = stcon14List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateStcon14() {
        try {

            

            selectedStcon14.setStstc(Constants.RECORD_ACTIVED);
            selectedStcon14.setFestc(new Date());
            selectedStcon14.setUsstc(FacesUtil.getUserName());

            stcon14Manager.updateStcon14(selectedStcon14);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Stcon14NotFound ex) {
            Logger.getLogger(Stcon14Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteStcon14() {
        try {
            

            selectedStcon14.setStstc(Constants.RECORD_DELETED);
            selectedStcon14.setFestc(new Date());
            selectedStcon14.setUsstc(FacesUtil.getUserName());

            stcon14Manager.deleteStcon14(selectedStcon14);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Stcon14NotFound ex) {
            Logger.getLogger(Stcon14Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedStcon14 != null) {
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
        if (stcon14List != null) {
            if (stcon14List.isEmpty() || stcon14List.size() == 0) {
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

                for (Stcon14 e : stcon14List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">"+ e.getDestc()+"</td>"
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

        String outcome = "/secured/catalogos/statusContratoList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/statusContratoView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/statusContratoEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/statusContratoCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
