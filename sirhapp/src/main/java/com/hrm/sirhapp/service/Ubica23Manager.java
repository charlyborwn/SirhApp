package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Ubica23;
import com.hrm.sirhapp.service.exception.Ubica23AlreadyExists;
import com.hrm.sirhapp.service.exception.Ubica23NotFound;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Stateless
public class Ubica23Manager implements Ubica23ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idubi
     * @return
     * @throws Ubica23NotFound
     */
    @Override
    public Ubica23 getUbica23(Integer idubi) throws Ubica23NotFound {
        Query query = em.createQuery("SELECT ubica23 FROM Ubica23 ubica23 WHERE "
                + "ubica23.idubi = :idubi");

        query.setParameter("idubi", idubi);

        Ubica23 ubica23Info;

        try {
            ubica23Info = (Ubica23) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Ubica23NotFound();
        }

        Ubica23 ubica23 = ubica23Info;

        return ubica23;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Ubica23> getListUbica23() {
        Query query = em.createQuery("select ubica23 from Ubica23 ubica23 "
                + "where ubica23.stubi = 'SI'", String.class);

        List<Ubica23> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Ubica23>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<String> getListUbica23Paises() {
        TypedQuery<String> query = em.createQuery("select DISTINCT(ubica23.paubi) from Ubica23 ubica23 "
                + "where ubica23.stubi = 'SI' ", String.class);

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
    public List<Ubica23> getListUbica23Estados() {
        Query query = em.createQuery("select DISTINCT ubica23.esubi , ubica23.paubi    from Ubica23 ubica23  "
                + "where ubica23.stubi = 'SI' order by   ubica23.paubi ,  ubica23.esubi");

        List<Object[]> results = query.getResultList();

        List<Ubica23> listUbica23 = new ArrayList<Ubica23>();
        Ubica23 ubica23;

        for (int i = 0; i < results.size(); i++) {
            Object[] arr = results.get(i);

            ubica23 = new Ubica23();
            ubica23.setIdubi(i);

            if (arr[0] != null) {
                ubica23.setEsubi(arr[0].toString());
            } else {
                ubica23.setEsubi("");
            }
            if (arr[1] != null) {
                ubica23.setPaubi(arr[1].toString());
            } else {
                ubica23.setPaubi("");
            }

            listUbica23.add(ubica23);
        }
        
        if (listUbica23 == null) {
            return new ArrayList<Ubica23>();
        }
        
        return listUbica23;
    }

    /**
     *
     * @param pais
     * @return
     */
    @Override
    public List<String> getListUbica23Estados(String pais
    ) {
        TypedQuery<String> query = em.createQuery("select DISTINCT(ubica23.esubi) from Ubica23 ubica23 "
                + "where ubica23.stubi = 'SI' and ubica23.paubi = '" + pais + "'", String.class);

        List<String> result = query.getResultList();

        if (result == null) {
            return new ArrayList<String>();
        }

        return result;
    }

    /**
     *
     * @param pais
     * @param estado
     * @return
     */
    @Override
    public List<String> getListUbica23Municipios(String pais, String estado
    ) {
        TypedQuery<String> query = em.createQuery("select DISTINCT(ubica23.mdubi) from Ubica23 ubica23 "
                + "where ubica23.stubi = 'SI'  and ubica23.paubi = '" + pais + "' and ubica23.esubi = '" + estado + "'", String.class);

        List<String> result = query.getResultList();

        if (result == null) {
            return new ArrayList<String>();
        }

        return result;
    }

    /**
     *
     * @param pais
     * @param estado
     * @param municipio
     * @return
     */
    @Override
    public List<String> getListUbica23Ciudades(String pais, String estado,
            String municipio
    ) {
        TypedQuery<String> query = em.createQuery("select DISTINCT(ubica23.loubi) from Ubica23 ubica23 "
                + "where ubica23.stubi = 'SI' and ubica23.paubi = '" + pais + "' and ubica23.esubi = '" + estado + "' and ubica23.mdubi = '" + municipio + "'", String.class);

        List<String> result = query.getResultList();

        if (result == null) {
            return new ArrayList<String>();
        }

        return result;
    }

    /**
     *
     * @param pais
     * @param estado
     * @param municipio
     * @param ciudad
     * @return
     */
    @Override
    public List<String> getListUbica23Colonias(String pais, String estado,
            String municipio, String ciudad
    ) {
        TypedQuery<String> query = em.createQuery("select DISTINCT(ubica23.coubi) from Ubica23 ubica23 "
                + "where ubica23.stubi = 'SI' and ubica23.paubi = '" + pais + "' and ubica23.esubi = '" + estado + "' and ubica23.mdubi = '" + municipio + "' and ubica23.loubi = '" + ciudad + "'", String.class);

        List<String> result = query.getResultList();

        if (result == null) {
            return new ArrayList<String>();
        }

        return result;
    }

    /**
     *
     * @param pais
     * @param estado
     * @param municipio
     * @param ciudad
     * @param colonia
     * @return
     */
    @Override
    public String getListUbica23Cp(String pais, String estado,
            String municipio, String ciudad,
            String colonia
    ) {
        Query query = em.createQuery("select ubica23.cpubi from Ubica23 ubica23 "
                + "where ubica23.stubi = 'SI' and ubica23.paubi = '" + pais + "' and ubica23.esubi = '" + estado + "' and ubica23.mdubi = '" + municipio + "' and ubica23.loubi = '" + ciudad + "' and ubica23.coubi = '" + colonia + "'", String.class);

        String result = (String) query.getSingleResult();

        return result;
    }

    /**
     *
     * @param cp
     * @return
     */
    public List<Ubica23> getListUbica23Cp(String cp) {
        Query query = em.createQuery("select ubica23 from Ubica23 ubica23 "
                + "where ubica23.cpubi = '" + cp + "'", Ubica23.class);

        List<Ubica23> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Ubica23>();
        }

        return result;

    }

    /**
     *
     * @return
     */
    @Override
    public List<Ubica23> retrieveUbica23() {
        Query query = em.createQuery("select ubica23 from Ubica23 ubica23", Ubica23.class);

        List<Ubica23> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Ubica23>();
        }

        return result;
    }

    /**
     *
     * @param ubica23
     * @return
     * @throws Ubica23AlreadyExists
     */
    @Override
    public Ubica23 registerUbica23(Ubica23 ubica23) throws Ubica23AlreadyExists {

        em.persist(ubica23);
        em.flush();

        return ubica23;
    }

    /**
     *
     * @param ubica23
     * @throws Ubica23NotFound
     */
    @Override
    public void deleteUbica23(Ubica23 ubica23) throws Ubica23NotFound {
        Ubica23 updatableUbica23 = em.find(Ubica23.class, ubica23.getIdubi());

        if (updatableUbica23 == null) {
            throw new Ubica23NotFound();
        }

        em.merge(ubica23);
        em.flush();
    }

    /**
     *
     * @param ubica23
     * @throws Ubica23NotFound
     */
    @Override
    public void removeUbica23(Ubica23 ubica23) throws Ubica23NotFound {
        Ubica23 updatableUbica23 = em.find(Ubica23.class, ubica23.getIdubi());

        if (updatableUbica23 == null) {
            throw new Ubica23NotFound();
        }
        if (!em.contains(ubica23)) {
            ubica23 = em.merge(ubica23);
        }
        em.remove(ubica23);
        em.flush();
    }

    /**
     *
     * @param ubica23
     * @throws Ubica23NotFound
     */
    @Override
    public void updateUbica23(Ubica23 ubica23) throws Ubica23NotFound {
        Ubica23 updatableUbica23 = em.find(Ubica23.class, ubica23.getIdubi());

        if (updatableUbica23 == null) {
            throw new Ubica23NotFound();
        }

        em.merge(ubica23);
        em.flush();
    }
}
