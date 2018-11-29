package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Relig11;
import com.hrm.sirhapp.service.exception.Relig11AlreadyExists;
import com.hrm.sirhapp.service.exception.Relig11NotFound;
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
public class Relig11Manager implements Relig11ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idrel
     * @return
     * @throws Relig11NotFound
     */
    @Override
    public Relig11 getRelig11(Integer idrel) throws Relig11NotFound {
        Query query = em.createQuery("SELECT relig11 FROM Relig11 relig11 WHERE "
                + "relig11.idrel = :idrel");

        query.setParameter("idrel", idrel);

        Relig11 relig11Info;

        try {
            relig11Info = (Relig11) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Relig11NotFound();
        }

        Relig11 relig11 = relig11Info;

        return relig11;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Relig11> getListRelig11() {
        Query query = em.createQuery("select relig11 from Relig11 relig11 "
                + "where relig11.strel = 'SI'", Relig11.class);

        List<Relig11> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Relig11>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Relig11> retrieveRelig11() {
        Query query = em.createQuery("select relig11 from Relig11 relig11", Relig11.class);

        List<Relig11> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Relig11>();
        }

        return result;
    }

    /**
     *
     * @param relig11
     * @return
     * @throws Relig11AlreadyExists
     */
    @Override
    public Relig11 registerRelig11(Relig11 relig11) throws Relig11AlreadyExists {

        em.persist(relig11);
        em.flush();

        return relig11;
    }

    /**
     *
     * @param relig11
     * @throws Relig11NotFound
     */
    @Override
    public void removeRelig11(Relig11 relig11) throws Relig11NotFound {
        Relig11 updatableRelig11 = em.find(Relig11.class, relig11.getIdrel());

        if (updatableRelig11 == null) {
            throw new Relig11NotFound();
        }
        if (!em.contains(relig11)) {
            relig11 = em.merge(relig11);
        }
        em.remove(relig11);
        em.flush();
    }

    /**
     *
     * @param relig11
     * @throws Relig11NotFound
     */
    @Override
    public void deleteRelig11(Relig11 relig11) throws Relig11NotFound {
        Relig11 updatableRelig11 = em.find(Relig11.class, relig11.getIdrel());

        if (updatableRelig11 == null) {
            throw new Relig11NotFound();
        }

        em.merge(relig11);
        em.flush();
    }

    /**
     *
     * @param relig11
     * @throws Relig11NotFound
     */
    @Override
    public void updateRelig11(Relig11 relig11) throws Relig11NotFound {
        Relig11 updatableRelig11 = em.find(Relig11.class, relig11.getIdrel());

        if (updatableRelig11 == null) {
            throw new Relig11NotFound();
        }

        em.merge(relig11);
        em.flush();
    }

}
