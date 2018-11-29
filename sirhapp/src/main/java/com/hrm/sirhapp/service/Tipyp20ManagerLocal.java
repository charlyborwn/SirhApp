package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Tipyp20;
import com.hrm.sirhapp.service.exception.Tipyp20AlreadyExists;
import com.hrm.sirhapp.service.exception.Tipyp20NotFound;
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
public interface Tipyp20ManagerLocal {

    /**
     *
     * @param idpyp
     * @return
     * @throws Tipyp20NotFound
     */
    public Tipyp20 getTipyp20(Integer idpyp) throws Tipyp20NotFound;

    /**
     *
     * @return
     */
    public List<Tipyp20> getListTipyp20();

    /**
     *
     * @return
     */
    public List<String> getTopListTipyp20();

    /**
     *
     * @param grpyp
     * @return
     */
    public List<Tipyp20> getListTipyp20(String grpyp);

    /**
     *
     * @param grpyp
     * @param cepyp
     * @return
     */
    public List<Tipyp20> getListTipyp20(String grpyp, String cepyp);

    /**
     *
     * @param grpyp
     * @param hmpyp
     * @return
     */
    public String getTipyp20Nopyp(String grpyp, String hmpyp);

    /**
     *
     * @return
     */
    public List<Tipyp20> retrieveTipyp20();

    /**
     *
     * @param tipyp20
     * @return
     * @throws Tipyp20AlreadyExists
     */
    public Tipyp20 registerTipyp20(Tipyp20 tipyp20) throws Tipyp20AlreadyExists;

    /**
     *
     * @param tipyp20
     * @throws Tipyp20NotFound
     */
    public void removeTipyp20(Tipyp20 tipyp20) throws Tipyp20NotFound;

    /**
     *
     * @param tipyp20
     * @throws Tipyp20NotFound
     */
    public void deleteTipyp20(Tipyp20 tipyp20) throws Tipyp20NotFound;

    /**
     *
     * @param tipyp20
     * @throws Tipyp20NotFound
     */
    public void updateTipyp20(Tipyp20 tipyp20) throws Tipyp20NotFound;
}
