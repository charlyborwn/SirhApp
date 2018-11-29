package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Benef26;
import com.hrm.sirhapp.service.exception.Benef26AlreadyExists;
import com.hrm.sirhapp.service.exception.Benef26NotFound;
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
public interface Benef26ManagerLocal {

    /**
     *
     * @param idben
     * @return
     * @throws Benef26NotFound
     */
    public Benef26 getBenef26(Integer idben) throws Benef26NotFound;
    
    /**
     *
     * @param ntben
     * @return
     */
    public Float getPercentage(Integer ntben);

    /**
     *
     * @param ntben
     * @return
     */
    public List<Benef26> getListBenef26(Integer ntben);

    /**
     *
     * @return
     */
    public List<Benef26> retrieveBenef26();

    /**
     *
     * @param apriv25
     * @return
     * @throws Benef26AlreadyExists
     */
    public Benef26 registerBenef26(Benef26 apriv25) throws Benef26AlreadyExists;

    /**
     *
     * @param benef26
     * @throws Benef26NotFound
     */
    public void deleteBenef26(Benef26 benef26) throws Benef26NotFound;

    /**
     *
     * @param apriv25
     * @throws Benef26NotFound
     */
    public void removeBenef26(Benef26 apriv25) throws Benef26NotFound;

    /**
     *
     * @param apriv25
     * @throws Benef26NotFound
     */
    public void updateBenef26(Benef26 apriv25) throws Benef26NotFound;
}
