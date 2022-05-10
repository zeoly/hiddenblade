package com.yahacode.hiddenblade.tool.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private static final Logger log = LoggerFactory.getLogger(DateUtil.class);

    public static final String PATTERN_FULL = "yyyy-MM-dd HH:mm:ss";

    public static final String PATTERN_FULL_SHORT = "yyyyMMddHHmmss";

    public static final String PATTERN_DATE = "yyyy-MM-dd";

    public static final String PATTERN_DATE_SHORT = "yyyyMMdd";

    public static final String PATTERN_TIME = "HH:mm:ss";

    public static String format(Date date, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static String formatFull(Date date) {
        return format(date, PATTERN_FULL);
    }

    public static String formatFullShort(Date date) {
        return format(date, PATTERN_FULL_SHORT);
    }

    public static String formatDate(Date date) {
        return format(date, PATTERN_DATE);
    }

    public static String formatDateShort(Date date) {
        return format(date, PATTERN_DATE_SHORT);
    }

    public static String formatTime(Date date) {
        return format(date, PATTERN_TIME);
    }

    public static Date parse(String str, String pattern) {
        try {
            DateFormat df = new SimpleDateFormat(pattern);
            return df.parse(str);
        } catch (ParseException e) {
            log.warn("parse date string error: {}, pattern: {}", str, pattern, e);
            return null;
        }
    }

    public static Date parseFull(String str) {
        return parse(str, PATTERN_FULL);
    }

    public static Date parseFullShort(String str) {
        return parse(str, PATTERN_FULL_SHORT);
    }

    public static Date add(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    public static Date addYear(Date date, int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    public static Date addMonth(Date date, int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    public static Date addDay(Date date, int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    public static Date addHour(Date date, int amount) {
        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    public static Date addMinute(Date date, int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    public static Date addSecond(Date date, int amount) {
        return add(date, Calendar.SECOND, amount);
    }

    public static Date atTime(Date date, int hour, int minute, int second, int millisecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
        return calendar.getTime();
    }

    public static Date atStartOfDay(Date date) {
        return atTime(date, 0, 0, 0, 0);
    }

    public static Date atEndOfDay(Date date) {
        return atTime(date, 23, 59, 59, 999);
    }

    public static boolean isAround(Date date, Date target, int second) {
        if (date.before(addSecond(target, -second)) || date.after(addSecond(target, second))) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isAM(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 0 && hour <= 11) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPM(Date date) {
        return !isAM(date);
    }

    public static boolean isSameDay(Date date1, Date date2) {
        return formatDate(date1).equals(formatDate(date2));
    }
}
