package com.hrm.sirhapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 * Manage de CalendarView Values
 * 
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
public class CalendarView {

    private Date date1;
    private Date date2;
    private Date date3;
    private Date date4;
    private Date date5;
    private Date date6;
    private Date date7;
    private Date date8;
    private Date date9;
    private Date date10;
    private Date date11;

    /**
     *
     * @param event
     */
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fecha Seleccionada", format.format(event.getObject())));
    }

    /**
     *
     */
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();

        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }

    /**
     *
     * @return
     */
    public Date getDate1() {
        return date1;
    }

    /**
     *
     * @param date1
     */
    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    /**
     *
     * @return
     */
    public Date getDate2() {
        return date2;
    }

    /**
     *
     * @param date2
     */
    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    /**
     *
     * @return
     */
    public Date getDate3() {
        return date3;
    }

    /**
     *
     * @param date3
     */
    public void setDate3(Date date3) {
        this.date3 = date3;
    }

    /**
     *
     * @return
     */
    public Date getDate4() {
        return date4;
    }

    /**
     *
     * @param date4
     */
    public void setDate4(Date date4) {
        this.date4 = date4;
    }

    /**
     *
     * @return
     */
    public Date getDate5() {
        return date5;
    }

    /**
     *
     * @param date5
     */
    public void setDate5(Date date5) {
        this.date5 = date5;
    }

    /**
     *
     * @return
     */
    public Date getDate6() {
        return date6;
    }

    /**
     *
     * @param date6
     */
    public void setDate6(Date date6) {
        this.date6 = date6;
    }

    /**
     *
     * @return
     */
    public Date getDate7() {
        return date7;
    }

    /**
     *
     * @param date7
     */
    public void setDate7(Date date7) {
        this.date7 = date7;
    }

    /**
     *
     * @return
     */
    public Date getDate8() {
        return date8;
    }

    /**
     *
     * @param date8
     */
    public void setDate8(Date date8) {
        this.date8 = date8;
    }

    /**
     *
     * @return
     */
    public Date getDate9() {
        return date9;
    }

    /**
     *
     * @param date9
     */
    public void setDate9(Date date9) {
        this.date9 = date9;
    }

    /**
     *
     * @return
     */
    public Date getDate10() {
        return date10;
    }

    /**
     *
     * @param date10
     */
    public void setDate10(Date date10) {
        this.date10 = date10;
    }

    /**
     *
     * @return
     */
    public Date getDate11() {
        return date11;
    }

    /**
     *
     * @param date11
     */
    public void setDate11(Date date11) {
        this.date11 = date11;
    }
}
