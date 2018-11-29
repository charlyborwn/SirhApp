package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Escol30a;
import com.hrm.sirhapp.service.Escol30aManagerLocal;
import com.hrm.sirhapp.service.Espec05ManagerLocal;
import com.hrm.sirhapp.service.Tidoc18ManagerLocal;
import com.hrm.sirhapp.service.exception.Escol30aAlreadyExists;
import com.hrm.sirhapp.service.exception.Escol30aNotFound;
import com.hrm.sirhapp.service.exception.Tidoc18NotFound;
import com.hrm.sirhapp.util.FacesUtil;
import com.hrm.sirhapp.util.LanguagesUtil;
import java.io.Serializable;
import java.util.ArrayList;
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
public class Escol30aBackingBean extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Escol30aManagerLocal escol30aManager;
    @EJB
    private Tidoc18ManagerLocal tidoc18Manager;
    @EJB
    private Espec05ManagerLocal espec05Manager;

    private String rfc;
    private String path;
    private boolean add;
    private String w1escA;
    private String tdescA;
    private Integer escol30aTab;
    private Float slide;

    private String infoMessage;
    private String infoMessageModule;

    private Escol30a selectedEscol30aBean;

    private Escol30a newEscol30aBean = new Escol30a();

    private List<Escol30a> escol30aList;

    private int escol30aI;
    private int escol30aF;
    private DualListModel<String> dias;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Escolaridad";
        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);
        rfc = userSessionBean.getAspirante();
        escol30aI = 6;
        escol30aF = 22;
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
        getListEscol30a();
    }

    /**
     *
     */
    public void getListEscol30a() {

        escol30aList = escol30aManager.getListEscol30a(rfc);
        System.out.println("se obtienen las escolaridad");

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
    public Escol30a prepareCreateEscol30a() {
        getListEscol30a();

        newEscol30aBean = new Escol30a();
        newEscol30aBean.setRfescA(rfc);

        add = true;
        escol30aTab = 0;
        return newEscol30aBean;
    }

    /**
     *
     * @param event
     */
    public void prepareFileUploadEsc(AjaxBehaviorEvent event) {

        String value = (String) ((ValueHolder) event.getSource()).getValue();
        String id = ((UIComponent) event.getSource()).getId();
        try {
            if (selectedEscol30aBean != null) {

                selectedEscol30aBean.setW1escA(tidoc18Manager.getTidoc18(value));

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
    public String registerEscol30a() {

        try {

            newEscol30aBean.setStescA(Constants.RECORD_ACTIVED);
            newEscol30aBean.setFeescA(new Date());
            newEscol30aBean.setUsescA(FacesUtil.getUserName());

            escol30aManager.registerEscol30a(newEscol30aBean);

            newEscol30aBean = new Escol30a();

            infoMessage = "El registro se creo correctamente";

            getListEscol30a();
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

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

        return null;
    }

    /**
     *
     * @return
     */
    public String updateEscol30aFoto() {
        selectedEscol30aBean.setPaescA(null);
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
    public String updateEscol30a() {
        try {

            if (path != null) {
                selectedEscol30aBean.setPaescA(path);
                path = null;
            }

            String diasList = "";

            System.out.println(dias.getTarget().size());

            for (String mydays2 : dias.getTarget()) {
                System.out.println(mydays2 + "dias target");
                diasList = diasList + "," + mydays2;
            }

            selectedEscol30aBean.setHcescA("Dias " + diasList + " De las " + escol30aI + " a las " + escol30aF + " Hrs");
            selectedEscol30aBean.setStescA(Constants.RECORD_ACTIVED);
            selectedEscol30aBean.setFeescA(new Date());
            selectedEscol30aBean.setUsescA(FacesUtil.getUserName());

            escol30aManager.updateEscol30a(selectedEscol30aBean);

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

            selectedEscol30aBean.setStescA(Constants.RECORD_DELETED);
            selectedEscol30aBean.setFeescA(new Date());
            selectedEscol30aBean.setUsescA(FacesUtil.getUserName());

            escol30aManager.deleteEscol30a(selectedEscol30aBean);

            selectedEscol30aBean = null;

            infoMessage = "El registro se elimino correctamente";
            getListEscol30a();
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Escol30aNotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("db.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        }
        return null;

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
     * @param rfbenA
     */
    public void getListEscol30a(String rfbenA) {

        escol30aList = escol30aManager.getListEscol30a(rfbenA);

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
     * @param vce
     */
    public void handleChange(AjaxBehaviorEvent vce) {
        String name = (String) ((UIOutput) vce.getSource()).getValue();
        String id = (String) ((UIOutput) vce.getSource()).getId();
        switch (id) {
            default:
                break;
        }
        System.out.println("id:" + id + " name:" + name);
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
    public String getW1escA() {
        return w1escA;
    }

    /**
     *
     * @param w1escA
     */
    public void setW1escA(String w1escA) {
        this.w1escA = w1escA;
    }

    /**
     *
     * @return
     */
    public String getTdescA() {
        return tdescA;
    }

    /**
     *
     * @param tdescA
     */
    public void setTdescA(String tdescA) {
        this.tdescA = tdescA;
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
    public Escol30a getSelectedEscol30aBean() {
        return selectedEscol30aBean;
    }

    /**
     *
     * @param selectedEscol30aBean
     */
    public void setSelectedEscol30aBean(Escol30a selectedEscol30aBean) {
        this.selectedEscol30aBean = selectedEscol30aBean;
    }

    /**
     *
     * @return
     */
    public Escol30a getNewEscol30aBean() {
        return newEscol30aBean;
    }

    /**
     *
     * @param newEscol30aBean
     */
    public void setNewEscol30aBean(Escol30a newEscol30aBean) {
        this.newEscol30aBean = newEscol30aBean;
    }

    /**
     *
     * @return
     */
    public List<Escol30a> getEscol30aList() {
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
     * @return
     */
    public Float getSlide() {
        return slide;
    }

    /**
     *
     * @param slide
     */
    public void setSlide(Float slide) {
        this.slide = slide;
    }
    
    

}
