package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Contr27;
import com.hrm.sirhapp.service.exception.Contr27AlreadyExists;
import com.hrm.sirhapp.service.exception.Contr27NotFound;
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
public interface Contr27ManagerLocal {

    /**
     *
     * @param nucto
     * @return
     */
    public Integer getNtctoNucto(String nucto);

    /**
     *
     * @param idcto
     * @return
     */
    public Boolean alreadyExistsInnactive(Integer idcto);

    /**
     *
     * @param idcto
     * @return
     * @throws Contr27NotFound
     */
    public Contr27 getContr27(Integer idcto) throws Contr27NotFound;

    /**
     *
     * @return
     */
    public List<Contr27> retrieveContr27();

    /**
     *
     * @param rfcto
     * @return
     */
    public List<Contr27> getListContr27Rfcto(String rfcto);

    /**
     *
     * @param ntcto
     * @return
     */
    public List<Contr27> getListContr27Ntcto(Integer ntcto);

    /**
     *
     * @param nucto
     * @return
     */
    public List<Contr27> getListContr27Nucto(String nucto);

    /**
     *
     * @param idcto
     * @return
     */
    public List<Contr27> getListContr27Idcto(Integer idcto);

    /**
     *
     * @param contr27
     * @return
     */
    public List<Contr27> getListContr27(Contr27 contr27);

    /**
     *
     * @param contr27
     * @return
     * @throws Contr27AlreadyExists
     */
    public Contr27 registerContr27(Contr27 contr27) throws Contr27AlreadyExists;

    /**
     *
     * @param contr27
     * @throws Contr27NotFound
     */
    public void removeContr27(Contr27 contr27) throws Contr27NotFound;

    /**
     *
     * @param contr27
     * @return
     * @throws Contr27NotFound
     */
    public Contr27 deleteContr27(Contr27 contr27) throws Contr27NotFound;

    /**
     *
     * @param contr27
     * @return
     * @throws Contr27NotFound
     */
    public Contr27 updateContr27(Contr27 contr27) throws Contr27NotFound;
}
