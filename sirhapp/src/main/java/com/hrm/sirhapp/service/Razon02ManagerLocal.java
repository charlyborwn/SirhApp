package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Razon02;
import com.hrm.sirhapp.service.exception.Razon02AlreadyExists;
import com.hrm.sirhapp.service.exception.Razon02NotFound;
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
public interface Razon02ManagerLocal {

    /**
     *
     * @param idraz
     * @return
     * @throws Razon02NotFound
     */
    public Razon02 getRazon02(Integer idraz) throws Razon02NotFound;

    /**
     *
     * @return
     */
    public List<Razon02> getListRazon02();

    /**
     *
     * @return
     */
    public List<Razon02> retrieveRazon02();

    /**
     *
     * @param razon02
     * @return
     * @throws Razon02AlreadyExists
     */
    public Razon02 registerRazon02(Razon02 razon02) throws Razon02AlreadyExists;

    /**
     *
     * @param razon02
     * @throws Razon02NotFound
     */
    public void removeRazon02(Razon02 razon02) throws Razon02NotFound;

    /**
     *
     * @param razon02
     * @throws Razon02NotFound
     */
    public void deleteRazon02(Razon02 razon02) throws Razon02NotFound;

    /**
     *
     * @param razon02
     * @throws Razon02NotFound
     */
    public void updateRazon02(Razon02 razon02) throws Razon02NotFound;
}
