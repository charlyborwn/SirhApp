package com.hrm.sirhapp.util;

import com.google.common.io.BaseEncoding;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.Serializable;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;
import javax.crypto.BadPaddingException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@ApplicationScoped
public class CapthaUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String CIPHER_INSTANCE_NAME = "AES/ECB/PKCS5Padding";

    private String siteKey = "6LffqUcUAAAAAPyfuCngMvyQ5m9OXmGfC6miDHJZ";
    private String siteSecret = "6LffqUcUAAAAAFx3Z0WH_zfqror-Tsp9AZ8sgcZX";

    /**
     *
     * @return
     */
    public String getSiteKey() {
        return siteKey;
    }

    /**
     *
     * @param siteKey
     */
    public void setSiteKey(String siteKey) {
        this.siteKey = siteKey;
    }

    /**
     *
     * @return
     */
    public String getSiteSecret() {
        return siteSecret;
    }

    /**
     *
     * @param siteSecret
     */
    public void setSiteSecret(String siteSecret) {
        this.siteSecret = siteSecret;
    }

    /**
     *
     * @param siteSecret
     * @return
     */
    public static final String createSToken(String siteSecret) {
        String sessionId = UUID.randomUUID().toString();
        String jsonToken = createJsonToken(sessionId);
        return encryptAes(jsonToken, siteSecret);
    }

    private static final String createJsonToken(String sessionId) {
        JsonObject obj = new JsonObject();
        obj.addProperty("session_id", sessionId);
        obj.addProperty("ts_ms", System.currentTimeMillis());
        return new Gson().toJson(obj);
    }

    private static String encryptAes(String input, String siteSecret) {
        try {
            SecretKeySpec secretKey = getKey(siteSecret);
            Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return BaseEncoding.base64Url().omitPadding().encode(cipher.doFinal(input.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String decryptAes(String input, String key) throws Exception {
        SecretKeySpec secretKey = getKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE_NAME);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(
                BaseEncoding.base64Url().omitPadding().decode(input)), "UTF-8");
    }

    private static SecretKeySpec getKey(String siteSecret) {
        try {
            byte[] key = siteSecret.getBytes("UTF-8");
            key = Arrays.copyOf(MessageDigest.getInstance("SHA").digest(key), 16);
            return new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void logo() {
        System.out.println("\n                                                                 ");
        System.out.println("            _____          _____ _______ _____ _    _          ");
        System.out.println("           / ____|   /\\   |  __ \\__   __/ ____| |  | |   /\\     ");
        System.out.println("  _ __ ___| |       /  \\  | |__) | | | | |    | |__| |  /  \\     ");
        System.out.println(" | '__/ _ \\ |      / /\\ \\ |  ___/  | | | |    |  __  | / /\\ \\ ");
        System.out.println(" | | |  __/ |____ / ____ \\| |      | | | |____| |  | |/ ____ \\   ");
        System.out.println(" |_|  \\___|\\_____/_/    \\_\\_|      |_|  \\_____|_|  |_/_/    \\_\\ ");
    }

    private static void demo() {
        String sessionId = UUID.randomUUID().toString();
        String siteSecret = "12345678";
        String jsonToken = createJsonToken(sessionId);

        System.out.println("\n Demo for stoken generation.\n                                   ");

        System.out.println(" Session Id: " + sessionId);
        System.out.println(" json token: " + jsonToken);
        System.out.println(" siteSecret: " + siteSecret);
        System.out.println(" Encrypted stoken: " + encryptAes(jsonToken, siteSecret));
        System.out.println("\n");
    }

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        logo();
        if (args.length <= 1) {
            demo();
        } else {
            System.out.println("\n Decode .\n                                   ");
            System.out.println(" siteSecret: " + args[1]);
            System.out.println(" Encrypted stoken: " + args[0]);
            System.out.println(" Raw input: " + decryptAes(args[0], args[1]));
        }
    }

}
