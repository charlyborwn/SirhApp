package com.hrm.sirhapp.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
public class MultiPageMessagesSupport implements PhaseListener {

    private static final long serialVersionUID = 1250469273857785274L;
    private static final String SESSIONTOKEN = "MULTI_PAGE_MESSAGES_SUPPORT";
    private static final String PAGE_REDIRECT = "login.xhtml?faces-redirect=true";

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    /*
     * Check to see if we are "naturally" in the RENDER_RESPONSE phase. If we
     * have arrived here and the response is already complete, then the page is
     * not going to show up: don't display messages yet.
     */
    // TODO: Blog this (MultiPageMessagesSupport)
    @Override
    public void beforePhase(final PhaseEvent phaseEvent) {

        for (FacesMessage message : phaseEvent.getFacesContext().getMessageList()) {
            if (StringUtils.isBlank(message.getDetail())
                    || (StringUtils.isNotBlank(message.getSummary()) && message.getSummary().equals(message.getDetail()))) {
                
            }
        }

        if (phaseEvent.getPhaseId() == PhaseId.RESTORE_VIEW) {
            //System.out.println("Processing new Request!");
        }
        FacesContext facesContext = phaseEvent.getFacesContext();
        this.saveMessages(facesContext);

        if (PhaseId.RENDER_RESPONSE.equals(phaseEvent.getPhaseId())) {
            if (!facesContext.getResponseComplete()) {
                this.restoreMessages(facesContext);
            }
        }
    }


    /*
     * Save messages into the session after every phase.
     */
    @Override
    public void afterPhase(final PhaseEvent phaseEvent) {
        String viewId = phaseEvent.getFacesContext().getViewRoot().getViewId();
        if (PhaseId.RENDER_RESPONSE.equals(phaseEvent.getPhaseId())) {

        }

        if (!PhaseId.RENDER_RESPONSE.equals(phaseEvent.getPhaseId())) {
            FacesContext facesContext = phaseEvent.getFacesContext();
            this.saveMessages(facesContext);
        }

        if (viewId.equals("/")) {
            boolean flag = FacesContext.getCurrentInstance().getExternalContext().isResponseCommitted();
            if (!flag) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect(PAGE_REDIRECT);
                } catch (IOException ex) {
                    Logger.getLogger(MultiPageMessagesSupport.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    @SuppressWarnings("unchecked")
    private int saveMessages(final FacesContext facesContext
    ) {
        List<FacesMessage> messages = new ArrayList<>();
        for (Iterator<FacesMessage> iter = facesContext.getMessages(null); iter.hasNext();) {
            messages.add(iter.next());
            iter.remove();
        }

        if (messages.size() == 0) {
            return 0;
        }

        Map<String, Object> sessionMap = facesContext.getExternalContext().getSessionMap();
        List<FacesMessage> existingMessages = (List<FacesMessage>) sessionMap.get(SESSIONTOKEN);
        if (existingMessages != null) {
            existingMessages.addAll(messages);
        } else {
            sessionMap.put(SESSIONTOKEN, messages);
        }
        return messages.size();
    }

    @SuppressWarnings("unchecked")
    private int restoreMessages(final FacesContext facesContext
    ) {
        Map<String, Object> sessionMap = facesContext.getExternalContext().getSessionMap();
        List<FacesMessage> messages = (List<FacesMessage>) sessionMap.remove(SESSIONTOKEN);

        if (messages == null) {
            return 0;
        }

        int restoredCount = messages.size();
        for (Object element : messages) {
            facesContext.addMessage(null, (FacesMessage) element);
        }
        return restoredCount;
    }
}
