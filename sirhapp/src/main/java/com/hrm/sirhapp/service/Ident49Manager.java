package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Ident49;
import com.hrm.sirhapp.service.exception.Ident49AlreadyExists;
import com.hrm.sirhapp.service.exception.Ident49NotFound;
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
public class Ident49Manager implements Ident49ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param ntide
     * @return
     */
    @Override
    public Boolean alreadyExistsInnactive(Integer ntide) {
        Query query = em.createQuery("SELECT ident49 FROM Ident49 ident49 WHERE "
                + "ident49.stide='NO' and ident49.ntide = :ntide");

        query.setParameter("ntide", ntide);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    /**
     *
     * @param ntide
     * @return
     * @throws Ident49NotFound
     */
    @Override
    public Ident49 getIdent49(Integer ntide) throws Ident49NotFound {
        Query query = em.createQuery("SELECT ident49 FROM Ident49 ident49 WHERE "
                + "ident49.stide='SI' and "
                + "ident49.ntide = :ntide");

        query.setParameter("ntide", ntide);

        Ident49 ident49Info;

        try {
            ident49Info = (Ident49) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Ident49NotFound();
        }

        Ident49 ident49 = ident49Info;

        return ident49;
    }

    /**
     *
     * @param ntide
     * @return
     * @throws Ident49NotFound
     */
    @Override
    public Ident49 getIdent49ById(Integer ntide) throws Ident49NotFound {
        Query query = em.createQuery("SELECT ident49 FROM  Ident49 ident49 where "
                + "ident49.ntide = :ntide");

        query.setParameter("ntide", ntide);

        Ident49 ident49Info;

        try {
            ident49Info = (Ident49) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Ident49NotFound();
        }

        Ident49 ident49 = ident49Info;

        return ident49;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Ident49> getListIdent49() {
        Query query = em.createQuery("select ident49 from Ident49 ident49 "
                + "where ident49.stide = 'SI'", Ident49.class);

        List<Ident49> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Ident49>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Ident49> retrieveIdent49() {
        Query query = em.createQuery("select ident49 from Ident49 ident49", Ident49.class);

        List<Ident49> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Ident49>();
        }

        return result;
    }

    /**
     *
     * @param ident49
     * @return
     * @throws Ident49AlreadyExists
     */
    @Override
    public Ident49 registerIdent49(Ident49 ident49) throws Ident49AlreadyExists {

        em.persist(ident49);
        em.flush();

        return ident49;
    }

    /**
     *
     * @param ident49
     * @throws Ident49NotFound
     */
    @Override
    public void removeIdent49(Ident49 ident49) throws Ident49NotFound {
        Ident49 updatableIdent49 = em.find(Ident49.class, ident49.getIdide());

        if (updatableIdent49 == null) {
            throw new Ident49NotFound();
        }
        if (!em.contains(ident49)) {
            ident49 = em.merge(ident49);
        }
        em.remove(ident49);
        em.flush();
    }

    /**
     *
     * @param ident49
     * @throws Ident49NotFound
     */
    @Override
    public void deleteIdent49(Ident49 ident49) throws Ident49NotFound {
        Ident49 updatableIdent49 = em.find(Ident49.class, ident49.getIdide());

        if (updatableIdent49 == null) {
            throw new Ident49NotFound();
        }

        em.merge(ident49);
        em.flush();
    }

    /**
     *
     * @param ident49
     * @throws Ident49NotFound
     */
    @Override
    public void updateIdent49(Ident49 ident49) throws Ident49NotFound {
        Ident49 updatableIdent49 = em.find(Ident49.class, ident49.getIdide());

        if (updatableIdent49 == null) {
            throw new Ident49NotFound();
        }

        em.merge(ident49);
        em.flush();
    }

}
