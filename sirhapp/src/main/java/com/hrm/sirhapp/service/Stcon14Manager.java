package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Stcon14;
import com.hrm.sirhapp.service.exception.Stcon14AlreadyExists;
import com.hrm.sirhapp.service.exception.Stcon14NotFound;
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
public class Stcon14Manager implements Stcon14ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idstc
     * @return
     * @throws Stcon14NotFound
     */
    @Override
    public Stcon14 getStcon14(Integer idstc) throws Stcon14NotFound {
        Query query = em.createQuery("SELECT stcon14 FROM Stcon14 stcon14 WHERE "
                + "stcon14.idstc = :idstc");

        query.setParameter("idstc", idstc);

        Stcon14 stcon14Info;

        try {
            stcon14Info = (Stcon14) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Stcon14NotFound();
        }

        Stcon14 stcon14 = stcon14Info;

        return stcon14;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Stcon14> getListStcon14() {
        Query query = em.createQuery("select stcon14 from Stcon14 stcon14 "
                + "where stcon14.ststc = 'SI'", Stcon14.class);

        List<Stcon14> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Stcon14>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Stcon14> retrieveStcon14() {
        Query query = em.createQuery("select stcon14 from Stcon14 stcon14", Stcon14.class);

        List<Stcon14> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Stcon14>();
        }

        return result;
    }

    /**
     *
     * @param stcon14
     * @return
     * @throws Stcon14AlreadyExists
     */
    @Override
    public Stcon14 registerStcon14(Stcon14 stcon14) throws Stcon14AlreadyExists {

        em.persist(stcon14);
        em.flush();

        return stcon14;
    }

    /**
     *
     * @param stcon14
     * @throws Stcon14NotFound
     */
    @Override
    public void removeStcon14(Stcon14 stcon14) throws Stcon14NotFound {
        Stcon14 updatableStcon14 = em.find(Stcon14.class, stcon14.getIdstc());

        if (updatableStcon14 == null) {
            throw new Stcon14NotFound();
        }
        if (!em.contains(stcon14)) {
            stcon14 = em.merge(stcon14);
        }

        em.remove(stcon14);
        em.flush();
    }

    /**
     *
     * @param stcon14
     * @throws Stcon14NotFound
     */
    @Override
    public void deleteStcon14(Stcon14 stcon14) throws Stcon14NotFound {
        Stcon14 updatableStcon14 = em.find(Stcon14.class, stcon14.getIdstc());

        if (updatableStcon14 == null) {
            throw new Stcon14NotFound();
        }

        em.merge(stcon14);
        em.flush();
    }

    /**
     *
     * @param stcon14
     * @throws Stcon14NotFound
     */
    @Override
    public void updateStcon14(Stcon14 stcon14) throws Stcon14NotFound {
        Stcon14 updatableStcon14 = em.find(Stcon14.class, stcon14.getIdstc());

        if (updatableStcon14 == null) {
            throw new Stcon14NotFound();
        }

        em.merge(stcon14);
        em.flush();
    }
}
