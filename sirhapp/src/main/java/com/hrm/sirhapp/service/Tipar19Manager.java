package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Tipar19;
import com.hrm.sirhapp.service.exception.Tipar19AlreadyExists;
import com.hrm.sirhapp.service.exception.Tipar19NotFound;
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
public class Tipar19Manager implements Tipar19ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idtip
     * @return
     * @throws Tipar19NotFound
     */
    @Override
    public Tipar19 getTipar19(Integer idtip) throws Tipar19NotFound {
        Query query = em.createQuery("SELECT tipar19 FROM Tipar19 tipar19 WHERE "
                + "tipar19.idtip = :idtip");

        query.setParameter("idtip", idtip);

        Tipar19 tipar19Info;

        try {
            tipar19Info = (Tipar19) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Tipar19NotFound();
        }

        Tipar19 tipar19 = tipar19Info;

        return tipar19;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Tipar19> getListTipar19() {
        Query query = em.createQuery("select tipar19 from Tipar19 tipar19 "
                + "where tipar19.sttip = 'SI'", Tipar19.class);

        List<Tipar19> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Tipar19>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Tipar19> retrieveTipar19() {
        Query query = em.createQuery("select tipar19 from Tipar19 tipar19", Tipar19.class);

        List<Tipar19> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Tipar19>();
        }

        return result;
    }

    /**
     *
     * @param tipar19
     * @return
     * @throws Tipar19AlreadyExists
     */
    @Override
    public Tipar19 registerTipar19(Tipar19 tipar19) throws Tipar19AlreadyExists {

        em.persist(tipar19);
        em.flush();

        return tipar19;
    }

    /**
     *
     * @param tipar19
     * @throws Tipar19NotFound
     */
    @Override
    public void removeTipar19(Tipar19 tipar19) throws Tipar19NotFound {
        Tipar19 updatableTipar19 = em.find(Tipar19.class, tipar19.getIdtip());

        if (updatableTipar19 == null) {
            throw new Tipar19NotFound();
        }

        if (!em.contains(tipar19)) {
            tipar19 = em.merge(tipar19);
        }

        em.remove(tipar19);
        em.flush();
    }

    /**
     *
     * @param tipar19
     * @throws Tipar19NotFound
     */
    @Override
    public void deleteTipar19(Tipar19 tipar19) throws Tipar19NotFound {
        Tipar19 updatableTipar19 = em.find(Tipar19.class, tipar19.getIdtip());

        if (updatableTipar19 == null) {
            throw new Tipar19NotFound();
        }

        em.merge(tipar19);
        em.flush();
    }

    /**
     *
     * @param tipar19
     * @throws Tipar19NotFound
     */
    @Override
    public void updateTipar19(Tipar19 tipar19) throws Tipar19NotFound {
        Tipar19 updatableTipar19 = em.find(Tipar19.class, tipar19.getIdtip());

        if (updatableTipar19 == null) {
            throw new Tipar19NotFound();
        }

        em.merge(tipar19);
        em.flush();
    }
}
