package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }

    public static boolean isBetweenHalfOpen(LocalDateTime lt, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return lt.getHour() >= (startTime.getHour()) &&  lt.getHour() < (endTime.getHour())
                && lt.getDayOfYear() >= (startDate.getDayOfYear()) && lt.getDayOfYear() < (endDate.getDayOfYear());
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}

