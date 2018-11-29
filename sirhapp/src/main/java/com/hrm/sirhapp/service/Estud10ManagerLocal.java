package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Estud10;
import com.hrm.sirhapp.service.exception.Estud10AlreadyExists;
import com.hrm.sirhapp.service.exception.Estud10NotFound;
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
public interface Estud10ManagerLocal {

    /**
     *
     * @param idest
     * @return
     * @throws Estud10NotFound
     */
    public Estud10 getEstud10(Integer idest) throws Estud10NotFound;

    /**
     *
     * @return
     */
    public List<Estud10> getListEstud10();

    /**
     *
     * @return
     */
    public List<Estud10> retrieveEstud10();

    /**
     *
     * @param estud10
     * @return
     * @throws Estud10AlreadyExists
     */
    public Estud10 registerEstud10(Estud10 estud10) throws Estud10AlreadyExists;

    /**
     *
     * @param estud10
     * @throws Estud10NotFound
     */
    public void removeEstud10(Estud10 estud10) throws Estud10NotFound;

    /**
     *
     * @param estud10
     * @throws Estud10NotFound
     */
    public void deleteEstud10(Estud10 estud10) throws Estud10NotFound;

    /**
     *
     * @param estud10
     * @throws Estud10NotFound
     */
    public void updateEstud10(Estud10 estud10) throws Estud10NotFound;
}
