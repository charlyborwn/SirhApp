package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Datos28a;
import com.hrm.sirhapp.model.Famil32a;
import com.hrm.sirhapp.model.Ubica23;
import com.hrm.sirhapp.service.Datos28aManagerLocal;
import com.hrm.sirhapp.service.Famil32aManagerLocal;
import com.hrm.sirhapp.service.Tidoc18ManagerLocal;
import com.hrm.sirhapp.service.Ubica23ManagerLocal;
import com.hrm.sirhapp.service.exception.Datos28aNotFound;
import com.hrm.sirhapp.service.exception.Famil32aAlreadyExists;
import com.hrm.sirhapp.service.exception.Famil32aNotFound;
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
public class Famil32aBackingBean extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Famil32aManagerLocal famil32aManager;
    @EJB
    private Ubica23ManagerLocal ubica23Manager;
    @EJB
    private Tidoc18ManagerLocal tidoc18Manager;
    @EJB
    private Datos28aManagerLocal datos28aManager;

    private String rfc;
    private String path;
    private boolean add;
    private String w1famA;
    private String tdfamA;
    private Integer famil32aTab;

    private String infoMessage;
    private String infoMessageModule;

    private Famil32a selectedFamil32aBean;

    private Famil32a newFamil32aBean = new Famil32a();

    private List<Famil32a> famil32aList;

    private List<Ubica23> ubica23ListFam;

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
        getListFamil32a();
    }

    /**
     *
     */
    public void getListFamil32a() {

        famil32aList = famil32aManager.getListFamil32a(rfc);
        System.out.println("se obtienen las familiars");

    }

    /**
     *
     */
    public void prepareSelectFamil32a() {
        getUbica23CpListFam(selectedFamil32aBean.getCpfamA());
    }

    /**
     *
     * @return
     */
    public Famil32a prepareCreateFamil32a() {
        getListFamil32a();

        newFamil32aBean = new Famil32a();
        newFamil32aBean.setRffamA(rfc);

        add = true;
        famil32aTab = 0;
        return newFamil32aBean;
    }

    /**
     *
     * @param event
     */
    public void prepareFileUploadFam(AjaxBehaviorEvent event) {

        String value = (String) ((ValueHolder) event.getSource()).getValue();
        String id = ((UIComponent) event.getSource()).getId();
        try {
            if (selectedFamil32aBean != null) {

                selectedFamil32aBean.setW1famA(tidoc18Manager.getTidoc18(value));

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
    public String registerFamil32a() {

        try {

            newFamil32aBean.setStfamA(Constants.RECORD_ACTIVED);
            newFamil32aBean.setFefamA(new Date());
            newFamil32aBean.setUsfamA(FacesUtil.getUserName());

            famil32aManager.registerFamil32a(newFamil32aBean);

            newFamil32aBean = new Famil32a();

            infoMessage = "El registro se creo correctamente";

            getListFamil32a();
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

            return "success";
        } catch (Famil32aAlreadyExists ex) {
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
    public String updateFamil32aFoto() {
        selectedFamil32aBean.setPtfamA(null);
        FacesUtil.removeManagedBeanInSession("fileUploadBean");
        String oldInfoMessageModule = infoMessageModule;
        infoMessageModule = "Documento de familiar";
        infoMessage = "Se borro el Documento de familiar";
        FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
        infoMessageModule = oldInfoMessageModule;

        return null;
    }

    /**
     *
     * @return
     */
    public String updateFamil32a() {
        try {

            if (path != null) {
                selectedFamil32aBean.setPtfamA(path);
                path = null;
            }

            selectedFamil32aBean.setStfamA(Constants.RECORD_ACTIVED);
            selectedFamil32aBean.setFefamA(new Date());
            selectedFamil32aBean.setUsfamA(FacesUtil.getUserName());

            famil32aManager.updateFamil32a(selectedFamil32aBean);

            selectedFamil32aBean = null;

            infoMessage = "El registro se actualizo correctamente";

            getListFamil32a();
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Famil32aNotFound ex) {
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
    public String deleteFamil32a() {
        try {

            selectedFamil32aBean.setStfamA(Constants.RECORD_DELETED);
            selectedFamil32aBean.setFefamA(new Date());
            selectedFamil32aBean.setUsfamA(FacesUtil.getUserName());

            famil32aManager.deleteFamil32a(selectedFamil32aBean);

            selectedFamil32aBean = null;

            infoMessage = "Experiencia eliminada correctamente";
            getListFamil32a();
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Famil32aNotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        }
        return null;

    }

    /**
     *
     */
    public void retrieveTraba36aToFamil32aDir() {

        if (selectedFamil32aBean != null) {
            if (selectedFamil32aBean.getVmfamA().equals("si")) {
                Datos28a datos28a = new Datos28a();
                try {
                    datos28a = datos28aManager.getDatos28a(rfc);
                } catch (Datos28aNotFound ex) {
                    Logger.getLogger(Benef26aBacking.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (datos28a != null) {
                    selectedFamil32aBean.setPafamA(datos28a.getPadatA());
                    selectedFamil32aBean.setEsfamA(datos28a.getEsdatA());
                    selectedFamil32aBean.setMdfamA(datos28a.getMddatA());
                    selectedFamil32aBean.setLofamA(datos28a.getLodatA());
                    selectedFamil32aBean.setCofamA(datos28a.getCodatA());
                    selectedFamil32aBean.setCafamA(datos28a.getCadatA());
                    selectedFamil32aBean.setCpfamA(datos28a.getCpdatA());
                    selectedFamil32aBean.setTdfamA(datos28a.getTddatA());
                    getUbica23CpList(datos28a.getPadatA());
                }
            } else {

                selectedFamil32aBean.setPafamA("");
                selectedFamil32aBean.setEsfamA("");
                selectedFamil32aBean.setMdfamA("");
                selectedFamil32aBean.setLofamA("");
                selectedFamil32aBean.setCofamA("");
                selectedFamil32aBean.setCafamA("");
                selectedFamil32aBean.setCpfamA("");
                selectedFamil32aBean.setTdfamA("");
            }
        }
    }

    /**
     *
     * @param rfbenA
     */
    public void getListFamil32a(String rfbenA) {

        famil32aList = famil32aManager.getListFamil32a(rfbenA);

    }

    /**
     *
     * @param vce
     */
    public void handleChange(AjaxBehaviorEvent vce) {
        String name = (String) ((UIOutput) vce.getSource()).getValue();
        String id = (String) ((UIOutput) vce.getSource()).getId();
        switch (id) {
            case "famsn_dir":
                retrieveTraba36aToFamil32aDir();
                break;

            case "paisfam":

                newFamil32aBean.setCpfamA(name);
                if (selectedFamil32aBean != null) {
                    selectedFamil32aBean.setPafamA(name);
                }
                getUbica23EstadosFam(name);

                municipios = null;
                ciudades = null;
                colonias = null;
                break;
            case "estadofam":
                if (add) {
                    newFamil32aBean.setEsfamA(name);
                    getUbica23MunicipiosFam(newFamil32aBean.getPafamA(), newFamil32aBean.getEsfamA());
                }
                if (selectedFamil32aBean != null) {
                    selectedFamil32aBean.setEsfamA(name);
                    getUbica23MunicipiosFam(selectedFamil32aBean.getPafamA(), selectedFamil32aBean.getEsfamA());
                }

                ciudades = null;
                colonias = null;
                break;
            case "municipiofam":
                if (add) {
                    newFamil32aBean.setMdfamA(name);
                    getUbica23CiudadesFam(newFamil32aBean.getPafamA(), newFamil32aBean.getEsfamA(), newFamil32aBean.getMdfamA());
                }
                if (selectedFamil32aBean != null) {
                    selectedFamil32aBean.setMdfamA(name);
                    getUbica23CiudadesFam(selectedFamil32aBean.getPafamA(), selectedFamil32aBean.getEsfamA(), selectedFamil32aBean.getMdfamA());
                }

                colonias = null;
                break;
            case "ciudadfam":

                if (add) {
                    newFamil32aBean.setLofamA(name);
                    getUbica23ColoniasFam(newFamil32aBean.getPafamA(), newFamil32aBean.getEsfamA(), newFamil32aBean.getMdfamA(), newFamil32aBean.getLofamA());
                }
                if (selectedFamil32aBean != null) {
                    selectedFamil32aBean.setLofamA(name);
                    getUbica23ColoniasFam(selectedFamil32aBean.getPafamA(), selectedFamil32aBean.getEsfamA(), selectedFamil32aBean.getMdfamA(), selectedFamil32aBean.getLofamA());
                }
                break;
            case "coloniafam":

                if (add) {
                    newFamil32aBean.setCofamA(name);
                    getUbica23CpFam(newFamil32aBean.getPafamA(), newFamil32aBean.getEsfamA(), newFamil32aBean.getMdfamA(), newFamil32aBean.getLofamA(), newFamil32aBean.getCofamA());
                }
                if (selectedFamil32aBean != null) {
                    selectedFamil32aBean.setCofamA(name);
                    getUbica23CpFam(selectedFamil32aBean.getPafamA(), selectedFamil32aBean.getEsfamA(), selectedFamil32aBean.getMdfamA(), selectedFamil32aBean.getLofamA(), selectedFamil32aBean.getCofamA());
                }
                break;
            case "codigopostalfam":

                if (selectedFamil32aBean != null) {
                    selectedFamil32aBean.setCpfamA(name);
                }

                if (name.indexOf("_", 0) == -1) {
                    getUbica23CpFam(name);
                } else {

                    ubica23ListFam = null;
                    String var = name.replace("_", "");
                    if (var.length() == 4 || var.length() == 0) {

                        getUbica23PaisesFam();

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
        ubica23ListFam = ubica23Manager.getListUbica23Cp(cpubi);

        if (ubica23ListFam.size() > 0) {
            actualizaUbiListFam();
        }

    }

    /**
     *
     */
    public void getUbica23PaisesFam() {

        paises = ubica23Manager.getListUbica23Paises();
        Collections.sort(paises, Collator.getInstance());

    }

    /**
     *
     * @param pais
     */
    public void getUbica23EstadosFam(String pais) {

        estados = ubica23Manager.getListUbica23Estados(pais);
        Collections.sort(estados, Collator.getInstance());

    }

    /**
     *
     * @param pais
     * @param estado
     */
    public void getUbica23MunicipiosFam(String pais, String estado) {

        municipios = ubica23Manager.getListUbica23Municipios(pais, estado);
        Collections.sort(municipios, Collator.getInstance());

    }

    /**
     *
     * @param pais
     * @param estado
     * @param municipio
     */
    public void getUbica23CiudadesFam(String pais, String estado, String municipio) {

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
    public void getUbica23ColoniasFam(String pais, String estado, String municipio, String ciudad) {

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
    public void getUbica23CpFam(String pais, String estado, String municipio, String ciudad, String colonia) {

        if (selectedFamil32aBean != null) {
            selectedFamil32aBean.setCpfamA(ubica23Manager.getListUbica23Cp(pais, estado, municipio, ciudad, colonia));
        }

    }

    /**
     *
     * @param cpubi
     */
    public void getUbica23CpFam(String cpubi) {
        ubica23ListFam = ubica23Manager.getListUbica23Cp(cpubi);

        if (ubica23ListFam.size() > 0) {
            actualizaUbiFam();
        }

    }

    /**
     *
     * @param cpubi
     */
    public void getUbica23CpListFam(String cpubi) {
        ubica23ListFam = ubica23Manager.getListUbica23Cp(cpubi);

        if (ubica23ListFam.size() > 0) {
            actualizaUbiListFam();
        }

    }

    /**
     *
     */
    public void actualizaUbiListFam() {

        getUbica23PaisesFam();

        getUbica23EstadosFam(ubica23ListFam.get(0).getPaubi());

        getUbica23MunicipiosFam(ubica23ListFam.get(0).getPaubi(), ubica23ListFam.get(0).getEsubi());

        getUbica23CiudadesFam(ubica23ListFam.get(0).getPaubi(), ubica23ListFam.get(0).getEsubi(), ubica23ListFam.get(0).getMdubi());

        getUbica23ColoniasFam(ubica23ListFam.get(0).getPaubi(), ubica23ListFam.get(0).getEsubi(), ubica23ListFam.get(0).getMdubi(), ubica23ListFam.get(0).getLoubi());

    }

    /**
     *
     */
    public void actualizaUbiFam() {

        getUbica23PaisesFam();

        getUbica23EstadosFam(ubica23ListFam.get(0).getPaubi());

        getUbica23MunicipiosFam(ubica23ListFam.get(0).getPaubi(), ubica23ListFam.get(0).getEsubi());

        getUbica23CiudadesFam(ubica23ListFam.get(0).getPaubi(), ubica23ListFam.get(0).getEsubi(), ubica23ListFam.get(0).getMdubi());

        getUbica23ColoniasFam(ubica23ListFam.get(0).getPaubi(), ubica23ListFam.get(0).getEsubi(), ubica23ListFam.get(0).getMdubi(), ubica23ListFam.get(0).getLoubi());

        if (selectedFamil32aBean != null) {
            selectedFamil32aBean.setPafamA(ubica23ListFam.get(0).getPaubi());
            selectedFamil32aBean.setEsfamA(ubica23ListFam.get(0).getEsubi());
            selectedFamil32aBean.setMdfamA(ubica23ListFam.get(0).getMdubi());
            selectedFamil32aBean.setLofamA(ubica23ListFam.get(0).getLoubi());
            selectedFamil32aBean.setCofamA(ubica23ListFam.get(0).getCoubi());
        }

    }

    /**
     *
     * @param event
     */
    public void onTabChangeFamil32a(TabChangeEvent event) {
        TabView tabView = (TabView) event.getComponent();

        famil32aTab = tabView.getChildren().indexOf(event.getTab());
        System.out.println("tab actual famil:" + famil32aTab);

    }

    /**
     *
     * @return
     */
    public Famil32aManagerLocal getFamil32aManager() {
        return famil32aManager;
    }

    /**
     *
     * @param famil32aManager
     */
    public void setFamil32aManager(Famil32aManagerLocal famil32aManager) {
        this.famil32aManager = famil32aManager;
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
    public String getW1famA() {
        return w1famA;
    }

    /**
     *
     * @param w1famA
     */
    public void setW1famA(String w1famA) {
        this.w1famA = w1famA;
    }

    /**
     *
     * @return
     */
    public String getTdfamA() {
        return tdfamA;
    }

    /**
     *
     * @param tdfamA
     */
    public void setTdfamA(String tdfamA) {
        this.tdfamA = tdfamA;
    }

    /**
     *
     * @return
     */
    public Integer getFamil32aTab() {
        return famil32aTab;
    }

    /**
     *
     * @param famil32aTab
     */
    public void setFamil32aTab(Integer famil32aTab) {
        this.famil32aTab = famil32aTab;
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
    public Famil32a getSelectedFamil32aBean() {
        return selectedFamil32aBean;
    }

    /**
     *
     * @param selectedFamil32aBean
     */
    public void setSelectedFamil32aBean(Famil32a selectedFamil32aBean) {
        this.selectedFamil32aBean = selectedFamil32aBean;
    }

    /**
     *
     * @return
     */
    public Famil32a getNewFamil32aBean() {
        return newFamil32aBean;
    }

    /**
     *
     * @param newFamil32aBean
     */
    public void setNewFamil32aBean(Famil32a newFamil32aBean) {
        this.newFamil32aBean = newFamil32aBean;
    }

    /**
     *
     * @return
     */
    public List<Famil32a> getFamil32aList() {
        return famil32aList;
    }

    /**
     *
     * @param famil32aList
     */
    public void setFamil32aList(List<Famil32a> famil32aList) {
        this.famil32aList = famil32aList;
    }

    /**
     *
     * @return
     */
    public List<Ubica23> getUbica23ListFam() {
        return ubica23ListFam;
    }

    /**
     *
     * @param ubica23ListFam
     */
    public void setUbica23ListFam(List<Ubica23> ubica23ListFam) {
        this.ubica23ListFam = ubica23ListFam;
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
