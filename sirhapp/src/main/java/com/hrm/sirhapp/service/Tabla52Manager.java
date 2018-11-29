package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Tabla52;
import com.hrm.sirhapp.service.exception.Tabla52AlreadyExists;
import com.hrm.sirhapp.service.exception.Tabla52NotFound;
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
public class Tabla52Manager implements Tabla52ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idtab
     * @return
     * @throws Tabla52NotFound
     */
    @Override
    public Tabla52 getTabla52(Integer idtab) throws Tabla52NotFound {
        Query query = em.createQuery("SELECT tabla52 FROM Tabla52 tabla52 WHERE "
                + "tabla52.idtab = :idtab");

        query.setParameter("idtab", idtab);

        Tabla52 tabla52Info;

        try {
            tabla52Info = (Tabla52) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Tabla52NotFound();
        }

        Tabla52 tabla52 = tabla52Info;

        return tabla52;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Tabla52> getListTabla52() {
        Query query = em.createQuery("select tabla52 from Tabla52 tabla52 "
                + "where tabla52.sttab='SI'", Tabla52.class);

        List<Tabla52> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Tabla52>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Tabla52> retrieveTabla52() {
        Query query = em.createQuery("select tabla52 from Tabla52 tabla52", Tabla52.class);

        List<Tabla52> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Tabla52>();
        }

        return result;
    }

    /**
     *
     * @param tabla52
     * @return
     * @throws Tabla52AlreadyExists
     */
    @Override
    public Tabla52 registerTabla52(Tabla52 tabla52) throws Tabla52AlreadyExists {

        em.persist(tabla52);
        em.flush();

        return tabla52;
    }

    /**
     *
     * @param tabla52
     * @throws Tabla52NotFound
     */
    @Override
    public void deleteTabla52(Tabla52 tabla52) throws Tabla52NotFound {
        Tabla52 updatableTabla52 = em.find(Tabla52.class, tabla52.getIdtab());

        if (updatableTabla52 == null) {
            throw new Tabla52NotFound();
        }

        em.merge(tabla52);
        em.flush();
    }

    /**
     *
     * @param tabla52
     * @throws Tabla52NotFound
     */
    @Override
    public void removeTabla52(Tabla52 tabla52) throws Tabla52NotFound {
        Tabla52 updatableTabla52 = em.find(Tabla52.class, tabla52.getIdtab());

        if (updatableTabla52 == null) {
            throw new Tabla52NotFound();
        }

        if (!em.contains(tabla52)) {
            tabla52 = em.merge(tabla52);
        }

        em.remove(tabla52);
        em.flush();
    }

    /**
     *
     * @param tabla52
     * @throws Tabla52NotFound
     */
    @Override
    public void updateTabla52(Tabla52 tabla52) throws Tabla52NotFound {
        Tabla52 updatableTabla52 = em.find(Tabla52.class, tabla52.getIdtab());

        if (updatableTabla52 == null) {
            throw new Tabla52NotFound();
        }

        em.merge(tabla52);
        em.flush();
    }

}
