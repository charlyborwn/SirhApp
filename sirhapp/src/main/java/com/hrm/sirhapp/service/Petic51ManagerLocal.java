package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Petic51;
import com.hrm.sirhapp.service.exception.Petic51AlreadyExists;
import com.hrm.sirhapp.service.exception.Petic51NotFound;
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
public interface Petic51ManagerLocal {

    /**
     *
     * @param idpet
     * @return
     * @throws Petic51NotFound
     */
    public Petic51 getPetic51(Integer idpet) throws Petic51NotFound;

    /**
     *
     * @return
     */
    public List<Petic51> getListPetic51();

    /**
     *
     * @return
     */
    public List<Petic51> retrievePetic51();

    /**
     *
     * @param petic51
     * @return
     * @throws Petic51AlreadyExists
     */
    public Petic51 sendPetic51(Petic51 petic51) throws Petic51AlreadyExists;

    /**
     *
     * @param petic51Number
     * @throws Petic51NotFound
     */
    public void approvePetic51(Integer petic51Number) throws Petic51NotFound;

    /**
     *
     * @param petic51Number
     * @throws Petic51NotFound
     */
    public void rejectPetic51(Integer petic51Number) throws Petic51NotFound;

    /**
     *
     * @param userName
     * @param status
     * @return
     */
    public List<Petic51> viewRequests(String userName, int status);

    /**
     *
     * @param petic51
     * @return
     * @throws Petic51AlreadyExists
     */
    public Petic51 registerPetic51(Petic51 petic51) throws Petic51AlreadyExists;

    /**
     *
     * @param petic51
     * @throws Petic51NotFound
     */
    public void removePetic51(Petic51 petic51) throws Petic51NotFound;
    
    /**
     *
     * @param petic51
     * @throws Petic51NotFound
     */
    public void deletePetic51(Petic51 petic51) throws Petic51NotFound;

    /**
     *
     * @param petic51
     * @throws Petic51NotFound
     */
    public void updatePetic51(Petic51 petic51) throws Petic51NotFound;
}
