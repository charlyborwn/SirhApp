package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Traba36a;
import com.hrm.sirhapp.service.exception.Traba36aAlreadyExists;
import com.hrm.sirhapp.service.exception.Traba36aNotFound;
import com.hrm.sirhapp.util.FacesUtil;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
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
public class Traba36aManager implements Traba36aManagerLocal {

    private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(Traba36aManager.class);

    @PersistenceContext(unitName = "SirhAppPU")
    EntityManager em;

    /**
     *
     * @return
     */
    @Override
    public List<Traba36a> retrieveTraba36a() {
        Query query = em.createQuery("select traba36a from Traba36a traba36a where  traba36a.sttraA='SI' and ", Traba36a.class);

        List<Traba36a> result = query.getResultList();
//result.sort(Comparator.comparing(Traba36a::getFxtraA));

        if (result == null) {
            return new ArrayList<Traba36a>();
        }

        return result;
    }

    /**
     *
     * @param traba36a
     * @return
     */
    @Override
    public List<Traba36a> getListTraba36aWiz(Traba36a traba36a) {

        String str = "select traba36a from Traba36a traba36a   "
                + "where 1=1  and traba36a.sttraA='SI' ";
        Query query = em.createQuery(str, Traba36a.class);

        if (traba36a.getAptraA() != null && traba36a.getAmtraA() == null && traba36a.getNotraA() == null) {
            str = str + " and traba36a.aptraA like :aptraA ";
            //query = em.createQuery(str, Traba36a.class);
            //query.setParameter("aptra", "%" + traba36a.getAptraA() + "%");
            System.out.println("0busqueda por paterno");
        }

        if (traba36a.getAptraA() != null && traba36a.getAmtraA() != null && traba36a.getNotraA() == null) {
            str = str + " and traba36a.aptraA like :aptraA and traba36a.amtraA like :amtraA ";
            //query = em.createQuery(str, Traba36a.class);
            //query.setParameter("aptra", "%" + traba36a.getAptraA() + "%");
            //query.setParameter("amtra", "%" + traba36a.getAmtraA() + "%");
            //System.out.println(traba36a.getAptraA());
            //System.out.println(traba36a.getAmtraA());
            System.out.println("1busqueda por paterno y materno ");
        }

        if (traba36a.getAptraA() != null && traba36a.getAmtraA() == null && traba36a.getNotraA() != null) {
            str = str + " and traba36a.aptraA like :aptraA and traba36a.notraA like :notraA ";
            //query = em.createQuery(str, Traba36a.class);
            //query.setParameter("aptra", "%" + traba36a.getAptraA() + "%");
            //query.setParameter("notra", "%" + traba36a.getNotraA() + "%");
            //System.out.println(traba36a.getAptraA());
            //System.out.println(traba36a.getNotraA());
            System.out.println("2busqueda por paterno y nombres ");
        }

        if (traba36a.getAptraA() != null && traba36a.getAmtraA() != null && traba36a.getNotraA() != null) {
            str = str + " and traba36a.aptraA like :aptraA and traba36a.amtraA like :amtraA and traba36a.notraA like :notraA";
            //query = em.createQuery(str, Traba36a.class);
            //query.setParameter("aptra", "%" + traba36a.getAptraA() + "%");
            //query.setParameter("amtra", "%" + traba36a.getAmtraA() + "%");
            //query.setParameter("notra", "%" + traba36a.getNotraA() + "%");
            //System.out.println(traba36a.getAptraA());
            //System.out.println(traba36a.getAmtraA());
            //System.out.println(traba36a.getNotraA());
            System.out.println("3busqueda por paterno, materno y nombres");
        }

        if (traba36a.getAptraA() == null && traba36a.getAmtraA() != null && traba36a.getNotraA() == null) {
            str = str + " and traba36a.amtraA like :amtraA";
            //query = em.createQuery(str, Traba36a.class);
            //query.setParameter("amtra", "%" + traba36a.getAmtraA() + "%");
            //System.out.println(traba36a.getAmtraA());
            System.out.println("4busqueda por materno");
        }

        if (traba36a.getAptraA() == null && traba36a.getAmtraA() != null && traba36a.getNotraA() != null) {
            str = str + " and traba36a.amtraA like :amtraA and traba36a.notraA like :notraA";
            //query = em.createQuery(str, Traba36a.class);
            //query.setParameter("amtra", "%" + traba36a.getAmtraA() + "%");
            //query.setParameter("notra", "%" + traba36a.getNotraA() + "%");
            //System.out.println(traba36a.getAmtraA());
            //System.out.println(traba36a.getNotraA());
            System.out.println("5busqueda por materno y nombres");
        }

        if (traba36a.getAptraA() == null && traba36a.getAmtraA() == null && traba36a.getNotraA() != null) {
            str = str + "  and traba36a.notraA like :notraA";
            //query = em.createQuery(str, Traba36a.class);
            //query.setParameter("notra", "%" + traba36a.getNotraA() + "%");
            //System.out.println(traba36a.getNotraA());
            System.out.println("6busqueda por nombres");
        }

        if (traba36a.getRftraA() != null && traba36a.getRftraA().length() > 0) {
            str = str + " and traba36a.rftraA like :rftraA";
            System.out.println("7busqueda por rfc ");
        }

        if (traba36a.getCutraA() != null && traba36a.getCutraA().length() > 0) {
            str = str + " and traba36a.cutraA like :cutraA";
            System.out.println("8busqueda por curp");
        }

        if (traba36a.getRitraA() != null && traba36a.getRitraA().length() > 0) {
            str = str + " and traba36a.ritraA = :ritraA";
            System.out.println("9busqueda por Imss");
        }

        query = em.createQuery(str + " ", Traba36a.class);

        if (traba36a.getAmtraA() != null && traba36a.getAmtraA().length() > 0) {
            query.setParameter("amtraA", "%" + traba36a.getAmtraA() + "%");
        }
        if (traba36a.getAptraA() != null && traba36a.getAptraA().length() > 0) {
            query.setParameter("aptraA", "%" + traba36a.getAptraA() + "%");
        }
        if (traba36a.getNotraA() != null && traba36a.getNotraA().length() > 0) {
            query.setParameter("notraA", "%" + traba36a.getNotraA() + "%");
        }
        if (traba36a.getRftraA() != null && traba36a.getRftraA().length() > 0) {
            System.out.println("7rftraA" + traba36a.getRftraA());
            query.setParameter("rftraA", "%" + traba36a.getRftraA() + "%");
        }
        if (traba36a.getCutraA() != null && traba36a.getCutraA().length() > 0) {
            query.setParameter("cutraA", "%" + traba36a.getCutraA() + "%");
        }
        if (traba36a.getRitraA() != null && traba36a.getRitraA().length() > 0) {
            query.setParameter("ritraA", traba36a.getRitraA());
        }

        List<Traba36a> result = query.getResultList();
//result.sort(Comparator.comparing(Traba36a::getFxtraA));

        if (result == null) {
            return new ArrayList<Traba36a>();
        }

        return result;
    }

    /**
     *
     * @param enterprise
     * @param startDate
     * @param endDate
     * @param reporte
     * @param gen
     * @return
     */
    @Override
    public List<Traba36a> getListTraba36aGen(String enterprise, Date startDate, Date endDate, String reporte, boolean gen) {

        String str = "";
        String strGen = "";

        if (gen) {
            strGen = " is null";

        } else {
            strGen = " is not null";

        }
        switch (reporte) {
            case "0":
                str = " and (traba36a.oetraA" + strGen + ")";
                break;
            case "1":
                str = " and (traba36a.odtraA" + strGen + ")";
                break;
            case "2":
                str = " and (traba36a.octraA" + strGen + ")";
                break;
            default:
                break;

        }

        Locale locale = new Locale("es", "es_MX");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS", locale);

        Calendar calS = Calendar.getInstance();
        calS.setTime(startDate);
        calS.set(Calendar.MILLISECOND, 0);
        calS.set(Calendar.SECOND, 0);
        calS.set(Calendar.MINUTE, 0);
        calS.set(Calendar.HOUR, 0);

        TimeZone tzS = calS.getTimeZone();
        ZoneId zidS = tzS == null ? ZoneId.systemDefault() : ZoneId.of("America/Mexico_City");

        Calendar calE = Calendar.getInstance();
        calE.setTime(endDate);
        calE.set(Calendar.MILLISECOND, 999);
        calE.set(Calendar.SECOND, 59);
        calE.set(Calendar.MINUTE, 59);
        calE.set(Calendar.HOUR, 23);

        TimeZone tzE = calS.getTimeZone();
        ZoneId zidE = tzE == null ? ZoneId.systemDefault() : ZoneId.of("America/Mexico_City");

        LocalDateTime ldtS = LocalDateTime.parse(LocalDateTime.ofInstant(calS.toInstant(), zidS).format(formatter), formatter);
        LocalDateTime ldtE = LocalDateTime.parse(LocalDateTime.ofInstant(calE.toInstant(), zidE).format(formatter), formatter);

        Query query = em.createQuery("select traba36a from Traba36a traba36a where  traba36a.sttraA='SI' and "
                + "traba36a.eitraA = :eitraA and traba36a.cctraA >= :startDate and traba36a.cctraA <=:endDate " + str + " ", Traba36a.class);

        query.setParameter("eitraA", enterprise);

        Timestamp startDateParam = Timestamp.valueOf(ldtS);
        Timestamp endDateParam = Timestamp.valueOf(ldtE);

        System.out.println(startDateParam);
        System.out.println(endDateParam);

        query.setParameter("startDate", startDateParam);
        query.setParameter("endDate", endDateParam);

        List<Traba36a> result = query.getResultList();
//result.sort(Comparator.comparing(Traba36a::getFxtraA));

        System.out.println(query);

        System.out.println(result.size());

        if (result
                == null) {
            return new ArrayList<Traba36a>();
        }

        return result;
    }

    /**
     *
     * @param enterprise
     * @param start
     * @param end
     * @param reporte
     * @param gen
     * @return
     */
    @Override
    public List<Traba36a> getListTraba36aGen(String enterprise, Integer start, Integer end, String reporte, boolean gen) {

        String str = "";
        String strGen = "";

        if (gen) {
            strGen = " is null";

        } else {
            strGen = " is not null";

        }
        switch (reporte) {
            case "0":
                str = " and (traba36a.oetraA" + strGen + ")";
                break;
            case "1":
                str = " and (traba36a.odtraA" + strGen + ")";
                break;
            case "2":
                str = " and (traba36a.octraA" + strGen + ")";
                break;
            default:
                break;

        }

        Query query = em.createQuery("select traba36a from Traba36a traba36a where  traba36a.sttraA='SI' and "
                + "traba36a.eitraA = :eitraA and traba36a.fxtraA >= :start and traba36a.fxtraA <= :end " + str + " ", Traba36a.class);

        query.setParameter("eitraA", enterprise);
        query.setParameter("start", start);
        query.setParameter("end", end);

        List<Traba36a> result = query.getResultList();
//result.sort(Comparator.comparing(Traba36a::getFxtraA));

        System.out.println(result.size());

        if (result == null) {
            return new ArrayList<Traba36a>();
        }

        return result;
    }

    /**
     *
     * @param enterprise
     * @param period
     * @param reporte
     * @param gen
     * @return
     */
    @Override
    public List<Traba36a> getListTraba36aGen(String enterprise, String period, String reporte, boolean gen) {

        String str = "";
        String strGen = "";

        if (gen) {
            strGen = " is null";

        } else {
            strGen = " is not null";

        }
        switch (reporte) {
            case "0":
                str = " and (traba36a.oetraA" + strGen + ")";
                break;
            case "1":
                str = " and (traba36a.odtraA" + strGen + ")";
                break;
            case "2":
                str = " and (traba36a.octraA" + strGen + ")";
                break;
            default:
                break;

        }

        Query query = em.createQuery("select traba36a from Traba36a traba36a where  traba36a.sttraA='SI' and "
                + "traba36a.eitraA = :eitraA and traba36a.cctraA >= :startDate and traba36a.cctraA <=:endDate " + str + " ", Traba36a.class);

        query.setParameter("eitraA", enterprise);

        Locale locale = new Locale("es", "es_MX");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS", locale);

        Calendar now = Calendar.getInstance();

        TimeZone tz = now.getTimeZone();
        ZoneId zid = tz == null ? ZoneId.systemDefault() : ZoneId.of("America/Mexico_City");

        Calendar start = Calendar.getInstance();
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);

        Calendar end = Calendar.getInstance();
        end.set(Calendar.HOUR, 23);
        end.set(Calendar.MINUTE, 59);
        end.set(Calendar.SECOND, 59);
        end.set(Calendar.MILLISECOND, 999);

        LocalDateTime startDate = LocalDateTime.parse(LocalDateTime.ofInstant(start.toInstant(), zid).format(formatter), formatter);

        LocalDateTime endDate = LocalDateTime.parse(LocalDateTime.ofInstant(end.toInstant(), zid).format(formatter), formatter);
        /*List Periods
        period this year
        period this month
        period this week
        period this day*/
        if (period != null) {
            switch (period) {
                case "year":
                    startDate
                            = startDate.withDayOfYear(1);
                    break;
                case "month":
                    startDate
                            = startDate.withDayOfMonth(1);
                    break;
                case "week":
                    startDate
                            = startDate.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
                    break;
            }
        }

        Timestamp startDateParam = Timestamp.valueOf(startDate);
        Timestamp endDateParam = Timestamp.valueOf(endDate);

        System.out.println(startDateParam);
        System.out.println(endDateParam);

        query.setParameter("startDate", startDateParam);
        query.setParameter("endDate", endDateParam);

        List<Traba36a> result = query.getResultList();
//result.sort(Comparator.comparing(Traba36a::getFxtraA));

        System.out.println(query);
        System.out.println(result.size());

        if (result == null) {
            return new ArrayList<Traba36a>();
        }

        return result;
    }

    /**
     *
     * @param folios
     * @param reporte
     * @param gen
     * @return
     */
    @Override
    public List<Traba36a> getListTraba36aGen(int[] folios, String reporte, boolean gen) {

        String str = "";
        String strGen = "";

        if (gen) {
            strGen = " is null";

        } else {
            strGen = " is not null";

        }
        switch (reporte) {
            case "0":
                str = " and (traba36a.oetraA" + strGen + ")";
                break;
            case "1":
                str = " and (traba36a.odtraA" + strGen + ")";
                break;
            case "2":
                str = " and (traba36a.octraA" + strGen + ")";
                break;
            default:
                break;

        }
        Query query = em.createQuery("select traba36a from Traba36a traba36a where  traba36a.sttraA='SI' and  "
                + " (traba36a.fxtraA IN :folios) " + str + " ", Traba36a.class
        );

        List<Integer> intList = new ArrayList<Integer>();
        for (int i : folios) {
            intList.add(i);
        }

        query.setParameter("folios", intList);
        List<Traba36a> result = query.getResultList();
//result.sort(Comparator.comparing(Traba36a::getFxtraA));

        if (result == null) {
            return new ArrayList<Traba36a>();
        }

        return result;
    }

    /**
     *
     * @param rftraA
     * @param reporte
     * @param gen
     * @return
     */
    @Override
    public List<Traba36a> getListTraba36aGen(String rftraA, String reporte, boolean gen) {

        String str = "";
        String strGen = "";

        if (gen) {
            strGen = " is null";

        } else {
            strGen = " is not null";

        }
        switch (reporte) {
            case "0":
                str = " and (traba36a.oetraA" + strGen + ")";
                break;
            case "1":
                str = " and (traba36a.odtraA" + strGen + ")";
                break;
            case "2":
                str = " and (traba36a.octraA" + strGen + ")";
                break;
            default:
                break;

        }

        Query query = em.createQuery("select traba36a from Traba36a traba36a "
                + "where traba36a.sttraA='SI' and traba36a.rftraA like :rftraA " + str + " ", Traba36a.class
        );

        query.setParameter("rftraA", "%" + rftraA + "%");

        List<Traba36a> result = query.getResultList();
//result.sort(Comparator.comparing(Traba36a::getFxtraA));

        if (result == null) {
            return new ArrayList<Traba36a>();
        }

        return result;
    }

    /**
     *
     * @param traba36a
     * @param reporte
     * @param gen
     * @return
     */
    @Override
    public List<Traba36a> getListTraba36aGen(Traba36a traba36a, String reporte, boolean gen) {

        String str = "";
        String strGen = "";

        if (gen) {
            strGen = " is null";

        } else {
            strGen = " is not null";

        }
        switch (reporte) {
            case "0":
                str = " and (traba36a.oetraA" + strGen + ")";
                break;
            case "1":
                str = " and (traba36a.odtraA" + strGen + ")";
                break;
            case "2":
                str = " and (traba36a.octraA" + strGen + ")";
                break;
            default:
                break;

        }

        str = "select traba36a from Traba36a traba36a "
                + "where traba36a.sttraA='SI'  " + str + " ";
        Query query = em.createQuery(str, Traba36a.class
        );
        System.out.println("7todos");

        if (traba36a.getAptraA() != null && traba36a.getAmtraA() == null && traba36a.getNotraA() == null) {
            str = str + " and traba36a.aptraA like :aptraA ";
            query
                    = em.createQuery(str, Traba36a.class
                    );
            query.setParameter("aptraA", "%" + traba36a.getAptraA() + "%");
            System.out.println("0busqueda por paterno");
        }

        if (traba36a.getAptraA() != null && traba36a.getAmtraA() != null && traba36a.getNotraA() == null) {
            str = str + " and traba36a.aptraA like :aptraA and traba36a.amtraA like :amtraA ";
            query
                    = em.createQuery(str, Traba36a.class
                    );
            query.setParameter("aptraA", "%" + traba36a.getAptraA() + "%");
            query.setParameter("amtraA", "%" + traba36a.getAmtraA() + "%");
            //System.out.println(traba36a.getAptraA());
            //System.out.println(traba36a.getAmtraA());
            System.out.println("1busqueda por paterno y materno ");
        }

        if (traba36a.getAptraA() != null && traba36a.getAmtraA() == null && traba36a.getNotraA() != null) {
            str = str + " and traba36a.aptraA like :aptraA and traba36a.notraA like :notraA ";
            query
                    = em.createQuery(str, Traba36a.class
                    );
            query.setParameter("aptraA", "%" + traba36a.getAptraA() + "%");
            query.setParameter("notraA", "%" + traba36a.getNotraA() + "%");
            //System.out.println(traba36a.getAptraA());
            //System.out.println(traba36a.getNotraA());
            System.out.println("2busqueda por paterno y nombres ");
        }

        if (traba36a.getAptraA() != null && traba36a.getAmtraA() != null && traba36a.getNotraA() != null) {
            str = str + " and traba36a.aptraA like :aptraA and traba36a.amtraA like :amtraA and traba36a.notraA like :notraA";
            query
                    = em.createQuery(str, Traba36a.class
                    );
            query.setParameter("aptraA", "%" + traba36a.getAptraA() + "%");
            query.setParameter("amtraA", "%" + traba36a.getAmtraA() + "%");
            query.setParameter("notraA", "%" + traba36a.getNotraA() + "%");
            //System.out.println(traba36a.getAptraA());
            //System.out.println(traba36a.getAmtraA());
            //System.out.println(traba36a.getNotraA());
            System.out.println("3busqueda por paterno, materno y nombres");
        }

        if (traba36a.getAptraA() == null && traba36a.getAmtraA() != null && traba36a.getNotraA() == null) {
            str = str + " and traba36a.amtraA like :amtraA";
            query
                    = em.createQuery(str, Traba36a.class
                    );
            query.setParameter("amtraA", "%" + traba36a.getAmtraA() + "%");
            //System.out.println(traba36a.getAmtraA());
            System.out.println("4busqueda por materno");
        }

        if (traba36a.getAptraA() == null && traba36a.getAmtraA() != null && traba36a.getNotraA() != null) {
            str = str + " and traba36a.amtraA like :amtraA and traba36a.notraA like :notraA";
            query
                    = em.createQuery(str, Traba36a.class
                    );
            query.setParameter("amtraA", "%" + traba36a.getAmtraA() + "%");
            query.setParameter("notraA", "%" + traba36a.getNotraA() + "%");
            //System.out.println(traba36a.getAmtraA());
            //System.out.println(traba36a.getNotraA());
            System.out.println("5busqueda por materno y nombres");
        }

        if (traba36a.getAptraA() == null && traba36a.getAmtraA() == null && traba36a.getNotraA() != null) {
            str = str + "  and traba36a.notraA like :notraA";
            query = em.createQuery(str + " ", Traba36a.class);
            query.setParameter("notraA", "%" + traba36a.getNotraA() + "%");
            //System.out.println(traba36a.getNotraA());
            System.out.println("6busqueda por nombres");
        }

        List<Traba36a> result = query.getResultList();
//result.sort(Comparator.comparing(Traba36a::getFxtraA));

        if (result == null) {
            return new ArrayList<Traba36a>();
        }

        return result;
    }

    /**
     *
     * @param fxtraA
     * @param reporte
     * @param gen
     * @return
     */
    @Override
    public List<Traba36a> getListTraba36aGen(Integer fxtraA, String reporte, boolean gen) {

        String str = "";
        String strGen = "";

        if (gen) {
            strGen = " is null";

        } else {
            strGen = " is not null";

        }
        switch (reporte) {
            case "0":
                str = " and (traba36a.oetraA" + strGen + ")";
                break;
            case "1":
                str = " and (traba36a.odtraA" + strGen + ")";
                break;
            case "2":
                str = " and (traba36a.octraA" + strGen + ")";
                break;
            default:
                break;
        }

        Query query = em.createQuery("select traba36a from Traba36a traba36a "
                + "where traba36a.sttraA='SI' and traba36a.fxtraA = :fxtraA  " + str + " ", Traba36a.class);

        query.setParameter("fxtraA", fxtraA);

        List<Traba36a> result = query.getResultList();


        if (result == null) {
            return new ArrayList<Traba36a>();
        }

        return result;
    }

    /**
     *
     * @param traba36a
     * @return
     */
    @Override
    public List<Traba36a> getListTraba36a(Traba36a traba36a) {

        String str = "select traba36a from Traba36a traba36a "
                + "where traba36a.sttraA='SI' ";
        Query query = em.createQuery(str, Traba36a.class
        );
        System.out.println("7todos");

        if (traba36a.getAptraA() != null && traba36a.getAmtraA() == null && traba36a.getNotraA() == null) {
            str = str + " and traba36a.aptraA like :aptraA ";
            query
                    = em.createQuery(str, Traba36a.class
                    );
            query.setParameter("aptraA", "%" + traba36a.getAptraA() + "%");
            System.out.println("0busqueda por paterno");
        }

        if (traba36a.getAptraA() != null && traba36a.getAmtraA() != null && traba36a.getNotraA() == null) {
            str = str + " and traba36a.aptraA like :aptraA and traba36a.amtraA like :amtraA ";
            query
                    = em.createQuery(str, Traba36a.class
                    );
            query.setParameter("aptraA", "%" + traba36a.getAptraA() + "%");
            query.setParameter("amtraA", "%" + traba36a.getAmtraA() + "%");
            //System.out.println(traba36a.getAptraA());
            //System.out.println(traba36a.getAmtraA());
            System.out.println("1busqueda por paterno y materno ");
        }

        if (traba36a.getAptraA() != null && traba36a.getAmtraA() == null && traba36a.getNotraA() != null) {
            str = str + " and traba36a.aptraA like :aptraA and traba36a.notraA like :notraA ";
            query
                    = em.createQuery(str, Traba36a.class
                    );
            query.setParameter("aptraA", "%" + traba36a.getAptraA() + "%");
            query.setParameter("notraA", "%" + traba36a.getNotraA() + "%");
            //System.out.println(traba36a.getAptraA());
            //System.out.println(traba36a.getNotraA());
            System.out.println("2busqueda por paterno y nombres ");
        }

        if (traba36a.getAptraA() != null && traba36a.getAmtraA() != null && traba36a.getNotraA() != null) {
            str = str + " and traba36a.aptraA like :aptraA and traba36a.amtraA like :amtraA and traba36a.notraA like :notraA";
            query
                    = em.createQuery(str, Traba36a.class
                    );
            query.setParameter("aptraA", "%" + traba36a.getAptraA() + "%");
            query.setParameter("amtraA", "%" + traba36a.getAmtraA() + "%");
            query.setParameter("notraA", "%" + traba36a.getNotraA() + "%");
            //System.out.println(traba36a.getAptraA());
            //System.out.println(traba36a.getAmtraA());
            //System.out.println(traba36a.getNotraA());
            System.out.println("3busqueda por paterno, materno y nombres");
        }

        if (traba36a.getAptraA() == null && traba36a.getAmtraA() != null && traba36a.getNotraA() == null) {
            str = str + " and traba36a.amtraA like :amtraA";
            query
                    = em.createQuery(str, Traba36a.class
                    );
            query.setParameter("amtraA", "%" + traba36a.getAmtraA() + "%");
            //System.out.println(traba36a.getAmtraA());
            System.out.println("4busqueda por materno");
        }

        if (traba36a.getAptraA() == null && traba36a.getAmtraA() != null && traba36a.getNotraA() != null) {
            str = str + " and traba36a.amtraA like :amtraA and traba36a.notraA like :notraA";
            query
                    = em.createQuery(str, Traba36a.class
                    );
            query.setParameter("amtraA", "%" + traba36a.getAmtraA() + "%");
            query.setParameter("notraA", "%" + traba36a.getNotraA() + "%");
            //System.out.println(traba36a.getAmtraA());
            //System.out.println(traba36a.getNotraA());
            System.out.println("5busqueda por materno y nombres");
        }

        if (traba36a.getAptraA() == null && traba36a.getAmtraA() == null && traba36a.getNotraA() != null) {
            str = str + "  and traba36a.notraA like :notraA";
            query = em.createQuery(str + " ", Traba36a.class);
            query.setParameter("notraA", "%" + traba36a.getNotraA() + "%");
            //System.out.println(traba36a.getNotraA());
            System.out.println("6busqueda por nombres");
        }

        List<Traba36a> result = query.getResultList();
//result.sort(Comparator.comparing(Traba36a::getFxtraA));

        if (result == null) {
            return new ArrayList<Traba36a>();
        }

        return result;
    }

    /**
     *
     * @param rftraA
     * @return
     */
    @Override
    public List<Traba36a> getListTraba36a(String rftraA) {
        Query query = em.createQuery("select traba36a from Traba36a traba36a "
                + "where traba36a.sttraA='SI' and traba36a.rftraA like :rftraA " + " ", Traba36a.class
        );

        query.setParameter("rftraA", "%" + rftraA + "%");

        List<Traba36a> result = query.getResultList();
//result.sort(Comparator.comparing(Traba36a::getFxtraA));

        if (result == null) {
            return new ArrayList<Traba36a>();
        }

        return result;
    }

    /**
     *
     * @param rftraA
     * @return
     */
    @Override
    public List<Traba36a> getListTraba36aAll(String rftraA) {
        Query query = em.createQuery("select traba36a from Traba36a traba36a "
                + "where  traba36a.rftraA = :rftraA " + " ", Traba36a.class
        );

        query.setParameter("rftraA", rftraA);

        List<Traba36a> result = query.getResultList();
//result.sort(Comparator.comparing(Traba36a::getFxtraA));

        if (result == null) {
            return new ArrayList<Traba36a>();
        }

        return result;
    }

    /**
     *
     * @param rftraA
     * @return
     */
    @Override
    public Boolean alreadyExistsInnactive(String rftraA) {
        Query query = em.createQuery("SELECT traba36a FROM Traba36a traba36a "
                + "WHERE traba36a.sttraA='NO' and traba36a.rftraA like :rftraA");

        query.setParameter("rftraA", rftraA);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    /**
     *
     * @param rftraA
     * @return
     * @throws Traba36aNotFound
     */
    @Override
    public Traba36a getTraba36a(String rftraA) throws Traba36aNotFound {
        Query query = em.createQuery("SELECT traba36a FROM Traba36a traba36a "
                + "where traba36a.rftraA = :rftraA");

        query.setParameter("rftraA", rftraA);

        Traba36a traba36aInfo;

        try {
            traba36aInfo = (Traba36a) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new Traba36aNotFound();
        }

        Traba36a traba36a = traba36aInfo;

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + traba36a.getIdtraA() + " | SEL: " + traba36a.toString() + " | ACT: " + this.getClass().getName() + ".getTraba36a");

        return traba36a;
    }

    /**
     *
     * @param traba36a
     * @return
     * @throws Traba36aAlreadyExists
     */
    @Override
    public Traba36a registerTraba36a(Traba36a traba36a) throws Traba36aAlreadyExists {
        Query query = em.createQuery("select traba36a from Traba36a traba36a "
                + "where traba36a.rftraA like :rftraA" + " ");

        query.setParameter("rftraA", traba36a.getRftraA());

        try {
            query.getSingleResult();
            throw new Traba36aAlreadyExists();

        } catch (NoResultException ex) {
            Logger.getLogger(Traba36aManager.class
                    .getName()).log(Level.FINER, "Aspirante no encontrado, se registra.");
        }

        em.persist(traba36a);
        em.flush();
        em.refresh(traba36a);

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + traba36a.getIdtraA() + " | NEW: " + traba36a.toString() + " | ACT: " + this.getClass().getName() + ".registerTraba36a");
        System.out.println("Este es el folio que genero" + traba36a.getFxtraA());
        return traba36a;
    }

    /**
     *
     * @param traba36a
     * @throws Traba36aNotFound
     */
    @Override
    public void deleteTraba36a(Traba36a traba36a) throws Traba36aNotFound {
        Traba36a updatableTraba36a = em.find(Traba36a.class,
                traba36a.getIdtraA());

        if (updatableTraba36a == null) {
            throw new Traba36aNotFound();
        }

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + traba36a.getIdtraA() + " | UPD: " + updatableTraba36a.toString() + " | ACT: " + this.getClass().getName() + ".deleteTraba36a");
        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + traba36a.getIdtraA() + " | DEL: " + traba36a.toString() + " | ACT: " + this.getClass().getName() + ".deleteTraba36a");

        em.merge(traba36a);
        em.flush();

    }

    /**
     *
     * @param traba36a
     * @throws Traba36aNotFound
     */
    @Override
    public void removeTraba36a(Traba36a traba36a) throws Traba36aNotFound {
        Traba36a updatableTraba36a = em.find(Traba36a.class,
                traba36a.getIdtraA());

        if (updatableTraba36a == null) {
            throw new Traba36aNotFound();
        }

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + traba36a.getIdtraA() + " | REM: " + traba36a.toString() + " | ACT: " + this.getClass().getName() + ".removeTraba36a");

        if (!em.contains(traba36a)) {
            traba36a = em.merge(traba36a);
        }
        em.remove(traba36a);
        em.flush();

    }

    /**
     *
     * @param traba36a
     * @throws Traba36aNotFound
     */
    @Override
    public void updateTraba36a(Traba36a traba36a) throws Traba36aNotFound {
        Traba36a updatableTraba36a = em.find(Traba36a.class,
                traba36a.getIdtraA());

        if (updatableTraba36a == null) {
            throw new Traba36aNotFound();
        }

        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + traba36a.getIdtraA() + " | UPD: " + updatableTraba36a.toString() + updatableTraba36a.getSitraA() + " | ACT: " + this.getClass().getName() + ".updateTraba36a");
        LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + traba36a.getIdtraA() + " | DAT: " + traba36a.toString() + traba36a.getSitraA() + " | ACT: " + this.getClass().getName() + ".updateTraba36a");

        em.merge(traba36a);
        em.flush();

    }

}
