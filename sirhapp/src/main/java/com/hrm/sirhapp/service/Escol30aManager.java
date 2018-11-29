package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Escol30a;
import com.hrm.sirhapp.service.exception.Escol30aAlreadyExists;
import com.hrm.sirhapp.service.exception.Escol30aNotFound;
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
public class Escol30aManager implements Escol30aManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idescA
     * @return
     */
    @Override
    public Escol30a getEscol30a(Integer idescA) {
        Query query = em.createQuery("SELECT escol30a FROM Escol30a escol30a WHERE "
                + "escol30a.idescA = :idescA");

        query.setParameter("idescA", idescA);

        Escol30a escol30aInfo;

        escol30aInfo = (Escol30a) query.getSingleResult();

        Escol30a escol30a = escol30aInfo;

        return escol30a;
    }

    /**
     *
     * @param rfescA
     * @return
     */
    @Override
    public List<Escol30a> getListEscol30a(String rfescA) {
        Query query = em.createQuery("select escol30a from Escol30a escol30a "
                + "where escol30a.stescA='SI' and  escol30a.rfescA = :rfescA", Escol30a.class);

        query.setParameter("rfescA", rfescA);

        List<Escol30a> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Escol30a>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Escol30a> retrieveEscol30a() {
        Query query = em.createQuery("select escol30a from Escol30a escol30a", Escol30a.class);

        List<Escol30a> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Escol30a>();
        }

        return result;
    }

    /**
     *
     * @param escol30a
     * @return
     * @throws Escol30aAlreadyExists
     */
    @Override
    public Escol30a registerEscol30a(Escol30a escol30a) throws Escol30aAlreadyExists {

        em.persist(escol30a);
        em.flush();

        return escol30a;
    }

    /**
     *
     * @param escol30a
     * @throws Escol30aNotFound
     */
    @Override
    public void deleteEscol30a(Escol30a escol30a) throws Escol30aNotFound {
        Escol30a updatableEscol30a = em.find(Escol30a.class, escol30a.getIdescA());

        if (updatableEscol30a == null) {
            throw new Escol30aNotFound();
        }

        em.merge(escol30a);
        em.flush();
    }

    /**
     *
     * @param escol30a
     * @throws Escol30aNotFound
     */
    @Override
    public void removeEscol30a(Escol30a escol30a) throws Escol30aNotFound {
        Escol30a updatableEscol30a = em.find(Escol30a.class, escol30a.getIdescA());

        if (updatableEscol30a == null) {
            throw new Escol30aNotFound();
        }
        if (!em.contains(escol30a)) {
            escol30a = em.merge(escol30a);
        }
        em.remove(escol30a);
        em.flush();
    }

    /**
     *
     * @param escol30a
     * @throws Escol30aNotFound
     */
    @Override
    public void updateEscol30a(Escol30a escol30a) throws Escol30aNotFound {
        Escol30a updatableEscol30a = em.find(Escol30a.class, escol30a.getIdescA());

        if (updatableEscol30a == null) {
            throw new Escol30aNotFound();
        }

        em.merge(escol30a);
        em.flush();
    }

}
