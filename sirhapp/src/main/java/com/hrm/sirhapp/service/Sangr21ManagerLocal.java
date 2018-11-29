package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Sangr21;
import com.hrm.sirhapp.service.exception.Sangr21AlreadyExists;
import com.hrm.sirhapp.service.exception.Sangr21NotFound;
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
public interface Sangr21ManagerLocal {

    /**
     *
     * @param idcat
     * @return
     * @throws Sangr21NotFound
     */
    public Sangr21 getSangr21(Integer idcat) throws Sangr21NotFound;

    /**
     *
     * @return
     */
    public List<Sangr21> getListSangr21();

    /**
     *
     * @return
     */
    public List<Sangr21> retrieveSangr21();

    /**
     *
     * @param sangr21
     * @return
     * @throws Sangr21AlreadyExists
     */
    public Sangr21 registerSangr21(Sangr21 sangr21) throws Sangr21AlreadyExists;

    /**
     *
     * @param sangr21
     * @throws Sangr21NotFound
     */
    public void removeSangr21(Sangr21 sangr21) throws Sangr21NotFound;
    
    /**
     *
     * @param sangr21
     * @throws Sangr21NotFound
     */
    public void deleteSangr21(Sangr21 sangr21) throws Sangr21NotFound;

    /**
     *
     * @param sangr21
     * @throws Sangr21NotFound
     */
    public void updateSangr21(Sangr21 sangr21) throws Sangr21NotFound;
}
