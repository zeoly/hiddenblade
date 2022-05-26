package com.yahacode.hiddenblade.tool.utils;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DateUtilTest {

    @Test
    void atStartOfDay() {
        Date date1 = DateUtil.atStartOfDay(DateUtil.parseFull("2022-07-06 03:06:07"));
        assertNotNull(date1);
        Date date2 = DateUtil.atStartOfDay(DateUtil.parseFull("2022-04-28 23:06:07"));
        assertNotNull(date2);
    }

    @Test
    void parseDate() {
        Date date = DateUtil.parseDate("2022-03-03");
        assertNotNull(date);
    }
}