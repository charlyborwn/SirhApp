package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Nacio09;
import com.hrm.sirhapp.service.exception.Nacio09AlreadyExists;
import com.hrm.sirhapp.service.exception.Nacio09NotFound;
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
public class Nacio09Manager implements Nacio09ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idnac
     * @return
     * @throws Nacio09NotFound
     */
    @Override
    public Nacio09 getNacio09(Integer idnac) throws Nacio09NotFound {
        Query query = em.createQuery("SELECT nacio09 FROM Nacio09 nacio09 WHERE "
                + "nacio09.idnac = :idnac");

        query.setParameter("idnac", idnac);

        Nacio09 nacio09Info;

        try {
            nacio09Info = (Nacio09) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Nacio09NotFound();
        }

        Nacio09 nacio09 = nacio09Info;

        return nacio09;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Nacio09> getListNacio09() {
        Query query = em.createQuery("select nacio09 from Nacio09 nacio09 "
                + "where nacio09.stnac = 'SI'", Nacio09.class);

        List<Nacio09> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Nacio09>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Nacio09> retrieveNacio09() {
        Query query = em.createQuery("select nacio09 from Nacio09 nacio09", Nacio09.class);

        List<Nacio09> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Nacio09>();
        }

        return result;
    }

    /**
     *
     * @param nacio09
     * @return
     * @throws Nacio09AlreadyExists
     */
    @Override
    public Nacio09 registerNacio09(Nacio09 nacio09) throws Nacio09AlreadyExists {

        em.persist(nacio09);
        em.flush();

        return nacio09;
    }

    /**
     *
     * @param nacio09
     * @throws Nacio09NotFound
     */
    @Override
    public void deleteNacio09(Nacio09 nacio09) throws Nacio09NotFound {
        Nacio09 updatableNacio09 = em.find(Nacio09.class, nacio09.getIdnac());

        if (updatableNacio09 == null) {
            throw new Nacio09NotFound();
        }

        em.merge(nacio09);
        em.flush();
    }

    /**
     *
     * @param nacio09
     * @throws Nacio09NotFound
     */
    @Override
    public void removeNacio09(Nacio09 nacio09) throws Nacio09NotFound {
        Nacio09 updatableNacio09 = em.find(Nacio09.class, nacio09.getIdnac());

        if (updatableNacio09 == null) {
            throw new Nacio09NotFound();
        }
        if (!em.contains(nacio09)) {
            nacio09 = em.merge(nacio09);
        }
        em.remove(nacio09);
        em.flush();
    }

    /**
     *
     * @param nacio09
     * @throws Nacio09NotFound
     */
    @Override
    public void updateNacio09(Nacio09 nacio09) throws Nacio09NotFound {
        Nacio09 updatableNacio09 = em.find(Nacio09.class, nacio09.getIdnac());

        if (updatableNacio09 == null) {
            throw new Nacio09NotFound();
        }

        em.merge(nacio09);
        em.flush();
    }

}
