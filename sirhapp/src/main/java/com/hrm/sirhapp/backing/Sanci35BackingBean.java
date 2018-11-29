package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Comit08;
import com.hrm.sirhapp.model.Sanci35;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Contr27;
import com.hrm.sirhapp.model.Tipsa47;
import com.hrm.sirhapp.model.Traba36;
import com.hrm.sirhapp.service.Comit08ManagerLocal;
import com.hrm.sirhapp.service.Sanci35ManagerLocal;
import com.hrm.sirhapp.service.Contr27ManagerLocal;
import com.hrm.sirhapp.service.Tipsa47ManagerLocal;
import com.hrm.sirhapp.service.Traba36ManagerLocal;
import com.hrm.sirhapp.service.exception.Sanci35AlreadyExists;
import com.hrm.sirhapp.service.exception.Sanci35NotFound;
import com.hrm.sirhapp.service.exception.Contr27NotFound;
import com.hrm.sirhapp.service.exception.Traba36NotFound;
import com.hrm.sirhapp.util.FacesUtil;
import com.hrm.sirhapp.util.LanguagesUtil;
import java.io.IOException;
import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
import javax.faces.component.ValueHolder;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@ViewScoped
public class Sanci35BackingBean extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Sanci35ManagerLocal sanci35Manager;

    @EJB
    private Traba36ManagerLocal traba36Manager;

    @EJB
    private Contr27ManagerLocal contr27Manager;

    @EJB
    private Tipsa47ManagerLocal tipsa47Manager;

    @EJB
    private Comit08ManagerLocal comit08Manager;

    private Sanci35 newSanci35 = new Sanci35();

    private Sanci35 selectedSanci35;
    private Traba36 selectedTraba36;
    private Contr27 selectedContr27;

    private Tipsa47 selectedTipsa47;

    private String infoMessageModule;
    private String infoMessage;
    private String ptsan;
    private String pathFile;
    private Boolean active;
    private Boolean create;
    private Boolean select;
    private String cusan;

    private List<Sanci35> listSanci35;
    private List<Tipsa47> listTipsa47;
    private List<Comit08> listComit08;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Sanciones";
        FacesContext facesContext = FacesContext.getCurrentInstance();

        if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
            create = false;
            select = false;
            prepareSelect();
        }
    }

    /**
     *
     */
    public void prepareSelect() {
        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);

        if (userSessionBean.getContratoSanci() == null || userSessionBean.getContratoSanci() == 0 || userSessionBean.getContratoSanci() == -1) {
            if (selectedSanci35 != null) {
                userSessionBean.setContratoSanci(selectedSanci35.getIdsan());
            }
        }
        if (!select) {

            try {

                if (userSessionBean.getContratoSanci() != null && userSessionBean.getContratoSanci() > 0) {

                    this.selectedSanci35 = sanci35Manager.getSanci35(userSessionBean.getContratoSanci());
                    this.active = true;

                    System.out.println("Sanci35BackingBean:prepareSelect():" + selectedSanci35.toString());

                    if (sanci35Manager.alreadyExistsInnactive(selectedSanci35.getIdsan())) {
                        this.active = false;
                    }
                    getListTipsa47();
                    newSanci35 = null;
                }

                this.selectedContr27 = contr27Manager.getContr27(userSessionBean.getContrato());
                System.out.println("Sanci35BackingBean:prepareSelect():" + selectedContr27.toString());
                this.selectedTraba36 = traba36Manager.getTraba36(userSessionBean.getContratoTrabajador());
                System.out.println("Sanci35BackingBean:prepareSelect():" + selectedTraba36.toString());
                getSanci35List();
            } catch (Sanci35NotFound | Contr27NotFound | Traba36NotFound ex) {
                Logger.getLogger(Sanci35BackingBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            select = true;
        }

    }

    /**
     *
     */
    public void prepareCreate() {
        if (!create) {
            try {

                UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);

                this.cusan = userSessionBean.getContratoTsanci();

                ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("America/Mexico_City"));
                Date currentTime = java.util.Date.from(zdt.toInstant());
                System.out.println(currentTime);
                Sanci35 sanci35 = new Sanci35();
                Contr27 contr27 = contr27Manager.getContr27(userSessionBean.getContrato());

                sanci35.setNtsan(contr27.getNtcto());
                sanci35.setNnsan(contr27.getTncto());
                sanci35.setRfsan(contr27.getRfcto());
                sanci35.setPfsan(contr27.getPfcto());
                sanci35.setSssan(contr27.getSscto());
                sanci35.setFssan(contr27.getFscto());
                sanci35.setNusan(contr27.getNucto());
                sanci35.setEssan(contr27.getEscto());
                sanci35.setCesan(contr27.getCvcto());
                sanci35.setNesan(contr27.getNecto());
                sanci35.setNdsan(contr27.getNdcto());
                sanci35.setDesan(contr27.getNbcto());
                sanci35.setCssan(contr27.getCscto());
                sanci35.setSesan(contr27.getSecto());
                sanci35.setCcsan(contr27.getCccto());
                sanci35.setCasan(contr27.getCacto());
                sanci35.setSxsan(contr27.getSxcto());
                sanci35.setCtsan(contr27.getTucto());
                sanci35.setNosan(contr27.getTtcto());
                sanci35.setTcsan(contr27.getTccto());
                sanci35.setCosan(contr27.getNccto());
                sanci35.setIcsan(contr27.getIncto());
                sanci35.setFcsan(contr27.getTecto());

                sanci35.setCusan(cusan);
                //sanci35.setW1san(cusan.substring(0, Math.min(cusan.length(), 6)));

                this.newSanci35 = sanci35;
                create = true;
                System.out.println(sanci35);
                getListTipsa47();
                selectedSanci35 = null;
            } catch (Contr27NotFound ex) {
                Logger.getLogger(Sanci35BackingBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param grpsa
     * @param hmpsa
     * @return
     */
    public String getTipsa47Nopsa(String grpsa, String hmpsa) {
        return tipsa47Manager.getTipsa47Nopsa(grpsa, hmpsa);
    }

    /**
     *
     * @param grpsa
     * @param hmpsa
     * @param cepsa
     * @return
     */
    public String getTipsa47Nopsa(String grpsa, String hmpsa, String cepsa) {
        return tipsa47Manager.getTipsa47Nopsa(grpsa, hmpsa, cepsa);
    }

    /**
     *
     * @return
     */
    public List<Comit08> getListComit08() {

        if (newSanci35 != null) {
            List<Comit08> myList1 = comit08Manager.getListComit08CecomCdcom(newSanci35.getCesan(), newSanci35.getNdsan());
            if (myList1.isEmpty()) {
                myList1 = comit08Manager.getListComit08CecomCdcom(newSanci35.getCesan(), "");
            }
            if (myList1.isEmpty()) {
                myList1 = comit08Manager.getListComit08CecomCdcom("", "");
            }
            this.listComit08 = myList1;
        }
        if (selectedSanci35 != null) {
            List<Comit08> myList2 = comit08Manager.getListComit08CecomCdcom(selectedSanci35.getCesan(), selectedSanci35.getNdsan());
            if (myList2.isEmpty()) {
                myList2 = comit08Manager.getListComit08CecomCdcom(selectedSanci35.getCesan(), "");
            }
            if (myList2.isEmpty()) {
                myList2 = comit08Manager.getListComit08CecomCdcom("", "");
            }
            this.listComit08 = myList2;
        }
        return listComit08;
    }

    /**
     *
     * @param listComit08
     */
    public void setListComit08(List<Comit08> listComit08) {
        this.listComit08 = listComit08;
    }

    /**
     *
     * @param idsan
     * @return
     */
    public Sanci35 getSanci35Request(Integer idsan) {

        try {

            this.selectedSanci35 = sanci35Manager.getSanci35(idsan);

            if (sanci35Manager.alreadyExistsInnactive(idsan)) {
                this.active = false;
            }

        } catch (Sanci35NotFound ex) {
            Logger.getLogger(Contr27Backing.class.getName()).log(Level.SEVERE, null, ex);
            //getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
            infoMessage = new LanguagesUtil().getResources("reports.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return selectedSanci35;
    }

    /**
     *
     * @param nutra
     * @return
     */
    public List<Sanci35> getListSanci35Traba36(Integer nutra) {

        listSanci35 = sanci35Manager.getListSanci35Traba36(nutra);
        
        return listSanci35;
    }

    /**
     *
     */
    public void getSanci35List() {
        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);

        if (userSessionBean.getContratoTrabajador() != null && userSessionBean.getContratoTrabajador() > 0) {
            if (userSessionBean.getContratoTsanci().equals("TODAS")) {
                this.listSanci35 = sanci35Manager.getListSanci35(selectedContr27.getNucto());
            } else {
                this.listSanci35 = sanci35Manager.getListSanci35(selectedContr27.getNucto(), userSessionBean.getContratoTsanci());
            }

            if (listSanci35.isEmpty()) {
                infoMessage = "No se encontraron sanciones del No./Contrato: " + selectedContr27.getNucto() + ".";

            } else {
                infoMessage = listSanci35.size() + " registros.";

            }
        } else {
            infoMessage = "Proporcione un numero de trabajador.";
        }

    }

    /**
     *
     * @param nusan
     */
    public void getListSanci35(String nusan) {

        listSanci35 = sanci35Manager.getListSanci35(nusan);

        if (!listSanci35.isEmpty()) {
            infoMessage = listSanci35.size() + " registro(s)";
        }
    }

    /**
     *
     * @return
     */
    public List<Sanci35> getListSanci35() {
        return listSanci35;
    }

    /**
     *
     * @param listSanci35
     */
    public void setListSanci35(List<Sanci35> listSanci35) {
        this.listSanci35 = listSanci35;
    }

    /**
     *
     * @return
     */
    public List<Tipsa47> getListTipsa47() {
        if (cusan == null) {
            this.cusan = "";
        }
        System.out.println(cusan + "<-cusan ");

        List<Tipsa47> myList = new ArrayList<Tipsa47>();
        if (selectedSanci35 != null) {
            System.out.println(selectedSanci35.getCesan() + "<-getCesan ");
            myList = tipsa47Manager.getListTipsa47(cusan, selectedSanci35.getCesan());

        }

        if (newSanci35 != null) {
            System.out.println(newSanci35.getCesan() + "<-getCesan ");
            myList = tipsa47Manager.getListTipsa47(cusan, newSanci35.getCesan());

        }
        if (myList.isEmpty()) {
            myList = tipsa47Manager.getListTipsa47(cusan);
        }
        this.listTipsa47 = myList;

        return listTipsa47;
    }

    /**
     *
     * @param listTipsa47
     */
    public void setListTipsa47(List<Tipsa47> listTipsa47) {
        this.listTipsa47 = listTipsa47;
    }

    /**
     *
     * @return
     */
    public String register() {

        try {

            if (ptsan != null) {
                newSanci35.setPtsan(ptsan);
            }

            newSanci35.setStsan(Constants.RECORD_ACTIVED);
            newSanci35.setFesan(new Date());
            newSanci35.setUssan(FacesUtil.getUserName());

            sanci35Manager.registerSanci35(newSanci35);

            UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);

            userSessionBean.setContrato(selectedContr27.getIdcto());
            userSessionBean.setContratoTrabajador(selectedContr27.getNtcto());
            infoMessage = "El registro se creo correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

            return "success";

        } catch (Sanci35AlreadyExists ex) {
            Logger.getLogger(Contr27AddBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
            return "failure";
        } catch (EJBException ex) {
            infoMessage = "El registro no se agregó en la base de datos.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
            return "failure";
        }

    }

    /**
     *
     * @return
     */
    public String deleteSanci35() {
        try {

            selectedSanci35.setStsan(Constants.RECORD_DELETED);
            selectedSanci35.setFesan(new Date());
            selectedSanci35.setUssan(FacesUtil.getUserName());

            selectedSanci35 = sanci35Manager.deleteSanci35(selectedSanci35);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            return "success";

        } catch (Sanci35NotFound ex) {
            Logger.getLogger(Contr27Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
            return "failure";
        }

    }

    /**
     *
     * @return
     */
    public String acivateContr27() {
        try {

            selectedSanci35.setStsan(Constants.RECORD_ACTIVED);
            selectedSanci35.setFesan(new Date());
            selectedSanci35.setUssan(FacesUtil.getUserName());

            sanci35Manager.updateSanci35(selectedSanci35);

            if (!contr27Manager.alreadyExistsInnactive(selectedContr27.getIdcto())) {
                infoMessage = "El registro fue activado correctamente";
                FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            }
            return "success";

        } catch (Sanci35NotFound ex) {
            Logger.getLogger(Contr27Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
            return "failure";
        }

    }

    /**
     *
     * @param file
     * @return
     */
    public String updateSanci35File(String file) {
        String result = "";
        this.pathFile = file;
        switch (file) {
            case "ptsan":
                if (newSanci35 != null) {
                    newSanci35.setPtsan(null);
                    result = "success";
                }

                if (selectedSanci35 != null) {
                    selectedSanci35.setPtsan(null);
                    result = updateSanci35();
                }
                break;
            default:
                break;
        }
        if (result.equals("success")) {
            FacesUtil.removeManagedBeanInSession("fileUploadBean");
            String oldInfoMessageModule = infoMessageModule;
            infoMessageModule = "Sanciones";
            infoMessage = "Se borro el Documento";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            infoMessageModule = oldInfoMessageModule;
        }

        return "success";
    }

    /**
     *
     * @return
     */
    public String updateSanci35() {

        try {
            if (ptsan != null) {
                selectedSanci35.setPtsan(ptsan);
            }

            selectedSanci35.setFesan(new Date());
            selectedSanci35.setUssan(FacesUtil.getUserName());

            sanci35Manager.updateSanci35(selectedSanci35);

            return "success";

        } catch (Sanci35NotFound ex) {
            Logger.getLogger(Contr27Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
            return "failure";
        }
    }

    /**
     *
     * @param action
     * @return
     * @throws IOException
     */
    public String go(int action) throws IOException {

        String outcome = "/secured/contratos/sanciList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/contratos/sanciView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/contratos/sanciEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/contratos/sanciCreate.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;

    }

    /**
     *
     * @param vce
     */
    public void handleChange(AjaxBehaviorEvent vce) {

        String value = (String) ((ValueHolder) vce.getSource()).getValue();
        String id = ((UIComponent) vce.getSource()).getId();
        System.out.println("id:" + id + " value:" + value);
        switch (id) {
            case "w1san":
                if (selectedSanci35 != null) {
                    selectedSanci35.setSgsan(getTipsa47Nopsa(cusan, value));
                }
                if (newSanci35 != null) {
                    newSanci35.setSgsan(getTipsa47Nopsa(cusan, value));
                }
                break;
            case "cvsan":
                if (selectedSanci35 != null) {
                    String npsan1 = comit08Manager.getComit08CecomCdcom(selectedSanci35.getCesan(), selectedSanci35.getNdsan().trim(), value);
                    if (npsan1 == null) {
                        npsan1 = comit08Manager.getComit08CecomCdcom(selectedSanci35.getCesan(), selectedSanci35.getNdsan().trim(), "");
                    }
                    if (npsan1 == null) {
                        npsan1 = comit08Manager.getComit08CecomCdcom(selectedSanci35.getCesan(), "", "");
                    }
                    if (npsan1 == null) {
                        npsan1 = comit08Manager.getComit08CecomCdcom("", "", "");
                    }
                    selectedSanci35.setNpsan(npsan1);
                }
                if (newSanci35 != null) {
                    String npsan = comit08Manager.getComit08CecomCdcom(newSanci35.getCesan(), newSanci35.getNdsan().trim(), value);
                    if (npsan == null) {
                        npsan = comit08Manager.getComit08CecomCdcom(newSanci35.getCesan(), newSanci35.getNdsan().trim(), "");
                    }
                    if (npsan == null) {
                        npsan = comit08Manager.getComit08CecomCdcom(newSanci35.getCesan(), "", "");
                    }
                    if (npsan == null) {
                        npsan = comit08Manager.getComit08CecomCdcom("", "", "");
                    }
                    newSanci35.setNpsan(npsan);
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
    public Sanci35 getNewSanci35() {
        return newSanci35;
    }

    /**
     *
     * @param newSanci35
     */
    public void setNewSanci35(Sanci35 newSanci35) {
        this.newSanci35 = newSanci35;
    }

    /**
     *
     * @return
     */
    public Sanci35 getSelectedSanci35() {
        return selectedSanci35;
    }

    /**
     *
     * @param selectedSanci35
     */
    public void setSelectedSanci35(Sanci35 selectedSanci35) {
        this.selectedSanci35 = selectedSanci35;
    }

    /**
     *
     * @return
     */
    public Traba36 getSelectedTraba36() {
        return selectedTraba36;
    }

    /**
     *
     * @param selectedTraba36
     */
    public void setSelectedTraba36(Traba36 selectedTraba36) {
        this.selectedTraba36 = selectedTraba36;
    }

    /**
     *
     * @return
     */
    public Contr27 getSelectedContr27() {
        return selectedContr27;
    }

    /**
     *
     * @param selectedContr27
     */
    public void setSelectedContr27(Contr27 selectedContr27) {
        this.selectedContr27 = selectedContr27;
    }

    /**
     *
     * @return
     */
    public Tipsa47 getSelectedTipsa47() {
        return selectedTipsa47;
    }

    /**
     *
     * @param selectedTipsa47
     */
    public void setSelectedTipsa47(Tipsa47 selectedTipsa47) {
        this.selectedTipsa47 = selectedTipsa47;
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
    public String getP1san() {
        return ptsan;
    }

    /**
     *
     * @param ptsan
     */
    public void setP1san(String ptsan) {
        this.ptsan = ptsan;
    }

    /**
     *
     * @return
     */
    public String getPtsan() {
        return ptsan;
    }

    /**
     *
     * @param ptsan
     */
    public void setPtsan(String ptsan) {
        this.ptsan = ptsan;
    }

    /**
     *
     * @return
     */
    public String getCusan() {
        return cusan;
    }

    /**
     *
     * @param cusan
     */
    public void setCusan(String cusan) {
        this.cusan = cusan;
    }

    /**
     *
     * @return
     */
    public String getPathFile() {
        return pathFile;
    }

    /**
     *
     * @param pathFile
     */
    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    /**
     *
     * @return
     */
    public Boolean getActive() {
        return active;
    }

    /**
     *
     * @param active
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     *
     * @return
     */
    public Boolean getCreate() {
        return create;
    }

    /**
     *
     * @param create
     */
    public void setCreate(Boolean create) {
        this.create = create;
    }

    /**
     *
     * @return
     */
    public Boolean getSelect() {
        return select;
    }

    /**
     *
     * @param select
     */
    public void setSelect(Boolean select) {
        this.select = select;
    }

    /**
     *
     * @return
     */
    public String getTisan() {
        return cusan;
    }

    /**
     *
     * @param cusan
     */
    public void setTisan(String cusan) {
        this.cusan = cusan;
    }
}
