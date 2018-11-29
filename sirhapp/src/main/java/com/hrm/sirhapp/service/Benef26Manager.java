package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Benef26;
import com.hrm.sirhapp.service.exception.Benef26AlreadyExists;
import com.hrm.sirhapp.service.exception.Benef26NotFound;
import com.hrm.sirhapp.util.FacesUtil;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
public class Benef26Manager implements Benef26ManagerLocal {

    private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(Traba36aManager.class);

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idben
     * @return
     * @throws Benef26NotFound
     */
    @Override
    public Benef26 getBenef26(Integer idben) throws Benef26NotFound {
        Query query = em.createQuery("SELECT benef26 FROM Benef26 benef26 WHERE "
                + "benef26.idben = :idben");

        query.setParameter("idben", idben);

        Benef26 benef26Info;

        try {
            benef26Info = (Benef26) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Benef26NotFound();
        }

        Benef26 benef26 = benef26Info;

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + benef26.getIdben() + " | SEL: " + benef26.toString() + " | ACT: " + this.getClass().getName() + ".getBenef26");

        return benef26;
    }

    /**
     *
     * @param ntben
     * @return
     */
    @Override
    public Float getPercentage(Integer ntben) {
        Query query = em.createQuery("select SUM(benef26.poben) from Benef26 benef26 "
                + "where benef26.stben='SI' and benef26.ntben = :ntben");

        query.setParameter("ntben", ntben);

        Double result = (Double) query.getSingleResult();

        if (result == null) {
            return new Float(100);
        } else {
            result = 100 - (result * 100);
        }
        return result.floatValue();
    }

    /**
     *
     * @param ntben
     * @return
     */
    @Override
    public List<Benef26> getListBenef26(Integer ntben) {
        Query query = em.createQuery("select benef26 from Benef26 benef26 "
                + "where benef26.stben='SI' and benef26.ntben = :ntben", Benef26.class);

        query.setParameter("ntben", ntben);

        List<Benef26> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Benef26>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Benef26> retrieveBenef26() {
        Query query = em.createQuery("select benef26 from Benef26 benef26", Benef26.class);

        List<Benef26> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Benef26>();
        }

        return result;
    }

    /**
     *
     * @param benef26
     * @return
     * @throws Benef26AlreadyExists
     */
    @Override
    public Benef26 registerBenef26(Benef26 benef26) throws Benef26AlreadyExists {

        em.persist(benef26);
        em.flush();

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + benef26.getIdben() + " | NEW: " + benef26.toString() + " | ACT: " + this.getClass().getName() + ".registerBenef26");

        return benef26;
    }

    /**
     *
     * @param benef26
     * @throws Benef26NotFound
     */
    @Override
    public void deleteBenef26(Benef26 benef26) throws Benef26NotFound {
        Benef26 updatableBenef26 = em.find(Benef26.class, benef26.getIdben());

        if (updatableBenef26 == null) {
            throw new Benef26NotFound();
        }

        em.merge(benef26);
        em.flush();

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + benef26.getIdben() + " | UPD: " + updatableBenef26.toString() + " | ACT: " + this.getClass().getName() + ".deleteBenef26");
        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + benef26.getIdben() + " | DEL: " + benef26.toString() + " | ACT: " + this.getClass().getName() + ".deleteBenef26");

    }

    /**
     *
     * @param benef26
     * @throws Benef26NotFound
     */
    @Override
    public void removeBenef26(Benef26 benef26) throws Benef26NotFound {
        Benef26 updatableBenef26 = em.find(Benef26.class, benef26.getIdben());

        if (updatableBenef26 == null) {
            throw new Benef26NotFound();
        }

        if (!em.contains(benef26)) {
            benef26 = em.merge(benef26);
        }

        em.remove(benef26);
        em.flush();

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + benef26.getIdben() + " | REM: " + benef26.toString() + " | ACT: " + this.getClass().getName() + ".removeBenef26");

    }

    /**
     *
     * @param benef26
     * @throws Benef26NotFound
     */
    @Override
    public void updateBenef26(Benef26 benef26) throws Benef26NotFound {
        Benef26 updatableBenef26 = em.find(Benef26.class, benef26.getIdben());

        if (updatableBenef26 == null) {
            throw new Benef26NotFound();
        }

        em.merge(benef26);
        em.flush();

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + benef26.getIdben() + " | UPD: " + updatableBenef26.toString() + " | ACT: " + this.getClass().getName() + ".updateBenef26");
        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + benef26.getIdben() + " | DAT: " + benef26.toString() + " | ACT: " + this.getClass().getName() + ".updateBenef26");

    }

}
