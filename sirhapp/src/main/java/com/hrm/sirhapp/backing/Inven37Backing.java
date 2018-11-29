package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Inven37;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Empre04ManagerLocal;
import com.hrm.sirhapp.service.Inven37ManagerLocal;
import com.hrm.sirhapp.service.Prove48ManagerLocal;
import com.hrm.sirhapp.service.exception.Empre04NotFound;
import com.hrm.sirhapp.service.exception.Inven37NotFound;
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
import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
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
public class Inven37Backing extends BaseBacking implements Serializable {

    @EJB
    private Inven37ManagerLocal inven37Manager;
    @EJB
    private Empre04ManagerLocal empre04Manager;
    @EJB
    private Prove48ManagerLocal prove48Manager;

    @Named
    @Produces
    @RequestScoped
    private Inven37 selectedInven37;

    private List<Inven37> inven37List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Inventarios";
    }

    /**
     *
     * @return
     */
    public List<Inven37> getInven37List() {
        return inven37List;
    }

    /**
     *
     * @param inven37List
     */
    public void setInven37List(List<Inven37> inven37List) {
        this.inven37List = inven37List;
    }

    /**
     *
     * @return
     */
    public Inven37 getSelectedInven37() {
        return selectedInven37;
    }

    /**
     *
     * @param selectedInven37
     */
    public void setSelectedInven37(Inven37 selectedInven37) {
        this.selectedInven37 = selectedInven37;
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
     * @param idinv
     * @return
     */
    public Inven37 getInven37Request(Integer idinv) {

        try {

            this.selectedInven37 = inven37Manager.getInven37(idinv);

        } catch (Inven37NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedInven37;
    }

    /**
     *
     * @param idinv
     */
    public void getListInven37(Integer idinv) {

        inven37List = inven37Manager.getListInven37(idinv);

        if (!inven37List.isEmpty()) {
            infoMessage = inven37List.size() + " registro(s)";
        }
    }

    /**
     *
     * @param tInven37
     */
    public void getListInven37T(Inven37 tInven37) {

        inven37List = inven37Manager.getListInven37(tInven37);

        if (!inven37List.isEmpty()) {
            infoMessage = inven37List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveInven37List() {

        inven37List = inven37Manager.retrieveInven37();

        if (inven37List.isEmpty()) {
            infoMessage = "No existen datos";
        } else {
            infoMessage = inven37List.size() + " registros";
        }

    }

    /**
     *
     */
    public void retrieveInven37EList() {

        inven37List = inven37Manager.retrieveInven37E();

        if (inven37List.isEmpty()) {
            infoMessage = "No existen datos";
        } else {
            infoMessage = inven37List.size() + " registros";
        }

    }

    /**
     *
     * @param inven37
     * @return
     */
    public Long countOutInven37(Inven37 inven37) {

        Long count;
        count = inven37Manager.countOutInven37(inven37);

        return count;
    }

    /**
     *
     * @return
     */
    public String updateInven37() {
        try {

            if (prove48Manager.retrieveProve48(selectedInven37.getRfinv()).getNcpro().equals("EL PROVEEDOR NO ESTA REGISTRADO")) {
                infoMessage = "Registre primero el Proveedor y vuelva a intentarlo.";
            } else {

                selectedInven37.setStinv(Constants.RECORD_ACTIVED);
                selectedInven37.setFeinv(new Date());
                selectedInven37.setUsinv(FacesUtil.getUserName());

                inven37Manager.updateInven37(selectedInven37);

                infoMessage = "El registro fue actualizado correctamente";

            }
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Inven37NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteInven37() {
        try {

            selectedInven37.setStinv(Constants.RECORD_DELETED);
            selectedInven37.setFeinv(new Date());
            selectedInven37.setUsinv(FacesUtil.getUserName());

            inven37Manager.deleteInven37(selectedInven37);

            infoMessage = "El registro fue eliminado correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Inven37NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
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

        if (selectedInven37 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>APRIV</title>"
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

        if (inven37List.isEmpty() || inven37List.size() == 0) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Inventario</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>Inventario</h1>"
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
                    + "<title>Inventario</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>Inventario: " + "</h1>";

            for (Inven37 e : inven37List) {
                benef = benef + "<div class=\"page-content-border\">"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">TIPO:</td>"
                        + "<td class=\"dato\">" + e.getTiinv() + "</td>"
                        + "</tr>"
                        + "</table>"
                        + "</div><hr/>";
            }

            str = str + benef + "</body>"
                    + "</html>";

        }
        this.contentListPdf = str;

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

        String outcome = "/secured/inventarios/inventarioList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/inventarios/inventarioView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/inventarios/inventarioEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/inventarios/inventarioCreate.xhtml?faces-redirect=true";
        }

        outcome = "/secured/inventarios/NOEXISTE?faces-redirect=true";

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

    /**
     *
     * @param vce
     */
    public void handleChange(AjaxBehaviorEvent vce) {

        String value = (String) ((ValueHolder) vce.getSource()).getValue();
        String id = ((UIComponent) vce.getSource()).getId();
        System.out.println("id:" + id + " value:" + value);
        switch (id) {
            case "ceinv":
                if (selectedInven37 != null) {
                    try {
                        selectedInven37.setNeinv(empre04Manager.getEmpre04(value).getNoemp());
                        System.out.println(empre04Manager.getEmpre04(value).getNoemp());
                        System.out.println(selectedInven37.getNeinv());
                    } catch (Empre04NotFound ex) {
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "rfinv":
                if (selectedInven37 != null) {
                    if (value != null && value.length() >= 10) {
                        selectedInven37.setNcinv(prove48Manager.retrieveProve48(value).getNcpro());
                    }
                }
                break;
            default:
                break;
        }
    }

}
