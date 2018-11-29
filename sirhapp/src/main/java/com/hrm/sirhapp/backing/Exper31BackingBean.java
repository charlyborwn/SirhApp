package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Exper31;
import com.hrm.sirhapp.model.Ubica23;
import com.hrm.sirhapp.service.Exper31ManagerLocal;
import com.hrm.sirhapp.service.Tidoc18ManagerLocal;
import com.hrm.sirhapp.service.Ubica23ManagerLocal;
import com.hrm.sirhapp.service.exception.Exper31AlreadyExists;
import com.hrm.sirhapp.service.exception.Exper31NotFound;
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
public class Exper31BackingBean extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Exper31ManagerLocal exper31Manager;
    @EJB
    private Ubica23ManagerLocal ubica23Manager;
    @EJB
    private Tidoc18ManagerLocal tidoc18Manager;

    private String rfc;
    private Integer nt;
    private String path;
    private boolean add;
    private String w1exp;
    private String tdexp;
    private Integer exper31Tab;

    private String infoMessage;
    private String infoMessageModule;

    private Exper31 selectedExper31Bean;

    private Exper31 newExper31Bean = new Exper31();

    private List<Exper31> exper31List;

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
        nt = userSessionBean.getTrabajador();
        getListExper31();
    }

    /**
     *
     */
    public void getListExper31() {

        exper31List = exper31Manager.getListExper31(nt);
        System.out.println("se obtienen las experiencias");

    }

    /**
     *
     */
    public void prepareSelectExper31() {
        getUbica23CpListExp(selectedExper31Bean.getCpexp());
    }

    /**
     *
     * @return
     */
    public Exper31 prepareCreateExper31() {
        getListExper31();

        newExper31Bean = new Exper31();
        newExper31Bean.setNtexp(nt);

        add = true;
        exper31Tab = 0;
        return newExper31Bean;
    }

    /**
     *
     * @param event
     */
    public void prepareFileUploadExp(AjaxBehaviorEvent event) {

        String value = (String) ((ValueHolder) event.getSource()).getValue();
        String id = ((UIComponent) event.getSource()).getId();
        try {
            if (selectedExper31Bean != null) {

                selectedExper31Bean.setW1exp(tidoc18Manager.getTidoc18(value));

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
    public String registerExper31() {

        try {

            newExper31Bean.setStexp(Constants.RECORD_ACTIVED);
            newExper31Bean.setFeexp(new Date());
            newExper31Bean.setUsexp(FacesUtil.getUserName());

            exper31Manager.registerExper31(newExper31Bean);

            newExper31Bean = new Exper31();

            infoMessage = "El registro se creo correctamente";

            getListExper31();
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

            return "success";
        } catch (Exper31AlreadyExists ex) {
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
    public String updateExper31Foto() {
        selectedExper31Bean.setPtexp(null);
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
    public String updateExper31() {
        try {

            if (path != null) {
                selectedExper31Bean.setPtexp(path);
                path = null;
            }

            selectedExper31Bean.setStexp(Constants.RECORD_ACTIVED);
            selectedExper31Bean.setFeexp(new Date());
            selectedExper31Bean.setUsexp(FacesUtil.getUserName());

            exper31Manager.updateExper31(selectedExper31Bean);

            selectedExper31Bean = null;

            infoMessage = "El registro se actualizo correctamente";

            getListExper31();
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Exper31NotFound ex) {
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
    public String deleteExper31() {
        try {

            selectedExper31Bean.setStexp(Constants.RECORD_DELETED);
            selectedExper31Bean.setFeexp(new Date());
            selectedExper31Bean.setUsexp(FacesUtil.getUserName());

            exper31Manager.deleteExper31(selectedExper31Bean);

            selectedExper31Bean = null;

            infoMessage = "Experiencia eliminada correctamente";
            getListExper31();
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Exper31NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        }
        return null;

    }

    /**
     *
     * @param nt
     */
    public void getListExper31(Integer nt) {

        exper31List = exper31Manager.getListExper31(nt);

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

                newExper31Bean.setCpexp(name);
                if (selectedExper31Bean != null) {
                    selectedExper31Bean.setPaexp(name);
                }
                getUbica23EstadosExp(name);

                municipios = null;
                ciudades = null;
                colonias = null;
                break;
            case "estadoexp":
                if (add) {
                    newExper31Bean.setEsexp(name);
                    getUbica23MunicipiosExp(newExper31Bean.getPaexp(), newExper31Bean.getEsexp());
                }
                if (selectedExper31Bean != null) {
                    selectedExper31Bean.setEsexp(name);
                    getUbica23MunicipiosExp(selectedExper31Bean.getPaexp(), selectedExper31Bean.getEsexp());
                }

                ciudades = null;
                colonias = null;
                break;
            case "municipioexp":
                if (add) {
                    newExper31Bean.setMdexp(name);
                    getUbica23CiudadesExp(newExper31Bean.getPaexp(), newExper31Bean.getEsexp(), newExper31Bean.getMdexp());
                }
                if (selectedExper31Bean != null) {
                    selectedExper31Bean.setMdexp(name);
                    getUbica23CiudadesExp(selectedExper31Bean.getPaexp(), selectedExper31Bean.getEsexp(), selectedExper31Bean.getMdexp());
                }

                colonias = null;
                break;
            case "ciudadexp":

                if (add) {
                    newExper31Bean.setLoexp(name);
                    getUbica23ColoniasExp(newExper31Bean.getPaexp(), newExper31Bean.getEsexp(), newExper31Bean.getMdexp(), newExper31Bean.getLoexp());
                }
                if (selectedExper31Bean != null) {
                    selectedExper31Bean.setLoexp(name);
                    getUbica23ColoniasExp(selectedExper31Bean.getPaexp(), selectedExper31Bean.getEsexp(), selectedExper31Bean.getMdexp(), selectedExper31Bean.getLoexp());
                }
                break;
            case "coloniaexp":

                if (add) {
                    newExper31Bean.setCoexp(name);
                    getUbica23CpExp(newExper31Bean.getPaexp(), newExper31Bean.getEsexp(), newExper31Bean.getMdexp(), newExper31Bean.getLoexp(), newExper31Bean.getCoexp());
                }
                if (selectedExper31Bean != null) {
                    selectedExper31Bean.setCoexp(name);
                    getUbica23CpExp(selectedExper31Bean.getPaexp(), selectedExper31Bean.getEsexp(), selectedExper31Bean.getMdexp(), selectedExper31Bean.getLoexp(), selectedExper31Bean.getCoexp());
                }
                break;
            case "codigopostalexp":

                if (selectedExper31Bean != null) {
                    selectedExper31Bean.setCpexp(name);
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

        if (selectedExper31Bean != null) {
            selectedExper31Bean.setCpexp(ubica23Manager.getListUbica23Cp(pais, estado, municipio, ciudad, colonia));
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

        if (selectedExper31Bean != null) {
            selectedExper31Bean.setPaexp(ubica23ListExp.get(0).getPaubi());
            selectedExper31Bean.setEsexp(ubica23ListExp.get(0).getEsubi());
            selectedExper31Bean.setMdexp(ubica23ListExp.get(0).getMdubi());
            selectedExper31Bean.setLoexp(ubica23ListExp.get(0).getLoubi());
            selectedExper31Bean.setCoexp(ubica23ListExp.get(0).getCoubi());
        }

    }

    /**
     *
     * @param event
     */
    public void onTabChangeExper31(TabChangeEvent event) {
        TabView tabView = (TabView) event.getComponent();

        exper31Tab = tabView.getChildren().indexOf(event.getTab());
        System.out.println("tab actual exper:" + exper31Tab);

    }

    /**
     *
     * @return
     */
    public Exper31ManagerLocal getExper31Manager() {
        return exper31Manager;
    }

    /**
     *
     * @param exper31Manager
     */
    public void setExper31Manager(Exper31ManagerLocal exper31Manager) {
        this.exper31Manager = exper31Manager;
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
    public String getW1exp() {
        return w1exp;
    }

    /**
     *
     * @param w1exp
     */
    public void setW1exp(String w1exp) {
        this.w1exp = w1exp;
    }

    /**
     *
     * @return
     */
    public String getTdexp() {
        return tdexp;
    }

    /**
     *
     * @param tdexp
     */
    public void setTdexp(String tdexp) {
        this.tdexp = tdexp;
    }

    /**
     *
     * @return
     */
    public Integer getExper31Tab() {
        return exper31Tab;
    }

    /**
     *
     * @param exper31Tab
     */
    public void setExper31Tab(Integer exper31Tab) {
        this.exper31Tab = exper31Tab;
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
    public Exper31 getSelectedExper31Bean() {
        return selectedExper31Bean;
    }

    /**
     *
     * @param selectedExper31Bean
     */
    public void setSelectedExper31Bean(Exper31 selectedExper31Bean) {
        this.selectedExper31Bean = selectedExper31Bean;
    }

    /**
     *
     * @return
     */
    public Exper31 getNewExper31Bean() {
        return newExper31Bean;
    }

    /**
     *
     * @param newExper31Bean
     */
    public void setNewExper31Bean(Exper31 newExper31Bean) {
        this.newExper31Bean = newExper31Bean;
    }

    /**
     *
     * @return
     */
    public List<Exper31> getExper31List() {
        return exper31List;
    }

    /**
     *
     * @param exper31List
     */
    public void setExper31List(List<Exper31> exper31List) {
        this.exper31List = exper31List;
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
