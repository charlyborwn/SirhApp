package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Tapri16;
import com.hrm.sirhapp.service.exception.Tapri16AlreadyExists;
import com.hrm.sirhapp.service.exception.Tapri16NotFound;
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
public interface Tapri16ManagerLocal {

    /**
     *
     * @param idtap
     * @return
     * @throws Tapri16NotFound
     */
    public Tapri16 getTapri16(Integer idtap) throws Tapri16NotFound;

    /**
     *
     * @return
     */
    public List<Tapri16> getListTapri16();

    /**
     *
     * @return
     */
    public List<String> getTopListTapri16();

    /**
     *
     * @param grtap
     * @return
     */
    public List<Tapri16> getListTapri16(String grtap);

    /**
     *
     * @param grtap
     * @param hmtap
     * @return
     */
    public String getTapri16Detap(String grtap, String hmtap);

    /**
     *
     * @return
     */
    public List<Tapri16> retrieveTapri16();

    /**
     *
     * @param tapri16
     * @return
     * @throws Tapri16AlreadyExists
     */
    public Tapri16 registerTapri16(Tapri16 tapri16) throws Tapri16AlreadyExists;

    /**
     *
     * @param tapri16
     * @throws Tapri16NotFound
     */
    public void removeTapri16(Tapri16 tapri16) throws Tapri16NotFound;

    /**
     *
     * @param tapri16
     * @throws Tapri16NotFound
     */
    public void deleteTapri16(Tapri16 tapri16) throws Tapri16NotFound;

    /**
     *
     * @param tapri16
     * @throws Tapri16NotFound
     */
    public void updateTapri16(Tapri16 tapri16) throws Tapri16NotFound;
}
