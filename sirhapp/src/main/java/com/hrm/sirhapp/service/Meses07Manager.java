package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Meses07;
import com.hrm.sirhapp.service.exception.Meses07AlreadyExists;
import com.hrm.sirhapp.service.exception.Meses07NotFound;
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
public class Meses07Manager implements Meses07ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idmes
     * @return
     * @throws Meses07NotFound
     */
    @Override
    public Meses07 getMeses07(Integer idmes) throws Meses07NotFound {
        Query query = em.createQuery("SELECT meses07 FROM Meses07 meses07 WHERE "
                + "meses07.idmes = :idmes");

        query.setParameter("idmes", idmes);

        Meses07 meses07Info;

        try {
            meses07Info = (Meses07) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Meses07NotFound();
        }

        Meses07 meses07 = meses07Info;

        return meses07;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Meses07> getListMeses07() {
        Query query = em.createQuery("select meses07 from Meses07 meses07 "
                + "where meses07.stmes = 'SI'", Meses07.class);

        List<Meses07> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Meses07>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Meses07> retrieveMeses07() {
        Query query = em.createQuery("select meses07 from Meses07 meses07", Meses07.class);

        List<Meses07> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Meses07>();
        }

        return result;
    }

    /**
     *
     * @param meses07
     * @return
     * @throws Meses07AlreadyExists
     */
    @Override
    public Meses07 registerMeses07(Meses07 meses07) throws Meses07AlreadyExists {

        em.persist(meses07);
        em.flush();

        return meses07;
    }

    /**
     *
     * @param meses07
     * @throws Meses07NotFound
     */
    @Override
    public void removeMeses07(Meses07 meses07) throws Meses07NotFound {
        Meses07 updatableMeses07 = em.find(Meses07.class, meses07.getIdmes());

        if (updatableMeses07 == null) {
            throw new Meses07NotFound();
        }
        if (!em.contains(meses07)) {
            meses07 = em.merge(meses07);
        }
        em.remove(meses07);
        em.flush();
    }

    /**
     *
     * @param meses07
     * @throws Meses07NotFound
     */
    @Override
    public void deleteMeses07(Meses07 meses07) throws Meses07NotFound {
        Meses07 updatableMeses07 = em.find(Meses07.class, meses07.getIdmes());

        if (updatableMeses07 == null) {
            throw new Meses07NotFound();
        }

        em.merge(meses07);
        em.flush();
    }

    /**
     *
     * @param meses07
     * @throws Meses07NotFound
     */
    @Override
    public void updateMeses07(Meses07 meses07) throws Meses07NotFound {
        Meses07 updatableMeses07 = em.find(Meses07.class, meses07.getIdmes());

        if (updatableMeses07 == null) {
            throw new Meses07NotFound();
        }

        em.merge(meses07);
        em.flush();
    }

}
