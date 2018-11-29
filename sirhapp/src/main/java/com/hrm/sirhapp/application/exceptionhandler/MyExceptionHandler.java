package com.hrm.sirhapp.application.exceptionhandler;

import java.io.IOException;
import java.util.Iterator;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
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
public class MyExceptionHandler extends ExceptionHandlerWrapper {

    private ExceptionHandler wrapped;

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    /**
     *
     * @param wrapped
     */
    public MyExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void handle() {
        for (Iterator<ExceptionQueuedEvent> it = getUnhandledExceptionQueuedEvents().iterator(); it.hasNext();) {
            ExceptionQueuedEventContext eventContext = it.next().getContext();
            // 1. ハンドリング対象のアプリケーション例外を取得
            Throwable throwable = eventContext.getException();
            System.out.println("throwable = " + throwable);
            Throwable rootCause = getRootCause(throwable);
            System.out.println("rootCause = " + rootCause);
            Throwable th = rootCause.getCause();
            System.out.println("th = " + th);
            // メッセージを追加する
            String exceptionClassName = th.getClass().getName();
            ResourceBundle resourceBundle = ResourceBundle.getBundle("ExceptionMessages");
            String summaryMessage;
            String detailMessage;
            if (resourceBundle.containsKey(exceptionClassName)) {
                summaryMessage = resourceBundle.getString(exceptionClassName);
                detailMessage = resourceBundle.getString(exceptionClassName + "_detail");
            } else {
                summaryMessage = resourceBundle.getString(Exception.class.getName());
                detailMessage = resourceBundle.getString(Exception.class.getName() + "_detail");
            }
            FacesContext facesContext = eventContext.getContext();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summaryMessage, detailMessage));
            // 2. リダイレクトしてもFacesMessageが消えないように設定
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.getFlash().setKeepMessages(true);
            try {
                // エラー画面に画面遷移させる
                String contextPath = externalContext.getRequestContextPath();
                externalContext.redirect(contextPath + "/exception.xhtml");
            } catch (IOException e) {
                System.out.println("error.xhtmlがありません");
            } finally {
                // 3. 未ハンドリングキューから削除する
                it.remove();
            }
        }
        wrapped.handle();
    }

}
