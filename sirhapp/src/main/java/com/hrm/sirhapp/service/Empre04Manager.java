package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Empre04;
import com.hrm.sirhapp.model.Usuar24;
import com.hrm.sirhapp.service.exception.Empre04AlreadyExists;
import com.hrm.sirhapp.service.exception.Empre04NotFound;
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
public class Empre04Manager implements Empre04ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idemp
     * @return
     * @throws Empre04NotFound
     */
    @Override
    public Empre04 getEmpre04(Integer idemp) throws Empre04NotFound {
        Query query = em.createQuery("SELECT empre04 FROM  Empre04 empre04 WHERE "
                + "empre04.idemp = :idemp");

        query.setParameter("idemp", idemp);

        Empre04 empre04Info;

        try {
            empre04Info = (Empre04) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Empre04NotFound();
        }

        Empre04 empre04 = empre04Info;

        return empre04;
    }

    /**
     *
     * @param cvemp
     * @return
     * @throws Empre04NotFound
     */
    @Override
    public Empre04 getEmpre04(String cvemp) throws Empre04NotFound {
        Query query = em.createQuery("SELECT empre04 FROM  Empre04 empre04 WHERE "
                + "empre04.cvemp = :cvemp");

        query.setParameter("cvemp", cvemp);

        Empre04 empre04Info;

        try {
            empre04Info = (Empre04) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Empre04NotFound();
        }

        return empre04Info;

    }

    /**
     *
     * @return
     */
    @Override
    public List<Empre04> getListEmpre04() {

        Usuar24 ususar24 = FacesUtil.getUsuar24();

        String str = strQuery(ususar24);

        Query query = em.createQuery("select empre04 from Empre04 empre04 " + str + " ORDER BY empre04.seemp, empre04.oremp ", Empre04.class);

        List<Empre04> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Empre04>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Empre04> getListEmpre04All() {
        Query query = em.createQuery("select empre04 from Empre04 empre04 WHERE empre04.stemp='SI'  ORDER BY empre04.seemp, empre04.oremp ", Empre04.class);

        List<Empre04> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Empre04>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Empre04> retrieveEmpre04() {
        Query query = em.createQuery("select empre04 from Empre04 empre04", Empre04.class);

        List<Empre04> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Empre04>();
        }

        return result;
    }

    /**
     *
     * @param empre04
     * @return
     * @throws Empre04AlreadyExists
     */
    @Override
    public Empre04 registerEmpre04(Empre04 empre04) throws Empre04AlreadyExists {

        em.persist(empre04);
        em.flush();

        return empre04;
    }

    /**
     *
     * @param empre04
     * @throws Empre04NotFound
     */
    @Override
    public void removeEmpre04(Empre04 empre04) throws Empre04NotFound {
        Empre04 updatableEmpre04 = em.find(Empre04.class, empre04.getIdemp());

        if (updatableEmpre04 == null) {
            throw new Empre04NotFound();
        }
        if (!em.contains(empre04)) {
            empre04 = em.merge(empre04);
        }
        em.remove(empre04);
        em.flush();
    }

    /**
     *
     * @param empre04
     * @throws Empre04NotFound
     */
    @Override
    public void deleteEmpre04(Empre04 empre04) throws Empre04NotFound {
        Empre04 updatableEmpre04 = em.find(Empre04.class, empre04.getIdemp());

        if (updatableEmpre04 == null) {
            throw new Empre04NotFound();
        }

        em.merge(empre04);
        em.flush();
    }

    /**
     *
     * @param empre04
     * @throws Empre04NotFound
     */
    @Override
    public void updateEmpre04(Empre04 empre04) throws Empre04NotFound {
        Empre04 updatableEmpre04 = em.find(Empre04.class, empre04.getIdemp());

        if (updatableEmpre04 == null) {
            throw new Empre04NotFound();
        }

        em.merge(empre04);
        em.flush();
    }

    private String strQuery(Usuar24 usuar24) {
        String str = " WHERE empre04.cvemp<>0 and empre04.stemp='SI' ";
        String str2 = "";
        String str3 = "";

        if (usuar24.getEmusu() != null) {
            if (Integer.parseInt(usuar24.getEmusu()) > 0) {
                str2 = " and empre04.cvemp='" + usuar24.getEmusu() + "' ";
            }
        }

        if (usuar24.getSeusu() != null) {
            if (Integer.parseInt(usuar24.getSeusu()) > 0) {
                str3 = " and empre04.seemp='" + usuar24.getSeusu() + "' ";
            }
        }

        if (str2.length() > 0) {
            str += str2;
        }

        if (str3.length() > 0) {
            str += str3;
        }

        System.out.println(str);
        return str;
    }

}
