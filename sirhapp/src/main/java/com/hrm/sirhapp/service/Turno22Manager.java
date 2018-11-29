package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Turno22;
import com.hrm.sirhapp.service.exception.Turno22AlreadyExists;
import com.hrm.sirhapp.service.exception.Turno22NotFound;
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
public class Turno22Manager implements Turno22ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idtur
     * @return
     * @throws Turno22NotFound
     */
    @Override
    public Turno22 getTurno22(Integer idtur) throws Turno22NotFound {
        Query query = em.createQuery("SELECT turno22 FROM Turno22 turno22 WHERE "
                + "turno22.idtur = :idtur");

        query.setParameter("idtur", idtur);

        Turno22 turno22Info;

        try {
            turno22Info = (Turno22) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Turno22NotFound();
        }

        return turno22Info;
    }

    /**
     *
     * @param cvtur
     * @return
     * @throws Turno22NotFound
     */
    @Override
    public Turno22 getTurno22(String cvtur) throws Turno22NotFound {
        Query query = em.createQuery("SELECT turno22 FROM Turno22 turno22 WHERE "
                + "turno22.cvtur = :cvtur");

        query.setParameter("cvtur", cvtur);

        Turno22 turno22Info;

        try {
            turno22Info = (Turno22) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Turno22NotFound();
        }

        return turno22Info;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Turno22> getListTurno22() {
        Query query = em.createQuery("select turno22 from Turno22 turno22 "
                + "where turno22.sttur = 'SI'", Turno22.class);

        List<Turno22> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Turno22>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Turno22> retrieveTurno22() {
        Query query = em.createQuery("select turno22 from Turno22 turno22", Turno22.class);

        List<Turno22> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Turno22>();
        }

        return result;
    }

    /**
     *
     * @param turno22
     * @return
     * @throws Turno22AlreadyExists
     */
    @Override
    public Turno22 registerTurno22(Turno22 turno22) throws Turno22AlreadyExists {

        em.persist(turno22);
        em.flush();

        return turno22;
    }

    /**
     *
     * @param turno22
     * @throws Turno22NotFound
     */
    @Override
    public void removeTurno22(Turno22 turno22) throws Turno22NotFound {
        Turno22 updatableTurno22 = em.find(Turno22.class, turno22.getIdtur());

        if (updatableTurno22 == null) {
            throw new Turno22NotFound();
        }
        if (!em.contains(turno22)) {
            turno22 = em.merge(turno22);
        }
        em.remove(turno22);
        em.flush();
    }

    /**
     *
     * @param turno22
     * @throws Turno22NotFound
     */
    @Override
    public void deleteTurno22(Turno22 turno22) throws Turno22NotFound {
        Turno22 updatableTurno22 = em.find(Turno22.class, turno22.getIdtur());

        if (updatableTurno22 == null) {
            throw new Turno22NotFound();
        }

        em.merge(turno22);
        em.flush();
    }

    /**
     *
     * @param turno22
     * @throws Turno22NotFound
     */
    @Override
    public void updateTurno22(Turno22 turno22) throws Turno22NotFound {
        Turno22 updatableTurno22 = em.find(Turno22.class, turno22.getIdtur());

        if (updatableTurno22 == null) {
            throw new Turno22NotFound();
        }

        em.merge(turno22);
        em.flush();
    }
}
