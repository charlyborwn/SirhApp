package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Benef26a;
import com.hrm.sirhapp.service.exception.Benef26aAlreadyExists;
import com.hrm.sirhapp.service.exception.Benef26aNotFound;
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
public class Benef26aManager implements Benef26aManagerLocal {

    private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(Benef26aManager.class);

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param id
     * @return
     * @throws Benef26aNotFound
     */
    @Override
    public Benef26a getBenef26a(Long id) throws Benef26aNotFound {
        Query query = em.createQuery("SELECT benef26a FROM Benef26a benef26a WHERE "
                + "benef26a.id = :id");

        query.setParameter("id", id);

        Benef26a benef26aInfo;

        try {
            benef26aInfo = (Benef26a) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Benef26aNotFound();
        }

        Benef26a benef26a = benef26aInfo;

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + benef26a.getId() + " | SEL: " + benef26a.toString() + " | ACT: " + this.getClass().getName() + ".benef26a");

        return benef26a;
    }

    /**
     *
     * @param rfbenA
     * @return
     */
    @Override
    public Float getPercentage(String rfbenA) {
        Query query = em.createQuery("select SUM(benef26a.pobenA) from Benef26a benef26a "
                + "where benef26a.stbenA='SI' and benef26a.rfbenA = :rfbenA");

        query.setParameter("rfbenA", rfbenA);

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
     * @param rfbenA
     * @return
     */
    @Override
    public List<Benef26a> getListBenef26a(String rfbenA
    ) {
        Query query = em.createQuery("select benef26a from Benef26a benef26a "
                + "where benef26a.stbenA='SI' and benef26a.rfbenA = :rfbenA", Benef26a.class);

        query.setParameter("rfbenA", rfbenA);

        List<Benef26a> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Benef26a>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Benef26a> retrieveBenef26a() {
        Query query = em.createQuery("select benef26a from Benef26a benef26a", Benef26a.class);

        List<Benef26a> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Benef26a>();
        }

        return result;
    }

    /**
     *
     * @param benef26a
     * @return
     * @throws Benef26aAlreadyExists
     */
    @Override
    public Benef26a registerBenef26a(Benef26a benef26a) throws Benef26aAlreadyExists {

        em.persist(benef26a);
        em.flush();

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + benef26a.getId() + " | NEW: " + benef26a.toString() + " | ACT: " + this.getClass().getName() + ".registerBenef26a");

        return benef26a;
    }

    /**
     *
     * @param benef26a
     * @throws Benef26aNotFound
     */
    @Override
    public void removeBenef26a(Benef26a benef26a) throws Benef26aNotFound {
        Benef26a updatableBenef26a = em.find(Benef26a.class, benef26a.getId());

        if (updatableBenef26a == null) {
            throw new Benef26aNotFound();
        }
        
        if (!em.contains(benef26a)) {
            benef26a = em.merge(benef26a);
        }

        em.remove(benef26a);
        em.flush();

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + benef26a.getId() + " | REM: " + benef26a.toString() + " | ACT: " + this.getClass().getName() + ".deleteBenef26a");

    }

    /**
     *
     * @param benef26a
     * @throws Benef26aNotFound
     */
    @Override
    public void deleteBenef26a(Benef26a benef26a) throws Benef26aNotFound {
        Benef26a updatableBenef26a = em.find(Benef26a.class, benef26a.getId());

        if (updatableBenef26a == null) {
            throw new Benef26aNotFound();
        }

        em.merge(benef26a);
        em.flush();

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + benef26a.getId() + " | UPD: " + updatableBenef26a.toString() + " | ACT: " + this.getClass().getName() + ".deleteBenef26a");
        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + benef26a.getId() + " | DEL: " + benef26a.toString() + " | ACT: " + this.getClass().getName() + ".deleteBenef26a");

    }

    /**
     *
     * @param benef26a
     * @throws Benef26aNotFound
     */
    @Override
    public void updateBenef26a(Benef26a benef26a) throws Benef26aNotFound {
        Benef26a updatableBenef26a = em.find(Benef26a.class, benef26a.getId());

        if (updatableBenef26a == null) {
            throw new Benef26aNotFound();
        }

        em.merge(benef26a);
        em.flush();

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + benef26a.getId() + " | UPD: " + updatableBenef26a.toString() + " | ACT: " + this.getClass().getName() + ".updateBenef26a");
        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + benef26a.getId() + " | DAT: " + benef26a.toString() + " | ACT: " + this.getClass().getName() + ".updateBenef26a");

    }

}
