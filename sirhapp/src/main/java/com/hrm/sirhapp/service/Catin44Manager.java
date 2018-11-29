package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Catin44;
import com.hrm.sirhapp.service.exception.Catin44AlreadyExists;
import com.hrm.sirhapp.service.exception.Catin44NotFound;
import com.hrm.sirhapp.util.FacesUtil;
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
public class Catin44Manager implements Catin44ManagerLocal {

    private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(Catin44Manager.class);

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idcin
     * @return
     * @throws Catin44NotFound
     */
    @Override
    public Catin44 getCatin44(Integer idcin) throws Catin44NotFound {
        Query query = em.createQuery("SELECT catin44 FROM Catin44 catin44 WHERE "
                + "catin44.idcin = :idcin");

        query.setParameter("idcin", idcin);

        Catin44 catin44Info;

        try {
            catin44Info = (Catin44) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Catin44NotFound();
        }

        Catin44 catin44 = catin44Info;
        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + catin44.getIdcin() + " | SEL: " + catin44.toString() + " | ACT: " + this.getClass().getName() + ".catin44");

        return catin44;
    }

    /**
     *
     * @param arcin
     * @return
     */
    @Override
    public List<String> getListCatin44Arcin(String arcin) {

        Query listIdsQry = em.createQuery("select catin44.idcin from Catin44 catin44  "
                + "where catin44.stcin = 'SI' ", Integer.class);

        List<Integer> listIds = listIdsQry.getResultList();

        Collections.shuffle(listIds);

        int randomSeriesLength = 1;

        if (listIds.size() >= 10) {
            randomSeriesLength = 10;
        } else {
            randomSeriesLength = listIds.size();
        }

        List<Integer> randomSeries = listIds.subList(0, randomSeriesLength);

        String str = "select DISTINCT(UPPER(catin44.arcin)) from Catin44 catin44  "
                + "where 1=1  and catin44.stcin = 'SI' ";

        Query query = em.createQuery(str, String.class);

        if (arcin != null && arcin.length() > 0) {
            str = str + " and catin44.arcin like :arcin ";
            query = em.createQuery(str, String.class);
            query.setParameter("arcin", "%" + arcin + "%");
        } else {
            str = "SELECT DISTINCT(UPPER(catin44.arcin)) from Catin44 catin44 "
                    + "WHERE catin44.idcin IN :indexs and catin44.stcin = 'SI' ";
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
     * @param arcin
     * @param macin
     * @return
     */
    @Override
    public List<String> getListCatin44Macin(String arcin, String macin) {

        Query listIdsQry = em.createQuery("select catin44.idcin from Catin44 catin44  "
                + "where catin44.stcin = 'SI' and  catin44.arcin ='" + arcin + "' ", Integer.class);

        List<Integer> listIds = listIdsQry.getResultList();

        Collections.shuffle(listIds);

        int randomSeriesLength = 1;

        if (listIds.size() >= 10) {
            randomSeriesLength = 10;
        } else {
            randomSeriesLength = listIds.size();
        }

        List<Integer> randomSeries = listIds.subList(0, randomSeriesLength);

        String str = "select DISTINCT(UPPER(catin44.macin)) from Catin44 catin44  "
                + "where 1=1  and catin44.stcin = 'SI' and  catin44.arcin ='" + arcin + "' ";

        Query query = em.createQuery(str, String.class);

        if (macin != null && macin.length() > 0) {
            str = str + " and catin44.macin like :macin  and  catin44.arcin ='" + arcin + "' ";
            query = em.createQuery(str, String.class);
            query.setParameter("macin", "%" + macin + "%");
        } else {
            str = "SELECT DISTINCT(UPPER(catin44.macin)) from Catin44 catin44 "
                    + "WHERE catin44.idcin IN :indexs and catin44.stcin = 'SI' and  catin44.arcin ='" + arcin + "' ";
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
     * @param arcin
     * @param macin
     * @param mocin
     * @return
     */
    @Override
    public List<String> getListCatin44Mocin(String arcin, String macin, String mocin) {

        Query listIdsQry = em.createQuery("select catin44.idcin from Catin44 catin44  "
                + "where catin44.stcin = 'SI' and  catin44.arcin ='" + arcin + "' and  catin44.macin ='" + macin + "' ", Integer.class);

        List<Integer> listIds = listIdsQry.getResultList();

        Collections.shuffle(listIds);

        int randomSeriesLength = 1;

        if (listIds.size() >= 10) {
            randomSeriesLength = 10;
        } else {
            randomSeriesLength = listIds.size();
        }

        List<Integer> randomSeries = listIds.subList(0, randomSeriesLength);

        String str = "select DISTINCT(UPPER(catin44.mocin)) from Catin44 catin44  "
                + "where 1=1  and catin44.stcin = 'SI' and  catin44.arcin ='" + arcin + "' and  catin44.macin ='" + macin + "' ";

        Query query = em.createQuery(str, String.class);

        if (mocin != null && mocin.length() > 0) {
            str = str + " and catin44.mocin like :mocin  and  catin44.arcin ='" + arcin + "' and  catin44.macin ='" + macin + "' ";
            query = em.createQuery(str, String.class);
            query.setParameter("mocin", "%" + mocin + "%");
        } else {
            str = "SELECT DISTINCT(UPPER(catin44.mocin)) from Catin44 catin44 "
                    + "WHERE catin44.idcin IN :indexs and catin44.stcin = 'SI' and  catin44.arcin ='" + arcin + "' and  catin44.macin ='" + macin + "' ";
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
     * @param arcin
     * @param macin
     * @param mocin
     * @param cocin
     * @return
     */
    @Override
    public List<String> getListCatin44Cocin(String arcin, String macin, String mocin, String cocin) {
        List<String> result = new ArrayList<String>();
        result.add("NA");

        Query listIdsQry = em.createQuery("select catin44.idcin from Catin44 catin44  "
                + "where catin44.stcin = 'SI' and  catin44.arcin ='" + arcin + "' and  catin44.macin ='" + macin + "' and  catin44.mocin ='" + mocin + "' and  catin44.cocin is not null ", Integer.class);

        List<Integer> listIds = listIdsQry.getResultList();

        Collections.shuffle(listIds);

        int randomSeriesLength = 1;

        if (listIds.size() >= 10) {
            randomSeriesLength = 10;
        } else {
            randomSeriesLength = listIds.size();
        }

        if (randomSeriesLength != 0) {

            List<Integer> randomSeries = listIds.subList(0, randomSeriesLength);

            String str = "select DISTINCT(UPPER(catin44.cocin)) from Catin44 catin44  "
                    + "where 1=1  and catin44.stcin = 'SI' and  catin44.arcin ='" + arcin + "' and  catin44.macin ='" + macin + "' and  catin44.mocin ='" + mocin + "' ";

            Query query = em.createQuery(str, String.class);

            if (cocin != null && cocin.length() > 0) {
                str = str + " and catin44.cocin like :cocin  and  catin44.arcin ='" + arcin + "' and  catin44.macin ='" + macin + "' and  catin44.mocin ='" + mocin + "' ";
                query = em.createQuery(str, String.class);
                query.setParameter("cocin", "%" + cocin + "%");
                System.out.println("evaluar primer query" + query.toString());
            } else {
                str = "SELECT DISTINCT(UPPER(catin44.cocin)) from Catin44 catin44 "
                        + "WHERE catin44.idcin IN :indexs and catin44.stcin = 'SI' and  catin44.arcin ='" + arcin + "' and  catin44.macin ='" + macin + "' and  catin44.mocin ='" + mocin + "' ";
                query = em.createQuery(str, String.class);
                query.setParameter("indexs", randomSeries);
                System.out.println("evaluar segundo query" + query.toString());
            }
            result = query.getResultList();
        }

        return result;
    }

    /**
     *
     * @param arcin
     * @param macin
     * @param mocin
     * @param cocin
     * @param tacin
     * @return
     */
    @Override
    public List<String> getListCatin44Tacin(String arcin, String macin, String mocin, String cocin, String tacin) {
        List<String> result = new ArrayList<String>();
        result.add("NA");

        Query listIdsQry = em.createQuery("select catin44.idcin from Catin44 catin44  "
                + "where catin44.stcin = 'SI' and  catin44.arcin ='" + arcin + "' and  catin44.macin ='" + macin + "' and  catin44.mocin ='" + mocin + "' and  catin44.cocin ='" + cocin + "' and  catin44.tacin is not null ", Integer.class);

        List<Integer> listIds = listIdsQry.getResultList();

        Collections.shuffle(listIds);

        int randomSeriesLength = 1;

        if (listIds.size() >= 10) {
            randomSeriesLength = 10;
        } else {
            randomSeriesLength = listIds.size();
        }

        if (randomSeriesLength != 0) {

            List<Integer> randomSeries = listIds.subList(0, randomSeriesLength);

            String str = "select DISTINCT(UPPER(catin44.tacin)) from Catin44 catin44  "
                    + "where 1=1  and catin44.stcin = 'SI' and  catin44.arcin ='" + arcin + "' and  catin44.macin ='" + macin + "' and  catin44.mocin ='" + mocin + "'  and  catin44.cocin ='" + cocin + "' ";

            Query query = em.createQuery(str, String.class);

            if (tacin != null && tacin.length() > 0) {
                str = str + " and catin44.tacin like :tacin  and  catin44.arcin ='" + arcin + "' and  catin44.macin ='" + macin + "' and  catin44.mocin ='" + mocin + "'  and  catin44.cocin ='" + cocin + "' ";
                query = em.createQuery(str, String.class);
                query.setParameter("tacin", "%" + tacin + "%");
            } else {
                str = "SELECT DISTINCT(UPPER(catin44.tacin)) from Catin44 catin44 "
                        + "WHERE catin44.idcin IN :indexs and catin44.stcin = 'SI' and  catin44.arcin ='" + arcin + "' and  catin44.macin ='" + macin + "' and  catin44.mocin ='" + mocin + "'  and  catin44.cocin ='" + cocin + "' ";
                query = em.createQuery(str, String.class);
                query.setParameter("indexs", randomSeries);
            }
            result = query.getResultList();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Catin44> getListCatin44() {
        Query query = em.createQuery("select catin44 from Catin44 catin44 "
                + "where catin44.stcin = 'SI' order by catin44.cecin ", Catin44.class);

        List<Catin44> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Catin44>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Catin44> retrieveCatin44() {
        Query query = em.createQuery("select catin44 from Catin44 catin44 order by catin44.cecin ", Catin44.class);

        List<Catin44> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Catin44>();
        }

        return result;
    }

    /**
     *
     * @param catin44
     * @return
     * @throws Catin44AlreadyExists
     */
    @Override
    public Catin44 registerCatin44(Catin44 catin44) throws Catin44AlreadyExists {

        em.persist(catin44);
        em.flush();
        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + catin44.getIdcin() + " | NEW: " + catin44.toString() + " | ACT: " + this.getClass().getName() + ".registerCatin44");

        return catin44;
    }

    /**
     *
     * @param catin44
     * @throws Catin44NotFound
     */
    @Override
    public void removeCatin44(Catin44 catin44) throws Catin44NotFound {
        Catin44 updatableCatin44 = em.find(Catin44.class, catin44.getIdcin());

        if (updatableCatin44 == null) {
            throw new Catin44NotFound();
        }

        if (!em.contains(catin44)) {
            catin44 = em.merge(catin44);
        }

        em.remove(catin44);
        em.flush();
        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + catin44.getIdcin() + " | REM: " + catin44.toString() + " | ACT: " + this.getClass().getName() + ".deleteCatin44");

    }

    /**
     *
     * @param catin44
     * @throws Catin44NotFound
     */
    @Override
    public void deleteCatin44(Catin44 catin44) throws Catin44NotFound {
        Catin44 updatableCatin44 = em.find(Catin44.class, catin44.getIdcin());

        if (updatableCatin44 == null) {
            throw new Catin44NotFound();
        }

        em.merge(catin44);
        em.flush();

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + catin44.getIdcin() + " | UPD: " + updatableCatin44.toString() + " | ACT: " + this.getClass().getName() + ".deleteCatin44");
        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + catin44.getIdcin() + " | DEL: " + catin44.toString() + " | ACT: " + this.getClass().getName() + ".deleteCatin44");

    }

    /**
     *
     * @param catin44
     * @throws Catin44NotFound
     */
    @Override
    public void updateCatin44(Catin44 catin44) throws Catin44NotFound {
        Catin44 updatableCatin44 = em.find(Catin44.class, catin44.getIdcin());

        if (updatableCatin44 == null) {
            throw new Catin44NotFound();
        }

        em.merge(catin44);
        em.flush();
        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + catin44.getIdcin() + " | UPD: " + updatableCatin44.toString() + " | ACT: " + this.getClass().getName() + ".updateCatin44");
        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + catin44.getIdcin() + " | DAT: " + catin44.toString() + " | ACT: " + this.getClass().getName() + ".updateCatin44");

    }
}
