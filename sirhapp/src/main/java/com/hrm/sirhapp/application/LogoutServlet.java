package com.hrm.sirhapp.application;

import java.io.IOException;
import java.security.Principal;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@WebServlet(name = "logoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(LogoutServlet.class);

    private static final String LOGIN_PAGE_REDIRECT = "/login.xhtml?faces-redirect=true";

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String ipAddress = request.getRemoteAddr();
        Principal user = request.getUserPrincipal();

        String result = " IP[" + ipAddress + "] USR[" + user + "] " + "| ACT: Fin de sesion correcto";

        LOGGER.info(result);

        System.out.println(result);

        HttpSession session = request.getSession(true);
        Enumeration attributeNames = session.getAttributeNames(); //get all attributeNames associated with session

        while (attributeNames.hasMoreElements()) //destroy session
        {
            String name = (String) attributeNames.nextElement();
            String value = session.getAttribute(name).toString();
            session.removeAttribute(name);

            System.out.println(name + "=" + value + " -> cleared");
        }

        request.logout();

        session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        response.sendRedirect(request.getContextPath() + LOGIN_PAGE_REDIRECT);

    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Invalida la sesion existente del usuario.";
    }
}
