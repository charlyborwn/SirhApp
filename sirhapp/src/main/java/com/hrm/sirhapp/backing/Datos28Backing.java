package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Datos28;
import com.hrm.sirhapp.model.Ubica23;
import com.hrm.sirhapp.service.Datos28ManagerLocal;
import com.hrm.sirhapp.service.Ubica23ManagerLocal;
import com.hrm.sirhapp.service.exception.Datos28NotFound;
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
public class Datos28Backing extends BaseBacking implements Serializable {

    @EJB
    private Datos28ManagerLocal datos28Manager;

    @Named
    @Produces
    @ViewScoped
    private Datos28 selectedDatos28;

    private List<Datos28> datos28List;

    @EJB
    private Ubica23ManagerLocal ubica23Manager;

    private List<Ubica23> ubica23List;

    private List<String> paises;
    private List<String> estados;
    private List<String> municipios;
    private List<String> ciudades;
    private List<String> colonias;

    private boolean active;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;

    /**
     *
     */
    public void preRenderView() {
        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);
        getDatos28Request(userSessionBean.getTrabajador());

        if (selectedDatos28 != null) {
            System.out.println("ESTE ES EL RFC" + selectedDatos28.getRfdat());
            if (selectedDatos28.getCpdat() != null && selectedDatos28.getCpdat().length() > 0) {
                getUbica23Cp(selectedDatos28.getCpdat());
            }
        }
    }

    @PostConstruct
    private void init() {
        this.active = true;
        this.infoMessageModule = "Modulo Datos Trabajadores";
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
    public List<Datos28> getDatos28List() {
        return datos28List;
    }

    /**
     *
     * @param datos28List
     */
    public void setDatos28RequestList(List<Datos28> datos28List) {
        this.datos28List = datos28List;
    }

    /**
     *
     * @return
     */
    public Datos28 getSelectedDatos28() {
        return selectedDatos28;
    }

    /**
     *
     * @param selectedDatos28
     */
    public void setSelectedDatos28(Datos28 selectedDatos28) {
        this.selectedDatos28 = selectedDatos28;
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
     * @return
     */
    public Datos28ManagerLocal getDatos28Manager() {
        return datos28Manager;
    }

    /**
     *
     * @param datos28Manager
     */
    public void setDatos28Manager(Datos28ManagerLocal datos28Manager) {
        this.datos28Manager = datos28Manager;
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
     * @param ntdat
     * @return
     */
    public Datos28 getDatos28Request(Integer ntdat) {

        try {

            this.selectedDatos28 = datos28Manager.getDatos28(ntdat);

        } catch (Datos28NotFound ex) {
            Logger.getLogger(Datos28Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Datos28Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        } finally {
            if (datos28Manager.alreadyExistsInnactive(ntdat)) {

                this.infoMessage = "El registro fue eliminado previamente";
                this.active = false;
            }
        }

        return selectedDatos28;
    }

    /**
     *
     */
    public void retrieveDatos28List() {

        datos28List = datos28Manager.retrieveDatos28();

        if (datos28List.isEmpty()) {
            infoMessage = "No existen trabajadores!";
        } else {
            infoMessage = datos28List.size() + " Trabajadores";
        }

    }

    /**
     *
     * @param ntdat
     * @return
     */
    public String acivateDatos28(Integer ntdat) {
        try {

            Datos28 activateDatos28 = datos28Manager.getDatos28ById(ntdat);

            activateDatos28.setStdat(Constants.RECORD_ACTIVED);
            activateDatos28.setFedat(new Date());
            activateDatos28.setUsdat(FacesUtil.getUserName());

            datos28Manager.updateDatos28(activateDatos28);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Datos28NotFound ex) {
            Logger.getLogger(Datos28Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        } finally {
            if (datos28Manager.alreadyExistsInnactive(ntdat)) {

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
    public String updateDatos28() {
        try {

            selectedDatos28.setStdat(Constants.RECORD_ACTIVED);
            selectedDatos28.setFedat(new Date());
            selectedDatos28.setUsdat(FacesUtil.getUserName());

            datos28Manager.updateDatos28(selectedDatos28);

            infoMessage = "Actualizado correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Datos28NotFound ex) {
            Logger.getLogger(Datos28Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al actualizar los datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        }
        return null;

    }

    /**
     *
     * @return
     */
    public String deleteDatos28() {
        try {

            selectedDatos28.setStdat(Constants.RECORD_DELETED);
            selectedDatos28.setFedat(new Date());
            selectedDatos28.setUsdat(FacesUtil.getUserName());

            datos28Manager.deleteDatos28(selectedDatos28);

            infoMessage = "Se elimino correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));

        } catch (Datos28NotFound ex) {
            Logger.getLogger(Datos28Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedDatos28 != null) {
            str = ""
                    + "<html>"
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "<title>DATOS GENERALES DE TRABAJADOR</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>DATOS GENERALES DE TRABAJADOR</h1>"
                    + "<div class=\"page-content\">"
                    + "<table class=\"table-content\">"
                    + "<tr>"
                    + "<td width=\"160px\"><img width=\"160\"  src=\"http://sirhapp.dynu.net/sirhapp/files/" + this.selectedDatos28.getRfdat() + "\" /></td>"
                    + "<td class=\"separador\"></td>"
                    + "<td>"
                    + "<table>"
                    + "<tr>"
                    + "<td class=\"titulo\">RFC:</td>"
                    + "<td class=\"dato\">" + this.selectedDatos28.getRfdat() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">CURP:</td>"
                    + "<td class=\"dato\">" + this.selectedDatos28.getRfdat() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">NOMBRE:</td>"
                    + "<td class=\"dato\">" + this.selectedDatos28.getRfdat() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">NACIMIENTO FECHA:</td>"
                    + "<td class=\"dato\">" + this.selectedDatos28.getRfdat() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">EDAD:</td>"
                    + "<td class=\"dato\">" + this.selectedDatos28.getRfdat() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">SEXO:</td>"
                    + "<td class=\"dato\">" + this.selectedDatos28.getRfdat() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">NACIONALIDAD:</td>"
                    + "<td class=\"dato\">" + this.selectedDatos28.getRfdat() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td class=\"titulo\">OBSERVACIONES:</td>"
                    + "<td class=\"dato\">" + this.selectedDatos28.getRfdat() + "</td>"
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
     * @param action
     * @return
     * @throws IOException
     */
    public String go(int action) throws IOException {

        String outcome = "/secured/trabajadores/datosPersonales.xhtml?faces-redirect=true";

        if (action == 1) {
            outcome = "/secured/trabajadores/datosPersonalesEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/trabajadores/datosPersonalesCreate.xhtml?faces-redirect=true";
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

        if (selectedDatos28 != null) {
            selectedDatos28.setCpdat(ubica23Manager.getListUbica23Cp(pais, estado, municipio, ciudad, colonia));
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

        if (selectedDatos28 != null) {
            selectedDatos28.setPadat(ubica23List.get(0).getPaubi());
            selectedDatos28.setEsdat(ubica23List.get(0).getEsubi());
            selectedDatos28.setMddat(ubica23List.get(0).getMdubi());
            selectedDatos28.setLodat(ubica23List.get(0).getLoubi());
            selectedDatos28.setCodat(ubica23List.get(0).getCoubi());
        }
    }

}
