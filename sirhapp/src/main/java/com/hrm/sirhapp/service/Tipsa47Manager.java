package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Tipsa47;
import com.hrm.sirhapp.service.exception.Tipsa47AlreadyExists;
import com.hrm.sirhapp.service.exception.Tipsa47NotFound;
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
public class Tipsa47Manager implements Tipsa47ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idpsa
     * @return
     * @throws Tipsa47NotFound
     */
    @Override
    public Tipsa47 getTipsa47(Integer idpsa) throws Tipsa47NotFound {
        Query query = em.createQuery("SELECT tipsa47 FROM Tipsa47 tipsa47 WHERE "
                + "tipsa47.idpsa = :idpsa");

        query.setParameter("idpsa", idpsa);

        Tipsa47 tipsa47Info;

        try {
            tipsa47Info = (Tipsa47) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Tipsa47NotFound();
        }

        Tipsa47 tipsa47 = tipsa47Info;

        return tipsa47;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Tipsa47> getListTipsa47() {
        Query query = em.createQuery("select tipsa47 from Tipsa47 tipsa47 "
                + "where tipsa47.stpsa = 'SI'", Tipsa47.class);

        List<Tipsa47> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Tipsa47>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<String> getTopListTipsa47() {
        Query query = em.createQuery("select DISTINCT(tipsa47.grpsa) from Tipsa47 tipsa47 "
                + "where tipsa47.stpsa = 'SI' order by tipsa47.grpsa", String.class);

        List<String> result = query.getResultList();

        if (result == null) {
            return new ArrayList<String>();
        }

        return result;
    }

    /**
     *
     * @param grpsa
     * @return
     */
    @Override
    public List<Tipsa47> getListTipsa47(String grpsa) {
        Query query = em.createQuery("select tipsa47 from Tipsa47 tipsa47 "
                + "where tipsa47.stpsa = 'SI' and tipsa47.grpsa like :grpsa order by tipsa47.nopsa", Tipsa47.class);

        query.setParameter("grpsa", "%" + grpsa + "%");

        List<Tipsa47> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Tipsa47>();
        }

        return result;
    }

    /**
     *
     * @param grpsa
     * @param cepsa
     * @return
     */
    @Override
    public List<Tipsa47> getListTipsa47(String grpsa, String cepsa) {
        Query query = em.createQuery("select tipsa47 from Tipsa47 tipsa47 "
                + "where tipsa47.stpsa = 'SI' and tipsa47.grpsa like :grpsa and tipsa47.cepsa like :cepsa order by tipsa47.nopsa ", Tipsa47.class);

        query.setParameter("grpsa", "%" + grpsa + "%");
        query.setParameter("cepsa", "%" + cepsa + "%");

        List<Tipsa47> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Tipsa47>();
        }

        return result;
    }

    /**
     *
     * @param grpsa
     * @param hmpsa
     * @return
     */
    @Override
    public String getTipsa47Nopsa(String grpsa, String hmpsa) {

        Query query = em.createQuery("select tipsa47.nopsa from Tipsa47 tipsa47 "
                + "where tipsa47.stpsa = 'SI' and tipsa47.grpsa like :grpsa and tipsa47.hmpsa like :hmpsa order by tipsa47.nopsa", String.class);

        query.setParameter("grpsa", "%" + grpsa + "%");
        query.setParameter("hmpsa", "%" + hmpsa + "%");

        query.setFirstResult(0);
        query.setMaxResults(1);
        String result;

        try {
            result = (String) query.getSingleResult();
        } catch (NoResultException ex) {
            result = "";
        }

        if (result.equals("")) {
            result = grpsa;
        }

        return result;
    }

    /**
     *
     * @param grpsa
     * @param hmpsa
     * @param cepsa
     * @return
     */
    @Override
    public String getTipsa47Nopsa(String grpsa, String hmpsa, String cepsa) {

        Query query = em.createQuery("select tipsa47.nopsa from Tipsa47 tipsa47 "
                + "where tipsa47.stpsa = 'SI' and tipsa47.grpsa like :grpsa and tipsa47.cepsa like :cepsa and tipsa47.hmpsa like :hmpsa order by tipsa47.nopsa", String.class);

        query.setParameter("grpsa", "%" + grpsa + "%");
        query.setParameter("hmpsa", "%" + hmpsa + "%");
        query.setParameter("cepsa", "%" + cepsa + "%");

        query.setFirstResult(0);
        query.setMaxResults(1);
        String result;

        try {
            result = (String) query.getSingleResult();
        } catch (NoResultException ex) {
            result = "";
        }

        if (result.equals("")) {
            result = grpsa;
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Tipsa47> retrieveTipsa47() {
        Query query = em.createQuery("select tipsa47 from Tipsa47 tipsa47", Tipsa47.class);

        List<Tipsa47> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Tipsa47>();
        }

        return result;
    }

    /**
     *
     * @param tipsa47
     * @return
     * @throws Tipsa47AlreadyExists
     */
    @Override
    public Tipsa47 registerTipsa47(Tipsa47 tipsa47) throws Tipsa47AlreadyExists {

        em.persist(tipsa47);
        em.flush();

        return tipsa47;
    }

    /**
     *
     * @param tipsa47
     * @throws Tipsa47NotFound
     */
    @Override
    public void removeTipsa47(Tipsa47 tipsa47) throws Tipsa47NotFound {
        Tipsa47 updatableTipsa47 = em.find(Tipsa47.class, tipsa47.getIdpsa());

        if (updatableTipsa47 == null) {
            throw new Tipsa47NotFound();
        }

        if (!em.contains(tipsa47)) {
            tipsa47 = em.merge(tipsa47);
        }

        em.remove(tipsa47);
        em.flush();
    }

    /**
     *
     * @param tipsa47
     * @throws Tipsa47NotFound
     */
    @Override
    public void deleteTipsa47(Tipsa47 tipsa47) throws Tipsa47NotFound {
        Tipsa47 updatableTipsa47 = em.find(Tipsa47.class, tipsa47.getIdpsa());

        if (updatableTipsa47 == null) {
            throw new Tipsa47NotFound();
        }

        em.merge(tipsa47);
        em.flush();
    }

    /**
     *
     * @param tipsa47
     * @throws Tipsa47NotFound
     */
    @Override
    public void updateTipsa47(Tipsa47 tipsa47) throws Tipsa47NotFound {
        Tipsa47 updatableTipsa47 = em.find(Tipsa47.class, tipsa47.getIdpsa());

        if (updatableTipsa47 == null) {
            throw new Tipsa47NotFound();
        }

        em.merge(tipsa47);
        em.flush();
    }

}
