package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Docum29;
import com.hrm.sirhapp.service.exception.Docum29AlreadyExists;
import com.hrm.sirhapp.service.exception.Docum29NotFound;
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
public class Docum29Manager implements Docum29ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param iddoc
     * @return
     * @throws Docum29NotFound
     */
    @Override
    public Docum29 getDocum29(Integer iddoc) throws Docum29NotFound {
        Query query = em.createQuery("SELECT docum29 FROM Docum29 docum29 WHERE "
                + "docum29.iddoc = :iddoc");

        query.setParameter("iddoc", iddoc);

        Docum29 docum29Info;

        try {
            docum29Info = (Docum29) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Docum29NotFound();
        }

        Docum29 docum29 = docum29Info;

        return docum29;
    }

    /**
     *
     * @param ntdoc
     * @return
     */
    @Override
    public List<Docum29> getListDocum29(Integer ntdoc) {
        Query query = em.createQuery("select docum29 from Docum29 docum29 "
                + "where docum29.ntdoc = :ntdoc", Docum29.class);

        query.setParameter("ntdoc", ntdoc);

        List<Docum29> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Docum29>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Docum29> retrieveDocum29() {
        Query query = em.createQuery("select docum29 from Docum29 docum29", Docum29.class);

        List<Docum29> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Docum29>();
        }

        return result;
    }

    /**
     *
     * @param docum29
     * @return
     * @throws Docum29AlreadyExists
     */
    @Override
    public Docum29 registerDocum29(Docum29 docum29) throws Docum29AlreadyExists {

        em.persist(docum29);
        em.flush();

        return docum29;
    }

    /**
     *
     * @param docum29
     * @throws Docum29NotFound
     */
    @Override
    public void removeDocum29(Docum29 docum29) throws Docum29NotFound {
        Docum29 updatableDocum29 = em.find(Docum29.class, docum29.getIddoc());

        if (updatableDocum29 == null) {
            throw new Docum29NotFound();
        }
        if (!em.contains(docum29)) {
            docum29 = em.merge(docum29);
        }

        em.remove(docum29);
        em.flush();
    }

    /**
     *
     * @param docum29
     * @throws Docum29NotFound
     */
    @Override
    public void deleteDocum29(Docum29 docum29) throws Docum29NotFound {
        Docum29 updatableDocum29 = em.find(Docum29.class, docum29.getIddoc());

        if (updatableDocum29 == null) {
            throw new Docum29NotFound();
        }

        em.merge(docum29);
        em.flush();
    }

    /**
     *
     * @param docum29
     * @throws Docum29NotFound
     */
    @Override
    public void updateDocum29(Docum29 docum29) throws Docum29NotFound {
        Docum29 updatableDocum29 = em.find(Docum29.class, docum29.getIddoc());

        if (updatableDocum29 == null) {
            throw new Docum29NotFound();
        }

        em.merge(docum29);
        em.flush();
    }

}
