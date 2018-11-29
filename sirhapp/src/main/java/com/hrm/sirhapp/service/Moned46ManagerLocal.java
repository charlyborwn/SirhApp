package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Moned46;
import com.hrm.sirhapp.service.exception.Moned46AlreadyExists;
import com.hrm.sirhapp.service.exception.Moned46NotFound;
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
public interface Moned46ManagerLocal {

    /**
     *
     * @param idmon
     * @return
     * @throws Moned46NotFound
     */
    public Moned46 getMoned46(Integer idmon) throws Moned46NotFound;

    /**
     *
     * @return
     */
    public List<Moned46> getListMoned46();

    /**
     *
     * @return
     */
    public List<Moned46> retrieveMoned46();

    /**
     *
     * @param moned46
     * @return
     * @throws Moned46AlreadyExists
     */
    public Moned46 registerMoned46(Moned46 moned46) throws Moned46AlreadyExists;

    /**
     *
     * @param moned46
     * @throws Moned46NotFound
     */
    public void removeMoned46(Moned46 moned46) throws Moned46NotFound;

    /**
     *
     * @param moned46
     * @throws Moned46NotFound
     */
    public void deleteMoned46(Moned46 moned46) throws Moned46NotFound;

    /**
     *
     * @param moned46
     * @throws Moned46NotFound
     */
    public void updateMoned46(Moned46 moned46) throws Moned46NotFound;
}
