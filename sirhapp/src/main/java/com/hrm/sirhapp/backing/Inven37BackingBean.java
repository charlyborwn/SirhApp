package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Inven37;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.service.Catin44ManagerLocal;
import com.hrm.sirhapp.service.Empre04ManagerLocal;
import com.hrm.sirhapp.service.Inven37ManagerLocal;
import com.hrm.sirhapp.service.Prove48ManagerLocal;
import com.hrm.sirhapp.service.Traba36ManagerLocal;
import com.hrm.sirhapp.service.exception.Empre04NotFound;
import com.hrm.sirhapp.service.exception.Inven37AlreadyExists;
import com.hrm.sirhapp.service.exception.Inven37NotFound;
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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@ViewScoped
public class Inven37BackingBean extends BaseBacking implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @EJB
    private Inven37ManagerLocal inven37Manager;
    @EJB
    private Empre04ManagerLocal empre04Manager;
    @EJB
    private Prove48ManagerLocal prove48Manager;
    @EJB
    private Catin44ManagerLocal catin44Manager;
    @EJB
    private Traba36ManagerLocal traba36Manager;
    
    private Inven37 selectedInven37;
    
    private Inven37 newInven37 = new Inven37();
    
    private List<Inven37> inven37List;
    
    private String infoMessageModule;
    private String infoMessage;
    private Boolean registered;
    private Boolean create;
    private Boolean select;
    private Boolean list;
    private String proveedor;
    
    @PostConstruct
    private void init() {
        this.infoMessageModule = "Modulo Inventario";
        this.registered = false;
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
            create = false;
            select = false;
            list = false;
            prepareSelect();
            String viewId = facesContext.getViewRoot().getViewId();
            System.out.println(viewId);
            if (viewId.contains("inventarioList")) {
                prepareList();
            }
        }
    }
    
    /**
     *
     */
    public void prepareList() {
        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);
        if (!list) {
            if (userSessionBean.getTinven().equals("EXISTENCIAS")) {
                System.out.println("HACER LISTA PARA EXISTENCIAS" + userSessionBean.getTinven());
                retrieveInven37EList();
            } else {
                Inven37 selectListInven37= new Inven37();
                selectListInven37.setTiinv(userSessionBean.getTinven());
                getListInven37T(selectListInven37);
                System.out.println("HACER LISTA PARA MOVIMIENTO" + userSessionBean.getTinven());
            }
        }
    }
    
    /**
     *
     */
    public void prepareSelect() {
        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);
        
        if (!select) {
            try {
                if (userSessionBean.getInven() != null && userSessionBean.getInven() > 0) {
                    this.selectedInven37 = inven37Manager.getInven37(userSessionBean.getInven());
                    System.out.println("Inven37BackingBean:prepareSelect():" + selectedInven37.toString());
                }
            } catch (Inven37NotFound ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
            select = true;
        }
    }
    
    /**
     *
     */
    public void prepareCreate() {
        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);
        if (!create) {
            create = true;
            newInven37.setTiinv(userSessionBean.getTinven());
        }
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
    public Boolean isRegistered() {
        return registered;
    }
    
    /**
     *
     * @param registered
     */
    public void setRegistered(Boolean registered) {
        this.registered = registered;
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
    public Boolean getList() {
        return list;
    }
    
    /**
     *
     * @param list
     */
    public void setList(Boolean list) {
        this.list = list;
    }
    
    /**
     *
     * @return
     */
    public Inven37 getNewInven37() {
        return newInven37;
    }
    
    /**
     *
     * @param newInven37
     */
    public void setNewInven37(Inven37 newInven37) {
        this.newInven37 = newInven37;
    }
    
    /**
     *
     * @return
     */
    public Inven37 getSelectedInven37() {
        return selectedInven37;
    }
    
    /**
     *
     * @param selectedInven37
     */
    public void setSelectedInven37(Inven37 selectedInven37) {
        this.selectedInven37 = selectedInven37;
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
    public String getProveedor() {
        return proveedor;
    }
    
    /**
     *
     * @param proveedor
     */
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    
    /**
     *
     * @return
     */
    public List<Inven37> getInven37List() {
        return inven37List;
    }
    
    /**
     *
     * @param inven37List
     */
    public void setInven37List(List<Inven37> inven37List) {
        this.inven37List = inven37List;
    }
    
    /**
     *
     * @param idinv
     * @return
     */
    public Inven37 getInven37Request(Integer idinv) {
        
        try {
            
            this.selectedInven37 = inven37Manager.getInven37(idinv);
            
        } catch (Inven37NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }
        
        return selectedInven37;
    }
    
    /**
     *
     * @param idinv
     */
    public void getListInven37(Integer idinv) {
        
        inven37List = inven37Manager.getListInven37(idinv);
        
        if (!inven37List.isEmpty()) {
            infoMessage = inven37List.size() + " registro(s)";
        }
    }
    
    /**
     *
     * @param tInven37
     */
    public void getListInven37T(Inven37 tInven37) {
        
        inven37List = inven37Manager.getListInven37(tInven37);
        
        if (!inven37List.isEmpty()) {
            infoMessage = inven37List.size() + " registro(s)";
        }
    }
    
    /**
     *
     */
    public void retrieveInven37List() {
        
        inven37List = inven37Manager.retrieveInven37();
        
        if (inven37List.isEmpty()) {
            infoMessage = "No existen datos";
        } else {
            infoMessage = inven37List.size() + " registros";
        }
        
    }
    
    /**
     *
     */
    public void retrieveInven37EList() {
        
        inven37List = inven37Manager.retrieveInven37E();
        
        if (inven37List.isEmpty()) {
            infoMessage = "No existen datos";
        } else {
            infoMessage = inven37List.size() + " registros";
        }
        
    }
    
    /**
     *
     * @param inven37
     * @return
     */
    public Long countOutInven37(Inven37 inven37) {
        
        Long count;
        count = inven37Manager.countOutInven37(inven37);
        
        return count;
    }
    
    /**
     *
     * @return
     */
    public String updateInven37() {
        try {
            
            if (prove48Manager.retrieveProve48(selectedInven37.getRfinv()).getNcpro().equals("EL PROVEEDOR NO ESTA REGISTRADO")) {
                infoMessage = "Registre primero el Trabajador y vuelva a intentarlo.";
                FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
                return "failure";
            }
            
            if (prove48Manager.retrieveProve48(selectedInven37.getRfinv()).getNcpro().equals("EL PROVEEDOR NO ESTA REGISTRADO")) {
                infoMessage = "Registre primero el Proveedor y vuelva a intentarlo.";
            } else {
                
                selectedInven37.setStinv(Constants.RECORD_ACTIVED);
                selectedInven37.setFeinv(new Date());
                selectedInven37.setUsinv(FacesUtil.getUserName());
                
                inven37Manager.updateInven37(selectedInven37);
                
                infoMessage = "El registro se actualizó correctamente";
            }
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            return "success";
            
        } catch (Inven37NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
            return "failure";
        }
    }
    
    /**
     *
     * @return
     */
    public String deleteInven37() {
        try {
            
            selectedInven37.setStinv(Constants.RECORD_DELETED);
            selectedInven37.setFeinv(new Date());
            selectedInven37.setUsinv(FacesUtil.getUserName());
            
            inven37Manager.deleteInven37(selectedInven37);
            
            infoMessage = "El registro se eliminó correctamente.";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            
        } catch (Inven37NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
        }
        
        return null;
    }
    
    /**
     *
     * @return
     */
    public String register() {
        
        try {
            
            if (prove48Manager.retrieveProve48(newInven37.getRfinv()).getNcpro().equals("EL PROVEEDOR NO ESTA REGISTRADO")) {
                infoMessage = "Registre primero el Proveedor y vuelva a intentarlo.";
            } else {
                
                newInven37.setStinv(Constants.RECORD_ACTIVED);
                newInven37.setFeinv(new Date());
                newInven37.setUsinv(FacesUtil.getUserName());
                
                inven37Manager.registerInven37(newInven37);
                
                infoMessage = "El registro se creo correctamente";
            }
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, infoMessageModule, infoMessage);
            
        } catch (Inven37AlreadyExists ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = ex.getMessage();
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, infoMessageModule, infoMessage);
            return "failure";
            
        } catch (Exception ex) {
            
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            infoMessage = "Ocurrio un error en la base de datos";
            FacesUtil.addMessage(FacesMessage.SEVERITY_FATAL, infoMessageModule, infoMessage);
            return "failure";
        }
        
        return null;
    }
    
    /**
     *
     * @param vce
     * @throws IOException
     */
    public void handleChange(AjaxBehaviorEvent vce) throws IOException {
        
        String value = "";
        String id = ((UIComponent) vce.getSource()).getId();
        switch (id) {
            case "arinv":
            case "mainv":
            case "moinv":
            case "coinv":
                if (selectedInven37 != null) {
                    updateInven37();
                    go(1);
                }
                break;
            case "ntinv":
                if (selectedInven37 != null) {
                    selectedInven37.setNninv(traba36Manager.getTraba36Nctra((Integer) ((ValueHolder) vce.getSource()).getValue()));
                }
                if (newInven37 != null) {
                    newInven37.setNninv(traba36Manager.getTraba36Nctra((Integer) ((ValueHolder) vce.getSource()).getValue()));
                }
                break;
            case "ceinv":
                value = (String) ((ValueHolder) vce.getSource()).getValue();
                if (selectedInven37 != null) {
                    try {
                        selectedInven37.setNeinv(empre04Manager.getEmpre04(value).getNoemp());
                        System.out.println(empre04Manager.getEmpre04(value).getNoemp());
                        System.out.println(selectedInven37.getNeinv());
                    } catch (Empre04NotFound ex) {
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (newInven37 != null) {
                    try {
                        newInven37.setNeinv(empre04Manager.getEmpre04(value).getNoemp());
                        System.out.println(empre04Manager.getEmpre04(value).getNoemp());
                        System.out.println(newInven37.getNeinv());
                    } catch (Empre04NotFound ex) {
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "rfinv":
                value = (String) ((ValueHolder) vce.getSource()).getValue();
                
                if (selectedInven37 != null) {
                    if (value != null && value.length() >= 10) {
                        selectedInven37.setNcinv(prove48Manager.retrieveProve48(value).getNcpro());
                    } else {
                        selectedInven37.setNcinv(prove48Manager.retrieveProve48("").getNcpro());
                    }
                }
                if (newInven37 != null) {
                    if (value != null && value.length() >= 10) {
                        newInven37.setNcinv(prove48Manager.retrieveProve48(value).getNcpro());
                    } else {
                        newInven37.setNcinv(prove48Manager.retrieveProve48("").getNcpro());
                    }
                }
                
                System.out.println(value);
                break;
            
            default:
                break;
        }
    }
    
    /**
     *
     * @param query
     * @return
     */
    public List<String> proveedores(String query) {
        
        List<String> results = prove48Manager.getListProve48Rfpro(query);
        
        return results;
    }
    
    /**
     *
     * @param query
     * @return
     */
    public List<String> descripcionArticulos(String query) {
        
        List<String> results = catin44Manager.getListCatin44Arcin(query);
        
        return results;
    }
    
    /**
     *
     * @param query
     * @return
     */
    public List<String> marcaArticulos(String query) {
        
        List<String> results = catin44Manager.getListCatin44Macin(newInven37.getArinv(), query);
        
        return results;
    }
    
    /**
     *
     * @param query
     * @return
     */
    public List<String> modeloArticulos(String query) {
        
        List<String> results = catin44Manager.getListCatin44Mocin(newInven37.getArinv(), newInven37.getMainv(), query);
        
        return results;
    }
    
    /**
     *
     * @param query
     * @return
     */
    public List<String> colorArticulos(String query) {
        
        List<String> results = catin44Manager.getListCatin44Cocin(newInven37.getArinv(), newInven37.getMainv(), newInven37.getMoinv(), query);
        
        return results;
    }
    
    /**
     *
     * @param query
     * @return
     */
    public List<String> tallaArticulos(String query) {
        
        List<String> results = catin44Manager.getListCatin44Tacin(newInven37.getArinv(), newInven37.getMainv(), newInven37.getMoinv(), newInven37.getCoinv(), query);
        
        return results;
    }
    
    /**
     *
     * @param action
     * @return
     * @throws IOException
     */
    public String go(int action) throws IOException {
        
        String outcome = "/secured/inventarios/inventarioList.xhtml?faces-redirect=true";

        if (action == 3) {
            outcome = "/secured/inventarios/inventarioView.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/inventarios/inventarioEdit.xhtml?faces-redirect=true";
        }
        if (action == 0) {
            outcome = "/secured/inventarios/inventarioCreate.xhtml?faces-redirect=true";
        }
        
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);
        
        return outcome;
        
    }
}
