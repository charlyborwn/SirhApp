package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Depar03;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Depar03ManagerLocal;
import com.hrm.sirhapp.service.exception.Depar03NotFound;
import com.hrm.sirhapp.util.FacesUtil;
import com.hrm.sirhapp.util.LanguagesUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
public class Depar03Backing extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Depar03ManagerLocal depar03Manager;

    @Named
    @Produces
    @RequestScoped
    private Depar03 selectedDepar03;

    private List<Depar03> depar03List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListDepar03();
        this.infoMessageModule = "Modulo Departamentos";
    }

    /**
     *
     * @param sede
     * @param enterprise
     * @return
     */
    public List<Depar03> getDepar03List(String sede, String enterprise) {
        List<Depar03> result = depar03List;
        if (sede != null) {
            System.out.println(sede + "| sede gelist");
            result = result.stream()
                    .filter(depar03 -> sede.equals(depar03.getSedep()))
                    .collect(Collectors.toList());
        }

        if (enterprise != null) {
            System.out.println(enterprise + "| empresa gelist");
            result = result.stream()
                    .filter(depar03 -> enterprise.equals(depar03.getCedep()))
                    .collect(Collectors.toList());
        }

        return result;
    }

    /**
     *
     * @return
     */
    public List<Depar03> getDepar03List() {
        return depar03List;
    }

    /**
     *
     * @param depar03List
     */
    public void setDepar03List(List<Depar03> depar03List) {
        this.depar03List = depar03List;
    }

    /**
     *
     * @return
     */
    public Depar03 getSelectedDepar03() {
        return selectedDepar03;
    }

    /**
     *
     * @param selectedDepar03
     */
    public void setSelectedDepar03(Depar03 selectedDepar03) {
        this.selectedDepar03 = selectedDepar03;
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
     * @param iddep
     * @return
     */
    public Depar03 getDepar03Request(Integer iddep) {

        try {

            this.selectedDepar03 = depar03Manager.getDepar03(iddep);

        } catch (Depar03NotFound ex) {
            Logger.getLogger(Depar03Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Depar03Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedDepar03;
    }

    /**
     *
     * @param cedep
     * @param nudep
     * @return
     */
    public String getDepar03Request(String cedep, String nudep) {
        Depar03 depar03 = new Depar03();
        String out = "";
        try {

            depar03 = depar03Manager.getDepar03(cedep, nudep);

            out = depar03.getNudep() + " | " + depar03.getNodep();

            return out;

        } catch (Depar03NotFound ex) {

            try {
                depar03 = depar03Manager.getDepar03(nudep);
                out = depar03.getNudep() + " | " + depar03.getNodep();
                return out;
            } catch (Depar03NotFound ex1) {
                Logger.getLogger(Depar03Backing.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (Exception ex) {
            Logger.getLogger(Depar03Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return "";
    }

    /**
     *
     * @param cedep
     * @param sedep
     * @param nudep
     * @return
     */
    public String getDepar03Request(String cedep, String sedep, String nudep) {

        try {

            Depar03 depar03 = depar03Manager.getDepar03(cedep, sedep, nudep);
            String out = depar03.getNodep();

            return out;

        } catch (Depar03NotFound ex) {
            Logger.getLogger(Depar03Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Depar03Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return "";
    }

    /**
     *
     */
    public void getListDepar03() {

        depar03List = depar03Manager.getListDepar03();

        if (!depar03List.isEmpty()) {
            infoMessage = depar03List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveDepar03List() {

        depar03List = depar03Manager.retrieveDepar03();

        if (depar03List.isEmpty()) {
            infoMessage = "No existen avisos";
        } else {
            infoMessage = depar03List.size() + " avisos";
        }

    }

    /**
     *
     * @return
     */
    public String updateDepar03() {
        try {

            selectedDepar03.setStdep(Constants.RECORD_ACTIVED);
            selectedDepar03.setFedep(new Date());
            selectedDepar03.setUsdep(FacesUtil.getUserName());

            depar03Manager.updateDepar03(selectedDepar03);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Depar03NotFound ex) {
            Logger.getLogger(Depar03Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteDepar03() {
        try {

            selectedDepar03.setStdep(Constants.RECORD_DELETED);
            selectedDepar03.setFedep(new Date());
            selectedDepar03.setUsdep(FacesUtil.getUserName());

            depar03Manager.deleteDepar03(selectedDepar03);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Depar03NotFound ex) {
            Logger.getLogger(Depar03Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedDepar03 != null) {
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
        if (depar03List != null) {
            if (depar03List.isEmpty() || depar03List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>LISTA DE DEPARTAMENTOS</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>LISTA DE DEPARTAMENTOS</h1>"
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
                        + "<title>LISTA DE DEPARTAMENTOS</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>LISTA DE DEPARTAMENTOS</h1>";

                for (Depar03 e : depar03List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">" + e.getNodep() + "</td>"
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

        String outcome = "/secured/catalogos/departamentosList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/departamentosView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/departamentosEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/departamentosCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
