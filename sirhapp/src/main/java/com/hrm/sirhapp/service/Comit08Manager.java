package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Comit08;
import com.hrm.sirhapp.service.exception.Comit08AlreadyExists;
import com.hrm.sirhapp.service.exception.Comit08NotFound;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Stateless
public class Comit08Manager implements Comit08ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idcom
     * @return
     * @throws Comit08NotFound
     */
    @Override
    public Comit08 getComit08(Integer idcom) throws Comit08NotFound {
        Query query = em.createQuery("SELECT comit08 FROM Comit08 comit08 WHERE "
                + "comit08.idcom = :idcom");

        query.setParameter("idcom", idcom);

        Comit08 comit08Info;

        try {
            comit08Info = (Comit08) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Comit08NotFound();
        }

        Comit08 comit08 = comit08Info;

        return comit08;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Comit08> getListComit08() {
        Query query = em.createQuery("select comit08 from Comit08 comit08 "
                + "where comit08.stcom = 'SI' ", Comit08.class);

        List<Comit08> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Comit08>();
        }

        return result;
    }

    /**
     *
     * @param ntcom
     * @return
     */
    @Override
    public String getComit08Ntcom(Integer ntcom) {
        TypedQuery<String> query = em.createQuery("select comit08.npcom from Comit08 comit08 "
                + "WHERE comit08.stcom = 'SI' and comit08.ntcom = :ntcom ", String.class);

        query.setParameter("ntcom", ntcom);
        query.setFirstResult(0);
        query.setMaxResults(1);
        String result = query.getSingleResult();

        System.out.println("ID->" + ntcom + ":NOMBRE->" + result);

        if (result == null) {
            return "";
        }

        return result;
    }

    /**
     *
     * @param cecom
     * @param cdcom
     * @return
     */
    @Override
    public List<Comit08> getListComit08CecomCdcom(String cecom, String cdcom) {
        Query query = em.createQuery("select comit08 from Comit08 comit08 "
                + "where comit08.stcom = 'SI' and comit08.cecom like :cecom and comit08.cdcom like :cdcom", Comit08.class);

        query.setParameter("cecom", "%" + cecom + "%");
        query.setParameter("cdcom", "%" + cdcom + "%");

        System.out.println("cecom " + cecom + "%");
        System.out.println("cdcom " + cdcom + "%");

        List<Comit08> result = query.getResultList();

        System.out.println(query.toString());

        if (result == null) {
            return new ArrayList<Comit08>();
        }

        return result;
    }

    /**
     *
     * @param cecom
     * @param cdcom
     * @param cvcom
     * @return
     */
    @Override
    public String getComit08CecomCdcom(String cecom, String cdcom, String cvcom) {
        String result="";
        try {
            Query query = em.createQuery("select comit08.npcom from Comit08 comit08 "
                    + "where "
                    + "comit08.stcom = 'SI' and "
                    + "comit08.cecom like :cecom and "
                    + "comit08.cdcom like :cdcom and "
                    + "comit08.cvcom like :cvcom ", String.class);

            query.setParameter("cecom", "%" + cecom + "%");
            query.setParameter("cdcom", "%" + cdcom + "%");
            query.setParameter("cvcom", "%" + cvcom + "%");

            System.out.println("cecom " + cecom + "%");
            System.out.println("cdcom " + cdcom + "%");
            System.out.println("cvcom " + cvcom + "%");

            query.setFirstResult(0);
            query.setMaxResults(1);
            result = (String) query.getSingleResult();

            System.out.println(query.toString());

            if (result == null) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Comit08> retrieveComit08() {
        Query query = em.createQuery("select comit08 from Comit08 comit08", Comit08.class);

        List<Comit08> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Comit08>();
        }

        return result;
    }

    /**
     *
     * @param comit08
     * @return
     * @throws Comit08AlreadyExists
     */
    @Override
    public Comit08 registerComit08(Comit08 comit08) throws Comit08AlreadyExists {

        em.persist(comit08);
        em.flush();

        return comit08;
    }

    /**
     *
     * @param comit08
     * @throws Comit08NotFound
     */
    @Override
    public void removeComit08(Comit08 comit08) throws Comit08NotFound {
        Comit08 updatableComit08 = em.find(Comit08.class, comit08.getIdcom());

        if (updatableComit08 == null) {
            throw new Comit08NotFound();
        }

        if (!em.contains(comit08)) {
            comit08 = em.merge(comit08);
        }
        em.remove(comit08);
        em.flush();
    }

    /**
     *
     * @param comit08
     * @throws Comit08NotFound
     */
    @Override
    public void deleteComit08(Comit08 comit08) throws Comit08NotFound {
        Comit08 updatableComit08 = em.find(Comit08.class, comit08.getIdcom());

        if (updatableComit08 == null) {
            throw new Comit08NotFound();
        }

        em.merge(comit08);
        em.flush();
    }

    /**
     *
     * @param comit08
     * @throws Comit08NotFound
     */
    @Override
    public void updateComit08(Comit08 comit08) throws Comit08NotFound {
        Comit08 updatableComit08 = em.find(Comit08.class, comit08.getIdcom());

        if (updatableComit08 == null) {
            throw new Comit08NotFound();
        }

        em.merge(comit08);
        em.flush();
    }

}
