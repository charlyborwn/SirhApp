package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Ticon17;
import com.hrm.sirhapp.service.exception.Ticon17AlreadyExists;
import com.hrm.sirhapp.service.exception.Ticon17NotFound;
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
public interface Ticon17ManagerLocal {

    /**
     *
     * @param idtic
     * @return
     * @throws Ticon17NotFound
     */
    public Ticon17 getTicon17(Integer idtic) throws Ticon17NotFound;
    
    /**
     *
     * @param cvtic
     * @return
     * @throws Ticon17NotFound
     */
    public Ticon17 getTicon17(String cvtic) throws Ticon17NotFound;

    /**
     *
     * @return
     */
    public List<Ticon17> getListTicon17();

    /**
     *
     * @return
     */
    public List<Ticon17> retrieveTicon17();

    /**
     *
     * @param tapri16
     * @return
     * @throws Ticon17AlreadyExists
     */
    public Ticon17 registerTicon17(Ticon17 tapri16) throws Ticon17AlreadyExists;

    /**
     *
     * @param tapri16
     * @throws Ticon17NotFound
     */
    public void removeTicon17(Ticon17 tapri16) throws Ticon17NotFound;

    /**
     *
     * @param tapri16
     * @throws Ticon17NotFound
     */
    public void deleteTicon17(Ticon17 tapri16) throws Ticon17NotFound;

    /**
     *
     * @param tapri16
     * @throws Ticon17NotFound
     */
    public void updateTicon17(Ticon17 tapri16) throws Ticon17NotFound;
}
