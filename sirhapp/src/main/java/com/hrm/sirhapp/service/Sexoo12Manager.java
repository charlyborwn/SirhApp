package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Sexoo12;
import com.hrm.sirhapp.service.exception.Sexoo12AlreadyExists;
import com.hrm.sirhapp.service.exception.Sexoo12NotFound;
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
public class Sexoo12Manager implements Sexoo12ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idsex
     * @return
     * @throws Sexoo12NotFound
     */
    @Override
    public Sexoo12 getSexoo12(Integer idsex) throws Sexoo12NotFound {
        Query query = em.createQuery("SELECT sexoo12 FROM Sexoo12 sexoo12 where "
                + "sexoo12.idsex = :idsex");

        query.setParameter("idsex", idsex);

        Sexoo12 sexoo12Info;

        try {
            sexoo12Info = (Sexoo12) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Sexoo12NotFound();
        }

        Sexoo12 sexoo12 = sexoo12Info;

        return sexoo12;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Sexoo12> getListSexoo12() {
        Query query = em.createQuery("select sexoo12 from Sexoo12 sexoo12 "
                + "where sexoo12.stsex = 'SI'", Sexoo12.class);

        List<Sexoo12> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Sexoo12>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Sexoo12> retrieveSexoo12() {
        Query query = em.createQuery("select sexoo12 from Sexoo12 sexoo12", Sexoo12.class);

        List<Sexoo12> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Sexoo12>();
        }

        return result;
    }

    /**
     *
     * @param sexoo12
     * @return
     * @throws Sexoo12AlreadyExists
     */
    @Override
    public Sexoo12 registerSexoo12(Sexoo12 sexoo12) throws Sexoo12AlreadyExists {

        em.persist(sexoo12);
        em.flush();

        return sexoo12;
    }

    /**
     *
     * @param sexoo12
     * @throws Sexoo12NotFound
     */
    @Override
    public void removeSexoo12(Sexoo12 sexoo12) throws Sexoo12NotFound {
        Sexoo12 updatableSexoo12 = em.find(Sexoo12.class, sexoo12.getIdsex());

        if (updatableSexoo12 == null) {
            throw new Sexoo12NotFound();
        }
        if (!em.contains(sexoo12)) {
            sexoo12 = em.merge(sexoo12);
        }
        em.remove(sexoo12);
        em.flush();
    }

    /**
     *
     * @param sexoo12
     * @throws Sexoo12NotFound
     */
    @Override
    public void deleteSexoo12(Sexoo12 sexoo12) throws Sexoo12NotFound {
        Sexoo12 updatableSexoo12 = em.find(Sexoo12.class, sexoo12.getIdsex());

        if (updatableSexoo12 == null) {
            throw new Sexoo12NotFound();
        }

        em.merge(sexoo12);
        em.flush();
    }

    /**
     *
     * @param sexoo12
     * @throws Sexoo12NotFound
     */
    @Override
    public void updateSexoo12(Sexoo12 sexoo12) throws Sexoo12NotFound {
        Sexoo12 updatableSexoo12 = em.find(Sexoo12.class, sexoo12.getIdsex());

        if (updatableSexoo12 == null) {
            throw new Sexoo12NotFound();
        }

        em.merge(sexoo12);
        em.flush();
    }

}
