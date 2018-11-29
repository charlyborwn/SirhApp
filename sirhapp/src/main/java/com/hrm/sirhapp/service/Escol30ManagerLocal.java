package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Escol30;
import com.hrm.sirhapp.service.exception.Escol30AlreadyExists;
import com.hrm.sirhapp.service.exception.Escol30NotFound;
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
public interface Escol30ManagerLocal {

    /**
     *
     * @param idescA
     * @return
     * @throws Escol30NotFound
     */
    public Escol30 getEscol30(Integer idescA) throws Escol30NotFound;

    /**
     *
     * @param rfescA
     * @return
     */
    public List<Escol30> getListEscol30(Integer rfescA);

    /**
     *
     * @return
     */
    public List<Escol30> retrieveEscol30();

    /**
     *
     * @param escol30
     * @return
     * @throws Escol30AlreadyExists
     */
    public Escol30 registerEscol30(Escol30 escol30) throws Escol30AlreadyExists;

    /**
     *
     * @param escol30
     * @throws Escol30NotFound
     */
    public void removeEscol30(Escol30 escol30) throws Escol30NotFound;

    /**
     *
     * @param escol30
     * @throws Escol30NotFound
     */
    public void deleteEscol30(Escol30 escol30) throws Escol30NotFound;

    /**
     *
     * @param escol30
     * @throws Escol30NotFound
     */
    public void updateEscol30(Escol30 escol30) throws Escol30NotFound;
}
