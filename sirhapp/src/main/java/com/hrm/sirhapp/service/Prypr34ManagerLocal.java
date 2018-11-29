package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Prypr34;
import com.hrm.sirhapp.service.exception.Prypr34AlreadyExists;
import com.hrm.sirhapp.service.exception.Prypr34NotFound;
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
public interface Prypr34ManagerLocal {

    /**
     *
     * @param idpry
     * @return
     * @throws Prypr34NotFound
     */
    public Prypr34 getPrypr34(Integer idpry) throws Prypr34NotFound;

    /**
     *
     * @param idpry
     * @return
     */
    public Boolean alreadyExistsInnactive(Integer idpry);

    /**
     *
     * @param nupry
     * @return
     */
    public List<Prypr34> getListPrypr34(String nupry);

    /**
     *
     * @param nupry
     * @param tppry
     * @return
     */
    public List<Prypr34> getListPrypr34(String nupry, String tppry);

    /**
     *
     * @return
     */
    public List<Prypr34> retrievePrypr34();

    /**
     *
     * @param nutra
     * @return
     */
    public List<Prypr34> getListPrypr34Traba36(Integer nutra);

    /**
     *
     * @param prypr34
     * @return
     * @throws Prypr34AlreadyExists
     */
    public Prypr34 registerPrypr34(Prypr34 prypr34) throws Prypr34AlreadyExists;

    /**
     *
     * @param prypr34
     * @throws Prypr34NotFound
     */
    public void removePrypr34(Prypr34 prypr34) throws Prypr34NotFound;

    /**
     *
     * @param prypr34
     * @return
     * @throws Prypr34NotFound
     */
    public Prypr34 deletePrypr34(Prypr34 prypr34) throws Prypr34NotFound;

    /**
     *
     * @param prypr34
     * @return
     * @throws Prypr34NotFound
     */
    public Prypr34 updatePrypr34(Prypr34 prypr34) throws Prypr34NotFound;
}
