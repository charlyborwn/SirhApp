package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Inven37;
import com.hrm.sirhapp.service.exception.Inven37AlreadyExists;
import com.hrm.sirhapp.service.exception.Inven37NotFound;
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
public interface Inven37ManagerLocal {

    /**
     *
     * @param idinv
     * @return
     * @throws Inven37NotFound
     */
    public Inven37 getInven37(Integer idinv) throws Inven37NotFound;

    /**
     *
     * @param idinv
     * @return
     */
    public List<Inven37> getListInven37(Integer idinv);
    
    /**
     *
     * @param tInven37
     * @return
     */
    public List<Inven37> getListInven37(Inven37 tInven37);

    /**
     *
     * @return
     */
    public List<Inven37> retrieveInven37();
    
    /**
     *
     * @return
     */
    public List<Inven37> retrieveInven37E();
    
    /**
     *
     * @param inven37
     * @return
     */
    public Long countOutInven37(Inven37 inven37);

    /**
     *
     * @param inven37
     * @return
     * @throws Inven37AlreadyExists
     */
    public Inven37 registerInven37(Inven37 inven37) throws Inven37AlreadyExists;

    /**
     *
     * @param inven37
     * @throws Inven37NotFound
     */
    public void removeInven37(Inven37 inven37) throws Inven37NotFound;

    /**
     *
     * @param inven37
     * @throws Inven37NotFound
     */
    public void deleteInven37(Inven37 inven37) throws Inven37NotFound;
    
    /**
     *
     * @param inven37
     * @throws Inven37NotFound
     */
    public void updateInven37(Inven37 inven37) throws Inven37NotFound;
}
