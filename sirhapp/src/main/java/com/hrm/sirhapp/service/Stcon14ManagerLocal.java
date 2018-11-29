package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Stcon14;
import com.hrm.sirhapp.service.exception.Stcon14AlreadyExists;
import com.hrm.sirhapp.service.exception.Stcon14NotFound;
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
public interface Stcon14ManagerLocal {

    /**
     *
     * @param idstc
     * @return
     * @throws Stcon14NotFound
     */
    public Stcon14 getStcon14(Integer idstc) throws Stcon14NotFound;

    /**
     *
     * @return
     */
    public List<Stcon14> getListStcon14();

    /**
     *
     * @return
     */
    public List<Stcon14> retrieveStcon14();

    /**
     *
     * @param stcon14
     * @return
     * @throws Stcon14AlreadyExists
     */
    public Stcon14 registerStcon14(Stcon14 stcon14) throws Stcon14AlreadyExists;

    /**
     *
     * @param stcon14
     * @throws Stcon14NotFound
     */
    public void removeStcon14(Stcon14 stcon14) throws Stcon14NotFound;
    
    /**
     *
     * @param stcon14
     * @throws Stcon14NotFound
     */
    public void deleteStcon14(Stcon14 stcon14) throws Stcon14NotFound;

    /**
     *
     * @param stcon14
     * @throws Stcon14NotFound
     */
    public void updateStcon14(Stcon14 stcon14) throws Stcon14NotFound;
}
