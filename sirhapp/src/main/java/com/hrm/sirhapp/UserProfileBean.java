package com.hrm.sirhapp;

import com.hrm.sirhapp.backing.BaseBacking;
import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Usuar24;
import com.hrm.sirhapp.service.Usuar24ManagerLocal;
import com.hrm.sirhapp.service.exception.Usuar24NotFound;
import com.hrm.sirhapp.util.FacesUtil;
import com.hrm.sirhapp.util.LanguagesUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@SessionScoped
public class UserProfileBean extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private Usuar24ManagerLocal usuar24Manager;

    private Usuar24 selectedUsuar24;

    private String user;

    @PostConstruct
    private void init() {
        user = getUser();
    }

    /**
     *
     * @return
     * @throws Usuar24NotFound
     */
    public Usuar24 getSelectedUsuar24Request() throws Usuar24NotFound {
        String sysUser = FacesUtil.getUserName();
        if (sysUser == null || sysUser.equals("SYSTEM")) {
            selectedUsuar24 = usuar24Manager.getUsuar24("SYSTEM");
        }
        return selectedUsuar24;
    }

    /**
     *
     * @param selectedUsuar24
     */
    public void setSelectedUsuar24Request(Usuar24 selectedUsuar24) {
        this.selectedUsuar24 = selectedUsuar24;
    }

    /**
     *
     * @return
     */
    public String getUser() {
        String sysUser = FacesUtil.getUserName();
        if (sysUser == null || sysUser.equals("SYSTEM")) {
            user = "SYSTEM";
        } else {
            user = sysUser;
        }
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     *
     * @param cvusu
     * @return
     */
    public Usuar24 getUsuar24Request(String cvusu) {

        try {
            if (cvusu != null && cvusu.length() > 0) {
                this.selectedUsuar24 = usuar24Manager.getUsuar24(cvusu);
                if (selectedUsuar24.getStusu().equals(Constants.RECORD_DELETED)) {

                    String outcome = "/logout";

                    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                    context.redirect(context.getRequestContextPath() + outcome);
                }
            } else {
                this.selectedUsuar24 = new Usuar24();
            }

        } catch (Usuar24NotFound ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage(new LanguagesUtil().getResources("reports.error")));
        }

        return selectedUsuar24;
    }

}
