package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Apriv25;
import com.hrm.sirhapp.service.exception.Apriv25AlreadyExists;
import com.hrm.sirhapp.service.exception.Apriv25NotFound;
import com.hrm.sirhapp.util.FacesUtil;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import static javax.persistence.ParameterMode.IN;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Stateless
public class Apriv25Manager implements Apriv25ManagerLocal {

    private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(Traba36aManager.class);

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idapr
     * @return
     * @throws Apriv25NotFound
     */
    @Override
    public Apriv25 getApriv25(Integer idapr) throws Apriv25NotFound {
        Query query = em.createQuery("SELECT apriv25 FROM Apriv25 apriv25 "
                + "WHERE apriv25.idapr = :idapr");

        query.setParameter("idapr", idapr);

        Apriv25 apriv25Info;

        try {
            apriv25Info = (Apriv25) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Apriv25NotFound();
        }

        Apriv25 apriv25 = apriv25Info;

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + apriv25.getIdapr() + " | SEL: " + apriv25.toString() + " | ACT: " + this.getClass().getName() + ".getApriv25");

        return apriv25;
    }

    /**
     *
     * @param idapr
     * @return
     */
    @Override
    public Boolean alreadyExistsInnactive(Integer idapr) {
        Query query = em.createQuery("select apriv25 from Apriv25 apriv25 "
                + "WHERE apriv25.stapr='SI' and apriv25.idapr = :idapr");

        query.setParameter("idapr", idapr);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    /**
     *
     * @param nuapr
     * @return
     */
    @Override
    public List<Apriv25> getListApriv25(String nuapr) {
        Query query = em.createQuery("select apriv25 from Apriv25 apriv25 "
                + "where apriv25.stapr='SI' and apriv25.nuapr = :nuapr", Apriv25.class);

        query.setParameter("nuapr", nuapr);

        System.out.println(query.toString());
        System.out.println(nuapr);

        List<Apriv25> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Apriv25>();
        }

        return result;
    }

    /**
     *
     * @param nuapr
     * @param tiapr
     * @return
     */
    @Override
    public List<Apriv25> getListApriv25(String nuapr, String tiapr) {
        Query query = em.createQuery("select apriv25 from Apriv25 apriv25 "
                + "where apriv25.stapr='SI' and apriv25.tiapr= :tiapr and apriv25.nuapr = :nuapr", Apriv25.class);

        query.setParameter("tiapr", tiapr);
        query.setParameter("nuapr", nuapr);

        List<Apriv25> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Apriv25>();
        }

        return result;

    }

    /**
     *
     * @param nutra
     * @return
     */
    @Override
    public List<Apriv25> getListApriv25Traba36(Integer nutra) {
        Query query = em.createQuery("select apriv25 from Apriv25 apriv25 where "
                + "apriv25.ntapr = :ntapr and apriv25.stapr='SI'", Apriv25.class);

        query.setParameter("ntapr", nutra);

        List<Apriv25> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Apriv25>();
        }

        return result;
    }

    /**
     *
     * @param text
     * @param size
     * @return
     */
    public static String fill(String text, int size) {
        StringBuilder builder = new StringBuilder(text);
        while (builder.length() < size) {
            builder.append('0');
        }
        return builder.toString();
    }

    /**
     *
     * @param esapr
     * @param ceapr
     * @param ndapr
     * @param period
     * @param esam
     * @param espa
     * @param espm
     * @param my_query
     * @return
     */
    @Override
    public List<Object[]> retrieveApriv25Repasp(
            String esapr,
            String ceapr,
            String ndapr,
            String period,
            String esam,
            String espa,
            String espm, 
            String my_query) {

        String parameter = espa;
        String my_query1="0";

        if (espa != null) {
            parameter = espa;
            my_query1 = "0";
        }

        if (esam != null) {
            parameter = String.valueOf(java.time.Year.now().getValue()).concat(fill(esam, 2));
            my_query1 = "1";
        }

        if (espm != null) {
            parameter = String.valueOf(java.time.Year.now().getValue()).concat(fill(espm, 2));
            my_query1 = "1";
        }
        
        if (my_query!=null){
            my_query1=my_query;;
        }

        StoredProcedureQuery query = em.createStoredProcedureQuery("get_ausencias");
        query.registerStoredProcedureParameter("my_year", String.class, IN);
        query.setParameter("my_year", parameter);
        query.registerStoredProcedureParameter("my_central", String.class, IN);
        query.setParameter("my_central", esapr);
        query.registerStoredProcedureParameter("my_enterprise", String.class, IN);
        query.setParameter("my_enterprise", ceapr);
        query.registerStoredProcedureParameter("my_department", String.class, IN);
        query.setParameter("my_department", ndapr);
        query.registerStoredProcedureParameter("my_query", String.class, IN);
        query.setParameter("my_query", my_query1);

        List<Object[]> my_list = query.getResultList();

        System.out.println(my_list);
        System.out.println("my_year" + parameter);
        System.out.println("my_central" + esapr);
        System.out.println("my_enterprise" + ceapr);
        System.out.println("my_department" + ndapr);
        System.out.println("my_query" + my_query);

        if (my_list == null) {
            return new ArrayList<Object[]>();
        }

        return my_list;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Apriv25> retrieveApriv25() {
        Query query = em.createQuery("select apriv25 from Apriv25 apriv25", Apriv25.class);

        List<Apriv25> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Apriv25>();
        }

        return result;
    }

    /**
     *
     * @param apriv25
     * @return
     * @throws Apriv25AlreadyExists
     */
    @Override
    public Apriv25 registerApriv25(Apriv25 apriv25) throws Apriv25AlreadyExists {

        em.persist(apriv25);
        em.flush();

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + apriv25.getIdapr() + " | NEW: " + apriv25.toString() + " | ACT: " + this.getClass().getName() + ".registerApriv25");

        return apriv25;
    }

    /**
     *
     * @param apriv25
     * @return
     * @throws Apriv25NotFound
     */
    @Override
    public Apriv25 deleteApriv25(Apriv25 apriv25) throws Apriv25NotFound {
        Apriv25 updatableApriv25 = em.find(Apriv25.class, apriv25.getIdapr());

        if (updatableApriv25 == null) {
            throw new Apriv25NotFound();
        }

        Apriv25 updatedApriv25 = em.merge(apriv25);
        em.flush();

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + apriv25.getIdapr() + " | UPD: " + updatableApriv25.toString() + " | ACT: " + this.getClass().getName() + ".deleteApriv25");
        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + apriv25.getIdapr() + " | DEL: " + apriv25.toString() + " | ACT: " + this.getClass().getName() + ".deleteApriv25");
        return updatedApriv25;
    }

    /**
     *
     * @param apriv25
     * @return
     * @throws Apriv25NotFound
     */
    @Override
    public Apriv25 updateApriv25(Apriv25 apriv25) throws Apriv25NotFound {
        Apriv25 updatableApriv25 = em.find(Apriv25.class, apriv25.getIdapr());

        if (updatableApriv25 == null) {
            throw new Apriv25NotFound();
        }

        Apriv25 updatedApriv25 = em.merge(apriv25);
        em.flush();

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + apriv25.getIdapr() + " | UPD: " + updatableApriv25.toString() + " | ACT: " + this.getClass().getName() + ".updateApriv25");
        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + apriv25.getIdapr() + " | DAT: " + apriv25.toString() + " | ACT: " + this.getClass().getName() + ".updateApriv25");
        return updatedApriv25;
    }

    /**
     *
     * @param apriv25
     * @throws Apriv25NotFound
     */
    @Override
    public void removeApriv25(Apriv25 apriv25) throws Apriv25NotFound {
        Apriv25 updatableApriv25 = em.find(Apriv25.class, apriv25.getIdapr());

        if (updatableApriv25 == null) {
            throw new Apriv25NotFound();
        }

        if (!em.contains(apriv25)) {
            apriv25 = em.merge(apriv25);
        }

        em.remove(apriv25);
        em.flush();

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + apriv25.getIdapr() + " | REM: " + apriv25.toString() + " | ACT: " + this.getClass().getName() + ".removeApriv25");

    }

}
