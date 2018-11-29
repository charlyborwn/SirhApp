package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Seqen55;
import java.math.BigInteger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Stateless
public class Seqen55Manager implements Seqen55ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param sequenceName
     * @return
     */
    @Override
    public BigInteger getSeqen55(String sequenceName) {
        Query query = em.createQuery("SELECT seqen55 FROM Seqen55 seqen55 "
                + "WHERE seqen55.sequenceName = :sequenceName");

        query.setParameter("sequenceName", sequenceName);

        Seqen55 seqen55Info;

        seqen55Info = (Seqen55) query.getSingleResult();
        BigInteger returnValue = seqen55Info.getSequenceCurValue();
        seqen55Info = em.find(Seqen55.class, seqen55Info.getSequenceName());

        if (seqen55Info == null) {
            return returnValue;
        }
        Seqen55 updateSeqen55 = new Seqen55();
        
        BigInteger i = new BigInteger("1");
        updateSeqen55.setSequenceName(sequenceName);
        updateSeqen55.setSequenceCurValue(returnValue.add(i));

        updateSeqen55 = em.merge(updateSeqen55);
        em.flush();

        return returnValue;
    }

}
