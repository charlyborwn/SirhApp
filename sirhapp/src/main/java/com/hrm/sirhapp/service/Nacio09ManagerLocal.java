package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Nacio09;
import com.hrm.sirhapp.service.exception.Nacio09AlreadyExists;
import com.hrm.sirhapp.service.exception.Nacio09NotFound;
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
public interface Nacio09ManagerLocal {

    /**
     *
     * @param idcat
     * @return
     * @throws Nacio09NotFound
     */
    public Nacio09 getNacio09(Integer idcat) throws Nacio09NotFound;

    /**
     *
     * @return
     */
    public List<Nacio09> getListNacio09();

    /**
     *
     * @return
     */
    public List<Nacio09> retrieveNacio09();

    /**
     *
     * @param nacio09
     * @return
     * @throws Nacio09AlreadyExists
     */
    public Nacio09 registerNacio09(Nacio09 nacio09) throws Nacio09AlreadyExists;

    /**
     *
     * @param nacio09
     * @throws Nacio09NotFound
     */
    public void removeNacio09(Nacio09 nacio09) throws Nacio09NotFound;

    /**
     *
     * @param nacio09
     * @throws Nacio09NotFound
     */
    public void deleteNacio09(Nacio09 nacio09) throws Nacio09NotFound;

    /**
     *
     * @param nacio09
     * @throws Nacio09NotFound
     */
    public void updateNacio09(Nacio09 nacio09) throws Nacio09NotFound;
}
