package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Datos28a;
import com.hrm.sirhapp.model.Ubica23;
import com.hrm.sirhapp.service.Datos28aManagerLocal;
import com.hrm.sirhapp.service.Ubica23ManagerLocal;
import com.hrm.sirhapp.service.exception.Datos28aNotFound;
import com.hrm.sirhapp.util.FacesUtil;
import com.hrm.sirhapp.util.LanguagesUtil;
import java.io.IOException;
import java.io.Serializable;
import java.text.Collator;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIOutput;
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
public class Datos28aBacking extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Datos28aManagerLocal datos28aManager;

    @Named
    @Produces
    @RequestScoped
    private Datos28a selectedDatos28a;

    @EJB
    private Ubica23ManagerLocal ubica23Manager;

    private List<Ubica23> ubica23List;

    private List<String> paises;
    private List<String> estados;
    private List<String> municipios;
    private List<String> ciudades;
    private List<String> colonias;

    private List<Datos28a> datos28aList;

    private int status = 1;
    private boolean active;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;

    /**
     *
     */
    public void preRenderView() {
        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);
        getDatos28aRequest(userSessionBean.getAspirante());

        if (selectedDatos28a != null) {
            System.out.println("ESTE ES EL RFC" + selectedDatos28a.getRfdatA());
            if (selectedDatos28a.getCpdatA() != null && selectedDatos28a.getCpdatA().length() > 0) {
                getUbica23Cp(selectedDatos28a.getCpdatA());
            }
        }
    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        this.active = true;
        this.infoMessageModule = "Modulo Aspirantes";

        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
            preRenderView();
            System.out.println("preRenderView called");
        }
    }

    /**
     *
     * @return
     */
    public Datos28aManagerLocal getDatos28aManager() {
        return datos28aManager;
    }

    /**
     *
     * @param datos28aManager
     */
    public void setDatos28aManager(Datos28aManagerLocal datos28aManager) {
        this.datos28aManager = datos28aManager;
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
    public List<Datos28a> getDatos28aList() {
        return datos28aList;
    }

    /**
     *
     * @param datos28aList
     */
    public void setDatos28aRequestList(List<Datos28a> datos28aList) {
        this.datos28aList = datos28aList;
    }

    /**
     *
     * @return
     */
    public Datos28a getSelectedDatos28a() {
        return selectedDatos28a;
    }

    /**
     *
     * @param selectedDatos28a
     */
    public void setSelectedDatos28a(Datos28a selectedDatos28a) {
        this.selectedDatos28a = selectedDatos28a;
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
     * @param rfdatA
     * @return
     */
    public Datos28a getDatos28aRequest(String rfdatA) {

        try {

            this.selectedDatos28a = datos28aManager.getDatos28a(rfdatA);

        } catch (Datos28aNotFound ex) {
            Logger.getLogger(Datos28aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Datos28aBacking.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        } finally {
            if (datos28aManager.alreadyExistsInnactive(rfdatA)) {

                this.infoMessage = "El registro fue eliminado previamente";
                this.active = false;
            }
        }

        return selectedDatos28a;
    }

    /**
     *
     */
    public void retrieveDatos28aList() {

        datos28aList = datos28aManager.retrieveDatos28a();

        if (datos28aList.isEmpty()) {
            infoMessage = "No existen aspirantes!";
        } else {
            infoMessage = datos28aList.size() + " Aspirantes";
        }

    }

    /**
     *
     * @param rfdatA
     * @return
     */
    public String activateDatos28a(String rfdatA) {
        try {

            Datos28a activateDatos28a = datos28aManager.getDatos28aById(rfdatA);

            activateDatos28a.setStdatA(Constants.RECORD_ACTIVED);
            activateDatos28a.setFedatA(new Date());
            activateDatos28a.setUsdatA(FacesUtil.getUserName());

            datos28aManager.updateDatos28a(activateDatos28a);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Datos28aNotFound ex) {
            Logger.getLogger(Datos28Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        } finally {
            if (datos28aManager.alreadyExistsInnactive(rfdatA)) {

                this.infoMessage = "El registro fue eliminado previamente";
                this.active = false;
            }
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String updateDatos28a() {
        try {

            selectedDatos28a.setStdatA(Constants.RECORD_ACTIVED);
            selectedDatos28a.setFedatA(new Date());
            selectedDatos28a.setUsdatA(FacesUtil.getUserName());

            datos28aManager.updateDatos28a(selectedDatos28a);

            System.out.println(selectedDatos28a.getRfdatA());
            System.out.println(selectedDatos28a.getPadatA());
            System.out.println(selectedDatos28a.getEsdatA());
            System.out.println(selectedDatos28a.getMddatA());
            System.out.println(selectedDatos28a.getLodatA());
            System.out.println(selectedDatos28a.getCodatA());

            infoMessage = "Aspirante actualizado correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Datos28aNotFound ex) {
            Logger.getLogger(Datos28aBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al actualizar los datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        }
        return null;

    }

    /**
     *
     * @return
     */
    public String deleteDatos28a() {
        try {

            selectedDatos28a.setStdatA(Constants.RECORD_DELETED);
            selectedDatos28a.setFedatA(new Date());
            selectedDatos28a.setUsdatA(FacesUtil.getUserName());

            datos28aManager.deleteDatos28a(selectedDatos28a);

            infoMessage = "Los datos personales del aspirante fueron eliminados correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Datos28aNotFound ex) {
            Logger.getLogger(Datos28aBacking.class.getName()).log(Level.SEVERE, null, ex);
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
        String str;

        if (selectedDatos28a != null) {
            if (selectedDatos28a.getRfdatA().length() > 0) {
                str = ""
                        + "<html>"
                        + "<head>"
                        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                        + "<title>DATOS GENERALES DE ASPIRANTE</title>"
                        + "</head>"
                        + "<body>"
                        + "<h1>DATOS GENERALES DE ASPIRANTE</h1>"
                        + "<div class=\"page-content\">"
                        + "<table class=\"table-content\">"
                        + "<tr>"
                        + "<td width=\"160px\"><img width=\"160\"  src=\"http://sirhapp.dynu.net/sirhapp/files/" + this.selectedDatos28a.getRfdatA() + "\" /></td>"
                        + "<td class=\"separador\"></td>"
                        + "<td>"
                        + "<table>"
                        + "<tr>"
                        + "<td class=\"titulo\">RFC:</td>"
                        + "<td class=\"dato\">" + this.selectedDatos28a.getRfdatA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">CURP:</td>"
                        + "<td class=\"dato\">" + this.selectedDatos28a.getRfdatA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">NOMBRE:</td>"
                        + "<td class=\"dato\">" + this.selectedDatos28a.getRfdatA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">NACIMIENTO FECHA:</td>"
                        + "<td class=\"dato\">" + this.selectedDatos28a.getRfdatA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">EDAD:</td>"
                        + "<td class=\"dato\">" + this.selectedDatos28a.getRfdatA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">SEXO:</td>"
                        + "<td class=\"dato\">" + this.selectedDatos28a.getRfdatA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">NACIONALIDAD:</td>"
                        + "<td class=\"dato\">" + this.selectedDatos28a.getRfdatA() + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"titulo\">OBSERVACIONES:</td>"
                        + "<td class=\"dato\">" + this.selectedDatos28a.getRfdatA() + "</td>"
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
     * @param action
     * @return
     * @throws IOException
     */
    public String go(int action) throws IOException {

        String outcome = "/secured/aspirantes/datosPersonales.xhtml?faces-redirect=true";

        if (action == 1) {
            outcome = "/secured/aspirantes/datosPersonalesEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/aspirantes/datosPersonalesCreate.xhtml?faces-redirect=true";
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

    /**
     *
     * @return
     */
    public Ubica23ManagerLocal getUbica23Manager() {
        return ubica23Manager;
    }

    /**
     *
     * @param ubica23Manager
     */
    public void setUbica23Manager(Ubica23ManagerLocal ubica23Manager) {
        this.ubica23Manager = ubica23Manager;
    }

    /**
     *
     * @return
     */
    public List<Ubica23> getUbica23List() {
        return ubica23List;
    }

    /**
     *
     * @param ubica23List
     */
    public void setUbica23List(List<Ubica23> ubica23List) {
        this.ubica23List = ubica23List;
    }

    /**
     *
     * @return
     */
    public List<String> getPaises() {
        return paises;
    }

    /**
     *
     * @param paises
     */
    public void setPaises(List<String> paises) {
        this.paises = paises;
    }

    /**
     *
     * @return
     */
    public List<String> getEstados() {
        return estados;
    }

    /**
     *
     * @param estados
     */
    public void setEstados(List<String> estados) {
        this.estados = estados;
    }

    /**
     *
     * @return
     */
    public List<String> getMunicipios() {
        return municipios;
    }

    /**
     *
     * @param municipios
     */
    public void setMunicipios(List<String> municipios) {
        this.municipios = municipios;
    }

    /**
     *
     * @return
     */
    public List<String> getCiudades() {
        return ciudades;
    }

    /**
     *
     * @param ciudades
     */
    public void setCiudades(List<String> ciudades) {
        this.ciudades = ciudades;
    }

    /**
     *
     * @return
     */
    public List<String> getColonias() {
        return colonias;
    }

    /**
     *
     * @param colonias
     */
    public void setColonias(List<String> colonias) {
        this.colonias = colonias;
    }

    /**
     *
     * @param vce
     */
    public void handleChanges(AjaxBehaviorEvent vce) {
        String name = (String) ((UIOutput) vce.getSource()).getValue();
        String id = (String) ((UIOutput) vce.getSource()).getId();
        switch (id) {
            case "pais":
                if (selectedDatos28a != null) {
                    selectedDatos28a.setPadatA(name);
                }
                getUbica23Estados(name);

                municipios = null;
                ciudades = null;
                colonias = null;
                break;
            case "estado":
                if (selectedDatos28a != null) {
                    selectedDatos28a.setEsdatA(name);
                    getUbica23Municipios(selectedDatos28a.getPadatA(), selectedDatos28a.getEsdatA());
                }
                ciudades = null;
                colonias = null;
                break;
            case "municipio":

                if (selectedDatos28a != null) {
                    selectedDatos28a.setMddatA(name);
                    getUbica23Ciudades(selectedDatos28a.getPadatA(), selectedDatos28a.getEsdatA(), selectedDatos28a.getMddatA());
                }
                colonias = null;
                break;
            case "ciudad":

                if (selectedDatos28a != null) {
                    selectedDatos28a.setLodatA(name);
                    getUbica23Colonias(selectedDatos28a.getPadatA(), selectedDatos28a.getEsdatA(), selectedDatos28a.getMddatA(), selectedDatos28a.getLodatA());
                }
                break;
            case "colonia":

                if (selectedDatos28a != null) {
                    selectedDatos28a.setCodatA(name);
                    getUbica23Cp(selectedDatos28a.getPadatA(), selectedDatos28a.getEsdatA(), selectedDatos28a.getMddatA(), selectedDatos28a.getLodatA(), selectedDatos28a.getCodatA());
                }
                break;
            case "codigopostal":

                if (selectedDatos28a != null) {
                    selectedDatos28a.setCpdatA(name);
                }

                System.out.println(name);

                if (name.indexOf("_", 0) == -1) {
                    getUbica23Cp(name);
                } else {

                    ubica23List = null;
                    String var = name.replace("_", "");
                    if (var.length() == 4 || var.length() == 0) {

                        getUbica23Paises();

                        estados = null;
                        municipios = null;
                        ciudades = null;
                        colonias = null;
                    }
                }
                break;

            default:
                break;
        }
        System.out.println("id:" + id + " name:" + name);
    }

    /**
     *
     */
    public void getUbica23Paises() {

        paises = ubica23Manager.getListUbica23Paises();
        Collections.sort(paises, Collator.getInstance());

    }

    /**
     *
     * @param pais
     */
    public void getUbica23Estados(String pais) {

        estados = ubica23Manager.getListUbica23Estados(pais);
        Collections.sort(estados, Collator.getInstance());

    }

    /**
     *
     * @param pais
     * @param estado
     */
    public void getUbica23Municipios(String pais, String estado) {

        municipios = ubica23Manager.getListUbica23Municipios(pais, estado);
        Collections.sort(municipios, Collator.getInstance());

    }

    /**
     *
     * @param pais
     * @param estado
     * @param municipio
     */
    public void getUbica23Ciudades(String pais, String estado, String municipio) {

        ciudades = ubica23Manager.getListUbica23Ciudades(pais, estado, municipio);
        Collections.sort(ciudades, Collator.getInstance());

    }

    /**
     *
     * @param pais
     * @param estado
     * @param municipio
     * @param ciudad
     */
    public void getUbica23Colonias(String pais, String estado, String municipio, String ciudad) {

        colonias = ubica23Manager.getListUbica23Colonias(pais, estado, municipio, ciudad);
        Collections.sort(colonias, Collator.getInstance());

    }

    /**
     *
     * @param pais
     * @param estado
     * @param municipio
     * @param ciudad
     * @param colonia
     */
    public void getUbica23Cp(String pais, String estado, String municipio, String ciudad, String colonia) {

        if (selectedDatos28a != null) {
            selectedDatos28a.setCpdatA(ubica23Manager.getListUbica23Cp(pais, estado, municipio, ciudad, colonia));
        }

    }

    /**
     *
     * @param cpubi
     */
    public void getUbica23Cp(String cpubi) {
        ubica23List = ubica23Manager.getListUbica23Cp(cpubi);
        System.out.println("cpubi" + cpubi);
        if (ubica23List.size() > 0) {
            actualizaUbi();
        }

    }

    /**
     *
     * @param cpubi
     */
    public void getUbica23CpList(String cpubi) {
        ubica23List = ubica23Manager.getListUbica23Cp(cpubi);

        if (ubica23List.size() > 0) {
            actualizaUbiList();
        }

    }

    /**
     *
     */
    public void actualizaUbiList() {

        getUbica23Paises();

        getUbica23Estados(ubica23List.get(0).getPaubi());

        getUbica23Municipios(ubica23List.get(0).getPaubi(), ubica23List.get(0).getEsubi());

        getUbica23Ciudades(ubica23List.get(0).getPaubi(), ubica23List.get(0).getEsubi(), ubica23List.get(0).getMdubi());

        getUbica23Colonias(ubica23List.get(0).getPaubi(), ubica23List.get(0).getEsubi(), ubica23List.get(0).getMdubi(), ubica23List.get(0).getLoubi());

    }

    /**
     *
     */
    public void actualizaUbi() {

        getUbica23Paises();

        getUbica23Estados(ubica23List.get(0).getPaubi());

        getUbica23Municipios(ubica23List.get(0).getPaubi(), ubica23List.get(0).getEsubi());

        getUbica23Ciudades(ubica23List.get(0).getPaubi(), ubica23List.get(0).getEsubi(), ubica23List.get(0).getMdubi());

        getUbica23Colonias(ubica23List.get(0).getPaubi(), ubica23List.get(0).getEsubi(), ubica23List.get(0).getMdubi(), ubica23List.get(0).getLoubi());

        if (selectedDatos28a != null) {
            selectedDatos28a.setPadatA(ubica23List.get(0).getPaubi());
            selectedDatos28a.setEsdatA(ubica23List.get(0).getEsubi());
            selectedDatos28a.setMddatA(ubica23List.get(0).getMdubi());
            selectedDatos28a.setLodatA(ubica23List.get(0).getLoubi());
            selectedDatos28a.setCodatA(ubica23List.get(0).getCoubi());
        }
    }
}
