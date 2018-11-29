package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Niacc33;
import com.hrm.sirhapp.model.Usuar24;
import com.hrm.sirhapp.service.Niacc33ManagerLocal;
import com.hrm.sirhapp.service.Usuar24ManagerLocal;
import com.hrm.sirhapp.service.exception.Niacc33AlreadyExists;
import com.hrm.sirhapp.service.exception.Niacc33NotFound;
import com.hrm.sirhapp.service.exception.Usuar24NotFound;
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
public class Usuar24Backing extends BaseBacking implements Serializable {

    private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(Usuar24Backing.class);

    private static final long serialVersionUID = 1L;

    @EJB
    private Niacc33ManagerLocal niacc33Manager;

    private Niacc33 newNiacc33 = new Niacc33();

    @EJB
    private Usuar24ManagerLocal usuar24Manager;

    @Named
    @Produces
    @RequestScoped
    private Usuar24 selectedUsuar24;

    private List<Usuar24> usuar24List;

    private boolean active;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;

    @PostConstruct
    private void init() {
        this.active = true;
        getListUsuar24();
        if (selectedUsuar24 != null) {
            Empre04Backing empre04Backing = FacesUtil.getManagedBean("empre04Backing", Empre04Backing.class);
            empre04Backing.getEmpre04List(selectedUsuar24.getSeusu());
        }

        this.infoMessageModule = "Modulo Usuarios";
    }

    /**
     *
     * @return
     */
    public boolean isActive() {
        return active;
    }

    /**
     *
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
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
     * @param cvusu
     * @return
     */
    public Usuar24 getUsuar24Request(String cvusu) {

        try {

            this.selectedUsuar24 = usuar24Manager.getUsuar24(cvusu);

            if (usuar24Manager.alreadyExistsInnactive(cvusu)) {
                this.active = false;
            }

        } catch (Usuar24NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedUsuar24;
    }

    /**
     *
     * @return
     */
    public List<Usuar24> getUsuar24List() {
        return usuar24List;
    }

    /**
     *
     * @param usuar24List
     */
    public void setUsuar24List(List<Usuar24> usuar24List) {
        this.usuar24List = usuar24List;
    }

    /**
     *
     * @return
     */
    public Usuar24 getSelectedUsuar24() {
        return selectedUsuar24;
    }

    /**
     *
     * @param selectedUsuar24
     */
    public void setSelectedUsuar24(Usuar24 selectedUsuar24) {
        this.selectedUsuar24 = selectedUsuar24;
    }

    /**
     *
     */
    public void getListUsuar24() {

        usuar24List = usuar24Manager.getListUsuar24();

        if (usuar24List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = usuar24List.size() + " registro(s)";
        }
    }

    /**
     *
     */
    public void getListInnactiveUsuar24() {

        usuar24List = usuar24Manager.getListInnactiveUsuar24();

        if (usuar24List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = usuar24List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String acivateUsuar24() {
        try {

            selectedUsuar24.setStusu(Constants.RECORD_ACTIVED);
            selectedUsuar24.setFeusu(new Date());
            selectedUsuar24.setUsusu(FacesUtil.getUserName());

            usuar24Manager.updateUsuar24(selectedUsuar24);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ACT: Usuario Activado: " + selectedUsuar24.getCvusu());

        } catch (Usuar24NotFound ex) {
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
    public String updateUsuar24() {
        Usuar24 selectedUsuar24 = getSelectedUsuar24();

        try {

            selectedUsuar24.setStusu(Constants.RECORD_ACTIVED);
            selectedUsuar24.setFeusu(new Date());
            selectedUsuar24.setUsusu(FacesUtil.getUserName());

            selectedUsuar24 = usuar24Manager.updateUsuar24(selectedUsuar24);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

            return null;
        } catch (Usuar24NotFound ex) {
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
    public String deleteUsuar24() {
        Usuar24 selectedUsuar24 = getSelectedUsuar24();

        try {

            selectedUsuar24.setStusu(Constants.RECORD_DELETED);
            selectedUsuar24.setFeusu(new Date());
            selectedUsuar24.setUsusu(FacesUtil.getUserName());
            usuar24Manager.deleteUsuar24(selectedUsuar24);
            infoMessage = "El usuario fue borrado";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

            System.out.println(infoMessage + ": " + selectedUsuar24.getCvusu());
            return infoMessage;

        } catch (Usuar24NotFound ex) {
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

        if (selectedUsuar24 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>Usuarios</title>"
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
        if (usuar24List != null) {
            if (usuar24List.isEmpty() || usuar24List.size() == 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>Lista de Usuarios</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Usuarios</h1>"
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
                        + "<title>Lista de Usuarios</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>Lista de Usuarios</h1>";

                for (Usuar24 e : usuar24List) {
                    benef = benef + "<div class=\"page-content-border\">"
                            + "<table>"
                            + "<tr>"
                            + "<td class=\"titulo\">Nombre:</td>"
                            + "<td class=\"dato\">" + e.getNcusu() + "</td>"
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

        String outcome = "/secured/catalogos/usuariosList.xhtml?faces-redirect=true";

        if (action == 4) {
            outcome = "/secured/catalogos/usuariosListUnactivated.xhtml?faces-redirect=true";
        }
        if (action == 3) {
            outcome = "/secured/catalogos/usuariosView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/usuariosEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/usuariosCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

    /**
     *
     * @param vce
     * @throws IOException
     */
    public void handleChange(AjaxBehaviorEvent vce) throws IOException {

        String value = (String) ((ValueHolder) vce.getSource()).getValue();
        String id = ((UIComponent) vce.getSource()).getId();
        System.out.println("id:" + id + " value:" + value);
        switch (id) {
            case "neusu":

            case "seusu":

            case "emusu":

            case "deusu":
                updateUsuar24();
                go(1);
                break;
            case "nausu":
                newNiacc33 = new Niacc33();
                newNiacc33.setCvnia(selectedUsuar24);
                newNiacc33.setNania(value);
                newNiacc33.setStnia(Constants.RECORD_ACTIVED);
                newNiacc33.setFenia(new Date());
                newNiacc33.setUsnia(FacesUtil.getUserName());

                try {
                    niacc33Manager.deleteNiacc33Usuar24(selectedUsuar24.getNiacc33List());

                } catch (Niacc33NotFound ex) {
                    Logger.getLogger(Usuar24Backing.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    niacc33Manager.registerNiacc33(newNiacc33);
                } catch (Niacc33AlreadyExists ex) {
                    Logger.getLogger(Usuar24Backing.class.getName()).log(Level.SEVERE, null, ex);
                }

                updateUsuar24();
                go(1);
                break;
            default:
                break;
        }
    }

}
