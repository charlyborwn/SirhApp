package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.FileUploadBean;
import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Benef26;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Datos28;
import com.hrm.sirhapp.model.Ubica23;
import com.hrm.sirhapp.service.Benef26ManagerLocal;
import com.hrm.sirhapp.service.Datos28ManagerLocal;
import com.hrm.sirhapp.service.Ubica23ManagerLocal;
import com.hrm.sirhapp.service.exception.Benef26AlreadyExists;
import com.hrm.sirhapp.service.exception.Benef26NotFound;
import com.hrm.sirhapp.service.exception.Datos28NotFound;
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
public class Benef26BackingBean extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Benef26ManagerLocal benef26Manager;
    @EJB
    private Datos28ManagerLocal datos28Manager;
    @EJB
    private Ubica23ManagerLocal ubica23Manager;

    private Benef26 newBenef26Bean;
    private Benef26 selectedBenef26Bean;

    private List<Benef26> benef26List;

    private String infoMessageModule;
    private String infoMessage;
    private String rfc;
    private Integer nt;

    private String path;
    private boolean add;

    private List<Ubica23> ubica23ListBen;

    private List<String> paises;
    private List<String> estados;
    private List<String> municipios;
    private List<String> ciudades;
    private List<String> colonias;

    private Float percentage;

    private Integer benef26Tab;

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
        nt = userSessionBean.getTrabajador();
        getListBenef26();
    }

    /**
     *
     */
    public void getListBenef26() {

        benef26List = benef26Manager.getListBenef26(nt);
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("beneficiarioListForm");
        System.out.println("se obtienen los beneficiarios");

    }

    /**
     *
     */
    public void updateBenef26Foto() {
        selectedBenef26Bean.setPtben(null);

        FacesUtil.resetManagedBean("fileUploadBean", FileUploadBean.class);
        String oldInfoMessageModule = infoMessageModule;
        infoMessageModule = "Beneficiarios";
        infoMessage = "Se borro el Documento del beneficiario";
        FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
        infoMessageModule = oldInfoMessageModule;

    }

    /**
     *
     */
    public void commandValidateBenef26Percentage() {
        if (newBenef26Bean != null) {
            infoMessage = validateBenef26Percentage(newBenef26Bean.getPoben());
        }
        if (selectedBenef26Bean != null) {
            infoMessage = validateBenef26Percentage(selectedBenef26Bean.getPoben());
        }

    }

    /**
     *
     * @param percent
     * @return
     */
    public String validateBenef26Percentage(Float percent) {

        Boolean isValidPercentage = false;
        System.out.println("aqui es1" + isValidPercentage);
        Float currentPercentage = 0f;
        Float validationPercentage = 0f;
        //Se obtiene el valor total disponible de porcentaje
        Float currentAviablePercentage = getPercentage();

        if (newBenef26Bean != null) {
            currentPercentage = newBenef26Bean.getPoben();
            validationPercentage = newBenef26Bean.getPoben();
            System.out.println("newBenef26Bean != null" + isValidPercentage);
            System.out.println("newBenef26Bean != null currentAviablePercentage" + Math.round(currentAviablePercentage));
            System.out.println("newBenef26Bean != null validationPercentage" + Math.round(validationPercentage * 100));
            if (Math.round(currentAviablePercentage) < Math.round(validationPercentage * 100)) {
                isValidPercentage = false;
                System.out.println("aqui es2" + isValidPercentage);
                return "failure";
            }
        }

        if (selectedBenef26Bean != null) {
            currentPercentage = selectedBenef26Bean.getPoben();
            validationPercentage = selectedBenef26Bean.getPoben();
            Benef26 actualBenef26Bean = new Benef26();
            try {
                actualBenef26Bean = benef26Manager.getBenef26(selectedBenef26Bean.getIdben());
                validationPercentage = actualBenef26Bean.getPoben();
            } catch (Benef26NotFound ex) {
                validationPercentage = selectedBenef26Bean.getPoben();
            }
            System.out.println("selectedBenef26Bean != null" + isValidPercentage);
            System.out.println("selectedBenef26Bean != null currentPercentage" + Math.round(currentPercentage * 100));
            System.out.println("selectedBenef26Bean != null validationPercentage" + Math.round(validationPercentage * 100));
            if (Math.round(currentPercentage * 100) <= Math.round(validationPercentage * 100)) {
                isValidPercentage = true;
            }
            System.out.println("selectedBenef26Bean != null" + isValidPercentage);
            System.out.println("selectedBenef26Bean != null VAL1" + Math.round(currentAviablePercentage + (validationPercentage * 100)));
            System.out.println("selectedBenef26Bean != null VS" + Math.round(currentAviablePercentage + (currentPercentage * 100)));
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
    public String registerBenef26() {

        try {
            UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);
            newBenef26Bean.setNtben(userSessionBean.getTrabajador());
            newBenef26Bean.setStben(Constants.RECORD_ACTIVED);
            newBenef26Bean.setFeben(new Date());
            newBenef26Bean.setUsben(FacesUtil.getUserName());
            newBenef26Bean.setPoben(newBenef26Bean.getPoben());

            if (validateBenef26Percentage(newBenef26Bean.getPoben()).equals("failure")) {
                infoMessage = "No se puede continuar revise el porcentaje";
                getListBenef26();
                getPercentage();
                FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
                return "failure";
            }

            selectedBenef26Bean = benef26Manager.registerBenef26(newBenef26Bean);
            newBenef26Bean = null;
            add = false;
            infoMessage = "El registro se creo correctamente";
            getListBenef26();
            getPercentage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            return "success";
        } catch (Benef26AlreadyExists ex) {
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
    public String updateBenef26() {
        try {

            selectedBenef26Bean.setStben(Constants.RECORD_ACTIVED);
            selectedBenef26Bean.setFeben(new Date());
            selectedBenef26Bean.setUsben(FacesUtil.getUserName());

            if (path != null) {
                selectedBenef26Bean.setPtben(path);
                path = null;
            }

            if (benef26Tab == 0) {
                if (validateBenef26Percentage(selectedBenef26Bean.getPoben()).equals("failure")) {
                    infoMessage = "No se puede continuar revise el porcentaje";
                    getListBenef26();
                    getPercentage();
                    FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
                    return "failure";
                }
            }

            benef26Manager.updateBenef26(selectedBenef26Bean);
            getListBenef26();
            getPercentage();
            selectedBenef26Bean = null;
            infoMessage = "El registro se actualizo correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            return "success";
        } catch (Benef26NotFound ex) {
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
    public String deleteBenef26() {
        try {

            selectedBenef26Bean.setStben(Constants.RECORD_DELETED);
            selectedBenef26Bean.setFeben(new Date());
            selectedBenef26Bean.setUsben(FacesUtil.getUserName());

            benef26Manager.deleteBenef26(selectedBenef26Bean);

            selectedBenef26Bean = null;
            getListBenef26();
            getPercentage();

            infoMessage = "El registro se elimino correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Benef26NotFound ex) {
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
    public void retrieveTraba36aToBenef26Dir() {

        if (selectedBenef26Bean != null) {
            if (selectedBenef26Bean.getVmben().equals("si")) {
                Datos28 datos28 = new Datos28();
                try {
                    datos28 = datos28Manager.getDatos28(nt);
                } catch (Datos28NotFound ex) {
                    Logger.getLogger(Benef26Backing.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (datos28 != null) {
                    selectedBenef26Bean.setPaben(datos28.getPadat());
                    selectedBenef26Bean.setEsben(datos28.getEsdat());
                    selectedBenef26Bean.setMdben(datos28.getMddat());
                    selectedBenef26Bean.setLoben(datos28.getLodat());
                    selectedBenef26Bean.setCoben(datos28.getCodat());
                    selectedBenef26Bean.setCaben(datos28.getCadat());
                    selectedBenef26Bean.setCpben(datos28.getCpdat());
                    selectedBenef26Bean.setTdben(datos28.getTddat());
                    getUbica23CpList(datos28.getPadat());
                }
            } else {

                selectedBenef26Bean.setPaben("");
                selectedBenef26Bean.setEsben("");
                selectedBenef26Bean.setMdben("");
                selectedBenef26Bean.setLoben("");
                selectedBenef26Bean.setCoben("");
                selectedBenef26Bean.setCaben("");
                selectedBenef26Bean.setCpben("");
                selectedBenef26Bean.setTdben("");
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

        if (selectedBenef26Bean != null) {
            selectedBenef26Bean.setCpben(ubica23Manager.getListUbica23Cp(pais, estado, municipio, ciudad, colonia));
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

        if (selectedBenef26Bean != null) {
            selectedBenef26Bean.setPaben(ubica23ListBen.get(0).getPaubi());
            selectedBenef26Bean.setEsben(ubica23ListBen.get(0).getEsubi());
            selectedBenef26Bean.setMdben(ubica23ListBen.get(0).getMdubi());
            selectedBenef26Bean.setLoben(ubica23ListBen.get(0).getLoubi());
            selectedBenef26Bean.setCoben(ubica23ListBen.get(0).getCoubi());
        }

    }

    /**
     *
     * @param event
     */
    public void onTabChangeBenef26(TabChangeEvent event) {
        TabView tabView = (TabView) event.getComponent();
        benef26Tab = tabView.getChildren().indexOf(event.getTab());
        System.out.println("tab actual benef:" + benef26Tab);
        Boolean passTab = false;

        if (add) {
            if (newBenef26Bean.getApben() != null && newBenef26Bean.getApben().length() > 0) {
                passTab = true;
            } else {
                passTab = false;
            }
            System.out.println("getApbenA" + passTab);
            if (newBenef26Bean.getAmben() != null && newBenef26Bean.getAmben().length() > 0 && passTab == true) {
                passTab = true;
            } else {
                passTab = false;
            }
            System.out.println("getAmbenA" + passTab);
            if (newBenef26Bean.getNoben() != null && newBenef26Bean.getNoben().length() > 0 && passTab == true) {
                passTab = true;
            } else {
                passTab = false;
            }
            System.out.println("getNobenA" + passTab);
            if (newBenef26Bean.getTpben() != null && newBenef26Bean.getTpben().length() > 0 && passTab == true) {
                passTab = true;
            } else {
                passTab = false;
            }
            System.out.println("getTpbenA" + passTab);
            if (newBenef26Bean.getSeben() != null && newBenef26Bean.getSeben().length() > 0 && passTab == true) {
                passTab = true;
            } else {
                passTab = false;
            }
            System.out.println("getSebenA" + passTab);
            if (newBenef26Bean.getFnben() != null && passTab == true) {
                passTab = true;
            } else {
                passTab = false;
            }
            System.out.println("getFnbenA" + passTab);

            if (passTab) {
                if (newBenef26Bean.getPoben() != null && !newBenef26Bean.getPoben().equals("0%")) {
                    registerBenef26();
                } else {
                    benef26Tab = 0;
                }
            } else {
                benef26Tab = 0;
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
        if (id.equals("poben")) {
            valuePoben = (Float) ((UIOutput) vce.getSource()).getValue();
        } else {
            value = (String) ((UIOutput) vce.getSource()).getValue();
        }

        switch (id) {
            case "poben":
                if (validateBenef26Percentage(valuePoben).equals("failure")) {
                    infoMessage = "No se puede continuar revise el porcentaje";
                    FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
                }
                break;
            case "bensn_dir":
                retrieveTraba36aToBenef26Dir();
                break;
            case "paisben":
                newBenef26Bean.setCpben(value);
                if (selectedBenef26Bean != null) {
                    selectedBenef26Bean.setPaben(value);
                }
                getUbica23EstadosBen(value);

                municipios = null;
                ciudades = null;
                colonias = null;
                break;
            case "estadoben":
                if (add) {
                    newBenef26Bean.setEsben(value);
                    getUbica23MunicipiosBen(newBenef26Bean.getPaben(), newBenef26Bean.getEsben());
                }
                if (selectedBenef26Bean != null) {
                    selectedBenef26Bean.setEsben(value);
                    getUbica23MunicipiosBen(selectedBenef26Bean.getPaben(), selectedBenef26Bean.getEsben());
                }

                ciudades = null;
                colonias = null;
                break;
            case "municipioben":
                if (add) {
                    newBenef26Bean.setMdben(value);
                    getUbica23CiudadesBen(newBenef26Bean.getPaben(), newBenef26Bean.getEsben(), newBenef26Bean.getMdben());
                }
                if (selectedBenef26Bean != null) {
                    selectedBenef26Bean.setMdben(value);
                    getUbica23CiudadesBen(selectedBenef26Bean.getPaben(), selectedBenef26Bean.getEsben(), selectedBenef26Bean.getMdben());
                }

                colonias = null;
                break;
            case "ciudadben":
                if (add) {
                    newBenef26Bean.setLoben(value);
                    getUbica23ColoniasBen(newBenef26Bean.getPaben(), newBenef26Bean.getEsben(), newBenef26Bean.getMdben(), newBenef26Bean.getLoben());
                }
                if (selectedBenef26Bean != null) {
                    selectedBenef26Bean.setLoben(value);
                    getUbica23ColoniasBen(selectedBenef26Bean.getPaben(), selectedBenef26Bean.getEsben(), selectedBenef26Bean.getMdben(), selectedBenef26Bean.getLoben());
                }
                break;
            case "coloniaben":
                if (add) {
                    newBenef26Bean.setCoben(value);
                    getUbica23CpBen(newBenef26Bean.getPaben(), newBenef26Bean.getEsben(), newBenef26Bean.getMdben(), newBenef26Bean.getLoben(), newBenef26Bean.getCoben());
                }
                if (selectedBenef26Bean != null) {
                    selectedBenef26Bean.setCoben(value);
                    getUbica23CpBen(selectedBenef26Bean.getPaben(), selectedBenef26Bean.getEsben(), selectedBenef26Bean.getMdben(), selectedBenef26Bean.getLoben(), selectedBenef26Bean.getCoben());
                }
                break;
            case "codigopostalben":
                if (selectedBenef26Bean != null) {
                    selectedBenef26Bean.setCpben(value);
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
    }

    /**
     *
     * @return
     */
    public Benef26 prepareCreateBenef26() {
        selectedBenef26Bean = null;
        getListBenef26();
        getPercentage();
        newBenef26Bean = new Benef26();
        newBenef26Bean.setNtben(nt);
        newBenef26Bean.setPoben(percentage / 100);
        add = true;
        benef26Tab = 0;
        return newBenef26Bean;
    }

    /**
     *
     */
    public void prepareSelectBenef26() {
        newBenef26Bean = null;
        getUbica23CpListBen(selectedBenef26Bean.getCpben());
    }

    /**
     *
     * @return
     */
    public Float getPercentage() {

        Float result = benef26Manager.getPercentage(nt);
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
    public Benef26 getSelectedBenef26Bean() {
        return selectedBenef26Bean;
    }

    /**
     *
     * @param selectedBenef26Bean
     */
    public void setSelectedBenef26Bean(Benef26 selectedBenef26Bean) {
        this.selectedBenef26Bean = selectedBenef26Bean;
    }

    /**
     *
     * @return
     */
    public Benef26 getNewBenef26Bean() {
        return newBenef26Bean;
    }

    /**
     *
     * @param newBenef26Bean
     */
    public void setNewBenef26Bean(Benef26 newBenef26Bean) {
        this.newBenef26Bean = newBenef26Bean;
    }

    /**
     *
     * @return
     */
    public List<Benef26> getBenef26List() {
        return benef26List;
    }

    /**
     *
     * @param benef26List
     */
    public void setBenef26List(List<Benef26> benef26List) {
        this.benef26List = benef26List;
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
    public Integer getBenef26Tab() {
        return benef26Tab;
    }

    /**
     *
     * @param benef26Tab
     */
    public void setBenef26Tab(Integer benef26Tab) {
        this.benef26Tab = benef26Tab;
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
