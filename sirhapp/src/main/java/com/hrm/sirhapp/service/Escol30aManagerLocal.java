package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Escol30a;
import com.hrm.sirhapp.service.exception.Escol30aAlreadyExists;
import com.hrm.sirhapp.service.exception.Escol30aNotFound;
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
public interface Escol30aManagerLocal {

    /**
     *
     * @param idescA
     * @return
     * @throws Escol30aNotFound
     */
    public Escol30a getEscol30a(Integer idescA) throws Escol30aNotFound;

    /**
     *
     * @param rfescA
     * @return
     */
    public List<Escol30a> getListEscol30a(String rfescA);

    /**
     *
     * @return
     */
    public List<Escol30a> retrieveEscol30a();

    /**
     *
     * @param escol30a
     * @return
     * @throws Escol30aAlreadyExists
     */
    public Escol30a registerEscol30a(Escol30a escol30a) throws Escol30aAlreadyExists;

    /**
     *
     * @param escol30a
     * @throws Escol30aNotFound
     */
    public void deleteEscol30a(Escol30a escol30a) throws Escol30aNotFound;

    /**
     *
     * @param escol30a
     * @throws Escol30aNotFound
     */
    public void removeEscol30a(Escol30a escol30a) throws Escol30aNotFound;

    /**
     *
     * @param escol30a
     * @throws Escol30aNotFound
     */
    public void updateEscol30a(Escol30a escol30a) throws Escol30aNotFound;
}
