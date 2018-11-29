package com.hrm.filesystem.security;

import java.io.Serializable;
import java.security.Principal;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
public class JAASRolePrincipal implements Principal, Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    /**
     * @param name
     */
    public JAASRolePrincipal(String name) {
        if (name == null) {
            throw new NullPointerException("NULL role name");
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "JAASRolePrincipal [name=" + name + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        JAASRolePrincipal other = (JAASRolePrincipal) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }

        return true;
    }
}
