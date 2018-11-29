package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Niacc33;
import com.hrm.sirhapp.model.Usuar24;
import com.hrm.sirhapp.service.exception.Niacc33AlreadyExists;
import com.hrm.sirhapp.service.exception.Niacc33Ambiguous;
import com.hrm.sirhapp.service.exception.Niacc33NotFound;
import com.hrm.sirhapp.util.FacesUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
public class Niacc33Manager implements Niacc33ManagerLocal {

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @param idnia
     * @return
     * @throws Niacc33NotFound
     */
    @Override
    public Niacc33 getNiacc33(Integer idnia) throws Niacc33NotFound {
        Query query = em.createQuery("SELECT niacc33 FROM Niacc33 niacc33 WHERE "
                + "niacc33.idnia = :idnia");

        query.setParameter("idnia", idnia);

        Niacc33 niacc33Info;

        try {
            niacc33Info = (Niacc33) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Niacc33NotFound();
        }

        Niacc33 niacc33 = niacc33Info;

        return niacc33;
    }

    /**
     *
     * @param cvnia
     * @return
     * @throws Niacc33NotFound
     */
    @Override
    public Niacc33 getNiacc33(String cvnia) throws Niacc33NotFound {
        Query query = em.createQuery("SELECT * FROM  Niacc33 niacc33 WHERE "
                + "niacc33.cvnia  = :cvnia and niacc33.stnia = 'SI' ");

        query.setParameter("cvnia", cvnia);

        Collection<Niacc33> niacc33Info;

        try {
            niacc33Info = (Collection<Niacc33>) query.getSingleResult();
            if (niacc33Info.size() > 1) {
                throw new Niacc33Ambiguous();
            }
        } catch (NoResultException exception) {
            throw new Niacc33NotFound(exception.getMessage());
        } catch (Niacc33Ambiguous ex) {
            Logger.getLogger(Niacc33Manager.class.getName()).log(Level.SEVERE, null, ex);
        }

        Usuar24 usuar24 = new Usuar24();
        usuar24.setCvusu(cvnia);

        Niacc33 niacc33 = (Niacc33) query.getSingleResult();

        return niacc33;
    }

    /**
     *
     * @param cpnia
     * @return
     */
    @Override
    public String getNiacc33Rol(String cpnia) {
        Query query = em.createQuery("SELECT niacc33.npnia FROM  Niacc33 niacc33 WHERE "
                + "niacc33.cpnia  = :cpnia and niacc33.stnia = 'SI' ", String.class);

        query.setParameter("cpnia", cpnia);

        query.setFirstResult(0);
        query.setMaxResults(1);

        return (String) query.getSingleResult();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Niacc33> getListNiacc33() {
        Query query = em.createQuery("select niacc33 from Niacc33 niacc33 "
                + "where niacc33.stnia = 'SI'", Niacc33.class);

        List<Niacc33> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Niacc33>();
        }

        return result;
    }

    /**
     *
     * @param cvnia
     * @return
     */
    @Override
    public List<String> getListNiacc33(String cvnia) {
        Query query = em.createQuery("select niacc33.nania from Niacc33 niacc33 "
                + "where niacc33.stnia = 'SI' and niacc33.cvnia.cvusu  = :cvnia");

        query.setParameter("cvnia", cvnia);

        List<String> result = (List<String>) query.getResultList();

        if (result == null) {
            return new ArrayList<String>();
        }

        return result;
    }

    /**
     *
     * @param ronia
     * @return
     */
    @Override
    public List<Niacc33> getListNiacc33Nav(String ronia) {
        //NANIA Niveles de acceso es un string de permisos 
        //que se guarda en el Campo CVNIA para obtener los niveles de acceso por ROL
        Query query = em.createQuery("select niacc33 from Niacc33 niacc33 "
                + "where niacc33.stnia = 'SI' and niacc33.ronia like :ronia and niacc33.nania='-1'", Niacc33.class);

        query.setParameter("ronia", "%" + ronia + "%");

        List<Niacc33> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Niacc33>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Niacc33> getListNiacc33Nania() {
        Query query = em.createQuery("select niacc33 from Niacc33 niacc33 "
                + "where niacc33.nania = '-2' and niacc33.stnia ='SI'  order by niacc33.cpnia", Niacc33.class);

        List<Niacc33> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Niacc33>();
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Niacc33> retrieveNiacc33() {
        Query query = em.createQuery("select niacc33 from Niacc33 niacc33", Niacc33.class);

        List<Niacc33> result = query.getResultList();

        if (result == null) {
            return new ArrayList<Niacc33>();
        }

        return result;
    }

    /**
     *
     * @param niacc33
     * @return
     * @throws Niacc33AlreadyExists
     */
    @Override
    public Niacc33 registerNiacc33(Niacc33 niacc33) throws Niacc33AlreadyExists {
        Query query = em.createQuery("select niacc33 from Niacc33 niacc33 where "
                + "niacc33.cvnia = :cvnia  and niacc33.stnia='SI'");

        query.setParameter("cvnia", niacc33.getCvnia());

        try {
            query.getSingleResult();
            throw new Niacc33AlreadyExists();
        } catch (NoResultException exception) {
            Logger.getLogger(Niacc33Manager.class.getName()).log(Level.FINER, "Usuario no encontrado");
        }

        em.persist(niacc33);
        em.flush();

        return niacc33;
    }

    /**
     *
     * @param niacc33
     * @return
     * @throws Niacc33AlreadyExists
     */
    @Override
    public Niacc33 registerNiacc33Nav(Niacc33 niacc33) throws Niacc33AlreadyExists {
        Query query = em.createQuery("select niacc33 from Niacc33 niacc33 where "
                + "niacc33.cvnia = :cvnia");

        query.setParameter("cvnia", niacc33.getCvnia());

        niacc33.setNania("-1");

        try {
            query.getSingleResult();
            throw new Niacc33AlreadyExists();
        } catch (NoResultException exception) {
            Logger.getLogger(Niacc33Manager.class.getName()).log(Level.FINER, "Usuario no encontrado");
        }

        em.persist(niacc33);
        em.flush();

        return niacc33;
    }

    /**
     *
     * @param niacc33
     * @throws Niacc33NotFound
     */
    @Override
    public void removeNiacc33(Niacc33 niacc33) throws Niacc33NotFound {
        Niacc33 updatableNiacc33 = em.find(Niacc33.class, niacc33.getIdnia());

        if (updatableNiacc33 == null) {
            throw new Niacc33NotFound();
        }
        if (!em.contains(niacc33)) {
            niacc33 = em.merge(niacc33);
        }
        em.remove(niacc33);
        em.flush();
    }

    /**
     *
     * @param niacc33
     * @throws Niacc33NotFound
     */
    @Override
    public void deleteNiacc33(Niacc33 niacc33) throws Niacc33NotFound {
        Niacc33 updatableNiacc33 = em.find(Niacc33.class, niacc33.getIdnia());

        if (updatableNiacc33 == null) {
            throw new Niacc33NotFound();
        }

        em.merge(niacc33);
        em.flush();
    }

    /**
     *
     * @param niacc33
     * @throws Niacc33NotFound
     */
    @Override
    public void updateNiacc33(Niacc33 niacc33) throws Niacc33NotFound {
        Niacc33 updatableNiacc33 = em.find(Niacc33.class, niacc33.getIdnia());

        if (updatableNiacc33 == null) {
            throw new Niacc33NotFound();
        }

        em.merge(niacc33);
        em.flush();
    }

    /**
     *
     * @param listNiacc33
     * @throws Niacc33NotFound
     */
    @Override
    public void deleteNiacc33Usuar24(List<Niacc33> listNiacc33) throws Niacc33NotFound {

        if (!listNiacc33.isEmpty()) {
            for (Niacc33 niacc33 : listNiacc33) {

                niacc33.setStnia(Constants.RECORD_DELETED);
                niacc33.setFenia(new Date());
                niacc33.setUsnia(FacesUtil.getUserName());

                em.find(Niacc33.class, niacc33.getIdnia());

                if (niacc33 == null) {
                    throw new Niacc33NotFound();
                }

                em.merge(niacc33);
                em.flush();
            }
        }

    }

}
