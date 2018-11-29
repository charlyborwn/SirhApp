package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Categ01;
import com.hrm.sirhapp.service.exception.Categ01AlreadyExists;
import com.hrm.sirhapp.service.exception.Categ01NotFound;
import com.hrm.sirhapp.util.FacesUtil;
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
public class Categ01Manager implements Categ01ManagerLocal {

    private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(Categ01Manager.class);

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idcat
     * @return
     * @throws Categ01NotFound
     */
    @Override
    public Categ01 getCateg01(Integer idcat) throws Categ01NotFound {
        Query query = em.createQuery("SELECT categ01 FROM Categ01 categ01 WHERE "
                + "categ01.idcat = :idcat");

        query.setParameter("idcat", idcat);

        Categ01 categ01Info;

        try {
            categ01Info = (Categ01) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Categ01NotFound();
        }

        Categ01 categ01 = categ01Info;

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + categ01.getIdcat() + " | SEL: " + categ01.toString() + " | ACT: " + this.getClass().getName() + ".categ01");

        return categ01;
    }

    /**
     *
     * @param cvcat
     * @param cecat
     * @return
     * @throws Categ01NotFound
     */
    @Override
    public Categ01 getCateg01(String cvcat, String cecat) throws Categ01NotFound {
        Query query = em.createQuery("SELECT categ01 FROM Categ01 categ01 WHERE "
                + "categ01.cvcat = :cvcat and categ01.cecat = :cecat");

        query.setParameter("cvcat", cvcat);
        query.setParameter("cecat", cecat);

        Categ01 categ01Info;

        try {
            categ01Info = (Categ01) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Categ01NotFound();
        }

        Categ01 categ01 = categ01Info;

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + categ01.getIdcat() + " | SEL: " + categ01.toString() + " | ACT: " + this.getClass().getName() + ".categ01");

        return categ01;
    }

    /**
     *
     * @param cecat
     * @return
     */
    @Override
    public List<Categ01> getListCateg01(String cecat) {
        Query query = em.createQuery("select categ01 from Categ01 categ01 "
                + "where categ01.stcat = 'SI' and categ01.cecat = :cecat order by categ01.cecat", Categ01.class);
        
        query.setParameter("cecat", cecat);

        List<Categ01> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Categ01>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Categ01> getListCateg01() {
        Query query = em.createQuery("select categ01 from Categ01 categ01  "
                + "where categ01.stcat = 'SI' order by categ01.cecat ", Categ01.class);

        List<Categ01> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Categ01>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Categ01> retrieveCateg01() {
        Query query = em.createQuery("select categ01 from Categ01 categ01 order by categ01.cecat", Categ01.class);

        List<Categ01> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Categ01>();
        }

        return result;
    }

    /**
     *
     * @param categ01
     * @return
     * @throws Categ01AlreadyExists
     */
    @Override
    public Categ01 registerCateg01(Categ01 categ01) throws Categ01AlreadyExists {

        em.persist(categ01);
        em.flush();

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + categ01.getIdcat() + " | NEW: " + categ01.toString() + " | ACT: " + this.getClass().getName() + ".registerCateg01");

        return categ01;
    }

    /**
     *
     * @param categ01
     * @throws Categ01NotFound
     */
    @Override
    public void removeCateg01(Categ01 categ01) throws Categ01NotFound {
        Categ01 updatableCateg01 = em.find(Categ01.class, categ01.getIdcat());

        if (updatableCateg01 == null) {
            throw new Categ01NotFound();
        }

        if (!em.contains(categ01)) {
            categ01 = em.merge(categ01);
        }

        em.remove(categ01);
        em.flush();

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + categ01.getIdcat() + " | REM: " + categ01.toString() + " | ACT: " + this.getClass().getName() + ".deleteCateg01");

    }

    /**
     *
     * @param categ01
     * @throws Categ01NotFound
     */
    @Override
    public void deleteCateg01(Categ01 categ01) throws Categ01NotFound {
        Categ01 updatableCateg01 = em.find(Categ01.class, categ01.getIdcat());

        if (updatableCateg01 == null) {
            throw new Categ01NotFound();
        }

        em.merge(categ01);
        em.flush();

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + categ01.getIdcat() + " | UPD: " + updatableCateg01.toString() + " | ACT: " + this.getClass().getName() + ".deleteCateg01");
        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + categ01.getIdcat() + " | DEL: " + categ01.toString() + " | ACT: " + this.getClass().getName() + ".deleteCateg01");

    }

    /**
     *
     * @param categ01
     * @throws Categ01NotFound
     */
    @Override
    public void updateCateg01(Categ01 categ01) throws Categ01NotFound {
        Categ01 updatableCateg01 = em.find(Categ01.class, categ01.getIdcat());

        if (updatableCateg01 == null) {
            throw new Categ01NotFound();
        }

        em.merge(categ01);
        em.flush();
        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + categ01.getIdcat() + " | UPD: " + updatableCateg01.toString() + " | ACT: " + this.getClass().getName() + ".updateCateg01");
        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + categ01.getIdcat() + " | DAT: " + categ01.toString() + " | ACT: " + this.getClass().getName() + ".updateCateg01");

    }

}
