package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Apriv25;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Contr27;
import com.hrm.sirhapp.model.Tapri16;
import com.hrm.sirhapp.model.Traba36;
import com.hrm.sirhapp.service.Apriv25ManagerLocal;
import com.hrm.sirhapp.service.Contr27ManagerLocal;
import com.hrm.sirhapp.service.Tapri16ManagerLocal;
import com.hrm.sirhapp.service.Traba36ManagerLocal;
import com.hrm.sirhapp.service.exception.Apriv25AlreadyExists;
import com.hrm.sirhapp.service.exception.Apriv25NotFound;
import com.hrm.sirhapp.service.exception.Contr27NotFound;
import com.hrm.sirhapp.service.exception.Traba36NotFound;
import com.hrm.sirhapp.util.FacesUtil;
import com.hrm.sirhapp.util.LanguagesUtil;
import java.io.IOException;
import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
public class Apriv25BackingBean extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Apriv25ManagerLocal apriv25Manager;

    @EJB
    private Traba36ManagerLocal traba36Manager;

    @EJB
    private Contr27ManagerLocal contr27Manager;

    @EJB
    private Tapri16ManagerLocal tapri16Manager;

    private Apriv25 newApriv25 = new Apriv25();

    private Apriv25 selectedApriv25;
    private Traba36 selectedTraba36;
    private Contr27 selectedContr27;

    private Tapri16 selectedTapri16;

    private String infoMessageModule;
    private String infoMessage;
    private String p1apr;
    private String p2apr;
    private String p3apr;
    private String pathFile;
    private Boolean active;
    private Boolean create;
    private Boolean select;
    private String tiapr;

    private List<Apriv25> listApriv25;
    private List<Tapri16> listTapri16;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Ausencias, Permisos, Retardos, Incapacidades, Vacaciones, etc";
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

        if (userSessionBean.getContratoApriv() == null || userSessionBean.getContratoApriv() == 0 || userSessionBean.getContratoApriv() == -1) {
            if (selectedApriv25 != null) {
                userSessionBean.setContratoApriv(selectedApriv25.getIdapr());
            }
        }
        if (!select) {

            try {

                if (userSessionBean.getContratoApriv() != null && userSessionBean.getContratoApriv() > 0) {

                    this.selectedApriv25 = apriv25Manager.getApriv25(userSessionBean.getContratoApriv());
                    this.active = true;

                    System.out.println("Apriv25BackingBean:prepareSelect():" + selectedApriv25.toString());

                    if (apriv25Manager.alreadyExistsInnactive(selectedApriv25.getIdapr())) {
                        this.active = false;
                    }
                }

                this.selectedContr27 = contr27Manager.getContr27(userSessionBean.getContrato());
                System.out.println("Apriv25BackingBean:prepareSelect():" + selectedContr27.toString());
                this.selectedTraba36 = traba36Manager.getTraba36(userSessionBean.getContratoTrabajador());
                System.out.println("Apriv25BackingBean:prepareSelect():" + selectedTraba36.toString());
                getApriv25List();
            } catch (Apriv25NotFound | Contr27NotFound | Traba36NotFound ex) {
                Logger.getLogger(Apriv25BackingBean.class.getName()).log(Level.SEVERE, null, ex);
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

                this.tiapr = userSessionBean.getContratoTapriv();

                ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("America/Mexico_City"));
                Date currentTime = java.util.Date.from(zdt.toInstant());
                System.out.println(currentTime);
                Apriv25 apriv25 = new Apriv25();
                Contr27 contr27 = contr27Manager.getContr27(userSessionBean.getContrato());

                apriv25.setNtapr(contr27.getNtcto());
                apriv25.setNnapr(contr27.getTncto());
                apriv25.setRfapr(contr27.getRfcto());
                apriv25.setPfapr(contr27.getPfcto());
                apriv25.setSsapr(contr27.getSscto());
                apriv25.setFsapr(contr27.getFscto());
                apriv25.setNuapr(contr27.getNucto());
                apriv25.setEsapr(contr27.getEscto());
                apriv25.setCeapr(contr27.getCvcto());
                apriv25.setNeapr(contr27.getNecto());
                apriv25.setNdapr(contr27.getNdcto());
                apriv25.setDeapr(contr27.getNbcto());
                apriv25.setCsapr(contr27.getCscto());
                apriv25.setSeapr(contr27.getSecto());
                apriv25.setCcapr(contr27.getCccto());
                apriv25.setCaapr(contr27.getCacto());
                apriv25.setSxapr(contr27.getSxcto());
                apriv25.setCtapr(contr27.getTucto());
                apriv25.setNoapr(contr27.getTtcto());
                apriv25.setTcapr(contr27.getTccto());
                apriv25.setCoapr(contr27.getNccto());
                apriv25.setIcapr(contr27.getIncto());
                apriv25.setFcapr(contr27.getTecto());

                apriv25.setTiapr(tiapr);
                //apriv25.setW1apr(tiapr.substring(0, Math.min(tiapr.length(), 6)));
                apriv25.setW2apr("JUSTIF");
                apriv25.setW3apr("REANUD");
                apriv25.setFaapr(currentTime);
                apriv25.setHaapr(currentTime);

                this.newApriv25 = apriv25;
                create = true;
                System.out.println(apriv25);
            } catch (Contr27NotFound ex) {
                Logger.getLogger(Apriv25BackingBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param grtap
     * @param hmtap
     * @return
     */
    public String getTapri16Detap(String grtap, String hmtap) {
        return tapri16Manager.getTapri16Detap(grtap, hmtap);
    }

    /**
     *
     * @param idapr
     * @return
     */
    public Apriv25 getApriv25Request(Integer idapr) {

        try {

            this.selectedApriv25 = apriv25Manager.getApriv25(idapr);

            if (apriv25Manager.alreadyExistsInnactive(idapr)) {
                this.active = false;
            }

        } catch (Apriv25NotFound ex) {
            Logger.getLogger(Contr27Backing.class.getName()).log(Level.SEVERE, null, ex);
            //getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
            infoMessage = new LanguagesUtil().getResources("reports.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return selectedApriv25;
    }

    /**
     *
     * @param nutra
     * @return
     */
    public List<Apriv25> getListApriv25Traba36(Integer nutra) {

        listApriv25 = apriv25Manager.getListApriv25Traba36(nutra);

        return listApriv25;
    }

    /**
     *
     */
    public void getApriv25List() {
        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);

        if (userSessionBean.getContratoTrabajador() != null && userSessionBean.getContratoTrabajador() > 0) {
            if (userSessionBean.getContratoTapriv().equals("TODOS")) {
                this.listApriv25 = apriv25Manager.getListApriv25(selectedContr27.getNucto());
            } else {
                this.listApriv25 = apriv25Manager.getListApriv25(selectedContr27.getNucto(), userSessionBean.getContratoTapriv());
            }

            if (listApriv25.isEmpty()) {
                infoMessage = "No se encontraron ausencias, permisos, retardos, incapacidades, vacaciones, etc. del No./Contrato: " + selectedContr27.getNucto() + ".";

            } else {
                infoMessage = listApriv25.size() + " registros.";

            }
        } else {
            infoMessage = "Proporcione un numero de trabajador.";
        }

    }

    /**
     *
     * @param nuapr
     */
    public void getListApriv25(String nuapr) {

        listApriv25 = apriv25Manager.getListApriv25(nuapr);

        if (!listApriv25.isEmpty()) {
            infoMessage = listApriv25.size() + " registro(s)";
        }
    }

    /**
     *
     * @return
     */
    public List<Apriv25> getListApriv25() {
        return listApriv25;
    }

    /**
     *
     * @param listApriv25
     */
    public void setListApriv25(List<Apriv25> listApriv25) {
        this.listApriv25 = listApriv25;
    }

    /**
     *
     * @return
     */
    public List<Tapri16> getListTapri16() {
        if (tiapr == null) {
            this.tiapr = "";
        }
        System.out.println(tiapr + "<-tiapr ");
        this.listTapri16 = tapri16Manager.getListTapri16(tiapr);
        return listTapri16;

    }

    /**
     *
     * @param listTapri16
     */
    public void setListTapri16(List<Tapri16> listTapri16) {
        this.listTapri16 = listTapri16;
    }

    /**
     *
     * @return
     */
    public String register() {

        try {

            if (p1apr != null) {
                newApriv25.setP1apr(p1apr);
            }
            if (p2apr != null) {
                newApriv25.setP2apr(p2apr);
            }
            if (p3apr != null) {
                newApriv25.setP3apr(p3apr);
            }

            newApriv25.setStapr(Constants.RECORD_ACTIVED);
            newApriv25.setFeapr(new Date());
            newApriv25.setUsapr(FacesUtil.getUserName());

            apriv25Manager.registerApriv25(newApriv25);

            UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);

            userSessionBean.setContrato(selectedContr27.getIdcto());
            userSessionBean.setContratoTrabajador(selectedContr27.getNtcto());
            infoMessage = "El registro se creo correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

            return "success";

        } catch (Apriv25AlreadyExists ex) {
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
    public String deleteApriv25() {
        try {

            selectedApriv25.setStapr(Constants.RECORD_DELETED);
            selectedApriv25.setFeapr(new Date());
            selectedApriv25.setUsapr(FacesUtil.getUserName());

            selectedApriv25 = apriv25Manager.deleteApriv25(selectedApriv25);

            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            return "success";

        } catch (Apriv25NotFound ex) {
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

            selectedApriv25.setStapr(Constants.RECORD_ACTIVED);
            selectedApriv25.setFeapr(new Date());
            selectedApriv25.setUsapr(FacesUtil.getUserName());

            apriv25Manager.updateApriv25(selectedApriv25);

            if (!contr27Manager.alreadyExistsInnactive(selectedContr27.getIdcto())) {
                infoMessage = "El registro fue activado correctamente";
                FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            }
            return "success";

        } catch (Apriv25NotFound ex) {
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
    public String updateApriv25File(String file) {
        String result = "";
        this.pathFile = file;
        switch (file) {
            case "p1apr":
                if (newApriv25 != null) {
                    newApriv25.setP1apr(null);
                    result = "success";
                }

                if (selectedApriv25 != null) {
                    selectedApriv25.setP1apr(null);
                    result = updateApriv25();
                }
                break;
            case "p2apr":
                if (newApriv25 != null) {
                    newApriv25.setP2apr(null);
                    result = "success";
                }

                if (selectedApriv25 != null) {
                    selectedApriv25.setP2apr(null);
                    result = updateApriv25();
                }
                break;
            case "p3apr":
                if (newApriv25 != null) {
                    newApriv25.setP3apr(null);
                    result = "success";
                }

                if (selectedApriv25 != null) {
                    selectedApriv25.setP3apr(null);
                    result = updateApriv25();
                }
                break;
            default:
                break;
        }
        if (result.equals("success")) {
            FacesUtil.removeManagedBeanInSession("fileUploadBean");
            String oldInfoMessageModule = infoMessageModule;
            infoMessageModule = "Ausencias, Permisos, Retardos, Incapacidades, Vacaciones, etc";
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
    public String updateApriv25() {

        try {
            if (p1apr != null) {
                selectedApriv25.setP1apr(p1apr);
            }
            if (p2apr != null) {
                selectedApriv25.setP2apr(p2apr);
            }
            if (p3apr != null) {
                selectedApriv25.setP3apr(p3apr);
            }

            selectedApriv25.setFeapr(new Date());
            selectedApriv25.setUsapr(FacesUtil.getUserName());

            apriv25Manager.updateApriv25(selectedApriv25);

            return "success";

        } catch (Apriv25NotFound ex) {
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

        String outcome = "/secured/contratos/aprivList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/contratos/aprivView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/contratos/aprivEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/contratos/aprivCreate.xhtml?faces-redirect=true";
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
    public Apriv25 getNewApriv25() {
        return newApriv25;
    }

    /**
     *
     * @param newApriv25
     */
    public void setNewApriv25(Apriv25 newApriv25) {
        this.newApriv25 = newApriv25;
    }

    /**
     *
     * @return
     */
    public Apriv25 getSelectedApriv25() {
        return selectedApriv25;
    }

    /**
     *
     * @param selectedApriv25
     */
    public void setSelectedApriv25(Apriv25 selectedApriv25) {
        this.selectedApriv25 = selectedApriv25;
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
    public Tapri16 getSelectedTapri16() {
        return selectedTapri16;
    }

    /**
     *
     * @param selectedTapri16
     */
    public void setSelectedTapri16(Tapri16 selectedTapri16) {
        this.selectedTapri16 = selectedTapri16;
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
    public String getP1apr() {
        return p1apr;
    }

    /**
     *
     * @param p1apr
     */
    public void setP1apr(String p1apr) {
        this.p1apr = p1apr;
    }

    /**
     *
     * @return
     */
    public String getP2apr() {
        return p2apr;
    }

    /**
     *
     * @param p2apr
     */
    public void setP2apr(String p2apr) {
        this.p2apr = p2apr;
    }

    /**
     *
     * @return
     */
    public String getP3apr() {
        return p3apr;
    }

    /**
     *
     * @param p3apr
     */
    public void setP3apr(String p3apr) {
        this.p3apr = p3apr;
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
    public String getTiapr() {
        return tiapr;
    }

    /**
     *
     * @param tiapr
     */
    public void setTiapr(String tiapr) {
        this.tiapr = tiapr;
    }
}
