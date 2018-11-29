package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Niacc33;
import com.hrm.sirhapp.model.Petic51;
import com.hrm.sirhapp.model.Repor50;
import com.hrm.sirhapp.model.Usuar24;
import com.hrm.sirhapp.service.exception.Petic51AlreadyExists;
import com.hrm.sirhapp.service.exception.Petic51NotFound;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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
public class Petic51Manager implements Petic51ManagerLocal {

    private static final AtomicInteger count = new AtomicInteger(0);

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idpet
     * @return
     * @throws Petic51NotFound
     */
    @Override
    public Petic51 getPetic51(Integer idpet) throws Petic51NotFound {
        Query query = em.createQuery("SELECT petic51 FROM Petic51 petic51 WHERE "
                + "petic51.idpet = :idpet");

        query.setParameter("idpet", idpet);

        Petic51 petic51Info;

        try {
            petic51Info = (Petic51) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Petic51NotFound();
        }

        Petic51 petic51 = petic51Info;

        return petic51;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Petic51> getListPetic51() {
        Query query = em.createQuery("select petic51 from Petic51 petic51 "
                + "where petic51.stpet='SI'", Petic51.class);

        List<Petic51> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Petic51>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Petic51> retrievePetic51() {
        Query query = em.createQuery("select petic51 from Petic51 petic51", Petic51.class);

        List<Petic51> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Petic51>();
        }

        return result;
    }

    /**
     *
     * @param petic51
     * @return
     * @throws Petic51AlreadyExists
     */
    @Override
    public Petic51 sendPetic51(Petic51 petic51) throws Petic51AlreadyExists {
        Query query = em.createQuery("select petic51 from Petic51 petic51 where "
                + "petic51.repet.idrep = :idrep and petic51.cvpet.cvusu = :cvusu");

        query.setParameter("idrep", petic51.getRepet().getIdrep());
        query.setParameter("cvusu", petic51.getCvpet().getCvusu());

        try {
            query.getSingleResult();
            throw new Petic51AlreadyExists();
        } catch (NoResultException exception) {
            Logger.getLogger(Petic51Manager.class.getName()).log(Level.FINER, "No hay solicituds");
        }

        petic51.setSrpet(System.currentTimeMillis());
        petic51.setEspet(Constants.PENDING_REQUEST); //pending status...

        em.persist(petic51);
        em.flush();

        return petic51;
    }

    /**
     *
     * @param number
     * @throws Petic51NotFound
     */
    @Override
    public void approvePetic51(Integer number) throws Petic51NotFound {
        Petic51 info = em.find(Petic51.class, number);

        if (info == null) {
            throw new Petic51NotFound();
        }

        info.setEspet(Constants.APPROVED_REQUEST); //approved status
        info.setRspet(System.currentTimeMillis());

        em.merge(info);
        em.flush();
    }

    /**
     *
     * @param number
     * @throws Petic51NotFound
     */
    @Override
    public void rejectPetic51(Integer number) throws Petic51NotFound {
        Petic51 info = em.find(Petic51.class, number);

        if (info == null) {
            throw new Petic51NotFound();
        }

        info.setEspet(Constants.REJECTED_REQUEST); //rejected status
        info.setRspet(System.currentTimeMillis());

        em.merge(info);
        em.flush();
    }

    /**
     *
     * @param cvusu
     * @param status
     * @return
     */
    @Override
    public List<Petic51> viewRequests(String cvusu, int status) {

        String requestQuery = "select "
                + "petic51.idpet , "
                + "repor50.idrep, "
                + "repor50.tirep, "
                + "petic51.srpet, "
                + "petic51.rspet, "
                + "petic51.cvpet.cvusu "
                + "from Petic51 petic51 JOIN petic51.repet repor50 JOIN petic51.cvpet usuar24 "
                + "where petic51.espet = :espet";

        Query query;

        Niacc33 group = getNiacc33(cvusu);

        if (group.getNania() == null ? Constants.USER_GROUP == null : group.getNania().equals(Constants.USER_GROUP)) {
            requestQuery += " and petic51.cvpet.cvusu = :cvusu";

            query = em.createQuery(requestQuery);

            query.setParameter("espet", status);
            query.setParameter("cvusu", cvusu);
        } else {
            query = em.createQuery(requestQuery);

            query.setParameter("espet", status);
        }

        List<Petic51> petic51s = new ArrayList<>();

        List<Object[]> results = (List<Object[]>) query.getResultList();

        if (results == null) {
            return petic51s;
        }

        results.stream().map((result) -> {
            Petic51 petic51 = new Petic51((Integer) result[0]);
            Repor50 repor50 = new Repor50();
            repor50.setIdrep((Integer) result[1]);
            repor50.setTirep((String) result[2]);
            petic51.setRepet(repor50);
            petic51.setSrpet((Long) result[3]);
            petic51.setRspet((Long) result[4]);
            petic51.setCvpet(new Usuar24((Integer) count.incrementAndGet(), (String) result[5]));
            return petic51;
        }).forEachOrdered((petic51) -> {
            petic51s.add(petic51);
        });

        return petic51s;
    }

    private Niacc33 getNiacc33(String cvusu) {
        Query query = em.createQuery("Select niacc33 from Niacc33 niacc33 where niacc33.cvnia.cvusu=:cvusu and niacc33.stnia='SI' ", Niacc33.class);

        query.setParameter("cvusu", cvusu);

        Niacc33 group;

        try {
            group = (Niacc33) query.getSingleResult();
        } catch (NoResultException exception) {
            throw new IllegalStateException(cvusu + " El usuario no tiene rol asignado!!!");
        }

        return group;
    }

    /**
     *
     * @param petic51
     * @return
     * @throws Petic51AlreadyExists
     */
    @Override
    public Petic51 registerPetic51(Petic51 petic51) throws Petic51AlreadyExists {

        em.persist(petic51);
        em.flush();

        return petic51;
    }

    /**
     *
     * @param petic51
     * @throws Petic51NotFound
     */
    @Override
    public void removePetic51(Petic51 petic51) throws Petic51NotFound {
        Petic51 updatablePetic51 = em.find(Petic51.class, petic51.getIdpet());

        if (updatablePetic51 == null) {
            throw new Petic51NotFound();
        }
        if (!em.contains(petic51)) {
            petic51 = em.merge(petic51);
        }
        em.remove(petic51);
        em.flush();
    }

    /**
     *
     * @param petic51
     * @throws Petic51NotFound
     */
    @Override
    public void deletePetic51(Petic51 petic51) throws Petic51NotFound {
        Petic51 updatablePetic51 = em.find(Petic51.class, petic51.getIdpet());

        if (updatablePetic51 == null) {
            throw new Petic51NotFound();
        }

        em.merge(petic51);
        em.flush();
    }

    /**
     *
     * @param petic51
     * @throws Petic51NotFound
     */
    @Override
    public void updatePetic51(Petic51 petic51) throws Petic51NotFound {
        Petic51 updatablePetic51 = em.find(Petic51.class, petic51.getIdpet());

        if (updatablePetic51 == null) {
            throw new Petic51NotFound();
        }

        em.merge(petic51);
        em.flush();
    }
}
