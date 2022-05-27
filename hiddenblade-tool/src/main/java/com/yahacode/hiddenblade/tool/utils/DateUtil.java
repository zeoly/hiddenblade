package com.yahacode.hiddenblade.tool.utils;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil extends DateUtils {

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
        } catch (Exception e) {
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

    public static Date parseDate(String str) {
        return parse(str, PATTERN_DATE);
    }

    public static Date atTime(Date date, int hour, int minute, int second, int millisecond) {
        Calendar calendar = toCalendar(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
        return calendar.getTime();
    }

    public static Date atStartOfDay(Date date) {
        return truncate(date, Calendar.DAY_OF_MONTH);
    }

    public static Date atEndOfDay(Date date) {
        return atTime(date, 23, 59, 59, 999);
    }

    public static boolean isAround(Date date, Date target, int second) {
        if (date.before(addSeconds(target, -second)) || date.after(addSeconds(target, second))) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isAM(Date date) {
        Calendar calendar = toCalendar(date);
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
}