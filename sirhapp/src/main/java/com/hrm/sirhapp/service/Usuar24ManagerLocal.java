package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Usuar24;
import com.hrm.sirhapp.service.exception.Usuar24AlreadyExists;
import com.hrm.sirhapp.service.exception.Usuar24NotFound;
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
public interface Usuar24ManagerLocal {

    /**
     *
     * @param cvusu
     * @return
     */
    public Boolean alreadyExistsInnactive(String cvusu);

    /**
     *
     * @param cvusu
     * @return
     * @throws Usuar24NotFound
     */
    public Usuar24 getUsuar24(String cvusu) throws Usuar24NotFound;

    /**
     *
     * @param cousu
     * @return
     * @throws Usuar24NotFound
     */
    public Usuar24 getUsuar24Email(String cousu) throws Usuar24NotFound;

    /**
     *
     * @return
     */
    public List<Usuar24> getListUsuar24();

    /**
     *
     * @return
     */
    public List<Usuar24> getListInnactiveUsuar24();

    /**
     *
     * @param usuar24
     * @return
     * @throws Usuar24AlreadyExists
     */
    public Usuar24 registerUsuar24(Usuar24 usuar24) throws Usuar24AlreadyExists;

    /**
     *
     * @param usuar24
     * @throws Usuar24NotFound
     */
    public void removeUsuar24(Usuar24 usuar24) throws Usuar24NotFound;

    /**
     *
     * @param usuar24
     * @throws Usuar24NotFound
     */
    public void deleteUsuar24(Usuar24 usuar24) throws Usuar24NotFound;

    /**
     *
     * @param usuar24
     * @return
     * @throws Usuar24NotFound
     */
    public Usuar24 updateUsuar24(Usuar24 usuar24) throws Usuar24NotFound;
}
