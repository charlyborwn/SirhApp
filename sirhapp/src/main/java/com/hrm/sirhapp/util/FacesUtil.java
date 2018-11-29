package com.hrm.sirhapp.util;

import com.hrm.sirhapp.model.Usuar24;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Principal;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
public class FacesUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    // Getters -----------------------------------------------------------------------------------

    /**
     *
     * @param event
     * @param name
     * @return
     */
    public static String getActionAttribute(ActionEvent event, String name) {
        return (String) event.getComponent().getAttributes().get(name);
    }

    /**
     * Get servlet context.
     *
     * @return the servlet context
     */
    public static ServletContext getServletContext() {
        return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    }

    /**
     *
     * @return
     */
    public static ExternalContext getExternalContext() {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc.getExternalContext();
    }

    /**
     *
     * @param create
     * @return
     */
    public static HttpSession getHttpSession(boolean create) {
        return (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(create);
    }
    
    /**
     *
     * @param <T>
     * @param beanName
     * @param type
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getManagedBean(String beanName, Class type) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        StringBuffer sb = new StringBuffer("#{");
        sb.append(beanName).append("}");
        //System.out.println(sb.toString());
        ValueExpression valueExpression = expressionFactory.createValueExpression(
                facesContext.getELContext(), sb.toString(), type);
        return (T) valueExpression.getValue(facesContext.getELContext());
    }

    /**
     * Remove the managed bean based on the bean name.
     *
     * @param beanName the bean name of the managed bean to be removed
     * @param type the class type of the bean
     */
    public static void resetManagedBean(String beanName, Class type) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExpressionFactory expressionFactory
                = facesContext.getApplication().getExpressionFactory();
        StringBuffer sb = new StringBuffer("#{");
        sb.append(beanName).append("}");
        ValueExpression valueExpression = expressionFactory.createValueExpression(
                facesContext.getELContext(), sb.toString(), type);
        valueExpression.setValue(facesContext.getELContext(), null);
        Object evaluateExpressionGet = facesContext.getApplication().evaluateExpressionGet(facesContext, getJsfEl(beanName), type);
        System.out.println(evaluateExpressionGet);
    }

    //Este metodo se implementa por urgencia de tiempo despues mejorar 
    //para que reciba n variables y ejecute n metodos

    /**
     *
     * @param beanName
     * @param beanMethod
     * @param var1
     * @param var2
     * @param var3
     * @return
     */
    public static InputStream runManagedBeanToPdf(String beanName, String beanMethod, String var1, String var2, Boolean var3) {
        try {

            Object objBeanName = FacesUtil.getManagedBean(beanName, Object.class);
            Method objBeanMethod = objBeanName.getClass().getMethod(beanMethod, String.class, String.class, Boolean.class);
            InputStream result = (InputStream) objBeanMethod.invoke(objBeanName, var1, var2, var3);

            return result;

        } catch (IllegalArgumentException | SecurityException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            Logger.getLogger(FacesUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @param beanName
     * @param beanMethod
     * @return
     */
    public static String runManagedBean(String beanName, String beanMethod) {
        try {

            Object objBeanName = FacesUtil.getManagedBean(beanName, Object.class);
            Method objBeanMethod = objBeanName.getClass().getMethod(beanMethod);
            String result = (String) objBeanMethod.invoke(objBeanName);
            System.out.println("Executed: " + beanName + "." + beanMethod + "()");
            return result;

        } catch (IllegalArgumentException | SecurityException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            Logger.getLogger(FacesUtil.class.getName()).log(Level.SEVERE, null, ex);
            return "failure";
        }
    }

    /**
     *
     * @param beanName
     * @param beanMethod
     * @param value
     * @param cls
     * @return
     */
    public static String setSessionBeanAttValue(String beanName, String beanMethod, Object value, Class<?> cls) {
        try {

            Object objBeanName = FacesUtil.getManagedBean(beanName, Object.class);

            Method objBeanMethod = objBeanName.getClass().getMethod(beanMethod, cls);

            if (cls.equals(String.class)) {
                String y = value.toString();
                objBeanMethod.invoke(objBeanName, y);
            } else if (cls.equals(Integer.class)) {
                Integer y = Integer.parseInt(value.toString());
                objBeanMethod.invoke(objBeanName, y);
            } else if (cls.equals(Boolean.class)) {
                Boolean y = (Boolean) value;
                objBeanMethod.invoke(objBeanName, y);
            }

            return "success";

        } catch (IllegalArgumentException | SecurityException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            Logger.getLogger(FacesUtil.class.getName()).log(Level.SEVERE, null, ex);
            return "failure";
        }
    }

    /**
     *
     * @param beanName
     * @param action
     * @return
     */
    public static String runManagedBeanAction(String beanName, int action) {
        try {
            Object objBeanName = FacesUtil.getManagedBean(beanName, Object.class);
            Method go = objBeanName.getClass().getMethod("go", int.class);
            String result = (String) go.invoke(objBeanName, action);
            return result;

        } catch (IllegalArgumentException | SecurityException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            Logger.getLogger(FacesUtil.class.getName()).log(Level.SEVERE, null, ex);
            return "failure";
        }
    }

    /**
     * Store the managed bean inside the session scope.
     *
     * @param beanName the name of the managed bean to be stored
     * @param managedBean the managed bean to be stored
     */
    public static void setManagedBeanInSession(String beanName, Object managedBean) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(beanName, managedBean);
    }

    /**
     *
     * @param beanName
     */
    public static void removeManagedBeanInSession(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove(beanName);
        context.getExternalContext().getSessionMap().put(beanName, null);
    }

    /**
     * Get parameter value from request scope.
     *
     * @param name the name of the parameter
     * @return the parameter value
     */
    public static String getRequestParameter(String name) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
    }

    /**
     * Add information message.
     *
     * @param msg the information message
     */
    public static void addInfoMessage(String msg) {
        addInfoMessage(null, msg);
    }

    /**
     * Add information message to a specific client.
     *
     * @param clientId the client id
     * @param msg the information message
     */
    public static void addInfoMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
    }

    /**
     * Add information message from the specific resource.
     *
     * @param key the client id
     * @param params the information message
     */
    public static void addInfoMessage(String key, Object params[]) {
        String message = getMessageResourceString(key, params);
        addInfoMessage(message);
    }

    /**
     * Add error message.
     *
     * @param msg the error message
     */
    public static void addErrorMessage(String msg) {
        addErrorMessage(null, msg);
    }

    /**
     *
     * @return
     */
    public static HttpServletRequest getServletRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    /**
     *
     * @return
     */
    public static HttpServletResponse getServletResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    /**
     * Add error message to a specific client.
     *
     * @param clientId the client id
     * @param msg the error message
     */
    public static void addErrorMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
    }

    /**
     * Add error message from the default resource.
     *
     * @param key the client id
     * @param params the error message
     */
    public static void addErrorMessageFromResource(String key, Object params[]) {
        String message = getMessageResourceString(key, params);
        addErrorMessage(message);
    }

    private static Application getApplication() {
        ApplicationFactory appFactory = (ApplicationFactory) FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
        return appFactory.getApplication();
    }

    private static String getJsfEl(String value) {
        return "#{" + value + "}";
    }

    private static ClassLoader getCurrentClassLoader(Object defaultObject) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null) {
            loader = defaultObject.getClass().getClassLoader();
        }
        return loader;
    }

    /**
     *
     * @param user
     */
    public static void setLoggedinUser(Usuar24 user) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        session.setAttribute("LOGIN_USER", user);
    }

    /**
     *
     * @return
     */
    public static Usuar24 getLoggedinUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        return (Usuar24) session.getAttribute("LOGIN_USER");
    }

    /**
     *
     * @param msg
     */
    public static void addInfoMsg(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
    }

    /**
     *
     * @param msg
     */
    public static void addErrorMsg(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
    }

    /**
     *
     * @param componentId
     * @param msg
     */
    public static void addInfoMsg(String componentId, String msg) {
        FacesContext.getCurrentInstance().addMessage(componentId,
                new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
    }

    /**
     *
     * @param componentId
     * @param msg
     */
    public static void addErrorMsg(String componentId, String msg) {
        FacesContext.getCurrentInstance().addMessage(componentId,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
    }

    /**
     *
     * @param key
     * @return
     */
    public static String getMessageResourceString(
            String key) {
        return getMessageResourceString(key, null);
    }

    /**
     *
     * @param key
     * @param params
     * @return
     */
    public static String getMessageResourceString(
            String key,
            Object params[]) {
        Locale locale = Locale.getDefault();
        String text;
        String bundleName
                = FacesContext.getCurrentInstance().getApplication().getMessageBundle();
        ResourceBundle bundle;
        if (params == null) {
            bundle = ResourceBundle.getBundle(bundleName, locale);
        } else {
            bundle = ResourceBundle.getBundle(bundleName, locale,
                    getCurrentClassLoader(params));
        }

        try {
            text = bundle.getString(key);
        } catch (MissingResourceException e) {
            text = "?? key " + key + " not found ??";
        }

        if (params != null) {
            MessageFormat mf = new MessageFormat(text, locale);
            text = mf.format(params, new StringBuffer(), null).toString();
        }

        return text;
    }

    /**
     * Add information message.
     *
     * @param growl  severity INFO|WARN|ERROR|FATAL
     * @param message the message

     */
    public static void addMessage(String growl, FacesMessage message) {

        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(growl, message);

    }

    /**
     * Add information message.
     *
     * @param severity the message severity INFO|WARN|ERROR|FATAL
     * @param infoMessageModule infoMessageModule
     * @param infoMessage infoMessage
     */
    public static void addMessage(FacesMessage.Severity severity, String infoMessageModule, String infoMessage) {

        FacesMessage message = new FacesMessage(severity, infoMessageModule, infoMessage);
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, message);
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("growl");

    }

    /**
     *
     * @return
     */
    public static String getUserName() {
        String sysUser = "SYSTEM";
        try {

            Principal user = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

            if (user == null) {
                return sysUser;
            } else {
                return user.getName();
            }

        } catch (IllegalArgumentException | SecurityException ex) {
            return sysUser;
        }
    }

    /**
     *
     * @return
     */
    public static String getUserIpAddress() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    /**
     *
     * @return
     */
    public static String getUserIpAddressLog() {
        String result = " IP[" + getUserIpAddress() + "] USR[" + getUserName() + "] ";
        return result;
    }

    /**
     *
     * @return
     */
    public static Map<String, String[]> getParameterMap() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        Map<String, String[]> map = request.getParameterMap();
        //Reading the Map
        //Works for GET && POST Method
        for (String paramName : map.keySet()) {
            String[] paramValues = map.get(paramName);

            //Get Values of Param Name
            for (String valueOfParam : paramValues) {
                //Output the Values
                System.out.println("Value of Param with Name " + paramName + ": " + valueOfParam);
            }
        }
        return map;
    }

    /**
     *
     * @return
     */
    public static String getUrl() {

        ResourceBundle bundle = ResourceBundle.getBundle("Bundle");
        String urlApp = bundle.getString("UrlApp");

        FacesContext context = FacesContext.getCurrentInstance();
        Object result = context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), urlApp, Object.class).getValue(context.getELContext());

        System.out.println("Deferred expression: " + urlApp + " Value:" + result.toString());
        return result.toString();

    }

    /**
     *
     * @return
     */
    public static Usuar24 getUsuar24() {
        try {
            Object selectedUser = FacesUtil.getManagedBean("userProfileBean", Object.class);
            Method method = selectedUser.getClass().getMethod("getSelectedUsuar24Request");
            Usuar24 usuar24 = (Usuar24) method.invoke(selectedUser);
            return usuar24;
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            return null;
        }
    }

    /**
     *
     * @return
     */
    public static String getClaveDianmica() {
        try {
            Object clavesDinamicasBean = FacesUtil.getManagedBean("clavesDinamicasBean", Object.class);
            Method getPassword = clavesDinamicasBean.getClass().getMethod("getPassword");
            String claveDianmica = (String) getPassword.invoke(clavesDinamicasBean);
            return claveDianmica;
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            return null;
        }
    }

    /**
     *
     * @param s
     * @param n
     * @param padding
     * @return
     */
    public static String padRight(String s, int n, char padding) {
        String pad = "";
        for (int i = 0; i < n; i++) {
            pad += padding;
        }
        return s + pad;
    }

    /**
     *
     * @param s
     * @param n
     * @param padding
     * @return
     */
    public static String padLeft(String s, int n, char padding) {
        String pad = "";
        for (int i = 0; i < n; i++) {
            pad += padding;
        }
        return pad + s;
    }

    /**
     *
     * @param s
     * @param n
     * @param padding
     * @return
     */
    public static String pad(String s, int n, char padding) {
        return padLeft(padRight(s, n, padding), n, padding);
    }

    /**
     *
     * @return
     */
    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date localdate = new Date();
        return dateFormat.format(localdate);
    }

    /**
     *
     * @param date
     * @return
     */
    public static String getDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date localdate = date;
        return dateFormat.format(localdate);
    }

    /**
     *
     * @return
     */
    public static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date localdate = new Date();
        return dateFormat.format(localdate);
    }

    /**
     *
     * @param date
     * @return
     */
    public static String getDateTime(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date localdate = date;
        return dateFormat.format(localdate);
    }

    /**
     *
     * @return
     */
    public static String getDatewFormat() {
        DateFormat dateFormatDay = new SimpleDateFormat("dd");
        DateFormat dateFormatMonth = new SimpleDateFormat("MMMM");
        DateFormat dateFormatYear = new SimpleDateFormat("yyyy");
        Date localdate = new Date();

        String str = dateFormatDay.format(localdate) + " de " + dateFormatMonth.format(localdate) + " de " + dateFormatYear.format(localdate);
        return str;
    }

    /**
     *
     * @param date
     * @return
     */
    public static String getDatewFormat(Date date) {
        DateFormat dateFormatDay = new SimpleDateFormat("dd");
        DateFormat dateFormatMonth = new SimpleDateFormat("MMMM");
        DateFormat dateFormatYear = new SimpleDateFormat("yyyy");
        Date localdate = date;

        String str = dateFormatDay.format(localdate) + " de " + dateFormatMonth.format(localdate) + " de " + dateFormatYear.format(localdate);
        return str;
    }

    /**
     *
     * @param o
     * @return
     */
    public static String getImgPath(Object o) {
        String var;

        if (o != null) {
            var = o.toString();
        } else {
            var = "SINFOTO.gif";
        }

        return var;
    }

    /**
     *
     * @param s
     * @return
     */
    public static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    /**
     *
     * @param s
     * @param radix
     * @return
     */
    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) {
                    return false;
                } else {
                    continue;
                }
            }
            if (Character.digit(s.charAt(i), radix) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param fileName
     * @return
     */
    public static String mimeTypeMap(final String fileName) {
        final MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
        return fileTypeMap.getContentType(fileName);
    }

    /**
     *
     * @param fileName
     * @return
     */
    public static String getExtension(final String fileName) {
        return FilenameUtils.getExtension(fileName);
    }

}
