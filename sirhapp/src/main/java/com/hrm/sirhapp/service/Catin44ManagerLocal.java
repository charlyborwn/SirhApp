package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Catin44;
import com.hrm.sirhapp.service.exception.Catin44AlreadyExists;
import com.hrm.sirhapp.service.exception.Catin44NotFound;
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
public interface Catin44ManagerLocal {

    /**
     *
     * @param idcin
     * @return
     * @throws Catin44NotFound
     */
    public Catin44 getCatin44(Integer idcin) throws Catin44NotFound;

    /**
     *
     * @param arcin
     * @return
     */
    public List<String> getListCatin44Arcin(String arcin);

    /**
     *
     * @param arcin
     * @param macin
     * @return
     */
    public List<String> getListCatin44Macin(String arcin, String macin);

    /**
     *
     * @param arcin
     * @param macin
     * @param mocin
     * @return
     */
    public List<String> getListCatin44Mocin(String arcin, String macin, String mocin);

    /**
     *
     * @param arcin
     * @param macin
     * @param mocin
     * @param cocin
     * @return
     */
    public List<String> getListCatin44Cocin(String arcin, String macin, String mocin, String cocin);

    /**
     *
     * @param arcin
     * @param macin
     * @param mocin
     * @param cocin
     * @param tacin
     * @return
     */
    public List<String> getListCatin44Tacin(String arcin, String macin, String mocin, String cocin, String tacin);

    /**
     *
     * @return
     */
    public List<Catin44> getListCatin44();

    /**
     *
     * @return
     */
    public List<Catin44> retrieveCatin44();

    /**
     *
     * @param catin44
     * @return
     * @throws Catin44AlreadyExists
     */
    public Catin44 registerCatin44(Catin44 catin44) throws Catin44AlreadyExists;

    /**
     *
     * @param catin44
     * @throws Catin44NotFound
     */
    public void removeCatin44(Catin44 catin44) throws Catin44NotFound;

    /**
     *
     * @param catin44
     * @throws Catin44NotFound
     */
    public void deleteCatin44(Catin44 catin44) throws Catin44NotFound;

    /**
     *
     * @param catin44
     * @throws Catin44NotFound
     */
    public void updateCatin44(Catin44 catin44) throws Catin44NotFound;
}
