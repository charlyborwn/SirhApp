package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Apriv25;
import com.hrm.sirhapp.service.exception.Apriv25AlreadyExists;
import com.hrm.sirhapp.service.exception.Apriv25NotFound;
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
public interface Apriv25ManagerLocal {

    /**
     *
     * @param idapr
     * @return
     * @throws Apriv25NotFound
     */
    public Apriv25 getApriv25(Integer idapr) throws Apriv25NotFound;

    /**
     *
     * @param idapr
     * @return
     */
    public Boolean alreadyExistsInnactive(Integer idapr);

    /**
     *
     * @param nuapr
     * @return
     */
    public List<Apriv25> getListApriv25(String nuapr);

    /**
     *
     * @param nuapr
     * @param tiapr
     * @return
     */
    public List<Apriv25> getListApriv25(String nuapr, String tiapr);

    /**
     *
     * @param esapr
     * @param ceapr
     * @param ndapr
     * @param period
     * @param esam
     * @param espa
     * @param espm
     * @param my_query
     * @return
     */
    public List<Object[]> retrieveApriv25Repasp(
            String esapr,
            String ceapr,
            String ndapr,
            String period,
            String esam,
            String espa,
            String espm, 
            String my_query);

    /**
     *
     * @return
     */
    public List<Apriv25> retrieveApriv25();

    /**
     *
     * @param nutra
     * @return
     */
    public List<Apriv25> getListApriv25Traba36(Integer nutra);

    /**
     *
     * @param apriv25
     * @return
     * @throws Apriv25AlreadyExists
     */
    public Apriv25 registerApriv25(Apriv25 apriv25) throws Apriv25AlreadyExists;

    /**
     *
     * @param apriv25
     * @return
     * @throws Apriv25NotFound
     */
    public Apriv25 deleteApriv25(Apriv25 apriv25) throws Apriv25NotFound;

    /**
     *
     * @param apriv25
     * @return
     * @throws Apriv25NotFound
     */
    public Apriv25 updateApriv25(Apriv25 apriv25) throws Apriv25NotFound;

    /**
     *
     * @param apriv25
     * @throws Apriv25NotFound
     */
    public void removeApriv25(Apriv25 apriv25) throws Apriv25NotFound;
}
