package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Moned46;
import com.hrm.sirhapp.service.exception.Moned46AlreadyExists;
import com.hrm.sirhapp.service.exception.Moned46NotFound;
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
public class Moned46Manager implements Moned46ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idmon
     * @return
     * @throws Moned46NotFound
     */
    @Override
    public Moned46 getMoned46(Integer idmon) throws Moned46NotFound {
        Query query = em.createQuery("SELECT moned46 FROM Moned46 moned46 WHERE "
                + "moned46.idmon = :idmon");

        query.setParameter("idmon", idmon);

        Moned46 moned46Info;

        try {
            moned46Info = (Moned46) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Moned46NotFound();
        }

        Moned46 moned46 = moned46Info;

        return moned46;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Moned46> getListMoned46() {
        Query query = em.createQuery("select moned46 from Moned46 moned46 "
                + "where moned46.stmon = 'SI'", Moned46.class);

        List<Moned46> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Moned46>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Moned46> retrieveMoned46() {
        Query query = em.createQuery("select moned46 from Moned46 moned46", Moned46.class);

        List<Moned46> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Moned46>();
        }

        return result;
    }

    /**
     *
     * @param moned46
     * @return
     * @throws Moned46AlreadyExists
     */
    @Override
    public Moned46 registerMoned46(Moned46 moned46) throws Moned46AlreadyExists {

        em.persist(moned46);
        em.flush();

        return moned46;
    }

    /**
     *
     * @param moned46
     * @throws Moned46NotFound
     */
    @Override
    public void removeMoned46(Moned46 moned46) throws Moned46NotFound {
        Moned46 updatableMoned46 = em.find(Moned46.class, moned46.getIdmon());

        if (updatableMoned46 == null) {
            throw new Moned46NotFound();
        }
        if (!em.contains(moned46)) {
            moned46 = em.merge(moned46);
        }
        em.remove(moned46);
        em.flush();
    }

    /**
     *
     * @param moned46
     * @throws Moned46NotFound
     */
    @Override
    public void deleteMoned46(Moned46 moned46) throws Moned46NotFound {
        Moned46 updatableMoned46 = em.find(Moned46.class, moned46.getIdmon());

        if (updatableMoned46 == null) {
            throw new Moned46NotFound();
        }

        em.merge(moned46);
        em.flush();
    }

    /**
     *
     * @param moned46
     * @throws Moned46NotFound
     */
    @Override
    public void updateMoned46(Moned46 moned46) throws Moned46NotFound {
        Moned46 updatableMoned46 = em.find(Moned46.class, moned46.getIdmon());

        if (updatableMoned46 == null) {
            throw new Moned46NotFound();
        }

        em.merge(moned46);
        em.flush();
    }
}
