package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Chofe40;
import com.hrm.sirhapp.service.exception.Chofe40AlreadyExists;
import com.hrm.sirhapp.service.exception.Chofe40NotFound;
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
public class Chofe40Manager implements Chofe40ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idcho
     * @return
     * @throws Chofe40NotFound
     */
    @Override
    public Chofe40 getChofe40(Integer idcho) throws Chofe40NotFound {
        Query query = em.createQuery("SELECT chofe40 FROM Chofe40 chofe40 WHERE "
                + "chofe40.idcho = :idcho");

        query.setParameter("idcho", idcho);

        Chofe40 chofe40Info;

        try {
            chofe40Info = (Chofe40) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Chofe40NotFound();
        }

        Chofe40 chofe40 = chofe40Info;

        return chofe40;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Chofe40> getListChofe40() {
        Query query = em.createQuery("select chofe40 from Chofe40 chofe40 "
                + "where chofe40.stcho = 'SI'", Chofe40.class);

        List<Chofe40> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Chofe40>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Chofe40> retrieveChofe40() {
        Query query = em.createQuery("select chofe40 from Chofe40 chofe40", Chofe40.class);

        List<Chofe40> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Chofe40>();
        }

        return result;
    }

    /**
     *
     * @param chofe40
     * @return
     * @throws Chofe40AlreadyExists
     */
    @Override
    public Chofe40 registerChofe40(Chofe40 chofe40) throws Chofe40AlreadyExists {

        em.persist(chofe40);
        em.flush();

        return chofe40;
    }

    /**
     *
     * @param chofe40
     * @throws Chofe40NotFound
     */
    @Override
    public void removeChofe40(Chofe40 chofe40) throws Chofe40NotFound {
        Chofe40 updatableChofe40 = em.find(Chofe40.class, chofe40.getIdcho());

        if (updatableChofe40 == null) {
            throw new Chofe40NotFound();
        }
        if (!em.contains(chofe40)) {
            chofe40 = em.merge(chofe40);
        }

        em.remove(chofe40);
        em.flush();
    }

    /**
     *
     * @param chofe40
     * @throws Chofe40NotFound
     */
    @Override
    public void deleteChofe40(Chofe40 chofe40) throws Chofe40NotFound {
        Chofe40 updatableChofe40 = em.find(Chofe40.class, chofe40.getIdcho());

        if (updatableChofe40 == null) {
            throw new Chofe40NotFound();
        }

        em.merge(chofe40);
        em.flush();
    }

    /**
     *
     * @param chofe40
     * @throws Chofe40NotFound
     */
    @Override
    public void updateChofe40(Chofe40 chofe40) throws Chofe40NotFound {
        Chofe40 updatableChofe40 = em.find(Chofe40.class, chofe40.getIdcho());

        if (updatableChofe40 == null) {
            throw new Chofe40NotFound();
        }

        em.merge(chofe40);
        em.flush();
    }
}
