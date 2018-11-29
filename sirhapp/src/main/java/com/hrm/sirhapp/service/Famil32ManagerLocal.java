package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Famil32;
import com.hrm.sirhapp.service.exception.Famil32AlreadyExists;
import com.hrm.sirhapp.service.exception.Famil32NotFound;
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
public interface Famil32ManagerLocal {

    /**
     *
     * @param idfam
     * @return
     * @throws Famil32NotFound
     */
    public Famil32 getFamil32(Integer idfam) throws Famil32NotFound;

    /**
     *
     * @param ntfam
     * @return
     */
    public List<Famil32> getListFamil32(Integer ntfam);

    /**
     *
     * @return
     */
    public List<Famil32> retrieveFamil32();

    /**
     *
     * @param famil32
     * @return
     * @throws Famil32AlreadyExists
     */
    public Famil32 registerFamil32(Famil32 famil32) throws Famil32AlreadyExists;

    /**
     *
     * @param famil32
     * @throws Famil32NotFound
     */
    public void removeFamil32(Famil32 famil32) throws Famil32NotFound;

    /**
     *
     * @param famil32
     * @throws Famil32NotFound
     */
    public void deleteFamil32(Famil32 famil32) throws Famil32NotFound;

    /**
     *
     * @param famil32
     * @throws Famil32NotFound
     */
    public void updateFamil32(Famil32 famil32) throws Famil32NotFound;
}
