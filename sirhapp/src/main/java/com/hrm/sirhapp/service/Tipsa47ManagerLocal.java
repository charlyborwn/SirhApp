package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Tipsa47;
import com.hrm.sirhapp.service.exception.Tipsa47AlreadyExists;
import com.hrm.sirhapp.service.exception.Tipsa47NotFound;
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
public interface Tipsa47ManagerLocal {

    /**
     *
     * @param idpsa
     * @return
     * @throws Tipsa47NotFound
     */
    public Tipsa47 getTipsa47(Integer idpsa) throws Tipsa47NotFound;

    /**
     *
     * @return
     */
    public List<Tipsa47> getListTipsa47();

    /**
     *
     * @return
     */
    public List<String> getTopListTipsa47();

    /**
     *
     * @param grpsa
     * @return
     */
    public List<Tipsa47> getListTipsa47(String grpsa);

    /**
     *
     * @param grpsa
     * @param cepsa
     * @return
     */
    public List<Tipsa47> getListTipsa47(String grpsa, String cepsa);

    /**
     *
     * @return
     */
    public List<Tipsa47> retrieveTipsa47();

    /**
     *
     * @param tipsa47
     * @return
     * @throws Tipsa47AlreadyExists
     */
    public Tipsa47 registerTipsa47(Tipsa47 tipsa47) throws Tipsa47AlreadyExists;

    /**
     *
     * @param grpsa
     * @param hmpsa
     * @return
     */
    public String getTipsa47Nopsa(String grpsa, String hmpsa);

    /**
     *
     * @param grpsa
     * @param hmpsa
     * @param cepsa
     * @return
     */
    public String getTipsa47Nopsa(String grpsa, String hmpsa, String cepsa);

    /**
     *
     * @param tipsa47
     * @throws Tipsa47NotFound
     */
    public void removeTipsa47(Tipsa47 tipsa47) throws Tipsa47NotFound;

    /**
     *
     * @param tipsa47
     * @throws Tipsa47NotFound
     */
    public void deleteTipsa47(Tipsa47 tipsa47) throws Tipsa47NotFound;

    /**
     *
     * @param tipsa47
     * @throws Tipsa47NotFound
     */
    public void updateTipsa47(Tipsa47 tipsa47) throws Tipsa47NotFound;
}
