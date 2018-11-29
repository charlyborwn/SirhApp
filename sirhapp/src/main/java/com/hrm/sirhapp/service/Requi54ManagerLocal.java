package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Requi54;
import com.hrm.sirhapp.service.exception.Requi54AlreadyExists;
import com.hrm.sirhapp.service.exception.Requi54NotFound;
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
public interface Requi54ManagerLocal {

    /**
     *
     * @param rfreq
     * @return
     * @throws Requi54NotFound
     */
    public Requi54 getRequi54(String rfreq) throws Requi54NotFound;

    /**
     *
     * @param idreq
     * @return
     * @throws Requi54NotFound
     */
    public Requi54 getRequi54ById(Integer idreq) throws Requi54NotFound;

    /**
     *
     * @return
     */
    public List<Requi54> retrieveRequi54();

    /**
     *
     * @param requi54
     * @return
     * @throws Requi54AlreadyExists
     */
    public Requi54 registerRequi54(Requi54 requi54) throws Requi54AlreadyExists;

    /**
     *
     * @param requi54
     * @throws Requi54NotFound
     */
    public void removeRequi54(Requi54 requi54) throws Requi54NotFound;

    /**
     *
     * @param requi54
     * @throws Requi54NotFound
     */
    public void deleteRequi54(Requi54 requi54) throws Requi54NotFound;

    /**
     *
     * @param requi54
     * @throws Requi54NotFound
     */
    public void updateRequi54(Requi54 requi54) throws Requi54NotFound;
}
