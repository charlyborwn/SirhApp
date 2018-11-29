package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Datos28a;
import com.hrm.sirhapp.model.Ubica23;
import com.hrm.sirhapp.service.Datos28aManagerLocal;
import com.hrm.sirhapp.service.Ubica23ManagerLocal;
import com.hrm.sirhapp.service.exception.Datos28aAlreadyExists;
import com.hrm.sirhapp.util.FacesUtil;
import java.io.Serializable;
import java.text.Collator;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@ViewScoped
public class Datos28aAddBacking extends BaseBacking implements Serializable {
    
    @EJB
    private Datos28aManagerLocal datos28aManager;
    
    @Named
    @Produces
    @RequestScoped
    private Datos28a newDatos28a = new Datos28a();
    
    private String infoMessage;
    private boolean registered;
    
    @EJB
    private Ubica23ManagerLocal ubica23Manager;
    
    private List<Ubica23> ubica23List;
    
    private List<String> paises;
    private List<String> estados;
    private List<String> municipios;
    private List<String> ciudades;
    private List<String> colonias;
    
    private String rfc;
    
    @PostConstruct
    private void init() {
        this.registered = false;
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
    public String register() {
        
        try {
            UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);
            rfc = userSessionBean.getAspirante();
            
            newDatos28a.setRfdatA(rfc);
            
            newDatos28a.setStdatA(Constants.RECORD_ACTIVED);
            newDatos28a.setFedatA(new Date());
            newDatos28a.setUsdatA(FacesUtil.getUserName());
            
            datos28aManager.registerDatos28a(newDatos28a);
            
            infoMessage = "El registro se creo correctamente";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));
            this.registered = true;
            
        } catch (Datos28aAlreadyExists ex) {
            Logger.getLogger(Datos28aAddBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Los datos personales del aspirante ya se encuentran en la base de datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));
            
        } catch (EJBException ex) {
            @SuppressWarnings("ThrowableResultIgnored")
            Exception cause = ex.getCausedByException();
            if (cause instanceof ConstraintViolationException) {
                @SuppressWarnings("ThrowableResultIgnored")
                ConstraintViolationException cve = (ConstraintViolationException) ex.getCausedByException();
                for (Iterator<ConstraintViolation<?>> it = cve.getConstraintViolations().iterator(); it.hasNext();) {
                    ConstraintViolation<? extends Object> v = it.next();
                    System.err.println(v);
                    System.err.println("==>>" + v.getMessage());
                }
                Logger.getLogger(Datos28aAddBacking.class.getName()).log(Level.SEVERE, null, ex);
                
            }
            infoMessage = "Ocurrio un error en la base de datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", infoMessage));
        }
        
        return null;
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
                if (newDatos28a != null) {
                    newDatos28a.setPadatA(name);
                }
                getUbica23Estados(name);
                
                municipios = null;
                ciudades = null;
                colonias = null;
                break;
            case "estado":
                if (newDatos28a != null) {
                    newDatos28a.setEsdatA(name);
                    getUbica23Municipios(newDatos28a.getPadatA(), newDatos28a.getEsdatA());
                }
                ciudades = null;
                colonias = null;
                break;
            case "municipio":
                
                if (newDatos28a != null) {
                    newDatos28a.setMddatA(name);
                    getUbica23Ciudades(newDatos28a.getPadatA(), newDatos28a.getEsdatA(), newDatos28a.getMddatA());
                }
                colonias = null;
                break;
            case "ciudad":
                
                if (newDatos28a != null) {
                    newDatos28a.setLodatA(name);
                    getUbica23Colonias(newDatos28a.getPadatA(), newDatos28a.getEsdatA(), newDatos28a.getMddatA(), newDatos28a.getLodatA());
                }
                break;
            case "colonia":
                
                if (newDatos28a != null) {
                    newDatos28a.setCodatA(name);
                    getUbica23Cp(newDatos28a.getPadatA(), newDatos28a.getEsdatA(), newDatos28a.getMddatA(), newDatos28a.getLodatA(), newDatos28a.getCodatA());
                }
                break;
            case "codigopostal":
                
                if (newDatos28a != null) {
                    newDatos28a.setCpdatA(name);
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
        
        if (newDatos28a != null) {
            newDatos28a.setCpdatA(ubica23Manager.getListUbica23Cp(pais, estado, municipio, ciudad, colonia));
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
        
        if (newDatos28a != null) {
            newDatos28a.setPadatA(ubica23List.get(0).getPaubi());
            newDatos28a.setEsdatA(ubica23List.get(0).getEsubi());
            newDatos28a.setMddatA(ubica23List.get(0).getMdubi());
            newDatos28a.setLodatA(ubica23List.get(0).getLoubi());
            newDatos28a.setCodatA(ubica23List.get(0).getCoubi());
        }
    }
    
    /**
     *
     * @return
     */
    public String getRfc() {
        return rfc;
    }
    
    /**
     *
     * @param rfc
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
    
}
