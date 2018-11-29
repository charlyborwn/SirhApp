package com.hrm.sirhapp.backing;

import java.io.Serializable;
import java.util.Map;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
public class BaseBacking  implements Serializable  {
    
    private static final long serialVersionUID = 1L;
    
    /**
     *
     * @return
     */
    protected FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }
    
    /**
     *
     * @return
     */
    protected Map getRequestMap() {
        return getContext().getExternalContext().getRequestMap();
    }
    
    /**
     *
     * @return
     */
    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getContext().getExternalContext().getRequest();
    }    
    
    /**
     *
     * @return
     */
    protected HttpSession getSession() {
        return (HttpSession) getContext().getExternalContext().getSession(false);
    }
  
    /**
     *
     * @param elExpression
     * @param beanClazz
     * @return
     */
    protected Object evaluateEL(String elExpression, Class beanClazz) {
        return getContext().getApplication().evaluateExpressionGet(getContext(), elExpression, beanClazz);
    }
    
    /**
     *
     * @param expression
     * @param value
     */
    protected void updateValueExpression(String expression, Object value) {
        
        ELContext elContext = getContext().getELContext();
        ValueExpression targetExpression = getContext().getApplication().getExpressionFactory().
                                           createValueExpression(elContext, expression, Object.class);
        
        targetExpression.setValue(elContext, value);
    }
    
    /**
     *
     */
    public static final String SYSTEM_ERROR = "System error ...";
}
