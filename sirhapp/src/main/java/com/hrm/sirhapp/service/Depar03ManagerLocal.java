package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Depar03;
import com.hrm.sirhapp.service.exception.Depar03AlreadyExists;
import com.hrm.sirhapp.service.exception.Depar03NotFound;
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
public interface Depar03ManagerLocal {

    /**
     *
     * @param iddep
     * @return
     * @throws Depar03NotFound
     */
    public Depar03 getDepar03(Integer iddep) throws Depar03NotFound;

    /**
     *
     * @param nudep
     * @return
     * @throws Depar03NotFound
     */
    public Depar03 getDepar03(String nudep) throws Depar03NotFound;

    /**
     *
     * @param cedep
     * @param nudep
     * @return
     * @throws Depar03NotFound
     */
    public Depar03 getDepar03(String cedep, String nudep) throws Depar03NotFound;

    /**
     *
     * @param cedep
     * @param sedep
     * @param nudep
     * @return
     * @throws Depar03NotFound
     */
    public Depar03 getDepar03(String cedep, String sedep, String nudep) throws Depar03NotFound;

    /**
     *
     * @return
     */
    public List<Depar03> getListDepar03();

    /**
     *
     * @return
     */
    public List<Depar03> retrieveDepar03();

    /**
     *
     * @param depar03
     * @return
     * @throws Depar03AlreadyExists
     */
    public Depar03 registerDepar03(Depar03 depar03) throws Depar03AlreadyExists;

    /**
     *
     * @param depar03
     * @throws Depar03NotFound
     */
    public void removeDepar03(Depar03 depar03) throws Depar03NotFound;

    /**
     *
     * @param depar03
     * @throws Depar03NotFound
     */
    public void deleteDepar03(Depar03 depar03) throws Depar03NotFound;

    /**
     *
     * @param depar03
     * @throws Depar03NotFound
     */
    public void updateDepar03(Depar03 depar03) throws Depar03NotFound;
}
