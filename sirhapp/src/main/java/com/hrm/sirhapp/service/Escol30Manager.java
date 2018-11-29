package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Escol30;
import com.hrm.sirhapp.service.exception.Escol30AlreadyExists;
import com.hrm.sirhapp.service.exception.Escol30NotFound;
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
public class Escol30Manager implements Escol30ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idesc
     * @return
     */
    @Override
    public Escol30 getEscol30(Integer idesc) {
        Query query = em.createQuery("SELECT escol30 FROM Escol30 escol30 WHERE "
                + "escol30.idesc = :idesc");

        query.setParameter("idesc", idesc);

        Escol30 escol30Info;

        escol30Info = (Escol30) query.getSingleResult();

        Escol30 escol30 = escol30Info;

        return escol30;
    }

    /**
     *
     * @param ntesc
     * @return
     */
    @Override
    public List<Escol30> getListEscol30(Integer ntesc) {
        Query query = em.createQuery("select escol30 from Escol30 escol30 "
                + "where escol30.ntesc = :ntesc", Escol30.class);

        query.setParameter("ntesc", ntesc);

        List<Escol30> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Escol30>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Escol30> retrieveEscol30() {
        Query query = em.createQuery("select escol30 from Escol30 escol30", Escol30.class);

        List<Escol30> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Escol30>();
        }

        return result;
    }

    /**
     *
     * @param escol30
     * @return
     * @throws Escol30AlreadyExists
     */
    @Override
    public Escol30 registerEscol30(Escol30 escol30) throws Escol30AlreadyExists {

        em.persist(escol30);
        em.flush();

        return escol30;
    }

    /**
     *
     * @param escol30
     * @throws Escol30NotFound
     */
    @Override
    public void removeEscol30(Escol30 escol30) throws Escol30NotFound {
        Escol30 updatableEscol30 = em.find(Escol30.class, escol30.getIdesc());

        if (updatableEscol30 == null) {
            throw new Escol30NotFound();
        }
        if (!em.contains(escol30)) {
            escol30 = em.merge(escol30);
        }

        em.remove(escol30);
        em.flush();
    }

    /**
     *
     * @param escol30
     * @throws Escol30NotFound
     */
    @Override
    public void deleteEscol30(Escol30 escol30) throws Escol30NotFound {
        Escol30 updatableEscol30 = em.find(Escol30.class, escol30.getIdesc());

        if (updatableEscol30 == null) {
            throw new Escol30NotFound();
        }

        em.merge(escol30);
        em.flush();
    }

    /**
     *
     * @param escol30
     * @throws Escol30NotFound
     */
    @Override
    public void updateEscol30(Escol30 escol30) throws Escol30NotFound {
        Escol30 updatableEscol30 = em.find(Escol30.class, escol30.getIdesc());

        if (updatableEscol30 == null) {
            throw new Escol30NotFound();
        }

        em.merge(escol30);
        em.flush();
    }

}
