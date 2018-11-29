package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Exper31a;
import com.hrm.sirhapp.service.exception.Exper31aAlreadyExists;
import com.hrm.sirhapp.service.exception.Exper31aNotFound;
import java.util.ArrayList;
import java.util.List;
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
public class Exper31aManager implements Exper31aManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idexpA
     * @return
     */
    @Override
    public Exper31a getExper31a(Integer idexpA) {
        Query query = em.createQuery("SELECT exper31a FROM Exper31a exper31a WHERE "
                + "exper31a.idexpA = :idexpA");

        query.setParameter("idexpA", idexpA);

        Exper31a exper31aInfo;

        exper31aInfo = (Exper31a) query.getSingleResult();

        Exper31a exper31a = exper31aInfo;

        return exper31a;
    }

    /**
     *
     * @param rfexpA
     * @return
     */
    @Override
    public List<Exper31a> getListExper31a(String rfexpA) {
        Query query = em.createQuery("select exper31a from Exper31a exper31a "
                + "where exper31a.stexpA='SI' and  exper31a.rfexpA = :rfexpA", Exper31a.class);

        query.setParameter("rfexpA", rfexpA);

        List<Exper31a> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Exper31a>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Exper31a> retrieveExper31a() {
        Query query = em.createQuery("select exper31a from Exper31a exper31a", Exper31a.class);

        List<Exper31a> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Exper31a>();
        }

        return result;
    }

    /**
     *
     * @param exper31a
     * @return
     * @throws Exper31aAlreadyExists
     */
    @Override
    public Exper31a registerExper31a(Exper31a exper31a) throws Exper31aAlreadyExists {

        em.persist(exper31a);
        em.flush();

        return exper31a;
    }

    /**
     *
     * @param exper31a
     * @throws Exper31aNotFound
     */
    @Override
    public void removeExper31a(Exper31a exper31a) throws Exper31aNotFound {
        Exper31a updatableExper31a = em.find(Exper31a.class, exper31a.getIdexpA());

        if (updatableExper31a == null) {
            throw new Exper31aNotFound();
        }
        if (!em.contains(exper31a)) {
            exper31a = em.merge(exper31a);
        }
        em.remove(exper31a);
        em.flush();
    }

    /**
     *
     * @param exper31a
     * @throws Exper31aNotFound
     */
    @Override
    public void deleteExper31a(Exper31a exper31a) throws Exper31aNotFound {
        Exper31a updatableExper31a = em.find(Exper31a.class, exper31a.getIdexpA());

        if (updatableExper31a == null) {
            throw new Exper31aNotFound();
        }

        em.merge(exper31a);
        em.flush();
    }

    /**
     *
     * @param exper31a
     * @throws Exper31aNotFound
     */
    @Override
    public void updateExper31a(Exper31a exper31a) throws Exper31aNotFound {
        Exper31a updatableExper31a = em.find(Exper31a.class, exper31a.getIdexpA());

        if (updatableExper31a == null) {
            throw new Exper31aNotFound();
        }

        em.merge(exper31a);
        em.flush();
    }

}
