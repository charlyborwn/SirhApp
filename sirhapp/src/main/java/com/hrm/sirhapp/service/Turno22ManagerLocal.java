package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Turno22;
import com.hrm.sirhapp.service.exception.Turno22AlreadyExists;
import com.hrm.sirhapp.service.exception.Turno22NotFound;
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
public interface Turno22ManagerLocal {

    /**
     *
     * @param idtur
     * @return
     * @throws Turno22NotFound
     */
    public Turno22 getTurno22(Integer idtur) throws Turno22NotFound;
    
    /**
     *
     * @param cvtur
     * @return
     * @throws Turno22NotFound
     */
    public Turno22 getTurno22(String cvtur) throws Turno22NotFound;

    /**
     *
     * @return
     */
    public List<Turno22> getListTurno22();

    /**
     *
     * @return
     */
    public List<Turno22> retrieveTurno22();

    /**
     *
     * @param turno22
     * @return
     * @throws Turno22AlreadyExists
     */
    public Turno22 registerTurno22(Turno22 turno22) throws Turno22AlreadyExists;

    /**
     *
     * @param turno22
     * @throws Turno22NotFound
     */
    public void removeTurno22(Turno22 turno22) throws Turno22NotFound;

    /**
     *
     * @param turno22
     * @throws Turno22NotFound
     */
    public void deleteTurno22(Turno22 turno22) throws Turno22NotFound;

    /**
     *
     * @param turno22
     * @throws Turno22NotFound
     */
    public void updateTurno22(Turno22 turno22) throws Turno22NotFound;
}
