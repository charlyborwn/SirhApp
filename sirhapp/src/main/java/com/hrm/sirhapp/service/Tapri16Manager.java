package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Tapri16;
import com.hrm.sirhapp.service.exception.Tapri16AlreadyExists;
import com.hrm.sirhapp.service.exception.Tapri16NotFound;
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
public class Tapri16Manager implements Tapri16ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idtap
     * @return
     * @throws Tapri16NotFound
     */
    @Override
    public Tapri16 getTapri16(Integer idtap) throws Tapri16NotFound {
        Query query = em.createQuery("SELECT tapri16 FROM Tapri16 tapri16 WHERE "
                + "tapri16.idtap = :idtap");

        query.setParameter("idtap", idtap);

        Tapri16 tapri16Info;

        try {
            tapri16Info = (Tapri16) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Tapri16NotFound();
        }

        Tapri16 tapri16 = tapri16Info;

        return tapri16;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Tapri16> getListTapri16() {
        Query query = em.createQuery("select tapri16 from Tapri16 tapri16 "
                + "where tapri16.sttap = 'SI' order by tapri16.grtap", Tapri16.class);

        List<Tapri16> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Tapri16>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<String> getTopListTapri16() {
        Query query = em.createQuery("select DISTINCT(tapri16.grtap) from Tapri16 tapri16 "
                + "where tapri16.sttap = 'SI' order by tapri16.grtap", String.class);

        List<String> result = query.getResultList();

        if (result == null) {
            return new ArrayList<String>();
        }

        return result;
    }

    /**
     *
     * @param grtap
     * @return
     */
    @Override
    public List<Tapri16> getListTapri16(String grtap) {
        Query query = em.createQuery("select tapri16 from Tapri16 tapri16 "
                + "where tapri16.sttap = 'SI' and tapri16.grtap like :grtap order by tapri16.detap", Tapri16.class);

        query.setParameter("grtap", "%" + grtap + "%");

        List<Tapri16> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Tapri16>();
        }

        return result;
    }

    /**
     *
     * @param grtap
     * @param hmtap
     * @return
     */
    @Override
    public String getTapri16Detap(String grtap, String hmtap) {
        Query query = em.createQuery("select tapri16.detap from Tapri16 tapri16 "
                + "where tapri16.sttap = 'SI' and tapri16.grtap = :grtap and tapri16.hmtap = :hmtap order by tapri16.detap", String.class);

        query.setParameter("grtap", grtap);
        query.setParameter("hmtap", hmtap);

        query.setFirstResult(0);
        query.setMaxResults(1);
        String result;

        try {
            result = (String) query.getSingleResult();
        } catch (NoResultException ex) {
            result = "";
        }

        if (result.equals("")) {
            result = grtap;
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Tapri16> retrieveTapri16() {
        Query query = em.createQuery("select tapri16 from Tapri16 tapri16  order by tapri16.grtap", Tapri16.class);

        List<Tapri16> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Tapri16>();
        }

        return result;
    }

    /**
     *
     * @param tapri16
     * @return
     * @throws Tapri16AlreadyExists
     */
    @Override
    public Tapri16 registerTapri16(Tapri16 tapri16) throws Tapri16AlreadyExists {

        em.persist(tapri16);
        em.flush();

        return tapri16;
    }

    /**
     *
     * @param tapri16
     * @throws Tapri16NotFound
     */
    @Override
    public void deleteTapri16(Tapri16 tapri16) throws Tapri16NotFound {
        Tapri16 updatableTapri16 = em.find(Tapri16.class, tapri16.getIdtap());

        if (updatableTapri16 == null) {
            throw new Tapri16NotFound();
        }

        em.merge(tapri16);
        em.flush();
    }

    /**
     *
     * @param tapri16
     * @throws Tapri16NotFound
     */
    @Override
    public void removeTapri16(Tapri16 tapri16) throws Tapri16NotFound {
        Tapri16 updatableTapri16 = em.find(Tapri16.class, tapri16.getIdtap());

        if (updatableTapri16 == null) {
            throw new Tapri16NotFound();
        }

        if (!em.contains(tapri16)) {
            tapri16 = em.merge(tapri16);
        }

        em.remove(tapri16);
        em.flush();
    }

    /**
     *
     * @param tapri16
     * @throws Tapri16NotFound
     */
    @Override
    public void updateTapri16(Tapri16 tapri16) throws Tapri16NotFound {
        Tapri16 updatableTapri16 = em.find(Tapri16.class, tapri16.getIdtap());

        if (updatableTapri16 == null) {
            throw new Tapri16NotFound();
        }

        em.merge(tapri16);
        em.flush();
    }
}
