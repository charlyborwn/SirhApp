package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Traba36;
import com.hrm.sirhapp.model.Usuar24;
import com.hrm.sirhapp.service.exception.Traba36AlreadyExists;
import com.hrm.sirhapp.service.exception.Traba36NotFound;
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
public interface Traba36ManagerLocal {

    /**
     *
     * @param nutra
     * @return
     */
    public Boolean alreadyExistsInnactive(Integer nutra);

    /**
     *
     * @param nutra
     * @return
     * @throws Traba36NotFound
     */
    public Traba36 getTraba36(Integer nutra) throws Traba36NotFound;
    
    /**
     *
     * @param nutra
     * @return
     */
    public String getTraba36Nctra(Integer nutra);

    /**
     *
     * @param usuar24
     * @return
     */
    public List<Traba36> retrieveTraba36(Usuar24 usuar24);

    /**
     *
     * @param traba36
     * @return
     */
    public List<Traba36> getListTraba36Wiz(Traba36 traba36);

    /**
     *
     * @param traba36
     * @param usuar24
     * @return
     */
    public List<Traba36> getListTraba36PvWiz(Traba36 traba36, Usuar24 usuar24);

    /**
     *
     * @param nutra
     * @param usuar24
     * @return
     */
    public List<Traba36> getListTraba36(Integer nutra, Usuar24 usuar24);

    /**
     *
     * @param rftra
     * @param usuar24
     * @return
     */
    public List<Traba36> getListTraba36(String rftra, Usuar24 usuar24);

    /**
     *
     * @param traba36
     * @param usuar24
     * @return
     */
    public List<Traba36> getListTraba36(Traba36 traba36, Usuar24 usuar24);

    /**
     *
     * @param traba36
     * @return
     * @throws Traba36AlreadyExists
     */
    public Traba36 registerTraba36(Traba36 traba36) throws Traba36AlreadyExists;

    /**
     *
     * @param traba36
     * @throws Traba36NotFound
     */
    public void removeTraba36(Traba36 traba36) throws Traba36NotFound;

    /**
     *
     * @param traba36
     * @throws Traba36NotFound
     */
    public void deleteTraba36(Traba36 traba36) throws Traba36NotFound;

    /**
     *
     * @param traba36
     * @return
     * @throws Traba36NotFound
     */
    public Traba36 updateTraba36(Traba36 traba36) throws Traba36NotFound;
}
