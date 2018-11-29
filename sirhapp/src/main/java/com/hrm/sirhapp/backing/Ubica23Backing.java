package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Ubica23;
import com.hrm.sirhapp.service.Ubica23ManagerLocal;
import com.hrm.sirhapp.service.exception.Ubica23NotFound;
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
public class Ubica23Backing extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Ubica23ManagerLocal ubica23Manager;

    @Named
    @Produces
    @RequestScoped
    private Ubica23 selectedUbica23;

    private List<Ubica23> ubica23List;

    private List<Ubica23> ubica23ListEstados;

    private List<String> paises;
    private List<String> estados;
    private List<String> municipios;
    private List<String> ciudades;
    private List<String> colonias;

    private String paubi;
    private String esubi;
    private String mdubi;
    private String loubi;
    private String coubi;
    private String cpubi;

    private int status = 1;
    private String infoMessageModule;
    private String infoMessage;
    private String contentPdf;

    @PostConstruct
    private void init() {
        getUbica23Paises();
        this.infoMessageModule = "Modulo Ubicaciones";
    }

    /**
     *
     * @return
     */
    public String getPaubi() {
        return paubi;
    }

    /**
     *
     * @param paubi
     */
    public void setPaubi(String paubi) {
        this.paubi = paubi;
    }

    /**
     *
     * @return
     */
    public String getEsubi() {
        return esubi;
    }

    /**
     *
     * @param esubi
     */
    public void setEsubi(String esubi) {
        this.esubi = esubi;
    }

    /**
     *
     * @return
     */
    public String getMdubi() {
        return mdubi;
    }

    /**
     *
     * @param mdubi
     */
    public void setMdubi(String mdubi) {
        this.mdubi = mdubi;
    }

    /**
     *
     * @return
     */
    public String getLoubi() {
        return loubi;
    }

    /**
     *
     * @param loubi
     */
    public void setLoubi(String loubi) {
        this.loubi = loubi;
    }

    /**
     *
     * @return
     */
    public String getCoubi() {
        return coubi;
    }

    /**
     *
     * @param coubi
     */
    public void setCoubi(String coubi) {
        this.coubi = coubi;
    }

    /**
     *
     * @return
     */
    public String getCpubi() {
        return cpubi;
    }

    /**
     *
     * @param cpubi
     */
    public void setCpubi(String cpubi) {
        this.cpubi = cpubi;
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
     * @param pais
     * @return
     */
    public List<String> getEstados(String pais) {
        List<String> result = ubica23Manager.getListUbica23Estados(pais);
        return result;
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
    public List<Ubica23> getUbica23ListEstados() {
        return ubica23ListEstados;
    }

    /**
     *
     * @param ubica23ListEstados
     */
    public void setUbica23ListEstados(List<Ubica23> ubica23ListEstados) {
        this.ubica23ListEstados = ubica23ListEstados;
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
     * @param pais
     * @param estado
     * @return
     */
    public List<String> getMunicipios(String pais, String estado) {
        List<String> result = ubica23Manager.getListUbica23Municipios(pais, estado);
        return result;
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
     * @param pais
     * @param estado
     * @param municipio
     * @return
     */
    public List<String> getCiudades(String pais, String estado, String municipio) {
        List<String> result = ubica23Manager.getListUbica23Ciudades(pais, estado, municipio);
        return result;
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
    public void handleChange(AjaxBehaviorEvent vce) {
        String name = (String) ((UIOutput) vce.getSource()).getValue();
        String id = (String) ((UIOutput) vce.getSource()).getId();
        switch (id) {
            case "pais":
                paubi = name;
                getUbica23Estados(paubi);
                municipios = null;
                ciudades = null;
                colonias = null;

                break;
            case "estado":
                esubi = name;
                getUbica23Municipios(paubi, esubi);
                ciudades = null;
                colonias = null;
                break;
            case "municipio":
                mdubi = name;
                getUbica23Ciudades(paubi, esubi, mdubi);
                colonias = null;
                break;
            case "ciudad":
                loubi = name;
                getUbica23Colonias(paubi, esubi, mdubi, loubi);
                break;
            case "colonia":
                getUbica23Cp(paubi, esubi, mdubi, loubi, coubi);
                break;

            case "codigopostal":
                if (name.indexOf("_", 0) == -1) {
                    getUbica23Cp(name);
                } else {
                    cpubi = null;
                    ubica23List = null;
                    String var = name.replace("_", "");
                    if (var.length() == 4 || var.length() == 0) {

                        paubi = null;
                        esubi = null;
                        municipios = null;
                        ciudades = null;
                        colonias = null;

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
        System.out.println(pais);

        estados = ubica23Manager.getListUbica23Estados(pais);
        System.out.println(estados.size());
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

        cpubi = ubica23Manager.getListUbica23Cp(pais, estado, municipio, ciudad, colonia);

        System.out.println("cp" + cpubi);

    }

    /**
     *
     * @param cpubi
     */
    public void getUbica23Cp(String cpubi) {
        ubica23List = ubica23Manager.getListUbica23Cp(cpubi);

        System.out.println("Actualizar todo: con cpubi+" + cpubi);
        if (ubica23List.size() > 0) {
            actualizaUbi();
        }

    }

    /**
     *
     */
    public void actualizaUbi() {

        paubi = ubica23List.get(0).getPaubi();
        esubi = ubica23List.get(0).getEsubi();
        mdubi = ubica23List.get(0).getMdubi();
        loubi = ubica23List.get(0).getLoubi();
        coubi = ubica23List.get(0).getCoubi();

        getUbica23Estados(paubi);

        getUbica23Municipios(paubi, esubi);

        getUbica23Ciudades(paubi, esubi, mdubi);

        getUbica23Colonias(paubi, esubi, mdubi, loubi);

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
    public Ubica23 getSelectedUbica23() {
        return selectedUbica23;
    }

    /**
     *
     * @param selectedUbica23
     */
    public void setSelectedUbica23(Ubica23 selectedUbica23) {
        this.selectedUbica23 = selectedUbica23;
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
     * @param idubi
     * @return
     */
    public Ubica23 getUbica23Request(Integer idubi) {

        try {

            this.selectedUbica23 = ubica23Manager.getUbica23(idubi);

        } catch (Ubica23NotFound ex) {
            Logger.getLogger(Ubica23Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(Ubica23Backing.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedUbica23;
    }

    /**
     *
     */
    public void getListUbica23() {

        ubica23List = ubica23Manager.getListUbica23();

        if (!ubica23List.isEmpty()) {
            infoMessage = ubica23List.size() + " registro(s)";
        }
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
     */
    public void retrieveUbica23List() {

        ubica23List = ubica23Manager.retrieveUbica23();

        if (ubica23List.isEmpty()) {
            infoMessage = "No existen registros";
        } else {
            infoMessage = ubica23List.size() + " registros";
        }

    }

    /**
     *
     * @return
     */
    public String updateUbica23() {
        try {

            selectedUbica23.setStubi(Constants.RECORD_ACTIVED);
            selectedUbica23.setFeubi(new Date());
            selectedUbica23.setUsubi(FacesUtil.getUserName());

            ubica23Manager.updateUbica23(selectedUbica23);

            infoMessage = "El registro se actualizó correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Ubica23NotFound ex) {
            Logger.getLogger(Ubica23Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteUbica23() {
        try {

            selectedUbica23.setStubi(Constants.RECORD_DELETED);
            selectedUbica23.setFeubi(new Date());
            selectedUbica23.setUsubi(FacesUtil.getUserName());

            ubica23Manager.deleteUbica23(selectedUbica23);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Ubica23NotFound ex) {
            Logger.getLogger(Ubica23Backing.class.getName()).log(Level.SEVERE, null, ex);
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

        if (selectedUbica23 != null) {
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
     * @param action
     * @return
     * @throws IOException
     */
    public String go(int action) throws IOException {

        String outcome = "/secured/catalogos/ubicacionesList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/catalogos/ubicacionesView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/catalogos/ubicacionesEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/catalogos/ubicacionesCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

}
