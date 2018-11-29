package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Event43;
import com.hrm.sirhapp.service.exception.Event43AlreadyExists;
import com.hrm.sirhapp.service.exception.Event43NotFound;
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
public class Event43Manager implements Event43ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param nueve
     * @return
     * @throws Event43NotFound
     */
    @Override
    public Event43 getEvent43(Integer nueve) throws Event43NotFound {
        Query query = em.createQuery("SELECT event43 FROM Event43 event43 WHERE "
                + "event43.nueve = :nueve");

        query.setParameter("nueve", nueve);

        Event43 event43Info;

        try {
            event43Info = (Event43) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Event43NotFound();
        }

        Event43 event43 = event43Info;

        return event43;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Event43> getListEvent43() {
        Query query = em.createQuery("select event43 from Event43 event43 "
                + "where event43.steve = 'SI'", Event43.class);

        List<Event43> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Event43>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Event43> retrieveEvent43() {
        Query query = em.createQuery("select event43 from Event43 event43", Event43.class);

        List<Event43> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Event43>();
        }

        return result;
    }

    /**
     *
     * @param event43
     * @return
     * @throws Event43AlreadyExists
     */
    @Override
    public Event43 registerEvent43(Event43 event43) throws Event43AlreadyExists {

        em.persist(event43);
        em.flush();

        return event43;
    }

    /**
     *
     * @param event43
     * @throws Event43NotFound
     */
    @Override
    public void removeEvent43(Event43 event43) throws Event43NotFound {
        Event43 updatableEvent43 = em.find(Event43.class, event43.getNueve());

        if (updatableEvent43 == null) {
            throw new Event43NotFound();
        }
        if (!em.contains(event43)) {
            event43 = em.merge(event43);
        }
        em.remove(event43);
        em.flush();
    }

    /**
     *
     * @param event43
     * @throws Event43NotFound
     */
    @Override
    public void deleteEvent43(Event43 event43) throws Event43NotFound {
        Event43 updatableEvent43 = em.find(Event43.class, event43.getNueve());

        if (updatableEvent43 == null) {
            throw new Event43NotFound();
        }

        em.merge(event43);
        em.flush();
    }

    /**
     *
     * @param event43
     * @throws Event43NotFound
     */
    @Override
    public void updateEvent43(Event43 event43) throws Event43NotFound {
        Event43 updatableEvent43 = em.find(Event43.class, event43.getNueve());

        if (updatableEvent43 == null) {
            throw new Event43NotFound();
        }

        em.merge(event43);
        em.flush();
    }
}
