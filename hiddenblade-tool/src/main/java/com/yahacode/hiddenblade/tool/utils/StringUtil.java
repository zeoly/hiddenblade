package com.yahacode.hiddenblade.tool.utils;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * String type utilities
 *
 * @author zengyongli
 * @since 2020/02/27
 */
public class StringUtil extends StringUtils {

    private static final Pattern numericPattern = Pattern.compile("[+-]?[0-9]+(\\.[0-9]+)?");

    private static final Pattern yearPattern = Pattern.compile("(19|20|21)\\d{2}");

    private static final Pattern yearMonthPattern = Pattern.compile("(19|20|21)\\d{2}[-|/]((0[1-9])|(1[0-2]))");

    private static final Pattern datePattern = Pattern.compile("(19|20|21)\\d{2}[-|/]((0[1-9])|(1[0-2]))[-|/]((0[1-9])|([12][0-9])|(3[0-1]))");

    private static final Pattern hourMinutePattern = Pattern.compile("(([01][0-9])|(2[0-3]))(:[0-5][0-9])");

    private static final Pattern timePattern = Pattern.compile("(([01][0-9])|(2[0-3]))(:[0-5][0-9]){2}");

    private static final Pattern vinPattern = Pattern.compile("[A-HJ-NPR-Z\\d]{17}");

    private static final Pattern plateNoOriginPattern = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使][A-Z][A-HJ-NP-Z0-9]{4}[A-Z0-9领挂学警港澳]$");

    private static final Pattern plateNoGreenPattern = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼][A-Z][A-HJ-NP-Z0-9]{6}$");

    private static final Pattern plateNoWJPattern = Pattern.compile("^WJ[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼][0-9A-Z]{5}");

    /**
     * not only digit char, include symbol and dot
     *
     * @param str the String to check
     * @return true if is numeric format
     */
    public static Boolean isNumericFormat(String str) {
        return isPattern(str, numericPattern);
    }

    /**
     * check format with "123.456,78.9"
     *
     * @param str the String to check
     * @return true if is coordinates
     */
    public static Boolean isCoordinates(String str) {
        if (isEmpty(str)) {
            return false;
        }
        String[] coordinates = str.split(",");
        if (coordinates.length == 2 && isNumericFormat(coordinates[0]) && isNumericFormat(coordinates[1])) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean isJson(String str) {
        if ("null".equals(str) || isEmpty(str)) {
            return false;
        }
        JsonNode jsonNode = JsonUtil.toJsonNode(str);
        return jsonNode != null;
    }

    public static boolean isPattern(String str, String pattern) {
        Pattern p = Pattern.compile(pattern);
        return isPattern(str, p);
    }

    private static boolean isPattern(String str, Pattern p) {
        if (str == null) {
            return false;
        }
        if (p.matcher(str).matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isYear(String str) {
        return isPattern(str, yearPattern);
    }

    public static boolean isYearMonth(String str) {
        return isPattern(str, yearMonthPattern);
    }

    public static boolean isDate(String str) {
        return isPattern(str, datePattern);
    }

    public static boolean isHourMinute(String str) {
        return isPattern(str, hourMinutePattern);
    }

    public static boolean isTime(String str) {
        return isPattern(str, timePattern);
    }

    public static boolean isDateHourMinute(String str) {
        String[] part = str.split(" ");
        if (part.length != 2) {
            return false;
        }
        return isDate(part[0]) && isHourMinute(part[1]);
    }

    public static boolean isDateTime(String str) {
        String[] part = str.split(" ");
        if (part.length != 2) {
            return false;
        }
        return isDate(part[0]) && isTime(part[1]);
    }

    public static boolean isVIN(String str) {
        return isPattern(str, vinPattern);
    }

    public static boolean isOriginPlateNo(String str) {
        return isPattern(str, plateNoOriginPattern);
    }

    public static boolean isGreenPlateNo(String str) {
        return isPattern(str, plateNoGreenPattern);
    }

    public static boolean isWJPlateNo(String str) {
        return isPattern(str, plateNoWJPattern);
    }

    public static boolean isPlateNo(String str) {
        return isOriginPlateNo(str) || isGreenPlateNo(str) || isWJPlateNo(str);
    }

    public static String toHex2(int i) {
        if (i > 255 || i < 0) {
            return null;
        }
        return String.format("%02x", i).toUpperCase();
    }

    public static String toHex4(int i) {
        if (i > 65535 || i < 0) {
            return null;
        }
        return String.format("%04x", i).toUpperCase();
    }

    public static String toHex8(long i) {
        if (i > 4294967295L || i < 0) {
            return null;
        }
        return String.format("%08x", i).toUpperCase();
    }
}
