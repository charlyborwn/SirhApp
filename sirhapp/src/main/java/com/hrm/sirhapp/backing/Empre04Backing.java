package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Empre04;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Empre04ManagerLocal;
import com.hrm.sirhapp.service.exception.Empre04NotFound;
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
public class Empre04Backing extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Empre04ManagerLocal empre04Manager;

    @Named
    @Produces
    @RequestScoped
    private Empre04 selectedEmpre04;

    private List<Empre04> empre04List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListEmpre04();
        this.infoMessageModule = "Modulo Empresas";
    }

    /**
     *
     * @param sede
     * @return
     */
    public List<Empre04> getEmpre04List(String sede) {

        List<Empre04> result = empre04List;
        if (sede != null) {
            result = empre04List.stream()
                    .filter(empre04 -> sede.equals(empre04.getSeemp()))
                    .collect(Collectors.toList());
        }
        return result;
    }

    /**
     *
     * @return
     */
    public List<Empre04> getEmpre04List() {
        return empre04List;
    }

    /**
     *
     * @param empre04List
     */
    public void setEmpre04List(List<Empre04> empre04List) {
        this.empre04List = empre04List;
    }

    /**
     *
     * @return
     */
    public Empre04 getSelectedEmpre04() {
        return selectedEmpre04;
    }

    /**
     *
     * @param selectedEmpre04
     */
    public void setSelectedEmpre04(Empre04 selectedEmpre04) {
        this.selectedEmpre04 = selectedEmpre04;
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
     * @param idemp
     * @return
     */
    public Empre04 getEmpre04Request(Integer idemp) {

        try {

            this.selectedEmpre04 = empre04Manager.getEmpre04(idemp);

        } catch (Empre04NotFound ex) {
            Logger.getLogger(Empre04Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Empre04Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedEmpre04;
    }

    /**
     *
     * @param cvemp
     * @return
     */
    public String getEmpre04RequestString(String cvemp) {

        try {
            Empre04 empre04 = empre04Manager.getEmpre04(cvemp);

            String out = empre04.getCvemp() + " | " + empre04.getNoemp();

            return out;
        } catch (Empre04NotFound ex) {
            Logger.getLogger(Empre04Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Empre04Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }
        return "";
    }

    /**
     *
     * @param cvemp
     * @return
     */
    public String getEmpre04RequestName(String cvemp) {

        try {
            Empre04 empre04 = empre04Manager.getEmpre04(cvemp);

            String out = empre04.getNoemp();

            return out;
        } catch (Empre04NotFound ex) {
            Logger.getLogger(Empre04Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Empre04Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }
        return "";
    }

    /**
     *
     * @return
     */
    public List<Empre04> getListEmpre04All() {

        List<Empre04> empre04ListAll = empre04Manager.getListEmpre04All();

        return empre04ListAll;
    }

    /**
     *
     */
    public void getListEmpre04() {

        empre04List = empre04Manager.getListEmpre04();

        if (!empre04List.isEmpty()) {
            infoMessage = empre04List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveEmpre04List() {

        empre04List = empre04Manager.retrieveEmpre04();

        if (empre04List.isEmpty()) {
            infoMessage = "No existen avisos";
        } else {
            infoMessage = empre04List.size() + " avisos";
        }

    }

    /**
     *
     * @return
     */
    public String updateEmpre04() {
        try {

            selectedEmpre04.setStemp(Constants.RECORD_ACTIVED);
            selectedEmpre04.setFeemp(new Date());
            selectedEmpre04.setUsemp(FacesUtil.getUserName());

            empre04Manager.updateEmpre04(selectedEmpre04);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Empre04NotFound ex) {
            Logger.getLogger(Empre04Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteEmpre04() {
        try {

            selectedEmpre04.setStemp(Constants.RECORD_DELETED);
            selectedEmpre04.setFeemp(new Date());
            selectedEmpre04.setUsemp(FacesUtil.getUserName());

            empre04Manager.deleteEmpre04(selectedEmpre04);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Empre04NotFound ex) {
            Logger.getLogger(Empre04Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedEmpre04 != null) {
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
        if (empre04List != null) {
            if (empre04List.isEmpty() || empre04List.size() == 0) {
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

                for (Empre04 e : empre04List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">" + e.getNeemp() + "</td>"
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

        String outcome = "/secured/catalogos/empresasList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/empresasView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/empresasEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/empresasCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
