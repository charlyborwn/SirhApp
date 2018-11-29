package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Ident49a;
import com.hrm.sirhapp.service.exception.Ident49aAlreadyExists;
import com.hrm.sirhapp.service.exception.Ident49aNotFound;
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
public class Ident49aManager implements Ident49aManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param rfideA
     * @return
     */
    @Override
    public Boolean alreadyExistsInnactive(String rfideA) {
        Query query = em.createQuery("SELECT ident49a FROM Ident49a ident49a WHERE "
                + "ident49a.stideA='NO' and ident49a.rfideA = :rfideA");

        query.setParameter("rfideA", rfideA);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    /**
     *
     * @param rfideA
     * @return
     * @throws Ident49aNotFound
     */
    @Override
    public Ident49a getIdent49a(String rfideA) throws Ident49aNotFound {
        Query query = em.createQuery("SELECT ident49a FROM Ident49a ident49a WHERE "
                + "ident49a.stideA='SI' and ident49a.rfideA = :rfideA");

        query.setParameter("rfideA", rfideA);

        Ident49a ident49aInfo;

        try {
            ident49aInfo = (Ident49a) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Ident49aNotFound();
        }

        Ident49a ident49a = ident49aInfo;

        return ident49a;
    }

    /**
     *
     * @param rfideA
     * @return
     * @throws Ident49aNotFound
     */
    @Override
    public Ident49a getIdent49aById(String rfideA) throws Ident49aNotFound {
        Query query = em.createQuery("SELECT ident49a FROM  Ident49a ident49a where "
                + "ident49a.rfideA = :rfideA");

        query.setParameter("rfideA", rfideA);

        Ident49a ident49aInfo;

        try {
            ident49aInfo = (Ident49a) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Ident49aNotFound();
        }

        Ident49a ident49a = ident49aInfo;

        return ident49a;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Ident49a> getListIdent49a() {
        Query query = em.createQuery("select ident49a from Ident49a ident49a "
                + "where ident49a.stide = 'SI'", Ident49a.class);

        List<Ident49a> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Ident49a>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Ident49a> retrieveIdent49a() {
        Query query = em.createQuery("select ident49a from Ident49a ident49a", Ident49a.class);

        List<Ident49a> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Ident49a>();
        }

        return result;
    }

    /**
     *
     * @param ident49a
     * @return
     * @throws Ident49aAlreadyExists
     */
    @Override
    public Ident49a registerIdent49a(Ident49a ident49a) throws Ident49aAlreadyExists {

        em.persist(ident49a);
        em.flush();

        return ident49a;
    }

    /**
     *
     * @param ident49a
     * @throws Ident49aNotFound
     */
    @Override
    public void deleteIdent49a(Ident49a ident49a) throws Ident49aNotFound {
        Ident49a updatableIdent49a = em.find(Ident49a.class, ident49a.getIdideA());

        if (updatableIdent49a == null) {
            throw new Ident49aNotFound();
        }

        em.merge(ident49a);
        em.flush();
    }

    /**
     *
     * @param ident49a
     * @throws Ident49aNotFound
     */
    @Override
    public void removeIdent49a(Ident49a ident49a) throws Ident49aNotFound {
        Ident49a updatableIdent49a = em.find(Ident49a.class, ident49a.getIdideA());

        if (updatableIdent49a == null) {
            throw new Ident49aNotFound();
        }
        if (!em.contains(ident49a)) {
            ident49a = em.merge(ident49a);
        }
        em.remove(ident49a);
        em.flush();
    }

    /**
     *
     * @param ident49a
     * @throws Ident49aNotFound
     */
    @Override
    public void updateIdent49a(Ident49a ident49a) throws Ident49aNotFound {
        Ident49a updatableIdent49a = em.find(Ident49a.class, ident49a.getIdideA());

        if (updatableIdent49a == null) {
            throw new Ident49aNotFound();
        }

        em.merge(ident49a);
        em.flush();
    }

}
