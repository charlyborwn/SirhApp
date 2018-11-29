package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Ticon17;
import com.hrm.sirhapp.service.exception.Ticon17AlreadyExists;
import com.hrm.sirhapp.service.exception.Ticon17NotFound;
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
public class Ticon17Manager implements Ticon17ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idtic
     * @return
     * @throws Ticon17NotFound
     */
    @Override
    public Ticon17 getTicon17(Integer idtic) throws Ticon17NotFound {
        Query query = em.createQuery("SELECT ticon17 FROM Ticon17 ticon17 WHERE "
                + "ticon17.idtic = :idtic");

        query.setParameter("idtic", idtic);

        Ticon17 ticon17Info;

        try {
            ticon17Info = (Ticon17) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Ticon17NotFound();
        }

        Ticon17 ticon17 = ticon17Info;

        return ticon17;
    }

    /**
     *
     * @param cvtic
     * @return
     * @throws Ticon17NotFound
     */
    @Override
    public Ticon17 getTicon17(String cvtic) throws Ticon17NotFound {
        Query query = em.createQuery("SELECT ticon17 FROM Ticon17 ticon17 WHERE "
                + "ticon17.cvtic = :cvtic");

        query.setParameter("cvtic", cvtic);

        Ticon17 ticon17Info;

        try {
            ticon17Info = (Ticon17) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Ticon17NotFound();
        }

        return ticon17Info;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Ticon17> getListTicon17() {
        Query query = em.createQuery("select ticon17 from Ticon17 ticon17 "
                + "where ticon17.sttic = 'SI'", Ticon17.class);

        List<Ticon17> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Ticon17>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Ticon17> retrieveTicon17() {
        Query query = em.createQuery("select ticon17 from Ticon17 ticon17", Ticon17.class);

        List<Ticon17> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Ticon17>();
        }

        return result;
    }

    /**
     *
     * @param ticon17
     * @return
     * @throws Ticon17AlreadyExists
     */
    @Override
    public Ticon17 registerTicon17(Ticon17 ticon17) throws Ticon17AlreadyExists {

        em.persist(ticon17);
        em.flush();

        return ticon17;
    }

    /**
     *
     * @param ticon17
     * @throws Ticon17NotFound
     */
    @Override
    public void removeTicon17(Ticon17 ticon17) throws Ticon17NotFound {
        Ticon17 updatableTicon17 = em.find(Ticon17.class, ticon17.getIdtic());

        if (updatableTicon17 == null) {
            throw new Ticon17NotFound();
        }
        if (!em.contains(ticon17)) {
            ticon17 = em.merge(ticon17);
        }

        em.remove(ticon17);
        em.flush();
    }

    /**
     *
     * @param ticon17
     * @throws Ticon17NotFound
     */
    @Override
    public void deleteTicon17(Ticon17 ticon17) throws Ticon17NotFound {
        Ticon17 updatableTicon17 = em.find(Ticon17.class, ticon17.getIdtic());

        if (updatableTicon17 == null) {
            throw new Ticon17NotFound();
        }

        em.merge(ticon17);
        em.flush();
    }

    /**
     *
     * @param ticon17
     * @throws Ticon17NotFound
     */
    @Override
    public void updateTicon17(Ticon17 ticon17) throws Ticon17NotFound {
        Ticon17 updatableTicon17 = em.find(Ticon17.class, ticon17.getIdtic());

        if (updatableTicon17 == null) {
            throw new Ticon17NotFound();
        }

        em.merge(ticon17);
        em.flush();
    }
}
