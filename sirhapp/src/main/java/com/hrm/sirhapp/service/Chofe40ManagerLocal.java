package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Chofe40;
import com.hrm.sirhapp.service.exception.Chofe40AlreadyExists;
import com.hrm.sirhapp.service.exception.Chofe40NotFound;
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
public interface Chofe40ManagerLocal {

    /**
     *
     * @param idcho
     * @return
     * @throws Chofe40NotFound
     */
    public Chofe40 getChofe40(Integer idcho) throws Chofe40NotFound;

    /**
     *
     * @return
     */
    public List<Chofe40> getListChofe40();

    /**
     *
     * @return
     */
    public List<Chofe40> retrieveChofe40();

    /**
     *
     * @param chofe40
     * @return
     * @throws Chofe40AlreadyExists
     */
    public Chofe40 registerChofe40(Chofe40 chofe40) throws Chofe40AlreadyExists;

    /**
     *
     * @param chofe40
     * @throws Chofe40NotFound
     */
    public void deleteChofe40(Chofe40 chofe40) throws Chofe40NotFound;

    /**
     *
     * @param chofe40
     * @throws Chofe40NotFound
     */
    public void removeChofe40(Chofe40 chofe40) throws Chofe40NotFound;

    /**
     *
     * @param chofe40
     * @throws Chofe40NotFound
     */
    public void updateChofe40(Chofe40 chofe40) throws Chofe40NotFound;
}
