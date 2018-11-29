package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Rutas41;
import com.hrm.sirhapp.service.exception.Rutas41AlreadyExists;
import com.hrm.sirhapp.service.exception.Rutas41NotFound;
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
public class Rutas41Manager implements Rutas41ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idrut
     * @return
     * @throws Rutas41NotFound
     */
    @Override
    public Rutas41 getRutas41(Integer idrut) throws Rutas41NotFound {
        Query query = em.createQuery("SELECT rutas41 FROM Rutas41 rutas41 WHERE "
                + "rutas41.idrut = :idrut");

        query.setParameter("idrut", idrut);

        Rutas41 rutas41Info;

        try {
            rutas41Info = (Rutas41) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Rutas41NotFound();
        }

        Rutas41 rutas41 = rutas41Info;

        return rutas41;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Rutas41> getListRutas41() {
        Query query = em.createQuery("select rutas41 from Rutas41 rutas41 "
                + "where rutas41.strut = 'SI'", Rutas41.class);

        List<Rutas41> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Rutas41>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Rutas41> retrieveRutas41() {
        Query query = em.createQuery("select rutas41 from Rutas41 rutas41", Rutas41.class);

        List<Rutas41> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Rutas41>();
        }

        return result;
    }

    /**
     *
     * @param rutas41
     * @return
     * @throws Rutas41AlreadyExists
     */
    @Override
    public Rutas41 registerRutas41(Rutas41 rutas41) throws Rutas41AlreadyExists {

        em.persist(rutas41);
        em.flush();

        return rutas41;
    }

    /**
     *
     * @param rutas41
     * @throws Rutas41NotFound
     */
    @Override
    public void removeRutas41(Rutas41 rutas41) throws Rutas41NotFound {
        Rutas41 updatableRutas41 = em.find(Rutas41.class, rutas41.getIdrut());

        if (updatableRutas41 == null) {
            throw new Rutas41NotFound();
        }

        if (!em.contains(rutas41)) {
            rutas41 = em.merge(rutas41);
        }

        em.remove(rutas41);
        em.flush();
    }

    /**
     *
     * @param rutas41
     * @throws Rutas41NotFound
     */
    @Override
    public void deleteRutas41(Rutas41 rutas41) throws Rutas41NotFound {
        Rutas41 updatableRutas41 = em.find(Rutas41.class, rutas41.getIdrut());

        if (updatableRutas41 == null) {
            throw new Rutas41NotFound();
        }

        em.merge(rutas41);
        em.flush();
    }

    /**
     *
     * @param rutas41
     * @throws Rutas41NotFound
     */
    @Override
    public void updateRutas41(Rutas41 rutas41) throws Rutas41NotFound {
        Rutas41 updatableRutas41 = em.find(Rutas41.class, rutas41.getIdrut());

        if (updatableRutas41 == null) {
            throw new Rutas41NotFound();
        }

        em.merge(rutas41);
        em.flush();
    }
}
