package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Exper31a;
import com.hrm.sirhapp.model.Ubica23;
import com.hrm.sirhapp.service.Exper31aManagerLocal;
import com.hrm.sirhapp.service.Tidoc18ManagerLocal;
import com.hrm.sirhapp.service.Ubica23ManagerLocal;
import com.hrm.sirhapp.service.exception.Exper31aAlreadyExists;
import com.hrm.sirhapp.service.exception.Exper31aNotFound;
import com.hrm.sirhapp.service.exception.Tidoc18NotFound;
import com.hrm.sirhapp.util.FacesUtil;
import com.hrm.sirhapp.util.LanguagesUtil;
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
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.ValueHolder;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@ViewScoped
public class Exper31aBackingBean extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Exper31aManagerLocal exper31aManager;
    @EJB
    private Ubica23ManagerLocal ubica23Manager;
    @EJB
    private Tidoc18ManagerLocal tidoc18Manager;

    private String rfc;
    private String path;
    private boolean add;
    private String w1expA;
    private String tdexpA;
    private Integer exper31aTab;

    private String infoMessage;
    private String infoMessageModule;

    private Exper31a selectedExper31aBean;

    private Exper31a newExper31aBean = new Exper31a();

    private List<Exper31a> exper31aList;

    private List<Ubica23> ubica23ListExp;

    private List<String> paises;
    private List<String> estados;
    private List<String> municipios;
    private List<String> ciudades;
    private List<String> colonias;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Experiencia";
        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);
        rfc = userSessionBean.getAspirante();
        getListExper31a();
    }

    /**
     *
     */
    public void getListExper31a() {

        exper31aList = exper31aManager.getListExper31a(rfc);
        System.out.println("se obtienen las experiencias");

    }

    /**
     *
     */
    public void prepareSelectExper31a() {
        getUbica23CpListExp(selectedExper31aBean.getCpexpA());
    }

    /**
     *
     * @return
     */
    public Exper31a prepareCreateExper31a() {
        getListExper31a();

        newExper31aBean = new Exper31a();
        newExper31aBean.setRfexpA(rfc);

        add = true;
        exper31aTab = 0;
        return newExper31aBean;
    }

    /**
     *
     * @param event
     */
    public void prepareFileUploadExp(AjaxBehaviorEvent event) {

        String value = (String) ((ValueHolder) event.getSource()).getValue();
        String id = ((UIComponent) event.getSource()).getId();
        try {
            if (selectedExper31aBean != null) {

                selectedExper31aBean.setW1expA(tidoc18Manager.getTidoc18(value));

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
    public String registerExper31a() {

        try {

            newExper31aBean.setStexpA(Constants.RECORD_ACTIVED);
            newExper31aBean.setFeexpA(new Date());
            newExper31aBean.setUsexpA(FacesUtil.getUserName());

            exper31aManager.registerExper31a(newExper31aBean);

            newExper31aBean = new Exper31a();

            infoMessage = "El registro se creo correctamente";

            getListExper31a();
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

            return "success";
        } catch (Exper31aAlreadyExists ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Los datos personales del aspirante ya se encuentran en la base de datos";
            FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR, infoMessageModule, infoMessage);
        } catch (EJBException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String updateExper31aFoto() {
        selectedExper31aBean.setPtexpA(null);
        FacesUtil.removeManagedBeanInSession("fileUploadBean");
        String oldInfoMessageModule = infoMessageModule;
        infoMessageModule = "Documento de experiencia";
        infoMessage = "Se borro el Documento de experiencia";
        FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
        infoMessageModule = oldInfoMessageModule;

        return null;
    }

    /**
     *
     * @return
     */
    public String updateExper31a() {
        try {

            if (path != null) {
                selectedExper31aBean.setPtexpA(path);
                path = null;
            }

            selectedExper31aBean.setStexpA(Constants.RECORD_ACTIVED);
            selectedExper31aBean.setFeexpA(new Date());
            selectedExper31aBean.setUsexpA(FacesUtil.getUserName());

            exper31aManager.updateExper31a(selectedExper31aBean);

            selectedExper31aBean = null;

            infoMessage = "El registro se actualizo correctamente";

            getListExper31a();
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Exper31aNotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        }
        return null;

    }

    /**
     *
     * @return
     */
    public String deleteExper31a() {
        try {

            selectedExper31aBean.setStexpA(Constants.RECORD_DELETED);
            selectedExper31aBean.setFeexpA(new Date());
            selectedExper31aBean.setUsexpA(FacesUtil.getUserName());

            exper31aManager.deleteExper31a(selectedExper31aBean);

            selectedExper31aBean = null;

            infoMessage = "Experiencia eliminada correctamente";
            getListExper31a();
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Exper31aNotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        }
        return null;

    }

    /**
     *
     * @param rfbenA
     */
    public void getListExper31a(String rfbenA) {

        exper31aList = exper31aManager.getListExper31a(rfbenA);

    }

    /**
     *
     * @param vce
     */
    public void handleChange(AjaxBehaviorEvent vce) {
        String name = (String) ((UIOutput) vce.getSource()).getValue();
        String id = (String) ((UIOutput) vce.getSource()).getId();
        switch (id) {
            case "paisexp":

                newExper31aBean.setCpexpA(name);
                if (selectedExper31aBean != null) {
                    selectedExper31aBean.setPaexpA(name);
                }
                getUbica23EstadosExp(name);

                municipios = null;
                ciudades = null;
                colonias = null;
                break;
            case "estadoexp":
                if (add) {
                    newExper31aBean.setEsexpA(name);
                    getUbica23MunicipiosExp(newExper31aBean.getPaexpA(), newExper31aBean.getEsexpA());
                }
                if (selectedExper31aBean != null) {
                    selectedExper31aBean.setEsexpA(name);
                    getUbica23MunicipiosExp(selectedExper31aBean.getPaexpA(), selectedExper31aBean.getEsexpA());
                }

                ciudades = null;
                colonias = null;
                break;
            case "municipioexp":
                if (add) {
                    newExper31aBean.setMdexpA(name);
                    getUbica23CiudadesExp(newExper31aBean.getPaexpA(), newExper31aBean.getEsexpA(), newExper31aBean.getMdexpA());
                }
                if (selectedExper31aBean != null) {
                    selectedExper31aBean.setMdexpA(name);
                    getUbica23CiudadesExp(selectedExper31aBean.getPaexpA(), selectedExper31aBean.getEsexpA(), selectedExper31aBean.getMdexpA());
                }

                colonias = null;
                break;
            case "ciudadexp":

                if (add) {
                    newExper31aBean.setLoexpA(name);
                    getUbica23ColoniasExp(newExper31aBean.getPaexpA(), newExper31aBean.getEsexpA(), newExper31aBean.getMdexpA(), newExper31aBean.getLoexpA());
                }
                if (selectedExper31aBean != null) {
                    selectedExper31aBean.setLoexpA(name);
                    getUbica23ColoniasExp(selectedExper31aBean.getPaexpA(), selectedExper31aBean.getEsexpA(), selectedExper31aBean.getMdexpA(), selectedExper31aBean.getLoexpA());
                }
                break;
            case "coloniaexp":

                if (add) {
                    newExper31aBean.setCoexpA(name);
                    getUbica23CpExp(newExper31aBean.getPaexpA(), newExper31aBean.getEsexpA(), newExper31aBean.getMdexpA(), newExper31aBean.getLoexpA(), newExper31aBean.getCoexpA());
                }
                if (selectedExper31aBean != null) {
                    selectedExper31aBean.setCoexpA(name);
                    getUbica23CpExp(selectedExper31aBean.getPaexpA(), selectedExper31aBean.getEsexpA(), selectedExper31aBean.getMdexpA(), selectedExper31aBean.getLoexpA(), selectedExper31aBean.getCoexpA());
                }
                break;
            case "codigopostalexp":

                if (selectedExper31aBean != null) {
                    selectedExper31aBean.setCpexpA(name);
                }

                if (name.indexOf("_", 0) == -1) {
                    getUbica23CpExp(name);
                } else {

                    ubica23ListExp = null;
                    String var = name.replace("_", "");
                    if (var.length() == 4 || var.length() == 0) {

                        getUbica23PaisesExp();

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
     * @param cpubi
     */
    public void getUbica23CpList(String cpubi) {
        ubica23ListExp = ubica23Manager.getListUbica23Cp(cpubi);

        if (ubica23ListExp.size() > 0) {
            actualizaUbiListExp();
        }

    }

    /**
     *
     */
    public void getUbica23PaisesExp() {

        paises = ubica23Manager.getListUbica23Paises();
        Collections.sort(paises, Collator.getInstance());

    }

    /**
     *
     * @param pais
     */
    public void getUbica23EstadosExp(String pais) {

        estados = ubica23Manager.getListUbica23Estados(pais);
        Collections.sort(estados, Collator.getInstance());

    }

    /**
     *
     * @param pais
     * @param estado
     */
    public void getUbica23MunicipiosExp(String pais, String estado) {

        municipios = ubica23Manager.getListUbica23Municipios(pais, estado);
        Collections.sort(municipios, Collator.getInstance());

    }

    /**
     *
     * @param pais
     * @param estado
     * @param municipio
     */
    public void getUbica23CiudadesExp(String pais, String estado, String municipio) {

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
    public void getUbica23ColoniasExp(String pais, String estado, String municipio, String ciudad) {

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
    public void getUbica23CpExp(String pais, String estado, String municipio, String ciudad, String colonia) {

        if (selectedExper31aBean != null) {
            selectedExper31aBean.setCpexpA(ubica23Manager.getListUbica23Cp(pais, estado, municipio, ciudad, colonia));
        }

    }

    /**
     *
     * @param cpubi
     */
    public void getUbica23CpExp(String cpubi) {
        ubica23ListExp = ubica23Manager.getListUbica23Cp(cpubi);

        if (ubica23ListExp.size() > 0) {
            actualizaUbiExp();
        }

    }

    /**
     *
     * @param cpubi
     */
    public void getUbica23CpListExp(String cpubi) {
        ubica23ListExp = ubica23Manager.getListUbica23Cp(cpubi);

        if (ubica23ListExp.size() > 0) {
            actualizaUbiListExp();
        }

    }

    /**
     *
     */
    public void actualizaUbiListExp() {

        getUbica23PaisesExp();

        getUbica23EstadosExp(ubica23ListExp.get(0).getPaubi());

        getUbica23MunicipiosExp(ubica23ListExp.get(0).getPaubi(), ubica23ListExp.get(0).getEsubi());

        getUbica23CiudadesExp(ubica23ListExp.get(0).getPaubi(), ubica23ListExp.get(0).getEsubi(), ubica23ListExp.get(0).getMdubi());

        getUbica23ColoniasExp(ubica23ListExp.get(0).getPaubi(), ubica23ListExp.get(0).getEsubi(), ubica23ListExp.get(0).getMdubi(), ubica23ListExp.get(0).getLoubi());

    }

    /**
     *
     */
    public void actualizaUbiExp() {

        getUbica23PaisesExp();

        getUbica23EstadosExp(ubica23ListExp.get(0).getPaubi());

        getUbica23MunicipiosExp(ubica23ListExp.get(0).getPaubi(), ubica23ListExp.get(0).getEsubi());

        getUbica23CiudadesExp(ubica23ListExp.get(0).getPaubi(), ubica23ListExp.get(0).getEsubi(), ubica23ListExp.get(0).getMdubi());

        getUbica23ColoniasExp(ubica23ListExp.get(0).getPaubi(), ubica23ListExp.get(0).getEsubi(), ubica23ListExp.get(0).getMdubi(), ubica23ListExp.get(0).getLoubi());

        if (selectedExper31aBean != null) {
            selectedExper31aBean.setPaexpA(ubica23ListExp.get(0).getPaubi());
            selectedExper31aBean.setEsexpA(ubica23ListExp.get(0).getEsubi());
            selectedExper31aBean.setMdexpA(ubica23ListExp.get(0).getMdubi());
            selectedExper31aBean.setLoexpA(ubica23ListExp.get(0).getLoubi());
            selectedExper31aBean.setCoexpA(ubica23ListExp.get(0).getCoubi());
        }

    }

    /**
     *
     * @param event
     */
    public void onTabChangeExper31a(TabChangeEvent event) {
        TabView tabView = (TabView) event.getComponent();

        exper31aTab = tabView.getChildren().indexOf(event.getTab());
        System.out.println("tab actual exper:" + exper31aTab);

    }

    /**
     *
     * @return
     */
    public Exper31aManagerLocal getExper31aManager() {
        return exper31aManager;
    }

    /**
     *
     * @param exper31aManager
     */
    public void setExper31aManager(Exper31aManagerLocal exper31aManager) {
        this.exper31aManager = exper31aManager;
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
    public boolean isAdd() {
        return add;
    }

    /**
     *
     * @param add
     */
    public void setAdd(boolean add) {
        this.add = add;
    }

    /**
     *
     * @return
     */
    public String getW1expA() {
        return w1expA;
    }

    /**
     *
     * @param w1expA
     */
    public void setW1expA(String w1expA) {
        this.w1expA = w1expA;
    }

    /**
     *
     * @return
     */
    public String getTdexpA() {
        return tdexpA;
    }

    /**
     *
     * @param tdexpA
     */
    public void setTdexpA(String tdexpA) {
        this.tdexpA = tdexpA;
    }

    /**
     *
     * @return
     */
    public Integer getExper31aTab() {
        return exper31aTab;
    }

    /**
     *
     * @param exper31aTab
     */
    public void setExper31aTab(Integer exper31aTab) {
        this.exper31aTab = exper31aTab;
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
    public Exper31a getSelectedExper31aBean() {
        return selectedExper31aBean;
    }

    /**
     *
     * @param selectedExper31aBean
     */
    public void setSelectedExper31aBean(Exper31a selectedExper31aBean) {
        this.selectedExper31aBean = selectedExper31aBean;
    }

    /**
     *
     * @return
     */
    public Exper31a getNewExper31aBean() {
        return newExper31aBean;
    }

    /**
     *
     * @param newExper31aBean
     */
    public void setNewExper31aBean(Exper31a newExper31aBean) {
        this.newExper31aBean = newExper31aBean;
    }

    /**
     *
     * @return
     */
    public List<Exper31a> getExper31aList() {
        return exper31aList;
    }

    /**
     *
     * @param exper31aList
     */
    public void setExper31aList(List<Exper31a> exper31aList) {
        this.exper31aList = exper31aList;
    }

    /**
     *
     * @return
     */
    public List<Ubica23> getUbica23ListExp() {
        return ubica23ListExp;
    }

    /**
     *
     * @param ubica23ListExp
     */
    public void setUbica23ListExp(List<Ubica23> ubica23ListExp) {
        this.ubica23ListExp = ubica23ListExp;
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

}
