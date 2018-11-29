package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Inven37;
import com.hrm.sirhapp.model.Usuar24;
import com.hrm.sirhapp.service.exception.Inven37AlreadyExists;
import com.hrm.sirhapp.service.exception.Inven37NotFound;
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
public class Inven37Manager implements Inven37ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idinv
     * @return
     * @throws Inven37NotFound
     */
    @Override
    public Inven37 getInven37(Integer idinv) throws Inven37NotFound {
        Query query = em.createQuery("SELECT inven37 FROM Inven37 inven37 WHERE "
                + "inven37.idinv = :idinv");

        query.setParameter("idinv", idinv);

        Inven37 inven37Info;

        try {
            inven37Info = (Inven37) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Inven37NotFound();
        }

        Inven37 inven37 = inven37Info;

        return inven37;
    }

    /**
     *
     * @param idinv
     * @return
     */
    @Override
    public List<Inven37> getListInven37(Integer idinv) {
        Query query = em.createQuery("select inven37 from Inven37 inven37 "
                + "where inven37.stinv='SI' and inven37.idinv = :idinv", Inven37.class);

        query.setParameter("idinv", idinv);

        List<Inven37> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Inven37>();
        }

        return result;
    }

    /**
     *
     * @param tInven37
     * @return
     */
    @Override
    public List<Inven37> getListInven37(Inven37 tInven37) {
        Usuar24 ususar24 = FacesUtil.getUsuar24();

        String str = strQuery(ususar24);

        Query query = em.createQuery("select inven37 from Inven37 inven37 "
                + str + "  and inven37.tiinv = :tiinv", Inven37.class);

        query.setParameter("tiinv", tInven37.getTiinv());

        List<Inven37> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Inven37>();
        }

        return result;

    }

    /**
     *
     * @return
     */
    @Override
    public List<Inven37> retrieveInven37() {
        Query query = em.createQuery("select inven37 from Inven37 inven37", Inven37.class);

        List<Inven37> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Inven37>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Inven37> retrieveInven37E() {
        Usuar24 ususar24 = FacesUtil.getUsuar24();

        String str = strQuery(ususar24);

        Query query = em.createQuery("select "
                + "inven37.ceinv, "
                + "inven37.neinv, "
                + "inven37.arinv, "
                + "inven37.mainv, "
                + "inven37.moinv, "
                + "inven37.coinv, "
                + "inven37.tainv, "
                + "SUM(inven37.cainv) from Inven37 inven37 "
                + str + " and inven37.tiinv='ENTRADA' "
                + "GROUP BY "
                + "inven37.ceinv, inven37.neinv, inven37.arinv, inven37.mainv, inven37.moinv, inven37.coinv, inven37.tainv ORDER BY inven37.neinv, inven37.arinv");

//ORDER BY inven37.ceinv, inven37.fiinv desc
        List<Object[]> results = query.getResultList();

        List<Inven37> listInven37 = new ArrayList<Inven37>();
        Inven37 inven37;

        for (int i = 0; i < results.size(); i++) {
            Object[] arr = results.get(i);

            inven37 = new Inven37();
            inven37.setIdinv(i);
            inven37.setCeinv(arr[0].toString());
            inven37.setNeinv(arr[1].toString());
            if (arr[2] != null) {
                inven37.setArinv(arr[2].toString());
            } else {
                inven37.setArinv("");
            }
            if (arr[3] != null) {
                inven37.setMainv(arr[3].toString());
            } else {
                inven37.setMainv("");
            }
            if (arr[4] != null) {
                inven37.setMoinv(arr[4].toString());
            } else {
                inven37.setMoinv("");
            }
            if (arr[5] != null) {
                inven37.setCoinv(arr[5].toString());
            } else {
                inven37.setCoinv("");
            }
            if (arr[6] != null) {
                inven37.setTainv(arr[6].toString());
            } else {
                inven37.setTainv("");
            }

            inven37.setCainv(Integer.valueOf(arr[7].toString()));
            listInven37.add(inven37);
            //AGREGA REGISTRO

            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[j] + " ");
            }
            System.out.println();

        }

        if (results == null) {
            return new ArrayList<Inven37>();
        }

        return listInven37;
    }

    /**
     *
     * @param inven37
     * @return
     */
    @Override
    public Long countOutInven37(Inven37 inven37) {

        try {
            System.out.println(inven37.getCeinv());
            System.out.println(inven37.getArinv());
            System.out.println(inven37.getMainv());
            System.out.println(inven37.getMoinv());
            System.out.println(inven37.getCoinv());
            System.out.println(inven37.getTainv());

            Query query = em.createQuery("SELECT sum(inven37.cainv) "
                    + "FROM Inven37 inven37 WHERE "
                    + "inven37.stinv='SI' AND "
                    + "inven37.tiinv='SALIDA' AND  "
                    + "inven37.ceinv='" + inven37.getCeinv() + "' AND "
                    + "inven37.arinv='" + inven37.getArinv() + "' AND "
                    + "inven37.mainv='" + inven37.getMainv() + "' AND "
                    + "inven37.moinv='" + inven37.getMoinv() + "' AND "
                    + "inven37.coinv='" + inven37.getCoinv() + "' AND "
                    + "inven37.tainv='" + inven37.getTainv() + "' "
                    + "GROUP BY "
                    + "inven37.ceinv, inven37.arinv, inven37.mainv, inven37.moinv, inven37.coinv, inven37.tainv ", Integer.class);

            Long result = (Long) query.getSingleResult();
            return result;
        } catch (NoResultException e) {
            return new Long(0);
        }
    }

    /**
     *
     * @param inven37
     * @return
     * @throws Inven37AlreadyExists
     */
    @Override
    public Inven37 registerInven37(Inven37 inven37) throws Inven37AlreadyExists {

        em.persist(inven37);
        em.flush();

        return inven37;
    }

    /**
     *
     * @param inven37
     * @throws Inven37NotFound
     */
    @Override
    public void removeInven37(Inven37 inven37) throws Inven37NotFound {
        Inven37 updatableInven37 = em.find(Inven37.class, inven37.getIdinv());

        if (updatableInven37 == null) {
            throw new Inven37NotFound();
        }
        if (!em.contains(inven37)) {
            inven37 = em.merge(inven37);
        }
        em.remove(inven37);
        em.flush();
    }

    /**
     *
     * @param inven37
     * @throws Inven37NotFound
     */
    @Override
    public void deleteInven37(Inven37 inven37) throws Inven37NotFound {
        Inven37 updatableInven37 = em.find(Inven37.class, inven37.getIdinv());

        if (updatableInven37 == null) {
            throw new Inven37NotFound();
        }

        em.merge(inven37);
        em.flush();
    }

    /**
     *
     * @param inven37
     * @throws Inven37NotFound
     */
    @Override
    public void updateInven37(Inven37 inven37) throws Inven37NotFound {
        Inven37 updatableInven37 = em.find(Inven37.class, inven37.getIdinv());

        if (updatableInven37 == null) {
            throw new Inven37NotFound();
        }

        em.merge(inven37);
        em.flush();
    }

    private String strQuery(Usuar24 usuar24) {
        String str = " WHERE inven37.stinv='SI' ";
        String str1 = "";

        if (usuar24.getEmusu() != null) {
            if (Integer.parseInt(usuar24.getEmusu()) > 0) {
                str1 = "and inven37.ceinv='" + usuar24.getEmusu() + "' ";
            }
        }

        if (str1.length() > 0) {
            str = str + str1;
        }

        System.out.println(str);
        return str;
    }

}
