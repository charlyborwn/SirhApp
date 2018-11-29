package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Exper31;
import com.hrm.sirhapp.service.exception.Exper31AlreadyExists;
import com.hrm.sirhapp.service.exception.Exper31NotFound;
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
public class Exper31Manager implements Exper31ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idexp
     * @return
     */
    @Override
    public Exper31 getExper31(Integer idexp) {
        Query query = em.createQuery("SELECT exper31 FROM  Exper31 exper31 WHERE "
                + "exper31.idexp = :idexp");

        query.setParameter("idexp", idexp);

        Exper31 exper31Info;

        exper31Info = (Exper31) query.getSingleResult();

        Exper31 exper31 = exper31Info;

        return exper31;
    }

    /**
     *
     * @param ntexp
     * @return
     */
    @Override
    public List<Exper31> getListExper31(Integer ntexp) {
        Query query = em.createQuery("select exper31 from Exper31 exper31 "
                + "where exper31.ntexp = :ntexp", Exper31.class);

        query.setParameter("ntexp", ntexp);

        List<Exper31> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Exper31>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Exper31> retrieveExper31() {
        Query query = em.createQuery("select exper31 from Exper31 exper31", Exper31.class);

        List<Exper31> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Exper31>();
        }

        return result;
    }

    /**
     *
     * @param exper31
     * @return
     * @throws Exper31AlreadyExists
     */
    @Override
    public Exper31 registerExper31(Exper31 exper31) throws Exper31AlreadyExists {

        em.persist(exper31);
        em.flush();

        return exper31;
    }

    /**
     *
     * @param exper31
     * @throws Exper31NotFound
     */
    @Override
    public void removeExper31(Exper31 exper31) throws Exper31NotFound {
        Exper31 updatableExper31 = em.find(Exper31.class, exper31.getIdexp());

        if (updatableExper31 == null) {
            throw new Exper31NotFound();
        }
        if (!em.contains(exper31)) {
            exper31 = em.merge(exper31);
        }
        em.remove(exper31);
        em.flush();
    }

    /**
     *
     * @param exper31
     * @throws Exper31NotFound
     */
    @Override
    public void deleteExper31(Exper31 exper31) throws Exper31NotFound {
        Exper31 updatableExper31 = em.find(Exper31.class, exper31.getIdexp());

        if (updatableExper31 == null) {
            throw new Exper31NotFound();
        }

        em.merge(exper31);
        em.flush();
    }

    /**
     *
     * @param exper31
     * @throws Exper31NotFound
     */
    @Override
    public void updateExper31(Exper31 exper31) throws Exper31NotFound {
        Exper31 updatableExper31 = em.find(Exper31.class, exper31.getIdexp());

        if (updatableExper31 == null) {
            throw new Exper31NotFound();
        }

        em.merge(exper31);
        em.flush();
    }

}
