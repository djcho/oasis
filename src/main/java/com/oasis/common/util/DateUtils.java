package com.oasis.common.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;

public class DateUtils {

    public static LocalDate getFirstDayOfMonth(LocalDate date) {
        return date.withDayOfMonth(1);
    }
    public static LocalDate getLastDayOfMonth(LocalDate date) {
        return date.withDayOfMonth(date.lengthOfMonth());
    }

    public static int remainDaysOfMonth(LocalDate start, LocalDate end) {
        return Period.between(start,end).getDays();
    }

    public static int countByWorkingDay(LocalDate date) {
        LocalDate start = getFirstDayOfMonth(date);
        LocalDate end = getLastDayOfMonth(date).plusDays(1);

        long ret = start.datesUntil(end).filter(d -> isWorkingDay(d)).count();

        return (int)ret;
    }

    public static boolean isWorkingDay(LocalDate date) {
        DayOfWeek dow = date.getDayOfWeek();
        return (dow != DayOfWeek.SATURDAY && dow != DayOfWeek.SUNDAY);
    }
}
