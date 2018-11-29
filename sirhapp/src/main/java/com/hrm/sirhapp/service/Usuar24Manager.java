package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Niacc33;
import com.hrm.sirhapp.model.Usuar24;
import com.hrm.sirhapp.service.exception.Usuar24AlreadyExists;
import com.hrm.sirhapp.service.exception.Usuar24NotFound;
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
public class Usuar24Manager implements Usuar24ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param cvusu
     * @return
     */
    @Override
    public Boolean alreadyExistsInnactive(String cvusu) {
        Query query = em.createQuery("SELECT usuar24 FROM Usuar24 usuar24 "
                + "WHERE usuar24.stusu <> 'SI' and  usuar24.cvusu  = :cvusu");

        query.setParameter("cvusu", cvusu);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    /**
     *
     * @param cvusu
     * @return
     * @throws Usuar24NotFound
     */
    @Override
    public Usuar24 getUsuar24(String cvusu) throws Usuar24NotFound {
        Query query = em.createQuery("SELECT usuar24 FROM Usuar24 usuar24 "
                + "WHERE usuar24.cvusu  = :cvusu");

        query.setParameter("cvusu", cvusu);

        Usuar24 usuar24Info;

        try {
            usuar24Info = (Usuar24) query.getSingleResult();
        } catch (NoResultException exception) {
            throw new Usuar24NotFound(exception.getMessage());
        }

        Usuar24 usuar24 = usuar24Info;

        return usuar24;
    }

    /**
     *
     * @param cousu
     * @return
     * @throws Usuar24NotFound
     */
    @Override
    public Usuar24 getUsuar24Email(String cousu) throws Usuar24NotFound {
        Query query = em.createQuery("SELECT usuar24 FROM Usuar24 usuar24 "
                + "WHERE usuar24.cousu = :cousu");

        query.setParameter("cousu", cousu);

        Usuar24 usuar24Info;

        try {
            usuar24Info = (Usuar24) query.getSingleResult();
        } catch (NoResultException exception) {
            throw new Usuar24NotFound(exception.getMessage());
        }

        Usuar24 usuar24 = usuar24Info;

        return usuar24;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Usuar24> getListUsuar24() {
        Query query = em.createQuery("select usuar24 from Usuar24 usuar24 "
                + "where usuar24.stusu = 'SI'", Usuar24.class);

        List<Usuar24> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Usuar24>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Usuar24> getListInnactiveUsuar24() {
        Query query = em.createQuery("select usuar24 from Usuar24 usuar24 "
                + "where usuar24.stusu = 'NO'", Usuar24.class);

        List<Usuar24> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Usuar24>();
        }

        return result;
    }

    /**
     *
     * @param usuar24
     * @return
     * @throws Usuar24AlreadyExists
     */
    @Override
    public Usuar24 registerUsuar24(Usuar24 usuar24) throws Usuar24AlreadyExists {

        Query query = em.createQuery("select usuar24 from Usuar24 usuar24 where "
                + "usuar24.cvusu = :cvusu");

        query.setParameter("cvusu", usuar24.getCvusu());

        try {
            query.getSingleResult();
            throw new Usuar24AlreadyExists();
        } catch (NoResultException exception) {
            Logger.getLogger(Usuar24Manager.class.getName()).log(Level.FINER, "Usuario no encontrado");
        }
        List<Niacc33> niacc33s = new ArrayList<Niacc33>(); // Error message for integrity constraint violation
        Niacc33 niacc33 = new Niacc33();
        niacc33.setCvnia(usuar24);
        niacc33.setNania("8");
        niacc33.setStnia(Constants.RECORD_ACTIVED);
        niacc33.setFenia(usuar24.getFeusu());
        niacc33.setUsnia(usuar24.getUsusu());
        niacc33s.add(niacc33);
        usuar24.setNiacc33List(niacc33s);
        em.persist(usuar24);
        em.flush();

        return usuar24;
    }

    /**
     *
     * @param usuar24
     * @throws Usuar24NotFound
     */
    @Override
    public void removeUsuar24(Usuar24 usuar24) throws Usuar24NotFound {
        Usuar24 updatableUsuar24 = em.find(Usuar24.class, usuar24.getIdusu());

        if (updatableUsuar24 == null) {
            throw new Usuar24NotFound();
        }

        if (!em.contains(usuar24)) {
            usuar24 = em.merge(usuar24);
        }

        em.remove(usuar24);
        em.flush();
    }

    /**
     *
     * @param usuar24
     * @throws Usuar24NotFound
     */
    @Override
    public void deleteUsuar24(Usuar24 usuar24) throws Usuar24NotFound {
        Usuar24 updatableUsuar24 = em.find(Usuar24.class, usuar24.getIdusu());

        if (updatableUsuar24 == null) {
            throw new Usuar24NotFound();
        }

        em.merge(usuar24);
        em.flush();
    }

    /**
     *
     * @param usuar24
     * @return
     * @throws Usuar24NotFound
     */
    @Override
    public Usuar24 updateUsuar24(Usuar24 usuar24) throws Usuar24NotFound {
        Usuar24 updatableUsuar24 = em.find(Usuar24.class, usuar24.getIdusu());

        if (updatableUsuar24 == null) {
            throw new Usuar24NotFound();
        }

        updatableUsuar24 = em.merge(usuar24);
        em.flush();

        return updatableUsuar24;
    }

}
