package com.hrm.sirhapp;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@SessionScoped
public class MenuView implements Serializable {

    private static final long serialVersionUID = 1L;

    private MenuModel model;

    /**
     *
     */
    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();

        //First submenu    <pu:menu model="#{menuView.model}" />
        DefaultSubMenu firstSubmenu = new DefaultSubMenu("Dynamic Submenu");

        DefaultMenuItem item = new DefaultMenuItem("External");
        item.setUrl("http://www.primefaces.org");
        item.setIcon("help_outline");
        firstSubmenu.addElement(item);

        model.addElement(firstSubmenu);

        //Second submenu
        DefaultSubMenu secondSubmenu = new DefaultSubMenu("Dynamic Actions");

        item = new DefaultMenuItem("Save");
        item.setIcon("help_outline");
        item.setCommand("#{menuView.save}");
        item.setUpdate("growl");
        secondSubmenu.addElement(item);

        item = new DefaultMenuItem("Delete");
        item.setIcon("help_outline");
        item.setCommand("#{menuView.delete}");
        item.setAjax(false);
        secondSubmenu.addElement(item);

        item = new DefaultMenuItem("Redirect");
        item.setIcon("help_outline");
        item.setCommand("#{menuView.redirect}");
        secondSubmenu.addElement(item);

        model.addElement(secondSubmenu);

    }

    /**
     *
     * @return
     */
    public MenuModel getModel() {
        return model;
    }   
     
    /**
     *
     */
    public void save() {
        addMessage("Success", "Data saved");
    }
     
    /**
     *
     */
    public void update() {
        addMessage("Success", "Data updated");
    }
     
    /**
     *
     */
    public void delete() {
        addMessage("Success", "Data deleted");
    }
     
    /**
     *
     * @param summary
     * @param detail
     */
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
