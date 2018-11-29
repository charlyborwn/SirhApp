package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Exper31a;
import com.hrm.sirhapp.service.exception.Exper31aAlreadyExists;
import com.hrm.sirhapp.service.exception.Exper31aNotFound;
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
public interface Exper31aManagerLocal {

    /**
     *
     * @param idexpA
     * @return
     * @throws Exper31aNotFound
     */
    public Exper31a getExper31a(Integer idexpA) throws Exper31aNotFound;

    /**
     *
     * @param rfexpA
     * @return
     */
    public List<Exper31a> getListExper31a(String rfexpA);

    /**
     *
     * @return
     */
    public List<Exper31a> retrieveExper31a();

    /**
     *
     * @param exper31a
     * @return
     * @throws Exper31aAlreadyExists
     */
    public Exper31a registerExper31a(Exper31a exper31a) throws Exper31aAlreadyExists;

    /**
     *
     * @param exper31a
     * @throws Exper31aNotFound
     */
    public void deleteExper31a(Exper31a exper31a) throws Exper31aNotFound;

    /**
     *
     * @param exper31a
     * @throws Exper31aNotFound
     */
    public void removeExper31a(Exper31a exper31a) throws Exper31aNotFound;

    /**
     *
     * @param exper31a
     * @throws Exper31aNotFound
     */
    public void updateExper31a(Exper31a exper31a) throws Exper31aNotFound;
}
