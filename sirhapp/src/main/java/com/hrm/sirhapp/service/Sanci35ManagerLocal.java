package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Sanci35;
import com.hrm.sirhapp.service.exception.Sanci35AlreadyExists;
import com.hrm.sirhapp.service.exception.Sanci35NotFound;
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
public interface Sanci35ManagerLocal {

    /**
     *
     * @param ntsan
     * @return
     * @throws Sanci35NotFound
     */
    public Sanci35 getSanci35(Integer ntsan) throws Sanci35NotFound;

    /**
     *
     * @param idsan
     * @return
     */
    public Boolean alreadyExistsInnactive(Integer idsan);

    /**
     *
     * @param nusan
     * @return
     */
    public List<Sanci35> getListSanci35(String nusan);

    /**
     *
     * @param nusan
     * @param tisan
     * @return
     */
    public List<Sanci35> getListSanci35(String nusan, String tisan);

    /**
     *
     * @param nutra
     * @return
     */
    public List<Sanci35> getListSanci35Traba36(Integer nutra);

    /**
     *
     * @return
     */
    public List<Sanci35> retrieveSanci35();

    /**
     *
     * @param sanci35
     * @return
     * @throws Sanci35AlreadyExists
     */
    public Sanci35 registerSanci35(Sanci35 sanci35) throws Sanci35AlreadyExists;

    /**
     *
     * @param sanci35
     * @throws Sanci35NotFound
     */
    public void removeSanci35(Sanci35 sanci35) throws Sanci35NotFound;

    /**
     *
     * @param sanci35
     * @return
     * @throws Sanci35NotFound
     */
    public Sanci35 deleteSanci35(Sanci35 sanci35) throws Sanci35NotFound;

    /**
     *
     * @param sanci35
     * @return
     * @throws Sanci35NotFound
     */
    public Sanci35 updateSanci35(Sanci35 sanci35) throws Sanci35NotFound;
}
