package com.hrm.sirhapp;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
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
public class LocaleBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    protected List<Locale> supportedLocales = new ArrayList<Locale>();

    private Locale locale;
    private String localeCode;
    private String localeDate;

    /**
     *
     */
    @PostConstruct
    public void init() {
        locale = new Locale("es", "MX");
    }

    /**
     *
     * @return
     */
    public String getLocaleDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        this.localeDate = dtf.format(localDate);
        return localeDate;
    }

    /**
     *
     * @param localeDate
     */
    public void setLocaleDate(String localeDate) {
        this.localeDate = localeDate;
    }

    /**
     *
     * @return
     */
    public Locale getLocale() {
        if (locale == null && FacesContext.getCurrentInstance() != null) {
            locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        }
        return locale;
    }

    /**
     *
     * @param locale
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);

    }

    private static final Map<String, Object> countries;

    static {
        countries = new LinkedHashMap<>();
        countries.put("Español Mexico", new Locale("es", "MX"));
        countries.put("English", new Locale("en", "US"));
    }

    /**
     *
     * @return
     */
    public Map<String, Object> getCountriesInMap() {
        return countries;
    }

    /**
     *
     * @return
     */
    public String getLocaleCode() {
        return localeCode;
    }

    /**
     *
     * @param localeCode
     */
    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    //value change event listener

    /**
     *
     * @param e
     */
    public void countryLocaleCodeChanged(ValueChangeEvent e) {
        String newLocaleValue = e.getNewValue().toString();

        //loop country map to compare the locale code
        for (Map.Entry<String, Object> entry : countries.entrySet()) {
            if (entry.getValue().toString().equals(newLocaleValue)) {
                locale = (Locale) entry.getValue();
            }
        }
    }

    /**
     *
     * @return
     */
    public List<Locale> getSupportedLocales() {
        if (supportedLocales.isEmpty() && FacesContext.getCurrentInstance() != null) {
            Iterator<Locale> facesLocales = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
            while (facesLocales.hasNext()) {
                supportedLocales.add(facesLocales.next());
            }
        }
        return supportedLocales;
    }

    /**
     *
     * @param localeStrings
     */
    public void setSupportedLocaleStrings(Collection<String> localeStrings) {
        supportedLocales.clear();
        for (String checkLocale : localeStrings) {
            for (Locale locale : Locale.getAvailableLocales()) {
                if (locale.toString().equals(checkLocale)) {
                    supportedLocales.add(locale);
                    break;
                }
            }
        }

    }

}
