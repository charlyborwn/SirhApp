package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Tidoc18;
import com.hrm.sirhapp.service.exception.Tidoc18AlreadyExists;
import com.hrm.sirhapp.service.exception.Tidoc18NotFound;
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
public class Tidoc18Manager implements Tidoc18ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idtid
     * @return
     * @throws Tidoc18NotFound
     */
    @Override
    public Tidoc18 getTidoc18(Integer idtid) throws Tidoc18NotFound {
        Query query = em.createQuery("SELECT tidoc18 FROM Tidoc18 tidoc18 WHERE "
                + "tidoc18.idtid = :idtid");

        query.setParameter("idtid", idtid);

        Tidoc18 tidoc18Info;

        try {
            tidoc18Info = (Tidoc18) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Tidoc18NotFound();
        }

        Tidoc18 tidoc18 = tidoc18Info;

        return tidoc18;
    }

    /**
     *
     * @param notid
     * @return
     * @throws Tidoc18NotFound
     */
    @Override
    public String getTidoc18(String notid) throws Tidoc18NotFound {
        Query query = em.createQuery("SELECT tidoc18 FROM Tidoc18 tidoc18 WHERE "
                + "tidoc18.notid = :notid");

        query.setParameter("notid", notid);

        Tidoc18 tidoc18Info;

        try {
            tidoc18Info = (Tidoc18) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Tidoc18NotFound();
        }

        Tidoc18 tidoc18 = tidoc18Info;

        return tidoc18.getHmtid();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Tidoc18> getListTidoc18() {
        Query query = em.createQuery("select tidoc18 from Tidoc18 tidoc18 "
                + "where tidoc18.sttid = 'SI'", Tidoc18.class);

        List<Tidoc18> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Tidoc18>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Tidoc18> retrieveTidoc18() {
        Query query = em.createQuery("select tidoc18 from Tidoc18 tidoc18", Tidoc18.class);

        List<Tidoc18> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Tidoc18>();
        }

        return result;
    }

    /**
     *
     * @param tidoc18
     * @return
     * @throws Tidoc18AlreadyExists
     */
    @Override
    public Tidoc18 registerTidoc18(Tidoc18 tidoc18) throws Tidoc18AlreadyExists {

        em.persist(tidoc18);
        em.flush();

        return tidoc18;
    }

    /**
     *
     * @param tidoc18
     * @throws Tidoc18NotFound
     */
    @Override
    public void removeTidoc18(Tidoc18 tidoc18) throws Tidoc18NotFound {
        Tidoc18 updatableTidoc18 = em.find(Tidoc18.class, tidoc18.getIdtid());

        if (updatableTidoc18 == null) {
            throw new Tidoc18NotFound();
        }

        if (!em.contains(tidoc18)) {
            tidoc18 = em.merge(tidoc18);
        }

        em.remove(tidoc18);
        em.flush();
    }

    /**
     *
     * @param tidoc18
     * @throws Tidoc18NotFound
     */
    @Override
    public void deleteTidoc18(Tidoc18 tidoc18) throws Tidoc18NotFound {
        Tidoc18 updatableTidoc18 = em.find(Tidoc18.class, tidoc18.getIdtid());

        if (updatableTidoc18 == null) {
            throw new Tidoc18NotFound();
        }

        em.merge(tidoc18);
        em.flush();
    }

    /**
     *
     * @param tidoc18
     * @throws Tidoc18NotFound
     */
    @Override
    public void updateTidoc18(Tidoc18 tidoc18) throws Tidoc18NotFound {
        Tidoc18 updatableTidoc18 = em.find(Tidoc18.class, tidoc18.getIdtid());

        if (updatableTidoc18 == null) {
            throw new Tidoc18NotFound();
        }

        em.merge(tidoc18);
        em.flush();
    }
}
