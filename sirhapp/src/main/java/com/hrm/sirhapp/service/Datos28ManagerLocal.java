package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Datos28;
import com.hrm.sirhapp.service.exception.Datos28AlreadyExists;
import com.hrm.sirhapp.service.exception.Datos28NotFound;
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
public interface Datos28ManagerLocal {

    /**
     *
     * @param ntdat
     * @return
     */
    public Boolean alreadyExistsInnactive(Integer ntdat);

    /**
     *
     * @param ntdat
     * @return
     * @throws Datos28NotFound
     */
    public Datos28 getDatos28(Integer ntdat) throws Datos28NotFound;

    /**
     *
     * @param ntdat
     * @return
     * @throws Datos28NotFound
     */
    public Datos28 getDatos28ById(Integer ntdat) throws Datos28NotFound;

    /**
     *
     * @return
     */
    public List<Datos28> retrieveDatos28();

    /**
     *
     * @param datos28
     * @return
     * @throws Datos28AlreadyExists
     */
    public Datos28 registerDatos28(Datos28 datos28) throws Datos28AlreadyExists;

    /**
     *
     * @param datos28
     * @throws Datos28NotFound
     */
    public void removeDatos28(Datos28 datos28) throws Datos28NotFound;

    /**
     *
     * @param datos28
     * @throws Datos28NotFound
     */
    public void deleteDatos28(Datos28 datos28) throws Datos28NotFound;

    /**
     *
     * @param datos28
     * @throws Datos28NotFound
     */
    public void updateDatos28(Datos28 datos28) throws Datos28NotFound;
}
