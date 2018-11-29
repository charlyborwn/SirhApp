package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Famil32;
import com.hrm.sirhapp.service.exception.Famil32AlreadyExists;
import com.hrm.sirhapp.service.exception.Famil32NotFound;
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
public class Famil32Manager implements Famil32ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idfam
     * @return
     */
    @Override
    public Famil32 getFamil32(Integer idfam) {
        Query query = em.createQuery("SELECT famil32 FROM Famil32 famil32 WHERE "
                + "famil32.idfam = :idfam");

        query.setParameter("idfam", idfam);

        Famil32 famil32Info;

        famil32Info = (Famil32) query.getSingleResult();

        Famil32 famil32 = famil32Info;

        return famil32;
    }

    /**
     *
     * @param ntfam
     * @return
     */
    @Override
    public List<Famil32> getListFamil32(Integer ntfam) {
        Query query = em.createQuery("select famil32 from Famil32 famil32 "
                + "where famil32.ntfam = :ntfam", Famil32.class);

        query.setParameter("ntfam", ntfam);

        List<Famil32> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Famil32>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Famil32> retrieveFamil32() {
        Query query = em.createQuery("select famil32 from Famil32 famil32", Famil32.class);

        List<Famil32> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Famil32>();
        }

        return result;
    }

    /**
     *
     * @param famil32
     * @return
     * @throws Famil32AlreadyExists
     */
    @Override
    public Famil32 registerFamil32(Famil32 famil32) throws Famil32AlreadyExists {

        em.persist(famil32);
        em.flush();

        return famil32;
    }

    /**
     *
     * @param famil32
     * @throws Famil32NotFound
     */
    @Override
    public void deleteFamil32(Famil32 famil32) throws Famil32NotFound {
        Famil32 updatableFamil32 = em.find(Famil32.class, famil32.getIdfam());

        if (updatableFamil32 == null) {
            throw new Famil32NotFound();
        }

        em.merge(famil32);
        em.flush();
    }

    /**
     *
     * @param famil32
     * @throws Famil32NotFound
     */
    @Override
    public void removeFamil32(Famil32 famil32) throws Famil32NotFound {
        Famil32 updatableFamil32 = em.find(Famil32.class, famil32.getIdfam());

        if (updatableFamil32 == null) {
            throw new Famil32NotFound();
        }
        if (!em.contains(famil32)) {
            famil32 = em.merge(famil32);
        }
        em.remove(famil32);
        em.flush();
    }

    /**
     *
     * @param famil32
     * @throws Famil32NotFound
     */
    @Override
    public void updateFamil32(Famil32 famil32) throws Famil32NotFound {
        Famil32 updatableFamil32 = em.find(Famil32.class, famil32.getIdfam());

        if (updatableFamil32 == null) {
            throw new Famil32NotFound();
        }

        em.merge(famil32);
        em.flush();
    }

}
