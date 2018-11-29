package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Docum29;
import com.hrm.sirhapp.service.exception.Docum29AlreadyExists;
import com.hrm.sirhapp.service.exception.Docum29NotFound;
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
public interface Docum29ManagerLocal {

    /**
     *
     * @param ntdat
     * @return
     * @throws Docum29NotFound
     */
    public Docum29 getDocum29(Integer ntdat) throws Docum29NotFound;

    /**
     *
     * @param ntdoc
     * @return
     */
    public List<Docum29> getListDocum29(Integer ntdoc);

    /**
     *
     * @return
     */
    public List<Docum29> retrieveDocum29();

    /**
     *
     * @param docum29
     * @return
     * @throws Docum29AlreadyExists
     */
    public Docum29 registerDocum29(Docum29 docum29) throws Docum29AlreadyExists;

    /**
     *
     * @param docum29
     * @throws Docum29NotFound
     */
    public void removeDocum29(Docum29 docum29) throws Docum29NotFound;

    /**
     *
     * @param docum29
     * @throws Docum29NotFound
     */
    public void deleteDocum29(Docum29 docum29) throws Docum29NotFound;

    /**
     *
     * @param docum29
     * @throws Docum29NotFound
     */
    public void updateDocum29(Docum29 docum29) throws Docum29NotFound;
}
