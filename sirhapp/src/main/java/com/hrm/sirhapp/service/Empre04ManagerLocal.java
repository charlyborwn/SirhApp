package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Empre04;
import com.hrm.sirhapp.service.exception.Empre04AlreadyExists;
import com.hrm.sirhapp.service.exception.Empre04NotFound;
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
public interface Empre04ManagerLocal {

    /**
     *
     * @param idcat
     * @return
     * @throws Empre04NotFound
     */
    public Empre04 getEmpre04(Integer idcat) throws Empre04NotFound;

    /**
     *
     * @param cvemp
     * @return
     * @throws Empre04NotFound
     */
    public Empre04 getEmpre04(String cvemp) throws Empre04NotFound;

    /**
     *
     * @return
     */
    public List<Empre04> getListEmpre04();

    /**
     *
     * @return
     */
    public List<Empre04> getListEmpre04All();

    /**
     *
     * @return
     */
    public List<Empre04> retrieveEmpre04();

    /**
     *
     * @param empre04
     * @return
     * @throws Empre04AlreadyExists
     */
    public Empre04 registerEmpre04(Empre04 empre04) throws Empre04AlreadyExists;

    /**
     *
     * @param empre04
     * @throws Empre04NotFound
     */
    public void removeEmpre04(Empre04 empre04) throws Empre04NotFound;

    /**
     *
     * @param empre04
     * @throws Empre04NotFound
     */
    public void deleteEmpre04(Empre04 empre04) throws Empre04NotFound;

    /**
     *
     * @param empre04
     * @throws Empre04NotFound
     */
    public void updateEmpre04(Empre04 empre04) throws Empre04NotFound;
}
