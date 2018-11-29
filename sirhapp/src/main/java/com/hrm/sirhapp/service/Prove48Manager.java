package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Prove48;
import com.hrm.sirhapp.service.exception.Prove48AlreadyExists;
import com.hrm.sirhapp.service.exception.Prove48NotFound;
import java.util.ArrayList;
import java.util.Collections;
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
public class Prove48Manager implements Prove48ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idpro
     * @return
     * @throws Prove48NotFound
     */
    @Override
    public Prove48 getProve48(Integer idpro) throws Prove48NotFound {
        Query query = em.createQuery("SELECT prove48 FROM Prove48 prove48 WHERE "
                + "prove48.idpro = :idpro");

        query.setParameter("idpro", idpro);

        Prove48 prove48Info;

        try {
            prove48Info = (Prove48) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Prove48NotFound();
        }

        Prove48 prove48 = prove48Info;

        return prove48;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Prove48> getListProve48() {
        Query query = em.createQuery("select prove48 from Prove48 prove48 "
                + "where prove48.stpro = 'SI'", Prove48.class);

        List<Prove48> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Prove48>();
        }

        return result;
    }

    /**
     *
     * @param rfpro
     * @return
     */
    @Override
    public List<String> getListProve48Rfpro(String rfpro) {

        Query listIdsQry = em.createQuery("select prove48.idpro from Prove48 prove48  "
                + "where prove48.stpro = 'SI' ", Integer.class);

        List<Integer> listIds = listIdsQry.getResultList();

        Collections.shuffle(listIds);

        int randomSeriesLength = 10;

        List<Integer> randomSeries = listIds.subList(0, randomSeriesLength);

        String str = "select prove48.rfpro from Prove48 prove48  "
                + "where 1=1  and prove48.stpro = 'SI' ";

        Query query = em.createQuery(str, String.class);

        if (rfpro != null && rfpro.length() > 0) {
            str = str + " and prove48.rfpro like :rfpro ";
            query = em.createQuery(str, String.class);
            query.setParameter("rfpro", "%" + rfpro + "%");
        } else {
            str = "SELECT prove48.rfpro from Prove48 prove48 "
                    + "WHERE prove48.idpro IN :indexs and prove48.stpro = 'SI' ";
            query = em.createQuery(str, String.class);
            query.setParameter("indexs", randomSeries);
        }

        List<String> result = query.getResultList();

        if (result == null) {
            return new ArrayList<String>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Prove48> retrieveProve48() {
        Query query = em.createQuery("select prove48 from Prove48 prove48", Prove48.class);

        List<Prove48> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Prove48>();
        }

        return result;
    }

    /**
     *
     * @param rfpro
     * @return
     */
    @Override
    public Prove48 retrieveProve48(String rfpro) {
        try {
            Query query = em.createQuery("select prove48 from Prove48 prove48 "
                    + "WHERE prove48.rfpro='" + rfpro + "' and prove48.stpro='SI'", Prove48.class);

            Prove48 result = (Prove48) query.getSingleResult();

            return result;
        } catch (NoResultException e) {
            Prove48 prove48 = new Prove48();
            prove48.setNcpro("EL PROVEEDOR NO ESTA REGISTRADO");
            return prove48;
        }

    }

    /**
     *
     * @param prove48
     * @return
     * @throws Prove48AlreadyExists
     */
    @Override
    public Prove48 registerProve48(Prove48 prove48) throws Prove48AlreadyExists {

        em.persist(prove48);
        em.flush();

        return prove48;
    }

    /**
     *
     * @param prove48
     * @throws Prove48NotFound
     */
    @Override
    public void removeProve48(Prove48 prove48) throws Prove48NotFound {
        Prove48 updatableProve48 = em.find(Prove48.class, prove48.getIdpro());

        if (updatableProve48 == null) {
            throw new Prove48NotFound();
        }
        if (!em.contains(prove48)) {
            prove48 = em.merge(prove48);
        }
        em.remove(prove48);
        em.flush();
    }

    /**
     *
     * @param prove48
     * @throws Prove48NotFound
     */
    @Override
    public void deleteProve48(Prove48 prove48) throws Prove48NotFound {
        Prove48 updatableProve48 = em.find(Prove48.class, prove48.getIdpro());

        if (updatableProve48 == null) {
            throw new Prove48NotFound();
        }

        em.merge(prove48);
        em.flush();
    }

    /**
     *
     * @param prove48
     * @throws Prove48NotFound
     */
    @Override
    public void updateProve48(Prove48 prove48) throws Prove48NotFound {
        Prove48 updatableProve48 = em.find(Prove48.class, prove48.getIdpro());

        if (updatableProve48 == null) {
            throw new Prove48NotFound();
        }

        em.merge(prove48);
        em.flush();
    }
}
