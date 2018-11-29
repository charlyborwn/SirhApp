package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Sedes53;
import com.hrm.sirhapp.model.Usuar24;
import com.hrm.sirhapp.service.exception.Sedes53AlreadyExists;
import com.hrm.sirhapp.service.exception.Sedes53NotFound;
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
public class Sedes53Manager implements Sedes53ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idsed
     * @return
     * @throws Sedes53NotFound
     */
    @Override
    public Sedes53 getSedes53(Integer idsed) throws Sedes53NotFound {
        Query query = em.createQuery("SELECT sedes53 FROM Sedes53 sedes53 WHERE "
                + "sedes53.idsed = :idsed");

        query.setParameter("idsed", idsed);

        Sedes53 sedes53Info;

        try {
            sedes53Info = (Sedes53) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Sedes53NotFound();
        }

        Sedes53 sedes53 = sedes53Info;

        return sedes53;
    }

    /**
     *
     * @param cvsed
     * @return
     * @throws Sedes53NotFound
     */
    @Override
    public Sedes53 getSedes53(String cvsed) throws Sedes53NotFound {
        Query query = em.createQuery("SELECT sedes53 FROM Sedes53 sedes53 WHERE "
                + "sedes53.cvsed = :cvsed");

        query.setParameter("cvsed", cvsed);

        Sedes53 sedes53Info;

        try {
            sedes53Info = (Sedes53) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Sedes53NotFound();
        }

        return sedes53Info;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Sedes53> getListSedes53() {

        Usuar24 ususar24 = FacesUtil.getUsuar24();

        String str = strQuery(ususar24);

        Query query = em.createQuery("SELECT sedes53 FROM Sedes53 sedes53  " + str + " ORDER BY sedes53.orsed ", Sedes53.class);

        List<Sedes53> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Sedes53>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Sedes53> retrieveSedes53() {
        Query query = em.createQuery("select sedes53 from Sedes53 sedes53", Sedes53.class);

        List<Sedes53> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Sedes53>();
        }

        return result;
    }

    /**
     *
     * @param sedes53
     * @return
     * @throws Sedes53AlreadyExists
     */
    @Override
    public Sedes53 registerSedes53(Sedes53 sedes53) throws Sedes53AlreadyExists {

        em.persist(sedes53);
        em.flush();

        return sedes53;
    }

    /**
     *
     * @param sedes53
     * @throws Sedes53NotFound
     */
    @Override
    public void deleteSedes53(Sedes53 sedes53) throws Sedes53NotFound {
        Sedes53 updatableSedes53 = em.find(Sedes53.class, sedes53.getIdsed());

        if (updatableSedes53 == null) {
            throw new Sedes53NotFound();
        }

        em.merge(sedes53);
        em.flush();
    }

    /**
     *
     * @param sedes53
     * @throws Sedes53NotFound
     */
    @Override
    public void removeSedes53(Sedes53 sedes53) throws Sedes53NotFound {
        Sedes53 updatableSedes53 = em.find(Sedes53.class, sedes53.getIdsed());

        if (updatableSedes53 == null) {
            throw new Sedes53NotFound();
        }

        if (!em.contains(sedes53)) {
            sedes53 = em.merge(sedes53);
        }

        em.remove(sedes53);
        em.flush();
    }

    /**
     *
     * @param sedes53
     * @throws Sedes53NotFound
     */
    @Override
    public void updateSedes53(Sedes53 sedes53) throws Sedes53NotFound {
        Sedes53 updatableSedes53 = em.find(Sedes53.class, sedes53.getIdsed());

        if (updatableSedes53 == null) {
            throw new Sedes53NotFound();
        }

        em.merge(sedes53);
        em.flush();
    }

    private String strQuery(Usuar24 usuar24) {
        String str = " WHERE sedes53.cvsed<>0 and sedes53.stsed='SI' ";
        String str2 = "";

        if (usuar24.getSeusu() != null) {
            if (Integer.parseInt(usuar24.getSeusu()) > 0) {
                str2 = " and sedes53.cvsed='" + usuar24.getSeusu() + "' ";
            }
        }

        if (str2.length() > 0) {
            str = str + str2;
        }

        System.out.println(str);
        return str;
    }

}
