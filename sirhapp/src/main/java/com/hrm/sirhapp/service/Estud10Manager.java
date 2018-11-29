package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Estud10;
import com.hrm.sirhapp.service.exception.Estud10AlreadyExists;
import com.hrm.sirhapp.service.exception.Estud10NotFound;
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
public class Estud10Manager implements Estud10ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idest
     * @return
     * @throws Estud10NotFound
     */
    @Override
    public Estud10 getEstud10(Integer idest) throws Estud10NotFound {
        Query query = em.createQuery("SELECT estud10 FROM Estud10 estud10 WHERE "
                + "estud10.idest = :idest");

        query.setParameter("idest", idest);

        Estud10 estud10Info;

        try {
            estud10Info = (Estud10) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Estud10NotFound();
        }

        Estud10 estud10 = estud10Info;

        return estud10;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Estud10> getListEstud10() {
        Query query = em.createQuery("select estud10 from Estud10 estud10 "
                + "where estud10.stest = 'SI'", Estud10.class);

        List<Estud10> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Estud10>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Estud10> retrieveEstud10() {
        Query query = em.createQuery("select estud10 from Estud10 estud10", Estud10.class);

        List<Estud10> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Estud10>();
        }

        return result;
    }

    /**
     *
     * @param estud10
     * @return
     * @throws Estud10AlreadyExists
     */
    @Override
    public Estud10 registerEstud10(Estud10 estud10) throws Estud10AlreadyExists {

        em.persist(estud10);
        em.flush();

        return estud10;
    }

    /**
     *
     * @param estud10
     * @throws Estud10NotFound
     */
    @Override
    public void removeEstud10(Estud10 estud10) throws Estud10NotFound {
        Estud10 updatableEstud10 = em.find(Estud10.class, estud10.getIdest());

        if (updatableEstud10 == null) {
            throw new Estud10NotFound();
        }
        if (!em.contains(estud10)) {
            estud10 = em.merge(estud10);
        }
        em.remove(estud10);
        em.flush();
    }

    /**
     *
     * @param estud10
     * @throws Estud10NotFound
     */
    @Override
    public void deleteEstud10(Estud10 estud10) throws Estud10NotFound {
        Estud10 updatableEstud10 = em.find(Estud10.class, estud10.getIdest());

        if (updatableEstud10 == null) {
            throw new Estud10NotFound();
        }

        em.merge(estud10);
        em.flush();
    }

    /**
     *
     * @param estud10
     * @throws Estud10NotFound
     */
    @Override
    public void updateEstud10(Estud10 estud10) throws Estud10NotFound {
        Estud10 updatableEstud10 = em.find(Estud10.class, estud10.getIdest());

        if (updatableEstud10 == null) {
            throw new Estud10NotFound();
        }

        em.merge(estud10);
        em.flush();
    }

}
