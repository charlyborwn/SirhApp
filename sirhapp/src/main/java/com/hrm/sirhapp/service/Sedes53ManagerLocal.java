package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Sedes53;
import com.hrm.sirhapp.service.exception.Sedes53AlreadyExists;
import com.hrm.sirhapp.service.exception.Sedes53NotFound;
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
public interface Sedes53ManagerLocal {

    /**
     *
     * @param idsed
     * @return
     * @throws Sedes53NotFound
     */
    public Sedes53 getSedes53(Integer idsed) throws Sedes53NotFound;
    
    /**
     *
     * @param cvsed
     * @return
     * @throws Sedes53NotFound
     */
    public Sedes53 getSedes53(String cvsed) throws Sedes53NotFound;

    /**
     *
     * @return
     */
    public List<Sedes53> getListSedes53();

    /**
     *
     * @return
     */
    public List<Sedes53> retrieveSedes53();

    /**
     *
     * @param sedes53
     * @return
     * @throws Sedes53AlreadyExists
     */
    public Sedes53 registerSedes53(Sedes53 sedes53) throws Sedes53AlreadyExists;

    /**
     *
     * @param sedes53
     * @throws Sedes53NotFound
     */
    public void removeSedes53(Sedes53 sedes53) throws Sedes53NotFound;

    /**
     *
     * @param sedes53
     * @throws Sedes53NotFound
     */
    public void deleteSedes53(Sedes53 sedes53) throws Sedes53NotFound;

    /**
     *
     * @param sedes53
     * @throws Sedes53NotFound
     */
    public void updateSedes53(Sedes53 sedes53) throws Sedes53NotFound;
}
