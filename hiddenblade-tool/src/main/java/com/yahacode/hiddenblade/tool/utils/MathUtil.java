package com.yahacode.hiddenblade.tool.utils;

import java.math.BigDecimal;

public class MathUtil {

    public static double precision(double number, int precision) {
        if (Double.isInfinite(number) || Double.isNaN(number)) {
            return 0.0;
        }
        BigDecimal decimal = new BigDecimal(number);
        return decimal.setScale(precision, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
