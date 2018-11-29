package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Sangr21;
import com.hrm.sirhapp.service.exception.Sangr21AlreadyExists;
import com.hrm.sirhapp.service.exception.Sangr21NotFound;
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
public class Sangr21Manager implements Sangr21ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idsan
     * @return
     * @throws Sangr21NotFound
     */
    @Override
    public Sangr21 getSangr21(Integer idsan) throws Sangr21NotFound {
        Query query = em.createQuery("SELECT sangr21 FROM Sangr21 sangr21 WHERE "
                + "sangr21.idsan = :idsan");

        query.setParameter("idsan", idsan);

        Sangr21 sangr21Info;

        try {
            sangr21Info = (Sangr21) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Sangr21NotFound();
        }

        Sangr21 sangr21 = sangr21Info;

        return sangr21;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Sangr21> getListSangr21() {
        Query query = em.createQuery("select sangr21 from Sangr21 sangr21 "
                + "where sangr21.stsan = 'SI'", Sangr21.class);

        List<Sangr21> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Sangr21>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Sangr21> retrieveSangr21() {
        Query query = em.createQuery("select sangr21 from Sangr21 sangr21", Sangr21.class);

        List<Sangr21> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Sangr21>();
        }

        return result;
    }

    /**
     *
     * @param sangr21
     * @return
     * @throws Sangr21AlreadyExists
     */
    @Override
    public Sangr21 registerSangr21(Sangr21 sangr21) throws Sangr21AlreadyExists {

        em.persist(sangr21);
        em.flush();

        return sangr21;
    }

    /**
     *
     * @param sangr21
     * @throws Sangr21NotFound
     */
    @Override
    public void removeSangr21(Sangr21 sangr21) throws Sangr21NotFound {
        Sangr21 updatableSangr21 = em.find(Sangr21.class, sangr21.getIdsan());

        if (updatableSangr21 == null) {
            throw new Sangr21NotFound();
        }

        if (!em.contains(sangr21)) {
            sangr21 = em.merge(sangr21);
        }

        em.remove(sangr21);
        em.flush();
    }

    /**
     *
     * @param sangr21
     * @throws Sangr21NotFound
     */
    @Override
    public void deleteSangr21(Sangr21 sangr21) throws Sangr21NotFound {
        Sangr21 updatableSangr21 = em.find(Sangr21.class, sangr21.getIdsan());

        if (updatableSangr21 == null) {
            throw new Sangr21NotFound();
        }

        em.merge(sangr21);
        em.flush();
    }

    /**
     *
     * @param sangr21
     * @throws Sangr21NotFound
     */
    @Override
    public void updateSangr21(Sangr21 sangr21) throws Sangr21NotFound {
        Sangr21 updatableSangr21 = em.find(Sangr21.class, sangr21.getIdsan());

        if (updatableSangr21 == null) {
            throw new Sangr21NotFound();
        }

        em.merge(sangr21);
        em.flush();
    }
}
