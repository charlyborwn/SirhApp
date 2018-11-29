package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Infor39;
import com.hrm.sirhapp.service.exception.Infor39AlreadyExists;
import com.hrm.sirhapp.service.exception.Infor39NotFound;
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
public interface Infor39ManagerLocal {

    /**
     *
     * @param idcat
     * @return
     * @throws Infor39NotFound
     */
    public Infor39 getInfor39(Integer idcat) throws Infor39NotFound;

    /**
     *
     * @return
     */
    public List<Infor39> getListInfor39();

    /**
     *
     * @return
     */
    public List<Infor39> retrieveInfor39();

    /**
     *
     * @param infor39
     * @return
     * @throws Infor39AlreadyExists
     */
    public Infor39 registerInfor39(Infor39 infor39) throws Infor39AlreadyExists;

    /**
     *
     * @param infor39
     * @throws Infor39NotFound
     */
    public void removeInfor39(Infor39 infor39) throws Infor39NotFound;

    /**
     *
     * @param infor39
     * @throws Infor39NotFound
     */
    public void deleteInfor39(Infor39 infor39) throws Infor39NotFound;

    /**
     *
     * @param infor39
     * @throws Infor39NotFound
     */
    public void updateInfor39(Infor39 infor39) throws Infor39NotFound;
}
