package com.hrm.sirhapp.model;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com Copyright 2018 Carlos Ernesto
 * Bernal Hernández. SirhApp
 *
 */
public class EntityToString {

    /**
     * Returns a string of the entity. getString() get the class all attributes and attribute values
     * @param o The operation object
     * @param c Operations, is used to obtain the methods in a class
     * @return string string
     */
    public static String getString(Object o, Class<?> c) {
        return ToStringBuilder.reflectionToString(o);
    }
}
