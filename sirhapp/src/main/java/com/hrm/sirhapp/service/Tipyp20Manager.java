package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Tipyp20;
import com.hrm.sirhapp.service.exception.Tipyp20AlreadyExists;
import com.hrm.sirhapp.service.exception.Tipyp20NotFound;
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
public class Tipyp20Manager implements Tipyp20ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idpyp
     * @return
     * @throws Tipyp20NotFound
     */
    @Override
    public Tipyp20 getTipyp20(Integer idpyp) throws Tipyp20NotFound {
        Query query = em.createQuery("SELECT tipyp20 FROM Tipyp20 tipyp20 WHERE "
                + "tipyp20.idpyp = :idpyp");

        query.setParameter("idpyp", idpyp);

        Tipyp20 tipyp20Info;

        try {
            tipyp20Info = (Tipyp20) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Tipyp20NotFound();
        }

        Tipyp20 tipyp20 = tipyp20Info;

        return tipyp20;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Tipyp20> getListTipyp20() {
        Query query = em.createQuery("select tipyp20 from Tipyp20 tipyp20 "
                + "where tipyp20.stpyp = 'SI'", Tipyp20.class);

        List<Tipyp20> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Tipyp20>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<String> getTopListTipyp20() {
        Query query = em.createQuery("select DISTINCT(tipyp20.grpyp) from Tipyp20 tipyp20 "
                + "where tipyp20.stpyp = 'SI' order by tipyp20.grpyp", String.class);

        List<String> result = query.getResultList();

        if (result == null) {
            return new ArrayList<String>();
        }

        return result;
    }

    /**
     *
     * @param grpyp
     * @return
     */
    @Override
    public List<Tipyp20> getListTipyp20(String grpyp) {
        Query query = em.createQuery("select tipyp20 from Tipyp20 tipyp20 "
                + "where tipyp20.stpyp = 'SI' and tipyp20.grpyp like :grpyp order by tipyp20.nopyp", Tipyp20.class);

        query.setParameter("grpyp", "%" + grpyp + "%");

        List<Tipyp20> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Tipyp20>();
        }

        return result;
    }

    /**
     *
     * @param grpyp
     * @param cepyp
     * @return
     */
    @Override
    public List<Tipyp20> getListTipyp20(String grpyp, String cepyp) {
        Query query = em.createQuery("select tipyp20 from Tipyp20 tipyp20 "
                + "where tipyp20.stpyp = 'SI' and tipyp20.grpyp like :grpyp and tipyp20.cepyp like :cepyp order by tipyp20.nopyp", Tipyp20.class);

        query.setParameter("grpyp", "%" + grpyp + "%");
        query.setParameter("cepyp", "%" + cepyp + "%");

        List<Tipyp20> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Tipyp20>();
        }

        return result;
    }

    /**
     *
     * @param grpyp
     * @param hmpyp
     * @return
     */
    @Override
    public String getTipyp20Nopyp(String grpyp, String hmpyp) {

        Query query = em.createQuery("select tipyp20.nopyp from Tipyp20 tipyp20 "
                + "where tipyp20.stpyp = 'SI' and tipyp20.grpyp = :grpyp and tipyp20.hmpyp = :hmpyp order by tipyp20.nopyp", String.class);

        query.setParameter("grpyp", grpyp);
        query.setParameter("hmpyp", hmpyp);

        query.setFirstResult(0);
        query.setMaxResults(1);
        String result;

        try {
            result = (String) query.getSingleResult();
        } catch (NoResultException ex) {
            result = "";
        }

        if (result.equals("")) {
            result = grpyp;
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Tipyp20> retrieveTipyp20() {
        Query query = em.createQuery("select tipyp20 from Tipyp20 tipyp20", Tipyp20.class);

        List<Tipyp20> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Tipyp20>();
        }

        return result;
    }

    /**
     *
     * @param tipyp20
     * @return
     * @throws Tipyp20AlreadyExists
     */
    @Override
    public Tipyp20 registerTipyp20(Tipyp20 tipyp20) throws Tipyp20AlreadyExists {

        em.persist(tipyp20);
        em.flush();

        return tipyp20;
    }

    /**
     *
     * @param tipyp20
     * @throws Tipyp20NotFound
     */
    @Override
    public void deleteTipyp20(Tipyp20 tipyp20) throws Tipyp20NotFound {
        Tipyp20 updatableTipyp20 = em.find(Tipyp20.class, tipyp20.getIdpyp());

        if (updatableTipyp20 == null) {
            throw new Tipyp20NotFound();
        }

        em.merge(tipyp20);
        em.flush();
    }

    /**
     *
     * @param tipyp20
     * @throws Tipyp20NotFound
     */
    @Override
    public void removeTipyp20(Tipyp20 tipyp20) throws Tipyp20NotFound {
        Tipyp20 updatableTipyp20 = em.find(Tipyp20.class, tipyp20.getIdpyp());

        if (updatableTipyp20 == null) {
            throw new Tipyp20NotFound();
        }

        if (!em.contains(tipyp20)) {
            tipyp20 = em.merge(tipyp20);
        }

        em.remove(tipyp20);
        em.flush();
    }

    /**
     *
     * @param tipyp20
     * @throws Tipyp20NotFound
     */
    @Override
    public void updateTipyp20(Tipyp20 tipyp20) throws Tipyp20NotFound {
        Tipyp20 updatableTipyp20 = em.find(Tipyp20.class, tipyp20.getIdpyp());

        if (updatableTipyp20 == null) {
            throw new Tipyp20NotFound();
        }

        em.merge(tipyp20);
        em.flush();
    }
}
