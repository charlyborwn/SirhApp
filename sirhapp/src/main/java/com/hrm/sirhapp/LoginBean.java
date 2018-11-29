package com.hrm.sirhapp;

import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Niacc33;
import com.hrm.sirhapp.model.Usuar24;
import com.hrm.sirhapp.service.Niacc33ManagerLocal;
import com.hrm.sirhapp.service.Usuar24ManagerLocal;
import com.hrm.sirhapp.service.exception.Usuar24NotFound;
import com.hrm.sirhapp.util.FacesUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Manage the login of the App
 * 
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@RequestScoped
public class LoginBean implements Serializable {

    private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(LoginBean.class);

    private static final long serialVersionUID = 1L;

    private Usuar24 usuar24;
    private String username;
    private String password;

    private String beanName;
    private String beanMethod;
    private int action;

    private String variableBeanName;
    private String variableBeanMethod;
    private String variableBeanValue;
    private String variableBeanType;

    private String redirect;

    @EJB
    private Usuar24ManagerLocal userManager;

    @EJB
    private Niacc33ManagerLocal niacc33Manager;

    @PostConstruct
    private void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
            System.out.println("preRenderView called");
        }
    }

    /**
     *
     * @return
     */
    public String getRedirect() {
        
        ResourceBundle bundle = ResourceBundle.getBundle("Bundle");
        redirect = bundle.getString("StartPage");

        return redirect;
    }

    /**
     *
     * @param redirect
     */
    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    /**
     *
     * @param beanName
     * @param beanMethod
     * @param action
     */
    public void Params(String beanName, String beanMethod, int action) {
        this.beanName = beanName;
        this.beanMethod = beanMethod;
        this.action = action;
        System.out.println("se establecieron los valores para ejecutar: " + beanName + "." + beanMethod + ":action:" + action);
    }

    /**
     *
     * @param beanName
     * @param beanMethod
     * @param action
     * @param variableBeanName
     * @param variableBeanMethod
     * @param variableBeanValue
     * @param variableBeanType
     */
    public void Params(String beanName, String beanMethod, int action, String variableBeanName, String variableBeanMethod, String variableBeanValue, String variableBeanType) {
        this.beanName = beanName;
        this.beanMethod = beanMethod;
        this.action = action;
        this.variableBeanName = variableBeanName;
        this.variableBeanMethod = variableBeanMethod;
        this.variableBeanValue = variableBeanValue;
        this.variableBeanType = variableBeanType;
        System.out.println("se establecieron los valores para ejecutar: " + beanName + "." + beanMethod + ":action:" + action + " set " + variableBeanName + "." + variableBeanMethod + "() :value: " + variableBeanValue);
    }

    /**
     *
     * @return
     */
    public String go() {
        System.out.println(action);
        return FacesUtil.runManagedBeanAction(beanName, action);
    }

    /**
     *
     */
    public void withOutAuth() {
        System.out.println("Execute: " + beanMethod + "." + beanName + "() -> action: " + action);

        String result = FacesUtil.runManagedBean(beanName, beanMethod);

        System.out.println("Execute: " + variableBeanName + "." + variableBeanMethod + "() -> value: " + variableBeanValue);

        if (variableBeanName != null && variableBeanName.length() > 0) {
            String resultSetVariable;
            resultSetVariable = FacesUtil.setSessionBeanAttValue(variableBeanName, variableBeanMethod, variableBeanValue, String.class);
            System.out.println("Resultado de la ejecución de: " + variableBeanName + "." + variableBeanMethod + "() :value: " + variableBeanValue + " :resultado: " + resultSetVariable);
        }

        System.out.println("Resultado de la ejecución de: " + beanName + "." + beanMethod + "() :resultado: " + result);

        if (action != -1) {
            go();
        }
    }

    /**
     *
     */
    public void auth() {
        FacesMessage message;
        this.username = FacesUtil.getUserName();
        this.usuar24 = FacesUtil.getUsuar24();

        System.out.println("Este es el nombre del usuario: " + this.username);

        if (usuar24 != null) {
            if (username != null && username.equals(this.username) && password != null && password.equals(usuar24.getPausu())) {

                System.out.println("Execute: " + beanMethod + "." + beanName + "() -> action: " + action);

                String result = FacesUtil.runManagedBean(beanName, beanMethod);

                System.out.println("Execute: " + variableBeanName + "." + variableBeanMethod + "() -> value: " + variableBeanValue);

                if (variableBeanName != null && variableBeanName.length() > 0) {
                    String resultSetVariable = "";
                    switch (variableBeanType) {
                        case "String":
                            resultSetVariable = FacesUtil.setSessionBeanAttValue(variableBeanName, variableBeanMethod, variableBeanValue, String.class);
                            break;
                        case "Integer":
                            resultSetVariable = FacesUtil.setSessionBeanAttValue(variableBeanName, variableBeanMethod, variableBeanValue, Integer.class);
                            break;
                        default:
                            break;
                    }
                    System.out.println("Resultado de la ejecución de: " + variableBeanName + "." + variableBeanMethod + "() :value: " + variableBeanValue + " :resultado: " + resultSetVariable);
                }

                System.out.println("Resultado de la ejecución de: " + beanName + "." + beanMethod + "() :resultado: " + result);

                if (action != -1) {
                    go();
                }
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de Loggin", "El password es incorrecto: " + username);
                FacesUtil.addMessage(null, message);
            }
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de Loggin", "No existe session del usuario: " + username);
            FacesUtil.addMessage(null, message);
        }
    }

    /**
     *
     * @return
     */
    public String login() {
        username = username.toUpperCase();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();

        try {

            usuar24 = userManager.getUsuar24(username);

            // Valida el pass del usuario
            if (usuar24.getPausu().equals(password)) {
                // Obtiene el status del usuario
                if (!usuar24.getStusu().equals(Constants.RECORD_DELETED)) {
                    List<String> niacc33List = niacc33Manager.getListNiacc33(username);
                    //System.out.println("Lista de Navegacion por usuario" + niacc33List.size());
                    // Valida permisos
                    if (!niacc33List.isEmpty()) {
                        request.login(username, password);
                        List<Niacc33> niacc33ListNav;

                        for (int i = 0; i < niacc33List.size(); i++) {
                            //System.out.println("Roles de los que se va a obtener la navegacion: " + niacc33List.get(i));

                            niacc33ListNav = niacc33Manager.getListNiacc33Nav(niacc33List.get(i));

                            //Conocer la Lista de elementos de Navegacion permitidos por el usuario
                            //System.out.println("Lista de Navegacion completa" + niacc33ListNav.size());
                            if (!niacc33ListNav.isEmpty()) {
                                for (Niacc33 niacc33 : niacc33ListNav) {
                                    if (niacc33.getCpnia() != null && niacc33.getCpnia().length() > 0) {
                                        FacesUtil.setSessionBeanAttValue("userSessionBean", "set" + niacc33.getCpnia().replace("m", "M"), true, Boolean.class);
                                    }
                                }
                            }

                        }
                        FacesUtil.setLoggedinUser(usuar24);

                        FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, "Ingreso correcto", "Bienvenido: " + usuar24.getNcusu());

                        LOGGER.info("IP[" + FacesUtil.getUserIpAddress() + "] USR[" + usuar24.getCvusu() + "] " + "| ACT: Inicio de sesion correcto");

                        externalContext.redirect(externalContext.getRequestContextPath() + getRedirect());

                        return "success";

                    } else {
                        FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, "El usuario no tiene permisos para ingresar", "Usuario: " + username);

                        LOGGER.info("IP[" + FacesUtil.getUserIpAddress() + "] USR[" + username + "] " + "| ACT: El usuario no tiene permisos para ingresar");
                    }
                } else {
                    FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, "El usuario no esta activo", "Usuario: " + username);

                    LOGGER.info("IP[" + FacesUtil.getUserIpAddress() + "] USR[" + username + "] " + "| ACT: El usuario no esta activo");
                }

            } else {

                FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, "Ingreso al Sistema", "El password es incorrecto: " + username);

                LOGGER.info("IP[" + FacesUtil.getUserIpAddress() + "] USR[" + username + "] " + "| ACT: El password es incorrecto");

            }

        } catch (Usuar24NotFound ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, "Ingreso al Sistema", "El usuario no existe: " + username);

            LOGGER.info("IP[" + FacesUtil.getUserIpAddress() + "] USR[" + username + "] " + "| ACT: El usuario no existe");

        } catch (ServletException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "Error al crear la session");

            LOGGER.error("IP[" + FacesUtil.getUserIpAddress() + "] USR[" + username + "] " + "GRAVE: " + ex.getMessage());

            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            System.err.println(errors);
            System.err.println(ex);

        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "failure";

    }

    /**
     *
     */
    public void checkAuthentication() {
        // Get the current servlet request from the facesContext
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();

        try {
            if (request.getUserPrincipal() != null) {
                externalContext.redirect(externalContext.getRequestContextPath() + getRedirect());
            }
        } catch (IOException ex) {
            FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Autenticación del usuario");

            LOGGER.error("IP[" + FacesUtil.getUserIpAddress() + "] USR[" + username + "] " + "Autenticación del usuario: IOException ");

            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
        }
    }

    /**
     *
     * @param rep
     * @param user
     * @param pass
     */
    public void automAuthentication(String rep, String user, String pass) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        ExternalContext externalcontext = facesContext.getExternalContext();

        try {
            if (request.getUserPrincipal() == null) {
                usuar24 = userManager.getUsuar24(user);

                if (usuar24.getPausu().equals(password)) {
                    // Get the current servlet request from the facesContext
                    if (!usuar24.getStusu().equals(Constants.RECORD_DELETED)) {

                        request.login(user, pass);
                        externalcontext.redirect(externalcontext.getRequestContextPath() + "/secured/kardex/" + rep);

                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Ingreso correcto"));

                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario no esta activo", usuar24.getNousu()));

                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso al Sistema", "El password es incorrecto: " + username));

                }
            }

        } catch (IOException | ServletException e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Información", "Ingreso no autorizado"));

            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));

        } catch (Usuar24NotFound ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getBeanMethod() {
        return beanMethod;
    }

    /**
     *
     * @param beanMethod
     */
    public void setBeanMethod(String beanMethod) {
        this.beanMethod = beanMethod;
    }

    /**
     *
     * @return
     */
    public String getBeanName() {
        return beanName;
    }

    /**
     *
     * @param beanName
     */
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    /**
     *
     * @return
     */
    public int getAction() {
        return action;
    }

    /**
     *
     * @param action
     */
    public void setAction(int action) {
        this.action = action;
    }

    /**
     *
     * @return
     */
    public String getVariableBeanName() {
        return variableBeanName;
    }

    /**
     *
     * @param variableBeanName
     */
    public void setVariableBeanName(String variableBeanName) {
        this.variableBeanName = variableBeanName;
    }

    /**
     *
     * @return
     */
    public String getVariableBeanMethod() {
        return variableBeanMethod;
    }

    /**
     *
     * @param variableBeanMethod
     */
    public void setVariableBeanMethod(String variableBeanMethod) {
        this.variableBeanMethod = variableBeanMethod;
    }

    /**
     *
     * @return
     */
    public String getVariableBeanValue() {
        return variableBeanValue;
    }

    /**
     *
     * @param variableBeanValue
     */
    public void setVariableBeanValue(String variableBeanValue) {
        this.variableBeanValue = variableBeanValue;
    }

    /**
     *
     * @return
     */
    public String getVariableBeanType() {
        return variableBeanType;
    }

    /**
     *
     * @param variableBeanType
     */
    public void setVariableBeanType(String variableBeanType) {
        this.variableBeanType = variableBeanType;
    }

}
