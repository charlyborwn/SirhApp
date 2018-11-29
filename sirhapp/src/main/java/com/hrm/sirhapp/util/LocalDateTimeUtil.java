package com.hrm.sirhapp.util;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
public class LocalDateTimeUtil {

    private void date2(String period) {
        Instant instant = Instant.now();
        LocalDateTime startDate = LocalDateTime.ofInstant(instant, ZoneId.of("America/Mexico_City"));

        LocalDateTime endDate = LocalDateTime.ofInstant(instant, ZoneId.of("America/Mexico_City"));
        /*List Periods
        period this year
        period this month
        period this week
        period this day*/
        if (period != null) {
            switch (period) {
                case "year":
                    startDate
                            = LocalDateTime.ofInstant(instant, ZoneId.of("America/Mexico_City"))
                                    .withDayOfYear(1);
                    break;
                case "month":
                    startDate
                            = LocalDateTime.ofInstant(instant, ZoneId.of("America/Mexico_City"))
                                    .withDayOfMonth(1);
                    break;
                case "week":
                    startDate
                            = LocalDateTime.ofInstant(instant, ZoneId.of("America/Mexico_City"))
                                    .with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
                    break;
            }
        }

        Timestamp startDateParam = Timestamp.valueOf(startDate);
        Timestamp endDateParam = Timestamp.valueOf(endDate);

        System.out.println(startDateParam);
        System.out.println(endDateParam);

    }

    private void date(String period) {
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
    }
}
