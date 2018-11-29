package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Espec05;
import com.hrm.sirhapp.service.exception.Espec05AlreadyExists;
import com.hrm.sirhapp.service.exception.Espec05NotFound;
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
public interface Espec05ManagerLocal {

    /**
     *
     * @param idcat
     * @return
     * @throws Espec05NotFound
     */
    public Espec05 getEspec05(Integer idcat) throws Espec05NotFound;

    /**
     *
     * @return
     */
    public List<Espec05> getListEspec05();

    /**
     *
     * @param noesp
     * @return
     */
    public List<String> getListEspec05Noesp(String noesp);

    /**
     *
     * @return
     */
    public List<Espec05> retrieveEspec05();

    /**
     *
     * @param espec05
     * @return
     * @throws Espec05AlreadyExists
     */
    public Espec05 registerEspec05(Espec05 espec05) throws Espec05AlreadyExists;

    /**
     *
     * @param espec05
     * @throws Espec05NotFound
     */
    public void removeEspec05(Espec05 espec05) throws Espec05NotFound;

    /**
     *
     * @param espec05
     * @throws Espec05NotFound
     */
    public void deleteEspec05(Espec05 espec05) throws Espec05NotFound;

    /**
     *
     * @param espec05
     * @throws Espec05NotFound
     */
    public void updateEspec05(Espec05 espec05) throws Espec05NotFound;
}
