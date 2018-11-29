package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Ident49a;
import com.hrm.sirhapp.service.exception.Ident49aAlreadyExists;
import com.hrm.sirhapp.service.exception.Ident49aNotFound;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Local
public interface Ident49aManagerLocal {
    
    /**
     *
     * @param rfideA
     * @return
     */
    public Boolean alreadyExistsInnactive(String rfideA);

    /**
     *
     * @param rfideA
     * @return
     * @throws Ident49aNotFound
     */
    public Ident49a getIdent49a(String rfideA) throws Ident49aNotFound;
    
    /**
     *
     * @param rfideA
     * @return
     * @throws Ident49aNotFound
     */
    public Ident49a getIdent49aById(String rfideA) throws Ident49aNotFound;

    /**
     *
     * @return
     */
    public List<Ident49a> getListIdent49a();

    /**
     *
     * @return
     */
    public List<Ident49a> retrieveIdent49a();

    /**
     *
     * @param ident49a
     * @return
     * @throws Ident49aAlreadyExists
     */
    public Ident49a registerIdent49a(Ident49a ident49a) throws Ident49aAlreadyExists;

    /**
     *
     * @param ident49a
     * @throws Ident49aNotFound
     */
    public void deleteIdent49a(Ident49a ident49a) throws Ident49aNotFound;

    /**
     *
     * @param ident49a
     * @throws Ident49aNotFound
     */
    public void removeIdent49a(Ident49a ident49a) throws Ident49aNotFound;

    /**
     *
     * @param ident49a
     * @throws Ident49aNotFound
     */
    public void updateIdent49a(Ident49a ident49a) throws Ident49aNotFound;
}
