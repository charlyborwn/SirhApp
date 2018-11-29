package com.hrm.sirhapp;

import com.hrm.sirhapp.util.FacesUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@RequestScoped
public class RequestBean implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String param1;
    private String param2;
    private String param3;

    private String prop1;
    private String attr1;

    /**
     *
     */
    @PostConstruct
    public void init() {
        //user = userService.find(id);
        System.out.println("parameterName1: " + param1);
    }

    /**
     *
     */
    public void action() {
        param1 = FacesUtil.getRequestParameter("param1");
    }

    /**
     *
     * @param event
     */
    public void action(ActionEvent event) {
        attr1 = FacesUtil.getActionAttribute(event, "attr1");
    }

    /**
     *
     * @return
     */
    public String getParam1() {
        return param1;
    }

    /**
     *
     * @param param1
     */
    public void setParam1(String param1) {
        this.param1 = param1;
    }

    /**
     *
     * @return
     */
    public String getParam2() {
        return param2;
    }

    /**
     *
     * @param param2
     */
    public void setParam2(String param2) {
        this.param2 = param2;
    }

    /**
     *
     * @return
     */
    public String getParam3() {
        return param3;
    }

    /**
     *
     * @param param3
     */
    public void setParam3(String param3) {
        this.param3 = param3;
    }

    /**
     *
     * @return
     */
    public String getProp1() {
        return prop1;
    }

    /**
     *
     * @param prop1
     */
    public void setProp1(String prop1) {
        this.prop1 = prop1;
    }

    /**
     *
     * @return
     */
    public String getAttr1() {
        return attr1;
    }

    /**
     *
     * @param attr1
     */
    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }


}
