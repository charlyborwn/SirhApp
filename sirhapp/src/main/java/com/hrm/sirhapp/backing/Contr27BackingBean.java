package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Categ01;
import com.hrm.sirhapp.model.Comit08;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Contr27;
import com.hrm.sirhapp.model.Ticon17;
import com.hrm.sirhapp.model.Traba36;
import com.hrm.sirhapp.model.Turno22;
import com.hrm.sirhapp.model.Ubica23;
import com.hrm.sirhapp.service.Categ01ManagerLocal;
import com.hrm.sirhapp.service.Comit08ManagerLocal;
import com.hrm.sirhapp.service.Contr27ManagerLocal;
import com.hrm.sirhapp.service.Depar03ManagerLocal;
import com.hrm.sirhapp.service.Empre04ManagerLocal;
import com.hrm.sirhapp.service.Rutas41ManagerLocal;
import com.hrm.sirhapp.service.Sedes53ManagerLocal;
import com.hrm.sirhapp.service.Stcon14ManagerLocal;
import com.hrm.sirhapp.service.Ticon17ManagerLocal;
import com.hrm.sirhapp.service.Traba36ManagerLocal;
import com.hrm.sirhapp.service.Turno22ManagerLocal;
import com.hrm.sirhapp.service.Ubica23ManagerLocal;
import com.hrm.sirhapp.service.Unida42ManagerLocal;
import com.hrm.sirhapp.service.exception.Categ01NotFound;
import com.hrm.sirhapp.service.exception.Contr27AlreadyExists;
import com.hrm.sirhapp.service.exception.Contr27NotFound;
import com.hrm.sirhapp.service.exception.Depar03NotFound;
import com.hrm.sirhapp.service.exception.Empre04NotFound;
import com.hrm.sirhapp.service.exception.Ticon17NotFound;
import com.hrm.sirhapp.service.exception.Traba36NotFound;
import com.hrm.sirhapp.service.exception.Turno22NotFound;
import com.hrm.sirhapp.util.FacesUtil;
import com.hrm.sirhapp.util.LanguagesUtil;
import java.io.IOException;
import java.io.Serializable;
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
public class Contr27BackingBean extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Contr27ManagerLocal contr27Manager;
    @EJB
    private Traba36ManagerLocal traba36Manager;
    @EJB
    private Stcon14ManagerLocal stcon14Manager;
    @EJB
    private Sedes53ManagerLocal sedes53Manager;
    @EJB
    private Empre04ManagerLocal empre04Manager;
    @EJB
    private Depar03ManagerLocal depar03Manager;
    @EJB
    private Categ01ManagerLocal categ01Manager;
    @EJB
    private Turno22ManagerLocal turno22Manager;
    @EJB
    private Ticon17ManagerLocal ticon17Manager;
    @EJB
    private Comit08ManagerLocal comit08Manager;
    @EJB
    private Ubica23ManagerLocal ubica23Manager;
    @EJB
    private Unida42ManagerLocal unida42Manager;
    @EJB
    private Rutas41ManagerLocal rutas41Manager;

    private Contr27 newContr27 = new Contr27();

    private Traba36 selectedTraba36;

    private Contr27 selectedContr27;

    private Categ01 selectedCateg01;

    private Turno22 selectedTurno22;

    private Ticon17 selectedTicon17;

    private List<Contr27> contr27List;

    private List<Categ01> categ01List;

    private List<Comit08> comit08ListSup;

    private List<Comit08> comit08ListDel;

    private String infoMessageModule;
    private String infoMessage;
    private String cvcto;
    private String path;
    private Boolean active;
    private Boolean create;
    private Boolean select;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Contratos";

        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);

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
        if (!select) {
            UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);
            if (userSessionBean.getContrato() > 0) {
                try {
                    this.selectedContr27 = contr27Manager.getContr27(userSessionBean.getContrato());
                    this.selectedTraba36 = traba36Manager.getTraba36(userSessionBean.getContratoTrabajador());
                } catch (Contr27NotFound | Traba36NotFound ex) {
                    Logger.getLogger(Contr27BackingBean.class.getName()).log(Level.SEVERE, null, ex);
                }

                this.active = true;

                System.out.println("prepareSelect():" + selectedContr27.toString());
                System.out.println("prepareSelect():" + selectedTraba36.toString());

                this.cvcto = selectedContr27.getCvcto();
                getListCateg01(cvcto);

                if (contr27Manager.alreadyExistsInnactive(selectedContr27.getIdcto())) {
                    this.active = false;
                }
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

                Traba36 traba36 = traba36Manager.getTraba36(userSessionBean.getContratoTrabajador());

                Contr27 contr27 = new Contr27();

                contr27.setNtcto(traba36.getNutra());
                contr27.setApcto(traba36.getAptra());
                contr27.setAmcto(traba36.getAmtra());
                contr27.setNocto(traba36.getNotra());
                contr27.setRfcto(traba36.getRftra());
                contr27.setShcto(traba36.getSstra());
                contr27.setFhcto(traba36.getFstra());
                contr27.setPfcto(traba36.getPatra());

                contr27.setSscto("VIGENTE");
                Date date = new Date();
                this.newContr27 = new Contr27(contr27.getNtcto(), contr27.getApcto(), contr27.getAmcto(), contr27.getNocto(), contr27.getRfcto(), contr27.getShcto(), contr27.getFhcto(), contr27.getPfcto(), contr27.getSscto(), date, "CONTRA", date);
                create = true;
            } catch (Traba36NotFound ex) {
                Logger.getLogger(Contr27BackingBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param idcto
     * @return
     */
    public Contr27 getContr27Request(Integer idcto) {

        try {

            this.selectedContr27 = contr27Manager.getContr27(idcto);

            if (contr27Manager.alreadyExistsInnactive(idcto)) {
                this.active = false;
            }

        } catch (Contr27NotFound ex) {
            Logger.getLogger(Contr27Backing.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        } catch (Exception ex) {
            Logger.getLogger(Contr27Backing.class.getName()).log(Level.SEVERE, null, ex);
            //getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
            infoMessage = new LanguagesUtil().getResources("reports.error");
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
        }

        return selectedContr27;
    }

    /**
     *
     * @return
     */
    public List<Contr27> getContr27List() {
        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);

        if (userSessionBean.getContratoTrabajador() != null && userSessionBean.getContratoTrabajador() > 0) {
            this.contr27List = contr27Manager.getListContr27Ntcto(userSessionBean.getContratoTrabajador());

            if (contr27List.isEmpty()) {
                infoMessage = "No se encontraron contratos del No./Trabajador: " + userSessionBean.getContratoTrabajador() + ".";

            } else {
                infoMessage = contr27List.size() + " contratos.";

            }
        } else {
            infoMessage = "Proporcione un numero de trabajador.";
        }

        return contr27List;
    }

    /**
     *
     * @param contr27List
     */
    public void setContr27List(List<Contr27> contr27List) {
        this.contr27List = contr27List;
    }

    /**
     *
     * @param nutra
     * @return
     */
    public List<Contr27> getListContr27(Integer nutra) {
        contr27List = contr27Manager.getListContr27Ntcto(nutra);

        if (!contr27List.isEmpty()) {
            infoMessage = contr27List.size() + " contratos";
        }
        return contr27List;
    }

    /**
     *
     * @return
     */
    public String register() {

        try {

            if (path != null) {
                newContr27.setPacto(path);
            }

            newContr27.setStcto(Constants.RECORD_ACTIVED);
            newContr27.setFecto(new Date());
            newContr27.setUscto(FacesUtil.getUserName());

            selectedContr27 = contr27Manager.registerContr27(newContr27);

            UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);

            userSessionBean.setContrato(selectedContr27.getIdcto());
            userSessionBean.setContratoTrabajador(selectedContr27.getNtcto());
            infoMessage = "El contrato se creo correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);

            return "success";

        } catch (Contr27AlreadyExists ex) {
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
    public String deleteContr27() {
        try {

            selectedContr27.setStcto(Constants.RECORD_DELETED);
            selectedContr27.setFecto(new Date());
            selectedContr27.setUscto(FacesUtil.getUserName());

            selectedContr27 = contr27Manager.deleteContr27(selectedContr27);

            infoMessage = "El contrato se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            return "success";

        } catch (Contr27NotFound ex) {
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

            selectedContr27.setStcto(Constants.RECORD_ACTIVED);
            selectedContr27.setFecto(new Date());
            selectedContr27.setUscto(FacesUtil.getUserName());

            selectedContr27 = contr27Manager.updateContr27(selectedContr27);

            if (!contr27Manager.alreadyExistsInnactive(selectedContr27.getIdcto())) {
                infoMessage = "El contrato fue activado correctamente";
                FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            }
            return "success";

        } catch (Contr27NotFound ex) {
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
    public String updateContr27Foto() {
        String result = "";

        if (newContr27 != null) {
            newContr27.setPacto(null);
            result = "success";
        }

        if (selectedContr27 != null) {
            selectedContr27.setPacto(null);
            result = updateContr27();
        }
        if (result.equals("success")) {
            FacesUtil.removeManagedBeanInSession("fileUploadBean");
            String oldInfoMessageModule = infoMessageModule;
            infoMessageModule = "Contrato";
            infoMessage = "Se borro el Documento del Contrato";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            infoMessageModule = oldInfoMessageModule;
        }
        return "success";
    }

    /**
     *
     * @return
     */
    public String updateContr27() {

        try {
            if (path != null) {
                selectedContr27.setPacto(path);
            }

            selectedContr27.setFecto(new Date());
            selectedContr27.setUscto(FacesUtil.getUserName());

            selectedContr27 = contr27Manager.updateContr27(selectedContr27);

            return "success";

        } catch (Contr27NotFound ex) {
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

        String outcome = "/secured/contratos/contratoView.xhtml?faces-redirect=true";

        if (action == 1) {
            outcome = "/secured/contratos/contratoEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/contratos/contratoCreate.xhtml?faces-redirect=true";
        }
        if (action == 9) {
            outcome = "/secured/contratos/contratoTrabajador.xhtml?faces-redirect=true";
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
            case "cvcto":
                if (newContr27 != null) {

                    try {
                        newContr27.setNecto(empre04Manager.getEmpre04(value).getNoemp());
                    } catch (Empre04NotFound ex) {
                        Logger.getLogger(Contr27BackingBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    newContr27.setSpcto("");
                    newContr27.setDpcto("");
                    getListCateg01(value);
                    cvcto = value;

                }
                if (selectedContr27 != null) {

                    try {
                        selectedContr27.setNecto(empre04Manager.getEmpre04(value).getNoemp());
                    } catch (Empre04NotFound ex) {
                        Logger.getLogger(Contr27BackingBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    selectedContr27.setSpcto("");
                    selectedContr27.setDpcto("");
                    getListCateg01(value);
                    cvcto = value;

                }
                break;
            case "ndcto":
                if (newContr27 != null) {

                    try {
                        newContr27.setNbcto(depar03Manager.getDepar03(newContr27.getCvcto(), value).getNodep());
                    } catch (Depar03NotFound ex) {
                        Logger.getLogger(Contr27BackingBean.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (selectedContr27 != null) {

                    try {
                        selectedContr27.setNbcto(depar03Manager.getDepar03(selectedContr27.getCvcto(), value).getNodep());
                    } catch (Depar03NotFound ex) {
                        Logger.getLogger(Contr27BackingBean.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                break;
            case "cccto":
                if (newContr27 != null) {

                    try {
                        selectedCateg01 = categ01Manager.getCateg01(value, cvcto);
                        newContr27.setCacto(selectedCateg01.getNocat());
                        newContr27.setSxcto(selectedCateg01.getSxcat());
                    } catch (Categ01NotFound ex) {
                        Logger.getLogger(Contr27BackingBean.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (selectedContr27 != null) {

                    try {
                        selectedCateg01 = categ01Manager.getCateg01(value, cvcto);
                        selectedContr27.setCacto(selectedCateg01.getNocat());
                        selectedContr27.setSxcto(selectedCateg01.getSxcat());
                    } catch (Categ01NotFound ex) {
                        Logger.getLogger(Contr27BackingBean.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                break;
            case "tucto":
                if (newContr27 != null) {

                    try {
                        selectedTurno22 = turno22Manager.getTurno22(value);
                        newContr27.setTtcto(selectedTurno22.getNotur());
                    } catch (Turno22NotFound ex) {
                        Logger.getLogger(Contr27BackingBean.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (selectedContr27 != null) {

                    try {
                        selectedTurno22 = turno22Manager.getTurno22(value);
                        selectedContr27.setTtcto(selectedTurno22.getNotur());
                    } catch (Turno22NotFound ex) {
                        Logger.getLogger(Contr27BackingBean.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                break;
            case "tccto":
                if (newContr27 != null) {

                    try {
                        selectedTicon17 = ticon17Manager.getTicon17(value);
                        newContr27.setNccto(selectedTicon17.getNotic());
                    } catch (Ticon17NotFound ex) {
                        Logger.getLogger(Contr27BackingBean.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (selectedContr27 != null) {

                    try {
                        selectedTicon17 = ticon17Manager.getTicon17(value);
                        selectedContr27.setNccto(selectedTicon17.getNotic());
                    } catch (Ticon17NotFound ex) {
                        Logger.getLogger(Contr27BackingBean.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                break;
            case "sccto":
                if (newContr27 != null) {

                    String comit08 = comit08Manager.getComit08Ntcom(Integer.valueOf(value));
                    newContr27.setSpcto(comit08);

                }
                if (selectedContr27 != null) {

                    String comit08 = comit08Manager.getComit08Ntcom(Integer.valueOf(value));
                    selectedContr27.setSpcto(comit08);

                }
                break;
            case "dccto":
                if (newContr27 != null) {

                    String comit08 = comit08Manager.getComit08Ntcom(Integer.valueOf(value));
                    newContr27.setDpcto(comit08);

                }
                if (selectedContr27 != null) {

                    String comit08 = comit08Manager.getComit08Ntcom(Integer.valueOf(value));

                    selectedContr27.setDpcto(comit08);

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
    public List<Ubica23> getUbica23Estados() {

        List<Ubica23> ubica23List = ubica23Manager.getListUbica23Estados();

        return ubica23List;

    }

    /**
     *
     * @param cecat
     */
    public void getListCateg01(String cecat) {

        categ01List = categ01Manager.getListCateg01(cecat);

    }

    /**
     *
     * @return
     */
    public List<Comit08> getComit08ListSup() {
        String cecom = "";

        if (selectedContr27 != null) {
            cecom = selectedContr27.getCvcto();

        } else if (newContr27 != null) {
            cecom = newContr27.getCvcto();

        }
        if (cecom == null) {
            cecom = "";
        }

        System.out.println(cecom + "<-cecom ");
        this.comit08ListSup = comit08Manager.getListComit08CecomCdcom(cecom, "");
        return comit08ListSup;
    }

    /**
     *
     * @param comit08ListSup
     */
    public void setComit08ListSup(List<Comit08> comit08ListSup) {
        this.comit08ListSup = comit08ListSup;
    }

    /**
     *
     * @return
     */
    public List<Comit08> getComit08ListDel() {
        String cecom = "";

        if (selectedContr27 != null) {
            cecom = selectedContr27.getCvcto();
        } else if (newContr27 != null) {
            cecom = newContr27.getCvcto();
        }
        if (cecom == null) {
            cecom = "";
        }

        System.out.println(cecom + "<-cecom ");
        this.comit08ListDel = comit08Manager.getListComit08CecomCdcom(cecom, "");
        return comit08ListDel;
    }

    /**
     *
     * @param comit08ListDel
     */
    public void setComit08ListDel(List<Comit08> comit08ListDel) {
        this.comit08ListDel = comit08ListDel;
    }

    /**
     *
     * @return
     */
    public Contr27 getNewContr27() {
        return newContr27;
    }

    /**
     *
     * @param newContr27
     */
    public void setNewContr27(Contr27 newContr27) {
        this.newContr27 = newContr27;
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
    public List<Categ01> getCateg01List() {
        return categ01List;
    }

    /**
     *
     * @param categ01List
     */
    public void setCateg01List(List<Categ01> categ01List) {
        this.categ01List = categ01List;
    }

    /**
     *
     * @return
     */
    public String getCvcto() {
        return cvcto;
    }

    /**
     *
     * @param cvcto
     */
    public void setCvcto(String cvcto) {
        this.cvcto = cvcto;
    }

    /**
     *
     * @return
     */
    public Categ01 getSelectedCateg01() {
        return selectedCateg01;
    }

    /**
     *
     * @param selectedCateg01
     */
    public void setSelectedCateg01(Categ01 selectedCateg01) {
        this.selectedCateg01 = selectedCateg01;
    }

    /**
     *
     * @return
     */
    public Turno22 getSelectedTurno22() {
        return selectedTurno22;
    }

    /**
     *
     * @param selectedTurno22
     */
    public void setSelectedTurno22(Turno22 selectedTurno22) {
        this.selectedTurno22 = selectedTurno22;
    }

    /**
     *
     * @return
     */
    public Ticon17 getSelectedTicon17() {
        return selectedTicon17;
    }

    /**
     *
     * @param selectedTicon17
     */
    public void setSelectedTicon17(Ticon17 selectedTicon17) {
        this.selectedTicon17 = selectedTicon17;
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

}
