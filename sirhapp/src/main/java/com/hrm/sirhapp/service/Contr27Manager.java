package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Contr27;
import com.hrm.sirhapp.service.exception.Contr27AlreadyExists;
import com.hrm.sirhapp.service.exception.Contr27NotFound;
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
public class Contr27Manager implements Contr27ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param nucto
     * @return
     */
    @Override
    public Integer getNtctoNucto(String nucto) {
        Query query = em.createQuery("select contr27 from Contr27 contr27 "
                + "where contr27.stcto='SI' and contr27.nucto = :nucto", Contr27.class);

        query.setParameter("nucto", nucto);

        try {
            Contr27 result = (Contr27) query.getSingleResult();

            if (result != null) {
                return result.getNtcto();
            }
        } catch (Exception e) {
        }

        return null;
    }

    /**
     *
     * @param idcto
     * @return
     */
    @Override
    public Boolean alreadyExistsInnactive(Integer idcto) {
        Query query = em.createQuery("SELECT contr27 FROM Contr27 contr27 "
                + "WHERE contr27.stcto='NO' and contr27.idcto = :idcto");

        query.setParameter("idcto", idcto);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public List<Contr27> retrieveContr27() {
        Query query = em.createQuery("select contr27 from Contr27 contr27", Contr27.class);

        List<Contr27> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Contr27>();
        }

        return result;
    }

    /**
     *
     * @param idcto
     * @return
     * @throws Contr27NotFound
     */
    @Override
    public Contr27 getContr27(Integer idcto) throws Contr27NotFound {
        Query query = em.createQuery("SELECT contr27 FROM Contr27 contr27 "
                + "where contr27.idcto = :idcto");

        query.setParameter("idcto", idcto);

        Contr27 contr27Info;

        try {
            contr27Info = (Contr27) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Contr27NotFound();
        }

        Contr27 contr27 = contr27Info;

        return contr27;
    }

    /**
     *
     * @param rfcto
     * @return
     */
    @Override
    public List<Contr27> getListContr27Rfcto(String rfcto) {
        Query query = em.createQuery("select contr27 from Contr27 contr27 "
                + "where contr27.stcto='SI' and contr27.rfcto like :rfcto", Contr27.class);

        query.setParameter("rfcto", "%" + rfcto + "%");

        List<Contr27> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Contr27>();
        }

        return result;
    }

    /**
     *
     * @param ntcto
     * @return
     */
    @Override
    public List<Contr27> getListContr27Ntcto(Integer ntcto) {
        Query query = em.createQuery("select contr27 from Contr27 contr27 "
                + "where  contr27.ntcto = :ntcto order by  CAST(contr27.nucto AS UNSIGNED) desc", Contr27.class);

        query.setParameter("ntcto", ntcto);

        List<Contr27> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Contr27>();
        }

        return result;
    }

    /**
     *
     * @param nucto
     * @return
     */
    @Override
    public List<Contr27> getListContr27Nucto(String nucto) {
        Query query = em.createQuery("select contr27 from Contr27 contr27 "
                + "where contr27.stcto='SI' and contr27.nucto = :nucto", Contr27.class);

        query.setParameter("nucto", nucto);

        List<Contr27> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Contr27>();
        }

        return result;
    }

    /**
     *
     * @param idcto
     * @return
     */
    @Override
    public List<Contr27> getListContr27Idcto(Integer idcto) {
        Query query = em.createQuery("select contr27 from Contr27 contr27 "
                + "where contr27.stcto='SI' and contr27.idcto = :idcto", Contr27.class);

        query.setParameter("idcto", idcto);

        List<Contr27> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Contr27>();
        }

        return result;
    }

    /**
     *
     * @param contr27
     * @return
     */
    @Override
    public List<Contr27> getListContr27(Contr27 contr27) {

        String str = "select contr27 from Contr27 contr27 "
                + "where contr27.stcto='SI' ";
        Query query = em.createQuery(str, Contr27.class);
        System.out.println("7todos");

        if (contr27.getApcto() != null && contr27.getAmcto() == null && contr27.getNocto() == null) {
            str = str + " and contr27.apcto like :apcto ";
            query = em.createQuery(str, Contr27.class);
            query.setParameter("apcto", "%" + contr27.getApcto() + "%");
            System.out.println("0busqueda por paterno");
        }

        if (contr27.getApcto() != null && contr27.getAmcto() != null && contr27.getNocto() == null) {
            str = str + " and contr27.apcto like :apcto and contr27.amcto like :amcto ";
            query = em.createQuery(str, Contr27.class);
            query.setParameter("apcto", "%" + contr27.getApcto() + "%");
            query.setParameter("amcto", "%" + contr27.getAmcto() + "%");
            //System.out.println(contr27.getApcto());
            //System.out.println(contr27.getAmcto());
            System.out.println("1busqueda por paterno y materno ");
        }

        if (contr27.getApcto() != null && contr27.getAmcto() == null && contr27.getNocto() != null) {
            str = str + " and contr27.apcto like :apcto and contr27.nocto like :nocto ";
            query = em.createQuery(str, Contr27.class);
            query.setParameter("apcto", "%" + contr27.getApcto() + "%");
            query.setParameter("nocto", "%" + contr27.getNocto() + "%");
            //System.out.println(contr27.getApcto());
            //System.out.println(contr27.getNocto());
            System.out.println("2busqueda por paterno y nombres ");
        }

        if (contr27.getApcto() != null && contr27.getAmcto() != null && contr27.getNocto() != null) {
            str = str + " and contr27.apcto like :apcto and contr27.amcto like :amcto and contr27.nocto like :nocto";
            query = em.createQuery(str, Contr27.class);
            query.setParameter("apcto", "%" + contr27.getApcto() + "%");
            query.setParameter("amcto", "%" + contr27.getAmcto() + "%");
            query.setParameter("nocto", "%" + contr27.getNocto() + "%");
            //System.out.println(contr27.getApcto());
            //System.out.println(contr27.getAmcto());
            //System.out.println(contr27.getNocto());
            System.out.println("3busqueda por paterno, materno y nombres");
        }

        if (contr27.getApcto() == null && contr27.getAmcto() != null && contr27.getNocto() == null) {
            str = str + " and contr27.amcto like :amcto";
            query = em.createQuery(str, Contr27.class);
            query.setParameter("amcto", "%" + contr27.getAmcto() + "%");
            //System.out.println(contr27.getAmcto());
            System.out.println("4busqueda por materno");
        }

        if (contr27.getApcto() == null && contr27.getAmcto() != null && contr27.getNocto() != null) {
            str = str + " and contr27.amcto like :amcto and contr27.nocto like :nocto";
            query = em.createQuery(str, Contr27.class);
            query.setParameter("amcto", "%" + contr27.getAmcto() + "%");
            query.setParameter("nocto", "%" + contr27.getNocto() + "%");
            //System.out.println(contr27.getAmcto());
            //System.out.println(contr27.getNocto());
            System.out.println("5busqueda por materno y nombres");
        }

        if (contr27.getApcto() == null && contr27.getAmcto() == null && contr27.getNocto() != null) {
            str = str + "  and contr27.nocto like :nocto";
            query = em.createQuery(str, Contr27.class);
            query.setParameter("nocto", "%" + contr27.getNocto() + "%");
            //System.out.println(contr27.getNocto());
            System.out.println("6busqueda por nombres");
        }

        List<Contr27> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Contr27>();
        }

        return result;
    }

    /**
     *
     * @param contr27
     * @return
     * @throws Contr27AlreadyExists
     */
    @Override
    public Contr27 registerContr27(Contr27 contr27) throws Contr27AlreadyExists {

        em.persist(contr27);
        em.flush();

        return contr27;
    }

    /**
     *
     * @param contr27
     * @throws Contr27NotFound
     */
    @Override
    public void removeContr27(Contr27 contr27) throws Contr27NotFound {
        Contr27 updatableContr27 = em.find(Contr27.class, contr27.getIdcto());

        if (updatableContr27 == null) {
            throw new Contr27NotFound();
        }
        if (!em.contains(contr27)) {
            contr27 = em.merge(contr27);
        }
        em.remove(contr27);
        em.flush();
    }

    /**
     *
     * @param contr27
     * @return
     * @throws Contr27NotFound
     */
    @Override
    public Contr27 deleteContr27(Contr27 contr27) throws Contr27NotFound {
        Contr27 updatableContr27 = em.find(Contr27.class, contr27.getIdcto());

        if (updatableContr27 == null) {
            throw new Contr27NotFound();
        }

        updatableContr27 = em.merge(contr27);
        em.flush();
        return updatableContr27;
    }

    /**
     *
     * @param contr27
     * @return
     * @throws Contr27NotFound
     */
    @Override
    public Contr27 updateContr27(Contr27 contr27) throws Contr27NotFound {
        Contr27 updatableContr27 = em.find(Contr27.class, contr27.getIdcto());

        if (updatableContr27 == null) {
            throw new Contr27NotFound();
        }

        updatableContr27 = em.merge(contr27);
        em.flush();
        return updatableContr27;
    }

}
