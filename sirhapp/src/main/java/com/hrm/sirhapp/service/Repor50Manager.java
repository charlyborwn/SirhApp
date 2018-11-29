package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Repor50;
import com.hrm.sirhapp.service.exception.Repor50AlreadyExists;
import com.hrm.sirhapp.service.exception.Repor50NotFound;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Repor50Manager implements Repor50ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idrep
     * @return
     * @throws Repor50NotFound
     */
    @Override
    public Repor50 getRepor50(Integer idrep) throws Repor50NotFound {
        Query query = em.createQuery("select repor50 from Repor50 repor50 where "
                + "repor50.idrep = :idrep");

        query.setParameter("idrep", idrep);

        Repor50 repor50Info;

        try {
            repor50Info = (Repor50) query.getSingleResult();
        } catch (NoResultException exception) {
            throw new Repor50NotFound(exception.getMessage());
        }

        Repor50 repor50 = repor50Info;

        return repor50;
    }

    /**
     *
     * @param idrep
     * @return
     * @throws Repor50NotFound
     */
    @Override
    public byte[] getRepor50Content(Integer idrep) throws Repor50NotFound {
        byte[] content = null;

        try {
            content = (byte[]) em.createQuery("Select repor50.corep from Repor50 repor50 where repor50.idrep=:idrep")
                    .setParameter("idrep", idrep)
                    .getSingleResult();
        } catch (NoResultException exception) {
            throw new Repor50NotFound(exception.getMessage());
        }

        return content;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Repor50> getListRepor50() {
        Query query = em.createQuery("select repor50 from Repor50 repor50 "
                + "where repor50.strep = 'SI'", Repor50.class);

        List<Repor50> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Repor50>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Repor50> getListInnactiveRepor50() {
        Query query = em.createQuery("select repor50 from Repor50 repor50 "
                + "where repor50.strep = 'NO'", Repor50.class);

        List<Repor50> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Repor50>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Repor50> retrieveRepor50() {
        Query query = em.createQuery("select repor50 from Repor50 repor50", Repor50.class);

        List<Repor50> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Repor50>();
        }

        return result;
    }

    /**
     *
     * @param searchableRepor50
     * @return
     */
    @Override
    public List<Repor50> getAllRepor50s(Repor50 searchableRepor50) {
        List<Repor50> list = new ArrayList<Repor50>();
        String searchableTitle = searchableRepor50.getTirep();

        Query query = em.createQuery("select repor50 from Repor50 repor50 where "
                + "repor50.tirep like :tirep");

        query.setParameter("tirep", "%" + searchableTitle + "%");

        System.out.println("TEXTO: " + searchableRepor50.getTirep());

        List<Repor50> repor50List = (List<Repor50>) query.getResultList();

        if (repor50List == null) {
            return list;
        }

        for (Repor50 repor50Info : repor50List) {
            Repor50 repor50 = repor50Info;

            list.add(repor50);
        }

        return list;
    }

    /**
     *
     * @param repor50
     * @return
     * @throws Repor50AlreadyExists
     */
    @Override
    public Repor50 registerRepor50(Repor50 repor50) throws Repor50AlreadyExists {
        Query query = em.createQuery("select repor50 from Repor50 repor50 "
                + "where repor50.cvrep = :cvrep and repor50.strep ='SI'");

        query.setParameter("cvrep", repor50.getCvrep());

        try {
            query.getSingleResult();
            throw new Repor50AlreadyExists();
        } catch (NoResultException exception) {
            Logger.getLogger(Repor50Manager.class.getName()).log(Level.FINER, "No se encontraron reportes");
        }

        em.persist(repor50);
        em.flush();

        return repor50;
    }

    /**
     *
     * @param repor50
     * @throws Repor50NotFound
     */
    @Override
    public void removeRepor50(Repor50 repor50) throws Repor50NotFound {
        Repor50 updatableRepor50 = em.find(Repor50.class, repor50.getIdrep());

        if (updatableRepor50 == null) {
            throw new Repor50NotFound();
        }

        if (!em.contains(repor50)) {
            repor50 = em.merge(repor50);
        }

        em.remove(repor50);
        em.flush();
    }

    /**
     *
     * @param repor50
     * @throws Repor50NotFound
     */
    @Override
    public void deleteRepor50(Repor50 repor50) throws Repor50NotFound {
        Repor50 updatableRepor50 = em.find(Repor50.class, repor50.getIdrep());

        if (updatableRepor50 == null) {
            throw new Repor50NotFound();
        }

        em.merge(repor50);
        em.flush();
    }

    /**
     *
     * @param repor50
     * @return
     * @throws Repor50NotFound
     */
    @Override
    public Repor50 updateRepor50(Repor50 repor50) throws Repor50NotFound {
        Repor50 updatableRepor50 = em.find(Repor50.class, repor50.getIdrep());

        if (updatableRepor50 == null) {
            throw new Repor50NotFound();
        }

        em.merge(repor50);
        em.flush();

        return repor50;
    }
}
