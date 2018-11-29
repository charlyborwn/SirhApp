package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Prove48;
import com.hrm.sirhapp.service.exception.Prove48AlreadyExists;
import com.hrm.sirhapp.service.exception.Prove48NotFound;
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
public interface Prove48ManagerLocal {

    /**
     *
     * @param idpro
     * @return
     * @throws Prove48NotFound
     */
    public Prove48 getProve48(Integer idpro) throws Prove48NotFound;

    /**
     *
     * @return
     */
    public List<Prove48> getListProve48();

    /**
     *
     * @param rfpro
     * @return
     */
    public List<String> getListProve48Rfpro(String rfpro);

    /**
     *
     * @return
     */
    public List<Prove48> retrieveProve48();

    /**
     *
     * @param rfpro
     * @return
     */
    public Prove48 retrieveProve48(String rfpro);

    /**
     *
     * @param prove48
     * @return
     * @throws Prove48AlreadyExists
     */
    public Prove48 registerProve48(Prove48 prove48) throws Prove48AlreadyExists;

    /**
     *
     * @param prove48
     * @throws Prove48NotFound
     */
    public void removeProve48(Prove48 prove48) throws Prove48NotFound;

    /**
     *
     * @param prove48
     * @throws Prove48NotFound
     */
    public void deleteProve48(Prove48 prove48) throws Prove48NotFound;

    /**
     *
     * @param prove48
     * @throws Prove48NotFound
     */
    public void updateProve48(Prove48 prove48) throws Prove48NotFound;
}
