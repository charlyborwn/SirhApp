package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Datos28a;
import com.hrm.sirhapp.service.exception.Datos28aAlreadyExists;
import com.hrm.sirhapp.service.exception.Datos28aNotFound;
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
public interface Datos28aManagerLocal {

    /**
     *
     * @param rfdatA
     * @return
     */
    public Boolean alreadyExistsInnactive(String rfdatA);

    /**
     *
     * @param rfdatA
     * @return
     * @throws Datos28aNotFound
     */
    public Datos28a getDatos28a(String rfdatA) throws Datos28aNotFound;

    /**
     *
     * @return
     */
    public List<Datos28a> retrieveDatos28a();

    /**
     *
     * @param rfdatA
     * @return
     * @throws Datos28aNotFound
     */
    public Datos28a getDatos28aById(String rfdatA) throws Datos28aNotFound;

    /**
     *
     * @param datos28a
     * @return
     * @throws Datos28aAlreadyExists
     */
    public Datos28a registerDatos28a(Datos28a datos28a) throws Datos28aAlreadyExists;

    /**
     *
     * @param datos28a
     * @throws Datos28aNotFound
     */
    public void deleteDatos28a(Datos28a datos28a) throws Datos28aNotFound;

    /**
     *
     * @param datos28a
     * @throws Datos28aNotFound
     */
    public void removeDatos28a(Datos28a datos28a) throws Datos28aNotFound;

    /**
     *
     * @param datos28a
     * @throws Datos28aNotFound
     */
    public void updateDatos28a(Datos28a datos28a) throws Datos28aNotFound;
}
