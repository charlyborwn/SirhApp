package com.hrm.sirhapp.security.jaas;

import java.security.Principal;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
public class GroupPrincipal implements Principal {
	
	private String name;
	
    /**
     *
     * @param name
     */
    public GroupPrincipal(String name) {
		super();
		this.name = name;
	}

    /**
     *
     * @param name
     */
    public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
