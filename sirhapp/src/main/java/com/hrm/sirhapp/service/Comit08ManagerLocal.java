package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Comit08;
import com.hrm.sirhapp.service.exception.Comit08AlreadyExists;
import com.hrm.sirhapp.service.exception.Comit08NotFound;
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
public interface Comit08ManagerLocal {

    /**
     *
     * @param idcom
     * @return
     * @throws Comit08NotFound
     */
    public Comit08 getComit08(Integer idcom) throws Comit08NotFound;

    /**
     *
     * @param cecom
     * @param cdcom
     * @return
     */
    public List<Comit08> getListComit08CecomCdcom(String cecom, String cdcom);

    /**
     *
     * @param cecom
     * @param cdcom
     * @param cvcom
     * @return
     */
    public String getComit08CecomCdcom(String cecom, String cdcom, String cvcom);

    /**
     *
     * @return
     */
    public List<Comit08> getListComit08();

    /**
     *
     * @param ntcom
     * @return
     */
    public String getComit08Ntcom(Integer ntcom);

    /**
     *
     * @return
     */
    public List<Comit08> retrieveComit08();

    /**
     *
     * @param comit08
     * @return
     * @throws Comit08AlreadyExists
     */
    public Comit08 registerComit08(Comit08 comit08) throws Comit08AlreadyExists;

    /**
     *
     * @param comit08
     * @throws Comit08NotFound
     */
    public void removeComit08(Comit08 comit08) throws Comit08NotFound;

    /**
     *
     * @param comit08
     * @throws Comit08NotFound
     */
    public void deleteComit08(Comit08 comit08) throws Comit08NotFound;

    /**
     *
     * @param comit08
     * @throws Comit08NotFound
     */
    public void updateComit08(Comit08 comit08) throws Comit08NotFound;
}
