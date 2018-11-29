package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Ubica23;
import com.hrm.sirhapp.service.exception.Ubica23AlreadyExists;
import com.hrm.sirhapp.service.exception.Ubica23NotFound;
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
public interface Ubica23ManagerLocal {

    /**
     *
     * @param idubi
     * @return
     * @throws Ubica23NotFound
     */
    public Ubica23 getUbica23(Integer idubi) throws Ubica23NotFound;

    /**
     *
     * @return
     */
    public List<Ubica23> getListUbica23();

    /**
     *
     * @return
     */
    public List<String> getListUbica23Paises();

    /**
     *
     * @return
     */
    public List<Ubica23> getListUbica23Estados();

    /**
     *
     * @param pais
     * @return
     */
    public List<String> getListUbica23Estados(String pais);

    /**
     *
     * @param pais
     * @param estado
     * @return
     */
    public List<String> getListUbica23Municipios(String pais, String estado);

    /**
     *
     * @param pais
     * @param estado
     * @param municipio
     * @return
     */
    public List<String> getListUbica23Ciudades(String pais, String estado, String municipio);

    /**
     *
     * @param pais
     * @param estado
     * @param municipio
     * @param ciudad
     * @return
     */
    public List<String> getListUbica23Colonias(String pais, String estado, String municipio, String ciudad);

    /**
     *
     * @param pais
     * @param estado
     * @param municipio
     * @param ciudad
     * @param colonia
     * @return
     */
    public String getListUbica23Cp(String pais, String estado, String municipio, String ciudad, String colonia);

    /**
     *
     * @param cp
     * @return
     */
    public List<Ubica23> getListUbica23Cp(String cp);

    /**
     *
     * @return
     */
    public List<Ubica23> retrieveUbica23();

    /**
     *
     * @param ubica23
     * @return
     * @throws Ubica23AlreadyExists
     */
    public Ubica23 registerUbica23(Ubica23 ubica23) throws Ubica23AlreadyExists;

    /**
     *
     * @param ubica23
     * @throws Ubica23NotFound
     */
    public void removeUbica23(Ubica23 ubica23) throws Ubica23NotFound;

    /**
     *
     * @param ubica23
     * @throws Ubica23NotFound
     */
    public void deleteUbica23(Ubica23 ubica23) throws Ubica23NotFound;

    /**
     *
     * @param ubica23
     * @throws Ubica23NotFound
     */
    public void updateUbica23(Ubica23 ubica23) throws Ubica23NotFound;
}
