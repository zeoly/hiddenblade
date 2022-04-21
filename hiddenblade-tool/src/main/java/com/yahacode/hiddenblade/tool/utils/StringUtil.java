package com.yahacode.hiddenblade.tool.utils;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.StringUtils;

/**
 * String type utilities
 *
 * @author zengyongli
 * @since 2020/02/27
 */
public class StringUtil extends StringUtils {

    /**
     * not only digit char, include symbol and dot
     *
     * @param str the String to check
     * @return true if is numeric format
     */
    public static boolean isNumericFormat(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches("[+-]?[0-9]+(\\.[0-9]+)?");
    }

    /**
     * check format with "123.456,78.9"
     *
     * @param str the String to check
     * @return true if is coordinates
     */
    public static boolean isCoordinates(String str) {
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

    public static boolean isJson(String str) {
        if ("null".equals(str) || isEmpty(str)) {
            return false;
        }
        JsonNode jsonNode = JsonUtil.toJsonNode(str);
        return jsonNode != null;
    }
}
