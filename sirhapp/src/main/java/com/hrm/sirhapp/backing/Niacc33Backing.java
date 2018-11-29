package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Niacc33;
import com.hrm.sirhapp.service.Niacc33ManagerLocal;
import com.hrm.sirhapp.service.exception.Niacc33NotFound;
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
public class Niacc33Backing extends BaseBacking implements Serializable {

    @EJB
    private Niacc33ManagerLocal niacc33Manager;

    @Named
    @Produces
    @RequestScoped
    private Niacc33 selectedNiacc33;

    private List<Niacc33> niacc33List;
    private List<Niacc33> niacc33ListNania;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        getListNiacc33();
        getListNiacc33Nania();
        this.infoMessageModule = "Modulo Niveles de Acceso";
    }

    /**
     *
     * @return
     */
    public List<Niacc33> getNiacc33List() {
        return niacc33List;
    }

    /**
     *
     * @param niacc33List
     */
    public void setNiacc33List(List<Niacc33> niacc33List) {
        this.niacc33List = niacc33List;
    }

    /**
     *
     * @return
     */
    public Niacc33 getSelectedNiacc33() {
        return selectedNiacc33;
    }

    /**
     *
     * @param selectedNiacc33
     */
    public void setSelectedNiacc33(Niacc33 selectedNiacc33) {
        this.selectedNiacc33 = selectedNiacc33;
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
     * @param idnia
     * @return
     */
    public Niacc33 getNiacc33Request(Integer idnia) {

        try {

            this.selectedNiacc33 = niacc33Manager.getNiacc33(idnia);

        } catch (Niacc33NotFound ex) {
            Logger.getLogger(Niacc33Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Niacc33Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedNiacc33;
    }

    /**
     *
     * @param cpnia
     * @return
     */
    public String getNiacc33RequestRol(String cpnia) {

        try {

            return niacc33Manager.getNiacc33Rol(cpnia);

        } catch (Exception ex) {
            Logger.getLogger(Niacc33Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return "";
    }

    /**
     *
     */
    public void getListNiacc33Nav() {

        niacc33List = niacc33Manager.getListNiacc33();

        if (!niacc33List.isEmpty()) {
            infoMessage = niacc33List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void getListNiacc33Nania() {

        niacc33ListNania = niacc33Manager.getListNiacc33Nania();

        System.out.println(niacc33ListNania.size() + " registro(s)");

        if (!niacc33ListNania.isEmpty()) {
            for (Niacc33 niacc33 : niacc33ListNania) {
                System.out.println(niacc33.getNania());
                System.out.println(niacc33.getCpnia());
                System.out.println(niacc33.getNpnia());
            }
        }

        if (!niacc33List.isEmpty()) {
            infoMessage = niacc33ListNania.size() + " registro(s)";
        }
    }

    /**
     *
     * @return
     */
    public List<Niacc33> getNiacc33ListNania() {
        return niacc33ListNania;
    }

    /**
     *
     * @param niacc33ListNania
     */
    public void setNiacc33ListNania(List<Niacc33> niacc33ListNania) {
        this.niacc33ListNania = niacc33ListNania;
    }

    /**
     *
     */
    public void getListNiacc33() {

        niacc33List = niacc33Manager.getListNiacc33();

        if (!niacc33List.isEmpty()) {
            infoMessage = niacc33List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void retrieveNiacc33List() {

        niacc33List = niacc33Manager.retrieveNiacc33();

        if (niacc33List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = niacc33List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateNiacc33() {
        try {

            selectedNiacc33.setStnia(Constants.RECORD_ACTIVED);
            selectedNiacc33.setFenia(new Date());
            selectedNiacc33.setUsnia(FacesUtil.getUserName());

            niacc33Manager.updateNiacc33(selectedNiacc33);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Niacc33NotFound ex) {
            Logger.getLogger(Niacc33Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteNiacc33() {
        try {

            selectedNiacc33.setStnia(Constants.RECORD_DELETED);
            selectedNiacc33.setFenia(new Date());
            selectedNiacc33.setUsnia(FacesUtil.getUserName());

            niacc33Manager.deleteNiacc33(selectedNiacc33);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Niacc33NotFound ex) {
            Logger.getLogger(Niacc33Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedNiacc33 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Ubicaciones</title>"
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
        if (niacc33List != null) {
            if (niacc33List.isEmpty() || niacc33List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Niveles de Acceso</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Niveles de Acceso</h1>"
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
                        + "<title>Lista de Niveles de Acceso</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Niveles de Acceso</h1>";

                for (Niacc33 e : niacc33List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">" + e.getNania() + "</td>"
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

        String outcome = "/secured/catalogos/nivelesAccesoList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/nivelesAccesoView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/nivelesAccesoEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/nivelesAccesoCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
