package com.hrm.sirhapp;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Generates a Password from a combination of CONSTANTS String Values
 * 
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@RequestScoped
public class ClavesDinamicasBean {

    private String pinNumber;
    private String password;

    /**
     * Has the numbers allowed
     */
    public static String NUMEROS = "0123456789";

    /**
     * Has the uppercase characters allowed
     */
    public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Has the lowercase characters allowed
     */
    public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Has the special characters allowed
     */
    public static String ESPECIALES = "@/%$*.";

    //

    /**
     *
     * @return a Dynamic key as a pin with 4 digits
     */
    public String getPinNumber() {
        pinNumber = getPassword(NUMEROS, 4);
        return pinNumber;
    }

    /**
     *
     * @return a Dynamic password with 4 digits
     */
    public String getPassword() {
        password = getPassword(8);
        return password;
    }

    /**
     *
     * @param pinNumber Set the Numeric Value provided as String
     */
    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    /**
     *
     * @param password Set the password Value provided as String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param length Is the lenght of a password
     * @return a Dynamic password with the lenght provided as int
     */
    public String getPassword(int length) {
        return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS + ESPECIALES, length);
    }

    /**
     *
     * @param key Is the password
     * @param length Is the lenght of a password
     * @return a Dynamic password randomized from the key provided
     */
    public String getPassword(String key, int length) {
        String pswd = "";

        for (int i = 0; i < length; i++) {
            pswd += (key.charAt((int) (Math.random() * key.length())));
        }

        return pswd;
    }

}
