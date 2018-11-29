package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Categ01;
import com.hrm.sirhapp.service.exception.Categ01AlreadyExists;
import com.hrm.sirhapp.service.exception.Categ01NotFound;
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
public interface Categ01ManagerLocal {

    /**
     *
     * @param idcat
     * @return
     * @throws Categ01NotFound
     */
    public Categ01 getCateg01(Integer idcat) throws Categ01NotFound;

    /**
     *
     * @param cvcat
     * @param cecat
     * @return
     * @throws Categ01NotFound
     */
    public Categ01 getCateg01(String cvcat,String cecat) throws Categ01NotFound;

    /**
     *
     * @return
     */
    public List<Categ01> getListCateg01();
    
    /**
     *
     * @param cvcat
     * @return
     */
    public List<Categ01> getListCateg01(String cvcat);

    /**
     *
     * @return
     */
    public List<Categ01> retrieveCateg01();

    /**
     *
     * @param categ01
     * @return
     * @throws Categ01AlreadyExists
     */
    public Categ01 registerCateg01(Categ01 categ01) throws Categ01AlreadyExists;

    /**
     *
     * @param categ01
     * @throws Categ01NotFound
     */
    public void deleteCateg01(Categ01 categ01) throws Categ01NotFound;

    /**
     *
     * @param categ01
     * @throws Categ01NotFound
     */
    public void removeCateg01(Categ01 categ01) throws Categ01NotFound;

    /**
     *
     * @param categ01
     * @throws Categ01NotFound
     */
    public void updateCateg01(Categ01 categ01) throws Categ01NotFound;
}
