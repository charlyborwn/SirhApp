package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Tiinf38;
import com.hrm.sirhapp.service.exception.Tiinf38AlreadyExists;
import com.hrm.sirhapp.service.exception.Tiinf38NotFound;
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
public class Tiinf38Manager implements Tiinf38ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idtin
     * @return
     * @throws Tiinf38NotFound
     */
    @Override
    public Tiinf38 getTiinf38(Integer idtin) throws Tiinf38NotFound {
        Query query = em.createQuery("SELECT tiinf38 FROM Tiinf38 tiinf38 WHERE "
                + "tiinf38.idtin = :idtin");

        query.setParameter("idtin", idtin);

        Tiinf38 tiinf38Info;

        try {
            tiinf38Info = (Tiinf38) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Tiinf38NotFound();
        }

        Tiinf38 tiinf38 = tiinf38Info;

        return tiinf38;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Tiinf38> getListTiinf38() {
        Query query = em.createQuery("select tiinf38 from Tiinf38 tiinf38 "
                + "where tiinf38.sttin = 'SI'", Tiinf38.class);

        List<Tiinf38> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Tiinf38>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Tiinf38> retrieveTiinf38() {
        Query query = em.createQuery("select tiinf38 from Tiinf38 tiinf38", Tiinf38.class);

        List<Tiinf38> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Tiinf38>();
        }

        return result;
    }

    /**
     *
     * @param tiinf38
     * @return
     * @throws Tiinf38AlreadyExists
     */
    @Override
    public Tiinf38 registerTiinf38(Tiinf38 tiinf38) throws Tiinf38AlreadyExists {

        em.persist(tiinf38);
        em.flush();

        return tiinf38;
    }

    /**
     *
     * @param tiinf38
     * @throws Tiinf38NotFound
     */
    @Override
    public void removeTiinf38(Tiinf38 tiinf38) throws Tiinf38NotFound {
        Tiinf38 updatableTiinf38 = em.find(Tiinf38.class, tiinf38.getIdtin());

        if (updatableTiinf38 == null) {
            throw new Tiinf38NotFound();
        }

        if (!em.contains(tiinf38)) {
            tiinf38 = em.merge(tiinf38);
        }

        em.remove(tiinf38);
        em.flush();
    }

    /**
     *
     * @param tiinf38
     * @throws Tiinf38NotFound
     */
    @Override
    public void deleteTiinf38(Tiinf38 tiinf38) throws Tiinf38NotFound {
        Tiinf38 updatableTiinf38 = em.find(Tiinf38.class, tiinf38.getIdtin());

        if (updatableTiinf38 == null) {
            throw new Tiinf38NotFound();
        }

        em.merge(tiinf38);
        em.flush();
    }

    /**
     *
     * @param tiinf38
     * @throws Tiinf38NotFound
     */
    @Override
    public void updateTiinf38(Tiinf38 tiinf38) throws Tiinf38NotFound {
        Tiinf38 updatableTiinf38 = em.find(Tiinf38.class, tiinf38.getIdtin());

        if (updatableTiinf38 == null) {
            throw new Tiinf38NotFound();
        }

        em.merge(tiinf38);
        em.flush();
    }

}
