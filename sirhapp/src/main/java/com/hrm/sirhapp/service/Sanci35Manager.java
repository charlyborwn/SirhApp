package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Sanci35;
import com.hrm.sirhapp.service.exception.Sanci35AlreadyExists;
import com.hrm.sirhapp.service.exception.Sanci35NotFound;
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
public class Sanci35Manager implements Sanci35ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idsan
     * @return
     * @throws Sanci35NotFound
     */
    @Override
    public Sanci35 getSanci35(Integer idsan) throws Sanci35NotFound {
        Query query = em.createQuery("SELECT sanci35 FROM Sanci35 sanci35 WHERE "
                + "sanci35.idsan = :idsan");

        query.setParameter("idsan", idsan);

        Sanci35 sanci35Info;

        try {
            sanci35Info = (Sanci35) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Sanci35NotFound();
        }

        Sanci35 sanci35 = sanci35Info;

        return sanci35;
    }

    /**
     *
     * @param idsan
     * @return
     */
    @Override
    public Boolean alreadyExistsInnactive(Integer idsan) {
        Query query = em.createQuery("select sanci35 from Sanci35 sanci35 "
                + "WHERE sanci35.stsan='SI' and sanci35.idsan = :idsan");

        query.setParameter("idsan", idsan);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    /**
     *
     * @param nusan
     * @return
     */
    @Override
    public List<Sanci35> getListSanci35(String nusan) {
        Query query = em.createQuery("select sanci35 from Sanci35 sanci35 "
                + "where sanci35.stsan='SI' and sanci35.nusan = :nusan", Sanci35.class);

        query.setParameter("nusan", nusan);

        List<Sanci35> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Sanci35>();
        }

        return result;
    }

    /**
     *
     * @param nusan
     * @param cusan
     * @return
     */
    @Override
    public List<Sanci35> getListSanci35(String nusan, String cusan) {
        Query query = em.createQuery("select sanci35 from Sanci35 sanci35 "
                + "where sanci35.stsan='SI' and sanci35.cusan= :cusan and sanci35.nusan = :nusan", Sanci35.class);

        query.setParameter("cusan", cusan);
        query.setParameter("nusan", nusan);

        List<Sanci35> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Sanci35>();
        }

        return result;

    }

    /**
     *
     * @param nutra
     * @return
     */
    @Override
    public List<Sanci35> getListSanci35Traba36(Integer nutra){
        Query query = em.createQuery("select sanci35 from Sanci35 sanci35 where "
                + "sanci35.ntsan = :ntsan and sanci35.stsan='SI'", Sanci35.class);

        query.setParameter("ntsan", nutra);

        List<Sanci35> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Sanci35>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Sanci35> retrieveSanci35() {
        Query query = em.createQuery("select sanci35 from Sanci35 sanci35", Sanci35.class);

        List<Sanci35> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Sanci35>();
        }

        return result;
    }

    /**
     *
     * @param sanci35
     * @return
     * @throws Sanci35AlreadyExists
     */
    @Override
    public Sanci35 registerSanci35(Sanci35 sanci35) throws Sanci35AlreadyExists {

        em.persist(sanci35);
        em.flush();

        return sanci35;
    }

    /**
     *
     * @param sanci35
     * @throws Sanci35NotFound
     */
    @Override
    public void removeSanci35(Sanci35 sanci35) throws Sanci35NotFound {
        Sanci35 updatableSanci35 = em.find(Sanci35.class, sanci35.getIdsan());

        if (updatableSanci35 == null) {
            throw new Sanci35NotFound();
        }

        if (!em.contains(sanci35)) {
            sanci35 = em.merge(sanci35);
        }

        em.remove(sanci35);
        em.flush();
    }

    /**
     *
     * @param sanci35
     * @return
     * @throws Sanci35NotFound
     */
    @Override
    public Sanci35 deleteSanci35(Sanci35 sanci35) throws Sanci35NotFound {
        Sanci35 updatableSanci35 = em.find(Sanci35.class, sanci35.getIdsan());

        if (updatableSanci35 == null) {
            throw new Sanci35NotFound();
        }
        Sanci35 updatedApriv25 = em.merge(sanci35);
        em.flush();
        return updatedApriv25;
    }

    /**
     *
     * @param sanci35
     * @return
     * @throws Sanci35NotFound
     */
    @Override
    public Sanci35 updateSanci35(Sanci35 sanci35) throws Sanci35NotFound {
        Sanci35 updatableSanci35 = em.find(Sanci35.class, sanci35.getIdsan());

        if (updatableSanci35 == null) {
            throw new Sanci35NotFound();
        }
        Sanci35 updatedApriv25 = em.merge(sanci35);
        em.flush();
        return updatedApriv25;
    }

}
