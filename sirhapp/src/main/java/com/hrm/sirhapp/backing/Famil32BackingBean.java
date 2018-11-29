package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Datos28;
import com.hrm.sirhapp.model.Famil32;
import com.hrm.sirhapp.model.Traba36;
import com.hrm.sirhapp.model.Ubica23;
import com.hrm.sirhapp.service.Datos28ManagerLocal;
import com.hrm.sirhapp.service.Famil32ManagerLocal;
import com.hrm.sirhapp.service.Tidoc18ManagerLocal;
import com.hrm.sirhapp.service.Ubica23ManagerLocal;
import com.hrm.sirhapp.service.exception.Datos28NotFound;
import com.hrm.sirhapp.service.exception.Famil32AlreadyExists;
import com.hrm.sirhapp.service.exception.Famil32NotFound;
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
public class Famil32BackingBean extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Famil32ManagerLocal famil32Manager;
    @EJB
    private Ubica23ManagerLocal ubica23Manager;
    @EJB
    private Tidoc18ManagerLocal tidoc18Manager;
    @EJB
    private Datos28ManagerLocal datos28Manager;

    private String rfc;
    private Integer nt;
    private String path;
    private boolean add;
    private String w1fam;
    private String tdfam;
    private Integer famil32Tab;

    private String infoMessage;
    private String infoMessageModule;

    private Famil32 selectedFamil32Bean;
    private Traba36 selectedTraba36Bean;

    private Famil32 newFamil32Bean = new Famil32();

    private List<Famil32> famil32List;

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
        nt = userSessionBean.getTrabajador();

        getListFamil32();
    }

    /**
     *
     */
    public void getListFamil32() {

        famil32List = famil32Manager.getListFamil32(nt);
        System.out.println("se obtienen las familiars");

    }

    /**
     *
     */
    public void prepareSelectFamil32() {
        getUbica23CpListFam(selectedFamil32Bean.getCpfam());
    }

    /**
     *
     * @return
     */
    public Famil32 prepareCreateFamil32() {
        getListFamil32();

        newFamil32Bean = new Famil32();
        newFamil32Bean.setNtfam(nt);

        add = true;
        famil32Tab = 0;
        return newFamil32Bean;
    }

    /**
     *
     * @param event
     */
    public void prepareFileUploadFam(AjaxBehaviorEvent event) {

        String value = (String) ((ValueHolder) event.getSource()).getValue();
        String id = ((UIComponent) event.getSource()).getId();
        try {
            if (selectedFamil32Bean != null) {

                selectedFamil32Bean.setW1fam(tidoc18Manager.getTidoc18(value));

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
    public String registerFamil32() {

        try {

            newFamil32Bean.setStfam(Constants.RECORD_ACTIVED);
            newFamil32Bean.setFefam(new Date());
            newFamil32Bean.setUsfam(FacesUtil.getUserName());

            famil32Manager.registerFamil32(newFamil32Bean);

            newFamil32Bean = new Famil32();

            infoMessage = "El registro se creo correctamente";

            getListFamil32();
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

            return "success";
        } catch (Famil32AlreadyExists ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Los datos personales del trabajador ya se encuentran en la base de datos";
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
    public String updateFamil32Foto() {
        selectedFamil32Bean.setPtfam(null);
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
    public String updateFamil32() {
        try {

            if (path != null) {
                selectedFamil32Bean.setPtfam(path);
                path = null;
            }

            selectedFamil32Bean.setStfam(Constants.RECORD_ACTIVED);
            selectedFamil32Bean.setFefam(new Date());
            selectedFamil32Bean.setUsfam(FacesUtil.getUserName());

            famil32Manager.updateFamil32(selectedFamil32Bean);

            selectedFamil32Bean = null;

            infoMessage = "El registro se actualizo correctamente";

            getListFamil32();
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Famil32NotFound ex) {
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
    public String deleteFamil32() {
        try {

            selectedFamil32Bean.setStfam(Constants.RECORD_DELETED);
            selectedFamil32Bean.setFefam(new Date());
            selectedFamil32Bean.setUsfam(FacesUtil.getUserName());

            famil32Manager.deleteFamil32(selectedFamil32Bean);

            selectedFamil32Bean = null;

            infoMessage = "Experiencia eliminada correctamente";
            getListFamil32();
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Famil32NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        }
        return null;

    }

    /**
     *
     */
    public void retrieveTraba36aToFamil32Dir() {

        if (selectedFamil32Bean != null) {
            if (selectedFamil32Bean.getVmfam().equals("si")) {

                Datos28 datos28 = new Datos28();
                try {
                    datos28 = datos28Manager.getDatos28(nt);
                } catch (Datos28NotFound ex) {
                    Logger.getLogger(Benef26Backing.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (datos28 != null) {
                    selectedFamil32Bean.setPafam(datos28.getPadat());
                    selectedFamil32Bean.setEsfam(datos28.getEsdat());
                    selectedFamil32Bean.setMdfam(datos28.getMddat());
                    selectedFamil32Bean.setLofam(datos28.getLodat());
                    selectedFamil32Bean.setCofam(datos28.getCodat());
                    selectedFamil32Bean.setCafam(datos28.getCadat());
                    selectedFamil32Bean.setCpfam(datos28.getCpdat());
                    selectedFamil32Bean.setTdfam(datos28.getTddat());
                    getUbica23CpList(datos28.getPadat());
                }
            } else {

                selectedFamil32Bean.setPafam("");
                selectedFamil32Bean.setEsfam("");
                selectedFamil32Bean.setMdfam("");
                selectedFamil32Bean.setLofam("");
                selectedFamil32Bean.setCofam("");
                selectedFamil32Bean.setCafam("");
                selectedFamil32Bean.setCpfam("");
                selectedFamil32Bean.setTdfam("");
            }
        }
    }

    /**
     *
     * @param rfbenA
     */
    public void getListFamil32(String rfbenA) {

        famil32List = famil32Manager.getListFamil32(nt);

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
                retrieveTraba36aToFamil32Dir();
                break;

            case "paisfam":

                newFamil32Bean.setCpfam(name);
                if (selectedFamil32Bean != null) {
                    selectedFamil32Bean.setPafam(name);
                }
                getUbica23EstadosFam(name);

                municipios = null;
                ciudades = null;
                colonias = null;
                break;
            case "estadofam":
                if (add) {
                    newFamil32Bean.setEsfam(name);
                    getUbica23MunicipiosFam(newFamil32Bean.getPafam(), newFamil32Bean.getEsfam());
                }
                if (selectedFamil32Bean != null) {
                    selectedFamil32Bean.setEsfam(name);
                    getUbica23MunicipiosFam(selectedFamil32Bean.getPafam(), selectedFamil32Bean.getEsfam());
                }

                ciudades = null;
                colonias = null;
                break;
            case "municipiofam":
                if (add) {
                    newFamil32Bean.setMdfam(name);
                    getUbica23CiudadesFam(newFamil32Bean.getPafam(), newFamil32Bean.getEsfam(), newFamil32Bean.getMdfam());
                }
                if (selectedFamil32Bean != null) {
                    selectedFamil32Bean.setMdfam(name);
                    getUbica23CiudadesFam(selectedFamil32Bean.getPafam(), selectedFamil32Bean.getEsfam(), selectedFamil32Bean.getMdfam());
                }

                colonias = null;
                break;
            case "ciudadfam":

                if (add) {
                    newFamil32Bean.setLofam(name);
                    getUbica23ColoniasFam(newFamil32Bean.getPafam(), newFamil32Bean.getEsfam(), newFamil32Bean.getMdfam(), newFamil32Bean.getLofam());
                }
                if (selectedFamil32Bean != null) {
                    selectedFamil32Bean.setLofam(name);
                    getUbica23ColoniasFam(selectedFamil32Bean.getPafam(), selectedFamil32Bean.getEsfam(), selectedFamil32Bean.getMdfam(), selectedFamil32Bean.getLofam());
                }
                break;
            case "coloniafam":

                if (add) {
                    newFamil32Bean.setCofam(name);
                    getUbica23CpFam(newFamil32Bean.getPafam(), newFamil32Bean.getEsfam(), newFamil32Bean.getMdfam(), newFamil32Bean.getLofam(), newFamil32Bean.getCofam());
                }
                if (selectedFamil32Bean != null) {
                    selectedFamil32Bean.setCofam(name);
                    getUbica23CpFam(selectedFamil32Bean.getPafam(), selectedFamil32Bean.getEsfam(), selectedFamil32Bean.getMdfam(), selectedFamil32Bean.getLofam(), selectedFamil32Bean.getCofam());
                }
                break;
            case "codigopostalfam":

                if (selectedFamil32Bean != null) {
                    selectedFamil32Bean.setCpfam(name);
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

        if (selectedFamil32Bean != null) {
            selectedFamil32Bean.setCpfam(ubica23Manager.getListUbica23Cp(pais, estado, municipio, ciudad, colonia));
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

        if (selectedFamil32Bean != null) {
            selectedFamil32Bean.setPafam(ubica23ListFam.get(0).getPaubi());
            selectedFamil32Bean.setEsfam(ubica23ListFam.get(0).getEsubi());
            selectedFamil32Bean.setMdfam(ubica23ListFam.get(0).getMdubi());
            selectedFamil32Bean.setLofam(ubica23ListFam.get(0).getLoubi());
            selectedFamil32Bean.setCofam(ubica23ListFam.get(0).getCoubi());
        }

    }

    /**
     *
     * @param event
     */
    public void onTabChangeFamil32(TabChangeEvent event) {
        TabView tabView = (TabView) event.getComponent();

        famil32Tab = tabView.getChildren().indexOf(event.getTab());
        System.out.println("tab actual famil:" + famil32Tab);

    }

    /**
     *
     * @return
     */
    public Famil32ManagerLocal getFamil32Manager() {
        return famil32Manager;
    }

    /**
     *
     * @param famil32Manager
     */
    public void setFamil32Manager(Famil32ManagerLocal famil32Manager) {
        this.famil32Manager = famil32Manager;
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
    public String getW1fam() {
        return w1fam;
    }

    /**
     *
     * @param w1fam
     */
    public void setW1fam(String w1fam) {
        this.w1fam = w1fam;
    }

    /**
     *
     * @return
     */
    public String getTdfam() {
        return tdfam;
    }

    /**
     *
     * @param tdfam
     */
    public void setTdfam(String tdfam) {
        this.tdfam = tdfam;
    }

    /**
     *
     * @return
     */
    public Integer getFamil32Tab() {
        return famil32Tab;
    }

    /**
     *
     * @param famil32Tab
     */
    public void setFamil32Tab(Integer famil32Tab) {
        this.famil32Tab = famil32Tab;
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
    public Famil32 getSelectedFamil32Bean() {
        return selectedFamil32Bean;
    }

    /**
     *
     * @param selectedFamil32Bean
     */
    public void setSelectedFamil32Bean(Famil32 selectedFamil32Bean) {
        this.selectedFamil32Bean = selectedFamil32Bean;
    }

    /**
     *
     * @return
     */
    public Famil32 getNewFamil32Bean() {
        return newFamil32Bean;
    }

    /**
     *
     * @param newFamil32Bean
     */
    public void setNewFamil32Bean(Famil32 newFamil32Bean) {
        this.newFamil32Bean = newFamil32Bean;
    }

    /**
     *
     * @return
     */
    public List<Famil32> getFamil32List() {
        return famil32List;
    }

    /**
     *
     * @param famil32List
     */
    public void setFamil32List(List<Famil32> famil32List) {
        this.famil32List = famil32List;
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

    /**
     *
     * @return
     */
    public Integer getNt() {
        return nt;
    }

    /**
     *
     * @param nt
     */
    public void setNt(Integer nt) {
        this.nt = nt;
    }

}
