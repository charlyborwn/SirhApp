package com.hrm.sirhapp.service;

import java.math.BigInteger;
import javax.ejb.Local;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Local
public interface Seqen55ManagerLocal {

    /**
     *
     * @param sequenceName
     * @return
     */
    public BigInteger getSeqen55(String sequenceName);

}
