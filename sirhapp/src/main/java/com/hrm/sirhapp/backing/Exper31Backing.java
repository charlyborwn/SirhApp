package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Exper31;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Exper31ManagerLocal;
import com.hrm.sirhapp.service.exception.Exper31NotFound;
import com.hrm.sirhapp.util.FacesUtil;
import com.hrm.sirhapp.util.LanguagesUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Exper31Backing extends BaseBacking implements Serializable {

    @EJB
    private Exper31ManagerLocal exper31Manager;

    @Named
    @Produces
    @RequestScoped
    private Exper31 selectedExper31;

    private List<Exper31> exper31List;

    private int status = 1;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    /**
     *
     * @return
     */
    public List<Exper31> getExper31List() {
        return exper31List;
    }

    /**
     *
     * @param exper31List
     */
    public void setExper31RequestList(List<Exper31> exper31List) {
        this.exper31List = exper31List;
    }

    /**
     *
     * @return
     */
    public Exper31 getSelectedExper31() {
        return selectedExper31;
    }

    /**
     *
     * @param selectedExper31
     */
    public void setSelectedExper31(Exper31 selectedExper31) {
        this.selectedExper31 = selectedExper31;
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
     * @param idbenA
     * @return
     */
    public Exper31 getExper31Request(Integer idbenA) {

        try {

            this.selectedExper31 = exper31Manager.getExper31(idbenA);

        } catch (Exper31NotFound ex) {
            Logger.getLogger(Exper31Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Exper31Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedExper31;
    }

    /**
     *
     * @param ntexp
     */
    public void getListExper31(Integer ntexp) {

        exper31List = exper31Manager.getListExper31(ntexp);

        if (exper31List.isEmpty()) {
            infoMessage = "No existen registros!";
        } else {
            infoMessage = exper31List.size() + " regitros";
        }
    }

    /**
     *
     */
    public void retrieveExper31List() {

        exper31List = exper31Manager.retrieveExper31();

        if (exper31List.isEmpty()) {
            infoMessage = "No existen registros!";
        } else {
            infoMessage = exper31List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateExper31() {
        try {

            selectedExper31.setStexp(Constants.RECORD_ACTIVED);
            selectedExper31.setFeexp(new Date());
            selectedExper31.setUsexp(FacesUtil.getUserName());

            exper31Manager.updateExper31(selectedExper31);

            infoMessage = "El registro se actualizó correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Exper31NotFound ex) {
            Logger.getLogger(Exper31Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al actualizar los datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        }
        return null;

    }

    /**
     *
     * @return
     */
    public String deleteExper31() {
        try {
            

            selectedExper31.setStexp(Constants.RECORD_DELETED);
            selectedExper31.setFeexp(new Date());
            selectedExper31.setUsexp(FacesUtil.getUserName());

            exper31Manager.deleteExper31(selectedExper31);

            infoMessage = "Experiencia eliminada correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Exper31NotFound ex) {
            Logger.getLogger(Exper31Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al eliminar los datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        }
        return null;

    }

    /**
     *
     * @return
     */
    public String getContentPdf() {
        String str = null;

        if (selectedExper31 != null) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>BENEFICIARIO DEL TRABAJADOR</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>BENEFICIARIO DEL TRABAJADOR:" + this.selectedExper31.getNtexp()+ "</h1>"
                        + "<div class=\"page-content\">"
                        + "<table class=\"table-content\">"
                        + "<tr>"
                        + "<td class=\"separador\"></td>"
                        + "<td>"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">TIPO:</td>"
                        + "<td class=\"dato\">" + this.selectedExper31.getTiexp() + "</td>"
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

        if (exper31List.isEmpty() || exper31List.size() == 0) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>LISTA DE BENEFICIARIOS DEL TRABAJADOR</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>LISTA DE BENEFICIARIOS DEL TRABAJADOR</h1>"
                    + "<div class=\"page-content\">"
                    + "<table class=\"table-content\">"
                    + "<tr>"
                    + "<td>No existen experiencias</td>"
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
                    + "<title>LISTA DE BENEFICIARIOS DEL TRABAJADOR</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>LISTA DE BENEFICIARIOS DEL TRABAJADOR: " + this.exper31List.get(0).getNtexp()+ "</h1>";

            for (Exper31 e : exper31List) {
                benef = benef + "<div class=\"page-content-border\">"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">TIPO:</td>"
                        + "<td class=\"dato\">" + e.getTiexp()+ "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">NOMBRE COMPLETO:</td>"
                        + "<td class=\"dato\">" + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">OBSERVACIONES:</td>"
                        + "<td class=\"dato\">" + e.getObexp()+ "</td>"
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

        String outcome = "/secured/trabajadores/experiencia.xhtml?faces-redirect=true";

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

}
