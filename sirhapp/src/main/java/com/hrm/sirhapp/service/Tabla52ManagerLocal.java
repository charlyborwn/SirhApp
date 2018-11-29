package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Tabla52;
import com.hrm.sirhapp.service.exception.Tabla52AlreadyExists;
import com.hrm.sirhapp.service.exception.Tabla52NotFound;
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
public interface Tabla52ManagerLocal {

    /**
     *
     * @param idmon
     * @return
     * @throws Tabla52NotFound
     */
    public Tabla52 getTabla52(Integer idmon) throws Tabla52NotFound;

    /**
     *
     * @return
     */
    public List<Tabla52> getListTabla52();

    /**
     *
     * @return
     */
    public List<Tabla52> retrieveTabla52();

    /**
     *
     * @param tabla52
     * @return
     * @throws Tabla52AlreadyExists
     */
    public Tabla52 registerTabla52(Tabla52 tabla52) throws Tabla52AlreadyExists;

    /**
     *
     * @param tabla52
     * @throws Tabla52NotFound
     */
    public void removeTabla52(Tabla52 tabla52) throws Tabla52NotFound;

    /**
     *
     * @param tabla52
     * @throws Tabla52NotFound
     */
    public void deleteTabla52(Tabla52 tabla52) throws Tabla52NotFound;

    /**
     *
     * @param tabla52
     * @throws Tabla52NotFound
     */
    public void updateTabla52(Tabla52 tabla52) throws Tabla52NotFound;
}
