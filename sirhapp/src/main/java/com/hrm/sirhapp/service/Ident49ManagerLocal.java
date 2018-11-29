package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Ident49;
import com.hrm.sirhapp.service.exception.Ident49AlreadyExists;
import com.hrm.sirhapp.service.exception.Ident49NotFound;
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
public interface Ident49ManagerLocal {

    /**
     *
     * @param ntide
     * @return
     */
    public Boolean alreadyExistsInnactive(Integer ntide);

    /**
     *
     * @param ntide
     * @return
     * @throws Ident49NotFound
     */
    public Ident49 getIdent49(Integer ntide) throws Ident49NotFound;

    /**
     *
     * @param ntide
     * @return
     * @throws Ident49NotFound
     */
    public Ident49 getIdent49ById(Integer ntide) throws Ident49NotFound;

    /**
     *
     * @return
     */
    public List<Ident49> getListIdent49();

    /**
     *
     * @return
     */
    public List<Ident49> retrieveIdent49();

    /**
     *
     * @param ident49
     * @return
     * @throws Ident49AlreadyExists
     */
    public Ident49 registerIdent49(Ident49 ident49) throws Ident49AlreadyExists;

    /**
     *
     * @param ident49
     * @throws Ident49NotFound
     */
    public void removeIdent49(Ident49 ident49) throws Ident49NotFound;

    /**
     *
     * @param ident49
     * @throws Ident49NotFound
     */
    public void deleteIdent49(Ident49 ident49) throws Ident49NotFound;

    /**
     *
     * @param ident49
     * @throws Ident49NotFound
     */
    public void updateIdent49(Ident49 ident49) throws Ident49NotFound;
}
