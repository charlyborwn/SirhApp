package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Docum29a;
import com.hrm.sirhapp.service.exception.Docum29aAlreadyExists;
import com.hrm.sirhapp.service.exception.Docum29aNotFound;
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
public class Docum29aManager implements Docum29aManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param iddocA
     * @return
     * @throws Docum29aNotFound
     */
    @Override
    public Docum29a getDocum29a(Integer iddocA) throws Docum29aNotFound {
        Query query = em.createQuery("SELECT docum29a FROM Docum29a docum29a WHERE "
                + "docum29a.iddocA = :iddocA");

        query.setParameter("iddocA", iddocA);

        Docum29a docum29aInfo;

        try {
            docum29aInfo = (Docum29a) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Docum29aNotFound();
        }

        Docum29a docum29a = docum29aInfo;

        return docum29a;
    }

    /**
     *
     * @param rfdocA
     * @return
     */
    @Override
    public List<Docum29a> getListDocum29a(String rfdocA) {
        Query query = em.createQuery("select docum29a from Docum29a docum29a "
                + "where docum29a.stdocA='SI' and docum29a.rfdocA = :rfdocA", Docum29a.class);

        query.setParameter("rfdocA", rfdocA);

        List<Docum29a> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Docum29a>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Docum29a> retrieveDocum29a() {
        Query query = em.createQuery("select docum29a from Docum29a docum29a", Docum29a.class);

        List<Docum29a> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Docum29a>();
        }

        return result;
    }

    /**
     *
     * @param docum29a
     * @return
     * @throws Docum29aAlreadyExists
     */
    @Override
    public Docum29a registerDocum29a(Docum29a docum29a) throws Docum29aAlreadyExists {

        em.persist(docum29a);
        em.flush();

        return docum29a;
    }

    /**
     *
     * @param docum29a
     * @throws Docum29aNotFound
     */
    @Override
    public void deleteDocum29a(Docum29a docum29a) throws Docum29aNotFound {

        Docum29a updatableDocum29a = em.find(Docum29a.class, docum29a.getIddocA());

        if (updatableDocum29a == null) {
            throw new Docum29aNotFound();
        }

        em.merge(docum29a);
        em.flush();
    }

    /**
     *
     * @param docum29a
     * @throws Docum29aNotFound
     */
    @Override
    public void removeDocum29a(Docum29a docum29a) throws Docum29aNotFound {

        Docum29a updatableDocum29a = em.find(Docum29a.class, docum29a.getIddocA());

        if (updatableDocum29a == null) {
            throw new Docum29aNotFound();
        }
        if (!em.contains(docum29a)) {
            docum29a = em.merge(docum29a);
        }

        em.remove(docum29a);
        em.flush();
    }

    /**
     *
     * @param docum29a
     * @throws Docum29aNotFound
     */
    @Override
    public void updateDocum29a(Docum29a docum29a) throws Docum29aNotFound {
        Docum29a updatableDocum29a = em.find(Docum29a.class, docum29a.getIddocA());

        if (updatableDocum29a == null) {
            throw new Docum29aNotFound();
        }

        em.merge(docum29a);
        em.flush();
    }

}
