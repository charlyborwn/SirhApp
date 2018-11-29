package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Requi54;
import com.hrm.sirhapp.service.exception.Requi54AlreadyExists;
import com.hrm.sirhapp.service.exception.Requi54NotFound;
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
public class Requi54Manager implements Requi54ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param rfreq
     * @return
     * @throws Requi54NotFound
     */
    @Override
    public Requi54 getRequi54(String rfreq) throws Requi54NotFound {
        Query query = em.createQuery("SELECT requi54 FROM Requi54 requi54 "
                + "WHERE requi54.rfreq = :rfreq");

        query.setParameter("rfreq", rfreq);

        Requi54 requi54Info;

        try {
            requi54Info = (Requi54) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Requi54NotFound();
        }

        Requi54 requi54 = requi54Info;

        return requi54;
    }

    /**
     *
     * @param idreq
     * @return
     * @throws Requi54NotFound
     */
    @Override
    public Requi54 getRequi54ById(Integer idreq) throws Requi54NotFound {
        Query query = em.createQuery("SELECT requi54 FROM  Requi54 requi54 where "
                + "requi54.idreq = :idreq");

        query.setParameter("idreq", idreq);

        Requi54 requi54Info;

        try {
            requi54Info = (Requi54) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Requi54NotFound();
        }

        Requi54 requi54 = requi54Info;

        return requi54;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Requi54> retrieveRequi54() {
        Query query = em.createQuery("select requi54 from Requi54 requi54", Requi54.class);

        List<Requi54> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Requi54>();
        }

        return result;
    }

    /**
     *
     * @param requi54
     * @return
     * @throws Requi54AlreadyExists
     */
    @Override
    public Requi54 registerRequi54(Requi54 requi54) throws Requi54AlreadyExists {
        Requi54 lookUpRequi54;
        try {
            lookUpRequi54 = getRequi54(requi54.getRfreq());
            if (lookUpRequi54 != null) {
                throw new Requi54AlreadyExists();
            }
        } catch (Requi54NotFound ex) {
            em.persist(requi54);
            em.flush();
        }

        return requi54;
    }

    /**
     *
     * @param requi54
     * @throws Requi54NotFound
     */
    @Override
    public void removeRequi54(Requi54 requi54) throws Requi54NotFound {
        Requi54 updatableRequi54 = em.find(Requi54.class, requi54.getIdreq());

        if (updatableRequi54 == null) {
            throw new Requi54NotFound();
        }

        if (!em.contains(requi54)) {
            requi54 = em.merge(requi54);
        }

        em.remove(requi54);
        em.flush();
    }

    /**
     *
     * @param requi54
     * @throws Requi54NotFound
     */
    @Override
    public void deleteRequi54(Requi54 requi54) throws Requi54NotFound {
        Requi54 updatableRequi54 = em.find(Requi54.class, requi54.getIdreq());

        if (updatableRequi54 == null) {
            throw new Requi54NotFound();
        }

        em.merge(requi54);
        em.flush();
    }

    /**
     *
     * @param requi54
     * @throws Requi54NotFound
     */
    @Override
    public void updateRequi54(Requi54 requi54) throws Requi54NotFound {
        Requi54 updatableRequi54 = em.find(Requi54.class, requi54.getIdreq());

        if (updatableRequi54 == null) {
            throw new Requi54NotFound();
        }

        em.merge(requi54);
        em.flush();
    }

}
