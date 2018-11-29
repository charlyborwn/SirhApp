package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Unida42;
import com.hrm.sirhapp.service.exception.Unida42AlreadyExists;
import com.hrm.sirhapp.service.exception.Unida42NotFound;
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
public class Unida42Manager implements Unida42ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param iduni
     * @return
     * @throws Unida42NotFound
     */
    @Override
    public Unida42 getUnida42(Integer iduni) throws Unida42NotFound {
        Query query = em.createQuery("SELECT unida42 FROM Unida42 unida42 WHERE "
                + "unida42.iduni = :iduni");

        query.setParameter("iduni", iduni);

        Unida42 unida42Info;

        try {
            unida42Info = (Unida42) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Unida42NotFound();
        }

        Unida42 unida42 = unida42Info;

        return unida42;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Unida42> getListUnida42() {
        Query query = em.createQuery("select unida42 from Unida42 unida42 "
                + "where unida42.stuni = 'SI'", Unida42.class);

        List<Unida42> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Unida42>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Unida42> retrieveUnida42() {
        Query query = em.createQuery("select unida42 from Unida42 unida42", Unida42.class);

        List<Unida42> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Unida42>();
        }

        return result;
    }

    /**
     *
     * @param unida42
     * @return
     * @throws Unida42AlreadyExists
     */
    @Override
    public Unida42 registerUnida42(Unida42 unida42) throws Unida42AlreadyExists {

        em.persist(unida42);
        em.flush();

        return unida42;
    }

    /**
     *
     * @param unida42
     * @throws Unida42NotFound
     */
    @Override
    public void deleteUnida42(Unida42 unida42) throws Unida42NotFound {
        Unida42 updatableUnida42 = em.find(Unida42.class, unida42.getIduni());

        if (updatableUnida42 == null) {
            throw new Unida42NotFound();
        }

        updatableUnida42.setStuni(unida42.getStuni());
        updatableUnida42.setFeuni(unida42.getFeuni());
        updatableUnida42.setUsuni(unida42.getUsuni());

        em.merge(unida42);
        em.flush();
    }

    /**
     *
     * @param unida42
     * @throws Unida42NotFound
     */
    @Override
    public void removeUnida42(Unida42 unida42) throws Unida42NotFound {
        Unida42 updatableUnida42 = em.find(Unida42.class, unida42.getIduni());

        if (updatableUnida42 == null) {
            throw new Unida42NotFound();
        }
        if (!em.contains(unida42)) {
            unida42 = em.merge(unida42);
        }
        em.remove(unida42);
        em.flush();
    }

    /**
     *
     * @param unida42
     * @throws Unida42NotFound
     */
    @Override
    public void updateUnida42(Unida42 unida42) throws Unida42NotFound {
        Unida42 updatableUnida42 = em.find(Unida42.class, unida42.getIduni());

        if (updatableUnida42 == null) {
            throw new Unida42NotFound();
        }

        em.merge(unida42);
        em.flush();
    }
}
