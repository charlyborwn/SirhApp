package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Infor39;
import com.hrm.sirhapp.service.exception.Infor39AlreadyExists;
import com.hrm.sirhapp.service.exception.Infor39NotFound;
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
public class Infor39Manager implements Infor39ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idinf
     * @return
     * @throws Infor39NotFound
     */
    @Override
    public Infor39 getInfor39(Integer idinf) throws Infor39NotFound {
        Query query = em.createQuery("SELECT infor39 FROM Infor39 infor39 where "
                + "infor39.idinf = :idinf");

        query.setParameter("idinf", idinf);

        Infor39 infor39Info;

        try {
            infor39Info = (Infor39) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Infor39NotFound();
        }

        Infor39 infor39 = infor39Info;

        return infor39;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Infor39> getListInfor39() {
        Query query = em.createQuery("select infor39 from Infor39 infor39 "
                + "where infor39.stinf = 'SI'", Infor39.class);

        List<Infor39> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Infor39>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Infor39> retrieveInfor39() {
        Query query = em.createQuery("select infor39 from Infor39 infor39", Infor39.class);

        List<Infor39> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Infor39>();
        }

        return result;
    }

    /**
     *
     * @param infor39
     * @return
     * @throws Infor39AlreadyExists
     */
    @Override
    public Infor39 registerInfor39(Infor39 infor39) throws Infor39AlreadyExists {

        em.persist(infor39);
        em.flush();

        return infor39;
    }

    /**
     *
     * @param infor39
     * @throws Infor39NotFound
     */
    @Override
    public void removeInfor39(Infor39 infor39) throws Infor39NotFound {
        Infor39 updatableInfor39 = em.find(Infor39.class, infor39.getIdinf());

        if (updatableInfor39 == null) {
            throw new Infor39NotFound();
        }
        if (!em.contains(infor39)) {
            infor39 = em.merge(infor39);
        }
        em.remove(infor39);
        em.flush();
    }

    /**
     *
     * @param infor39
     * @throws Infor39NotFound
     */
    @Override
    public void deleteInfor39(Infor39 infor39) throws Infor39NotFound {
        Infor39 updatableInfor39 = em.find(Infor39.class, infor39.getIdinf());

        if (updatableInfor39 == null) {
            throw new Infor39NotFound();
        }

        em.merge(infor39);
        em.flush();
    }

    /**
     *
     * @param infor39
     * @throws Infor39NotFound
     */
    @Override
    public void updateInfor39(Infor39 infor39) throws Infor39NotFound {
        Infor39 updatableInfor39 = em.find(Infor39.class, infor39.getIdinf());

        if (updatableInfor39 == null) {
            throw new Infor39NotFound();
        }

        em.merge(infor39);
        em.flush();
    }

}
