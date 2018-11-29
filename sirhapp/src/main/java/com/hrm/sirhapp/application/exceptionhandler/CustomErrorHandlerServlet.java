package com.hrm.sirhapp.application.exceptionhandler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@WebServlet(name = "errorHandlerServlet",
        urlPatterns = {"/errorHandler"},
        loadOnStartup = 1)
public class CustomErrorHandlerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();

        Exception exception = (Exception) req.getAttribute(
                "javax.servlet.error.exception");

        writer.printf("exception: %s%n", exception);

        Class exceptionClass = (Class) req.getAttribute(
                "javax.servlet.error.exception_type");
        writer.printf("exception_type: %s%n", exceptionClass);

        Integer code = (Integer) req.getAttribute(
                "javax.servlet.error.status_code");
        writer.printf("status_code: %s%n", code);

        String errorMessage = (String) req.getAttribute(
                "javax.servlet.error.message");
        writer.printf("message: %s%n", errorMessage);

        String requestUri = (String) req.getAttribute(
                "javax.servlet.error.request_uri");

        resp.getWriter().printf("request_uri: %s%n",
                requestUri);

        String servletName = (String) req.getAttribute(
                "javax.servlet.error.servlet_name");
        writer.printf("servlet_name: %s%n", servletName);

    }
}
