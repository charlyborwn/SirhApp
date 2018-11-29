package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.FileUploadBean;
import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Benef26a;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Datos28a;
import com.hrm.sirhapp.model.Ubica23;
import com.hrm.sirhapp.service.Benef26aManagerLocal;
import com.hrm.sirhapp.service.Datos28aManagerLocal;
import com.hrm.sirhapp.service.Ubica23ManagerLocal;
import com.hrm.sirhapp.service.exception.Benef26aAlreadyExists;
import com.hrm.sirhapp.service.exception.Benef26aNotFound;
import com.hrm.sirhapp.service.exception.Datos28aNotFound;
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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
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
public class Benef26aBackingBean extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Benef26aManagerLocal benef26aManager;

    @EJB
    private Datos28aManagerLocal datos28aManager;

    @EJB
    private Ubica23ManagerLocal ubica23Manager;

    private Benef26a newBenef26aBean = new Benef26a();

    private Benef26a selectedBenef26aBean;

    private List<Benef26a> benef26aList;

    private String infoMessageModule;
    private String infoMessage;
    private String rfc;

    private String path;
    private boolean add;

    private List<Ubica23> ubica23ListBen;

    private List<String> paises;
    private List<String> estados;
    private List<String> municipios;
    private List<String> ciudades;
    private List<String> colonias;

    private Float percentage;

    private Integer benef26aTab;

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

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Beneficiarios";
        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);
        rfc = userSessionBean.getAspirante();
        getListBenef26a();
    }

    /**
     *
     */
    public void getListBenef26a() {

        benef26aList = benef26aManager.getListBenef26a(rfc);
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("beneficiarioListForm");
        System.out.println("se obtienen los beneficiarios");

    }

    /**
     *
     */
    public void updateBenef26aFoto() {
        selectedBenef26aBean.setPtbenA(null);

        FacesUtil.resetManagedBean("fileUploadBean", FileUploadBean.class);
        String oldInfoMessageModule = infoMessageModule;
        infoMessageModule = "Beneficiarios";
        infoMessage = "Se borro el Documento del beneficiario";
        FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
        infoMessageModule = oldInfoMessageModule;

    }

    /**
     *
     * @param percent
     * @return
     */
    public String validateBenef26aPercentage(Float percent) {

        Boolean isValidPercentage = false;
        System.out.println("aqui es1" + isValidPercentage);
        Float currentPercentage = 0f;
        Float validationPercentage = 0f;
        //Se obtiene el valor total disponible de porcentaje
        Float currentAviablePercentage = getPercentage();

        if (newBenef26aBean != null) {
            currentPercentage = newBenef26aBean.getPobenA();
            validationPercentage = newBenef26aBean.getPobenA();
            System.out.println("newBenef26aBean != null" + isValidPercentage);
            System.out.println("newBenef26aBean != null currentAviablePercentage" + Math.round(currentAviablePercentage));
            System.out.println("newBenef26aBean != null validationPercentage" + Math.round(validationPercentage * 100));
            if (Math.round(currentAviablePercentage) < Math.round(validationPercentage * 100)) {
                isValidPercentage = false;
                System.out.println("aqui es2" + isValidPercentage);
                return "failure";
            }
        }

        if (selectedBenef26aBean != null) {
            currentPercentage = selectedBenef26aBean.getPobenA();
            validationPercentage = selectedBenef26aBean.getPobenA();
            Benef26a actualBenef26Bean = new Benef26a();
            try {
                actualBenef26Bean = benef26aManager.getBenef26a(selectedBenef26aBean.getId());
                validationPercentage = actualBenef26Bean.getPobenA();
            } catch (Benef26aNotFound ex) {
                validationPercentage = selectedBenef26aBean.getPobenA();
            }
            System.out.println("selectedBenef26aBean != null" + isValidPercentage);
            System.out.println("selectedBenef26aBean != null currentPercentage" + Math.round(currentPercentage * 100));
            System.out.println("selectedBenef26aBean != null validationPercentage" + Math.round(validationPercentage * 100));
            if (Math.round(currentPercentage * 100) <= Math.round(validationPercentage * 100)) {
                isValidPercentage = true;
            }
            System.out.println("selectedBenef26aBean != null" + isValidPercentage);
            System.out.println("selectedBenef26aBean != null VAL1" + Math.round(currentAviablePercentage + (validationPercentage * 100)));
            System.out.println("selectedBenef26aBean != null VS" + Math.round(currentAviablePercentage + (currentPercentage * 100)));
            if (Math.round(currentPercentage * 100) <= Math.round(currentAviablePercentage + (validationPercentage * 100))) {
                isValidPercentage = true;
            }
        }

        System.out.println(isValidPercentage);
        System.out.println("Este es el porcentaje que se va a validar:" + validationPercentage);
        System.out.println("Este es el porcentaje total disponible:" + currentAviablePercentage);
        System.out.println("Este es el porcentaje actual en select:" + currentPercentage);
        if (validationPercentage.equals(currentPercentage)) {
            isValidPercentage = true;
        }
        if (validationPercentage.equals(currentAviablePercentage)) {
            isValidPercentage = true;
        }

        System.out.println(isValidPercentage);
        System.out.println(Math.round(currentPercentage * 100));
        System.out.println(Math.round(currentAviablePercentage));
        if (Math.round(currentAviablePercentage) > Math.round(currentPercentage * 100)) {
            isValidPercentage = true;
        }

        System.out.println(isValidPercentage);
        if (isValidPercentage) {
            return "success";
        } else {
            return "failure";
        }

    }

    /**
     *
     * @return
     */
    public String registerBenef26a() {

        try {
            UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);
            newBenef26aBean.setRfbenA(userSessionBean.getAspirante());
            newBenef26aBean.setStbenA(Constants.RECORD_ACTIVED);
            newBenef26aBean.setFebenA(new Date());
            newBenef26aBean.setUsbenA(FacesUtil.getUserName());
            newBenef26aBean.setPobenA(newBenef26aBean.getPobenA());

            if (validateBenef26aPercentage(newBenef26aBean.getPobenA()).equals("failure")) {
                infoMessage = "No se puede continuar revise el porcentaje";
                getListBenef26a();
                getPercentage();
                FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
                return "failure";
            }
            
            selectedBenef26aBean = benef26aManager.registerBenef26a(newBenef26aBean);
            newBenef26aBean = null;
            add = false;
            infoMessage = "El registro se creo correctamente";
            getListBenef26a();
            getPercentage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            return "success";
        } catch (Benef26aAlreadyExists ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
            return "failure";
        }
    }

    /**
     *
     * @return
     */
    public String updateBenef26a() {
        try {

            selectedBenef26aBean.setStbenA(Constants.RECORD_ACTIVED);
            selectedBenef26aBean.setFebenA(new Date());
            selectedBenef26aBean.setUsbenA(FacesUtil.getUserName());

            if (path != null) {
                selectedBenef26aBean.setPtbenA(path);
                path = null;
            }

            if (benef26aTab == 0) {
                if (validateBenef26aPercentage(selectedBenef26aBean.getPobenA()).equals("failure")) {
                    infoMessage = "No se puede continuar revise el porcentaje";
                    getListBenef26a();
                    getPercentage();
                    FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
                    return "failure";
                }
            }

            benef26aManager.updateBenef26a(selectedBenef26aBean);
            getListBenef26a();
            getPercentage();
            selectedBenef26aBean = null;
            infoMessage = "El registro se actualizo correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            return "success";
        } catch (Benef26aNotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            return "failure";

        }

    }

    /**
     *
     * @return
     */
    public String deleteBenef26a() {
        try {

            selectedBenef26aBean.setStbenA(Constants.RECORD_DELETED);
            selectedBenef26aBean.setFebenA(new Date());
            selectedBenef26aBean.setUsbenA(FacesUtil.getUserName());

            benef26aManager.deleteBenef26a(selectedBenef26aBean);

            selectedBenef26aBean = null;
            getListBenef26a();
            getPercentage();

            infoMessage = "El registro se elimino correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Benef26aNotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR, infoMessageModule, infoMessage);
        }
        return null;

    }

    /**
     *
     * @param cpubi
     */
    public void getUbica23CpList(String cpubi) {
        ubica23ListBen = ubica23Manager.getListUbica23Cp(cpubi);

        if (ubica23ListBen.size() > 0) {
            actualizaUbiListBen();
        }

    }

    /**
     *
     */
    public void retrieveTraba36aToBenef26aDir() {

        if (selectedBenef26aBean != null) {
            if (selectedBenef26aBean.getVmbenA().equals("si")) {
                Datos28a datos28a = new Datos28a();
                try {
                    datos28a = datos28aManager.getDatos28a(rfc);
                } catch (Datos28aNotFound ex) {
                    Logger.getLogger(Benef26aBacking.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (datos28a != null) {
                    selectedBenef26aBean.setPabenA(datos28a.getPadatA());
                    selectedBenef26aBean.setEsbenA(datos28a.getEsdatA());
                    selectedBenef26aBean.setMdbenA(datos28a.getMddatA());
                    selectedBenef26aBean.setLobenA(datos28a.getLodatA());
                    selectedBenef26aBean.setCobenA(datos28a.getCodatA());
                    selectedBenef26aBean.setCabenA(datos28a.getCadatA());
                    selectedBenef26aBean.setCpbenA(datos28a.getCpdatA());
                    selectedBenef26aBean.setTdbenA(datos28a.getTddatA());
                    getUbica23CpList(datos28a.getPadatA());
                }
            } else {

                selectedBenef26aBean.setPabenA("");
                selectedBenef26aBean.setEsbenA("");
                selectedBenef26aBean.setMdbenA("");
                selectedBenef26aBean.setLobenA("");
                selectedBenef26aBean.setCobenA("");
                selectedBenef26aBean.setCabenA("");
                selectedBenef26aBean.setCpbenA("");
                selectedBenef26aBean.setTdbenA("");
            }
        }
    }

    /**
     *
     */
    public void getUbica23PaisesBen() {

        paises = ubica23Manager.getListUbica23Paises();
        Collections.sort(paises, Collator.getInstance());

    }

    /**
     *
     * @param pais
     */
    public void getUbica23EstadosBen(String pais) {

        estados = ubica23Manager.getListUbica23Estados(pais);
        Collections.sort(estados, Collator.getInstance());

    }

    /**
     *
     * @param pais
     * @param estado
     */
    public void getUbica23MunicipiosBen(String pais, String estado) {

        municipios = ubica23Manager.getListUbica23Municipios(pais, estado);
        Collections.sort(municipios, Collator.getInstance());

    }

    /**
     *
     * @param pais
     * @param estado
     * @param municipio
     */
    public void getUbica23CiudadesBen(String pais, String estado, String municipio) {

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
    public void getUbica23ColoniasBen(String pais, String estado, String municipio, String ciudad) {

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
    public void getUbica23CpBen(String pais, String estado, String municipio, String ciudad, String colonia) {

        if (selectedBenef26aBean != null) {
            selectedBenef26aBean.setCpbenA(ubica23Manager.getListUbica23Cp(pais, estado, municipio, ciudad, colonia));
        }

    }

    /**
     *
     * @param cpubi
     */
    public void getUbica23CpBen(String cpubi) {
        ubica23ListBen = ubica23Manager.getListUbica23Cp(cpubi);

        if (ubica23ListBen.size() > 0) {
            actualizaUbiBen();
        }

    }

    /**
     *
     * @param cpubi
     */
    public void getUbica23CpListBen(String cpubi) {
        ubica23ListBen = ubica23Manager.getListUbica23Cp(cpubi);

        if (ubica23ListBen.size() > 0) {
            actualizaUbiListBen();
        }

    }

    /**
     *
     */
    public void actualizaUbiListBen() {

        getUbica23PaisesBen();

        getUbica23EstadosBen(ubica23ListBen.get(0).getPaubi());

        getUbica23MunicipiosBen(ubica23ListBen.get(0).getPaubi(), ubica23ListBen.get(0).getEsubi());

        getUbica23CiudadesBen(ubica23ListBen.get(0).getPaubi(), ubica23ListBen.get(0).getEsubi(), ubica23ListBen.get(0).getMdubi());

        getUbica23ColoniasBen(ubica23ListBen.get(0).getPaubi(), ubica23ListBen.get(0).getEsubi(), ubica23ListBen.get(0).getMdubi(), ubica23ListBen.get(0).getLoubi());

    }

    /**
     *
     */
    public void actualizaUbiBen() {

        getUbica23PaisesBen();

        getUbica23EstadosBen(ubica23ListBen.get(0).getPaubi());

        getUbica23MunicipiosBen(ubica23ListBen.get(0).getPaubi(), ubica23ListBen.get(0).getEsubi());

        getUbica23CiudadesBen(ubica23ListBen.get(0).getPaubi(), ubica23ListBen.get(0).getEsubi(), ubica23ListBen.get(0).getMdubi());

        getUbica23ColoniasBen(ubica23ListBen.get(0).getPaubi(), ubica23ListBen.get(0).getEsubi(), ubica23ListBen.get(0).getMdubi(), ubica23ListBen.get(0).getLoubi());

        if (selectedBenef26aBean != null) {
            selectedBenef26aBean.setPabenA(ubica23ListBen.get(0).getPaubi());
            selectedBenef26aBean.setEsbenA(ubica23ListBen.get(0).getEsubi());
            selectedBenef26aBean.setMdbenA(ubica23ListBen.get(0).getMdubi());
            selectedBenef26aBean.setLobenA(ubica23ListBen.get(0).getLoubi());
            selectedBenef26aBean.setCobenA(ubica23ListBen.get(0).getCoubi());
        }

    }

    /**
     *
     * @param event
     */
    public void onTabChangeBenef26a(TabChangeEvent event) {
        TabView tabView = (TabView) event.getComponent();
        benef26aTab = tabView.getChildren().indexOf(event.getTab());
        System.out.println("tab actual benef:" + benef26aTab);
        Boolean passTab = false;

        if (add) {
            if (newBenef26aBean.getApbenA() != null && newBenef26aBean.getApbenA().length() > 0) {
                passTab = true;
            } else {
                passTab = false;
            }
            System.out.println("getApbenA" + passTab);
            if (newBenef26aBean.getAmbenA() != null && newBenef26aBean.getAmbenA().length() > 0 && passTab == true) {
                passTab = true;
            } else {
                passTab = false;
            }
            System.out.println("getAmbenA" + passTab);
            if (newBenef26aBean.getNobenA() != null && newBenef26aBean.getNobenA().length() > 0 && passTab == true) {
                passTab = true;
            } else {
                passTab = false;
            }
            System.out.println("getNobenA" + passTab);
            if (newBenef26aBean.getTpbenA() != null && newBenef26aBean.getTpbenA().length() > 0 && passTab == true) {
                passTab = true;
            } else {
                passTab = false;
            }
            System.out.println("getTpbenA" + passTab);
            if (newBenef26aBean.getSebenA() != null && newBenef26aBean.getSebenA().length() > 0 && passTab == true) {
                passTab = true;
            } else {
                passTab = false;
            }
            System.out.println("getSebenA" + passTab);
            if (newBenef26aBean.getFnbenA() != null && passTab == true) {
                passTab = true;
            } else {
                passTab = false;
            }
            System.out.println("getFnbenA" + passTab);

            if (passTab) {
                if (newBenef26aBean.getPobenA() != null && !newBenef26aBean.getPobenA().equals("0%")) {
                    registerBenef26a();
                } else {
                    benef26aTab = 0;
                }
            } else {
                benef26aTab = 0;
            }

        }

    }

    /**
     *
     * @param vce
     */
    public void handleChange(AjaxBehaviorEvent vce) {
        String id = (String) ((UIOutput) vce.getSource()).getId();
        String value = "";
        Float valuePoben = 0f;
        if (id.equals("pobenA")) {
            valuePoben = (Float) ((UIOutput) vce.getSource()).getValue();
        } else {
            value = (String) ((UIOutput) vce.getSource()).getValue();
        }
        switch (id) {
            case "pobenA":
                if (validateBenef26aPercentage(valuePoben).equals("failure")) {
                    infoMessage = "No se puede continuar revise el porcentaje";
                    FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
                }
                break;
            case "bensn_dir":
                value = (String) ((UIOutput) vce.getSource()).getValue();
                retrieveTraba36aToBenef26aDir();
                break;
            case "paisben":
                value = (String) ((UIOutput) vce.getSource()).getValue();
                newBenef26aBean.setCpbenA(value);
                if (selectedBenef26aBean != null) {
                    selectedBenef26aBean.setPabenA(value);
                }
                getUbica23EstadosBen(value);

                municipios = null;
                ciudades = null;
                colonias = null;
                break;
            case "estadoben":
                value = (String) ((UIOutput) vce.getSource()).getValue();
                if (add) {
                    newBenef26aBean.setEsbenA(value);
                    getUbica23MunicipiosBen(newBenef26aBean.getPabenA(), newBenef26aBean.getEsbenA());
                }
                if (selectedBenef26aBean != null) {
                    selectedBenef26aBean.setEsbenA(value);
                    getUbica23MunicipiosBen(selectedBenef26aBean.getPabenA(), selectedBenef26aBean.getEsbenA());
                }

                ciudades = null;
                colonias = null;
                break;
            case "municipioben":
                value = (String) ((UIOutput) vce.getSource()).getValue();
                if (add) {
                    newBenef26aBean.setMdbenA(value);
                    getUbica23CiudadesBen(newBenef26aBean.getPabenA(), newBenef26aBean.getEsbenA(), newBenef26aBean.getMdbenA());
                }
                if (selectedBenef26aBean != null) {
                    selectedBenef26aBean.setMdbenA(value);
                    getUbica23CiudadesBen(selectedBenef26aBean.getPabenA(), selectedBenef26aBean.getEsbenA(), selectedBenef26aBean.getMdbenA());
                }

                colonias = null;
                break;
            case "ciudadben":
                value = (String) ((UIOutput) vce.getSource()).getValue();

                if (add) {
                    newBenef26aBean.setLobenA(value);
                    getUbica23ColoniasBen(newBenef26aBean.getPabenA(), newBenef26aBean.getEsbenA(), newBenef26aBean.getMdbenA(), newBenef26aBean.getLobenA());
                }
                if (selectedBenef26aBean != null) {
                    selectedBenef26aBean.setLobenA(value);
                    getUbica23ColoniasBen(selectedBenef26aBean.getPabenA(), selectedBenef26aBean.getEsbenA(), selectedBenef26aBean.getMdbenA(), selectedBenef26aBean.getLobenA());
                }
                break;
            case "coloniaben":
                value = (String) ((UIOutput) vce.getSource()).getValue();
                if (add) {
                    newBenef26aBean.setCobenA(value);
                    getUbica23CpBen(newBenef26aBean.getPabenA(), newBenef26aBean.getEsbenA(), newBenef26aBean.getMdbenA(), newBenef26aBean.getLobenA(), newBenef26aBean.getCobenA());
                }
                if (selectedBenef26aBean != null) {
                    selectedBenef26aBean.setCobenA(value);
                    getUbica23CpBen(selectedBenef26aBean.getPabenA(), selectedBenef26aBean.getEsbenA(), selectedBenef26aBean.getMdbenA(), selectedBenef26aBean.getLobenA(), selectedBenef26aBean.getCobenA());
                }
                break;
            case "codigopostalben":
                value = (String) ((UIOutput) vce.getSource()).getValue();
                if (selectedBenef26aBean != null) {
                    selectedBenef26aBean.setCpbenA(value);
                }

                if (value.indexOf("_", 0) == -1) {
                    getUbica23CpBen(value);
                } else {

                    ubica23ListBen = null;
                    String var = value.replace("_", "");
                    if (var.length() == 4 || var.length() == 0) {

                        getUbica23PaisesBen();

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
        System.out.println("id:" + id + " value:" + value);
    }

    /**
     *
     * @return
     */
    public Benef26a prepareCreateBenef26a() {
        getListBenef26a();
        getPercentage();
        newBenef26aBean = new Benef26a();
        newBenef26aBean.setRfbenA(rfc);
        newBenef26aBean.setPobenA(percentage / 100);
        add = true;
        benef26aTab = 0;
        return newBenef26aBean;
    }

    /**
     *
     */
    public void prepareSelectBenef26a() {
        getUbica23CpListBen(selectedBenef26aBean.getCpbenA());
    }

    /**
     *
     * @return
     */
    public Float getPercentage() {

        Float result = benef26aManager.getPercentage(rfc);
        this.percentage = new Float(Math.round(result));

        return percentage;
    }

    /**
     *
     * @param percentage
     */
    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    /**
     *
     * @param fValue
     * @return
     */
    public static Double getFloatAsDouble(Float fValue) {
        Double a = Math.round(fValue * 100.0) / 100.0;
        return Double.valueOf(a.toString());
    }

    /**
     *
     * @param doubleValue
     * @return
     */
    public static Float getDoubleAsFloat(Double doubleValue) {
        return doubleValue == null ? null : doubleValue.floatValue();
    }

    /**
     *
     * @return
     */
    public Benef26a getSelectedBenef26aBean() {
        return selectedBenef26aBean;
    }

    /**
     *
     * @param selectedBenef26aBean
     */
    public void setSelectedBenef26aBean(Benef26a selectedBenef26aBean) {
        this.selectedBenef26aBean = selectedBenef26aBean;
    }

    /**
     *
     * @return
     */
    public Benef26a getNewBenef26aBean() {
        return newBenef26aBean;
    }

    /**
     *
     * @param newBenef26aBean
     */
    public void setNewBenef26aBean(Benef26a newBenef26aBean) {
        this.newBenef26aBean = newBenef26aBean;
    }

    /**
     *
     * @return
     */
    public List<Benef26a> getBenef26aList() {
        return benef26aList;
    }

    /**
     *
     * @param benef26aList
     */
    public void setBenef26aList(List<Benef26a> benef26aList) {
        this.benef26aList = benef26aList;
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
    public List<Ubica23> getUbica23ListBen() {
        return ubica23ListBen;
    }

    /**
     *
     * @param ubica23ListBen
     */
    public void setUbica23ListBen(List<Ubica23> ubica23ListBen) {
        this.ubica23ListBen = ubica23ListBen;
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
    public Integer getBenef26aTab() {
        return benef26aTab;
    }

    /**
     *
     * @param benef26aTab
     */
    public void setBenef26aTab(Integer benef26aTab) {
        this.benef26aTab = benef26aTab;
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

}
