package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Tiinf38;
import com.hrm.sirhapp.service.exception.Tiinf38AlreadyExists;
import com.hrm.sirhapp.service.exception.Tiinf38NotFound;
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
public interface Tiinf38ManagerLocal {

    /**
     *
     * @param idtin
     * @return
     * @throws Tiinf38NotFound
     */
    public Tiinf38 getTiinf38(Integer idtin) throws Tiinf38NotFound;

    /**
     *
     * @return
     */
    public List<Tiinf38> getListTiinf38();

    /**
     *
     * @return
     */
    public List<Tiinf38> retrieveTiinf38();

    /**
     *
     * @param tiinf38
     * @return
     * @throws Tiinf38AlreadyExists
     */
    public Tiinf38 registerTiinf38(Tiinf38 tiinf38) throws Tiinf38AlreadyExists;

    /**
     *
     * @param tiinf38
     * @throws Tiinf38NotFound
     */
    public void removeTiinf38(Tiinf38 tiinf38) throws Tiinf38NotFound;
    
    /**
     *
     * @param tiinf38
     * @throws Tiinf38NotFound
     */
    public void deleteTiinf38(Tiinf38 tiinf38) throws Tiinf38NotFound;

    /**
     *
     * @param tiinf38
     * @throws Tiinf38NotFound
     */
    public void updateTiinf38(Tiinf38 tiinf38) throws Tiinf38NotFound;
}
