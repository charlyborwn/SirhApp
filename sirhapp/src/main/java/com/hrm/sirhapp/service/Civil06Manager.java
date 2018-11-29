package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Civil06;
import com.hrm.sirhapp.service.exception.Civil06AlreadyExists;
import com.hrm.sirhapp.service.exception.Civil06NotFound;
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
public class Civil06Manager implements Civil06ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idciv
     * @return
     * @throws Civil06NotFound
     */
    @Override
    public Civil06 getCivil06(Integer idciv) throws Civil06NotFound {
        Query query = em.createQuery("SELECT civil06 FROM Civil06 civil06 WHERE "
                + "civil06.idciv = :idciv");

        query.setParameter("idciv", idciv);

        Civil06 civil06Info;

        try {
            civil06Info = (Civil06) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Civil06NotFound();
        }

        Civil06 civil06 = civil06Info;

        return civil06;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Civil06> getListCivil06() {
        Query query = em.createQuery("select civil06 from Civil06 civil06 "
                + "where civil06.stciv = 'SI'", Civil06.class);

        List<Civil06> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Civil06>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Civil06> retrieveCivil06() {
        Query query = em.createQuery("select civil06 from Civil06 civil06", Civil06.class);

        List<Civil06> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Civil06>();
        }

        return result;
    }

    /**
     *
     * @param civil06
     * @return
     * @throws Civil06AlreadyExists
     */
    @Override
    public Civil06 registerCivil06(Civil06 civil06) throws Civil06AlreadyExists {

        em.persist(civil06);
        em.flush();

        return civil06;
    }

    /**
     *
     * @param civil06
     * @throws Civil06NotFound
     */
    @Override
    public void removeCivil06(Civil06 civil06) throws Civil06NotFound {
        Civil06 updatableCivil06 = em.find(Civil06.class, civil06.getIdciv());

        if (updatableCivil06 == null) {
            throw new Civil06NotFound();
        }

        if (!em.contains(civil06)) {
            civil06 = em.merge(civil06);
        }

        em.remove(civil06);
        em.flush();
    }

    /**
     *
     * @param civil06
     * @throws Civil06NotFound
     */
    @Override
    public void deleteCivil06(Civil06 civil06) throws Civil06NotFound {
        Civil06 updatableCivil06 = em.find(Civil06.class, civil06.getIdciv());

        if (updatableCivil06 == null) {
            throw new Civil06NotFound();
        }

        em.merge(civil06);
        em.flush();
    }

    /**
     *
     * @param civil06
     * @throws Civil06NotFound
     */
    @Override
    public void updateCivil06(Civil06 civil06) throws Civil06NotFound {
        Civil06 updatableCivil06 = em.find(Civil06.class, civil06.getIdciv());

        if (updatableCivil06 == null) {
            throw new Civil06NotFound();
        }

        em.merge(civil06);
        em.flush();
    }

}
