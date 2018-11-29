package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Traba36a;
import com.hrm.sirhapp.service.exception.Traba36aAlreadyExists;
import com.hrm.sirhapp.service.exception.Traba36aNotFound;
import java.util.Date;
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
public interface Traba36aManagerLocal {

    /**
     *
     * @param rftraA
     * @return
     */
    public Boolean alreadyExistsInnactive(String rftraA);

    /**
     *
     * @param rftraA
     * @return
     * @throws Traba36aNotFound
     */
    public Traba36a getTraba36a(String rftraA) throws Traba36aNotFound;

    /**
     *
     * @return
     */
    public List<Traba36a> retrieveTraba36a();

    /**
     *
     * @param traba36a
     * @return
     */
    public List<Traba36a> getListTraba36aWiz(Traba36a traba36a);

    /**
     *
     * @param enterprise
     * @param startDate
     * @param endDate
     * @param reporte
     * @param gen
     * @return
     */
    public List<Traba36a> getListTraba36aGen(String enterprise, Date startDate, Date endDate, String reporte, boolean gen);

    /**
     *
     * @param enterprise
     * @param start
     * @param end
     * @param reporte
     * @param gen
     * @return
     */
    public List<Traba36a> getListTraba36aGen(String enterprise, Integer start, Integer end, String reporte, boolean gen);

    /**
     *
     * @param enterprise
     * @param period
     * @param reporte
     * @param gen
     * @return
     */
    public List<Traba36a> getListTraba36aGen(String enterprise, String period, String reporte, boolean gen);

    /**
     *
     * @param folios
     * @param reporte
     * @param gen
     * @return
     */
    public List<Traba36a> getListTraba36aGen(int[] folios, String reporte, boolean gen);

    /**
     *
     * @param rftraA
     * @param reporte
     * @param gen
     * @return
     */
    public List<Traba36a> getListTraba36aGen(String rftraA, String reporte, boolean gen);

    /**
     *
     * @param traba36a
     * @param reporte
     * @param gen
     * @return
     */
    public List<Traba36a> getListTraba36aGen(Traba36a traba36a, String reporte, boolean gen);

    /**
     *
     * @param fxtraA
     * @param reporte
     * @param gen
     * @return
     */
    public List<Traba36a> getListTraba36aGen(Integer fxtraA, String reporte, boolean gen);

    /**
     *
     * @param traba36a
     * @return
     */
    public List<Traba36a> getListTraba36a(Traba36a traba36a);

    /**
     *
     * @param rftraA
     * @return
     */
    public List<Traba36a> getListTraba36a(String rftraA);

    /**
     *
     * @param rftraA
     * @return
     */
    public List<Traba36a> getListTraba36aAll(String rftraA);

    /**
     *
     * @param traba36a
     * @return
     * @throws Traba36aAlreadyExists
     */
    public Traba36a registerTraba36a(Traba36a traba36a) throws Traba36aAlreadyExists;

    /**
     *
     * @param traba36a
     * @throws Traba36aNotFound
     */
    public void deleteTraba36a(Traba36a traba36a) throws Traba36aNotFound;

    /**
     *
     * @param traba36a
     * @throws Traba36aNotFound
     */
    public void removeTraba36a(Traba36a traba36a) throws Traba36aNotFound;

    /**
     *
     * @param traba36a
     * @throws Traba36aNotFound
     */
    public void updateTraba36a(Traba36a traba36a) throws Traba36aNotFound;
}
