package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Docum29;
import com.hrm.sirhapp.service.Docum29ManagerLocal;
import com.hrm.sirhapp.service.exception.Docum29NotFound;
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
public class Docum29Backing extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Docum29ManagerLocal docum29Manager;

    @Named
    @Produces
    @RequestScoped
    private Docum29 selectedDocum29;

    private List<Docum29> docum29List;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;
    private String contentListPdf;
    private String path;
    private String w1doc;
    private String tddoc;

    /**
     *
     * @return
     */
    public Docum29ManagerLocal getDocum29Manager() {
        return docum29Manager;
    }

    /**
     *
     * @param docum29Manager
     */
    public void setDocum29Manager(Docum29ManagerLocal docum29Manager) {
        this.docum29Manager = docum29Manager;
    }

    /**
     *
     * @return
     */
    public List<Docum29> getDocum29List() {
        return docum29List;
    }

    /**
     *
     * @param docum29List
     */
    public void setDocum29RequestList(List<Docum29> docum29List) {
        this.docum29List = docum29List;
    }

    /**
     *
     * @return
     */
    public Docum29 getSelectedDocum29() {
        return selectedDocum29;
    }

    /**
     *
     * @param selectedDocum29
     */
    public void setSelectedDocum29(Docum29 selectedDocum29) {
        this.selectedDocum29 = selectedDocum29;
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
     * @param docum29
     */
    public void prepareSelectDocum29(Docum29 docum29) {
        selectedDocum29 = docum29;
        System.out.println(selectedDocum29.getNtdoc());
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
     * @param iddoc
     * @return
     */
    public Docum29 getDocum29Request(Integer iddoc) {

        try {

            this.selectedDocum29 = docum29Manager.getDocum29(iddoc);

        } catch (Docum29NotFound ex) {
            Logger.getLogger(Docum29Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Docum29Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedDocum29;
    }

    /**
     *
     * @param ntdoc
     */
    public void getListDocum29(Integer ntdoc) {

        docum29List = docum29Manager.getListDocum29(ntdoc);

        if (!docum29List.isEmpty()) {
            infoMessage = docum29List.size() + " Documentos";
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));
        }
    }

    /**
     *
     */
    public void retrieveDocum29List() {

        docum29List = docum29Manager.retrieveDocum29();

        if (docum29List.isEmpty()) {
            infoMessage = "No existen documentos";
        } else {
            infoMessage = docum29List.size() + " Documentos";
        }

    }

    /**
     *
     * @return
     */
    public String updateDocum29() {
        try {

            if (tddoc != null) {
                selectedDocum29.setTddoc(tddoc);
            }
            if (w1doc != null) {
                selectedDocum29.setW1doc(w1doc);
            }
            if (path != null) {
                selectedDocum29.setPadoc(path);
            }

            selectedDocum29.setStdoc(Constants.RECORD_ACTIVED);
            selectedDocum29.setFedoc(new Date());
            selectedDocum29.setUsdoc(FacesUtil.getUserName());

            docum29Manager.updateDocum29(selectedDocum29);

            infoMessage = "El documento se actualizó correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Docum29NotFound ex) {
            Logger.getLogger(Docum29Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al actualizar el Documento";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        }
        return null;
    }

    /**
     *
     * @return
     */
    public String deleteDocum29() {
        System.out.println("Backing Este es el id del documento que voy a borrar: " + selectedDocum29.getIddoc());
        try {

            selectedDocum29.setStdoc(Constants.RECORD_DELETED);
            selectedDocum29.setFedoc(new Date());
            selectedDocum29.setUsdoc(FacesUtil.getUserName());

            docum29Manager.deleteDocum29(selectedDocum29);

            infoMessage = "Documento eliminado";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Docum29NotFound ex) {
            Logger.getLogger(Docum29Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al eliminar el Documento";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        }

        return null;
    }

    /**
     *
     * @param docum29
     * @return
     */
    public String deleteDocum29(Docum29 docum29) {
        System.out.println("Backing Este es el id del documento que voy a borrar: " + docum29.getIddoc());
        try {

            docum29.setStdoc(Constants.RECORD_DELETED);
            docum29.setFedoc(new Date());
            docum29.setUsdoc(FacesUtil.getUserName());

            docum29Manager.deleteDocum29(docum29);

            infoMessage = "Documento eliminado";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Docum29NotFound ex) {
            Logger.getLogger(Docum29Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedDocum29 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>INFORMACION DOCUMENTAL DEL TRABAJADOR</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>DOCUMENTO</h1>"
                    + "<div class=\"page-content\">"
                    + "<table class=\"table-content\">"
                    + "<tr>"
                    + "<td width=\"160px\"><img width=\"160\"  src=\"http://sirhapp.dynu.net/sirhapp/files/" + this.selectedDocum29.getPadoc() + "\" /></td>"
                    + "<td class=\"separador\"></td>"
                    + "<td>"
                    + "<table>"
                    + "<tr>"
                    + "<td class=\"titulo\">RFC:</td>"
                    + "<td class=\"dato\">" + this.selectedDocum29.getNtdoc() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">CURP:</td>"
                    + "<td class=\"dato\">" + this.selectedDocum29.getNtdoc() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">NOMBRE:</td>"
                    + "<td class=\"dato\">" + this.selectedDocum29.getNtdoc() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">NACIMIENTO FECHA:</td>"
                    + "<td class=\"dato\">" + this.selectedDocum29.getNtdoc() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">EDAD:</td>"
                    + "<td class=\"dato\">" + this.selectedDocum29.getNtdoc() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">SEXO:</td>"
                    + "<td class=\"dato\">" + this.selectedDocum29.getNtdoc() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">NACIONALIDAD:</td>"
                    + "<td class=\"dato\">" + this.selectedDocum29.getNtdoc() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">OBSERVACIONES:</td>"
                    + "<td class=\"dato\">" + this.selectedDocum29.getNtdoc() + "</td>"
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

        if (selectedDocum29 != null) {

            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>LISTA DE DOCUMENTOS DEL TRABAJADOR</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>DATOS DEL DOCUMENTO 1</h1>"
                    + "<div class=\"page-content\">"
                    + "<table class=\"table-content\">"
                    + "<tr>"
                    + "<td width=\"160px\"><img width=\"160\"  src=\"http://sirhapp.dynu.net/sirhapp/files/" + this.selectedDocum29.getNtdoc() + "\" /></td>"
                    + "<td class=\"separador\"></td>"
                    + "<td>"
                    + "<table>"
                    + "<tr>"
                    + "<td class=\"titulo\">RFC:</td>"
                    + "<td class=\"dato\">" + this.selectedDocum29.getNtdoc() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">CURP:</td>"
                    + "<td class=\"dato\">" + this.selectedDocum29.getNtdoc() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">NOMBRE:</td>"
                    + "<td class=\"dato\">" + this.selectedDocum29.getNtdoc() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">NACIMIENTO FECHA:</td>"
                    + "<td class=\"dato\">" + this.selectedDocum29.getNtdoc() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">EDAD:</td>"
                    + "<td class=\"dato\">" + this.selectedDocum29.getNtdoc() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">SEXO:</td>"
                    + "<td class=\"dato\">" + this.selectedDocum29.getNtdoc() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">NACIONALIDAD:</td>"
                    + "<td class=\"dato\">" + this.selectedDocum29.getNtdoc() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">OBSERVACIONES:</td>"
                    + "<td class=\"dato\">" + this.selectedDocum29.getNtdoc() + "</td>"
                    + "</tr>"
                    + "</table></td>"
                    + "</tr>"
                    + "</table>"
                    + "</div>"
                    + "</body>"
                    + "</html>";
            this.contentListPdf = str;

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

        String outcome = "/secured/trabajadores/documentacion.xhtml?faces-redirect=true";

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

    /**
     *
     * @return
     */
    public String getInfoMessageModule() {
        return infoMessageModule;
    }

    /**
     *
     * @param infoMessageModule
     */
    public void setInfoMessageModule(String infoMessageModule) {
        this.infoMessageModule = infoMessageModule;
    }

    /**
     *
     * @return
     */
    public String updateDocum29Foto() {

        if (selectedDocum29 != null) {
            selectedDocum29.setPadoc(null);
        }
        FacesUtil.removeManagedBeanInSession("fileUploadBean");
        String oldInfoMessageModule = infoMessageModule;
        infoMessageModule = "Documento";
        infoMessage = "Se borro el Documento";
        FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
        infoMessageModule = oldInfoMessageModule;

        return null;
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
    public String getW1doc() {
        return w1doc;
    }

    /**
     *
     * @param w1doc
     */
    public void setW1doc(String w1doc) {
        this.w1doc = w1doc;
    }

    /**
     *
     * @return
     */
    public String getTddoc() {
        return tddoc;
    }

    /**
     *
     * @param tddoc
     */
    public void setTddoc(String tddoc) {
        this.tddoc = tddoc;
    }

}
