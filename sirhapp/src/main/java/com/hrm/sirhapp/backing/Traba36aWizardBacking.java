package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.FileUploadBean;
import com.hrm.sirhapp.model.Benef26a;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Datos28a;
import com.hrm.sirhapp.model.Docum29a;
import com.hrm.sirhapp.model.Escol30a;
import com.hrm.sirhapp.model.Exper31a;
import com.hrm.sirhapp.model.Famil32a;
import com.hrm.sirhapp.model.Ident49a;
import com.hrm.sirhapp.model.Requi54;
import com.hrm.sirhapp.model.Traba36;
import com.hrm.sirhapp.model.Traba36a;
import com.hrm.sirhapp.model.Ubica23;
import com.hrm.sirhapp.model.Usuar24;
import com.hrm.sirhapp.service.Benef26aManagerLocal;
import com.hrm.sirhapp.service.Datos28aManagerLocal;
import com.hrm.sirhapp.service.Docum29aManagerLocal;
import com.hrm.sirhapp.service.Escol30aManagerLocal;
import com.hrm.sirhapp.service.Espec05ManagerLocal;
import com.hrm.sirhapp.service.Exper31aManagerLocal;
import com.hrm.sirhapp.service.Famil32aManagerLocal;
import com.hrm.sirhapp.service.Ident49aManagerLocal;
import com.hrm.sirhapp.service.Requi54ManagerLocal;
import com.hrm.sirhapp.service.Tidoc18ManagerLocal;
import com.hrm.sirhapp.service.Traba36ManagerLocal;
import com.hrm.sirhapp.service.Traba36aManagerLocal;
import com.hrm.sirhapp.service.Ubica23ManagerLocal;
import com.hrm.sirhapp.service.exception.Benef26aAlreadyExists;
import com.hrm.sirhapp.service.exception.Benef26aNotFound;
import com.hrm.sirhapp.service.exception.Datos28aAlreadyExists;
import com.hrm.sirhapp.service.exception.Datos28aNotFound;
import com.hrm.sirhapp.service.exception.Docum29aAlreadyExists;
import com.hrm.sirhapp.service.exception.Docum29aNotFound;
import com.hrm.sirhapp.service.exception.Escol30aAlreadyExists;
import com.hrm.sirhapp.service.exception.Escol30aNotFound;
import com.hrm.sirhapp.service.exception.Exper31aAlreadyExists;
import com.hrm.sirhapp.service.exception.Exper31aNotFound;
import com.hrm.sirhapp.service.exception.Famil32aAlreadyExists;
import com.hrm.sirhapp.service.exception.Famil32aNotFound;
import com.hrm.sirhapp.service.exception.Ident49aAlreadyExists;
import com.hrm.sirhapp.service.exception.Ident49aNotFound;
import com.hrm.sirhapp.service.exception.Requi54AlreadyExists;
import com.hrm.sirhapp.service.exception.Requi54NotFound;
import com.hrm.sirhapp.service.exception.Tidoc18NotFound;
import com.hrm.sirhapp.service.exception.Traba36aAlreadyExists;
import com.hrm.sirhapp.service.exception.Traba36aNotFound;
import com.hrm.sirhapp.util.FacesUtil;
import com.hrm.sirhapp.util.LanguagesUtil;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.ValueHolder;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SlideEndEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@ViewScoped
public class Traba36aWizardBacking extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Traba36aManagerLocal traba36aManager;
    @EJB
    private Traba36ManagerLocal traba36Manager;
    @EJB
    private Datos28aManagerLocal datos28aManager;
    @EJB
    private Ident49aManagerLocal ident49aManager;
    @EJB
    private Benef26aManagerLocal benef26aManager;
    @EJB
    private Famil32aManagerLocal famil32aManager;
    @EJB
    private Docum29aManagerLocal docum29aManager;
    @EJB
    private Escol30aManagerLocal escol30aManager;
    @EJB
    private Exper31aManagerLocal exper31aManager;
    @EJB
    private Requi54ManagerLocal requi54Manager;
    @EJB
    private Tidoc18ManagerLocal tidoc18Manager;
    @EJB
    private Espec05ManagerLocal espec05Manager;

    private List<Traba36> traba36List;
    private List<Traba36a> traba36aList;
    private List<Benef26a> benef26aList;
    private List<Famil32a> famil32aList;
    private List<Docum29a> docum29aList;
    private List<Escol30a> escol30aList;
    private List<Exper31a> exper31aList;
    private String imss;
    private String rfc;
    private String rfcSearch;
    private String curp;
    private String ap;
    private String am;
    private String n;

    private Float percentage;
    private Integer benef26aTab;

    private String path;
    private String w1expA;
    private String tdexpA;

    private String outcome;

    private int escol30aI;
    private int escol30aF;
    private Integer escol30aTab;

    private Integer exper31aTab;

    private Integer famil32aTab;

    private String lastStep = null;
    private String currentStep = null;

    private boolean skip;
    private boolean back;
    private boolean next;
    private boolean save;
    private boolean add;

    private String infoMessageModule;
    private String infoMessageAsp;
    private String infoMessageTra;
    private String infoMessage;

    private boolean activeTraba36a;
    private boolean registeredTraba36a;
    private boolean registeredDatos28a;
    private boolean registeredIdent49a;

    @Named
    @Produces
    @RequestScoped
    private Traba36a selectedTraba36aWiz;

    @Named
    @Produces
    @RequestScoped
    private Traba36 selectedTraba36Wiz;

    @Named
    @Produces
    @RequestScoped
    private Traba36a newTraba36aWiz = new Traba36a();

    @Named
    @Produces
    @RequestScoped
    private Datos28a selectedDatos28aWiz;

    @Named
    @Produces
    @RequestScoped
    private Datos28a newDatos28aWiz = new Datos28a();

    @Named
    @Produces
    @RequestScoped
    private Requi54 selectedRequi54Wiz;

    @Named
    @Produces
    @RequestScoped
    private Requi54 newRequi54Wiz = new Requi54();

    @Named
    @Produces
    @RequestScoped
    private Ident49a selectedIdent49aWiz;

    @Named
    @Produces
    @RequestScoped
    private Ident49a newIdent49aWiz = new Ident49a();

    private Benef26a selectedBenef26aWiz;

    @Named
    @Produces
    @RequestScoped
    private Benef26a newBenef26aWiz = new Benef26a();

    private Famil32a selectedFamil32aWiz;

    @Named
    @Produces
    @RequestScoped
    private Famil32a newFamil32aWiz = new Famil32a();

    private Docum29a selectedDocum29aWiz;

    @Named
    @Produces
    @RequestScoped
    private Docum29a newDocum29aWiz = new Docum29a();

    private Escol30a selectedEscol30aWiz;

    @Named
    @Produces
    @RequestScoped
    private Escol30a newEscol30aWiz = new Escol30a();

    private Exper31a selectedExper31aWiz;

    @Named
    @Produces
    @RequestScoped
    private Exper31a newExper31aWiz = new Exper31a();

    @EJB
    private Ubica23ManagerLocal ubica23Manager;

    private List<Ubica23> ubica23List;

    private List<String> paises;
    private List<String> estados;
    private List<String> municipios;
    private List<String> ciudades;
    private List<String> colonias;

    private List<Ubica23> ubica23ListBen;

    private List<String> paisesBen;
    private List<String> estadosBen;
    private List<String> municipiosBen;
    private List<String> ciudadesBen;
    private List<String> coloniasBen;

    private List<Ubica23> ubica23ListFam;

    private List<String> paisesFam;
    private List<String> estadosFam;
    private List<String> municipiosFam;
    private List<String> ciudadesFam;
    private List<String> coloniasFam;

    private List<Ubica23> ubica23ListExp;

    private List<String> paisesExp;
    private List<String> estadosExp;
    private List<String> municipiosExp;
    private List<String> ciudadesExp;
    private List<String> coloniasExp;

    private DualListModel<String> dias;

    @PostConstruct
    private void init() {
        FacesUtil.runManagedBean("traba36aGeneradorOficiosBacking", "clearState");

        getUbica23Paises();
        getUbica23PaisesBen();
        getUbica23PaisesFam();
        getUbica23PaisesExp();

        back = false;
        next = false;
        save = false;
        skip = false;

        benef26aTab = 0;

        escol30aI = 6;
        escol30aF = 22;

        path = null;

        currentStep = "buscar";

        infoMessageModule = "Wizard";

        List<String> diasSource = new ArrayList<String>();
        List<String> diasTarget = new ArrayList<String>();

        diasSource.add("LUN");
        diasSource.add("MAR");
        diasSource.add("MIE");
        diasSource.add("JUE");
        diasSource.add("VIE");
        diasSource.add("SAB");
        diasSource.add("DOM");

        this.dias = new DualListModel<String>(diasSource, diasTarget);
        //rfc = "GODM760429ABC";
        //retrieveTraba36aListTraba36List();
    }

    /**
     *
     * @return
     */
    public Espec05ManagerLocal getEspec05Manager() {
        return espec05Manager;
    }

    /**
     *
     * @param espec05Manager
     */
    public void setEspec05Manager(Espec05ManagerLocal espec05Manager) {
        this.espec05Manager = espec05Manager;
    }

    /**
     *
     * @return
     */
    public String getRfcSearch() {
        return rfcSearch;
    }

    /**
     *
     * @param rfcSearch
     */
    public void setRfcSearch(String rfcSearch) {
        this.rfcSearch = rfcSearch;
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
    public DualListModel<String> getDias() {
        return dias;
    }

    /**
     *
     * @param dias
     */
    public void setDias(DualListModel<String> dias) {
        this.dias = dias;
    }

    /**
     *
     * @param event
     */
    public void onSlideEnd(SlideEndEvent event) {
        //FacesMessage message = new FacesMessage("Slide Ended", "Value: " + event.getValue());
        //FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     *
     * @return
     */
    public int getEscol30aI() {
        return escol30aI;
    }

    /**
     *
     * @param escol30aI
     */
    public void setEscol30aI(int escol30aI) {
        this.escol30aI = escol30aI;
    }

    /**
     *
     * @return
     */
    public int getEscol30aF() {
        return escol30aF;
    }

    /**
     *
     * @param escol30aF
     */
    public void setEscol30aF(int escol30aF) {
        this.escol30aF = escol30aF;
    }

    /**
     *
     * @return
     */
    public Integer getEscol30aTab() {
        return escol30aTab;
    }

    /**
     *
     * @param escol30aTab
     */
    public void setEscol30aTab(Integer escol30aTab) {
        this.escol30aTab = escol30aTab;
    }

    /**
     *
     * @return
     */
    public String getOutcome() {
        return outcome;
    }

    private void getOutcome(Boolean is) {
        String out = "/secured/aspirantes/datosGeneralesCreate.xhtml?faces-redirect=true&ap=" + ap + "&am=" + am + "&n=" + n + "&rfc=" + rfc + "&curp=" + curp + "&imss=" + imss;
        System.out.println(out);
        this.outcome = out;
    }

    /**
     *
     * @param outcome
     */
    public void setOutcome(String outcome) {
        this.outcome = outcome;
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
            if (newBenef26aWiz.getApbenA() != null && newBenef26aWiz.getApbenA().length() > 0) {
                passTab = true;
            } else {
                passTab = false;
            }
            System.out.println("getApbenA" + passTab);
            if (newBenef26aWiz.getAmbenA() != null && newBenef26aWiz.getAmbenA().length() > 0 && passTab == true) {
                passTab = true;
            } else {
                passTab = false;
            }
            System.out.println("getAmbenA" + passTab);
            if (newBenef26aWiz.getNobenA() != null && newBenef26aWiz.getNobenA().length() > 0 && passTab == true) {
                passTab = true;
            } else {
                passTab = false;
            }
            System.out.println("getNobenA" + passTab);
            if (newBenef26aWiz.getTpbenA() != null && newBenef26aWiz.getTpbenA().length() > 0 && passTab == true) {
                passTab = true;
            } else {
                passTab = false;
            }
            System.out.println("getTpbenA" + passTab);
            if (newBenef26aWiz.getSebenA() != null && newBenef26aWiz.getSebenA().length() > 0 && passTab == true) {
                passTab = true;
            } else {
                passTab = false;
            }
            System.out.println("getSebenA" + passTab);
            if (newBenef26aWiz.getFnbenA() != null && passTab == true) {
                passTab = true;
            } else {
                passTab = false;
            }
            System.out.println("getFnbenA" + passTab);

            if (passTab) {
                if (newBenef26aWiz.getPobenA() != null && !newBenef26aWiz.getPobenA().equals("0%")) {
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
     * @param event
     */
    public void onTabChangeEscol30a(TabChangeEvent event) {
        TabView tabView = (TabView) event.getComponent();

        escol30aTab = tabView.getChildren().indexOf(event.getTab());
        System.out.println("tab actual escol:" + escol30aTab);

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

    /**
     *
     * @return
     */
    public Float getPercentage() {
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
     * @param rfbenA
     * @return
     */
    public Float getPercentage(String rfbenA) {
        Float result = benef26aManager.getPercentage(rfbenA);
        this.percentage = result;

        return result;
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
     */
    public void prepareCreateTraba36a() {

        if (selectedTraba36aWiz == null) {
            this.am = am.toUpperCase();
            newTraba36aWiz.setAmtraA(am);

            this.ap = ap.toUpperCase();
            newTraba36aWiz.setAptraA(ap);

            this.n = n.toUpperCase();
            newTraba36aWiz.setNotraA(n);

            newTraba36aWiz.setCctraA(new Date());

            newTraba36aWiz.setRftraA(rfcSearch.toUpperCase());
            newTraba36aWiz.setCutraA(rfcSearch.substring(0, 10));

            newDatos28aWiz.setRfdatA(rfcSearch);
            newIdent49aWiz.setRfideA(rfcSearch);
            newRequi54Wiz.setRfreq(rfcSearch);

            add = true;
        }
    }

    /**
     *
     */
    public void saveTraba36a() {

        if (selectedTraba36aWiz != null) {
            updateTraba36a();
        } else {
            registerTraba36a();
        }
    }

    /**
     *
     */
    public void saveDatos28a() {
        if (selectedDatos28aWiz != null) {
            updateDatos28a();
        } else {
            registerDatos28a();
        }
    }

    /**
     *
     */
    public void saveIdent49a() {
        if (selectedIdent49aWiz != null) {
            updateIdent49a();
        } else {
            registerIdent49a();
        }
    }

    /**
     *
     */
    public void saveRequi54() {
        if (selectedRequi54Wiz != null) {
            updateRequi54();
        } else {
            registerRequi54();
        }
    }

    /**
     *
     */
    public void retrieveTraba36aToFamil32aDir() {

        if (selectedFamil32aWiz != null) {
            if (selectedFamil32aWiz.getVmfamA().equals("si")) {
                if (selectedDatos28aWiz != null) {
                    selectedFamil32aWiz.setPafamA(selectedDatos28aWiz.getPadatA());
                    selectedFamil32aWiz.setEsfamA(selectedDatos28aWiz.getEsdatA());
                    selectedFamil32aWiz.setMdfamA(selectedDatos28aWiz.getMddatA());
                    selectedFamil32aWiz.setLofamA(selectedDatos28aWiz.getLodatA());
                    selectedFamil32aWiz.setCofamA(selectedDatos28aWiz.getCodatA());
                    selectedFamil32aWiz.setCafamA(selectedDatos28aWiz.getCadatA());
                    selectedFamil32aWiz.setCpfamA(selectedDatos28aWiz.getCpdatA());
                    selectedFamil32aWiz.setTdfamA(selectedDatos28aWiz.getTddatA());
                    getUbica23CpList(selectedDatos28aWiz.getPadatA());
                }
            } else {

                selectedFamil32aWiz.setPafamA("");
                selectedFamil32aWiz.setEsfamA("");
                selectedFamil32aWiz.setMdfamA("");
                selectedFamil32aWiz.setLofamA("");
                selectedFamil32aWiz.setCofamA("");
                selectedFamil32aWiz.setCafamA("");
                selectedFamil32aWiz.setCpfamA("");
                selectedFamil32aWiz.setTdfamA("");
            }
        }
    }

    /**
     *
     */
    public void retrieveTraba36aToBenef26aDir() {
        //Long id = selectedBenef26aWiz.getId();
        //Benef26a oldBenef26aWiz = benef26aManager.getBenef26a(id);

        if (selectedBenef26aWiz != null) {
            if (selectedBenef26aWiz.getVmbenA().equals("si")) {
                if (selectedDatos28aWiz != null) {
                    selectedBenef26aWiz.setPabenA(selectedDatos28aWiz.getPadatA());
                    selectedBenef26aWiz.setEsbenA(selectedDatos28aWiz.getEsdatA());
                    selectedBenef26aWiz.setMdbenA(selectedDatos28aWiz.getMddatA());
                    selectedBenef26aWiz.setLobenA(selectedDatos28aWiz.getLodatA());
                    selectedBenef26aWiz.setCobenA(selectedDatos28aWiz.getCodatA());
                    selectedBenef26aWiz.setCabenA(selectedDatos28aWiz.getCadatA());
                    selectedBenef26aWiz.setCpbenA(selectedDatos28aWiz.getCpdatA());
                    selectedBenef26aWiz.setTdbenA(selectedDatos28aWiz.getTddatA());
                    getUbica23CpList(selectedDatos28aWiz.getPadatA());
                }
            } else {

                selectedBenef26aWiz.setPabenA("");
                selectedBenef26aWiz.setEsbenA("");
                selectedBenef26aWiz.setMdbenA("");
                selectedBenef26aWiz.setLobenA("");
                selectedBenef26aWiz.setCobenA("");
                selectedBenef26aWiz.setCabenA("");
                selectedBenef26aWiz.setCpbenA("");
                selectedBenef26aWiz.setTdbenA("");
            }
        }
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

        if (add) {
            currentPercentage = newBenef26aWiz.getPobenA();
            validationPercentage = newBenef26aWiz.getPobenA();
            System.out.println("newBenef26aWiz != null" + isValidPercentage);
            System.out.println("newBenef26aWiz != null currentAviablePercentage" + Math.round(currentAviablePercentage));
            System.out.println("newBenef26aWiz != null validationPercentage" + Math.round(validationPercentage * 100));
            if (Math.round(currentAviablePercentage) < Math.round(validationPercentage * 100)) {
                isValidPercentage = false;
                System.out.println("aqui es2" + isValidPercentage);
                return "failure";
            }
        }

        if (selectedBenef26aWiz != null) {
            currentPercentage = selectedBenef26aWiz.getPobenA();
            validationPercentage = selectedBenef26aWiz.getPobenA();
            Benef26a actualBenef26Bean = new Benef26a();
            try {
                actualBenef26Bean = benef26aManager.getBenef26a(selectedBenef26aWiz.getId());
                validationPercentage = actualBenef26Bean.getPobenA();
            } catch (Benef26aNotFound ex) {
                validationPercentage = selectedBenef26aWiz.getPobenA();
            }
            System.out.println("selectedBenef26aWiz != null" + isValidPercentage);
            System.out.println("selectedBenef26aWiz != null currentPercentage" + Math.round(currentPercentage * 100));
            System.out.println("selectedBenef26aWiz != null validationPercentage" + Math.round(validationPercentage * 100));
            if (Math.round(currentPercentage * 100) <= Math.round(validationPercentage * 100)) {
                isValidPercentage = true;
            }
            System.out.println("selectedBenef26aWiz != null" + isValidPercentage);
            System.out.println("selectedBenef26aWiz != null VAL1" + Math.round(currentAviablePercentage + (validationPercentage * 100)));
            System.out.println("selectedBenef26aWiz != null VS" + Math.round(currentAviablePercentage + (currentPercentage * 100)));
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
            newBenef26aWiz.setStbenA(Constants.RECORD_ACTIVED);
            newBenef26aWiz.setFebenA(new Date());
            newBenef26aWiz.setUsbenA(FacesUtil.getUserName());
            newBenef26aWiz.setPobenA(newBenef26aWiz.getPobenA());

            if (validateBenef26aPercentage(newBenef26aWiz.getPobenA()).equals("failure")) {
                infoMessage = "No se puede continuar revise el porcentaje";
                getBenef26aList();
                getPercentage();
                FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
                return "failure";
            }

            selectedBenef26aWiz = benef26aManager.registerBenef26a(newBenef26aWiz);
            newBenef26aWiz = null;
            add = false;
            infoMessage = "El registro se creo correctamente";
            getBenef26aList();
            getPercentage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            return "success";
        } catch (Benef26aAlreadyExists ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        } catch (EJBException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return "failure";
    }

    /**
     *
     * @return
     */
    public Benef26a prepareCreateBenef26a() {
        newBenef26aWiz = new Benef26a();
        newBenef26aWiz.setRfbenA(rfc);
        newBenef26aWiz.setPobenA(getPercentage(rfc) / 100);
        System.out.println(newBenef26aWiz.getPobenA());
        add = true;
        benef26aTab = 0;
        return newBenef26aWiz;
    }

    /**
     *
     */
    public void prepareSelectBenef26a() {
        getUbica23CpListBen(selectedBenef26aWiz.getCpbenA());
        add = false;
    }

    /**
     *
     */
    public void prepareSelectExper31a() {
        getUbica23CpListExp(selectedExper31aWiz.getCpexpA());
        add = false;
    }

    /**
     *
     * @return
     */
    public String deleteBenef26a() {
        try {

            selectedBenef26aWiz.setStbenA(Constants.RECORD_DELETED);
            selectedBenef26aWiz.setFebenA(new Date());
            selectedBenef26aWiz.setUsbenA(FacesUtil.getUserName());

            benef26aManager.deleteBenef26a(selectedBenef26aWiz);

            selectedBenef26aWiz = null;

            infoMessage = "Beneficiario eliminado correctamente";

        } catch (Benef26aNotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR, infoMessageModule, infoMessage);
        }
        return null;

    }

    /**
     *
     * @param event
     */
    public void handleChangeEv(ValueChangeEvent event) {
        System.out.println("here " + event.getNewValue());
    }

    /**
     *
     * @return
     */
    public String updateBenef26a() {
        try {

            selectedBenef26aWiz.setStbenA(Constants.RECORD_ACTIVED);
            selectedBenef26aWiz.setFebenA(new Date());
            selectedBenef26aWiz.setUsbenA(FacesUtil.getUserName());

            if (path != null) {
                selectedBenef26aWiz.setPtbenA(path);
                path = null;
            }

            if (benef26aTab == 0) {
                if (validateBenef26aPercentage(selectedBenef26aWiz.getPobenA()).equals("failure")) {
                    infoMessage = "No se puede continuar revise el porcentaje";
                    getBenef26aList();
                    getPercentage();
                    FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
                    return "failure";
                }
            }
            benef26aManager.updateBenef26a(selectedBenef26aWiz);
            getBenef26aList();
            getPercentage();
            selectedBenef26aWiz = null;
            infoMessage = "El registro se actualizo correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Benef26aNotFound ex) {
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
    public Docum29a prepareCreateDocum29a() {
        newDocum29aWiz = new Docum29a();
        newDocum29aWiz.setRfdocA(rfc);
        return newDocum29aWiz;
    }

    /**
     *
     * @param docum29a
     */
    public void prepareSelectDocum29a(Docum29a docum29a) {
        selectedDocum29aWiz = docum29a;

        add = false;
    }

    /**
     *
     * @return
     */
    public String updateDocum29aFoto() {
        if (newDocum29aWiz != null) {
            newDocum29aWiz.setPadocA(null);
        }
        if (selectedDocum29aWiz != null) {
            selectedDocum29aWiz.setPadocA(null);
        }
        FacesUtil.removeManagedBeanInSession("fileUploadBean");
        String oldInfoMessageModule = infoMessageModule;
        infoMessageModule = "Documento";
        infoMessage = "Se borro el Documento";
        FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
        infoMessageModule = oldInfoMessageModule;

        return null;
    }

    /**
     *
     * @param docum29a
     * @return
     */
    public String deleteDocum29a(Docum29a docum29a) {
        System.out.println("Backing Este es el id del documento que voy a borrar: " + docum29a.getIddocA());
        try {

            docum29a.setStdocA(Constants.RECORD_DELETED);
            docum29a.setFedocA(new Date());
            docum29a.setUsdocA(FacesUtil.getUserName());

            docum29aManager.deleteDocum29a(docum29a);

            selectedDocum29aWiz = null;

            infoMessage = "Documento borrado";

        } catch (Docum29aNotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al eliminar el Documento";
            FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String updateDocum29a() {
        try {

            selectedDocum29aWiz.setStdocA(Constants.RECORD_ACTIVED);
            selectedDocum29aWiz.setFedocA(new Date());
            selectedDocum29aWiz.setUsdocA(FacesUtil.getUserName());

            if (path != null) {
                selectedDocum29aWiz.setPadocA(path);
                path = null;
            }

            docum29aManager.updateDocum29a(selectedDocum29aWiz);

            infoMessage = "El registro se actualizo correctamente";
            return "success";
        } catch (Docum29aNotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al actualizar el Documento";
            FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR, infoMessageModule, infoMessage);
        }
        return "failure";
    }

    /**
     *
     * @return
     */
    public String registerDocum29a() {

        try {

            newDocum29aWiz.setStdocA(Constants.RECORD_ACTIVED);
            newDocum29aWiz.setFedocA(new Date());
            newDocum29aWiz.setUsdocA(FacesUtil.getUserName());

            if (path != null) {
                newDocum29aWiz.setPadocA(path);
                path = null;
            }

            docum29aManager.registerDocum29a(newDocum29aWiz);

            infoMessage = "El registro se creo correctamente";

            newDocum29aWiz = new Docum29a();

            return "success";

        } catch (Docum29aAlreadyExists ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR, infoMessageModule, infoMessage);
        } catch (EJBException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return "failure";
    }

    /**
     *
     * @return
     */
    public Escol30a prepareCreateEscol30a() {
        newEscol30aWiz = new Escol30a();
        newEscol30aWiz.setRfescA(rfc);
        return newEscol30aWiz;
    }

    /**
     *
     */
    public void prepareSelectEscol30a() {

    }

    /**
     *
     * @return
     */
    public String registerEscol30a() {

        try {

            newEscol30aWiz.setStescA(Constants.RECORD_ACTIVED);
            newEscol30aWiz.setFeescA(new Date());
            newEscol30aWiz.setUsescA(FacesUtil.getUserName());

            escol30aManager.registerEscol30a(newEscol30aWiz);

            infoMessage = "El registro se creo correctamente";
            newEscol30aWiz = new Escol30a();
            return "success";
        } catch (Escol30aAlreadyExists ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Los datos personales del aspirante ya se encuentran en la base de datos";
            FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR, infoMessageModule, infoMessage);

        } catch (EJBException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return "failure";
    }

    /**
     *
     * @param rfescA
     */
    public void getListEscol30a(String rfescA) {

        escol30aList = escol30aManager.getListEscol30a(rfescA);

        if (!escol30aList.isEmpty()) {
            infoMessage = escol30aList.size() + " registro(s) de escolaridad";
        }
    }

    /**
     *
     * @return
     */
    public String updateEscol30a() {
        try {

            if (path != null) {
                selectedEscol30aWiz.setPaescA(path);
                path = null;
            }

            String diasList = "";

            System.out.println(dias.getTarget().size());

            for (String mydays2 : dias.getTarget()) {
                System.out.println(mydays2 + "dias target");
                diasList = diasList + "," + mydays2;
            }

            selectedEscol30aWiz.setHcescA("Dias " + diasList + " De las " + escol30aI + " a las " + escol30aF + " Hrs");
            selectedEscol30aWiz.setStescA(Constants.RECORD_ACTIVED);
            selectedEscol30aWiz.setFeescA(new Date());
            selectedEscol30aWiz.setUsescA(FacesUtil.getUserName());

            escol30aManager.updateEscol30a(selectedEscol30aWiz);

            infoMessage = "El registro se actualizo correctamente";

        } catch (Escol30aNotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al actualizar el registro del Aspirante";
            FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteEscol30a() {
        try {

            selectedEscol30aWiz.setStescA(Constants.RECORD_DELETED);
            selectedEscol30aWiz.setFeescA(new Date());
            selectedEscol30aWiz.setUsescA(FacesUtil.getUserName());

            escol30aManager.deleteEscol30a(selectedEscol30aWiz);

            selectedEscol30aWiz = null;

            infoMessage = "Registro de Escolaridad eliminado correctamente";

        } catch (Escol30aNotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al eliminar el registro del Aspirante";
            FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public Exper31a prepareCreateExper31a() {
        newExper31aWiz = new Exper31a();
        newExper31aWiz.setRfexpA(rfc);
        return newExper31aWiz;
    }

    /**
     *
     * @return
     */
    public String registerExper31a() {

        try {

            newExper31aWiz.setStexpA(Constants.RECORD_ACTIVED);
            newExper31aWiz.setFeexpA(new Date());
            newExper31aWiz.setUsexpA(FacesUtil.getUserName());

            exper31aManager.registerExper31a(newExper31aWiz);

            infoMessage = "El registro se creo correctamente";

            newExper31aWiz = new Exper31a();

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
     * @param rfbenA
     */
    public void getListExper31a(String rfbenA) {

        exper31aList = exper31aManager.getListExper31a(rfbenA);

        if (exper31aList.isEmpty()) {
            infoMessage = "No existen experiencias!";
        } else {
            infoMessage = exper31aList.size() + " Experiencias";
        }
    }

    /**
     *
     * @return
     */
    public String updateExper31a() {
        try {

            if (path != null) {
                selectedExper31aWiz.setPtexpA(path);
                path = null;
            }

            selectedExper31aWiz.setStexpA(Constants.RECORD_ACTIVED);
            selectedExper31aWiz.setFeexpA(new Date());
            selectedExper31aWiz.setUsexpA(FacesUtil.getUserName());

            exper31aManager.updateExper31a(selectedExper31aWiz);

            infoMessage = "El registro se actualizo correctamente";

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

            selectedExper31aWiz.setStexpA(Constants.RECORD_DELETED);
            selectedExper31aWiz.setFeexpA(new Date());
            selectedExper31aWiz.setUsexpA(FacesUtil.getUserName());

            exper31aManager.deleteExper31a(selectedExper31aWiz);

            selectedExper31aWiz = null;

            infoMessage = "Experiencia eliminada correctamente";
        } catch (Exper31aNotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        }
        return null;

    }

    /**
     *
     */
    public void prepareSelectFamil32a() {
        getUbica23CpListFam(selectedFamil32aWiz.getCpfamA());
        add = false;
    }

    /**
     *
     * @return
     */
    public String registerFamil32a() {
        try {

            System.out.println("RFC famaddbacking" + newFamil32aWiz.getRffamA());

            newFamil32aWiz.setRffamA(selectedTraba36aWiz.getRftraA());
            newFamil32aWiz.setStfamA(Constants.RECORD_ACTIVED);
            newFamil32aWiz.setFefamA(new Date());
            newFamil32aWiz.setUsfamA(FacesUtil.getUserName());

            famil32aManager.registerFamil32a(newFamil32aWiz);

            infoMessage = "El registro se creo correctamente";

            newFamil32aWiz = new Famil32a();

        } catch (Famil32aAlreadyExists ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);

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
    public Famil32a prepareCreateFamil32a() {
        newFamil32aWiz = new Famil32a();
        newFamil32aWiz.setRffamA(rfc);
        newFamil32aWiz.setVifamA("si");
        return newFamil32aWiz;
    }

    /**
     *
     * @return
     */
    public String updateFamil32a() {
        try {

            if (path != null) {
                selectedFamil32aWiz.setPtfamA(path);
                path = null;
            }

            selectedFamil32aWiz.setStfamA(Constants.RECORD_ACTIVED);
            selectedFamil32aWiz.setFefamA(new Date());
            selectedFamil32aWiz.setUsfamA(FacesUtil.getUserName());

            famil32aManager.updateFamil32a(selectedFamil32aWiz);

            infoMessage = "El registro se actualizo correctamente";

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

            selectedFamil32aWiz.setStfamA(Constants.RECORD_DELETED);
            selectedFamil32aWiz.setFefamA(new Date());
            selectedFamil32aWiz.setUsfamA(FacesUtil.getUserName());

            famil32aManager.deleteFamil32a(selectedFamil32aWiz);

            selectedFamil32aWiz = null;

            infoMessage = "Familiar eliminado correctamente";
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
    public Famil32a getSelectedFamil32aWiz() {
        return selectedFamil32aWiz;
    }

    /**
     *
     * @param selectedFamil32aWiz
     */
    public void setSelectedFamil32aWiz(Famil32a selectedFamil32aWiz) {
        this.selectedFamil32aWiz = selectedFamil32aWiz;
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
    public Datos28aManagerLocal getDatos28aManager() {
        return datos28aManager;
    }

    /**
     *
     * @param datos28aManager
     */
    public void setDatos28aManager(Datos28aManagerLocal datos28aManager) {
        this.datos28aManager = datos28aManager;
    }

    /**
     *
     * @return
     */
    public Requi54ManagerLocal getRequi54Manager() {
        return requi54Manager;
    }

    /**
     *
     * @param requi54Manager
     */
    public void setRequi54Manager(Requi54ManagerLocal requi54Manager) {
        this.requi54Manager = requi54Manager;
    }

    /**
     *
     * @return
     */
    public Requi54 getSelectedRequi54Wiz() {
        return selectedRequi54Wiz;
    }

    /**
     *
     * @param selectedRequi54Wiz
     */
    public void setSelectedRequi54Wiz(Requi54 selectedRequi54Wiz) {
        this.selectedRequi54Wiz = selectedRequi54Wiz;
    }

    /**
     *
     * @return
     */
    public Requi54 getNewRequi54Wiz() {
        return newRequi54Wiz;
    }

    /**
     *
     * @param newRequi54Wiz
     */
    public void setNewRequi54Wiz(Requi54 newRequi54Wiz) {
        this.newRequi54Wiz = newRequi54Wiz;
    }

    /**
     *
     * @return
     */
    public Ident49aManagerLocal getIdent49aManager() {
        return ident49aManager;
    }

    /**
     *
     * @param ident49aManager
     */
    public void setIdent49aManager(Ident49aManagerLocal ident49aManager) {
        this.ident49aManager = ident49aManager;
    }

    /**
     *
     * @return
     */
    public boolean isRegisteredDatos28a() {
        return registeredDatos28a;
    }

    /**
     *
     * @param registeredDatos28a
     */
    public void setRegisteredDatos28a(boolean registeredDatos28a) {
        this.registeredDatos28a = registeredDatos28a;
    }

    /**
     *
     * @return
     */
    public boolean isRegisteredIdent49a() {
        return registeredIdent49a;
    }

    /**
     *
     * @param registeredIdent49a
     */
    public void setRegisteredIdent49a(boolean registeredIdent49a) {
        this.registeredIdent49a = registeredIdent49a;
    }

    /**
     *
     * @return
     */
    public Datos28a getSelectedDatos28aWiz() {
        return selectedDatos28aWiz;
    }

    /**
     *
     * @param selectedDatos28aWiz
     */
    public void setSelectedDatos28aWiz(Datos28a selectedDatos28aWiz) {
        this.selectedDatos28aWiz = selectedDatos28aWiz;
    }

    /**
     *
     * @return
     */
    public Datos28a getNewDatos28aWiz() {
        return newDatos28aWiz;
    }

    /**
     *
     * @param newDatos28aWiz
     */
    public void setNewDatos28aWiz(Datos28a newDatos28aWiz) {
        this.newDatos28aWiz = newDatos28aWiz;
    }

    /**
     *
     * @return
     */
    public Ident49a getSelectedIdent49aWiz() {
        return selectedIdent49aWiz;
    }

    /**
     *
     * @param selectedIdent49aWiz
     */
    public void setSelectedIdent49aWiz(Ident49a selectedIdent49aWiz) {
        this.selectedIdent49aWiz = selectedIdent49aWiz;
    }

    /**
     *
     * @return
     */
    public Ident49a getNewIdent49aWiz() {
        return newIdent49aWiz;
    }

    /**
     *
     * @param newIdent49aWiz
     */
    public void setNewIdent49aWiz(Ident49a newIdent49aWiz) {
        this.newIdent49aWiz = newIdent49aWiz;
    }

    /**
     *
     * @return
     */
    public boolean isActiveTraba36a() {
        return activeTraba36a;
    }

    /**
     *
     * @param activeTraba36a
     */
    public void setActiveTraba36a(boolean activeTraba36a) {
        this.activeTraba36a = activeTraba36a;
    }

    /**
     *
     * @return
     */
    public boolean isRegisteredTraba36a() {
        return registeredTraba36a;
    }

    /**
     *
     * @param registeredTraba36a
     */
    public void setRegisteredTraba36a(boolean registeredTraba36a) {
        this.registeredTraba36a = registeredTraba36a;
    }

    /**
     *
     * @return
     */
    public Traba36a getSelectedTraba36aWiz() {
        return selectedTraba36aWiz;
    }

    /**
     *
     * @param selectedTraba36aWiz
     */
    public void setSelectedTraba36aWiz(Traba36a selectedTraba36aWiz) {
        this.selectedTraba36aWiz = selectedTraba36aWiz;
    }

    /**
     *
     * @return
     */
    public Traba36 getSelectedTraba36Wiz() {
        return selectedTraba36Wiz;
    }

    /**
     *
     * @param selectedTraba36Wiz
     */
    public void setSelectedTraba36Wiz(Traba36 selectedTraba36Wiz) {
        this.selectedTraba36Wiz = selectedTraba36Wiz;
    }

    /**
     *
     * @return
     */
    public Traba36a getNewTraba36aWiz() {
        return newTraba36aWiz;
    }

    /**
     *
     * @param newTraba36aWiz
     */
    public void setNewTraba36aWiz(Traba36a newTraba36aWiz) {
        this.newTraba36aWiz = newTraba36aWiz;
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
    public String getInfoMessageAsp() {
        return infoMessageAsp;
    }

    /**
     *
     * @param infoMessageAsp
     */
    public void setInfoMessageAsp(String infoMessageAsp) {
        this.infoMessageAsp = infoMessageAsp;
    }

    /**
     *
     * @return
     */
    public String getInfoMessageTra() {
        return infoMessageTra;
    }

    /**
     *
     * @param infoMessageTra
     */
    public void setInfoMessageTra(String infoMessageTra) {
        this.infoMessageTra = infoMessageTra;
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
    public Traba36aManagerLocal getTraba36aManager() {
        return traba36aManager;
    }

    /**
     *
     * @param traba36aManager
     */
    public void setTraba36aManager(Traba36aManagerLocal traba36aManager) {
        this.traba36aManager = traba36aManager;
    }

    /**
     *
     * @return
     */
    public Traba36ManagerLocal getTraba36Manager() {
        return traba36Manager;
    }

    /**
     *
     * @param traba36Manager
     */
    public void setTraba36Manager(Traba36ManagerLocal traba36Manager) {
        this.traba36Manager = traba36Manager;
    }

    /**
     *
     * @return
     */
    public List<Famil32a> getFamil32aList() {
        famil32aList = famil32aManager.getListFamil32a(rfc);
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
    public Docum29aManagerLocal getDocum29aManager() {
        return docum29aManager;
    }

    /**
     *
     * @param docum29aManager
     */
    public void setDocum29aManager(Docum29aManagerLocal docum29aManager) {
        this.docum29aManager = docum29aManager;
    }

    /**
     *
     * @return
     */
    public List<Docum29a> getdocum29aList() {
        docum29aList = docum29aManager.getListDocum29a(rfc);
        return docum29aList;
    }

    /**
     *
     * @param docum29aList
     */
    public void setDocum29aList(List<Docum29a> docum29aList) {
        this.docum29aList = docum29aList;
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
    public List<Exper31a> getExper31aList() {
        exper31aList = exper31aManager.getListExper31a(rfc);
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
    public Exper31a getSelectedExper31aWiz() {
        return selectedExper31aWiz;
    }

    /**
     *
     * @param selectedExper31aWiz
     */
    public void setSelectedExper31aWiz(Exper31a selectedExper31aWiz) {
        this.selectedExper31aWiz = selectedExper31aWiz;
    }

    /**
     *
     * @return
     */
    public Exper31a getNewExper31aWiz() {
        return newExper31aWiz;
    }

    /**
     *
     * @param newExper31aWiz
     */
    public void setNewExper31aWiz(Exper31a newExper31aWiz) {
        this.newExper31aWiz = newExper31aWiz;
    }

    /**
     *
     * @return
     */
    public Escol30aManagerLocal getEscol30aManager() {
        return escol30aManager;
    }

    /**
     *
     * @param escol30aManager
     */
    public void setEscol30aManager(Escol30aManagerLocal escol30aManager) {
        this.escol30aManager = escol30aManager;
    }

    /**
     *
     * @return
     */
    public List<Escol30a> getEscol30aList() {
        escol30aList = escol30aManager.getListEscol30a(rfc);
        return escol30aList;
    }

    /**
     *
     * @param escol30aList
     */
    public void setEscol30aList(List<Escol30a> escol30aList) {
        this.escol30aList = escol30aList;
    }

    /**
     *
     * @return
     */
    public Escol30a getSelectedEscol30aWiz() {
        return selectedEscol30aWiz;
    }

    /**
     *
     * @param selectedEscol30aWiz
     */
    public void setSelectedEscol30aWiz(Escol30a selectedEscol30aWiz) {
        this.selectedEscol30aWiz = selectedEscol30aWiz;
    }

    /**
     *
     * @return
     */
    public Escol30a getNewEscol30aWiz() {
        return newEscol30aWiz;
    }

    /**
     *
     * @param newEscol30aWiz
     */
    public void setNewEscol30aWiz(Escol30a newEscol30aWiz) {
        this.newEscol30aWiz = newEscol30aWiz;
    }

    /**
     *
     * @return
     */
    public Benef26a getNewBenef26aWiz() {
        return newBenef26aWiz;
    }

    /**
     *
     * @param newBenef26aWiz
     */
    public void setNewBenef26aWiz(Benef26a newBenef26aWiz) {
        this.newBenef26aWiz = newBenef26aWiz;
    }

    /**
     *
     * @return
     */
    public Famil32a getNewFamil32aWiz() {
        return newFamil32aWiz;
    }

    /**
     *
     * @param newFamil32aWiz
     */
    public void setNewFamil32aWiz(Famil32a newFamil32aWiz) {
        this.newFamil32aWiz = newFamil32aWiz;
    }

    /**
     *
     * @return
     */
    public Docum29a getSelectedDocum29aWiz() {
        return selectedDocum29aWiz;
    }

    /**
     *
     * @param selectedDocum29aWiz
     */
    public void setSelectedDocum29aWiz(Docum29a selectedDocum29aWiz) {
        this.selectedDocum29aWiz = selectedDocum29aWiz;
    }

    /**
     *
     * @return
     */
    public Docum29a getNewDocum29aWiz() {
        return newDocum29aWiz;
    }

    /**
     *
     * @param newDocum29aWiz
     */
    public void setNewDocum29aWiz(Docum29a newDocum29aWiz) {
        this.newDocum29aWiz = newDocum29aWiz;
    }

    /**
     *
     * @return
     */
    public List<Traba36> getTraba36List() {
        return traba36List;
    }

    /**
     *
     * @param traba36List
     */
    public void setTraba36List(List<Traba36> traba36List) {
        this.traba36List = traba36List;
    }

    /**
     *
     * @return
     */
    public List<Traba36a> getTraba36aList() {
        return traba36aList;
    }

    /**
     *
     * @param traba36aList
     */
    public void setTraba36aList(List<Traba36a> traba36aList) {
        this.traba36aList = traba36aList;
    }

    /**
     *
     * @return
     */
    public List<Benef26a> getBenef26aList() {
        benef26aList = benef26aManager.getListBenef26a(rfc);
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("panelForm:beneficiarioListForm:beneficiarioDatalist");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("beneficiarioCreateForm");

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
    public Benef26a getSelectedBenef26aWiz() {
        return selectedBenef26aWiz;
    }

    /**
     *
     * @param selectedBenef26aWiz
     */
    public void setSelectedBenef26aWiz(Benef26a selectedBenef26aWiz) {
        this.selectedBenef26aWiz = selectedBenef26aWiz;
    }

    /**
     *
     * @return
     */
    public Benef26aManagerLocal getBenef26aManager() {
        return benef26aManager;
    }

    /**
     *
     * @param benef26aManager
     */
    public void setBenef26aManager(Benef26aManagerLocal benef26aManager) {
        this.benef26aManager = benef26aManager;
    }

    /**
     *
     * @return
     */
    public String getImss() {
        return imss;
    }

    /**
     *
     * @param imss
     */
    public void setImss(String imss) {
        this.imss = imss;
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
    public String getCurp() {
        return curp;
    }

    /**
     *
     * @param curp
     */
    public void setCurp(String curp) {
        this.curp = curp;
    }

    /**
     *
     * @return
     */
    public String getAp() {
        return ap;
    }

    /**
     *
     * @param ap
     */
    public void setAp(String ap) {
        System.out.println(ap);
        this.ap = ap;
    }

    /**
     *
     * @return
     */
    public String getAm() {
        return am;
    }

    /**
     *
     * @param am
     */
    public void setAm(String am) {
        System.out.println(am);
        this.am = am;
    }

    /**
     *
     * @return
     */
    public String getN() {
        return n;
    }

    /**
     *
     * @param n
     */
    public void setN(String n) {
        System.out.println(n);
        this.n = n;
    }

    /**
     *
     * @return
     */
    public boolean isSkip() {
        return skip;
    }

    /**
     *
     * @param skip
     */
    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    /**
     *
     * @return
     */
    public boolean isBack() {
        return back;
    }

    /**
     *
     * @param back
     */
    public void setBack(boolean back) {
        this.back = back;
    }

    /**
     *
     * @return
     */
    public boolean isNext() {
        return next;
    }

    /**
     *
     * @param next
     */
    public void setNext(boolean next) {
        this.next = next;
    }

    /**
     *
     * @return
     */
    public String retrieveTraba36aListTraba36List() {
        selectedTraba36aWiz = null;
        traba36aList = null;
        traba36List = null;

        retrieveTraba36aList();
        retrieveTraba36List();

        if (traba36aList.size() > 0) {
            selectedTraba36aWiz = traba36aList.get(0);

            this.rfc = selectedTraba36aWiz.getRftraA();
            this.rfc = rfc.toUpperCase();

            this.add = false;
        } else {
            this.add = true;
            selectedTraba36aWiz = null;
        }

        getOutcome(true);

        return "resultadosBuscar";
    }

    /**
     *
     */
    public void retrieveTraba36List() {
        try {
            Boolean ex = false;
            Traba36 searchableTraba36 = new Traba36();
            List<Traba36> traba36ListCurp = new ArrayList<Traba36>();
            List<Traba36> traba36ListRfc = new ArrayList<Traba36>();
            List<Traba36> traba36ListNc = new ArrayList<Traba36>();
            List<Traba36> traba36ListImss = new ArrayList<Traba36>();

            HashMap<Integer, Traba36> map = new HashMap<Integer, Traba36>();

            Usuar24 usuar24 = FacesUtil.getUsuar24();

            if (imss != null && imss.length()
                    > 0) {
                searchableTraba36.setRitra(imss);
                traba36ListImss = traba36Manager.getListTraba36Wiz(searchableTraba36);
                System.out.println("Imss" + searchableTraba36.getRitra());
            }

            if (rfcSearch
                    != null && rfcSearch.length()
                    > 0) {
                searchableTraba36.setRftra(rfcSearch.toUpperCase());
                traba36ListRfc = traba36Manager.getListTraba36Wiz(searchableTraba36);
                System.out.println("Rfc" + searchableTraba36.getRftra());
            }
            if (curp
                    != null && curp.length()
                    > 0) {
                searchableTraba36.setRftra(null);
                searchableTraba36.setCutra(curp.toUpperCase());
                traba36ListCurp = traba36Manager.getListTraba36Wiz(searchableTraba36);
                System.out.println("Curp" + searchableTraba36.getCutra());
            }
            if (ap
                    != null && ap.length()
                    > 0) {
                searchableTraba36.setAptra(ap.toUpperCase());
                ex = true;
            }
            if (am
                    != null && am.length()
                    > 0) {
                searchableTraba36.setAmtra(am.toUpperCase());
                ex = true;
            }
            if (n
                    != null && n.length()
                    > 0) {
                searchableTraba36.setNotra(n.toUpperCase());
                ex = true;
            }

            if (ex) {
                searchableTraba36.setRftra(null);
                searchableTraba36.setCutra(null);

                System.out.println("Paterno" + searchableTraba36.getAptra());
                System.out.println("Materno" + searchableTraba36.getAmtra());
                System.out.println("Nombres" + searchableTraba36.getNotra());

                traba36ListNc = traba36Manager.getListTraba36Wiz(searchableTraba36);
            }

            traba36List = new ArrayList<Traba36>();

            System.out.println("Lista Imss" + traba36ListImss.size());

            if (!traba36ListImss.isEmpty()) {
                for (Traba36 traba36 : traba36ListImss) {
                    System.out.println(traba36.toString());
                    map.put(traba36.getNutra(), traba36);
                }
            }

            if (!traba36ListCurp.isEmpty()) {
                for (Traba36 traba36 : traba36ListCurp) {
                    map.put(traba36.getNutra(), traba36);
                }
            }

            if (!traba36ListRfc.isEmpty()) {
                for (Traba36 traba36 : traba36ListRfc) {
                    map.put(traba36.getNutra(), traba36);
                }
            }

            if (!traba36ListNc.isEmpty()) {
                for (Traba36 traba36 : traba36ListNc) {
                    map.put(traba36.getNutra(), traba36);
                }
            }

            traba36List = new ArrayList(map.values());

            if (!traba36List.isEmpty()) {
                infoMessageTra = traba36List.size() + " Trabajadores";
            } else {
                infoMessageTra = "";
            }

        } catch (SecurityException | IllegalArgumentException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public void retrieveTraba36aList() {
        try {
            Boolean ex = false;
            Traba36a searchableTraba36a = new Traba36a();
            List<Traba36a> traba36aListCurp = new ArrayList<Traba36a>();
            List<Traba36a> traba36aListRfc = new ArrayList<Traba36a>();
            List<Traba36a> traba36aListImss = new ArrayList<Traba36a>();
            List<Traba36a> traba36aListNc = new ArrayList<Traba36a>();

            HashMap<String, Traba36a> map = new HashMap<String, Traba36a>();

            if (imss != null && imss.length()
                    > 0) {
                searchableTraba36a.setRitraA(imss);
                traba36aListImss = traba36aManager.getListTraba36aWiz(searchableTraba36a);
                System.out.println("Imss" + searchableTraba36a.getRitraA());
            }

            if (rfcSearch
                    != null && rfcSearch.length()
                    > 0) {
                searchableTraba36a.setRftraA(rfcSearch.toUpperCase());
                traba36aListRfc = traba36aManager.getListTraba36aWiz(searchableTraba36a);
            }
            if (curp
                    != null && curp.length()
                    > 0) {
                searchableTraba36a.setCutraA(curp.toUpperCase());
                traba36aListCurp = traba36aManager.getListTraba36aWiz(searchableTraba36a);
            }
            if (ap
                    != null && ap.length()
                    > 0) {
                searchableTraba36a.setAptraA(ap.toUpperCase());
                ex = true;
            }
            if (am
                    != null && am.length()
                    > 0) {
                searchableTraba36a.setAmtraA(am.toUpperCase());
                ex = true;
            }
            if (n
                    != null && n.length()
                    > 0) {
                searchableTraba36a.setNotraA(n.toUpperCase());
                ex = true;
            }

            if (ex) {
                searchableTraba36a.setRftraA(null);
                searchableTraba36a.setCutraA(null);

                System.out.println("Paterno Aspirante" + searchableTraba36a.getAptraA());
                System.out.println("Materno Aspirante" + searchableTraba36a.getAmtraA());
                System.out.println("Nombres Aspirante" + searchableTraba36a.getNotraA());

                traba36aListNc = traba36aManager.getListTraba36aWiz(searchableTraba36a);

            }

            traba36aList = new ArrayList<Traba36a>();

            if (!traba36aListImss.isEmpty()) {
                for (Traba36a traba36a : traba36aListImss) {
                    System.out.println(traba36a.toString());
                    map.put(traba36a.getRftraA(), traba36a);
                }
            }

            if (!traba36aListCurp.isEmpty()) {
                for (Traba36a traba36a : traba36aListCurp) {
                    System.out.println(traba36a.toString());
                    map.put(traba36a.getRftraA(), traba36a);
                }
            }

            if (!traba36aListRfc.isEmpty()) {
                for (Traba36a traba36a : traba36aListRfc) {
                    System.out.println(traba36a.toString());
                    map.put(traba36a.getRftraA(), traba36a);
                }
            }

            if (!traba36aListNc.isEmpty()) {
                for (Traba36a traba36a : traba36aListNc) {
                    System.out.println(traba36a.toString());
                    map.put(traba36a.getRftraA(), traba36a);
                }
            }

            traba36aList = new ArrayList(map.values());

            if (!traba36aList.isEmpty()) {
                infoMessageAsp = +traba36aList.size() + " Aspirantes";
            } else {
                infoMessageAsp = "";
            }

        } catch (SecurityException | IllegalArgumentException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public void save() {
        switch (currentStep) {
            case "datos":
                saveTraba36a();
                FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
                break;
            case "fotografia":
                saveTraba36a();
                FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
                break;
            case "requisitos":
                saveRequi54();
                FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
                break;
            case "identidad":
                saveIdent49a();
                FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
                break;
            case "personales":
                saveDatos28a();
                FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
                break;
            default:
                break;
        }
    }

    /**
     *
     * @return
     */
    public String updateTraba36aFoto() {
        try {

            selectedTraba36aWiz.setPatraA(null);
            selectedTraba36aWiz.setSttraA(Constants.RECORD_ACTIVED);
            selectedTraba36aWiz.setFetraA(new Date());
            selectedTraba36aWiz.setUstraA(FacesUtil.getUserName());

            traba36aManager.updateTraba36a(selectedTraba36aWiz);
            FacesUtil.removeManagedBeanInSession("fileUploadBean");
            String oldInfoMessageModule = infoMessageModule;
            infoMessageModule = "Fotografia";
            infoMessage = "Se borro la fotografia del aspirante";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            infoMessageModule = oldInfoMessageModule;

        } catch (Traba36aNotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String updateBenef26aFoto() {
        selectedBenef26aWiz.setPtbenA(null);
        //selectedBenef26aWiz.setStbenA(Constants.RECORD_ACTIVED);
        //selectedBenef26aWiz.setFebenA(new Date());
        //selectedBenef26aWiz.setUsbenA(FacesUtil.getUserName());
        //benef26aManager.updateBenef26a(selectedBenef26aWiz);
        FacesUtil
                .resetManagedBean("fileUploadBean", FileUploadBean.class
                );
        String oldInfoMessageModule = infoMessageModule;
        infoMessageModule = "Beneficiarios";
        infoMessage = "Se borro el Documento del beneficiario";
        FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
        infoMessageModule = oldInfoMessageModule;

        return null;
    }

    /**
     *
     * @param event
     */
    public void prepareFileUpload(AjaxBehaviorEvent event) {

        String value = (String) ((ValueHolder) event.getSource()).getValue();
        String id = ((UIComponent) event.getSource()).getId();
        try {
            if (newDocum29aWiz != null) {

                newDocum29aWiz.setW1docA(tidoc18Manager.getTidoc18(value));
            }
            if (selectedDocum29aWiz != null) {

                selectedDocum29aWiz.setW1docA(tidoc18Manager.getTidoc18(value));

            }

        } catch (Tidoc18NotFound ex) {
            Logger.getLogger(Traba36aWizardBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(value); // Look, (new) value is already set.
    }

    /**
     *
     * @param event
     */
    public void prepareFileUploadExp(AjaxBehaviorEvent event) {

        String value = (String) ((ValueHolder) event.getSource()).getValue();
        String id = ((UIComponent) event.getSource()).getId();
        try {
            if (selectedExper31aWiz != null) {

                selectedExper31aWiz.setW1expA(tidoc18Manager.getTidoc18(value));

            }

        } catch (Tidoc18NotFound ex) {
            Logger.getLogger(Traba36aWizardBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(value); // Look, (new) value is already set.
    }

    /**
     *
     */
    public void confirmFileUpload() {

        if (newDocum29aWiz != null) {
            newDocum29aWiz.setPadocA(path);
            System.out.println("atualiza create form " + currentStep);
        }
        if (selectedDocum29aWiz != null) {
            selectedDocum29aWiz.setPadocA(path);
        }
        System.out.println("ok aqui debio actualizar " + currentStep);

        FacesUtil.resetManagedBean("fileUploadBean", FileUploadBean.class);
    }

    /**
     *
     * @return
     */
    public String updateEscol30aFoto() {
        selectedEscol30aWiz.setPaescA(null);
        //selectedBenef26aWiz.setStbenA(Constants.RECORD_ACTIVED);
        //selectedBenef26aWiz.setFebenA(new Date());
        //selectedBenef26aWiz.setUsbenA(FacesUtil.getUserName());
        //benef26aManager.updateBenef26a(selectedBenef26aWiz);
        FacesUtil.removeManagedBeanInSession("fileUploadBean");
        String oldInfoMessageModule = infoMessageModule;
        infoMessageModule = "Documento de escolaridad";
        infoMessage = "Se borro el Documento de escolaridad";
        FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
        infoMessageModule = oldInfoMessageModule;

        return null;
    }

    /**
     *
     * @return
     */
    public String updateExper31aFoto() {
        selectedExper31aWiz.setPtexpA(null);
        //selectedBenef26aWiz.setStbenA(Constants.RECORD_ACTIVED);
        //selectedBenef26aWiz.setFebenA(new Date());
        //selectedBenef26aWiz.setUsbenA(FacesUtil.getUserName());
        //benef26aManager.updateBenef26a(selectedBenef26aWiz);
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
    public String updateFamil32aFoto() {
        selectedFamil32aWiz.setPtfamA(null);
        //selectedBenef26aWiz.setStbenA(Constants.RECORD_ACTIVED);
        //selectedBenef26aWiz.setFebenA(new Date());
        //selectedBenef26aWiz.setUsbenA(FacesUtil.getUserName());
        //benef26aManager.updateBenef26a(selectedBenef26aWiz);
        FacesUtil.removeManagedBeanInSession("fileUploadBean");
        String oldInfoMessageModule = infoMessageModule;
        infoMessageModule = "Documento de Familiar";
        infoMessage = "Se borro el Documento de Familiar";
        FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
        infoMessageModule = oldInfoMessageModule;

        return null;
    }

    /**
     *
     * @return
     */
    public String updateTraba36a() {
        try {

            if (path != null) {
                selectedTraba36aWiz.setPatraA(path);
                path = null;
            }

            selectedTraba36aWiz.setSttraA(Constants.RECORD_ACTIVED);
            selectedTraba36aWiz.setFetraA(new Date());
            selectedTraba36aWiz.setUstraA(FacesUtil.getUserName());

            traba36aManager.updateTraba36a(selectedTraba36aWiz);

            infoMessage = "El registro se actualizo correctamente";

            return "success";
        } catch (Traba36aNotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR, infoMessageModule, infoMessage);
        }
        return "failure";
    }

    /**
     *
     * @return
     */
    public String registerTraba36a() {

        try {

            if (path != null) {
                newTraba36aWiz.setPatraA(path);
                path = null;
            }

            newTraba36aWiz.setRftraA(rfcSearch.toUpperCase());

            newTraba36aWiz.setSttraA(Constants.RECORD_ACTIVED);
            newTraba36aWiz.setFetraA(new Date());
            newTraba36aWiz.setUstraA(FacesUtil.getUserName());

            selectedTraba36aWiz = traba36aManager.registerTraba36a(newTraba36aWiz);

            this.registeredTraba36a = true;
            newTraba36aWiz = null;
            rfc = selectedTraba36aWiz.getRftraA();

            infoMessage = "El registro se creo correctamente";

            return "success";
        } catch (Traba36aAlreadyExists ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR, infoMessageModule, infoMessage);
        } catch (EJBException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return "failure";
    }

    /**
     *
     */
    public void getTraba36aRequest() {
        try {

            this.selectedTraba36aWiz = traba36aManager.getTraba36a(rfc);

            this.rfc = selectedTraba36aWiz.getRftraA().toUpperCase();
            this.rfcSearch = rfc;
            System.out.println("este es el rfc" + rfc);
            System.out.println("este es el rfc" + rfcSearch);

            FacesUtil.setSessionBeanAttValue("userSessionBean",
                    "setAspirante",
                    selectedTraba36aWiz.getRftraA(),
                    String.class);

        } catch (Traba36aNotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            //FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }
    }

    /**
     *
     * @return
     */
    public String updateDatos28a() {
        try {

            if (selectedDatos28aWiz != null) {
                selectedDatos28aWiz.setPadatA(selectedDatos28aWiz.getPadatA());
                selectedDatos28aWiz.setEsdatA(selectedDatos28aWiz.getEsdatA());
                selectedDatos28aWiz.setMddatA(selectedDatos28aWiz.getMddatA());
                selectedDatos28aWiz.setLodatA(selectedDatos28aWiz.getLodatA());
                selectedDatos28aWiz.setCodatA(selectedDatos28aWiz.getCodatA());

                selectedDatos28aWiz.setCpdatA(selectedDatos28aWiz.getCpdatA());

                selectedDatos28aWiz.setStdatA(Constants.RECORD_ACTIVED);
                selectedDatos28aWiz.setFedatA(new Date());
                selectedDatos28aWiz.setUsdatA(FacesUtil.getUserName());

                datos28aManager.updateDatos28a(selectedDatos28aWiz);

                infoMessage = "El registro se actualizo correctamente";
            }

            return "success";
        } catch (Datos28aNotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR, infoMessageModule, infoMessage);
        }
        return "failure";
    }

    /**
     *
     * @return
     */
    public String registerDatos28a() {

        try {
            newDatos28aWiz.setRfdatA(rfcSearch);
            newDatos28aWiz.setStdatA(Constants.RECORD_ACTIVED);
            newDatos28aWiz.setFedatA(new Date());
            newDatos28aWiz.setUsdatA(FacesUtil.getUserName());

            selectedDatos28aWiz = datos28aManager.registerDatos28a(newDatos28aWiz);

            infoMessage = "El registro se creo correctamente";

            this.registeredDatos28a = true;

            newDatos28aWiz = null;

            return "success";
        } catch (Datos28aAlreadyExists ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR, infoMessageModule, infoMessage);

        } catch (EJBException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }
        return "failure";
    }

    /**
     *
     * @param rfdatA
     * @return
     */
    public Datos28a getDatos28aRequest(String rfdatA) {

        try {

            this.selectedDatos28aWiz = datos28aManager.getDatos28a(rfdatA);

        } catch (Datos28aNotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return selectedDatos28aWiz;
    }

    /**
     *
     * @return
     */
    public String updateRequi54() {
        try {

            selectedRequi54Wiz.setStreq(Constants.RECORD_ACTIVED);
            selectedRequi54Wiz.setFereq(new Date());
            selectedRequi54Wiz.setUsreq(FacesUtil.getUserName());

            requi54Manager.updateRequi54(selectedRequi54Wiz);

            infoMessage = "El registro se actualizo correctamente";

            return "success";
        } catch (Requi54NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR, infoMessageModule, infoMessage);
        }
        return "failure";
    }

    /**
     *
     * @return
     */
    public String registerRequi54() {

        try {
            newRequi54Wiz.setRfreq(rfcSearch);

            newRequi54Wiz.setPrreq(FacesUtil.getLoggedinUser().getNcusu());
            newRequi54Wiz.setFvreq(new Date());
            newRequi54Wiz.setApreq(selectedTraba36aWiz.getAptraA());
            newRequi54Wiz.setAmreq(selectedTraba36aWiz.getAmtraA());
            newRequi54Wiz.setNoreq(selectedTraba36aWiz.getNotraA());
            newRequi54Wiz.setStreq(Constants.RECORD_ACTIVED);
            newRequi54Wiz.setFereq(new Date());
            newRequi54Wiz.setUsreq(FacesUtil.getUserName());

            selectedRequi54Wiz = requi54Manager.registerRequi54(newRequi54Wiz);

            infoMessage = "El registro se creo correctamente";

            return "success";
        } catch (Requi54AlreadyExists ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR, infoMessageModule, infoMessage);

        } catch (EJBException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return "failure";
    }

    /**
     *
     * @param rfreq
     * @return
     */
    public Requi54 getRequi54Request(String rfreq) {

        try {

            this.selectedRequi54Wiz = requi54Manager.getRequi54(rfreq);

        } catch (Requi54NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return selectedRequi54Wiz;
    }

    /**
     *
     * @return
     */
    public String updateIdent49a() {
        try {

            selectedIdent49aWiz.setStideA(Constants.RECORD_ACTIVED);
            selectedIdent49aWiz.setFeideA(new Date());
            selectedIdent49aWiz.setUsideA(FacesUtil.getUserName());

            ident49aManager.updateIdent49a(selectedIdent49aWiz);

            infoMessage = "El registro se actualizo correctamente";

        } catch (Ident49aNotFound ex) {
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
    public String registerIdent49a() {

        try {

            newIdent49aWiz.setRfideA(rfcSearch);
            newIdent49aWiz.setStideA(Constants.RECORD_ACTIVED);
            newIdent49aWiz.setFeideA(new Date());
            newIdent49aWiz.setUsideA(FacesUtil.getUserName());

            selectedIdent49aWiz = ident49aManager.registerIdent49a(newIdent49aWiz);

            infoMessage = "El registro se creo correctamente";
            newIdent49aWiz = null;

        } catch (Ident49aAlreadyExists ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, infoMessageModule, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);

        } catch (EJBException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, infoMessageModule, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @param rfideA
     * @return
     */
    public Ident49a getIdent49aRequest(String rfideA) {

        try {

            this.selectedIdent49aWiz = ident49aManager.getIdent49a(rfideA);

        } catch (Ident49aNotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return selectedIdent49aWiz;
    }

    /**
     *
     * @param event
     * @return
     */
    public String onFlowProcessRegistro(FlowEvent event) {

        lastStep = event.getOldStep();
        currentStep = event.getNewStep();

        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("panelForm:botones");

        if (lastStep.equals("buscar") && currentStep.equals("consisa")) {
            back = true;
            next = true;
            save = false;
            getTraba36aRequest();
            if (selectedTraba36aWiz == null) {
                prepareCreateTraba36a();
            }
        }
        if (lastStep.equals("consisa") && currentStep.equals("buscar")) {
            back = false;
            next = false;
            save = false;
            selectedTraba36aWiz = null;

        }

        if (lastStep.equals("consisa") && currentStep.equals("datos")) {
            save = true;
        }

        if (lastStep.equals("datos") && currentStep.equals("consisa")) {
            save = false;
        }

        if (lastStep.equals("datos") && currentStep.equals("fotografia")) {
            saveTraba36a();
        }

        if (lastStep.equals("fotografia") && currentStep.equals("datos")) {

        }

        if (lastStep.equals("fotografia") && currentStep.equals("confirmacion")) {
            FacesUtil.runManagedBean("traba36aGeneradorOficiosBacking", "clearState");
            saveTraba36a();
            next = false;
            save = false;
        }

        if (lastStep.equals("confirmacion") && currentStep.equals("fotografia")) {
            next = true;
            save = true;

        }

        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirmacion";
        } else {
            return event.getNewStep();
        }
    }

    /**
     *
     * @param event
     * @return
     */
    public String onFlowProcessAfiliacion(FlowEvent event) {

        lastStep = event.getOldStep();
        currentStep = event.getNewStep();

        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("panelForm:botones");

        if (lastStep.equals("buscar") && currentStep.equals("datos")) {
            back = true;
            next = true;
            save = true;
            getTraba36aRequest();
            if (selectedTraba36aWiz == null) {
                prepareCreateTraba36a();
            }
        }
        if (lastStep.equals("datos") && currentStep.equals("buscar")) {
            back = false;
            next = false;
            save = false;
            selectedTraba36aWiz = null;

        }

        if (lastStep.equals("datos") && currentStep.equals("requisitos")) {
            saveTraba36a();
            getRequi54Request(rfc);
            if (selectedRequi54Wiz == null) {
                newRequi54Wiz.setPrreq(FacesUtil.getLoggedinUser().getNcusu());
                newRequi54Wiz.setFvreq(new Date());
                newRequi54Wiz.setRfreq(rfc);
                newRequi54Wiz.setApreq(selectedTraba36aWiz.getAptraA());
                newRequi54Wiz.setAmreq(selectedTraba36aWiz.getAmtraA());
                newRequi54Wiz.setNoreq(selectedTraba36aWiz.getNotraA());
            }
        }

        if (lastStep.equals("requisitos") && currentStep.equals("datos")) {

        }

        if (lastStep.equals("requisitos") && currentStep.equals("identidad")) {
            saveRequi54();
            getIdent49aRequest(rfc);
            if (selectedIdent49aWiz == null) {
                newIdent49aWiz.setRfideA(rfc);
                newIdent49aWiz.setCuideA(selectedTraba36aWiz.getCutraA());
                newIdent49aWiz.setRiideA(selectedTraba36aWiz.getRitraA());
            }
        }

        if (lastStep.equals("identidad") && currentStep.equals("requisitos")) {

        }

        if (lastStep.equals("identidad") && currentStep.equals("personales")) {
            saveIdent49a();
            getDatos28aRequest(rfc);
            if (selectedDatos28aWiz == null) {
                newDatos28aWiz.setRfdatA(rfc);
                newDatos28aWiz.setCudatA(selectedTraba36aWiz.getCutraA());
                add = true;
            } else {
                if (selectedDatos28aWiz.getCpdatA() != null && selectedDatos28aWiz.getCpdatA().length() == 5) {
                    getUbica23CpList(selectedDatos28aWiz.getCpdatA());
                }
                add = false;
            }
        }

        if (lastStep.equals("personales") && currentStep.equals("identitad")) {

        }

        if (lastStep.equals("personales") && currentStep.equals("beneficiarios")) {
            saveDatos28a();
            save = false;
        }

        if (lastStep.equals("beneficiarios") && currentStep.equals("personales")) {
            save = true;
        }

        if (lastStep.equals("beneficiarios") && currentStep.equals("escolaridad")) {
            save = false;
        }

        if (lastStep.equals("escolaridad") && currentStep.equals("beneficiarios")) {
            save = false;
        }

        if (lastStep.equals("escolaridad") && currentStep.equals("experiencia")) {
            save = false;
        }

        if (lastStep.equals("experiencia") && currentStep.equals("escolaridad")) {
            save = false;
        }

        if (lastStep.equals("experiencia") && currentStep.equals("familiares")) {
            save = false;
        }

        if (lastStep.equals("familiares") && currentStep.equals("documentacion")) {
            save = false;
        }

        if (lastStep.equals("familiares") && currentStep.equals("experiencia")) {
            save = false;
        }

        if (lastStep.equals("documentacion") && currentStep.equals("familiares")) {
            save = false;
        }

        if (lastStep.equals("documentacion") && currentStep.equals("confirmacion")) {
            Traba36aGeneradorOficiosBacking traba36aGeneradorOficiosBacking = FacesUtil.getManagedBean("traba36aGeneradorOficiosBacking", Traba36aGeneradorOficiosBacking.class);

            if (traba36aGeneradorOficiosBacking != null) {
                traba36aGeneradorOficiosBacking.clearState();
                FacesUtil.removeManagedBeanInSession("traba36aGeneradorOficiosBacking");
            }
            save = false;
            back = true;
            next = false;
        }

        if (lastStep.equals("confirmacion") && currentStep.equals("documentacion")) {
            back = true;
            next = true;
        }

        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirmacion";
        }

        return event.getNewStep();

    }

    /**
     *
     * @param fileName
     * @return
     */
    public String mimeTypeMap(final String fileName) {
        final MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
        return fileTypeMap.getContentType(fileName);
    }

    /**
     *
     * @return
     */
    public String getLastStep() {
        return lastStep;
    }

    /**
     *
     * @param lastStep
     */
    public void setLastStep(String lastStep) {
        this.lastStep = lastStep;
    }

    /**
     *
     * @return
     */
    public String getCurrentStep() {
        return currentStep;
    }

    /**
     *
     * @param currentStep
     */
    public void setCurrentStep(String currentStep) {
        this.currentStep = currentStep;
    }

    /**
     *
     * @return
     */
    public boolean isSave() {
        return save;
    }

    /**
     *
     * @param save
     */
    public void setSave(boolean save) {
        this.save = save;
    }

    /**
     *
     * @param vce
     */
    public void handleChange(AjaxBehaviorEvent vce) {
        String id = ((UIComponent) vce.getSource()).getId();
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
            case "rfcSearch":
                value = (String) ((ValueHolder) vce.getSource()).getValue();
                rfcSearch = value;
                if (rfcSearch != null) {
                    retrieveTraba36aListTraba36List();
                }
                break;
            case "bensn_dir":
                value = (String) ((ValueHolder) vce.getSource()).getValue();
                retrieveTraba36aToBenef26aDir();
                break;
            case "famsn_dir":
                value = (String) ((ValueHolder) vce.getSource()).getValue();
                retrieveTraba36aToFamil32aDir();
                break;
            default:
                break;
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
                newDatos28aWiz.setPadatA(name);
                if (selectedDatos28aWiz != null) {
                    selectedDatos28aWiz.setPadatA(name);
                }
                getUbica23Estados(name);

                municipios = null;
                ciudades = null;
                colonias = null;
                break;
            case "estado":
                if (add) {
                    newDatos28aWiz.setEsdatA(name);
                    getUbica23Municipios(newDatos28aWiz.getPadatA(), newDatos28aWiz.getEsdatA());
                }
                if (selectedDatos28aWiz != null) {
                    selectedDatos28aWiz.setEsdatA(name);
                    getUbica23Municipios(selectedDatos28aWiz.getPadatA(), selectedDatos28aWiz.getEsdatA());
                }
                ciudades = null;
                colonias = null;
                break;
            case "municipio":
                if (add) {
                    newDatos28aWiz.setMddatA(name);
                    getUbica23Ciudades(newDatos28aWiz.getPadatA(), newDatos28aWiz.getEsdatA(), newDatos28aWiz.getMddatA());
                }
                if (selectedDatos28aWiz != null) {
                    selectedDatos28aWiz.setMddatA(name);
                    getUbica23Ciudades(selectedDatos28aWiz.getPadatA(), selectedDatos28aWiz.getEsdatA(), selectedDatos28aWiz.getMddatA());
                }
                colonias = null;
                break;
            case "ciudad":
                if (add) {
                    newDatos28aWiz.setLodatA(name);
                    getUbica23Colonias(newDatos28aWiz.getPadatA(), newDatos28aWiz.getEsdatA(), newDatos28aWiz.getMddatA(), newDatos28aWiz.getLodatA());
                }
                if (selectedDatos28aWiz != null) {
                    selectedDatos28aWiz.setLodatA(name);
                    getUbica23Colonias(selectedDatos28aWiz.getPadatA(), selectedDatos28aWiz.getEsdatA(), selectedDatos28aWiz.getMddatA(), selectedDatos28aWiz.getLodatA());
                }
                break;
            case "colonia":
                if (add) {
                    newDatos28aWiz.setCodatA(name);
                    getUbica23Cp(newDatos28aWiz.getPadatA(), newDatos28aWiz.getEsdatA(), newDatos28aWiz.getMddatA(), newDatos28aWiz.getLodatA(), newDatos28aWiz.getCodatA());
                }
                if (selectedDatos28aWiz != null) {
                    selectedDatos28aWiz.setCodatA(name);
                    getUbica23Cp(selectedDatos28aWiz.getPadatA(), selectedDatos28aWiz.getEsdatA(), selectedDatos28aWiz.getMddatA(), selectedDatos28aWiz.getLodatA(), selectedDatos28aWiz.getCodatA());
                }
                break;
            case "codigopostal":

                if (newDatos28aWiz != null) {
                    newDatos28aWiz.setCpdatA(name);
                }

                if (selectedDatos28aWiz != null) {
                    selectedDatos28aWiz.setCpdatA(name);
                }

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
            case "paisben":

                newBenef26aWiz.setCpbenA(name);
                if (selectedBenef26aWiz != null) {
                    selectedBenef26aWiz.setPabenA(name);
                }
                getUbica23EstadosBen(name);

                municipios = null;
                ciudades = null;
                colonias = null;
                break;
            case "estadoben":
                if (add) {
                    newBenef26aWiz.setEsbenA(name);
                    getUbica23MunicipiosBen(newBenef26aWiz.getPabenA(), newBenef26aWiz.getEsbenA());
                }
                if (selectedBenef26aWiz != null) {
                    selectedBenef26aWiz.setEsbenA(name);
                    getUbica23MunicipiosBen(selectedBenef26aWiz.getPabenA(), selectedBenef26aWiz.getEsbenA());
                }

                ciudades = null;
                colonias = null;
                break;
            case "municipioben":
                if (add) {
                    newBenef26aWiz.setMdbenA(name);
                    getUbica23CiudadesBen(newBenef26aWiz.getPabenA(), newBenef26aWiz.getEsbenA(), newBenef26aWiz.getMdbenA());
                }
                if (selectedBenef26aWiz != null) {
                    selectedBenef26aWiz.setMdbenA(name);
                    getUbica23CiudadesBen(selectedBenef26aWiz.getPabenA(), selectedBenef26aWiz.getEsbenA(), selectedBenef26aWiz.getMdbenA());
                }

                colonias = null;
                break;
            case "ciudadben":

                if (add) {
                    newBenef26aWiz.setLobenA(name);
                    getUbica23ColoniasBen(newBenef26aWiz.getPabenA(), newBenef26aWiz.getEsbenA(), newBenef26aWiz.getMdbenA(), newBenef26aWiz.getLobenA());
                }
                if (selectedBenef26aWiz != null) {
                    selectedBenef26aWiz.setLobenA(name);
                    getUbica23ColoniasBen(selectedBenef26aWiz.getPabenA(), selectedBenef26aWiz.getEsbenA(), selectedBenef26aWiz.getMdbenA(), selectedBenef26aWiz.getLobenA());
                }
                break;
            case "coloniaben":

                if (add) {
                    newBenef26aWiz.setCobenA(name);
                    getUbica23CpBen(newBenef26aWiz.getPabenA(), newBenef26aWiz.getEsbenA(), newBenef26aWiz.getMdbenA(), newBenef26aWiz.getLobenA(), newBenef26aWiz.getCobenA());
                }
                if (selectedBenef26aWiz != null) {
                    selectedBenef26aWiz.setCobenA(name);
                    getUbica23CpBen(selectedBenef26aWiz.getPabenA(), selectedBenef26aWiz.getEsbenA(), selectedBenef26aWiz.getMdbenA(), selectedBenef26aWiz.getLobenA(), selectedBenef26aWiz.getCobenA());
                }

                break;
            case "codigopostalben":

                if (selectedBenef26aWiz != null) {
                    selectedBenef26aWiz.setCpbenA(name);
                }

                if (name.indexOf("_", 0) == -1) {
                    getUbica23CpBen(name);
                } else {

                    ubica23ListBen = null;
                    String var = name.replace("_", "");
                    if (var.length() == 4 || var.length() == 0) {

                        getUbica23PaisesBen();

                        estadosBen = null;
                        municipiosBen = null;
                        ciudadesBen = null;
                        coloniasBen = null;
                    }
                }
                break;
            case "paisfam":

                if (newFamil32aWiz != null) {
                    newFamil32aWiz.setCpfamA(name);
                }
                if (selectedFamil32aWiz != null) {
                    selectedFamil32aWiz.setPafamA(name);
                }
                getUbica23EstadosFam(name);

                municipios = null;
                ciudades = null;
                colonias = null;
                break;
            case "estadofam":
                if (add) {
                    newFamil32aWiz.setEsfamA(name);
                    getUbica23MunicipiosFam(newFamil32aWiz.getPafamA(), newFamil32aWiz.getEsfamA());
                }
                if (selectedFamil32aWiz != null) {
                    selectedFamil32aWiz.setEsfamA(name);
                    getUbica23MunicipiosFam(selectedFamil32aWiz.getPafamA(), selectedFamil32aWiz.getEsfamA());
                }

                ciudades = null;
                colonias = null;
                break;
            case "municipiofam":
                if (add) {
                    newFamil32aWiz.setMdfamA(name);
                    getUbica23CiudadesFam(newFamil32aWiz.getPafamA(), newFamil32aWiz.getEsfamA(), newFamil32aWiz.getMdfamA());
                }
                if (selectedFamil32aWiz != null) {
                    selectedFamil32aWiz.setMdfamA(name);
                    getUbica23CiudadesFam(selectedFamil32aWiz.getPafamA(), selectedFamil32aWiz.getEsfamA(), selectedFamil32aWiz.getMdfamA());
                }

                colonias = null;
                break;
            case "ciudadfam":

                if (add) {
                    newFamil32aWiz.setLofamA(name);
                    getUbica23ColoniasFam(newFamil32aWiz.getPafamA(), newFamil32aWiz.getEsfamA(), newFamil32aWiz.getMdfamA(), newFamil32aWiz.getLofamA());
                }
                if (selectedFamil32aWiz != null) {
                    selectedFamil32aWiz.setLofamA(name);
                    getUbica23ColoniasFam(selectedFamil32aWiz.getPafamA(), selectedFamil32aWiz.getEsfamA(), selectedFamil32aWiz.getMdfamA(), selectedFamil32aWiz.getLofamA());
                }
                break;
            case "coloniafam":

                if (add) {
                    newFamil32aWiz.setCofamA(name);
                    getUbica23CpFam(newFamil32aWiz.getPafamA(), newFamil32aWiz.getEsfamA(), newFamil32aWiz.getMdfamA(), newFamil32aWiz.getLofamA(), newFamil32aWiz.getCofamA());
                }
                if (selectedFamil32aWiz != null) {
                    selectedFamil32aWiz.setCofamA(name);
                    getUbica23CpFam(selectedFamil32aWiz.getPafamA(), selectedFamil32aWiz.getEsfamA(), selectedFamil32aWiz.getMdfamA(), selectedFamil32aWiz.getLofamA(), selectedFamil32aWiz.getCofamA());
                }

                break;
            case "codigopostalfam":

                if (selectedFamil32aWiz != null) {
                    selectedFamil32aWiz.setCpfamA(name);
                }

                if (name.indexOf("_", 0) == -1) {
                    getUbica23CpFam(name);
                } else {

                    ubica23ListFam = null;
                    String var = name.replace("_", "");
                    if (var.length() == 4 || var.length() == 0) {

                        getUbica23PaisesFam();

                        estadosFam = null;
                        municipiosFam = null;
                        ciudadesFam = null;
                        coloniasFam = null;
                    }
                }
                break;
            case "paisexp":

                if (selectedExper31aWiz != null) {
                    selectedExper31aWiz.setPaexpA(name);
                }
                getUbica23EstadosExp(name);

                municipios = null;
                ciudades = null;
                colonias = null;
                break;
            case "estadoexp":

                if (selectedExper31aWiz != null) {
                    selectedExper31aWiz.setEsexpA(name);
                    getUbica23MunicipiosExp(selectedExper31aWiz.getPaexpA(), selectedExper31aWiz.getEsexpA());
                }

                ciudades = null;
                colonias = null;
                break;
            case "municipioexp":

                if (selectedExper31aWiz != null) {
                    selectedExper31aWiz.setMdexpA(name);
                    getUbica23CiudadesFam(selectedExper31aWiz.getPaexpA(), selectedExper31aWiz.getEsexpA(), selectedExper31aWiz.getMdexpA());
                }

                colonias = null;
                break;
            case "ciudadexp":

                if (selectedExper31aWiz != null) {
                    selectedExper31aWiz.setLoexpA(name);
                    getUbica23ColoniasFam(selectedExper31aWiz.getPaexpA(), selectedExper31aWiz.getEsexpA(), selectedExper31aWiz.getMdexpA(), selectedExper31aWiz.getLoexpA());
                }
                break;
            case "coloniaexp":

                if (selectedExper31aWiz != null) {
                    selectedExper31aWiz.setCoexpA(name);
                    getUbica23CpFam(selectedExper31aWiz.getPaexpA(), selectedExper31aWiz.getEsexpA(), selectedExper31aWiz.getMdexpA(), selectedExper31aWiz.getLoexpA(), selectedExper31aWiz.getCoexpA());
                }

                break;
            case "codigopostalexp":

                if (selectedExper31aWiz != null) {
                    selectedExper31aWiz.setCpexpA(name);
                }

                if (name.indexOf("_", 0) == -1) {
                    getUbica23CpExp(name);
                } else {

                    ubica23ListExp = null;
                    String var = name.replace("_", "");
                    if (var.length() == 4 || var.length() == 0) {

                        getUbica23PaisesExp();

                        estadosExp = null;
                        municipiosExp = null;
                        ciudadesExp = null;
                        coloniasExp = null;
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
        if (add) {
            newDatos28aWiz.setCpdatA(ubica23Manager.getListUbica23Cp(pais, estado, municipio, ciudad, colonia));

        } else {
            if (selectedDatos28aWiz != null) {
                selectedDatos28aWiz.setCpdatA(ubica23Manager.getListUbica23Cp(pais, estado, municipio, ciudad, colonia));
            }
        }
    }

    /**
     *
     * @param cpubi
     */
    public void getUbica23Cp(String cpubi) {
        ubica23List = ubica23Manager.getListUbica23Cp(cpubi);

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

        newDatos28aWiz.setPadatA(ubica23List.get(0).getPaubi());
        newDatos28aWiz.setEsdatA(ubica23List.get(0).getEsubi());
        newDatos28aWiz.setMddatA(ubica23List.get(0).getMdubi());
        newDatos28aWiz.setLodatA(ubica23List.get(0).getLoubi());
        newDatos28aWiz.setCodatA(ubica23List.get(0).getCoubi());

        if (selectedDatos28aWiz != null) {
            selectedDatos28aWiz.setPadatA(ubica23List.get(0).getPaubi());
            selectedDatos28aWiz.setEsdatA(ubica23List.get(0).getEsubi());
            selectedDatos28aWiz.setMddatA(ubica23List.get(0).getMdubi());
            selectedDatos28aWiz.setLodatA(ubica23List.get(0).getLoubi());
            selectedDatos28aWiz.setCodatA(ubica23List.get(0).getCoubi());
        }

    }

    /**
     *
     */
    public void getUbica23PaisesBen() {

        paisesBen = ubica23Manager.getListUbica23Paises();
        Collections.sort(paisesBen, Collator.getInstance());

    }

    /**
     *
     * @param pais
     */
    public void getUbica23EstadosBen(String pais) {

        estadosBen = ubica23Manager.getListUbica23Estados(pais);
        Collections.sort(estadosBen, Collator.getInstance());

    }

    /**
     *
     * @param pais
     * @param estado
     */
    public void getUbica23MunicipiosBen(String pais, String estado) {

        municipiosBen = ubica23Manager.getListUbica23Municipios(pais, estado);
        Collections.sort(municipiosBen, Collator.getInstance());

    }

    /**
     *
     * @param pais
     * @param estado
     * @param municipio
     */
    public void getUbica23CiudadesBen(String pais, String estado, String municipio) {

        ciudadesBen = ubica23Manager.getListUbica23Ciudades(pais, estado, municipio);
        Collections.sort(ciudadesBen, Collator.getInstance());

    }

    /**
     *
     * @param pais
     * @param estado
     * @param municipio
     * @param ciudad
     */
    public void getUbica23ColoniasBen(String pais, String estado, String municipio, String ciudad) {

        coloniasBen = ubica23Manager.getListUbica23Colonias(pais, estado, municipio, ciudad);
        Collections.sort(coloniasBen, Collator.getInstance());

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

        newBenef26aWiz.setCpbenA(ubica23Manager.getListUbica23Cp(pais, estado, municipio, ciudad, colonia));

        if (selectedBenef26aWiz != null) {
            selectedBenef26aWiz.setCpbenA(ubica23Manager.getListUbica23Cp(pais, estado, municipio, ciudad, colonia));
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

        if (selectedBenef26aWiz != null) {
            selectedBenef26aWiz.setPabenA(ubica23ListBen.get(0).getPaubi());
            selectedBenef26aWiz.setEsbenA(ubica23ListBen.get(0).getEsubi());
            selectedBenef26aWiz.setMdbenA(ubica23ListBen.get(0).getMdubi());
            selectedBenef26aWiz.setLobenA(ubica23ListBen.get(0).getLoubi());
            selectedBenef26aWiz.setCobenA(ubica23ListBen.get(0).getCoubi());
        }

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
    public List<String> getPaisesBen() {
        return paisesBen;
    }

    /**
     *
     * @param paisesBen
     */
    public void setPaisesBen(List<String> paisesBen) {
        this.paisesBen = paisesBen;
    }

    /**
     *
     * @return
     */
    public List<String> getEstadosBen() {
        return estadosBen;
    }

    /**
     *
     * @param estadosBen
     */
    public void setEstadosBen(List<String> estadosBen) {
        this.estadosBen = estadosBen;
    }

    /**
     *
     * @return
     */
    public List<String> getMunicipiosBen() {
        return municipiosBen;
    }

    /**
     *
     * @param municipiosBen
     */
    public void setMunicipiosBen(List<String> municipiosBen) {
        this.municipiosBen = municipiosBen;
    }

    /**
     *
     * @return
     */
    public List<String> getCiudadesBen() {
        return ciudadesBen;
    }

    /**
     *
     * @param ciudadesBen
     */
    public void setCiudadesBen(List<String> ciudadesBen) {
        this.ciudadesBen = ciudadesBen;
    }

    /**
     *
     * @return
     */
    public List<String> getColoniasBen() {
        return coloniasBen;
    }

    /**
     *
     * @param coloniasBen
     */
    public void setColoniasBen(List<String> coloniasBen) {
        this.coloniasBen = coloniasBen;
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
    public void getUbica23PaisesFam() {

        paisesFam = ubica23Manager.getListUbica23Paises();
        Collections.sort(paisesFam, Collator.getInstance());

    }

    /**
     *
     * @param pais
     */
    public void getUbica23EstadosFam(String pais) {

        estadosFam = ubica23Manager.getListUbica23Estados(pais);
        Collections.sort(estadosFam, Collator.getInstance());

    }

    /**
     *
     * @param pais
     * @param estado
     */
    public void getUbica23MunicipiosFam(String pais, String estado) {

        municipiosFam = ubica23Manager.getListUbica23Municipios(pais, estado);
        Collections.sort(municipiosFam, Collator.getInstance());

    }

    /**
     *
     * @param pais
     * @param estado
     * @param municipio
     */
    public void getUbica23CiudadesFam(String pais, String estado, String municipio) {

        ciudadesFam = ubica23Manager.getListUbica23Ciudades(pais, estado, municipio);
        Collections.sort(ciudadesFam, Collator.getInstance());

    }

    /**
     *
     * @param pais
     * @param estado
     * @param municipio
     * @param ciudad
     */
    public void getUbica23ColoniasFam(String pais, String estado, String municipio, String ciudad) {

        coloniasFam = ubica23Manager.getListUbica23Colonias(pais, estado, municipio, ciudad);
        Collections.sort(coloniasFam, Collator.getInstance());

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

        newFamil32aWiz.setCpfamA(ubica23Manager.getListUbica23Cp(pais, estado, municipio, ciudad, colonia));

        if (selectedFamil32aWiz != null) {
            selectedFamil32aWiz.setCpfamA(ubica23Manager.getListUbica23Cp(pais, estado, municipio, ciudad, colonia));
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

        if (selectedFamil32aWiz != null) {
            selectedFamil32aWiz.setPafamA(ubica23ListFam.get(0).getPaubi());
            selectedFamil32aWiz.setEsfamA(ubica23ListFam.get(0).getEsubi());
            selectedFamil32aWiz.setMdfamA(ubica23ListFam.get(0).getMdubi());
            selectedFamil32aWiz.setLofamA(ubica23ListFam.get(0).getLoubi());
            selectedFamil32aWiz.setCofamA(ubica23ListFam.get(0).getCoubi());
        }

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
    public List<String> getPaisesFam() {
        return paisesFam;
    }

    /**
     *
     * @param paisesFam
     */
    public void setPaisesFam(List<String> paisesFam) {
        this.paisesFam = paisesFam;
    }

    /**
     *
     * @return
     */
    public List<String> getEstadosFam() {
        return estadosFam;
    }

    /**
     *
     * @param estadosFam
     */
    public void setEstadosFam(List<String> estadosFam) {
        this.estadosFam = estadosFam;
    }

    /**
     *
     * @return
     */
    public List<String> getMunicipiosFam() {
        return municipiosFam;
    }

    /**
     *
     * @param municipiosFam
     */
    public void setMunicipiosFam(List<String> municipiosFam) {
        this.municipiosFam = municipiosFam;
    }

    /**
     *
     * @return
     */
    public List<String> getCiudadesFam() {
        return ciudadesFam;
    }

    /**
     *
     * @param ciudadesFam
     */
    public void setCiudadesFam(List<String> ciudadesFam) {
        this.ciudadesFam = ciudadesFam;
    }

    /**
     *
     * @return
     */
    public List<String> getColoniasFam() {
        return coloniasFam;
    }

    /**
     *
     * @param coloniasFam
     */
    public void setColoniasFam(List<String> coloniasFam) {
        this.coloniasFam = coloniasFam;
    }

    /**
     *
     * @param query
     * @return
     */
    public List<String> especialidadLaboral(String query) {

        List<String> results = espec05Manager.getListEspec05Noesp(query);

        return results;
    }

    /**
     *
     */
    public void getUbica23PaisesExp() {

        paisesExp = ubica23Manager.getListUbica23Paises();
        Collections.sort(paisesExp, Collator.getInstance());

    }

    /**
     *
     * @param pais
     */
    public void getUbica23EstadosExp(String pais) {

        estadosExp = ubica23Manager.getListUbica23Estados(pais);
        Collections.sort(estadosExp, Collator.getInstance());

    }

    /**
     *
     * @param pais
     * @param estado
     */
    public void getUbica23MunicipiosExp(String pais, String estado) {

        municipiosExp = ubica23Manager.getListUbica23Municipios(pais, estado);
        Collections.sort(municipiosExp, Collator.getInstance());

    }

    /**
     *
     * @param pais
     * @param estado
     * @param municipio
     */
    public void getUbica23CiudadesExp(String pais, String estado, String municipio) {

        ciudadesExp = ubica23Manager.getListUbica23Ciudades(pais, estado, municipio);
        Collections.sort(ciudadesExp, Collator.getInstance());

    }

    /**
     *
     * @param pais
     * @param estado
     * @param municipio
     * @param ciudad
     */
    public void getUbica23ColoniasExp(String pais, String estado, String municipio, String ciudad) {

        coloniasExp = ubica23Manager.getListUbica23Colonias(pais, estado, municipio, ciudad);
        Collections.sort(coloniasExp, Collator.getInstance());

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

        if (selectedExper31aWiz != null) {
            selectedExper31aWiz.setCpexpA(ubica23Manager.getListUbica23Cp(pais, estado, municipio, ciudad, colonia));
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

        if (selectedExper31aWiz != null) {
            selectedExper31aWiz.setPaexpA(ubica23ListExp.get(0).getPaubi());
            selectedExper31aWiz.setEsexpA(ubica23ListExp.get(0).getEsubi());
            selectedExper31aWiz.setMdexpA(ubica23ListExp.get(0).getMdubi());
            selectedExper31aWiz.setLoexpA(ubica23ListExp.get(0).getLoubi());
            selectedExper31aWiz.setCoexpA(ubica23ListExp.get(0).getCoubi());
        }

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
    public List<String> getPaisesExp() {
        return paisesExp;
    }

    /**
     *
     * @param paisesExp
     */
    public void setPaisesExp(List<String> paisesExp) {
        this.paisesExp = paisesExp;
    }

    /**
     *
     * @return
     */
    public List<String> getEstadosExp() {
        return estadosExp;
    }

    /**
     *
     * @param estadosExp
     */
    public void setEstadosExp(List<String> estadosExp) {
        this.estadosExp = estadosExp;
    }

    /**
     *
     * @return
     */
    public List<String> getMunicipiosExp() {
        return municipiosExp;
    }

    /**
     *
     * @param municipiosExp
     */
    public void setMunicipiosExp(List<String> municipiosExp) {
        this.municipiosExp = municipiosExp;
    }

    /**
     *
     * @return
     */
    public List<String> getCiudadesExp() {
        return ciudadesExp;
    }

    /**
     *
     * @param ciudadesExp
     */
    public void setCiudadesExp(List<String> ciudadesExp) {
        this.ciudadesExp = ciudadesExp;
    }

    /**
     *
     * @return
     */
    public List<String> getColoniasExp() {
        return coloniasExp;
    }

    /**
     *
     * @param coloniasExp
     */
    public void setColoniasExp(List<String> coloniasExp) {
        this.coloniasExp = coloniasExp;
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

}
