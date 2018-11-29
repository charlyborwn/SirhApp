package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Civil06;
import com.hrm.sirhapp.service.exception.Civil06AlreadyExists;
import com.hrm.sirhapp.service.exception.Civil06NotFound;
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
public interface Civil06ManagerLocal {

    /**
     *
     * @param idcat
     * @return
     * @throws Civil06NotFound
     */
    public Civil06 getCivil06(Integer idcat) throws Civil06NotFound;

    /**
     *
     * @return
     */
    public List<Civil06> getListCivil06();

    /**
     *
     * @return
     */
    public List<Civil06> retrieveCivil06();

    /**
     *
     * @param civil06
     * @return
     * @throws Civil06AlreadyExists
     */
    public Civil06 registerCivil06(Civil06 civil06) throws Civil06AlreadyExists;

    /**
     *
     * @param civil06
     * @throws Civil06NotFound
     */
    public void deleteCivil06(Civil06 civil06) throws Civil06NotFound;

    /**
     *
     * @param civil06
     * @throws Civil06NotFound
     */
    public void removeCivil06(Civil06 civil06) throws Civil06NotFound;

    /**
     *
     * @param civil06
     * @throws Civil06NotFound
     */
    public void updateCivil06(Civil06 civil06) throws Civil06NotFound;
}
