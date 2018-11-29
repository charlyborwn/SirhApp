package com.hrm.sirhapp.security;

import com.hrm.sirhapp.util.FacesUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
public class JAASLoginModule implements LoginModule {

    private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(JAASLoginModule.class);

    // initial state
    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map sharedState;
    private Map options;

    // configurable option
    private boolean debug = false;

    // the authentication status
    private boolean succeeded = false;
    private boolean commitSucceeded = false;

    //user credentials
    private String username = null;
    private char[] password = null;

    //user principle
    private JAASUserPrincipal userPrincipal = null;
    private JAASPasswordPrincipal passwordPrincipal = null;

    /**
     *
     */
    public JAASLoginModule() {
        super();
    }

    @Override
    public void initialize(Subject aSubject, CallbackHandler aCallbackHandler, Map aSharedState, Map aOptions) {
        this.subject = aSubject;
        this.callbackHandler = aCallbackHandler;
        this.sharedState = aSharedState;
        this.options = aOptions;
    }

    @Override
    public boolean login() throws LoginException {

        if (callbackHandler == null) {
            throw new LoginException("Error: no CallbackHandler available "
                    + "to garner authentication information from the user");
        }
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("username");
        callbacks[1] = new PasswordCallback("password: ", false);

        try {

            callbackHandler.handle(callbacks);
            username = ((NameCallback) callbacks[0]).getName();
            password = ((PasswordCallback) callbacks[1]).getPassword();

            if (debug) {
                LOGGER.debug("Username :" + username);
                LOGGER.debug("Password : " + password);
            }

            if (username == null || password == null) {
                LOGGER.error("Callback handler does not return login data properly");
                throw new LoginException("Callback handler does not return login data properly");
            }

            if (!isUser()) { //validate user.
                succeeded = false;
                throw new LoginException("El usuario no existe");
            }

            if (!isPass()) { //validate user.
                succeeded = false;
                throw new LoginException("El password es incorrecto");
            }

            List<String> roles = getRoles();
            if (roles.isEmpty()) { //validate roles.
                succeeded = false;
                throw new LoginException("El usuario no tiene privilegios para ingresar");
            }

            if (isValidUser()) { //validate user.
                succeeded = true;
                return true;
            }

        } catch (IOException | UnsupportedCallbackException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean commit() throws LoginException {
        if (succeeded == false) {
            return false;
        } else {
            userPrincipal = new JAASUserPrincipal(username);
            if (!subject.getPrincipals().contains(userPrincipal)) {
                subject.getPrincipals().add(userPrincipal);
            }
            passwordPrincipal = new JAASPasswordPrincipal(new String(password));
            if (!subject.getPrincipals().contains(passwordPrincipal)) {
                subject.getPrincipals().add(passwordPrincipal);
            }

            //populate subject with roles.
            List<String> roles = getRoles();
            for (String role : roles) {
                JAASRolePrincipal rolePrincipal = new JAASRolePrincipal(role);
                if (!subject.getPrincipals().contains(rolePrincipal)) {
                    subject.getPrincipals().add(rolePrincipal);
                    LOGGER.info("IP[" + FacesUtil.getUserIpAddress() + "] USR[" + username + "] " + "| ACT: Inicio de sesion - Role principal agregado: " + rolePrincipal);
                }
            }

            commitSucceeded = true;

            LOGGER.info("IP[" + FacesUtil.getUserIpAddress() + "] USR[" + username + "] " + "| ACT: Inicio de sesion - Roles agregados");

            return true;
        }
    }

    @Override
    public boolean abort() throws LoginException {
        if (succeeded == false) {
            return false;
        } else if (succeeded == true && commitSucceeded == false) {
            succeeded = false;
            username = null;
            if (password != null) {
                password = null;
            }
            userPrincipal = null;
        } else {
            logout();
        }
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(userPrincipal);
        succeeded = false;
        succeeded = commitSucceeded;
        username = null;

        if (password != null) {
            for (int i = 0; i < password.length - 1; i++) {

                password[i] = ' ';

            }
        }

        userPrincipal = null;
        return true;
    }

    private boolean isValidUser() throws LoginException {

        String sql = (String) options.get("userQuery");
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, new String(password));

            rs = stmt.executeQuery();

            while (rs.next()) { //User exist with the given user name and password.
                return true;
            }
        } catch (SQLException | LoginException e) {
            LOGGER.error("Error when loading user from the database " + e);
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                LOGGER.error("Error when closing result set." + e);
            }
            try {
                stmt.close();
            } catch (SQLException e) {
                LOGGER.error("Error when closing statement." + e);
            }
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("Error when closing connection." + e);
            }
        }
        return false;
    }

    private boolean isUser() {

        String sql = (String) options.get("isUser");
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        try {
            con = getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, username);

            rs = stmt.executeQuery();

            while (rs.next()) { //User exist with the given user name and password.
                return true;
            }
        } catch (SQLException | LoginException e) {
            LOGGER.error("Error when loading user from the database " + e);
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                LOGGER.error("Error when closing result set." + e);
            }
            try {
                stmt.close();
            } catch (SQLException e) {
                LOGGER.error("Error when closing statement." + e);
            }
            try {
                con.close();
            } catch (SQLException e) {
                LOGGER.error("Error when closing connection." + e);
            }
        }
        return false;
    }

    private boolean isPass() {

        String sql = (String) options.get("userQuery");
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, new String(password));

            rs = stmt.executeQuery();

            while (rs.next()) { //User exist with the given user name and password.
                return true;
            }
        } catch (SQLException | LoginException e) {
            LOGGER.error("Error when loading user from the database " + e);
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                LOGGER.error("Error when closing result set." + e);
            }
            try {
                stmt.close();
            } catch (SQLException e) {
                LOGGER.error("Error when closing statement." + e);
            }
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("Error when closing connection." + e);
            }
        }
        return false;
    }

    /**
     * Returns list of roles assigned to authenticated user.
     *
     * @return
     */
    private List<String> getRoles() {

        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        List<String> roleList = new ArrayList<String>();

        try {
            conn = getConnection();
            String sql = (String) options.get("roleQuery");
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            rs = stmt.executeQuery();

            while (rs.next()) {
                roleList.add(rs.getString("NANIA"));
            }

        } catch (SQLException | LoginException e) {
            LOGGER.error("Error when loading user from the database " + e);
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                LOGGER.error("Error when closing result set." + e);
            }
            try {
                stmt.close();
            } catch (SQLException e) {
                LOGGER.error("Error when closing statement." + e);
            }
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("Error when closing connection." + e);
            }
        }
        return roleList;
    }

    /**
     * Returns JDBC connection
     *
     * @return
     * @throws LoginException
     */
    private Connection getConnection() throws LoginException {

        String dBUser = (String) options.get("dbUser");
        String dBPassword = (String) options.get("dbPassword");
        String dBUrl = (String) options.get("dbURL");
        String dBDriver = (String) options.get("dbDriver");

        Connection conn = null;

        try {

            Class.forName(dBDriver).newInstance();
            conn = DriverManager.getConnection(dBUrl, dBUser, dBPassword);

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            Logger.getLogger(FacesUtil.class.getName()).log(Level.SEVERE, null, ex);
            LOGGER.error("No fue creada la conexion a la Base de Datos: " + ex);
        }
        
        return conn;

    }
}
