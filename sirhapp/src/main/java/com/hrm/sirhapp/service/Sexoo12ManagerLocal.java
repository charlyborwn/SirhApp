package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Sexoo12;
import com.hrm.sirhapp.service.exception.Sexoo12AlreadyExists;
import com.hrm.sirhapp.service.exception.Sexoo12NotFound;
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
public interface Sexoo12ManagerLocal {

    /**
     *
     * @param idsex
     * @return
     * @throws Sexoo12NotFound
     */
    public Sexoo12 getSexoo12(Integer idsex) throws Sexoo12NotFound;

    /**
     *
     * @return
     */
    public List<Sexoo12> getListSexoo12();

    /**
     *
     * @return
     */
    public List<Sexoo12> retrieveSexoo12();

    /**
     *
     * @param sexoo12
     * @return
     * @throws Sexoo12AlreadyExists
     */
    public Sexoo12 registerSexoo12(Sexoo12 sexoo12) throws Sexoo12AlreadyExists;

    /**
     *
     * @param sexoo12
     * @throws Sexoo12NotFound
     */
    public void removeSexoo12(Sexoo12 sexoo12) throws Sexoo12NotFound;

    /**
     *
     * @param sexoo12
     * @throws Sexoo12NotFound
     */
    public void deleteSexoo12(Sexoo12 sexoo12) throws Sexoo12NotFound;

    /**
     *
     * @param sexoo12
     * @throws Sexoo12NotFound
     */
    public void updateSexoo12(Sexoo12 sexoo12) throws Sexoo12NotFound;
}
