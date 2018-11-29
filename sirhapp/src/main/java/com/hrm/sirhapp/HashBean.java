package com.hrm.sirhapp;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@RequestScoped
public class HashBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String md5;
    private String sha1;

    /**
     *
     */
    @PostConstruct
    public void init() {
        try {
            String password = "SirhApp";

            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
            long secureInitializer = sr.nextLong();
            Random random = new Random(secureInitializer + Runtime.getRuntime().freeMemory());
            long srstr = random.nextInt();

            this.md5 = getSaltPassword(password, getSalted());
            this.sha1 = String.valueOf(srstr);
        } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            Logger.getLogger(HashBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Add salt
    private static byte[] getSalted() throws NoSuchAlgorithmException, NoSuchProviderException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private static String getSaltPassword(String password, byte[] salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(salt);
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    /* Retorna un hash a partir de un tipo y un texto */

    /**
     *
     * @param txt
     * @param hashType
     * @return
     */

    public static String getHash(String txt, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @return
     */
    public String getMd5() {
        return md5;
    }

    /**
     *
     * @param md5
     */
    public void setMd5(String md5) {
        this.md5 = HashBean.getHash(sha1, "MD5");
    }

    /**
     *
     * @return
     */
    public String getSha1() {
        return sha1;
    }

    /**
     *
     * @param sha1
     */
    public void setSha1(String sha1) {
        this.sha1 = HashBean.getHash(sha1, "SHA1");
    }

}
