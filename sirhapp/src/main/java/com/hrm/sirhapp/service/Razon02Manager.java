package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Razon02;
import com.hrm.sirhapp.service.exception.Razon02AlreadyExists;
import com.hrm.sirhapp.service.exception.Razon02NotFound;
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
public class Razon02Manager implements Razon02ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idraz
     * @return
     * @throws Razon02NotFound
     */
    @Override
    public Razon02 getRazon02(Integer idraz) throws Razon02NotFound {
        Query query = em.createQuery("SELECT razon02 FROM Razon02 razon02 WHERE "
                + "razon02.idraz = :idraz");

        query.setParameter("idraz", idraz);

        Razon02 razon02Info;

        try {
            razon02Info = (Razon02) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Razon02NotFound();
        }

        Razon02 razon02 = razon02Info;

        return razon02;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Razon02> getListRazon02() {
        Query query = em.createQuery("select razon02 from Razon02 razon02 "
                + "where razon02.straz = 'SI' ", Razon02.class);

        List<Razon02> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Razon02>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Razon02> retrieveRazon02() {
        Query query = em.createQuery("select razon02 from Razon02 razon02", Razon02.class);

        List<Razon02> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Razon02>();
        }

        return result;
    }

    /**
     *
     * @param razon02
     * @return
     * @throws Razon02AlreadyExists
     */
    @Override
    public Razon02 registerRazon02(Razon02 razon02) throws Razon02AlreadyExists {

        em.persist(razon02);
        em.flush();

        return razon02;
    }

    /**
     *
     * @param razon02
     * @throws Razon02NotFound
     */
    @Override
    public void removeRazon02(Razon02 razon02) throws Razon02NotFound {
        Razon02 updatableRazon02 = em.find(Razon02.class, razon02.getIdraz());

        if (updatableRazon02 == null) {
            throw new Razon02NotFound();
        }
       if (!em.contains(razon02)) {
            razon02 = em.merge(razon02);
        }
        em.remove(razon02);
        em.flush();
    }

    /**
     *
     * @param razon02
     * @throws Razon02NotFound
     */
    @Override
    public void deleteRazon02(Razon02 razon02) throws Razon02NotFound {
        Razon02 updatableRazon02 = em.find(Razon02.class, razon02.getIdraz());

        if (updatableRazon02 == null) {
            throw new Razon02NotFound();
        }

        em.merge(razon02);
        em.flush();
    }

    /**
     *
     * @param razon02
     * @throws Razon02NotFound
     */
    @Override
    public void updateRazon02(Razon02 razon02) throws Razon02NotFound {
        Razon02 updatableRazon02 = em.find(Razon02.class, razon02.getIdraz());

        if (updatableRazon02 == null) {
            throw new Razon02NotFound();
        }

        em.merge(razon02);
        em.flush();
    }

}
