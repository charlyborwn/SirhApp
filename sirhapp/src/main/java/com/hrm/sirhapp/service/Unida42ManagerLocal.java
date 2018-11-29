package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Unida42;
import com.hrm.sirhapp.service.exception.Unida42AlreadyExists;
import com.hrm.sirhapp.service.exception.Unida42NotFound;
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
public interface Unida42ManagerLocal {

    /**
     *
     * @param iduni
     * @return
     * @throws Unida42NotFound
     */
    public Unida42 getUnida42(Integer iduni) throws Unida42NotFound;

    /**
     *
     * @return
     */
    public List<Unida42> getListUnida42();

    /**
     *
     * @return
     */
    public List<Unida42> retrieveUnida42();

    /**
     *
     * @param unida42
     * @return
     * @throws Unida42AlreadyExists
     */
    public Unida42 registerUnida42(Unida42 unida42) throws Unida42AlreadyExists;

    /**
     *
     * @param unida42
     * @throws Unida42NotFound
     */
    public void removeUnida42(Unida42 unida42) throws Unida42NotFound;

    /**
     *
     * @param unida42
     * @throws Unida42NotFound
     */
    public void deleteUnida42(Unida42 unida42) throws Unida42NotFound;

    /**
     *
     * @param unida42
     * @throws Unida42NotFound
     */
    public void updateUnida42(Unida42 unida42) throws Unida42NotFound;
}
