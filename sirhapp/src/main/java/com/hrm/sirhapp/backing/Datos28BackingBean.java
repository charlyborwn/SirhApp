package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Datos28;
import com.hrm.sirhapp.model.Ubica23;
import com.hrm.sirhapp.service.Datos28ManagerLocal;
import com.hrm.sirhapp.service.Traba36ManagerLocal;
import com.hrm.sirhapp.service.Ubica23ManagerLocal;
import com.hrm.sirhapp.service.exception.Datos28AlreadyExists;
import com.hrm.sirhapp.service.exception.Datos28NotFound;
import com.hrm.sirhapp.service.exception.Traba36NotFound;
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
import javax.ejb.EJBException;
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
public class Datos28BackingBean extends BaseBacking implements Serializable {

    @EJB
    private Datos28ManagerLocal datos28Manager;
    @EJB
    private Traba36ManagerLocal traba36Manager;

    private Datos28 newDatos28Bean = new Datos28();
    private Datos28 selectedDatos28Bean;
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
    private boolean registered;

    /**
     *
     */
    public void preRenderView() {
        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);
        getDatos28Request(userSessionBean.getTrabajador());

        if (selectedDatos28Bean != null) {
            System.out.println("ESTE ES EL RFC" + selectedDatos28Bean.getRfdat());
            if (selectedDatos28Bean.getCpdat() != null && selectedDatos28Bean.getCpdat().length() > 0) {
                getUbica23Cp(selectedDatos28Bean.getCpdat());
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
    public Datos28 getSelectedDatos28Bean() {
        return selectedDatos28Bean;
    }

    /**
     *
     * @param selectedDatos28Bean
     */
    public void setSelectedDatos28Bean(Datos28 selectedDatos28Bean) {
        this.selectedDatos28Bean = selectedDatos28Bean;
    }

    /**
     *
     * @return
     */
    public Datos28 getNewDatos28Bean() {
        return newDatos28Bean;
    }

    /**
     *
     * @param newDatos28Bean
     */
    public void setNewDatos28Bena(Datos28 newDatos28Bean) {
        this.newDatos28Bean = newDatos28Bean;
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
    public boolean isRegistered() {
        return registered;
    }

    /**
     *
     * @param registered
     */
    public void setRegistered(boolean registered) {
        this.registered = registered;
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

            this.selectedDatos28Bean = datos28Manager.getDatos28(ntdat);

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

        return selectedDatos28Bean;
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
     * @return
     */
    public String activateDatos28() {
        Integer ntdat;
        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);
        ntdat = userSessionBean.getTrabajador();
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

            selectedDatos28Bean.setStdat(Constants.RECORD_ACTIVED);
            selectedDatos28Bean.setFedat(new Date());
            selectedDatos28Bean.setUsdat(FacesUtil.getUserName());

            datos28Manager.updateDatos28(selectedDatos28Bean);

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

            selectedDatos28Bean.setStdat(Constants.RECORD_DELETED);
            selectedDatos28Bean.setFedat(new Date());
            selectedDatos28Bean.setUsdat(FacesUtil.getUserName());

            datos28Manager.deleteDatos28(selectedDatos28Bean);

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
    public String register() {

        try {
            UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);
            

            newDatos28Bean.setNtdat(userSessionBean.getTrabajador());
            
            newDatos28Bean.setRfdat(traba36Manager.getTraba36(userSessionBean.getTrabajador()).getRftra());
            newDatos28Bean.setStdat(Constants.RECORD_ACTIVED);
            newDatos28Bean.setFedat(new Date());
            newDatos28Bean.setUsdat(FacesUtil.getUserName());

            datos28Manager.registerDatos28(newDatos28Bean);

            infoMessage = "El registro se creo correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Datos28AlreadyExists ex) {
            Logger.getLogger(Datos28AddBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        } catch (EJBException ex) {
            Logger.getLogger(Datos28AddBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error en la base de datos";
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        } catch (Traba36NotFound ex) {
            Logger.getLogger(Datos28BackingBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
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

        if (selectedDatos28Bean != null) {
            selectedDatos28Bean.setCpdat(ubica23Manager.getListUbica23Cp(pais, estado, municipio, ciudad, colonia));
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

        if (selectedDatos28Bean != null) {
            selectedDatos28Bean.setPadat(ubica23List.get(0).getPaubi());
            selectedDatos28Bean.setEsdat(ubica23List.get(0).getEsubi());
            selectedDatos28Bean.setMddat(ubica23List.get(0).getMdubi());
            selectedDatos28Bean.setLodat(ubica23List.get(0).getLoubi());
            selectedDatos28Bean.setCodat(ubica23List.get(0).getCoubi());
        }
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
                if (selectedDatos28Bean != null) {
                    selectedDatos28Bean.setPadat(name);
                }
                getUbica23Estados(name);

                municipios = null;
                ciudades = null;
                colonias = null;
                break;
            case "estado":
                if (selectedDatos28Bean != null) {
                    selectedDatos28Bean.setEsdat(name);
                    getUbica23Municipios(selectedDatos28Bean.getPadat(), selectedDatos28Bean.getEsdat());
                }
                ciudades = null;
                colonias = null;
                break;
            case "municipio":

                if (selectedDatos28Bean != null) {
                    selectedDatos28Bean.setMddat(name);
                    getUbica23Ciudades(selectedDatos28Bean.getPadat(), selectedDatos28Bean.getEsdat(), selectedDatos28Bean.getMddat());
                }
                colonias = null;
                break;
            case "ciudad":

                if (selectedDatos28Bean != null) {
                    selectedDatos28Bean.setLodat(name);
                    getUbica23Colonias(selectedDatos28Bean.getPadat(), selectedDatos28Bean.getEsdat(), selectedDatos28Bean.getMddat(), selectedDatos28Bean.getLodat());
                }
                break;
            case "colonia":

                if (selectedDatos28Bean != null) {
                    selectedDatos28Bean.setCodat(name);
                    getUbica23Cp(selectedDatos28Bean.getPadat(), selectedDatos28Bean.getEsdat(), selectedDatos28Bean.getMddat(), selectedDatos28Bean.getLodat(), selectedDatos28Bean.getCodat());
                }
                break;
            case "codigopostal":

                if (selectedDatos28Bean != null) {
                    selectedDatos28Bean.setCpdat(name);
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

}
