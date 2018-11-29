package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Prypr34;
import com.hrm.sirhapp.service.exception.Prypr34AlreadyExists;
import com.hrm.sirhapp.service.exception.Prypr34NotFound;
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
public class Prypr34Manager implements Prypr34ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idpry
     * @return
     * @throws Prypr34NotFound
     */
    @Override
    public Prypr34 getPrypr34(Integer idpry) throws Prypr34NotFound {
        Query query = em.createQuery("SELECT prypr34 FROM Prypr34 prypr34 WHERE "
                + "prypr34.idpry = :idpry");

        query.setParameter("idpry", idpry);

        Prypr34 prypr34Info;

        try {
            prypr34Info = (Prypr34) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Prypr34NotFound();
        }

        Prypr34 prypr34 = prypr34Info;

        return prypr34;
    }

    /**
     *
     * @param idpry
     * @return
     */
    @Override
    public Boolean alreadyExistsInnactive(Integer idpry) {
        Query query = em.createQuery("select prypr34 from Prypr34 prypr34 "
                + "where prypr34.stpry='SI' and prypr34.idpry = :idpry");

        query.setParameter("idpry", idpry);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    /**
     *
     * @param nupry
     * @return
     */
    @Override
    public List<Prypr34> getListPrypr34(String nupry) {
        Query query = em.createQuery("select prypr34 from Prypr34 prypr34 "
                + "where prypr34.stpry='SI' and prypr34.nupry = :nupry", Prypr34.class);

        query.setParameter("nupry", nupry);

        List<Prypr34> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Prypr34>();
        }

        return result;
    }

    /**
     *
     * @param nupry
     * @param tppry
     * @return
     */
    @Override
    public List<Prypr34> getListPrypr34(String nupry, String tppry) {
        Query query = em.createQuery("select prypr34 from Prypr34 prypr34 "
                + "where prypr34.stpry='SI' and prypr34.tppry= :tppry and prypr34.nupry = :nupry", Prypr34.class);

        query.setParameter("tppry", tppry);
        query.setParameter("nupry", nupry);

        List<Prypr34> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Prypr34>();
        }

        return result;

    }

    /**
     *
     * @param nutra
     * @return
     */
    @Override
    public List<Prypr34> getListPrypr34Traba36(Integer nutra) {
        Query query = em.createQuery("select prypr34 from Prypr34 prypr34 where "
                + "prypr34.ntpry = :ntpry and prypr34.stpry='SI'", Prypr34.class);

        query.setParameter("ntpry", nutra);

        List<Prypr34> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Prypr34>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Prypr34> retrievePrypr34() {
        Query query = em.createQuery("select prypr34 from Prypr34 prypr34", Prypr34.class);

        List<Prypr34> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Prypr34>();
        }

        return result;
    }

    /**
     *
     * @param prypr34
     * @return
     * @throws Prypr34AlreadyExists
     */
    @Override
    public Prypr34 registerPrypr34(Prypr34 prypr34) throws Prypr34AlreadyExists {

        em.persist(prypr34);
        em.flush();

        return prypr34;
    }

    /**
     *
     * @param prypr34
     * @throws Prypr34NotFound
     */
    @Override
    public void removePrypr34(Prypr34 prypr34) throws Prypr34NotFound {
        Prypr34 updatablePrypr34 = em.find(Prypr34.class, prypr34.getIdpry());

        if (updatablePrypr34 == null) {
            throw new Prypr34NotFound();
        }
        if (!em.contains(prypr34)) {
            prypr34 = em.merge(prypr34);
        }
        em.remove(prypr34);
        em.flush();
    }

    /**
     *
     * @param prypr34
     * @return
     * @throws Prypr34NotFound
     */
    @Override
    public Prypr34 deletePrypr34(Prypr34 prypr34) throws Prypr34NotFound {
        Prypr34 updatablePrypr34 = em.find(Prypr34.class, prypr34.getIdpry());

        if (updatablePrypr34 == null) {
            throw new Prypr34NotFound();
        }

        Prypr34 updatedPrypr34 = em.merge(prypr34);

        em.flush();

        return updatedPrypr34;
    }

    /**
     *
     * @param prypr34
     * @return
     * @throws Prypr34NotFound
     */
    @Override
    public Prypr34 updatePrypr34(Prypr34 prypr34) throws Prypr34NotFound {
        Prypr34 updatablePrypr34 = em.find(Prypr34.class, prypr34.getIdpry());

        if (updatablePrypr34 == null) {
            throw new Prypr34NotFound();
        }

        Prypr34 updatedPrypr34 = em.merge(prypr34);

        em.flush();

        return updatedPrypr34;
    }

}
