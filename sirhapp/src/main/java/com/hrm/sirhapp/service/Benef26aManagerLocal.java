package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Benef26a;
import com.hrm.sirhapp.service.exception.Benef26aAlreadyExists;
import com.hrm.sirhapp.service.exception.Benef26aNotFound;
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
public interface Benef26aManagerLocal {

    /**
     *
     * @param idbenA
     * @return
     * @throws Benef26aNotFound
     */
    public Benef26a getBenef26a(Long idbenA) throws Benef26aNotFound;

    /**
     *
     * @param rfbenA
     * @return
     */
    public Float getPercentage(String rfbenA);

    /**
     *
     * @param rfbenA
     * @return
     */
    public List<Benef26a> getListBenef26a(String rfbenA);

    /**
     *
     * @return
     */
    public List<Benef26a> retrieveBenef26a();

    /**
     *
     * @param benef26a
     * @return
     * @throws Benef26aAlreadyExists
     */
    public Benef26a registerBenef26a(Benef26a benef26a) throws Benef26aAlreadyExists;

    /**
     *
     * @param benef26a
     * @throws Benef26aNotFound
     */
    public void deleteBenef26a(Benef26a benef26a) throws Benef26aNotFound;

    /**
     *
     * @param benef26a
     * @throws Benef26aNotFound
     */
    public void removeBenef26a(Benef26a benef26a) throws Benef26aNotFound;

    /**
     *
     * @param benef26a
     * @throws Benef26aNotFound
     */
    public void updateBenef26a(Benef26a benef26a) throws Benef26aNotFound;
}
