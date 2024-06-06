//package com.smartInvoice.util;
//
//
//import org.springframework.lang.Nullable;
//
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.time.OffsetDateTime;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Objects;
//import java.util.TimeZone;
//
//import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;
//
//public class Dates {
//    public static SimpleDateFormat shortDate = new SimpleDateFormat("YYYY-MM-dd");
//    public static TimeZone TIME_ZONE = TimeZone.getTimeZone("Asia/Jerusalem");
//
//    public Dates() {
//    }
//
//    public static String dateToStr(@Nullable LocalDate date) {
//        return date == null ? null : shortDate.format(date);
//    }
//
//    public static Date atUtc(LocalDateTime date) {
//        return atUtc(date, TIME_ZONE);
//    }
//
//    public static Date atUtc(LocalDateTime date, TimeZone zone) {
//        if (date == null) return null;
//        Calendar calendar = Calendar.getInstance();
//        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
//        calendar.setTimeZone(zone);
//        calendar.set(date.getYear(), date.getDayOfYear()-1, date.getDayOfMonth());//convert from locatDateTime to Calender time
//        calendar.set(Calendar.HOUR_OF_DAY, date.getHour());
//        calendar.set(Calendar.MINUTE, date.getMinute());
//        calendar.set(Calendar.SECOND, date.getSecond());
//        calendar.set(Calendar.MILLISECOND, 0);
//        return calendar.getTime();
//    }
//
//    public static Date atUtc(@Nullable LocalDate date) {
//        return atUtc(date, TIME_ZONE);
//    }
//
//    public static Date atUtc(@Nullable LocalDate date, TimeZone zone) {
//        return date == null ? null : atUtc(date.atTime(LocalTime.MIDNIGHT), zone);
//    }
//
//    public static LocalDateTime atLocalTime(Date date) {
//        return atLocalTime(date, TIME_ZONE);
//    }
//
//    public static LocalDateTime atLocalTime(Date date, TimeZone zone) {
//        if (date == null) return null;
//        var localDate = OffsetDateTime.ofInstant(date.toInstant(), zone.toZoneId()).toLocalDateTime();
//        Calendar c = Calendar.getInstance();
//        c.set(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth());
//        c.set(Calendar.HOUR_OF_DAY, localDate.getHour());
//        c.set(Calendar.MINUTE, localDate.getMinute());
//        c.set(Calendar.SECOND, localDate.getSecond());
//        c.set(Calendar.MILLISECOND, 0);
//        LocalDateTime res;
//        res = LocalDateTime.from(c);
//        return res;
//    }
//
//    public static Date nowUTC() {
//        return DateTime.now().withZone(DateTime.UTC).toDate();
//    }
//toDate
//    public static String getFullDateTime() {
//        return DateTime.now().withZone(DateTimeZone.UTC).toDateTimeISO().toString();
//    }
//
//    public static boolean equals(@Nullable Date date1, @Nullable Date date2) {
//        if (date1 != null && date2 != null) {
//            return date1.getTime() == date2.getTime();
//        } else {
//            return Objects.equals(date1, date2);
//        }
//    }
//}