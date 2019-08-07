package com.chaochaogu.java8.test.date;

import java.lang.annotation.ElementType;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**
 * @author chaochao Gu
 * @date 2019/8/6
 */
public class DateAPI {

    public static void main(String[] args) {

        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);
        // print all available zoneIds
        System.out.println(ZoneId.getAvailableZoneIds());
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());

        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        System.out.println(now1.isBefore(now2));
        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
        LocalTime localTime = LocalTime.of(23,59,59);
        DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.GERMAN);
        LocalTime parse = LocalTime.parse("13:13", germanFormatter);

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);
        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        DateTimeFormatter chineseFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.CHINESE);
        LocalDate parse1 = LocalDate.parse("2019-12-24", chineseFormatter);

        // LocalDateTime String transfer
        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
        DayOfWeek dayOfWeek1 = sylvester.getDayOfWeek();
        Month month = sylvester.getMonth();
        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
        Instant instant1 = sylvester.atZone(ZoneId.systemDefault()).toInstant();
        Date legacyDate1 = Date.from(instant1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse2 = LocalDateTime.parse("2019-08-09 21:12:23", formatter);
        String dateString = formatter.format(parse2);

    }

}
