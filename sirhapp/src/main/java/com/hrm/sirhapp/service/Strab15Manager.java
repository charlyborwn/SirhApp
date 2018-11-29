package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Strab15;
import com.hrm.sirhapp.service.exception.Strab15AlreadyExists;
import com.hrm.sirhapp.service.exception.Strab15NotFound;
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
public class Strab15Manager implements Strab15ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idstr
     * @return
     * @throws Strab15NotFound
     */
    @Override
    public Strab15 getStrab15(Integer idstr) throws Strab15NotFound {
        Query query = em.createQuery("SELECT strab15 FROM Strab15 strab15 WHERE "
                + "strab15.idstr = :idstr");

        query.setParameter("idstr", idstr);

        Strab15 strab15Info;

        try {
            strab15Info = (Strab15) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Strab15NotFound();
        }

        Strab15 strab15 = strab15Info;

        return strab15;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Strab15> getListStrab15() {
        Query query = em.createQuery("select strab15 from Strab15 strab15 "
                + "where strab15.ststr = 'SI'", Strab15.class);

        List<Strab15> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Strab15>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Strab15> retrieveStrab15() {
        Query query = em.createQuery("select strab15 from Strab15 strab15", Strab15.class);

        List<Strab15> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Strab15>();
        }

        return result;
    }

    /**
     *
     * @param strab15
     * @return
     * @throws Strab15AlreadyExists
     */
    @Override
    public Strab15 registerStrab15(Strab15 strab15) throws Strab15AlreadyExists {

        em.persist(strab15);
        em.flush();

        return strab15;
    }

    /**
     *
     * @param strab15
     * @throws Strab15NotFound
     */
    @Override
    public void removeStrab15(Strab15 strab15) throws Strab15NotFound {
        Strab15 updatableStrab15 = em.find(Strab15.class, strab15.getIdstr());

        if (updatableStrab15 == null) {
            throw new Strab15NotFound();
        }
        if (!em.contains(strab15)) {
            strab15 = em.merge(strab15);
        }

        em.remove(strab15);
        em.flush();
    }

    /**
     *
     * @param strab15
     * @throws Strab15NotFound
     */
    @Override
    public void deleteStrab15(Strab15 strab15) throws Strab15NotFound {
        Strab15 updatableStrab15 = em.find(Strab15.class, strab15.getIdstr());

        if (updatableStrab15 == null) {
            throw new Strab15NotFound();
        }

        em.merge(strab15);
        em.flush();
    }

    /**
     *
     * @param strab15
     * @throws Strab15NotFound
     */
    @Override
    public void updateStrab15(Strab15 strab15) throws Strab15NotFound {
        Strab15 updatableStrab15 = em.find(Strab15.class, strab15.getIdstr());

        if (updatableStrab15 == null) {
            throw new Strab15NotFound();
        }

        em.merge(strab15);
        em.flush();
    }
}
