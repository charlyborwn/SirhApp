package com.hrm.sirhapp.application.exceptionhandler;

import com.hrm.sirhapp.application.LogoutServlet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
public class CustomExceptionHandler extends ExceptionHandlerWrapper {

    private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(CustomExceptionHandler.class);

    private ExceptionHandler wrapped;

    /**
     *
     * @param wrapped
     */
    public CustomExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void handle() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        Boolean viewExpiredException = false;

        //java.lang.NullPointerException
        //java.lang.IllegalStateException a;
        for (Iterator<ExceptionQueuedEvent> it
                = getUnhandledExceptionQueuedEvents().iterator(); it.hasNext() == true;) {

            ExceptionQueuedEventContext eventContext = it.next().getContext();

            for (Iterator<ExceptionQueuedEvent> iter = getUnhandledExceptionQueuedEvents().iterator(); iter.hasNext();) {
                Throwable exception = iter.next().getContext().getException(); // There it is!

                // Now do your thing with it. This example implementation merely prints the stack trace.
                exception.printStackTrace();

                // You could redirect to an error page (bad practice).
                // Or you could render a full error page (as OmniFaces does).
                // Or you could show a FATAL faces message.
                // Or you could trigger an oncomplete script.
                // etc..

                if (exception.getClass().equals(javax.faces.application.ViewExpiredException.class)) {
                    viewExpiredException = true;
                }

                if (!viewExpiredException) {
                    facesContext.addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Aplicación", "La operacion no se ha realizado, intente nuevamente"));
                }

                Logger.getLogger(LogoutServlet.class.getName()).log(Level.SEVERE, "Aplicación", exception.toString());

            }
            if (!viewExpiredException) {
                facesContext.getExternalContext().getFlash().setKeepMessages(true);
            }

            it.remove();

        }
        getWrapped().handle();
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }
}
