package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Escol30;
import com.hrm.sirhapp.service.Escol30ManagerLocal;
import com.hrm.sirhapp.service.Espec05ManagerLocal;
import com.hrm.sirhapp.service.Tidoc18ManagerLocal;
import com.hrm.sirhapp.service.exception.Escol30AlreadyExists;
import com.hrm.sirhapp.service.exception.Escol30NotFound;
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
public class Escol30BackingBean extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Escol30ManagerLocal escol30Manager;
    @EJB
    private Tidoc18ManagerLocal tidoc18Manager;
    @EJB
    private Espec05ManagerLocal espec05Manager;

    private String rfc;
    private Integer nt;
    private String path;
    private boolean add;
    private String w1esc;
    private String tdesc;
    private Integer escol30Tab;

    private String infoMessage;
    private String infoMessageModule;

    private Escol30 selectedEscol30Bean;

    private Escol30 newEscol30Bean = new Escol30();

    private List<Escol30> escol30List;

    private int escol30I;
    private int escol30F;
    private DualListModel<String> dias;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Escolaridad";
        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);
        nt = userSessionBean.getTrabajador();
        escol30I = 6;
        escol30F = 22;
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
        getListEscol30();
    }

    /**
     *
     */
    public void getListEscol30() {

        escol30List = escol30Manager.getListEscol30(nt);
        System.out.println("se obtienen las escolaridad");

    }

    /**
     *
     */
    public void prepareSelectEscol30() {

    }

    /**
     *
     * @return
     */
    public Escol30 prepareCreateEscol30() {
        getListEscol30();

        newEscol30Bean = new Escol30();
        newEscol30Bean.setNtesc(nt);

        add = true;
        escol30Tab = 0;
        return newEscol30Bean;
    }

    /**
     *
     * @param event
     */
    public void prepareFileUploadEsc(AjaxBehaviorEvent event) {

        String value = (String) ((ValueHolder) event.getSource()).getValue();
        String id = ((UIComponent) event.getSource()).getId();
        try {
            if (selectedEscol30Bean != null) {

                selectedEscol30Bean.setW1esc(tidoc18Manager.getTidoc18(value));

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
    public String registerEscol30() {

        try {

            newEscol30Bean.setStesc(Constants.RECORD_ACTIVED);
            newEscol30Bean.setFeesc(new Date());
            newEscol30Bean.setUsesc(FacesUtil.getUserName());

            escol30Manager.registerEscol30(newEscol30Bean);

            newEscol30Bean = new Escol30();

            infoMessage = "El registro se creo correctamente";

            getListEscol30();
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

            return "success";
        } catch (Escol30AlreadyExists ex) {
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
    public String updateEscol30Foto() {
        selectedEscol30Bean.setPaesc(null);
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
    public String updateEscol30() {
        try {

            if (path != null) {
                selectedEscol30Bean.setPaesc(path);
                path = null;
            }

            String diasList = "";

            System.out.println(dias.getTarget().size());

            for (String mydays2 : dias.getTarget()) {
                System.out.println(mydays2 + "dias target");
                diasList = diasList + "," + mydays2;
            }

            selectedEscol30Bean.setHcesc("Dias " + diasList + " De las " + escol30I + " a las " + escol30F + " Hrs");
            selectedEscol30Bean.setStesc(Constants.RECORD_ACTIVED);
            selectedEscol30Bean.setFeesc(new Date());
            selectedEscol30Bean.setUsesc(FacesUtil.getUserName());

            escol30Manager.updateEscol30(selectedEscol30Bean);

            infoMessage = "El registro se actualizo correctamente";

        } catch (Escol30NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error al actualizar el registro del Trabajador";
            FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR, infoMessageModule, infoMessage);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String deleteEscol30() {
        try {

            selectedEscol30Bean.setStesc(Constants.RECORD_DELETED);
            selectedEscol30Bean.setFeesc(new Date());
            selectedEscol30Bean.setUsesc(FacesUtil.getUserName());

            escol30Manager.deleteEscol30(selectedEscol30Bean);

            selectedEscol30Bean = null;

            infoMessage = "El registro se elimino correctamente";
            getListEscol30();
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

        } catch (Escol30NotFound ex) {
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
     * @param nt
     */
    public void getListEscol30(Integer nt) {

        escol30List = escol30Manager.getListEscol30(nt);

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
    public void onTabChangeEscol30(TabChangeEvent event) {
        TabView tabView = (TabView) event.getComponent();

        escol30Tab = tabView.getChildren().indexOf(event.getTab());
        System.out.println("tab actual escol:" + escol30Tab);

    }

    /**
     *
     * @return
     */
    public Escol30ManagerLocal getEscol30Manager() {
        return escol30Manager;
    }

    /**
     *
     * @param escol30Manager
     */
    public void setEscol30Manager(Escol30ManagerLocal escol30Manager) {
        this.escol30Manager = escol30Manager;
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
    public String getW1esc() {
        return w1esc;
    }

    /**
     *
     * @param w1esc
     */
    public void setW1esc(String w1esc) {
        this.w1esc = w1esc;
    }

    /**
     *
     * @return
     */
    public String getTdesc() {
        return tdesc;
    }

    /**
     *
     * @param tdesc
     */
    public void setTdesc(String tdesc) {
        this.tdesc = tdesc;
    }

    /**
     *
     * @return
     */
    public Integer getEscol30Tab() {
        return escol30Tab;
    }

    /**
     *
     * @param escol30Tab
     */
    public void setEscol30Tab(Integer escol30Tab) {
        this.escol30Tab = escol30Tab;
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
    public Escol30 getSelectedEscol30Bean() {
        return selectedEscol30Bean;
    }

    /**
     *
     * @param selectedEscol30Bean
     */
    public void setSelectedEscol30Bean(Escol30 selectedEscol30Bean) {
        this.selectedEscol30Bean = selectedEscol30Bean;
    }

    /**
     *
     * @return
     */
    public Escol30 getNewEscol30Bean() {
        return newEscol30Bean;
    }

    /**
     *
     * @param newEscol30Bean
     */
    public void setNewEscol30Bean(Escol30 newEscol30Bean) {
        this.newEscol30Bean = newEscol30Bean;
    }

    /**
     *
     * @return
     */
    public List<Escol30> getEscol30List() {
        return escol30List;
    }

    /**
     *
     * @param escol30List
     */
    public void setEscol30List(List<Escol30> escol30List) {
        this.escol30List = escol30List;
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
    public int getEscol30I() {
        return escol30I;
    }

    /**
     *
     * @param escol30I
     */
    public void setEscol30I(int escol30I) {
        this.escol30I = escol30I;
    }

    /**
     *
     * @return
     */
    public int getEscol30F() {
        return escol30F;
    }

    /**
     *
     * @param escol30F
     */
    public void setEscol30F(int escol30F) {
        this.escol30F = escol30F;
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
