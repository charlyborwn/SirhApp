package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Espec05;
import com.hrm.sirhapp.service.exception.Espec05AlreadyExists;
import com.hrm.sirhapp.service.exception.Espec05NotFound;
import java.util.ArrayList;
import java.util.Collections;
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
public class Espec05Manager implements Espec05ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idesp
     * @return
     * @throws Espec05NotFound
     */
    @Override
    public Espec05 getEspec05(Integer idesp) throws Espec05NotFound {
        Query query = em.createQuery("SELECT espec05 FROM Espec05 espec05 WHERE "
                + "espec05.idesp = :idesp");

        query.setParameter("idesp", idesp);

        Espec05 espec05Info;

        try {
            espec05Info = (Espec05) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Espec05NotFound();
        }

        Espec05 espec05 = espec05Info;

        return espec05;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Espec05> getListEspec05() {
        Query query = em.createQuery("select espec05 from Espec05 espec05 "
                + "where espec05.stesp = 'SI'", Espec05.class);

        List<Espec05> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Espec05>();
        }

        return result;
    }

    /**
     *
     * @param noesp
     * @return
     */
    @Override
    public List<String> getListEspec05Noesp(String noesp) {

        Query listIdsQry = em.createQuery("select espec05.idesp from Espec05 espec05 "
                + "where espec05.stesp = 'SI'", Integer.class);

        List<Integer> listIds = listIdsQry.getResultList();

        Collections.shuffle(listIds);

        int randomSeriesLength = 10;

        List<Integer> randomSeries = listIds.subList(0, randomSeriesLength);

        String str = "select espec05.noesp from Espec05 espec05 "
                + "where 1=1  and espec05.stesp = 'SI'";

        Query query = em.createQuery(str, String.class);

        if (noesp != null && noesp.length() > 0) {
            str = str + " and espec05.noesp like :noesp ";
            query = em.createQuery(str, String.class);
            query.setParameter("noesp", "%" + noesp + "%");
        } else {
            str = "SELECT espec05.noesp FROM Espec05 espec05 "
                    + "WHERE espec05.idesp IN :indexs";
            query = em.createQuery(str, String.class);
            query.setParameter("indexs", randomSeries);
        }

        List<String> result = query.getResultList();

        if (result == null) {
            return new ArrayList<String>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Espec05> retrieveEspec05() {
        Query query = em.createQuery("select espec05 from Espec05 espec05", Espec05.class);

        List<Espec05> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Espec05>();
        }

        return result;
    }

    /**
     *
     * @param espec05
     * @return
     * @throws Espec05AlreadyExists
     */
    @Override
    public Espec05 registerEspec05(Espec05 espec05) throws Espec05AlreadyExists {

        em.persist(espec05);
        em.flush();

        return espec05;
    }

    /**
     *
     * @param espec05
     * @throws Espec05NotFound
     */
    @Override
    public void removeEspec05(Espec05 espec05) throws Espec05NotFound {
        Espec05 updatableEspec05 = em.find(Espec05.class, espec05.getIdesp());

        if (updatableEspec05 == null) {
            throw new Espec05NotFound();
        }
        if (!em.contains(espec05)) {
            espec05 = em.merge(espec05);
        }
        em.remove(espec05);
        em.flush();
    }

    /**
     *
     * @param espec05
     * @throws Espec05NotFound
     */
    @Override
    public void deleteEspec05(Espec05 espec05) throws Espec05NotFound {
        Espec05 updatableEspec05 = em.find(Espec05.class, espec05.getIdesp());

        if (updatableEspec05 == null) {
            throw new Espec05NotFound();
        }

        em.merge(espec05);
        em.flush();
    }

    /**
     *
     * @param espec05
     * @throws Espec05NotFound
     */
    @Override
    public void updateEspec05(Espec05 espec05) throws Espec05NotFound {
        Espec05 updatableEspec05 = em.find(Espec05.class, espec05.getIdesp());

        if (updatableEspec05 == null) {
            throw new Espec05NotFound();
        }

        em.merge(espec05);
        em.flush();
    }

}
