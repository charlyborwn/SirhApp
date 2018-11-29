package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Docum29a;
import com.hrm.sirhapp.service.exception.Docum29aAlreadyExists;
import com.hrm.sirhapp.service.exception.Docum29aNotFound;
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
public interface Docum29aManagerLocal {

    /**
     *
     * @param iddocA
     * @return
     * @throws Docum29aNotFound
     */
    public Docum29a getDocum29a(Integer iddocA) throws Docum29aNotFound;

    /**
     *
     * @param rfdocA
     * @return
     */
    public List<Docum29a> getListDocum29a(String rfdocA);

    /**
     *
     * @return
     */
    public List<Docum29a> retrieveDocum29a();

    /**
     *
     * @param docum29a
     * @return
     * @throws Docum29aAlreadyExists
     */
    public Docum29a registerDocum29a(Docum29a docum29a) throws Docum29aAlreadyExists;

    /**
     *
     * @param docum29a
     * @throws Docum29aNotFound
     */
    public void deleteDocum29a(Docum29a docum29a) throws Docum29aNotFound;

    /**
     *
     * @param docum29a
     * @throws Docum29aNotFound
     */
    public void removeDocum29a(Docum29a docum29a) throws Docum29aNotFound;

    /**
     *
     * @param docum29a
     * @throws Docum29aNotFound
     */
    public void updateDocum29a(Docum29a docum29a) throws Docum29aNotFound;

}
