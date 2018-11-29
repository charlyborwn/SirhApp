package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Meses07;
import com.hrm.sirhapp.service.exception.Meses07AlreadyExists;
import com.hrm.sirhapp.service.exception.Meses07NotFound;
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
public interface Meses07ManagerLocal {

    /**
     *
     * @param idmes
     * @return
     * @throws Meses07NotFound
     */
    public Meses07 getMeses07(Integer idmes) throws Meses07NotFound;

    /**
     *
     * @return
     */
    public List<Meses07> getListMeses07();

    /**
     *
     * @return
     */
    public List<Meses07> retrieveMeses07();

    /**
     *
     * @param meses07
     * @return
     * @throws Meses07AlreadyExists
     */
    public Meses07 registerMeses07(Meses07 meses07) throws Meses07AlreadyExists;

    /**
     *
     * @param meses07
     * @throws Meses07NotFound
     */
    public void removeMeses07(Meses07 meses07) throws Meses07NotFound;

    /**
     *
     * @param meses07
     * @throws Meses07NotFound
     */
    public void deleteMeses07(Meses07 meses07) throws Meses07NotFound;

    /**
     *
     * @param meses07
     * @throws Meses07NotFound
     */
    public void updateMeses07(Meses07 meses07) throws Meses07NotFound;
}
