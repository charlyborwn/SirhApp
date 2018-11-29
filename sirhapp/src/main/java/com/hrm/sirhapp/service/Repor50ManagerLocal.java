package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Repor50;
import com.hrm.sirhapp.service.exception.Repor50AlreadyExists;
import com.hrm.sirhapp.service.exception.Repor50NotFound;
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
public interface Repor50ManagerLocal {

    /**
     *
     * @param idrep
     * @return
     * @throws Repor50NotFound
     */
    public Repor50 getRepor50(Integer idrep) throws Repor50NotFound;

    /**
     *
     * @param repor50
     * @return
     * @throws Repor50AlreadyExists
     */
    public Repor50 registerRepor50(Repor50 repor50) throws Repor50AlreadyExists;

    /**
     *
     * @param repor50
     * @return
     * @throws Repor50NotFound
     */
    public Repor50 updateRepor50(Repor50 repor50) throws Repor50NotFound;

    /**
     *
     * @param repor50
     * @throws Repor50NotFound
     */
    public void removeRepor50(Repor50 repor50) throws Repor50NotFound;
    
    /**
     *
     * @param repor50
     * @throws Repor50NotFound
     */
    public void deleteRepor50(Repor50 repor50) throws Repor50NotFound;

    /**
     *
     * @param repor50ID
     * @return
     * @throws Repor50NotFound
     */
    public byte[] getRepor50Content(Integer repor50ID) throws Repor50NotFound;

    /**
     *
     * @return
     */
    public List<Repor50> retrieveRepor50();

    /**
     *
     * @return
     */
    public List<Repor50> getListRepor50();
    
    /**
     *
     * @return
     */
    public List<Repor50> getListInnactiveRepor50();

    /**
     *
     * @param repor50
     * @return
     */
    public List<Repor50> getAllRepor50s(Repor50 repor50);
}
