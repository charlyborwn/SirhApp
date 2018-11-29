package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Exper31;
import com.hrm.sirhapp.service.exception.Exper31AlreadyExists;
import com.hrm.sirhapp.service.exception.Exper31NotFound;
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
public interface Exper31ManagerLocal {

    /**
     *
     * @param idexp
     * @return
     * @throws Exper31NotFound
     */
    public Exper31 getExper31(Integer idexp) throws Exper31NotFound;

    /**
     *
     * @param ntexp
     * @return
     */
    public List<Exper31> getListExper31(Integer ntexp);

    /**
     *
     * @return
     */
    public List<Exper31> retrieveExper31();

    /**
     *
     * @param exper31
     * @return
     * @throws Exper31AlreadyExists
     */
    public Exper31 registerExper31(Exper31 exper31) throws Exper31AlreadyExists;

    /**
     *
     * @param exper31
     * @throws Exper31NotFound
     */
    public void removeExper31(Exper31 exper31) throws Exper31NotFound;

    /**
     *
     * @param exper31
     * @throws Exper31NotFound
     */
    public void deleteExper31(Exper31 exper31) throws Exper31NotFound;

    /**
     *
     * @param exper31
     * @throws Exper31NotFound
     */
    public void updateExper31(Exper31 exper31) throws Exper31NotFound;
}
