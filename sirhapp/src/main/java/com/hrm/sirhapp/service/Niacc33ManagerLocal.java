package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Niacc33;
import com.hrm.sirhapp.service.exception.Niacc33AlreadyExists;
import com.hrm.sirhapp.service.exception.Niacc33NotFound;
import java.util.List;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
public interface Niacc33ManagerLocal {

    /**
     *
     * @param idnia
     * @return
     * @throws Niacc33NotFound
     */
    public Niacc33 getNiacc33(Integer idnia) throws Niacc33NotFound;

    /**
     *
     * @param cvusu
     * @return
     * @throws Niacc33NotFound
     */
    public Niacc33 getNiacc33(String cvusu) throws Niacc33NotFound;

    /**
     *
     * @param cpnia
     * @return
     */
    public String getNiacc33Rol(String cpnia);

    /**
     *
     * @return
     */
    public List<Niacc33> getListNiacc33();

    /**
     *
     * @param cvnia
     * @return
     */
    public List<String> getListNiacc33(String cvnia);

    /**
     *
     * @param ronia
     * @return
     */
    public List<Niacc33> getListNiacc33Nav(String ronia);

    /**
     *
     * @return
     */
    public List<Niacc33> getListNiacc33Nania();

    /**
     *
     * @return
     */
    public List<Niacc33> retrieveNiacc33();

    /**
     *
     * @param niacc33
     * @return
     * @throws Niacc33AlreadyExists
     */
    public Niacc33 registerNiacc33Nav(Niacc33 niacc33) throws Niacc33AlreadyExists;

    /**
     *
     * @param niacc33
     * @throws Niacc33NotFound
     */
    public void removeNiacc33(Niacc33 niacc33) throws Niacc33NotFound;

    /**
     *
     * @param niacc33
     * @throws Niacc33NotFound
     */
    public void deleteNiacc33(Niacc33 niacc33) throws Niacc33NotFound;

    /**
     *
     * @param niacc33
     * @return
     * @throws Niacc33AlreadyExists
     */
    public Niacc33 registerNiacc33(Niacc33 niacc33) throws Niacc33AlreadyExists;

    /**
     *
     * @param niacc33
     * @throws Niacc33NotFound
     */
    public void updateNiacc33(Niacc33 niacc33) throws Niacc33NotFound;

    /**
     *
     * @param listNiacc33
     * @throws Niacc33NotFound
     */
    public void deleteNiacc33Usuar24(List<Niacc33> listNiacc33) throws Niacc33NotFound;

}
