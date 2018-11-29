package com.hrm.sirhapp;

import java.util.Set;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Manaegd the RequestScope
 * 
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
public class ManagedBeans {

    private static final BeanManager beanManager;

    static {
        try {
            InitialContext ic = new InitialContext();
            beanManager = (BeanManager) ic.lookup("java:comp/BeanManager");
        } catch (NamingException ex) {
            throw new IllegalStateException(ex);
        }
    }

    private ManagedBeans() {
    }

    /**
     *
     * @param <T>
     * @param clazz
     * @param name
     * @return
     */
    public static <T> T getBean(Class<T> clazz, String name) {
        Set<Bean<?>> beans = beanManager.getBeans(name);
        Bean<? extends Object> resolve = beanManager.resolve(beans);
        CreationalContext<? extends Object> createCreationalContext = beanManager.createCreationalContext(resolve);
        return (T) beanManager.getReference(resolve, clazz, createCreationalContext);
    }
}
