package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Strab15;
import com.hrm.sirhapp.service.exception.Strab15AlreadyExists;
import com.hrm.sirhapp.service.exception.Strab15NotFound;
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
public interface Strab15ManagerLocal {

    /**
     *
     * @param idstr
     * @return
     * @throws Strab15NotFound
     */
    public Strab15 getStrab15(Integer idstr) throws Strab15NotFound;

    /**
     *
     * @return
     */
    public List<Strab15> getListStrab15();

    /**
     *
     * @return
     */
    public List<Strab15> retrieveStrab15();

    /**
     *
     * @param strab15
     * @return
     * @throws Strab15AlreadyExists
     */
    public Strab15 registerStrab15(Strab15 strab15) throws Strab15AlreadyExists;

    /**
     *
     * @param strab15
     * @throws Strab15NotFound
     */
    public void removeStrab15(Strab15 strab15) throws Strab15NotFound;

    /**
     *
     * @param strab15
     * @throws Strab15NotFound
     */
    public void deleteStrab15(Strab15 strab15) throws Strab15NotFound;

    /**
     *
     * @param strab15
     * @throws Strab15NotFound
     */
    public void updateStrab15(Strab15 strab15) throws Strab15NotFound;
}
