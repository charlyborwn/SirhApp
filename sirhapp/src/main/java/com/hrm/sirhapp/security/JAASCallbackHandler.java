package com.hrm.sirhapp.security;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
 public class JAASCallbackHandler implements CallbackHandler {

 private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(JAASCallbackHandler.class);
 
 private String username = null;
 private String password = null;
 
 /**
  * @param username username
  * @param password password
  */
 public JAASCallbackHandler(String username, String password) {
     this.username = username;
     this.password = password;
 }


 @Override
 public void handle(Callback[] callbacks) throws IOException,
   UnsupportedCallbackException {
  
     for (int i = 0; i < callbacks.length; i++) {
        if (callbacks[i] instanceof NameCallback) {
           NameCallback nameCallback = (NameCallback) callbacks[i];
           nameCallback.setName(username);
        } else if (callbacks[i] instanceof PasswordCallback) {
           PasswordCallback passwordCallback = (PasswordCallback) callbacks[i];
           passwordCallback.setPassword(password.toCharArray());
        } else {
           throw new UnsupportedCallbackException(callbacks[i], "The submitted Callback is unsupported");
        }
     }
 }
}