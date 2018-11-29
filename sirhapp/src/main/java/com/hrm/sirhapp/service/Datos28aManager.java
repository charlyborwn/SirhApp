package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Datos28a;
import com.hrm.sirhapp.service.exception.Datos28aAlreadyExists;
import com.hrm.sirhapp.service.exception.Datos28aNotFound;
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
public class Datos28aManager implements Datos28aManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param rfdatA
     * @return
     */
    @Override
    public Boolean alreadyExistsInnactive(String rfdatA) {
        Query query = em.createQuery("SELECT datos28a FROM Datos28a datos28a WHERE "
                + "datos28a.stdatA='NO' and datos28a.rfdatA = :rfdatA");

        query.setParameter("rfdatA", rfdatA);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    /**
     *
     * @param rfdatA
     * @return
     * @throws Datos28aNotFound
     */
    @Override
    public Datos28a getDatos28a(String rfdatA) throws Datos28aNotFound {
        Query query = em.createQuery("SELECT datos28a FROM Datos28a datos28a WHERE "
                + "datos28a.stdatA ='SI' and "
                + "datos28a.rfdatA = :rfdatA");

        query.setParameter("rfdatA", rfdatA);

        Datos28a datos28aInfo;

        try {
            datos28aInfo = (Datos28a) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Datos28aNotFound();
        }

        Datos28a datos28a = datos28aInfo;

        return datos28a;
    }

    /**
     *
     * @param rfdatA
     * @return
     * @throws Datos28aNotFound
     */
    @Override
    public Datos28a getDatos28aById(String rfdatA) throws Datos28aNotFound {
        Query query = em.createQuery("SELECT datos28a FROM  Datos28a datos28a where "
                + "datos28a.rfdatA = :rfdatA");

        query.setParameter("rfdatA", rfdatA);

        Datos28a datos28aInfo;

        try {
            datos28aInfo = (Datos28a) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Datos28aNotFound();
        }

        Datos28a datos28a = datos28aInfo;

        return datos28a;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Datos28a> retrieveDatos28a() {
        Query query = em.createQuery("select datos28a from Datos28a datos28a", Datos28a.class);

        List<Datos28a> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Datos28a>();
        }

        return result;
    }

    /**
     *
     * @param datos28a
     * @return
     * @throws Datos28aAlreadyExists
     */
    @Override
    public Datos28a registerDatos28a(Datos28a datos28a) throws Datos28aAlreadyExists {

        Datos28a lookUpDatos28a;
        try {
            lookUpDatos28a = getDatos28a(datos28a.getRfdatA());
            if (lookUpDatos28a != null) {
                throw new Datos28aAlreadyExists();
            }
        } catch (Datos28aNotFound ex) {
            em.persist(datos28a);
            em.flush();
        }

        return datos28a;
    }

    /**
     *
     * @param datos28a
     * @throws Datos28aNotFound
     */
    @Override
    public void deleteDatos28a(Datos28a datos28a) throws Datos28aNotFound {
        Datos28a updatableDatos28a = em.find(Datos28a.class, datos28a.getIddatA());

        if (updatableDatos28a == null) {
            throw new Datos28aNotFound();
        }

        em.merge(datos28a);
        em.flush();
    }

    /**
     *
     * @param datos28a
     * @throws Datos28aNotFound
     */
    @Override
    public void removeDatos28a(Datos28a datos28a) throws Datos28aNotFound {
        Datos28a updatableDatos28a = em.find(Datos28a.class, datos28a.getIddatA());

        if (updatableDatos28a == null) {
            throw new Datos28aNotFound();
        }

        if (!em.contains(datos28a)) {
            datos28a = em.merge(datos28a);
        }

        em.remove(datos28a);
        em.flush();
    }

    /**
     *
     * @param datos28a
     * @throws Datos28aNotFound
     */
    @Override
    public void updateDatos28a(Datos28a datos28a) throws Datos28aNotFound {
        Datos28a updatableDatos28a = em.find(Datos28a.class, datos28a.getIddatA());

        if (updatableDatos28a == null) {
            throw new Datos28aNotFound();
        }

        em.merge(datos28a);
        em.flush();
    }

}
