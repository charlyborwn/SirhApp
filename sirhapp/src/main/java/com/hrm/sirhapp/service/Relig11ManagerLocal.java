package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Relig11;
import com.hrm.sirhapp.service.exception.Relig11AlreadyExists;
import com.hrm.sirhapp.service.exception.Relig11NotFound;
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
public interface Relig11ManagerLocal {

    /**
     *
     * @param idrel
     * @return
     * @throws Relig11NotFound
     */
    public Relig11 getRelig11(Integer idrel) throws Relig11NotFound;

    /**
     *
     * @return
     */
    public List<Relig11> getListRelig11();

    /**
     *
     * @return
     */
    public List<Relig11> retrieveRelig11();

    /**
     *
     * @param relig11
     * @return
     * @throws Relig11AlreadyExists
     */
    public Relig11 registerRelig11(Relig11 relig11) throws Relig11AlreadyExists;

    /**
     *
     * @param relig11
     * @throws Relig11NotFound
     */
    public void removeRelig11(Relig11 relig11) throws Relig11NotFound;

    /**
     *
     * @param relig11
     * @throws Relig11NotFound
     */
    public void deleteRelig11(Relig11 relig11) throws Relig11NotFound;

    /**
     *
     * @param relig11
     * @throws Relig11NotFound
     */
    public void updateRelig11(Relig11 relig11) throws Relig11NotFound;
}
