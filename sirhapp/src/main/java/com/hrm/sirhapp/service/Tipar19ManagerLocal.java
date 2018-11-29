package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Tipar19;
import com.hrm.sirhapp.service.exception.Tipar19AlreadyExists;
import com.hrm.sirhapp.service.exception.Tipar19NotFound;
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
public interface Tipar19ManagerLocal {

    /**
     *
     * @param idubi
     * @return
     * @throws Tipar19NotFound
     */
    public Tipar19 getTipar19(Integer idubi) throws Tipar19NotFound;

    /**
     *
     * @return
     */
    public List<Tipar19> getListTipar19();

    /**
     *
     * @return
     */
    public List<Tipar19> retrieveTipar19();

    /**
     *
     * @param tipar19
     * @return
     * @throws Tipar19AlreadyExists
     */
    public Tipar19 registerTipar19(Tipar19 tipar19) throws Tipar19AlreadyExists;

    /**
     *
     * @param tipar19
     * @throws Tipar19NotFound
     */
    public void removeTipar19(Tipar19 tipar19) throws Tipar19NotFound;

    /**
     *
     * @param tipar19
     * @throws Tipar19NotFound
     */
    public void deleteTipar19(Tipar19 tipar19) throws Tipar19NotFound;

    /**
     *
     * @param tipar19
     * @throws Tipar19NotFound
     */
    public void updateTipar19(Tipar19 tipar19) throws Tipar19NotFound;
}
