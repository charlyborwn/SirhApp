package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Tidoc18;
import com.hrm.sirhapp.service.exception.Tidoc18AlreadyExists;
import com.hrm.sirhapp.service.exception.Tidoc18NotFound;
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
public interface Tidoc18ManagerLocal {

    /**
     *
     * @param idcat
     * @return
     * @throws Tidoc18NotFound
     */
    public Tidoc18 getTidoc18(Integer idcat) throws Tidoc18NotFound;

    /**
     *
     * @param notid
     * @return
     * @throws Tidoc18NotFound
     */
    public String getTidoc18(String notid) throws Tidoc18NotFound;

    /**
     *
     * @return
     */
    public List<Tidoc18> getListTidoc18();

    /**
     *
     * @return
     */
    public List<Tidoc18> retrieveTidoc18();

    /**
     *
     * @param tidoc18
     * @return
     * @throws Tidoc18AlreadyExists
     */
    public Tidoc18 registerTidoc18(Tidoc18 tidoc18) throws Tidoc18AlreadyExists;

    /**
     *
     * @param tidoc18
     * @throws Tidoc18NotFound
     */
    public void removeTidoc18(Tidoc18 tidoc18) throws Tidoc18NotFound;

    /**
     *
     * @param tidoc18
     * @throws Tidoc18NotFound
     */
    public void deleteTidoc18(Tidoc18 tidoc18) throws Tidoc18NotFound;

    /**
     *
     * @param tidoc18
     * @throws Tidoc18NotFound
     */
    public void updateTidoc18(Tidoc18 tidoc18) throws Tidoc18NotFound;
}
