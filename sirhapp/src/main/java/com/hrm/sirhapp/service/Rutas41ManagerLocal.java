package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Rutas41;
import com.hrm.sirhapp.service.exception.Rutas41AlreadyExists;
import com.hrm.sirhapp.service.exception.Rutas41NotFound;
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
public interface Rutas41ManagerLocal {

    /**
     *
     * @param idrut
     * @return
     * @throws Rutas41NotFound
     */
    public Rutas41 getRutas41(Integer idrut) throws Rutas41NotFound;

    /**
     *
     * @return
     */
    public List<Rutas41> getListRutas41();

    /**
     *
     * @return
     */
    public List<Rutas41> retrieveRutas41();

    /**
     *
     * @param rutas41
     * @return
     * @throws Rutas41AlreadyExists
     */
    public Rutas41 registerRutas41(Rutas41 rutas41) throws Rutas41AlreadyExists;

    /**
     *
     * @param rutas41
     * @throws Rutas41NotFound
     */
    public void removeRutas41(Rutas41 rutas41) throws Rutas41NotFound;

    /**
     *
     * @param rutas41
     * @throws Rutas41NotFound
     */
    public void deleteRutas41(Rutas41 rutas41) throws Rutas41NotFound;

    /**
     *
     * @param rutas41
     * @throws Rutas41NotFound
     */
    public void updateRutas41(Rutas41 rutas41) throws Rutas41NotFound;
}
