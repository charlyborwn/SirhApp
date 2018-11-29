package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Tdhab45;
import com.hrm.sirhapp.service.exception.Tdhab45AlreadyExists;
import com.hrm.sirhapp.service.exception.Tdhab45NotFound;
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
public interface Tdhab45ManagerLocal {

    /**
     *
     * @param idhab
     * @return
     * @throws Tdhab45NotFound
     */
    public Tdhab45 getTdhab45(Integer idhab) throws Tdhab45NotFound;

    /**
     *
     * @return
     */
    public List<Tdhab45> getListTdhab45();

    /**
     *
     * @return
     */
    public List<Tdhab45> retrieveTdhab45();

    /**
     *
     * @param tdhab45
     * @return
     * @throws Tdhab45AlreadyExists
     */
    public Tdhab45 registerTdhab45(Tdhab45 tdhab45) throws Tdhab45AlreadyExists;

    /**
     *
     * @param tdhab45
     * @throws Tdhab45NotFound
     */
    public void removeTdhab45(Tdhab45 tdhab45) throws Tdhab45NotFound;

    /**
     *
     * @param tdhab45
     * @throws Tdhab45NotFound
     */
    public void deleteTdhab45(Tdhab45 tdhab45) throws Tdhab45NotFound;

    /**
     *
     * @param tdhab45
     * @throws Tdhab45NotFound
     */
    public void updateTdhab45(Tdhab45 tdhab45) throws Tdhab45NotFound;
}
