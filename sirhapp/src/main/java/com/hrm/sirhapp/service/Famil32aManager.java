package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Famil32a;
import com.hrm.sirhapp.service.exception.Famil32aAlreadyExists;
import com.hrm.sirhapp.service.exception.Famil32aNotFound;
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
public class Famil32aManager implements Famil32aManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idfamA
     * @return
     */
    @Override
    public Famil32a getFamil32a(Integer idfamA) {
        Query query = em.createQuery("SELECT famil32a FROM Famil32a famil32a WHERE "
                + "famil32a.idfamA = :idfamA");

        query.setParameter("idfamA", idfamA);

        Famil32a famil32aInfo;

        famil32aInfo = (Famil32a) query.getSingleResult();

        Famil32a famil32a = famil32aInfo;

        return famil32a;
    }

    /**
     *
     * @param rffamA
     * @return
     */
    @Override
    public List<Famil32a> getListFamil32a(String rffamA) {
        Query query = em.createQuery("select famil32a from Famil32a famil32a "
                + "where famil32a.stfamA= 'SI' and famil32a.rffamA = :rffamA", Famil32a.class);

        query.setParameter("rffamA", rffamA);

        List<Famil32a> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Famil32a>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Famil32a> retrieveFamil32a() {
        Query query = em.createQuery("select famil32a from Famil32a famil32a", Famil32a.class);

        List<Famil32a> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Famil32a>();
        }

        return result;
    }

    /**
     *
     * @param famil32a
     * @return
     * @throws Famil32aAlreadyExists
     */
    @Override
    public Famil32a registerFamil32a(Famil32a famil32a) throws Famil32aAlreadyExists {

        em.persist(famil32a);
        em.flush();

        return famil32a;
    }

    /**
     *
     * @param famil32a
     * @throws Famil32aNotFound
     */
    @Override
    public void deleteFamil32a(Famil32a famil32a) throws Famil32aNotFound {
        Famil32a updatableFamil32a = em.find(Famil32a.class, famil32a.getIdfamA());

        if (updatableFamil32a == null) {
            throw new Famil32aNotFound();
        }

        em.merge(famil32a);
        em.flush();
    }

    /**
     *
     * @param famil32a
     * @throws Famil32aNotFound
     */
    @Override
    public void removeFamil32a(Famil32a famil32a) throws Famil32aNotFound {
        Famil32a updatableFamil32a = em.find(Famil32a.class, famil32a.getIdfamA());

        if (updatableFamil32a == null) {
            throw new Famil32aNotFound();
        }
        if (!em.contains(famil32a)) {
            famil32a = em.merge(famil32a);
        }

        em.remove(famil32a);
        em.flush();
    }

    /**
     *
     * @param famil32a
     * @throws Famil32aNotFound
     */
    @Override
    public void updateFamil32a(Famil32a famil32a) throws Famil32aNotFound {
        Famil32a updatableFamil32a = em.find(Famil32a.class, famil32a.getIdfamA());

        if (updatableFamil32a == null) {
            throw new Famil32aNotFound();
        }

        em.merge(famil32a);
        em.flush();
    }

}
