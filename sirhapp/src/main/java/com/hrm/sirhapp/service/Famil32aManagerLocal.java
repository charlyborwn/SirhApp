package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Famil32a;
import com.hrm.sirhapp.service.exception.Famil32aAlreadyExists;
import com.hrm.sirhapp.service.exception.Famil32aNotFound;
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
public interface Famil32aManagerLocal {

    /**
     *
     * @param idfamA
     * @return
     * @throws Famil32aNotFound
     */
    public Famil32a getFamil32a(Integer idfamA) throws Famil32aNotFound;

    /**
     *
     * @param rffamA
     * @return
     */
    public List<Famil32a> getListFamil32a(String rffamA);

    /**
     *
     * @return
     */
    public List<Famil32a> retrieveFamil32a();

    /**
     *
     * @param famil32a
     * @return
     * @throws Famil32aAlreadyExists
     */
    public Famil32a registerFamil32a(Famil32a famil32a) throws Famil32aAlreadyExists;

    /**
     *
     * @param famil32a
     * @throws Famil32aNotFound
     */
    public void deleteFamil32a(Famil32a famil32a) throws Famil32aNotFound;

    /**
     *
     * @param famil32a
     * @throws Famil32aNotFound
     */
    public void removeFamil32a(Famil32a famil32a) throws Famil32aNotFound;

    /**
     *
     * @param famil32a
     * @throws Famil32aNotFound
     */
    public void updateFamil32a(Famil32a famil32a) throws Famil32aNotFound;
}
