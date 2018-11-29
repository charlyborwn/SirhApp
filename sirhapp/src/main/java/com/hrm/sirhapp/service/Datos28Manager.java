package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Datos28;
import com.hrm.sirhapp.service.exception.Datos28AlreadyExists;
import com.hrm.sirhapp.service.exception.Datos28NotFound;
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
public class Datos28Manager implements Datos28ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param ntdat
     * @return
     */
    @Override
    public Boolean alreadyExistsInnactive(Integer ntdat) {
        Query query = em.createQuery("SELECT datos28 FROM Datos28 datos28 WHERE "
                + "datos28.stdat='NO' and datos28.ntdat = :ntdat");

        query.setParameter("ntdat", ntdat);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    /**
     *
     * @param ntdat
     * @return
     * @throws Datos28NotFound
     */
    @Override
    public Datos28 getDatos28(Integer ntdat) throws Datos28NotFound {
        Query query = em.createQuery("SELECT datos28 FROM  Datos28 datos28 where "
                + "datos28.stdat='SI' and "
                + "datos28.ntdat = :ntdat");

        query.setParameter("ntdat", ntdat);

        Datos28 datos28Info;

        try {
            datos28Info = (Datos28) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Datos28NotFound();
        }

        Datos28 datos28 = datos28Info;

        return datos28;
    }

    /**
     *
     * @param ntdat
     * @return
     * @throws Datos28NotFound
     */
    @Override
    public Datos28 getDatos28ById(Integer ntdat) throws Datos28NotFound {
        Query query = em.createQuery("SELECT datos28 FROM  Datos28 datos28 where "
                + "datos28.ntdat = :ntdat");

        query.setParameter("ntdat", ntdat);

        Datos28 datos28Info;

        try {
            datos28Info = (Datos28) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Datos28NotFound();
        }

        Datos28 datos28 = datos28Info;

        return datos28;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Datos28> retrieveDatos28() {
        Query query = em.createQuery("select d from Datos28 d", Datos28.class);

        List<Datos28> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Datos28>();
        }

        return result;
    }

    /**
     *
     * @param datos28
     * @return
     * @throws Datos28AlreadyExists
     */
    @Override
    public Datos28 registerDatos28(Datos28 datos28) throws Datos28AlreadyExists {

        em.persist(datos28);
        em.flush();

        return datos28;
    }

    /**
     *
     * @param datos28
     * @throws Datos28NotFound
     */
    @Override
    public void removeDatos28(Datos28 datos28) throws Datos28NotFound {
        Datos28 updatableDatos28 = em.find(Datos28.class, datos28.getIddat());

        if (updatableDatos28 == null) {
            throw new Datos28NotFound();
        }
        if (!em.contains(datos28)) {
            datos28 = em.merge(datos28);
        }

        em.remove(datos28);
        em.flush();
    }

    /**
     *
     * @param datos28
     * @throws Datos28NotFound
     */
    @Override
    public void deleteDatos28(Datos28 datos28) throws Datos28NotFound {
        Datos28 updatableDatos28 = em.find(Datos28.class, datos28.getIddat());

        if (updatableDatos28 == null) {
            throw new Datos28NotFound();
        }

        em.merge(datos28);
        em.flush();
    }

    /**
     *
     * @param datos28
     * @throws Datos28NotFound
     */
    @Override
    public void updateDatos28(Datos28 datos28) throws Datos28NotFound {
        Datos28 updatableDatos28 = em.find(Datos28.class, datos28.getIddat());

        if (updatableDatos28 == null) {
            throw new Datos28NotFound();
        }

        em.merge(datos28);
        em.flush();
    }

}
