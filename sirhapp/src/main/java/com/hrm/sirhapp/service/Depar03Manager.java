package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Depar03;
import com.hrm.sirhapp.model.Usuar24;
import com.hrm.sirhapp.service.exception.Depar03AlreadyExists;
import com.hrm.sirhapp.service.exception.Depar03NotFound;
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
public class Depar03Manager implements Depar03ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param iddep
     * @return
     * @throws Depar03NotFound
     */
    @Override
    public Depar03 getDepar03(Integer iddep) throws Depar03NotFound {
        Query query = em.createQuery("SELECT depar03 FROM Depar03 depar03 WHERE "
                + "depar03.iddep = :iddep");

        query.setParameter("iddep", iddep);

        Depar03 depar03Info;

        try {
            query.setFirstResult(0);
            query.setMaxResults(1);
            depar03Info = (Depar03) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Depar03NotFound();
        }

        return depar03Info;
    }

    /**
     *
     * @param nudep
     * @return
     * @throws Depar03NotFound
     */
    @Override
    public Depar03 getDepar03(String nudep) throws Depar03NotFound {
        Query query = em.createQuery("SELECT depar03 FROM Depar03 depar03 WHERE "
                + "depar03.nudep = :nudep");

        query.setParameter("nudep", nudep);

        Depar03 depar03Info;

        try {
            query.setFirstResult(0);
            query.setMaxResults(1);
            depar03Info = (Depar03) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Depar03NotFound();
        }

        return depar03Info;
    }

    /**
     *
     * @param cedep
     * @param nudep
     * @return
     * @throws Depar03NotFound
     */
    @Override
    public Depar03 getDepar03(String cedep, String nudep) throws Depar03NotFound {
        Query query = em.createQuery("SELECT depar03 FROM Depar03 depar03 WHERE "
                + " depar03.cedep = :cedep AND depar03.nudep = :nudep");

        query.setParameter("cedep", cedep);
        query.setParameter("nudep", nudep);

        Depar03 depar03Info;

        try {
            query.setFirstResult(0);
            query.setMaxResults(1);
            depar03Info = (Depar03) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Depar03NotFound();
        }

        return depar03Info;
    }

    /**
     *
     * @param cedep
     * @param sedep
     * @param nudep
     * @return
     * @throws Depar03NotFound
     */
    @Override
    public Depar03 getDepar03(String cedep, String sedep, String nudep) throws Depar03NotFound {
        Query query = em.createQuery("SELECT depar03 FROM Depar03 depar03 WHERE "
                + " depar03.cedep = :cedep AND depar03.sedep = :sedep AND depar03.nudep = :nudep");

        query.setParameter("cedep", cedep);
        query.setParameter("sedep", sedep);
        query.setParameter("nudep", nudep);

        Depar03 depar03Info;

        try {
            query.setFirstResult(0);
            query.setMaxResults(1);
            depar03Info = (Depar03) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Depar03NotFound();
        }

        return depar03Info;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Depar03> getListDepar03() {

        Usuar24 ususar24 = FacesUtil.getUsuar24();

        String str = strQuery(ususar24);

        Query query = em.createQuery("select depar03 from Depar03 depar03 " + str + " ORDER BY depar03.cedep, depar03.ordep ", Depar03.class);

        List<Depar03> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Depar03>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Depar03> retrieveDepar03() {
        Query query = em.createQuery("select depar03 from Depar03 depar03", Depar03.class);

        List<Depar03> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Depar03>();
        }

        return result;
    }

    /**
     *
     * @param depar03
     * @return
     * @throws Depar03AlreadyExists
     */
    @Override
    public Depar03 registerDepar03(Depar03 depar03) throws Depar03AlreadyExists {

        em.persist(depar03);
        em.flush();

        return depar03;
    }

    /**
     *
     * @param depar03
     * @throws Depar03NotFound
     */
    @Override
    public void removeDepar03(Depar03 depar03) throws Depar03NotFound {
        Depar03 updatableDepar03 = em.find(Depar03.class, depar03.getIddep());

        if (updatableDepar03 == null) {
            throw new Depar03NotFound();
        }

        if (!em.contains(depar03)) {
            depar03 = em.merge(depar03);
        }

        em.remove(depar03);
        em.flush();
    }

    /**
     *
     * @param depar03
     * @throws Depar03NotFound
     */
    @Override
    public void deleteDepar03(Depar03 depar03) throws Depar03NotFound {
        Depar03 updatableDepar03 = em.find(Depar03.class, depar03.getIddep());

        if (updatableDepar03 == null) {
            throw new Depar03NotFound();
        }

        em.merge(depar03);
        em.flush();
    }

    /**
     *
     * @param depar03
     * @throws Depar03NotFound
     */
    @Override
    public void updateDepar03(Depar03 depar03) throws Depar03NotFound {
        Depar03 updatableDepar03 = em.find(Depar03.class, depar03.getIddep());

        if (updatableDepar03 == null) {
            throw new Depar03NotFound();
        }

        em.merge(depar03);
        em.flush();
    }

    private String strQuery(Usuar24 usuar24) {
        String str = " WHERE depar03.nudep<>'00000' and depar03.stdep='SI' ";
        String str1 = "";
        String str2 = "";
        String str3 = "";

        if (usuar24.getSeusu() != null) {
            if (Integer.parseInt(usuar24.getSeusu()) > 0) {
                str1 = "and depar03.sedep='" + usuar24.getSeusu() + "' ";
            }
        }

        if (usuar24.getEmusu() != null) {
            if (Integer.parseInt(usuar24.getEmusu()) > 0) {
                str2 = "and depar03.cedep='" + usuar24.getEmusu() + "' ";
            }
        }

        if (usuar24.getDeusu() != null) {
            if (Integer.parseInt(usuar24.getDeusu()) > 0) {
                str3 = "and depar03.nudep='" + usuar24.getDeusu() + "' ";
            }
        }

        if (str2.length() > 0) {
            str = str + str1 + str2 + str3;
        }

        System.out.println(str);
        return str;
    }

}
