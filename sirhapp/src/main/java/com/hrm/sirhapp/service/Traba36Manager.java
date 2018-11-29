package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Traba36;
import com.hrm.sirhapp.model.Usuar24;
import com.hrm.sirhapp.service.exception.Traba36AlreadyExists;
import com.hrm.sirhapp.service.exception.Traba36NotFound;
import java.util.ArrayList;
import java.util.List;
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
public class Traba36Manager implements Traba36ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param nutra
     * @return
     */
    @Override
    public Boolean alreadyExistsInnactive(Integer nutra) {
        Query query = em.createQuery("SELECT traba36 FROM Traba36 traba36 "
                + "WHERE traba36.sttra='NO' and traba36.nutra = :nutra");

        query.setParameter("nutra", nutra);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    /**
     *
     * @param nutra
     * @return
     */
    @Override
    public String getTraba36Nctra(Integer nutra) {
        Query query = em.createQuery("SELECT traba36 FROM Traba36 traba36 "
                + "where traba36.nutra = :nutra");

        query.setParameter("nutra", nutra);

        try {
            Traba36 traba36Info;
            query.setFirstResult(0);
            query.setMaxResults(1);
            traba36Info = (Traba36) query.getSingleResult();
            return traba36Info.getNctra();
        } catch (NoResultException ex) {
            return "No. DE TRABAJADOR INCORRECTO";
        }

    }

    /**
     *
     * @param nutra
     * @return
     * @throws Traba36NotFound
     */
    @Override
    public Traba36 getTraba36(Integer nutra) throws Traba36NotFound {
        Query query = em.createQuery("SELECT traba36 FROM Traba36 traba36 "
                + "where traba36.nutra = :nutra");

        query.setParameter("nutra", nutra);

        Traba36 traba36Info;

        try {
            traba36Info = (Traba36) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Traba36NotFound();
        }

        Traba36 traba36 = traba36Info;

        return traba36;
    }

    //Obtien ela Tabla de Trabajadores por con permisos y registros activos

    /**
     *
     * @param usuar24
     * @return
     */
    @Override
    public List<Traba36> retrieveTraba36(Usuar24 usuar24) {

        String qry = strQuery(usuar24);

        Query query = em.createQuery("select traba36 from Traba36 traba36" + qry, Traba36.class);

        List<Traba36> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Traba36>();
        }

        return result;
    }

    //Busqueda en la Tabla de Trabajadores por Nombre,Rfc y curp sin condiciones

    /**
     *
     * @param traba36
     * @param usuar24
     * @return
     */
    @Override
    public List<Traba36> getListTraba36PvWiz(Traba36 traba36, Usuar24 usuar24) {
        String qry = strQueryPv(usuar24);

        String str = "select traba36 from Traba36 traba36 "
                + qry;
        Query query = em.createQuery(str, Traba36.class);

        if (traba36.getAptra() != null && traba36.getAmtra() == null && traba36.getNotra() == null) {
            str = str + " and traba36.aptra like :aptra ";
            //query = em.createQuery(str, Traba36.class);
            //query.setParameter("aptra", "%" + traba36.getAptra() + "%");
            System.out.println("0busqueda por paterno");
        }

        if (traba36.getAptra() != null && traba36.getAmtra() != null && traba36.getNotra() == null) {
            str = str + " and traba36.aptra like :aptra and traba36.amtra like :amtra ";
            //query = em.createQuery(str, Traba36.class);
            //query.setParameter("aptra", "%" + traba36.getAptra() + "%");
            //query.setParameter("amtra", "%" + traba36.getAmtra() + "%");
            //System.out.println(traba36.getAptra());
            //System.out.println(traba36.getAmtra());
            System.out.println("1busqueda por paterno y materno ");
        }

        if (traba36.getAptra() != null && traba36.getAmtra() == null && traba36.getNotra() != null) {
            str = str + " and traba36.aptra like :aptra and traba36.notra like :notra ";
            //query = em.createQuery(str, Traba36.class);
            //query.setParameter("aptra", "%" + traba36.getAptra() + "%");
            //query.setParameter("notra", "%" + traba36.getNotra() + "%");
            //System.out.println(traba36.getAptra());
            //System.out.println(traba36.getNotra());
            System.out.println("2busqueda por paterno y nombres ");
        }

        if (traba36.getAptra() != null && traba36.getAmtra() != null && traba36.getNotra() != null) {
            str = str + " and traba36.aptra like :aptra and traba36.amtra like :amtra and traba36.notra like :notra";
            //query = em.createQuery(str, Traba36.class);
            //query.setParameter("aptra", "%" + traba36.getAptra() + "%");
            //query.setParameter("amtra", "%" + traba36.getAmtra() + "%");
            //query.setParameter("notra", "%" + traba36.getNotra() + "%");
            //System.out.println(traba36.getAptra());
            //System.out.println(traba36.getAmtra());
            //System.out.println(traba36.getNotra());
            System.out.println("3busqueda por paterno, materno y nombres");
        }

        if (traba36.getAptra() == null && traba36.getAmtra() != null && traba36.getNotra() == null) {
            str = str + " and traba36.amtra like :amtra";
            //query = em.createQuery(str, Traba36.class);
            //query.setParameter("amtra", "%" + traba36.getAmtra() + "%");
            //System.out.println(traba36.getAmtra());
            System.out.println("4busqueda por materno");
        }

        if (traba36.getAptra() == null && traba36.getAmtra() != null && traba36.getNotra() != null) {
            str = str + " and traba36.amtra like :amtra and traba36.notra like :notra";
            //query = em.createQuery(str, Traba36.class);
            //query.setParameter("amtra", "%" + traba36.getAmtra() + "%");
            //query.setParameter("notra", "%" + traba36.getNotra() + "%");
            //System.out.println(traba36.getAmtra());
            //System.out.println(traba36.getNotra());
            System.out.println("5busqueda por materno y nombres");
        }

        if (traba36.getAptra() == null && traba36.getAmtra() == null && traba36.getNotra() != null) {
            str = str + "  and traba36.notra like :notra";
            //query = em.createQuery(str, Traba36.class);
            //query.setParameter("notra", "%" + traba36.getNotra() + "%");
            //System.out.println(traba36.getNotra());
            System.out.println("6busqueda por nombres");
        }

        if (traba36.getNutra() != null && traba36.getNutra() > 0) {
            str = str + " and traba36.nutra = :nutra  ";
            System.out.println("10busqueda por numero ");
        }

        if (traba36.getRftra() != null && traba36.getRftra().length() > 0) {
            str = str + " and traba36.rftra like :rftra  ";
            System.out.println("7busqueda por rfc ");
        }

        if (traba36.getCutra() != null && traba36.getCutra().length() > 0) {
            str = str + " and traba36.cutra like :cutra";
            System.out.println("8busqueda por curp");
        }

        if (traba36.getRitra() != null && traba36.getRitra().length() > 0) {
            str = str + " and traba36.ritra = :ritra";
            System.out.println("9busqueda por Imss");
        }

        query = em.createQuery(str, Traba36.class);

        if (traba36.getAmtra() != null && traba36.getAmtra().length() > 0) {
            query.setParameter("amtra", "%" + traba36.getAmtra() + "%");
        }
        if (traba36.getAptra() != null && traba36.getAptra().length() > 0) {
            query.setParameter("aptra", "%" + traba36.getAptra() + "%");
        }
        if (traba36.getNotra() != null && traba36.getNotra().length() > 0) {
            query.setParameter("notra", "%" + traba36.getNotra() + "%");
        }
        if (traba36.getNutra() != null && traba36.getNutra() > 0) {
            System.out.println("10nutra:getListTraba36PvWiz" + traba36.getNutra());
            query.setParameter("nutra", traba36.getNutra());
        }
        if (traba36.getRftra() != null && traba36.getRftra().length() > 0) {
            System.out.println("7rftra:getListTraba36PvWiz" + traba36.getRftra());
            query.setParameter("rftra", "%" + traba36.getRftra() + "%");
        }
        if (traba36.getCutra() != null && traba36.getCutra().length() > 0) {
            query.setParameter("cutra", "%" + traba36.getCutra() + "%");
        }

        if (traba36.getRitra() != null && traba36.getRitra().length() > 0) {
            query.setParameter("ritra", traba36.getRitra());
        }

        List<Traba36> result = query.getResultList();
        System.out.println("resultados" + result.size());

        if (result == null) {
            return new ArrayList<Traba36>();
        }

        return result;
    }

    //Busqueda en la Tabla de Trabajadores por Nombre,Rfc y curp sin condiciones

    /**
     *
     * @param traba36
     * @return
     */
    @Override
    public List<Traba36> getListTraba36Wiz(Traba36 traba36) {

        String str = "select traba36 from Traba36 traba36 "
                + "where 1=1  ";
        Query query = em.createQuery(str, Traba36.class);

        if (traba36.getAptra() != null && traba36.getAmtra() == null && traba36.getNotra() == null) {
            str = str + " and traba36.aptra like :aptra ";
            //query = em.createQuery(str, Traba36.class);
            //query.setParameter("aptra", "%" + traba36.getAptra() + "%");
            System.out.println("0busqueda por paterno");
        }

        if (traba36.getAptra() != null && traba36.getAmtra() != null && traba36.getNotra() == null) {
            str = str + " and traba36.aptra like :aptra and traba36.amtra like :amtra ";
            //query = em.createQuery(str, Traba36.class);
            //query.setParameter("aptra", "%" + traba36.getAptra() + "%");
            //query.setParameter("amtra", "%" + traba36.getAmtra() + "%");
            //System.out.println(traba36.getAptra());
            //System.out.println(traba36.getAmtra());
            System.out.println("1busqueda por paterno y materno ");
        }

        if (traba36.getAptra() != null && traba36.getAmtra() == null && traba36.getNotra() != null) {
            str = str + " and traba36.aptra like :aptra and traba36.notra like :notra ";
            //query = em.createQuery(str, Traba36.class);
            //query.setParameter("aptra", "%" + traba36.getAptra() + "%");
            //query.setParameter("notra", "%" + traba36.getNotra() + "%");
            //System.out.println(traba36.getAptra());
            //System.out.println(traba36.getNotra());
            System.out.println("2busqueda por paterno y nombres ");
        }

        if (traba36.getAptra() != null && traba36.getAmtra() != null && traba36.getNotra() != null) {
            str = str + " and traba36.aptra like :aptra and traba36.amtra like :amtra and traba36.notra like :notra";
            //query = em.createQuery(str, Traba36.class);
            //query.setParameter("aptra", "%" + traba36.getAptra() + "%");
            //query.setParameter("amtra", "%" + traba36.getAmtra() + "%");
            //query.setParameter("notra", "%" + traba36.getNotra() + "%");
            //System.out.println(traba36.getAptra());
            //System.out.println(traba36.getAmtra());
            //System.out.println(traba36.getNotra());
            System.out.println("3busqueda por paterno, materno y nombres");
        }

        if (traba36.getAptra() == null && traba36.getAmtra() != null && traba36.getNotra() == null) {
            str = str + " and traba36.amtra like :amtra";
            //query = em.createQuery(str, Traba36.class);
            //query.setParameter("amtra", "%" + traba36.getAmtra() + "%");
            //System.out.println(traba36.getAmtra());
            System.out.println("4busqueda por materno");
        }

        if (traba36.getAptra() == null && traba36.getAmtra() != null && traba36.getNotra() != null) {
            str = str + " and traba36.amtra like :amtra and traba36.notra like :notra";
            //query = em.createQuery(str, Traba36.class);
            //query.setParameter("amtra", "%" + traba36.getAmtra() + "%");
            //query.setParameter("notra", "%" + traba36.getNotra() + "%");
            //System.out.println(traba36.getAmtra());
            //System.out.println(traba36.getNotra());
            System.out.println("5busqueda por materno y nombres");
        }

        if (traba36.getAptra() == null && traba36.getAmtra() == null && traba36.getNotra() != null) {
            str = str + "  and traba36.notra like :notra";
            //query = em.createQuery(str, Traba36.class);
            //query.setParameter("notra", "%" + traba36.getNotra() + "%");
            //System.out.println(traba36.getNotra());
            System.out.println("6busqueda por nombres");
        }

        if (traba36.getRftra() != null && traba36.getRftra().length() > 0) {
            str = str + " and traba36.rftra like :rftra  ";
            System.out.println("7busqueda por rfc ");
        }

        if (traba36.getCutra() != null && traba36.getCutra().length() > 0) {
            str = str + " and traba36.cutra like :cutra";
            System.out.println("8busqueda por curp");
        }

        if (traba36.getRitra() != null && traba36.getRitra().length() > 0) {
            str = str + " and traba36.ritra = :ritra";
            System.out.println("9busqueda por Imss");
        }

        query = em.createQuery(str, Traba36.class);

        if (traba36.getAmtra() != null && traba36.getAmtra().length() > 0) {
            query.setParameter("amtra", "%" + traba36.getAmtra() + "%");
        }
        if (traba36.getAptra() != null && traba36.getAptra().length() > 0) {
            query.setParameter("aptra", "%" + traba36.getAptra() + "%");
        }
        if (traba36.getNotra() != null && traba36.getNotra().length() > 0) {
            query.setParameter("notra", "%" + traba36.getNotra() + "%");
        }
        if (traba36.getRftra() != null && traba36.getRftra().length() > 0) {
            System.out.println("7rftra:getListTraba36Wiz" + traba36.getRftra());
            query.setParameter("rftra", "%" + traba36.getRftra() + "%");
        }
        if (traba36.getCutra() != null && traba36.getCutra().length() > 0) {
            query.setParameter("cutra", "%" + traba36.getCutra() + "%");
        }

        if (traba36.getRitra() != null && traba36.getRitra().length() > 0) {
            query.setParameter("ritra", traba36.getRitra());
        }

        List<Traba36> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Traba36>();
        }

        return result;
    }

    //Busqueda en la Tabla de Trabajadores por Numero de Trabajador con permisos de usuario y registros activos

    /**
     *
     * @param nutra
     * @param usuar24
     * @return
     */
    @Override
    public List<Traba36> getListTraba36(Integer nutra, Usuar24 usuar24) {

        String qry = strQuery(usuar24);
        Query query = em.createQuery("select traba36 from Traba36 traba36 "
                + qry + " and traba36.nutra = :nutra", Traba36.class);

        query.setParameter("nutra", nutra);

        List<Traba36> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Traba36>();
        }

        return result;
    }

    //Busqueda en la Tabla de Trabajadores por Rfc de Trabajador con permisos de usuario y registros activos

    /**
     *
     * @param rftra
     * @param usuar24
     * @return
     */
    @Override
    public List<Traba36> getListTraba36(String rftra, Usuar24 usuar24) {
        String qry = strQuery(usuar24);
        Query query = em.createQuery("select traba36 from Traba36 traba36 "
                + qry + " and  traba36.rftra like :rftra", Traba36.class);

        query.setParameter("rftra", "%" + rftra + "%");

        List<Traba36> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Traba36>();
        }

        return result;
    }

    //Busqueda en la Tabla de Trabajadores por Nombre de Trabajador con permisos de usuario y registros activos

    /**
     *
     * @param traba36
     * @param usuar24
     * @return
     */
    @Override
    public List<Traba36> getListTraba36(Traba36 traba36, Usuar24 usuar24) {
        String qry = strQuery(usuar24);

        String str = "select traba36 from Traba36 traba36 "
                + qry + " ";
        Query query = em.createQuery(str, Traba36.class);
        System.out.println("7todos");

        if (traba36.getAptra() != null && traba36.getAmtra() == null && traba36.getNotra() == null) {
            str = str + " and traba36.aptra like :aptra ";
            query = em.createQuery(str, Traba36.class);
            query.setParameter("aptra", "%" + traba36.getAptra() + "%");
            System.out.println("0busqueda por paterno");
        }

        if (traba36.getAptra() != null && traba36.getAmtra() != null && traba36.getNotra() == null) {
            str = str + " and traba36.aptra like :aptra and traba36.amtra like :amtra ";
            query = em.createQuery(str, Traba36.class);
            query.setParameter("aptra", "%" + traba36.getAptra() + "%");
            query.setParameter("amtra", "%" + traba36.getAmtra() + "%");
            //System.out.println(traba36.getAptra());
            //System.out.println(traba36.getAmtra());
            System.out.println("1busqueda por paterno y materno ");
        }

        if (traba36.getAptra() != null && traba36.getAmtra() == null && traba36.getNotra() != null) {
            str = str + " and traba36.aptra like :aptra and traba36.notra like :notra ";
            query = em.createQuery(str, Traba36.class);
            query.setParameter("aptra", "%" + traba36.getAptra() + "%");
            query.setParameter("notra", "%" + traba36.getNotra() + "%");
            //System.out.println(traba36.getAptra());
            //System.out.println(traba36.getNotra());
            System.out.println("2busqueda por paterno y nombres ");
        }

        if (traba36.getAptra() != null && traba36.getAmtra() != null && traba36.getNotra() != null) {
            str = str + " and traba36.aptra like :aptra and traba36.amtra like :amtra and traba36.notra like :notra";
            query = em.createQuery(str, Traba36.class);
            query.setParameter("aptra", "%" + traba36.getAptra() + "%");
            query.setParameter("amtra", "%" + traba36.getAmtra() + "%");
            query.setParameter("notra", "%" + traba36.getNotra() + "%");
            //System.out.println(traba36.getAptra());
            //System.out.println(traba36.getAmtra());
            //System.out.println(traba36.getNotra());
            System.out.println("3busqueda por paterno, materno y nombres");
        }

        if (traba36.getAptra() == null && traba36.getAmtra() != null && traba36.getNotra() == null) {
            str = str + " and traba36.amtra like :amtra";
            query = em.createQuery(str, Traba36.class);
            query.setParameter("amtra", "%" + traba36.getAmtra() + "%");
            //System.out.println(traba36.getAmtra());
            System.out.println("4busqueda por materno");
        }

        if (traba36.getAptra() == null && traba36.getAmtra() != null && traba36.getNotra() != null) {
            str = str + " and traba36.amtra like :amtra and traba36.notra like :notra";
            query = em.createQuery(str, Traba36.class);
            query.setParameter("amtra", "%" + traba36.getAmtra() + "%");
            query.setParameter("notra", "%" + traba36.getNotra() + "%");
            //System.out.println(traba36.getAmtra());
            //System.out.println(traba36.getNotra());
            System.out.println("5busqueda por materno y nombres");
        }

        if (traba36.getAptra() == null && traba36.getAmtra() == null && traba36.getNotra() != null) {
            str = str + "  and traba36.notra like :notra";
            query = em.createQuery(str, Traba36.class);
            query.setParameter("notra", "%" + traba36.getNotra() + "%");
            //System.out.println(traba36.getNotra());
            System.out.println("6busqueda por nombres");
        }

        List<Traba36> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Traba36>();
        }

        return result;
    }

    private String strQueryPv(Usuar24 usuar24) {
        String str = " WHERE 1=1 ";
        String str2 = "";
        String str3 = "";
        String str4 = "";

        if (usuar24.getSeusu() != null) {
            if (Integer.parseInt(usuar24.getSeusu()) > 0) {
                str2 = "and traba36.sitra='" + usuar24.getSeusu() + "' ";
            }
        }

        if (usuar24.getEmusu() != null) {
            if (Integer.parseInt(usuar24.getEmusu()) > 0) {
                str3 = "and traba36.eitra='" + usuar24.getEmusu() + "' ";
            }
        }

        if (usuar24.getDeusu() != null) {
            if (Integer.parseInt(usuar24.getDeusu()) > 0) {
                str4 = "and traba36.ditra='" + usuar24.getDeusu() + "' ";
            }
        }

        if (str2.length() > 0 || str3.length() > 0 || str3.length() > 0) {
            str = str + str2 + str3 + str4;
        }

        System.out.println(str);
        return str;
    }

    private String strQuery(Usuar24 usuar24) {
        String str = " WHERE traba36.sttra='SI' ";
        String str2 = "";
        String str3 = "";
        String str4 = "";

        if (usuar24.getSeusu() != null) {
            if (Integer.parseInt(usuar24.getSeusu()) > 0) {
                str2 = "and traba36.sitra='" + usuar24.getSeusu() + "' ";
            }
        }

        if (usuar24.getEmusu() != null) {
            if (Integer.parseInt(usuar24.getEmusu()) > 0) {
                str3 = "and traba36.eitra='" + usuar24.getEmusu() + "' ";
            }
        }

        if (usuar24.getDeusu() != null) {
            if (Integer.parseInt(usuar24.getDeusu()) > 0) {
                str4 = "and traba36.ditra='" + usuar24.getDeusu() + "' ";
            }
        }

        if (str2.length() > 0 || str3.length() > 0 || str3.length() > 0) {
            str = str + str2 + str3 + str4;
        }

        System.out.println(str);
        return str;
    }

    /**
     *
     * @param traba36
     * @return
     * @throws Traba36AlreadyExists
     */
    @Override
    public Traba36 registerTraba36(Traba36 traba36) throws Traba36AlreadyExists {
        Query query = em.createQuery("select traba36 from Traba36 traba36 "
                + "where traba36.nutra = :nutra");

        query.setParameter("nutra", traba36.getNutra());

        try {
            query.getSingleResult();
            throw new Traba36AlreadyExists();
        } catch (NoResultException ex) {
            Logger.getLogger(Usuar24Manager.class.getName()).log(Level.FINER, "Trabajador no encontrado, se registra.");
        }

        em.persist(traba36);
        em.flush();

        return traba36;
    }

    /**
     *
     * @param traba36
     * @throws Traba36NotFound
     */
    @Override
    public void deleteTraba36(Traba36 traba36) throws Traba36NotFound {
        Traba36 updatableTraba36 = em.find(Traba36.class, traba36.getIdtra());

        if (updatableTraba36 == null) {
            throw new Traba36NotFound();
        }

        em.merge(traba36);
        em.flush();
    }

    /**
     *
     * @param traba36
     * @throws Traba36NotFound
     */
    @Override
    public void removeTraba36(Traba36 traba36) throws Traba36NotFound {
        Traba36 updatableTraba36 = em.find(Traba36.class, traba36.getIdtra());

        if (updatableTraba36 == null) {
            throw new Traba36NotFound();
        }
        if (!em.contains(traba36)) {
            traba36 = em.merge(traba36);
        }

        em.remove(traba36);
        em.flush();
    }

    /**
     *
     * @param traba36
     * @return
     * @throws Traba36NotFound
     */
    @Override
    public Traba36 updateTraba36(Traba36 traba36) throws Traba36NotFound {
        Traba36 updatableTraba36 = em.find(Traba36.class, traba36.getIdtra());
        Traba36 reultTraba36;
        if (updatableTraba36 == null) {
            throw new Traba36NotFound();
        }
        reultTraba36 = em.merge(traba36);
        em.flush();
        return reultTraba36;
    }

}
