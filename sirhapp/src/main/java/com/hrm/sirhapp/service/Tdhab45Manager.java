package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Tdhab45;
import com.hrm.sirhapp.service.exception.Tdhab45AlreadyExists;
import com.hrm.sirhapp.service.exception.Tdhab45NotFound;
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
public class Tdhab45Manager implements Tdhab45ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idhab
     * @return
     * @throws Tdhab45NotFound
     */
    @Override
    public Tdhab45 getTdhab45(Integer idhab) throws Tdhab45NotFound {
        Query query = em.createQuery("SELECT tdhab45 FROM Tdhab45 tdhab45 WHERE "
                + "tdhab45.idhab = :idhab");

        query.setParameter("idhab", idhab);

        Tdhab45 tdhab45Info;

        try {
            tdhab45Info = (Tdhab45) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Tdhab45NotFound();
        }

        Tdhab45 tdhab45 = tdhab45Info;

        return tdhab45;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Tdhab45> getListTdhab45() {
        Query query = em.createQuery("select tdhab45 from Tdhab45 tdhab45 "
                + "where tdhab45.sthab = 'SI'", Tdhab45.class);

        List<Tdhab45> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Tdhab45>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Tdhab45> retrieveTdhab45() {
        Query query = em.createQuery("select tdhab45 from Tdhab45 tdhab45", Tdhab45.class);

        List<Tdhab45> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Tdhab45>();
        }

        return result;
    }

    /**
     *
     * @param tdhab45
     * @return
     * @throws Tdhab45AlreadyExists
     */
    @Override
    public Tdhab45 registerTdhab45(Tdhab45 tdhab45) throws Tdhab45AlreadyExists {

        em.persist(tdhab45);
        em.flush();

        return tdhab45;
    }

    /**
     *
     * @param tdhab45
     * @throws Tdhab45NotFound
     */
    @Override
    public void deleteTdhab45(Tdhab45 tdhab45) throws Tdhab45NotFound {
        Tdhab45 updatableTdhab45 = em.find(Tdhab45.class, tdhab45.getIdhab());

        if (updatableTdhab45 == null) {
            throw new Tdhab45NotFound();
        }

        em.merge(tdhab45);
        em.flush();
    }

    /**
     *
     * @param tdhab45
     * @throws Tdhab45NotFound
     */
    @Override
    public void removeTdhab45(Tdhab45 tdhab45) throws Tdhab45NotFound {
        Tdhab45 updatableTdhab45 = em.find(Tdhab45.class, tdhab45.getIdhab());

        if (updatableTdhab45 == null) {
            throw new Tdhab45NotFound();
        }
        if (!em.contains(tdhab45)) {
            tdhab45 = em.merge(tdhab45);
        }

        em.remove(tdhab45);
        em.flush();
    }

    /**
     *
     * @param tdhab45
     * @throws Tdhab45NotFound
     */
    @Override
    public void updateTdhab45(Tdhab45 tdhab45) throws Tdhab45NotFound {
        Tdhab45 updatableTdhab45 = em.find(Tdhab45.class, tdhab45.getIdhab());

        if (updatableTdhab45 == null) {
            throw new Tdhab45NotFound();
        }

        em.merge(tdhab45);
        em.flush();
    }
}
