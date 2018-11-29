package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Docum29a;
import com.hrm.sirhapp.service.Docum29aManagerLocal;
import com.hrm.sirhapp.service.Tidoc18ManagerLocal;
import com.hrm.sirhapp.service.exception.Docum29aNotFound;
import com.hrm.sirhapp.service.exception.Tidoc18NotFound;
import com.hrm.sirhapp.util.FacesUtil;
import com.hrm.sirhapp.util.LanguagesUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
public class Docum29aBacking extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Docum29aManagerLocal docum29aManager;

    private Docum29a selectedDocum29a;

    @EJB
    private Tidoc18ManagerLocal tidoc18Manager;

    private List<Docum29a> docum29aList;

    private int status = 1;
    private String infoMessage;
    private String infoMessageModule;
    private String contentPdf;
    private String contentListPdf;
    private String path;
    private String w1docA;
    private String tddocA;

    /**
     *
     * @return
     */
    public Tidoc18ManagerLocal getTidoc18Manager() {
        return tidoc18Manager;
    }

    /**
     *
     * @param tidoc18Manager
     */
    public void setTidoc18Manager(Tidoc18ManagerLocal tidoc18Manager) {
        this.tidoc18Manager = tidoc18Manager;
    }

    /**
     *
     * @return
     */
    public String getPath() {
        return path;
    }

    /**
     *
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     *
     * @return
     */
    public String getW1docA() {
        return w1docA;
    }

    /**
     *
     * @param w1docA
     */
    public void setW1docA(String w1docA) {
        this.w1docA = w1docA;
    }

    /**
     *
     * @return
     */
    public String getTddocA() {
        return tddocA;
    }

    /**
     *
     * @param tddocA
     */
    public void setTddocA(String tddocA) {
        this.tddocA = tddocA;
    }

    /**
     *
     * @return
     */
    public Docum29aManagerLocal getDocum29aManager() {
        return docum29aManager;
    }

    /**
     *
     * @param docum29aManager
     */
    public void setDocum29aManager(Docum29aManagerLocal docum29aManager) {
        this.docum29aManager = docum29aManager;
    }

    /**
     *
     * @return
     */
    public List<Docum29a> getDocum29aList() {
        return docum29aList;
    }

    /**
     *
     * @param docum29aList
     */
    public void setDocum29aRequestList(List<Docum29a> docum29aList) {
        this.docum29aList = docum29aList;
    }

    /**
     *
     * @return
     */
    public Docum29a getSelectedDocum29a() {
        return selectedDocum29a;
    }

    /**
     *
     * @param selectedDocum29a
     */
    public void setSelectedDocum29a(Docum29a selectedDocum29a) {
        this.selectedDocum29a = selectedDocum29a;
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
     * @param docum29a
     */
    public void prepareSelectDocum29a(Docum29a docum29a) {
        selectedDocum29a = docum29a;
        System.out.println(selectedDocum29a.getRfdocA());
    }

    /**
     *
     * @param iddocA
     * @return
     */
    public Docum29a getDocum29aRequest(Integer iddocA) {

        try {

            this.selectedDocum29a = docum29aManager.getDocum29a(iddocA);

        } catch (Docum29aNotFound ex) {
            Logger.getLogger(Docum29aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Docum29aBacking.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedDocum29a;
    }

    /**
     *
     * @param rfdocA
     */
    public void getListDocum29a(String rfdocA) {

        docum29aList = docum29aManager.getListDocum29a(rfdocA);

        if (!docum29aList.isEmpty()) {
            infoMessage = docum29aList.size() + " Documentos";
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));
        }
    }

    /**
     *
     */
    public void retrieveDocum29aList() {

        docum29aList = docum29aManager.retrieveDocum29a();

        if (docum29aList.isEmpty()) {
            infoMessage = "No existen documentos";
        } else {
            infoMessage = docum29aList.size() + " Documentos";
        }

    }

    /**
     *
     * @return
     */
    public String updateDocum29a() {
        try {

            if (tddocA != null) {
                selectedDocum29a.setTddocA(tddocA);
            }
            if (w1docA != null) {
                selectedDocum29a.setW1docA(w1docA);
            }
            if (path != null) {
                selectedDocum29a.setPadocA(path);
            }

            selectedDocum29a.setStdocA(Constants.RECORD_ACTIVED);
            selectedDocum29a.setFedocA(new Date());
            selectedDocum29a.setUsdocA(FacesUtil.getUserName());

            docum29aManager.updateDocum29a(selectedDocum29a);

            infoMessage = "El documento se actualizó correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Docum29aNotFound ex) {
            Logger.getLogger(Docum29aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al actualizar el Documento";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        }
        return null;
    }

    /**
     *
     * @return
     */
    public String deleteDocum29a() {
        System.out.println("Backing Este es el id del documento que voy a borrar: " + selectedDocum29a.getIddocA());
        try {

            selectedDocum29a.setStdocA(Constants.RECORD_DELETED);
            selectedDocum29a.setFedocA(new Date());
            selectedDocum29a.setUsdocA(FacesUtil.getUserName());

            docum29aManager.deleteDocum29a(selectedDocum29a);

            infoMessage = "Documento eliminado";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Docum29aNotFound ex) {
            Logger.getLogger(Docum29aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al eliminar el Documento";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        }

        return null;
    }

    /**
     *
     * @param docum29a
     * @return
     */
    public String deleteDocum29a(Docum29a docum29a) {
        System.out.println("Backing Este es el id del documento que voy a borrar: " + docum29a.getIddocA());
        try {

            docum29a.setStdocA(Constants.RECORD_DELETED);
            docum29a.setFedocA(new Date());
            docum29a.setUsdocA(FacesUtil.getUserName());

            docum29aManager.deleteDocum29a(docum29a);

            infoMessage = "Documento eliminado";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Docum29aNotFound ex) {
            Logger.getLogger(Docum29aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al eliminar el Documento";
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

        if (selectedDocum29a != null) {
            if (selectedDocum29a.getRfdocA().length() > 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>INFORMACION DOCUMENTAL DEL ASPIRANTE</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>DOCUMENTO</h1>"
                        + "<div class=\"page-content\">"
                        + "<table class=\"table-content\">"
                        + "<tr>"
                        + "<td width=\"160px\"><img width=\"160\"  src=\"http://sirhapp.dynu.net/sirhapp/files/" + this.selectedDocum29a.getPadocA() + "\" /></td>"
                        + "<td class=\"separador\"></td>"
                        + "<td>"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">RFC:</td>"
                        + "<td class=\"dato\">" + this.selectedDocum29a.getRfdocA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">CURP:</td>"
                        + "<td class=\"dato\">" + this.selectedDocum29a.getRfdocA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">NOMBRE:</td>"
                        + "<td class=\"dato\">" + this.selectedDocum29a.getRfdocA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">NACIMIENTO FECHA:</td>"
                        + "<td class=\"dato\">" + this.selectedDocum29a.getRfdocA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">EDAD:</td>"
                        + "<td class=\"dato\">" + this.selectedDocum29a.getRfdocA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">SEXO:</td>"
                        + "<td class=\"dato\">" + this.selectedDocum29a.getRfdocA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">NACIONALIDAD:</td>"
                        + "<td class=\"dato\">" + this.selectedDocum29a.getRfdocA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">OBSERVACIONES:</td>"
                        + "<td class=\"dato\">" + this.selectedDocum29a.getRfdocA() + "</td>"
                        + "</tr>"
                        + "</table></td>"
                        + "</tr>"
                        + "</table>"
                        + "</div>"
                        + "</body>"
                        + "</html>";
                this.contentPdf = str;
            }
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

        if (selectedDocum29a != null) {
            if (selectedDocum29a.getRfdocA().length() > 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>LISTA DE DOCUMENTOS DEL ASPIRANTE</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>DATOS DEL DOCUMENTO 1</h1>"
                        + "<div class=\"page-content\">"
                        + "<table class=\"table-content\">"
                        + "<tr>"
                        + "<td width=\"160px\"><img width=\"160\"  src=\"http://sirhapp.dynu.net/sirhapp/files/" + this.selectedDocum29a.getRfdocA() + "\" /></td>"
                        + "<td class=\"separador\"></td>"
                        + "<td>"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">RFC:</td>"
                        + "<td class=\"dato\">" + this.selectedDocum29a.getRfdocA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">CURP:</td>"
                        + "<td class=\"dato\">" + this.selectedDocum29a.getRfdocA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">NOMBRE:</td>"
                        + "<td class=\"dato\">" + this.selectedDocum29a.getRfdocA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">NACIMIENTO FECHA:</td>"
                        + "<td class=\"dato\">" + this.selectedDocum29a.getRfdocA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">EDAD:</td>"
                        + "<td class=\"dato\">" + this.selectedDocum29a.getRfdocA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">SEXO:</td>"
                        + "<td class=\"dato\">" + this.selectedDocum29a.getRfdocA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">NACIONALIDAD:</td>"
                        + "<td class=\"dato\">" + this.selectedDocum29a.getRfdocA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">OBSERVACIONES:</td>"
                        + "<td class=\"dato\">" + this.selectedDocum29a.getRfdocA() + "</td>"
                        + "</tr>"
                        + "</table></td>"
                        + "</tr>"
                        + "</table>"
                        + "</div>"
                        + "</body>"
                        + "</html>";
                this.contentListPdf = str;
            }
        }

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

        String outcome = "/secured/aspirantes/documentacionList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/aspirantes/documentacionView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/aspirantes/documentacionEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/aspirantes/documentacionCreate.xhtml?faces-redirect=true";
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

    /**
     *
     * @param event
     */
    public void prepareFileUpload(AjaxBehaviorEvent event) {

        String value = (String) ((ValueHolder) event.getSource()).getValue();
        String id = ((UIComponent) event.getSource()).getId();
        tddocA = value;

        try {
            if (selectedDocum29a != null) {

                w1docA = tidoc18Manager.getTidoc18(value);
            }

        } catch (Tidoc18NotFound ex) {
            Logger.getLogger(Traba36aWizardBacking.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(value); // Look, (new) value is already set.
    }

    /**
     *
     * @return
     */
    public String updateDocum29aFoto() {

        if (selectedDocum29a != null) {
            selectedDocum29a.setPadocA(null);
        }
        FacesUtil.removeManagedBeanInSession("fileUploadBean");
        String oldInfoMessageModule = infoMessageModule;
        infoMessageModule = "Documento";
        infoMessage = "Se borro el Documento";
        FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
        infoMessageModule = oldInfoMessageModule;

        return null;
    }

}
