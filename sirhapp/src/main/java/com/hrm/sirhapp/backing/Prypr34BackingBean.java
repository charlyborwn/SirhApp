package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Prypr34;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Contr27;
import com.hrm.sirhapp.model.Tipyp20;
import com.hrm.sirhapp.model.Traba36;
import com.hrm.sirhapp.service.Prypr34ManagerLocal;
import com.hrm.sirhapp.service.Contr27ManagerLocal;
import com.hrm.sirhapp.service.Tipyp20ManagerLocal;
import com.hrm.sirhapp.service.Traba36ManagerLocal;
import com.hrm.sirhapp.service.exception.Prypr34AlreadyExists;
import com.hrm.sirhapp.service.exception.Prypr34NotFound;
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
public class Prypr34BackingBean extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Prypr34ManagerLocal prypr34Manager;

    @EJB
    private Traba36ManagerLocal traba36Manager;

    @EJB
    private Contr27ManagerLocal contr27Manager;

    @EJB
    private Tipyp20ManagerLocal tipyp20Manager;

    private Prypr34 newPrypr34 = new Prypr34();

    private Prypr34 selectedPrypr34;
    private Traba36 selectedTraba36;
    private Contr27 selectedContr27;

    private Tipyp20 selectedTipyp20;

    private String infoMessageModule;
    private String infoMessage;
    private String pspry;
    private String ptpry;
    private String pathFile;
    private Boolean active;
    private Boolean create;
    private Boolean select;
    private String tppry;

    private List<Prypr34> listPrypr34;
    private List<Tipyp20> listTipyp20;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Premios y prestaciones";
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

        if (userSessionBean.getContratoPrypr() == null || userSessionBean.getContratoPrypr() == 0 || userSessionBean.getContratoPrypr() == -1) {
            if (selectedPrypr34 != null) {
                userSessionBean.setContratoPrypr(selectedPrypr34.getIdpry());
            }
        }
        if (!select) {

            try {

                if (userSessionBean.getContratoPrypr() != null && userSessionBean.getContratoPrypr() > 0) {

                    this.selectedPrypr34 = prypr34Manager.getPrypr34(userSessionBean.getContratoPrypr());
                    this.active = true;

                    System.out.println("Prypr34BackingBean:prepareSelect():" + selectedPrypr34.toString());

                    if (prypr34Manager.alreadyExistsInnactive(selectedPrypr34.getIdpry())) {
                        this.active = false;
                    }
                }

                this.selectedContr27 = contr27Manager.getContr27(userSessionBean.getContrato());
                System.out.println("Prypr34BackingBean:prepareSelect():" + selectedContr27.toString());
                this.selectedTraba36 = traba36Manager.getTraba36(userSessionBean.getContratoTrabajador());
                System.out.println("Prypr34BackingBean:prepareSelect():" + selectedTraba36.toString());
                getPrypr34List();
            } catch (Prypr34NotFound | Contr27NotFound | Traba36NotFound ex) {
                Logger.getLogger(Prypr34BackingBean.class.getName()).log(Level.SEVERE, null, ex);
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
                getListTipyp20();
                UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);

                this.tppry = userSessionBean.getContratoTprypr();

                ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("America/Mexico_City"));
                Date currentTime = java.util.Date.from(zdt.toInstant());
                System.out.println(currentTime);
                Prypr34 prypr34 = new Prypr34();
                Contr27 contr27 = contr27Manager.getContr27(userSessionBean.getContrato());

                prypr34.setNtpry(contr27.getNtcto());
                prypr34.setNnpry(contr27.getTncto());
                prypr34.setRfpry(contr27.getRfcto());
                prypr34.setPfpry(contr27.getPfcto());
                prypr34.setSspry(contr27.getSscto());
                prypr34.setFspry(contr27.getFscto());
                prypr34.setNupry(contr27.getNucto());
                prypr34.setEspry(contr27.getEscto());
                prypr34.setCepry(contr27.getCvcto());
                prypr34.setNepry(contr27.getNecto());
                prypr34.setNdpry(contr27.getNdcto());
                prypr34.setDepry(contr27.getNbcto());
                prypr34.setCspry(contr27.getCscto());
                prypr34.setSepry(contr27.getSecto());
                prypr34.setCcpry(contr27.getCccto());
                prypr34.setCapry(contr27.getCacto());
                prypr34.setSxpry(contr27.getSxcto());
                prypr34.setCtpry(contr27.getTucto());
                prypr34.setNopry(contr27.getTtcto());
                prypr34.setTcpry(contr27.getTccto());
                prypr34.setCopry(contr27.getNccto());
                prypr34.setIcpry(contr27.getIncto());
                prypr34.setFcpry(contr27.getTecto());

                prypr34.setTppry(tppry);
                //prypr34.setW1pry(tppry.substring(0, Math.min(tppry.length(), 6)));
                prypr34.setW2pry("JUSTIF");

                this.newPrypr34 = prypr34;
                create = true;
                System.out.println(prypr34);
            } catch (Contr27NotFound ex) {
                Logger.getLogger(Prypr34BackingBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param grpyp
     * @param hmpyp
     * @return
     */
    public String getTipyp20Detap(String grpyp, String hmpyp) {
        return tipyp20Manager.getTipyp20Nopyp(grpyp, hmpyp);
    }

    /**
     *
     * @param idpry
     * @return
     */
    public Prypr34 getPrypr34Request(Integer idpry) {

        try {

            this.selectedPrypr34 = prypr34Manager.getPrypr34(idpry);

            if (prypr34Manager.alreadyExistsInnactive(idpry)) {
                this.active = false;
            }

        } catch (Prypr34NotFound ex) {
            Logger.getLogger(Prypr34BackingBean.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = new LanguagesUtil().getResources("reports.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return selectedPrypr34;
    }

    /**
     *
     * @param nutra
     * @return
     */
    public List<Prypr34> getListPrypr34Traba36(Integer nutra) {

        listPrypr34 = prypr34Manager.getListPrypr34Traba36(nutra);

        return listPrypr34;
    }

    /**
     *
     */
    public void getPrypr34List() {
        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);

        if (userSessionBean.getContratoTrabajador() != null && userSessionBean.getContratoTrabajador() > 0) {
            if (userSessionBean.getContratoTprypr().equals("TODOS")) {
                this.listPrypr34 = prypr34Manager.getListPrypr34(selectedContr27.getNucto());
            } else {
                this.listPrypr34 = prypr34Manager.getListPrypr34(selectedContr27.getNucto(), userSessionBean.getContratoTprypr());
            }

            if (listPrypr34.isEmpty()) {
                infoMessage = "No se encontraron premios y prestaciones del No./Contrato: " + selectedContr27.getNucto() + ".";

            } else {
                infoMessage = listPrypr34.size() + " registros.";

            }
        } else {
            infoMessage = "Proporcione un numero de trabajador.";
        }

    }

    /**
     *
     * @param nupry
     */
    public void getListPrypr34(String nupry) {

        listPrypr34 = prypr34Manager.getListPrypr34(nupry);

        if (!listPrypr34.isEmpty()) {
            infoMessage = listPrypr34.size() + " registro(s)";
        }
    }

    /**
     *
     * @return
     */
    public List<Prypr34> getListPrypr34() {
        return listPrypr34;
    }

    /**
     *
     * @param listPrypr34
     */
    public void setListPrypr34(List<Prypr34> listPrypr34) {
        this.listPrypr34 = listPrypr34;
    }

    /**
     *
     * @return
     */
    public List<Tipyp20> getListTipyp20() {
        if (tppry == null) {
            this.tppry = "";
        }
        System.out.println(tppry + "<-tppry ");
        List<Tipyp20> list = new ArrayList<Tipyp20>();
        String cepry = "";
        if (selectedPrypr34 != null) {
            cepry = selectedPrypr34.getCepry();
        } else if (newPrypr34 != null) {
            cepry = newPrypr34.getCepry();
        }
        this.listTipyp20 = tipyp20Manager.getListTipyp20(tppry, cepry);;
        return listTipyp20;

    }

    /**
     *
     * @param listTipyp20
     */
    public void setListTipyp20(List<Tipyp20> listTipyp20) {
        this.listTipyp20 = listTipyp20;
    }

    /**
     *
     * @return
     */
    public String register() {

        try {

            if (pspry != null) {
                newPrypr34.setPspry(pspry);
            }
            if (ptpry != null) {
                newPrypr34.setPtpry(ptpry);
            }

            newPrypr34.setStpry(Constants.RECORD_ACTIVED);
            newPrypr34.setFepry(new Date());
            newPrypr34.setUspry(FacesUtil.getUserName());

            prypr34Manager.registerPrypr34(newPrypr34);

            UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);

            userSessionBean.setContrato(selectedContr27.getIdcto());
            userSessionBean.setContratoTrabajador(selectedContr27.getNtcto());
            infoMessage = "El registro se creo correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

            return "success";

        } catch (Prypr34AlreadyExists ex) {
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
    public String deletePrypr34() {
        try {

            selectedPrypr34.setStpry(Constants.RECORD_DELETED);
            selectedPrypr34.setFepry(new Date());
            selectedPrypr34.setUspry(FacesUtil.getUserName());

            selectedPrypr34 = prypr34Manager.deletePrypr34(selectedPrypr34);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            return "success";

        } catch (Prypr34NotFound ex) {
            Logger.getLogger(Prypr34BackingBean.class.getName()).log(Level.SEVERE, null, ex);
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

            selectedPrypr34.setStpry(Constants.RECORD_ACTIVED);
            selectedPrypr34.setFepry(new Date());
            selectedPrypr34.setUspry(FacesUtil.getUserName());

            prypr34Manager.updatePrypr34(selectedPrypr34);

            if (!contr27Manager.alreadyExistsInnactive(selectedContr27.getIdcto())) {
                infoMessage = "El registro fue activado correctamente";
                FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            }
            return "success";

        } catch (Prypr34NotFound ex) {
            Logger.getLogger(Prypr34BackingBean.class.getName()).log(Level.SEVERE, null, ex);
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
    public String updatePrypr34File(String file) {
        String result = "";
        this.pathFile = file;
        switch (file) {
            case "pspry":
                if (newPrypr34 != null) {
                    newPrypr34.setPspry(null);
                    result = "success";
                }

                if (selectedPrypr34 != null) {
                    selectedPrypr34.setPspry(null);
                    result = updatePrypr34();
                }
                break;
            case "ptpry":
                if (newPrypr34 != null) {
                    newPrypr34.setPtpry(null);
                    result = "success";
                }

                if (selectedPrypr34 != null) {
                    selectedPrypr34.setPtpry(null);
                    result = updatePrypr34();
                }
                break;
            default:
                break;
        }
        if (result.equals("success")) {
            FacesUtil.removeManagedBeanInSession("fileUploadBean");
            String oldInfoMessageModule = infoMessageModule;
            infoMessageModule = "Premios y prestaciones";
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
    public String updatePrypr34() {

        try {
            if (pspry != null) {
                selectedPrypr34.setPspry(pspry);
            }
            if (ptpry != null) {
                selectedPrypr34.setPtpry(ptpry);
            }

            selectedPrypr34.setFepry(new Date());
            selectedPrypr34.setUspry(FacesUtil.getUserName());

            prypr34Manager.updatePrypr34(selectedPrypr34);

            return "success";

        } catch (Prypr34NotFound ex) {
            Logger.getLogger(Prypr34BackingBean.class.getName()).log(Level.SEVERE, null, ex);
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

        String outcome = "/secured/contratos/pryprList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/contratos/pryprView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/contratos/pryprEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/contratos/pryprCreate.xhtml?faces-redirect=true";
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
            default:
                break;
        }
    }

    /**
     *
     * @return
     */
    public Prypr34 getNewPrypr34() {
        return newPrypr34;
    }

    /**
     *
     * @param newPrypr34
     */
    public void setNewPrypr34(Prypr34 newPrypr34) {
        this.newPrypr34 = newPrypr34;
    }

    /**
     *
     * @return
     */
    public Prypr34 getSelectedPrypr34() {
        return selectedPrypr34;
    }

    /**
     *
     * @param selectedPrypr34
     */
    public void setSelectedPrypr34(Prypr34 selectedPrypr34) {
        this.selectedPrypr34 = selectedPrypr34;
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
    public Tipyp20 getSelectedTipyp20() {
        return selectedTipyp20;
    }

    /**
     *
     * @param selectedTipyp20
     */
    public void setSelectedTipyp20(Tipyp20 selectedTipyp20) {
        this.selectedTipyp20 = selectedTipyp20;
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
    public String getPspry() {
        return pspry;
    }

    /**
     *
     * @param pspry
     */
    public void setPspry(String pspry) {
        this.pspry = pspry;
    }

    /**
     *
     * @return
     */
    public String getPtpry() {
        return ptpry;
    }

    /**
     *
     * @param ptpry
     */
    public void setPtpry(String ptpry) {
        this.ptpry = ptpry;
    }

    /**
     *
     * @return
     */
    public String getTppry() {
        return tppry;
    }

    /**
     *
     * @param tppry
     */
    public void setTppry(String tppry) {
        this.tppry = tppry;
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
    public String getTipry() {
        return tppry;
    }

    /**
     *
     * @param tppry
     */
    public void setTipry(String tppry) {
        this.tppry = tppry;
    }
}
